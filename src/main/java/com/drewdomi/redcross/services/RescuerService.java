package com.drewdomi.redcross.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.drewdomi.redcross.dtos.RescuerCreateDto;
import com.drewdomi.redcross.models.Rescuer;
import com.drewdomi.redcross.projections.RescuerProjection;
import com.drewdomi.redcross.repositories.RescuerRespository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RescuerService {

    @Autowired
    private RescuerRespository rescuerRespository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<RescuerProjection> findAll() {
        return this.rescuerRespository.findAllBy();
    }

    public Rescuer registerRescuer(RescuerCreateDto dto) {
        boolean emailExists = rescuerRespository
                .findByEmail(dto.email())
                .isPresent();

        if (emailExists)
            throw new IllegalStateException("Email already in use");

        boolean numMechanographicExists = rescuerRespository
                .findByNumMechanographic(dto.numMechanographic())
                .isPresent();

        if (numMechanographicExists)
            throw new IllegalStateException("Num mechanographic already in use");

        final var rescuer = new Rescuer(dto);
        rescuer.setPassword(
                passwordEncoder.encode(
                        rescuer.getPassword()));
        return this.rescuerRespository.save(rescuer);
    }
}
