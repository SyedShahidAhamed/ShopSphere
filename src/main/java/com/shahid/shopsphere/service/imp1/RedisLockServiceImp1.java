package com.shahid.shopsphere.service.imp1;

import java.util.Collections;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.shahid.shopsphere.service.RedisLockService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisLockServiceImp1 implements RedisLockService {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean acquireLock(
            String lockKey,
            String lockValue,
            long expirySeconds) {

        Boolean acquired = redisTemplate.opsForValue()
                .setIfAbsent(
                        lockKey,
                        lockValue,
                        expirySeconds,
                        java.util.concurrent.TimeUnit.SECONDS
                );

        return Boolean.TRUE.equals(acquired);
    }

    @Override
    public void releaseLock(String lockKey, String lockValue) {

        String script = """
                if redis.call('get', KEYS[1]) == ARGV[1] then
                    return redis.call('del', KEYS[1])
                else
                    return 0
                end
                """;

        redisTemplate.execute(
                new org.springframework.data.redis.core.script.DefaultRedisScript<>(
                        script,
                        Long.class
                ),
                Collections.singletonList(lockKey),
                lockValue
        );
    }
}