package com.kpakula.recruitment.model;

import lombok.Data;

@Data
public class ContactDto {
    private ContactType type;
    private String value;
}
