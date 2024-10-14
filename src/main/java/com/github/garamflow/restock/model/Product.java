package com.github.garamflow.restock.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer reStockRound;
    private Integer stock;
    LocalDateTime createdAt;

    public Product(String name, Integer stock) {
        this.name = name;
        this.stock = stock;
        this.reStockRound = 0; // 초기 재입고 회차는 0으로 설정
        this.createdAt = LocalDateTime.now();
    }

    protected Product() {}

    public void incrementReStockRound() {
        this.reStockRound++;
    }

    // 재고 감소 메서드 (재고가 부족하면 예외 발생)
    public void decreaseStock(int quantity) {
        if (this.stock - quantity < 0) {
            throw new IllegalStateException("재고가 부족합니다.");
        }
        this.stock -= quantity;
    }

    public boolean isOutOfStock() {
        return this.stock <= 0;
    }
}
