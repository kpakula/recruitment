package com.kpakula.recruitment.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class ContactId implements Serializable {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ContactType type;
}
