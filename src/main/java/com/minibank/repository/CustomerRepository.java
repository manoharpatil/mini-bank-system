package com.minibank.repository;

import com.minibank.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByNameAndLastnameAndPhoneNumberAndEmail(String name, String lastname, String phoneNumber, String email);

    @Query("SELECT c FROM Customer c WHERE " +
            "LOWER(c.name) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(c.lastname) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(c.email) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(c.phoneNumber) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(c.customerType) LIKE LOWER(CONCAT('%', :term, '%'))")

    Page<Customer> searchByTerm(@Param("term") String term, Pageable pageable);

}
