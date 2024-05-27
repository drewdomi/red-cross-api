package com.drewdomi.redcross.services;

import com.drewdomi.redcross.dtos.RescuerCreateDto;
import com.drewdomi.redcross.dtos.RescuerDto;
import com.drewdomi.redcross.mappers.RescuerMapper;
import com.drewdomi.redcross.models.Rescuer;
import com.drewdomi.redcross.repositories.RescuerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class RescuerService {
    private final RescuerRepository rescuerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RescuerService(
        RescuerRepository rescuerRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.rescuerRepository = rescuerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<RescuerDto> findAll() {
        return this.rescuerRepository.findAllBy();
    }

    public void registerRescuer(RescuerCreateDto dto) {
        boolean emailExists = rescuerRepository
            .findByEmail(dto.email())
            .isPresent();

        if (emailExists)
            throw new IllegalStateException("Email already in use");

        boolean numMechanographicAlreadyRegistered = rescuerRepository
            .findByNumMechanographic(dto.numMechanographic())
            .isPresent();

        if (numMechanographicAlreadyRegistered)
            throw new IllegalStateException("Num mechanographic already in use");

        final var rescuer = new Rescuer(dto);

        rescuer.setPassword(passwordEncoder.encode(rescuer.getPassword()));

        this.rescuerRepository.save(rescuer);
    }

    public RescuerDto findById(UUID id) {
        final var rescuer = rescuerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Rescuer not found"));
        return RescuerMapper.INSTANCE.toProjection(rescuer);
    }

}
