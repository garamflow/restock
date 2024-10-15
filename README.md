# 재입고 알림 시스템 (Restock Notification System)
이 프로젝트는 상품이 재입고 되었을 때, 해당 알림을 설정한 유저들에게 재입고 알림을 전송하는 시스템입니다. 이 시스템은 유저별로 알림을 순서대로 전송하며, 재고가 모두 소진되면 알림 전송을 중단합니다.

## Quick Start
```bash
   git clone https://github.com/your-repo/restock-notification.git
   cd restock-notification
   docker compose up -d --build
```

---

## 주요 기능

- **재입고 알림 전송**: 상품 재입고 시, 재입고 알림을 설정한 유저들에게 알림을 전송합니다.
- **재고 상태 관리**: 재고 상태를 `IN_STOCK`, `OUT_OF_STOCK`으로 관리하며, 재고가 소진되면 알림 전송을 중단합니다.
- **알림 전송 상태 관리**: `IN_PROGRESS`, `COMPLETED`, `CANCELLED_BY_SOLD_OUT`, `CANCELLED_BY_ERROR` 상태를 기록합니다.
- **속도 제한**: 1초에 최대 500개의 요청을 처리하는 속도 제한이 설정되어 있습니다.
- **알림 전송 이력 저장**: 재입고 알림 전송 내역을 기록하고, 재입고 회차 및 유저별 히스토리를 저장합니다.

---

## 기술 스택

- **Java 17**: 애플리케이션의 주 프로그래밍 언어
- **Spring Boot 3.x**: 웹 애플리케이션 프레임워크
- **MySQL 8.x**: 데이터베이스 관리
- **Bucket4j**: 속도 제한(Rate Limiting)을 위한 라이브러리
- **Lombok**: 코드 간결화
- **Docker & Docker Compose**: 애플리케이션 및 데이터베이스의 컨테이너화를 위한 도구
- **Hibernate**: JPA 구현체

---

## 테이블 구조

### 1. **Product (상품)**
- `id`: 상품 ID
- `name`: 상품명
- `reStockRound`: 재입고 회차
- `stockStatus`: 재고 상태 (enum: `IN_STOCK`, `OUT_OF_STOCK`)
- `createdAt`: 생성일

### 2. **ProductNotificationHistory (재입고 알림 히스토리)**
- `id`: 알림 히스토리 ID
- `product_id`: 상품 ID
- `status`: 알림 상태 (enum: `IN_PROGRESS`, `COMPLETED`, `CANCELLED_BY_SOLD_OUT`, `CANCELLED_BY_ERROR`)
- `lastNotifiedUserId`: 마지막 알림 발송 유저 ID
- `notificationSentAt`: 알림 발송 시각

### 3. **ProductUserNotification (상품별 재입고 알림을 설정한 유저)**
- `id`: 알림 설정 ID
- `product_id`: 상품 ID
- `user_id`: 유저 ID
- `isActive`: 알림 활성화 여부
- `createdAt`: 생성일
- `updatedAt`: 수정일

### 4. **ProductUserNotificationHistory (유저별 재입고 알림 히스토리)**
- `id`: 알림 히스토리 ID
- `product_id`: 상품 ID
- `user_id`: 유저 ID
- `notificationSentAt`: 알림 발송 시각

### 5. **User (사용자)**
- `id`: 사용자 ID
- `username`: 사용자 이름
- `createdAt`: 생성일

---

## API 명세

### 1. 재입고 알림 전송 API

- **Endpoint**: `POST /products/{productId}/notifications/re-stock`
- **Description**: 지정된 상품의 재입고 알림을 설정한 유저에게 알림을 전송합니다.
- **Request Parameters**:
    - `productId` (Long): 상품 ID
- **Response**: 알림 전송 결과를 반환합니다.

---

## 속도 제한 (Rate Limiting)

이 시스템은 **Bucket4j** 라이브러리를 사용하여 **1초에 최대 500개의 요청**을 처리하도록 설정되었습니다.

```java
@Service
public class RateLimiterService {
    private final Bucket bucket = Bucket.builder()
        .addLimit(limit -> limit.capacity(500).refillGreedy(500, Duration.ofSeconds(1)))  // 1초에 500개 리필
        .build();

    public boolean tryConsume() {
        return bucket.tryConsume(1);  // 1개의 요청 처리
    }
}
```