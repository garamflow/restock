package com.github.garamflow.restock.notification.productusernotification.repository;

import com.github.garamflow.restock.model.ProductUserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductUserNotificationRepository extends JpaRepository<ProductUserNotification, Long> {
    // 특정 상품 알림 활성화된 사용자 목록을 가져온다
    List<ProductUserNotification> findByProductIdAndIsActiveTrue(Long productId);
}
