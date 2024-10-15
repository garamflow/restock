package com.github.garamflow.restock.notification.productusernotification.service;

import com.github.garamflow.restock.model.ProductUserNotificationHistory;

public interface ProductUserNotificationHistoryService {
    void saveUserNotificationHistory(ProductUserNotificationHistory history);
}
