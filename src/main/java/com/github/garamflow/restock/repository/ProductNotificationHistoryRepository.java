package com.github.garamflow.restock.repository;

import com.github.garamflow.restock.model.ProductNotificationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductNotificationHistoryRepository extends JpaRepository<ProductNotificationHistory, Long> {
    List<ProductNotificationHistory> findByProductIdOrderByCreatedAtDesc(Long productId);
}
