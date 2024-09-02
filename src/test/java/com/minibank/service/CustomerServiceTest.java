package com.minibank.service;

import com.minibank.dto.CustomerDTO;
import com.minibank.entity.Customer;
import com.minibank.entity.CustomerType;
import com.minibank.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCustomer_Success() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .name("John")
                .lastname("Doe")
                .phoneNumber("1234567890")
                .email("john.doe@example.com")
                .customerType(CustomerType.PRIVATE)
                .build();

        when(customerRepository.findByNameAndLastnameAndPhoneNumberAndEmail(
                "John", "Doe", "1234567890", "john.doe@example.com")).thenReturn(Optional.empty());

        Customer savedCustomer = new Customer();
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        Customer result = customerService.createCustomer(customerDTO);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testCreateCustomer_AlreadyExists() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .name("John")
                .lastname("Doe")
                .phoneNumber("1234567890")
                .email("john.doe@example.com")
                .customerType(CustomerType.PRIVATE)
                .build();

        when(customerRepository.findByNameAndLastnameAndPhoneNumberAndEmail(
                "John", "Doe", "1234567890", "john.doe@example.com")).thenReturn(Optional.of(new Customer()));

        assertThrows(RuntimeException.class, () -> customerService.createCustomer(customerDTO));
    }

    @Test
    void testUpdateCustomer_Success() {
        Customer existingCustomer = new Customer();
        existingCustomer.setId(1L);

        CustomerDTO customerDTO = CustomerDTO.builder()
                .name("John")
                .lastname("Doe Updated")
                .phoneNumber("1234567890")
                .email("john.doe@example.com")
                .customerType(CustomerType.PRIVATE)
                .build();

        when(customerRepository.findById(1L)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(existingCustomer);

        Customer updatedCustomer = customerService.updateCustomer(1L, customerDTO);
        assertNotNull(updatedCustomer);
        assertEquals("Doe Updated", updatedCustomer.getLastname());
    }

    @Test
    void testUpdateCustomer_NotFound() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .name("John")
                .lastname("Doe Updated")
                .phoneNumber("1234567890")
                .email("john.doe@example.com")
                .customerType(CustomerType.PRIVATE)
                .build();

        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> customerService.updateCustomer(1L, customerDTO));
    }

    @Test
    void testSearchCustomers_Success() {
        Customer customer = new Customer();
        customer.setName("John");

        Page<Customer> page = new PageImpl<>(Collections.singletonList(customer));

        when(customerRepository.findAll(PageRequest.of(0, 5))).thenReturn(page);

        Page<Customer> result = customerService.searchCustomers("John", 0, 5);
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("John", result.getContent().get(0).getName());
    }
}
