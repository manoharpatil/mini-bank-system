package com.minibank.service;

import com.minibank.dto.CustomerDTO;
import com.minibank.entity.Customer;
import com.minibank.exception.CustomerAlreadyExistsException;
import com.minibank.exception.CustomerNotFoundException;
import com.minibank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(CustomerDTO customerDTO) {
        Optional<Customer> existingCustomer = customerRepository.findByNameAndLastnameAndPhoneNumberAndEmail(
                customerDTO.getName(), customerDTO.getLastname(), customerDTO.getPhoneNumber(), customerDTO.getEmail());

        if (existingCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists.");
        }

        Customer customer = Customer.builder()
                .name(customerDTO.getName())
                .lastname(customerDTO.getLastname())
                .phoneNumber(customerDTO.getPhoneNumber())
                .email(customerDTO.getEmail())
                .customerType(customerDTO.getCustomerType())
                .build();

        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long customerId, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));

        customer.setName(customerDTO.getName());
        customer.setLastname(customerDTO.getLastname());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setEmail(customerDTO.getEmail());
        customer.setCustomerType(customerDTO.getCustomerType());

        return customerRepository.save(customer);
    }

    public Page<Customer> searchCustomers(String term, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return customerRepository.searchByTerm(term, pageRequest);
    }
}
