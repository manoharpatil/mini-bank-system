package com.minibank.controller;

import com.minibank.dto.CustomerDTO;
import com.minibank.entity.Customer;
import com.minibank.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.createCustomer(customerDTO);
        return ResponseEntity.status(201).body(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Customer>> searchCustomers(@RequestParam String term, @RequestParam int page, @RequestParam int size) {
        Page<Customer> customers = customerService.searchCustomers(term, page, size);
        return ResponseEntity.ok(customers);
    }
}
