package com.drewdomi.redcross.controllers;

import com.drewdomi.redcross.dtos.AmbulanceCreateDto;
import com.drewdomi.redcross.models.Ambulance;
import com.drewdomi.redcross.services.AmbulanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("ambulance")
public class AmbulanceController {
    private final AmbulanceService ambulanceService;

    @Autowired
    public AmbulanceController(AmbulanceService ambulanceService) {
        this.ambulanceService = ambulanceService;
    }

    @PostMapping
    public ResponseEntity<Void> createAmbulance(@RequestBody @Valid AmbulanceCreateDto dto) {
        this.ambulanceService.createAmbulance(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Ambulance>> findAllAmbulances() {
        final var ambulances = this.ambulanceService.findAllAmbulances();
        return ResponseEntity.ok(ambulances);
    }

    @GetMapping("/{id}")
    public Ambulance findAmbulanceById(@PathVariable UUID id) {
        return this.ambulanceService.findAmbulanceById(id);
    }
}
