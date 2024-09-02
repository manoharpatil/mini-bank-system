package com.minibank.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {

    private String name;
    private String lastname;
    private String phoneNumber;
    private String email;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonManagedReference // Annotation to handle the circular reference
    private Set<Address> addresses;
}
