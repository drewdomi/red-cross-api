package com.drewdomi.redcross.dtos;

import com.drewdomi.redcross.models.enums.AccessType;

import java.time.LocalDateTime;
import java.util.UUID;

public record RescuerDto(
    UUID id,
    String name,
    String email,
    Integer numMechanographic,
    AccessType accessType,

    Boolean isActive,

    Boolean firstAccess,

    LocalDateTime createdAt,

    LocalDateTime updatedAt
) {
}
