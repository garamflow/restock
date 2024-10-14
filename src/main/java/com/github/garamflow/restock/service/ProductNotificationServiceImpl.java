package com.github.garamflow.restock.service;

import com.github.garamflow.restock.model.*;
import com.github.garamflow.restock.repository.ProductNotificationHistoryRepository;
import com.github.garamflow.restock.repository.ProductRepository;
import com.github.garamflow.restock.repository.ProductUserNotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductNotificationServiceImpl implements ProductNotificationService {

    private static final Logger log = LoggerFactory.getLogger(ProductNotificationServiceImpl.class);

    private final ProductNotificationHistoryRepository productNotificationHistoryRepository;
    private final ProductUserNotificationRepository productUserNotificationRepository;
    private final ProductRepository productRepository;

    public ProductNotificationServiceImpl(ProductNotificationHistoryRepository productNotificationHistoryRepository, ProductUserNotificationRepository productUserNotificationRepository, ProductRepository productRepository) {
        this.productNotificationHistoryRepository = productNotificationHistoryRepository;
        this.productUserNotificationRepository = productUserNotificationRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public void sendReStockNotification(Long productId) {

        // 상품 조회
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("상품이 존재하지 않습니다. " + productId));

        // 회차 증가
        product.incrementReStockRound();
        productRepository.save(product);

        // 알림 설정 유저 목록 조회
        List<ProductUserNotification> userNotifications = productUserNotificationRepository.findByProductId(productId);


        // 전송 상태 변경
        NotificationStatus status = NotificationStatus.IN_PROGRESS;
        Long lastNotifiedUserId = null;

        // 각 유저에게 알림 전송
        for (ProductUserNotification userNotification : userNotifications) {
            // 재고 없을 시 품절 상태로 변경
            if (product.isOutOfStock()) {
                status = NotificationStatus.CANCELLED_BY_SOLD_OUT;
                break;
            }

            try {
                sendNotification(product.getId(), userNotification.getUser().getId());
                product.decreaseStock(1);
                lastNotifiedUserId = userNotification.getUser().getId();
            } catch (Exception e) {
                status = NotificationStatus.CANCELLED_BY_ERROR;
                log.error("알림 전송 시 문제 발생: " + userNotification.getUser().getId(), e);
                break;
            }
        }

        if (status == NotificationStatus.IN_PROGRESS) status = NotificationStatus.COMPLETED;

        saveProductNotificationHistory(product, status, lastNotifiedUserId);
    }

    @Transactional
    @Override
    public List<ProductNotificationHistory> getNotificationHistory(Long productId) {
        try {
            return productNotificationHistoryRepository.findByProductIdOrderByCreatedAtDesc(productId);
        } catch (Exception e) {
            throw new RuntimeException("알림 전송 히스토리 조회 시 문제 발생", e);
        }
    }

    private void sendNotification(Long productId, Long userId) {
        // 실제 알림 발송 로직
    }

    private void saveProductNotificationHistory(Product product, NotificationStatus status, Long lastNotifiedUserId) {
        ProductNotificationHistory history = new ProductNotificationHistory(
                product, product.getReStockRound(), status, lastNotifiedUserId);
        productNotificationHistoryRepository.save(history);
    }
}
