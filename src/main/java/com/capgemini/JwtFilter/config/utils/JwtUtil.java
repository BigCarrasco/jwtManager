package com.capgemini.JwtFilter.config.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String secret = "miguelRANDOMangeljavalverSPRINGa12345678910111121345";
    private final Key key = Keys.hmacShaKeyFor(secret.getBytes());
    private final long expiration = 3600000; // 1 hora

    public String generateToken(String passwordToEncrypt) {
        return Jwts.builder()
                .setSubject(passwordToEncrypt)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }

    public String validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            return null;
        }
    }
}
