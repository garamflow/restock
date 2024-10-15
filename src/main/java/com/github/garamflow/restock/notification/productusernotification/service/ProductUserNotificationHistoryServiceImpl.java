package com.github.garamflow.restock.notification.productusernotification.service;

import com.github.garamflow.restock.model.ProductUserNotificationHistory;
import com.github.garamflow.restock.notification.productusernotification.repository.ProductUserNotificationHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductUserNotificationHistoryServiceImpl implements ProductUserNotificationHistoryService {
    private final ProductUserNotificationHistoryRepository productUserNotificationHistoryRepository;

    public ProductUserNotificationHistoryServiceImpl(ProductUserNotificationHistoryRepository productUserNotificationHistoryRepository) {
        this.productUserNotificationHistoryRepository = productUserNotificationHistoryRepository;
    }

    @Override
    public void saveUserNotificationHistory(ProductUserNotificationHistory history) {
        productUserNotificationHistoryRepository.save(history);
    }
}
