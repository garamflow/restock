package com.github.garamflow.restock.model;

// 상태를 enum으로 관리한다.
public enum NotificationStatus {
    IN_PROGRESS,
    COMPLETED,
    CANCELLED_BY_SOLD_OUT,
    CANCELLED_BY_ERROR,
}
