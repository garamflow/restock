package com.github.garamflow.restock.repository;

import com.github.garamflow.restock.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
