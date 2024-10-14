package com.github.garamflow.restock.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ProductNotificationHistory {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer reStockRound; // 재입고 회차

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    private Long lastNotifiedUserId; // 마지막 알림 발송 유저 아이디

    private LocalDateTime createdAt;

    public ProductNotificationHistory(Product product, Integer reStockRound, NotificationStatus status, Long lastNotifiedUserId) {
        this.product = product;
        this.reStockRound = reStockRound;
        this.status = status;
        this.lastNotifiedUserId = lastNotifiedUserId;
        this.createdAt = LocalDateTime.now();
    }

    protected ProductNotificationHistory() {}
}
