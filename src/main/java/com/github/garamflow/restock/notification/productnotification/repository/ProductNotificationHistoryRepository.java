package com.github.garamflow.restock.notification.productnotification.repository;

import com.github.garamflow.restock.model.ProductNotificationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductNotificationHistoryRepository extends JpaRepository<ProductNotificationHistory, Long> {

    // notificationSentAt 필드를 기준으로 정렬
    List<ProductNotificationHistory> findByProductIdOrderByNotificationSentAt(Long productId);
}
