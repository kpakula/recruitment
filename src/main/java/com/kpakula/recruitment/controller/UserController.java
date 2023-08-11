package com.kpakula.recruitment.controller;

import com.kpakula.recruitment.model.Contact;
import com.kpakula.recruitment.dto.ContactDto;
import com.kpakula.recruitment.model.User;
import com.kpakula.recruitment.dto.UserDto;
import com.kpakula.recruitment.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{pesel}")
    public User getUser(@PathVariable String pesel) {
        return userService.findByPesel(pesel);
    }

    @PostMapping
    public User addUser(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @PostMapping("/{id}/contact")
    public Contact addContactToUser(@PathVariable Long id, @RequestBody ContactDto contactDto) {
        return userService.saveContact(id, contactDto);
    }

}
