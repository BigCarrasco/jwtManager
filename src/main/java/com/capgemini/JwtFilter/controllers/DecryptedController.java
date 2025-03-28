package com.capgemini.JwtFilter.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endpoint")
@SecurityRequirement(name = "bearerAuth") // Requiere autenticación JWT con swagger
public class DecryptedController {

    @GetMapping("/protected")
    public String protegido() {
        return "You are decrypted";
    }
}