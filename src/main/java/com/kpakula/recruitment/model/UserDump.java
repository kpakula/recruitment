package com.kpakula.recruitment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
public class UserDump {
    @Id
    private UUID uuid;

    private Instant createdDate;

    private String path;

}
