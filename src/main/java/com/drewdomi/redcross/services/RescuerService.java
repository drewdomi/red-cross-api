package com.drewdomi.redcross.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drewdomi.redcross.dtos.RescuerCreateDto;
import com.drewdomi.redcross.models.Rescuer;
import com.drewdomi.redcross.repositories.RescuerRespository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RescuerService {

    @Autowired
    public final RescuerRespository rescuerRespository;

    public RescuerService(RescuerRespository rescuerRespository) {
        this.rescuerRespository = rescuerRespository;
    }

    public List<Rescuer> findAll() {
        return this.rescuerRespository.findAll();
    }

    public Rescuer registerRescuer(RescuerCreateDto dto) {
        boolean userExists = rescuerRespository
                .findByEmail(dto.email())
                .isPresent();

        if (userExists)
            throw new IllegalStateException("Email already in use");

        final var rescuer = new Rescuer(dto);
        return this.rescuerRespository.save(rescuer);
    }
}
