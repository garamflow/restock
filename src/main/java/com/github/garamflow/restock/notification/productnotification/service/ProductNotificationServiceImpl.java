package com.github.garamflow.restock.notification.productnotification.service;

import com.github.garamflow.restock.model.*;
import com.github.garamflow.restock.notification.productnotification.repository.ProductNotificationHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductNotificationServiceImpl implements ProductNotificationService {

    private final ProductNotificationHistoryRepository productNotificationHistoryRepository;


    public ProductNotificationServiceImpl(ProductNotificationHistoryRepository productNotificationHistoryRepository) {
        this.productNotificationHistoryRepository = productNotificationHistoryRepository;
    }

    @Override
    public List<ProductNotificationHistory> getNotificationHistoryByProduct(Long productId) {
        return productNotificationHistoryRepository.findByProductIdOrderByNotificationSentAt(productId);
    }

    @Override
    public void saveNotificationHistory(ProductNotificationHistory history) {
        productNotificationHistoryRepository.save(history);
    }
}
