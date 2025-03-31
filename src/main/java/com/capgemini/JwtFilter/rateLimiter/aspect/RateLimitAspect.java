package com.capgemini.JwtFilter.rateLimiter.aspect;

import com.capgemini.JwtFilter.rateLimiter.SimpleRateLimiter;
import com.capgemini.JwtFilter.rateLimiter.exception.RateLimitExceededException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class RateLimitAspect {
    private final SimpleRateLimiter rateLimiter;
    private final HttpServletRequest request;

    @Before("@annotation(com.capgemini.JwtFilter.rateLimiter.annotation.RateLimited)")
    public void checkRateLimit() {
        String clientIp = request.getRemoteAddr();

        if(!rateLimiter.isAllowed(clientIp)){
            throw new RateLimitExceededException("Too many requests from this IP address: " + clientIp);
        }
    }
}
