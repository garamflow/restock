package com.github.garamflow.restock.repository;

import com.github.garamflow.restock.model.ProductUserNotificationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductUserNotificationRepository extends JpaRepository<ProductUserNotificationHistory, Long> {
    // 상품별, 유저별로 알림 히스토리 조회하기
    List<ProductUserNotificationHistory> findByProductIdAndUserId(Long productId, Long userId);

    // 특정 상품 재입고 회차별로 유저 히스토리 조회하기
    List<ProductUserNotificationHistory> findByProductIdAndReStockRound(Long productId, Integer reStockRound);
}
