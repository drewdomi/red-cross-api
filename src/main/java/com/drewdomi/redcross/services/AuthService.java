package com.drewdomi.redcross.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.drewdomi.redcross.dtos.AuthResponse;
import com.drewdomi.redcross.repositories.RescuerRespository;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RescuerRespository rescuerRespository;

    @Autowired
    private JwtService jwtService;

    public AuthResponse login(String email, String password) {
        var user = rescuerRespository.findByEmail(email)
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
