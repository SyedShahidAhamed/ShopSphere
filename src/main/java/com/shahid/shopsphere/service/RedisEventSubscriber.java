package com.shahid.shopsphere.service;

import org.springframework.stereotype.Service;

@Service
public class RedisEventSubscriber {

    public void receiveMessage(String message) {

        System.out.println(
            "📢 ORDER EVENT RECEIVED: " + message
        );
    }
}