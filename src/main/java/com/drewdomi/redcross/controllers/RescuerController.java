package com.drewdomi.redcross.controllers;

import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drewdomi.redcross.services.RescuerService;
import com.drewdomi.redcross.models.Rescuer;

@RestController
public class RescuerController {

    private final RescuerService rescuerService;

    @Autowired
    public RescuerController(RescuerService rescuerService) {
        this.rescuerService = rescuerService;
    }

    @GetMapping("users")
    public ResponseEntity<List<Rescuer>> findAll() {
        final var users = rescuerService.findAll();
        return ResponseEntity.ok(users);
    }
}
