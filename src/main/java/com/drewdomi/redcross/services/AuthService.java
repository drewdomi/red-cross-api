package com.drewdomi.redcross.services;

import com.drewdomi.redcross.dtos.AuthResponse;
import com.drewdomi.redcross.repositories.RescuerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final RescuerRepository rescuerRepository;
    private final JwtService jwtService;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, RescuerRepository rescuerRepository, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.rescuerRepository = rescuerRepository;
        this.jwtService = jwtService;
    }

    public AuthResponse login(String email, String password) {
        var user = rescuerRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password));

        var token = jwtService.generateToken(user);

        return AuthResponse
            .builder()
            .token(token)
            .build();
    }
}
