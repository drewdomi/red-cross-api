package com.drewdomi.redcross.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

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
    String password
) { }
