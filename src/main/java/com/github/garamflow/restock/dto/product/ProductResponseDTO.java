package com.github.garamflow.restock.dto.product;

import com.github.garamflow.restock.model.StockStatus;

import java.time.LocalDateTime;

public record ProductResponseDTO(Long id, String name, Integer reStockRound, StockStatus stockStatus, LocalDateTime createdAt) {
}