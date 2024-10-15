package com.github.garamflow.restock.notification.productusernotification.service;

import com.github.garamflow.restock.model.ProductUserNotification;

import java.util.List;

public interface ProductUserNotificationService {
    List<ProductUserNotification> getActiveUserNotifications(Long productId);

    void saveUserNotification(ProductUserNotification notification);
}
