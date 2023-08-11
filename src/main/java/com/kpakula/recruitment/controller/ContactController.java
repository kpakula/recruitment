package com.kpakula.recruitment.controller;

import com.kpakula.recruitment.model.Contact;
import com.kpakula.recruitment.repository.ContactRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contact")
public class ContactController {
    private final ContactRepository repository;

    public ContactController(ContactRepository repository) {
        this.repository = repository;
    }

    @PostMapping()
    public Contact addContact(@RequestBody Contact contact) {
        return repository.save(contact);
    }
}
