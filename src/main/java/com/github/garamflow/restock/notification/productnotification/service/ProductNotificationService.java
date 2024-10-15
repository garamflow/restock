package com.github.garamflow.restock.notification.productnotification.service;

import com.github.garamflow.restock.model.ProductNotificationHistory;

import java.util.List;

public interface ProductNotificationService {
    List<ProductNotificationHistory> getNotificationHistoryByProduct(Long productId);
    void saveNotificationHistory(ProductNotificationHistory history);
}
