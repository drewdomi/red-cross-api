package com.drewdomi.redcross.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drewdomi.redcross.dtos.RescuerCreateDto;
import com.drewdomi.redcross.projections.RescuerProjection;
import com.drewdomi.redcross.services.RescuerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
public class RescuerController {

    @Autowired
    private RescuerService rescuerService;

    @GetMapping
    public ResponseEntity<List<RescuerProjection>> listAllRescuers() {
        final var users = rescuerService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("create")
    public ResponseEntity<Void> createRescuer(@RequestBody @Valid RescuerCreateDto dto) {
        rescuerService.registerRescuer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
