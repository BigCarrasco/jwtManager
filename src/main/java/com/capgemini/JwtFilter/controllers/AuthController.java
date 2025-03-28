package com.capgemini.JwtFilter.controllers;

import com.capgemini.JwtFilter.config.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/token")
    public String getToken(@RequestParam String passwordToEncrypt) {
        return jwtUtil.generateToken(passwordToEncrypt);
    }
}