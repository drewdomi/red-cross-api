package com.drewdomi.redcross.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drewdomi.redcross.dtos.AuthResponse;
import com.drewdomi.redcross.dtos.LoginDto;
import com.drewdomi.redcross.services.AuthService;

@RestController
@RequestMapping("auth")
public class AuthController extends Object {

    private final AuthService authService;

    @Autowired
    public AuthController(
            AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto.email(), loginDto.password()));
    }

}
