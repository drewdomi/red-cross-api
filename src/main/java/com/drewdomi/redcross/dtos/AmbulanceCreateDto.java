package com.drewdomi.redcross.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AmbulanceCreateDto(

    @NotBlank(message = "Brand is mandatory")
    @Size(min = 2, max = 255, message = "Brand invalid")
    String brand,

    @NotBlank(message = "Plate is mandatory")
    @Size(min = 2, max = 255, message = "Plate invalid")
    String plate,

    @NotBlank(message = "Number is mandatory")
    @Size(min = 2, max = 255, message = "Number invalid")
    String number,

    Boolean status

) {
}
