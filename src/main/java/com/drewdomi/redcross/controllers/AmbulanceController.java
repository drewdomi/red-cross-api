package com.drewdomi.redcross.controllers;

import com.drewdomi.redcross.dtos.AmbulanceCreateDto;
import com.drewdomi.redcross.services.AmbulanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
