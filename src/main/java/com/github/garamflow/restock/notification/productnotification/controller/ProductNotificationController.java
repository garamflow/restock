package com.github.garamflow.restock.notification.productnotification.controller;

import com.github.garamflow.restock.notification.productnotification.service.ProductNotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products/{productId}/notifications")
public class ProductNotificationController {

    private final ProductNotificationService productNotificationService;

    public ProductNotificationController(ProductNotificationService productNotificationService) {
        this.productNotificationService = productNotificationService;
    }

    /**
     * 재입고 알림 전송 API
     * @param productId 재입고 알림을 보낼 상품 ID
     * @return 성공 메시지 또는 에러 메시지
     */
    @PostMapping("/re-stock")
    public ResponseEntity<String> sendReStockNotification(@PathVariable Long productId) {
        try {
            productNotificationService.sendReStockNotification(productId);
            return new ResponseEntity<>("알림 시작", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
