package com.github.garamflow.restock.common.ratelimiter;

import io.github.bucket4j.Bucket;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimiterService {

    // 1초당 500개의 요청을 허용하는 버킷을 생성
    private final Bucket bucket = Bucket.builder()
            .addLimit(limit -> limit.capacity(500).refillGreedy(500, Duration.ofSeconds(1)))  // 1초에 500개 리필
            .build();

    public boolean tryConsume() {
        // 버킷에서 1개의 요청을 소비
        return bucket.tryConsume(1);
    }
}
