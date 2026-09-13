package com.shahid.shopsphere.security;

import java.io.IOException;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RateLimitFilter extends OncePerRequestFilter {

    private final StringRedisTemplate redisTemplate;
    
     @Value("${rate-limit.enabled:true}")
    private boolean rateLimitEnabled;
    
    private static final int MAX_REQUESTS = 100;
    private static final Duration TIME_WINDOW = Duration.ofMinutes(1);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

         // Skip Redis rate limiting when disabled
        if (!rateLimitEnabled) {
            filterChain.doFilter(request, response);
            return;
        }

        String clientIp = request.getRemoteAddr();

        String key = "rate_limit:" + clientIp;

        Long requestCount = redisTemplate.opsForValue().increment(key);

        if (requestCount != null && requestCount == 1) {
            redisTemplate.expire(key, TIME_WINDOW);
        }

        if (requestCount != null && requestCount > MAX_REQUESTS) {

            response.setStatus(429);

            response.setContentType("application/json");

            response.getWriter().write("""
                    {
                        "success": false,
                        "message": "Too many requests"
                    }
                    """);

            return;
        }

        filterChain.doFilter(request, response);
    }
}