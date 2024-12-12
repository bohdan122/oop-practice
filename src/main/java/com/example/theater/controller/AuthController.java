package com.example.theater.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    private static final long TOKEN_VALIDITY = 5 * 60 * 1000; // 5 хвилин
    private Map<String, Long> validTokens = new HashMap<>();

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        if (adminUsername.equals(username) && adminPassword.equals(password)) {
            long expirationTime = System.currentTimeMillis() + TOKEN_VALIDITY;
            String tokenData = username + ":" + expirationTime;
            String token = Base64.getEncoder().encodeToString(tokenData.getBytes(StandardCharsets.UTF_8));
            validTokens.put(token, expirationTime);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid username or password");
    }

    public boolean isTokenValid(String token) {
        try {
            String decodedToken = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
            String[] parts = decodedToken.split(":");
            if (parts.length == 2 && parts[0].equals(adminUsername)) {
                long expirationTime = Long.parseLong(parts[1]);
                return expirationTime > System.currentTimeMillis();
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
