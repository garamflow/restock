package com.github.garamflow.restock.product.service;

import com.github.garamflow.restock.model.Product;

public interface ProductService {
    Product getProductById(Long productId);

    void incrementReStockRound(Long productId);
}
