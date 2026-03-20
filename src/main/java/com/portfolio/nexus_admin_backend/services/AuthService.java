package com.portfolio.nexus_admin_backend.services;

import com.portfolio.nexus_admin_backend.dto.AuthResponse;
import com.portfolio.nexus_admin_backend.dto.LoginRequest;
import com.portfolio.nexus_admin_backend.dto.RegisterRequest;
import com.portfolio.nexus_admin_backend.models.Role;
import com.portfolio.nexus_admin_backend.models.User;
import com.portfolio.nexus_admin_backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService; // Inject the token factory!

    // Register method (The one we already had, but now it returns the Token in the
    // response)
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Error: The email is already registered.");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);

        var extraClaims = new java.util.HashMap<String, Object>();
        extraClaims.put("role", user.getRole().name());

        // Le pasamos esa caja a la máquina de tokens
        String jwtToken = jwtService.generateToken(extraClaims, user.getEmail());
        return new AuthResponse(jwtToken);
    }

    // NEW: Login method
    public AuthResponse login(LoginRequest request) {
        // 1. Search for the user in the database
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Compare the raw password with the saved hash
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }
        var extraClaims = new java.util.HashMap<String, Object>();
        extraClaims.put("role", user.getRole().name());

        String jwtToken = jwtService.generateToken(extraClaims, user.getEmail());
        return new AuthResponse(jwtToken);
    }
}