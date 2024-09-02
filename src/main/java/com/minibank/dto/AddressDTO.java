package com.minibank.dto;

import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;

@Data
@Builder
public class AddressDTO {
    @NotEmpty(message = "City must not be empty")
    private String city;

    @NotEmpty(message = "Street must not be empty")
    private String street;

    private String zipCode;
}
