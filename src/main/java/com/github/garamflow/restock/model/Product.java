package com.github.garamflow.restock.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer reStockRound;

    // 재고 상태 enum 관리
    @Enumerated(EnumType.STRING)
    private StockStatus stockStatus;

    LocalDateTime createdAt;

    public Product(String name, StockStatus stockStatus) {
        this.name = name;
        this.stockStatus = stockStatus;
        this.reStockRound = 0;  // 초기 재입고 회차는 0으로 설정
        this.createdAt = LocalDateTime.now();
    }

    protected Product() {}

    public void incrementReStockRound() {
        this.reStockRound++;
    }

    public boolean isOutOfStock() {
        return this.stockStatus == StockStatus.OUT_OF_STOCK;
    }
}
