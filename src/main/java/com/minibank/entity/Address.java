package com.minibank.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {

    private String city;
    private String street;
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference // Annotation to handle the circular reference
    private Customer customer;
}
