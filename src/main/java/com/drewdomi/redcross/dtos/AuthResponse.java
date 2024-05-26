package com.drewdomi.redcross.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class AuthResponse {

    @JsonProperty("token")
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
