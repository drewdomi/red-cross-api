package com.drewdomi.redcross.controllers;

import com.drewdomi.redcross.dtos.LoginDto;
import com.drewdomi.redcross.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {
        final var token = authService.login(loginDto.email(), loginDto.password());
        return ResponseEntity.ok(token);
    }
}
