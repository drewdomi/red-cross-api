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
import com.drewdomi.redcross.models.Rescuer;
import com.drewdomi.redcross.services.RescuerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
public class RescuerController {

    private final RescuerService rescuerService;

    @Autowired
    public RescuerController(RescuerService rescuerService) {
        this.rescuerService = rescuerService;
    }

    @GetMapping("")
    public ResponseEntity<List<Rescuer>> findAll() {
        final var users = rescuerService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("create")
    public ResponseEntity<String> save(@RequestBody @Valid RescuerCreateDto dto) {
        rescuerService.registerRescuer(dto);
        return new ResponseEntity<>("Rescuer created", HttpStatus.CREATED);
    }
}
