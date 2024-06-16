package com.drewdomi.redcross.services;

import com.drewdomi.redcross.dtos.AmbulanceCreateDto;
import com.drewdomi.redcross.models.Ambulance;
import com.drewdomi.redcross.repositories.AmbulanceRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AmbulanceService {

    private final AmbulanceRepository ambulanceRepository;

    @Autowired
    public AmbulanceService(AmbulanceRepository ambulanceRepository) {
        this.ambulanceRepository = ambulanceRepository;
    }

    public void createAmbulance(AmbulanceCreateDto dto) {

        boolean numberExist = this.ambulanceRepository
            .findAmbulanceByNumber(dto.number()).isPresent();

        if (numberExist)
            throw new IllegalStateException("Number already in use");

        boolean plateExist = this.ambulanceRepository
            .findAmbulanceByPlate(dto.plate()).isPresent();

        if (plateExist)
            throw new IllegalStateException("Plate already in use");

        final var ambulance = new Ambulance(dto);
        this.ambulanceRepository.save(ambulance);
    }

    public List<Ambulance> findAllAmbulances() {
        return this.ambulanceRepository.findAll();
    }
    
    public Ambulance findAmbulanceById(UUID id) {
        return this.ambulanceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ambulance not found"));
    }

}
