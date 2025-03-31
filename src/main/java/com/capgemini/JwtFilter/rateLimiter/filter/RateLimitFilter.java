package com.capgemini.JwtFilter.rateLimiter.filter;

import com.capgemini.JwtFilter.rateLimiter.SimpleRateLimiter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component si lo necesitará lo descomento para que agarre el contexto de nuevos
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
        //System.out.println("x Request URI x: " + path);


      // this is for skip swagger path uri
        if (path.startsWith("/swagger") || path.startsWith("/v3/api-docs") || path.startsWith("/favicon")) {
            chain.doFilter(request, response);
            return;
        }

        String clientIp = httpRequest.getRemoteAddr();
        //System.out.println("x clientip x: " + clientIp);

        if (!rateLimiter.isAllowed(clientIp)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            //System.out.println("x httpResponse x: " + httpResponse);

            httpResponse.setStatus(429);
            httpResponse.getWriter().write("Too Many Requests");
            return;
        }

        chain.doFilter(request, response);
    }
}
