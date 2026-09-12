package com.shahid.shopsphere.service;

public interface RedisLockService {

    boolean acquireLock(String lockKey, String lockValue, long expirySeconds);

    void releaseLock(String lockKey, String lockValue);
}