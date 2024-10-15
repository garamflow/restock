package com.github.garamflow.restock.notification.productnotification.service;

public interface ProductNotificationService {
    // 재입고 알림을 전송하는 메서드
    void sendReStockNotification(Long productId);
}
