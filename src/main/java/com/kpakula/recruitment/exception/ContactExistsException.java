package com.kpakula.recruitment.exception;

import com.kpakula.recruitment.model.ContactType;

public class ContactExistsException extends RuntimeException{
    public ContactExistsException(Long userId, ContactType type) {
        super(type + " already exists for userId: " + userId);
    }
}
