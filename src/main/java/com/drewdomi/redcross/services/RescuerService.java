package com.drewdomi.redcross.services;

import com.drewdomi.redcross.dtos.RescuerCreateDto;
import com.drewdomi.redcross.models.Rescuer;
import com.drewdomi.redcross.projections.RescuerProjection;
import com.drewdomi.redcross.repositories.RescuerRespository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RescuerService {

    private final RescuerRespository rescuerRespository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RescuerService(RescuerRespository rescuerRespository, PasswordEncoder passwordEncoder) {
        this.rescuerRespository = rescuerRespository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<RescuerProjection> findAll() {
        return this.rescuerRespository.findAllBy();
    }

    public void registerRescuer(RescuerCreateDto dto) {
        boolean emailExists = rescuerRespository
            .findByEmail(dto.email())
            .isPresent();

        if (emailExists)
            throw new IllegalStateException("Email already in use");

        boolean numMechanographicAlreadyRegistered = rescuerRespository
            .findByNumMechanographic(dto.numMechanographic())
            .isPresent();

        if (numMechanographicAlreadyRegistered)
            throw new IllegalStateException("Num mechanographic already in use");

        final var rescuer = new Rescuer(dto);

        rescuer.setPassword(passwordEncoder.encode(rescuer.getPassword()));

        this.rescuerRespository.save(rescuer);
    }
}
