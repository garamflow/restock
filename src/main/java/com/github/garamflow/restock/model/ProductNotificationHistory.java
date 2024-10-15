package com.github.garamflow.restock.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(indexes = {
        @Index(name="idx_product_id", columnList = "product_id"),
        @Index(name="idx_notificationSentAt", columnList = "notificationSentAt")
})
public class ProductNotificationHistory {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; // 상품과의 관계

    @Enumerated(EnumType.STRING)
    private NotificationStatus status; // 알림 상태

    // 마지막 알림 발송 유저의 아이디
    private Long lastNotifiedUserId;

    private LocalDateTime notificationSentAt;

    public ProductNotificationHistory(Product product, NotificationStatus status, Long lastNotifiedUserId) {
        this.product = product;
        this.status = status;
        this.lastNotifiedUserId = lastNotifiedUserId;
        this.notificationSentAt = LocalDateTime.now();
    }

    protected ProductNotificationHistory() {}

    public Integer getReSockRound() {
        return product.getReStockRound();
    }

    public void changeStatus(NotificationStatus newStatus) {
        this.status = newStatus;
    }
}
