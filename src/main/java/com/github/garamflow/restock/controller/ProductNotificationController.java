package com.github.garamflow.restock.controller;

import com.github.garamflow.restock.service.ProductNotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/{productId}/notifications")
public class ProductNotificationController {

    private final ProductNotificationService productNotificationService;

    public ProductNotificationController(ProductNotificationService productNotificationService) {
        this.productNotificationService = productNotificationService;
    }

    @PostMapping("/re-stock")
    public ResponseEntity<String> sendReStockNotification(@PathVariable Long productId) {
        try {
            productNotificationService.sendReStockNotification(productId);
            return new ResponseEntity<>("알림 전송 성공", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
