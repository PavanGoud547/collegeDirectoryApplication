package com.example.college_directory.security;

import org.springframework.stereotype.Component;

@Component
public class JwtManager {

    private final JwtUtil jwtUtil;

    public JwtManager(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String generateToken(String username) {
        return jwtUtil.generateToken(username);
    }

    public boolean validateToken(String token, String username) {
        return jwtUtil.validateToken(token, username);
    }

    public String extractUsername(String token) {
        return jwtUtil.extractUsername(token);
    }
}
