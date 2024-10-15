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

    // 알림 발송 날짜
    private LocalDateTime notificationSentAt;

    public ProductUserNotificationHistory(Product product, User user, LocalDateTime notificationSentAt) {
        this.product = product;
        this.user = user;
        this.notificationSentAt = notificationSentAt;
    }

    protected ProductUserNotificationHistory() {}

    public Integer getReStockRound() {
        return product.getReStockRound();
    }
}
