package com.github.garamflow.restock.product.service;

import com.github.garamflow.restock.model.Product;

public interface ProductService {
    Product getProductById(Long productId);
    void incrementStockRound(Long productId);
    boolean isOutofStock(Long productId);
}
