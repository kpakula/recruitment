package com.kpakula.recruitment.controller;

import com.kpakula.recruitment.exception.ContactExistsException;
import com.kpakula.recruitment.exception.UserNotFoundException;
import com.kpakula.recruitment.model.Contact;
import com.kpakula.recruitment.repository.ContactRepository;
import com.kpakula.recruitment.model.User;
import com.kpakula.recruitment.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
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

    @PostMapping("/{pesel}/contact")
    public User addContactToUser(@PathVariable String pesel, @RequestBody Contact contact) {
        User user = userRepository.findByPesel(pesel).orElseThrow(() -> new UserNotFoundException(pesel));

        for (Contact userContact : user.getContacts()) {
            if (userContact.getType().equals(contact.getType())) {
                throw new ContactExistsException(userContact.getUserId(), userContact.getType());
            }
        }

        contact.setUserId(user.getId());
        contactRepository.save(contact);

        return user;
    }

    @GetMapping("/downloadFile")
    public void downloadFile() {
        try {
            FileOutputStream fos  = new FileOutputStream("t.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userRepository.findAll());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
