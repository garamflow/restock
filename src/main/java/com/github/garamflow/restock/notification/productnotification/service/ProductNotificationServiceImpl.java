package com.github.garamflow.restock.notification.productnotification.service;

import com.github.garamflow.restock.common.ratelimiter.RateLimiterService;
import com.github.garamflow.restock.model.Product;
import com.github.garamflow.restock.model.ProductNotificationHistory;
import com.github.garamflow.restock.model.NotificationStatus;
import com.github.garamflow.restock.model.ProductUserNotification;
import com.github.garamflow.restock.notification.productnotification.repository.ProductNotificationHistoryRepository;
import com.github.garamflow.restock.notification.productusernotification.service.ProductUserNotificationService;
import com.github.garamflow.restock.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductNotificationServiceImpl implements ProductNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(ProductNotificationServiceImpl.class);

    private final ProductService productService;
    private final ProductUserNotificationService userNotificationService;
    private final ProductNotificationHistoryRepository historyRepository;
    private final RateLimiterService rateLimiterService;  // RateLimiterService 주입

    public ProductNotificationServiceImpl(ProductService productService,
                                          ProductUserNotificationService userNotificationService,
                                          ProductNotificationHistoryRepository historyRepository, RateLimiterService rateLimiterService) {
        this.productService = productService;
        this.userNotificationService = userNotificationService;
        this.historyRepository = historyRepository;
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    @Transactional
    public void sendReStockNotification(Long productId) {
        // 1. 상품 조회 및 재고 상태 확인
        Product product = productService.getProductById(productId);
        if (product.isOutOfStock()) {
            throw new IllegalStateException("Product is out of stock.");
        }

        // 2. 재입고 회차 증가
        productService.incrementReStockRound(productId);

        // 3. 알림 전송할 유저 목록 조회
        List<ProductUserNotification> activeUserNotifications = userNotificationService.getActiveUserNotifications(productId);

        // 4. 알림 상태: 전송 시작 (IN_PROGRESS)
        for (ProductUserNotification userNotification : activeUserNotifications) {
            if(rateLimiterService.tryConsume()) {
                try {
                    // 알림 전송 시작 (IN_PROGRESS)
                    createAndSaveHistory(product, userNotification.getUser().getId(), NotificationStatus.IN_PROGRESS);

                    // 실제 알림 전송 로직 (여기서는 로그로 대체)
                    logger.info("Notification sent to user {} for product {}", userNotification.getUser().getUsername(), product.getName());

                    // 알림 상태: 전송 완료 (COMPLETED)
                    createAndSaveHistory(product, userNotification.getUser().getId(), NotificationStatus.COMPLETED);

                } catch (Exception e) {
                    logger.error("Failed to send notification to user {}", userNotification.getUser().getUsername(), e);
                    createAndSaveHistory(product, userNotification.getUser().getId(), NotificationStatus.CANCELLED_BY_ERROR);
                    throw new RuntimeException("Failed to send notification to user " + userNotification.getUser().getUsername(), e);
                }

                // 5. 재고 확인 후 품절 상태일 경우 중단
                if (product.isOutOfStock()) {
                    createAndSaveHistory(product, userNotification.getUser().getId(), NotificationStatus.CANCELLED_BY_SOLD_OUT);
                    logger.warn("Notification process stopped due to out of stock for product {}", product.getName());

                    break;  // 품절로 인해 알림 전송 중단
                }
            } else {
                logger.warn("Rate Limit exceeded");
            }
        }
    }

    private void createAndSaveHistory(Product product, Long userId, NotificationStatus status) {
        ProductNotificationHistory history = new ProductNotificationHistory(product, status, userId);
        historyRepository.save(history);
    }
}
