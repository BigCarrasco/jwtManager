package com.capgemini.JwtFilter.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class rateLimiter {

    @GetMapping("/rateLimiter")
    public String hello() {
        return "Hola, mundo!";
    }
}
