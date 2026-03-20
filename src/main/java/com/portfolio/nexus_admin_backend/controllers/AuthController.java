package com.portfolio.nexus_admin_backend.controllers;

import com.portfolio.nexus_admin_backend.dto.AuthResponse;
import com.portfolio.nexus_admin_backend.dto.LoginRequest;
import com.portfolio.nexus_admin_backend.dto.RegisterRequest;
import com.portfolio.nexus_admin_backend.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}