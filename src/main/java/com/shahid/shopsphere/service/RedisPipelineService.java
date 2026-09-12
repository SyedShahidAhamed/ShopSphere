package com.shahid.shopsphere.service;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisPipelineService {

    private final RedisTemplate<String, String> redisTemplate;

    public void updateProductViews() {

        RedisCallback<Object> callback = connection -> {

            connection.stringCommands().set(
                "product:1:views".getBytes(),
                "100".getBytes()
            );

            connection.stringCommands().set(
                "product:2:views".getBytes(),
                "200".getBytes()
            );

            connection.stringCommands().set(
                "product:3:views".getBytes(),
                "300".getBytes()
            );

            connection.stringCommands().set(
                "product:4:views".getBytes(),
                "400".getBytes()
            );

            return null;
        };

        redisTemplate.executePipelined(callback);
    }
}