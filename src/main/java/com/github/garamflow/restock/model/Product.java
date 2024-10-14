package com.github.garamflow.restock.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
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
    public void incrementReStockRound() {
        this.reStockRound++;
    }
}
