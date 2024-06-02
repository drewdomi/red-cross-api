package com.drewdomi.redcross.controllers;

import com.drewdomi.redcross.dtos.RescuerCreateDto;
import com.drewdomi.redcross.dtos.RescuerDto;
import com.drewdomi.redcross.services.RescuerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("rescuer")
public class RescuerController {
    private final RescuerService rescuerService;

    @Autowired
    public RescuerController(RescuerService rescuerService) {
        this.rescuerService = rescuerService;
    }

    @GetMapping
    public ResponseEntity<List<RescuerDto>> listAllRescuers() {
        final var users = rescuerService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<Void> createRescuer(@RequestBody @Valid RescuerCreateDto dto) {
        rescuerService.registerRescuer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RescuerDto> findById(@PathVariable UUID id) {
        final var rescuer = rescuerService.findById(id);
        return ResponseEntity.ok(rescuer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        rescuerService.deleteById(id);
        return null;
    }
}
