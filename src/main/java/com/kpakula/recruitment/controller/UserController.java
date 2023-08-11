package com.kpakula.recruitment.controller;

import com.kpakula.recruitment.exception.ContactExistsException;
import com.kpakula.recruitment.exception.UserNotFoundException;
import com.kpakula.recruitment.model.Contact;
import com.kpakula.recruitment.model.ContactDto;
import com.kpakula.recruitment.model.UserDump;
import com.kpakula.recruitment.repository.ContactRepository;
import com.kpakula.recruitment.model.User;
import com.kpakula.recruitment.repository.UserDumpRepository;
import com.kpakula.recruitment.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;

    public UserController(UserRepository userRepository, ContactRepository contactRepository) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{pesel}")
    public User getUser(@PathVariable String pesel) {
        return userRepository.findByPesel(pesel).orElseThrow(() -> new UserNotFoundException(pesel));
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/{id}/contact")
    public Contact addContactToUser(@PathVariable Long id, @RequestBody ContactDto contactDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        for (Contact c : user.getContacts()) {
            if (c.getType().equals(contactDto.getType())) {
                throw new ContactExistsException(c.getUserId(), c.getType());
            }
        }

        Contact contact = new Contact();
        contact.setUserId(id);
        contact.setType(contactDto.getType());
        contact.setValue(contactDto.getValue());

        return contactRepository.save(contact);
    }

}
