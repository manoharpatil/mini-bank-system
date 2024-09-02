package com.minibank.dto;

import com.minibank.entity.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @NotEmpty(message = "Name must not be empty")
    private String name;

    @NotEmpty(message = "Lastname must not be empty")
    private String lastname;

    @NotEmpty(message = "Phone number must not be empty")
    private String phoneNumber;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Customer type must not be null")
    private CustomerType customerType;

    private Set<AddressDTO> addresses;
}
