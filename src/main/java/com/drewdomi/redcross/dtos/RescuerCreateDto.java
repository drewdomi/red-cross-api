package com.drewdomi.redcross.dtos;

import com.drewdomi.redcross.models.enums.AccessType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

public record RescuerCreateDto(
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 255, message = "Name invalid")
    String name,

    @Email(message = "Email invalid")
    @NotBlank(message = "Email is mandatory")
    @Size(min = 3, max = 255, message = "Email invalid")
    String email,

    @NotBlank(message = "Password is mandatory")
    @Pattern(
        regexp = "^(?=.*[a-zA-Z])[a-zA-Z\\d]{8,255}$",
        message = "Password must have at least 8 characters"
    )
    @Size(min = 8, max = 255, message = "Password invalid")
    String password,

    @Min(value = 100, message = "Num mechanographic invalid")
    @Max(value = 99999, message = "Num mechanographic invalid")
    Integer numMechanographic,

    @NotNull(message = "Access type is mandatory")
    @Enumerated(EnumType.STRING)
    AccessType accessType,

    Boolean isActive,

    Boolean firstAccess
) {
}
