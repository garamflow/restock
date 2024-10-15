package com.github.garamflow.restock.dto.product;

import com.github.garamflow.restock.model.StockStatus;

public record ProductRequestDTO(String name, StockStatus stockStatus) {
}
