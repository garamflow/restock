package com.github.garamflow.restock.product.service;

import com.github.garamflow.restock.model.Product;
import com.github.garamflow.restock.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found ID: " + productId));
    }

    @Override
    public void incrementReStockRound(Long productId) {
        Product product = getProductById(productId);
        product.incrementReStockRound();
        productRepository.save(product);
    }
}
