package com.drewdomi.redcross.repositories;

import com.drewdomi.redcross.models.Ambulance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AmbulanceRepository extends JpaRepository<Ambulance, UUID> {

    Optional<Ambulance> findAmbulanceByNumber(String number);

    Optional<Ambulance> findAmbulanceByPlate(String plate);
}
