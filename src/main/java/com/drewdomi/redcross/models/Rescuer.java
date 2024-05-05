package com.drewdomi.redcross.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Rescuer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email
    private String email;

    @Size(min = 2, message = "Name must have at least 3 characters")
    @Pattern(regexp = "^[A-Z]+(.)*")
    @NotBlank(message = "Name mandatory")
    private String name;

    @Size(min = 8, message = "Password must have at least 8 characters")
    private String password;

    public Rescuer() {
    }

    public Rescuer(@Email String email,
            @Size(min = 2, message = "Name must have at least 3 characters") @Pattern(regexp = "^[A-Z]+(.)*") @NotBlank(message = "Name mandatory") String name,
            @Size(min = 8, message = "Password must have at least 8 characters") String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

}
