package com.example.ecommerceapp.frameworks.security;

import com.example.ecommerceapp.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "super-secret-key-super-secret-key-super-secret-key";

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // ===== CREATE TOKEN =====
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)
                ) // 24h
                .signWith(key)
                .compact();
    }

    // ===== READ TOKEN =====
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ===== VALIDATE TOKEN =====
    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ===== INTERNAL =====
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
