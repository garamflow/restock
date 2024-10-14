package com.github.garamflow.restock.service;

import com.github.garamflow.restock.model.ProductNotificationHistory;

import java.util.List;

public interface ProductNotificationService {
    void sendReStockNotification(Long productId);
    List<ProductNotificationHistory> getNotificationHistory(Long productId);
}
