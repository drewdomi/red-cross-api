package com.drewdomi.redcross.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drewdomi.redcross.models.Rescuer;

@Repository
public interface RescuerRespository extends JpaRepository<Rescuer, Long> {
}
