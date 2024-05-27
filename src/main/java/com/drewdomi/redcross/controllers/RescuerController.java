package com.drewdomi.redcross.controllers;

import com.drewdomi.redcross.dtos.RescuerCreateDto;
import com.drewdomi.redcross.projections.RescuerProjection;
import com.drewdomi.redcross.services.RescuerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class RescuerController {

    private final RescuerService rescuerService;

    @Autowired
    public RescuerController(RescuerService rescuerService) {
        this.rescuerService = rescuerService;
    }

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
