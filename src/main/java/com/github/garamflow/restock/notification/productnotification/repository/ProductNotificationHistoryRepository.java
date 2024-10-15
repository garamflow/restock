package com.github.garamflow.restock.notification.productnotification.repository;

import com.github.garamflow.restock.model.ProductNotificationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductNotificationHistoryRepository extends JpaRepository<ProductNotificationHistory, Long> {

    // 특정 상품 알림 히스토리를 최신순으로 가져온다
    List<ProductNotificationHistory> findByProductIdOrderByNotificationSentAt(Long productId);
}
