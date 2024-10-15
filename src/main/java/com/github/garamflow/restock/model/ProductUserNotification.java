package com.github.garamflow.restock.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(indexes = {
        @Index(name = "idx_product_user", columnList = "product_id, user_id"),
        @Index(name = "idx_isActive", columnList = "isActive")
})
public class ProductUserNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 알림 활성화 여부
    private Boolean isActive;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductUserNotification(Product product, User user, Boolean isActive) {
        this.product = product;
        this.user = user;
        this.isActive = isActive;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    protected ProductUserNotification() {}
}
