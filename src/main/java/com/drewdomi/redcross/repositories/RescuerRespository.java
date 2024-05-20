package com.drewdomi.redcross.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drewdomi.redcross.models.Rescuer;
import com.drewdomi.redcross.projections.RescuerProjection;

@Repository
public interface RescuerRespository extends JpaRepository<Rescuer, UUID> {
    Optional<Rescuer> findByEmail(String email);

    Optional<Rescuer> findByNumMechanographic(Integer numMechanographic);

    List<RescuerProjection> findAllBy();
}
