package com.minibank.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@Audited
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int versionNum;

    private String createdBy;

    private LocalDateTime creationDate;

    private String lastModifiedBy;

    private LocalDateTime lastModifiedDate;
}
