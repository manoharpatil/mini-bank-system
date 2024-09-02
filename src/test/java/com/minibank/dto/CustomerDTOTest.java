package com.minibank.dto;

import com.minibank.entity.CustomerType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerDTOTest {

    @Test
    void testCustomerDTOBuilder() {
        CustomerDTO customer = CustomerDTO.builder()
                .name("John")
                .lastname("Doe")
                .phoneNumber("1234567890")
                .email("john.doe@example.com")
                .customerType(CustomerType.PRIVATE)
                .build();

        assertNotNull(customer);
        assertEquals("John", customer.getName());
        assertEquals("Doe", customer.getLastname());
        assertEquals("1234567890", customer.getPhoneNumber());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals(CustomerType.PRIVATE, customer.getCustomerType());
    }

    @Test
    void testAddressDTOBuilder() {
        AddressDTO address = AddressDTO.builder()
                .street("123 Main St")
                .city("Sample City")
                .zipCode("12345")
                .build();

        assertNotNull(address);
        assertEquals("123 Main St", address.getStreet());
        assertEquals("Sample City", address.getCity());
        assertEquals("12345", address.getZipCode());
    }
}
