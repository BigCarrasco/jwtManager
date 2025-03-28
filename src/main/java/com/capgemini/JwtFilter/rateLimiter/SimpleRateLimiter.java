package com.capgemini.JwtFilter.rateLimiter;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SimpleRateLimiter {

    private final Map<String, RequestInfo> requestCounts = new ConcurrentHashMap<>();
    private final int MAX_REQUESTS = 5; // n peticiones
    private final long TIME_WINDOW_MS = 10_000; // en n milisegundos (10 segundos)

    public boolean isAllowed(String clientId) {
        long now = Instant.now().toEpochMilli();

        requestCounts.compute(clientId, (key, info) -> {
            if (info == null || now - info.timestamp > TIME_WINDOW_MS) {
                return new RequestInfo(1, now);
            } else {
                info.count++;
                return info;
            }
        });

        return requestCounts.get(clientId).count <= MAX_REQUESTS;
    }

    private static class RequestInfo {
        int count;
        long timestamp;

        RequestInfo(int count, long timestamp) {
            this.count = count;
            this.timestamp = timestamp;
        }
    }
}
