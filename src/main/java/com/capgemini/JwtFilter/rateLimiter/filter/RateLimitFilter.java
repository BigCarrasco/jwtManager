package com.capgemini.JwtFilter.rateLimiter.filter;

import com.capgemini.JwtFilter.rateLimiter.SimpleRateLimiter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RateLimitFilter implements Filter {

    private final SimpleRateLimiter rateLimiter;

    public RateLimitFilter(SimpleRateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();

        if (path.startsWith("/swagger") || path.startsWith("/v3/api-docs") || path.startsWith("/favicon")) {
            chain.doFilter(request, response);
            return;
        }

        String clientIp = httpRequest.getRemoteAddr();

        if (!rateLimiter.isAllowed(clientIp)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(429);
            httpResponse.getWriter().write("Too Many Requests");
            return;
        }

        chain.doFilter(request, response);
    }
}
