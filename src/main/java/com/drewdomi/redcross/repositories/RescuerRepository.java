package com.drewdomi.redcross.repositories;

import com.drewdomi.redcross.dtos.RescuerDto;
import com.drewdomi.redcross.models.Rescuer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface RescuerRepository extends JpaRepository<Rescuer, UUID> {
    Optional<Rescuer> findByEmail(String email);

    Optional<Rescuer> findByNumMechanographic(Integer numMechanographic);

    List<RescuerDto> findAllBy();

    @Query("SELECT r FROM Rescuer r WHERE r.isActive <> false")
    List<Rescuer> findAllIncludeInactive();
}
