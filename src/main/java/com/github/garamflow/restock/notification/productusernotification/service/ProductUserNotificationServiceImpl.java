package com.github.garamflow.restock.notification.productusernotification.service;

import com.github.garamflow.restock.model.ProductUserNotification;
import com.github.garamflow.restock.notification.productusernotification.repository.ProductUserNotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductUserNotificationServiceImpl implements ProductUserNotificationService {

    private final ProductUserNotificationRepository userNotificationRepository;

    public ProductUserNotificationServiceImpl(ProductUserNotificationRepository userNotificationRepository) {
        this.userNotificationRepository = userNotificationRepository;
    }

    @Override
    public List<ProductUserNotification> getActiveUserNotifications(Long productId) {
        return userNotificationRepository.findByProductIdAndIsActiveTrue(productId);
    }

    @Override
    public void saveUserNotification(ProductUserNotification notification) {
        userNotificationRepository.save(notification);
    }
}
