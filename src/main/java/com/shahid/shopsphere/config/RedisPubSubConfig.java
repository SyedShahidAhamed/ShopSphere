package com.shahid.shopsphere.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.shahid.shopsphere.service.RedisEventSubscriber;

import lombok.RequiredArgsConstructor;

@Configuration
@ConditionalOnProperty(
    name = "redis.pubsub.enabled",
    havingValue = "true",
    matchIfMissing = true
)
@RequiredArgsConstructor
public class RedisPubSubConfig {

    private final RedisEventSubscriber redisEventSubscriber;

    @Bean
    public ChannelTopic orderEventsTopic() {
        return new ChannelTopic("order-events");
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(redisEventSubscriber, "receiveMessage");
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
            RedisConnectionFactory connectionFactory,
            MessageListenerAdapter messageListenerAdapter,
            ChannelTopic orderEventsTopic) {

        RedisMessageListenerContainer container =
                new RedisMessageListenerContainer();

        container.setConnectionFactory(connectionFactory);

        container.addMessageListener(
                messageListenerAdapter,
                orderEventsTopic
        );

        return container;
    }
}