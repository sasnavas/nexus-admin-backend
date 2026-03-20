package com.portfolio.nexus_admin_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class TestController {

    @GetMapping("/dashboard")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("¡Bienvenido al panel secreto, jefe!");
    }

    @GetMapping("/reportes-financieros")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // El Guardia revisará el rol aquí
    public ResponseEntity<String> verReportes() {
        return ResponseEntity.ok("💰 [TOP SECRET] Ganancias del mes: $1,000,000");
    }
}