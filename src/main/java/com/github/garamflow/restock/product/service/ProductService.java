package com.github.garamflow.restock.product.service;

import com.github.garamflow.restock.model.Product;
import com.github.garamflow.restock.model.StockStatus;

public interface ProductService {
    Product getProductById(Long productId);

    void incrementReStockRound(Long productId);

    void createProduct(String name, StockStatus stockStatus);
}
