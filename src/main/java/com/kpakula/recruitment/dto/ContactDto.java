package com.kpakula.recruitment.dto;

import com.kpakula.recruitment.model.ContactType;
import lombok.Data;

@Data
public class ContactDto {
    private ContactType type;
    private String value;
}
