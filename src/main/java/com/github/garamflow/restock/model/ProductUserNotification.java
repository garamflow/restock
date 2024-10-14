package com.github.garamflow.restock.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
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

    private Boolean isActive; // 알림 활성화 여부

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
