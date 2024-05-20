package com.drewdomi.redcross.projections;

import java.time.LocalDateTime;
import java.util.UUID;

import com.drewdomi.redcross.models.enums.AccessType;

public interface RescuerProjection {
    UUID getId();

    String getName();

    String getEmail();

    Integer getNumMechanographic();

    AccessType getAccessType();

    Boolean getIsActive();

    Boolean getFirstAccess();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
}
