//package com.minibank.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.minibank.dto.CustomerDTO;
//import com.minibank.entity.Customer;
//import com.minibank.entity.CustomerType;
//import com.minibank.service.CustomerService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(CustomerController.class)
//public class CustomerControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CustomerService customerService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    public void testCreateCustomer_Success() throws Exception {
//        CustomerDTO customerDto = CustomerDTO.builder()
//                .name("John")
//                .lastname("Doe")
//                .phoneNumber("1234567890")
//                .email("john.doe@example.com")
//                .customerType(CustomerType.PRIVATE)
//                .build();
//
//        Customer customer = new Customer();
//        customer.setId(1L);
//        customer.setName("John");
//        customer.setLastname("Doe");
//        customer.setPhoneNumber("1234567890");
//        customer.setEmail("john.doe@example.com");
//        customer.setCustomerType(CustomerType.PRIVATE);
//
//        when(customerService.createCustomer(any(CustomerDTO.class))).thenReturn(customer);
//
//        mockMvc.perform(post("/api/customers")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(customerDto)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("John"))
//                .andExpect(jsonPath("$.lastname").value("Doe"))
//                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
//
//        verify(customerService, times(1)).createCustomer(any(CustomerDTO.class));
//    }
//
//    @Test
//    public void testUpdateCustomer_Success() throws Exception {
//        CustomerDTO customerDto = CustomerDTO.builder()
//                .name("John")
//                .lastname("Smith")
//                .phoneNumber("1234567890")
//                .email("john.smith@example.com")
//                .customerType(CustomerType.INDIVIDUAL)
//                .build();
//
//        Customer customer = new Customer();
//        customer.setId(1L);
//        customer.setName("John");
//        customer.setLastname("Smith");
//        customer.setPhoneNumber("1234567890");
//        customer.setEmail("john.smith@example.com");
//        customer.setCustomerType(CustomerType.INDIVIDUAL);
//
//        when(customerService.updateCustomer(eq(1L), any(CustomerDTO.class))).thenReturn(customer);
//
//        mockMvc.perform(put("/api/customers/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(customerDto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.lastname").value("Smith"))
//                .andExpect(jsonPath("$.email").value("john.smith@example.com"));
//
//        verify(customerService, times(1)).updateCustomer(eq(1L), any(CustomerDTO.class));
//    }
//
//    @Test
//    public void testSearchCustomers_Success() throws Exception {
//        Customer customer = new Customer();
//        customer.setId(1L);
//        customer.setName("John");
//        customer.setLastname("Doe");
//        customer.setPhoneNumber("1234567890");
//        customer.setEmail("john.doe@example.com");
//        customer.setCustomerType(CustomerType.PRIVATE);
//
//        List<Customer> customers = new ArrayList<>();
//        customers.add(customer);
//
//        Page<Customer> customerPage = new PageImpl<>(customers, PageRequest.of(0, 5), 1);
//
//        when(customerService.searchCustomers(anyString(), anyInt(), anyInt())).thenReturn(customerPage);
//
//        mockMvc.perform(get("/api/customers/search?term=John&page=0&size=5"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content[0].id").value(1))
//                .andExpect(jsonPath("$.content[0].name").value("John"))
//                .andExpect(jsonPath("$.content[0].lastname").value("Doe"));
//
//        verify(customerService, times(1)).searchCustomers(anyString(), anyInt(), anyInt());
//    }
//
//    @Test
//    public void testCreateCustomer_ValidationError() throws Exception {
//        // Given an invalid customer DTO (missing mandatory fields)
//        CustomerDTO invalidCustomerDto = new CustomerDTO(); // All fields are empty or null
//
//        mockMvc.perform(post("/api/customers")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(invalidCustomerDto)))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.name").value("Name must not be empty"))
//                .andExpect(jsonPath("$.lastname").value("Lastname must not be empty"))
//                .andExpect(jsonPath("$.phoneNumber").value("Phone number must not be empty"))
//                .andExpect(jsonPath("$.email").value("Email must not be empty"))
//                .andExpect(jsonPath("$.customerType").value("Customer type must not be null"));
//    }
//}
