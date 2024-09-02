package com.minibank.repository;

import com.minibank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByNameAndLastnameAndPhoneNumberAndEmail(String name, String lastname, String phoneNumber, String email);
}
