package com.shahid.shopsphere.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisEventPublisher {

    private final RedisTemplate<String, String> redisTemplate;

    public void publishOrderPlaced(Long orderId) {

        String message = "Order " + orderId + " has been placed successfully!";

        redisTemplate.convertAndSend("order-events", message);
    }
}