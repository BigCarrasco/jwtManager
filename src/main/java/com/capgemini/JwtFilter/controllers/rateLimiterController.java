package com.capgemini.JwtFilter.controllers;

import com.capgemini.JwtFilter.rateLimiter.annotation.RateLimited;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class rateLimiterController {

    @RateLimited
    @GetMapping("/test")
    public String hello() {
        return "Hola, mundo!";
    }
}
