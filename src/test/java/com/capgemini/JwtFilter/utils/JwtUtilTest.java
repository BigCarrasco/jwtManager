package com.capgemini.JwtFilter.utils;

import com.capgemini.JwtFilter.config.utils.JwtUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Test
    public void testGenerateToken_NotNull() {
        String token = jwtUtil.generateToken("1234567890");
        assertNotNull(token);
    }
}
