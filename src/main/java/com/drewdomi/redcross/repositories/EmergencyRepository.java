package com.drewdomi.redcross.repositories;

import com.drewdomi.redcross.models.Emergency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmergencyRepository extends JpaRepository<Emergency, UUID> {
}
