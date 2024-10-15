package com.github.garamflow.restock.product.controller;

import com.github.garamflow.restock.dto.product.ProductRequestDTO;
import com.github.garamflow.restock.dto.product.ProductResponseDTO;
import com.github.garamflow.restock.model.Product;
import com.github.garamflow.restock.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);

        ProductResponseDTO productDTO = new ProductResponseDTO(
                product.getId(), product.getName(), product.getReStockRound(),
                product.getStockStatus(), product.getCreatedAt()
        );
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductRequestDTO requestDTO) {
        productService.createProduct(requestDTO.name(), requestDTO.stockStatus());
        return ResponseEntity.ok("Product created successfully");
    }
}
