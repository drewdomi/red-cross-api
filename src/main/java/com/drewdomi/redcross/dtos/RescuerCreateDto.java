package com.drewdomi.redcross.dtos;

import com.drewdomi.redcross.models.enums.AccessType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RescuerCreateDto(

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 255)
    String name,

    @Email
    @NotBlank(message = "Email is mandatory")
    @Size(min = 3, max = 255)
    String email,

    @NotBlank(message = "Password is mandatory")
    @Pattern(
        regexp = "^(?=.*[a-zA-Z])[a-zA-Z\\d]{8,60}$",
        message = "Password must have at least 8 characters"
    )
    @Size(min = 8, max = 255)
    String password,

    @Min(value = 100, message = "Num mechanographic invalid")
    @Max(value = 99999, message = "Num mechanographic invalid")

    Integer numMechanographic,

    @Enumerated(EnumType.STRING)
    AccessType accessType,

    Boolean isActive,

    Boolean firstAccess
) {
}
