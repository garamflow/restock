package com.github.garamflow.restock.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ProductUserNotificationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Integer reStockRound; // 재입고 회차

    private LocalDateTime notificationSentAt; // 발송 날짜

    public ProductUserNotificationHistory(Product product, User user, Integer reStockRound, LocalDateTime notificationSentAt) {
        this.product = product;
        this.user = user;
        this.reStockRound = reStockRound;
        this.notificationSentAt = notificationSentAt;
    }

    protected ProductUserNotificationHistory() {}
}
