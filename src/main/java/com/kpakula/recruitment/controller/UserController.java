package com.kpakula.recruitment.controller;

import com.kpakula.recruitment.model.Contact;
import com.kpakula.recruitment.dto.ContactDto;
import com.kpakula.recruitment.model.User;
import com.kpakula.recruitment.dto.UserDto;
import com.kpakula.recruitment.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // here I decided to give a demonstration of two methods -
    // the first one fetching everything and the second one allowing to fetch everything with paging

    //    @GetMapping
    //    public List<User> getUsers() {
    //        return userService.findAll();
    //    }

    @GetMapping
    public List<User> getUsersPagination(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return userService.findAllPageable(pageNo, pageSize);
    }

    @GetMapping("/{pesel}")
    public User getUser(@PathVariable String pesel) {
        return userService.findByPesel(pesel);
    }

    @PostMapping
    public User addUser(@Valid @RequestBody UserDto userDto) {
        userService.checkIfUserExist(userDto.getPesel());
        return userService.save(userDto);
    }

    @PostMapping("/{id}/contact")
    public Contact addContactToUser(@PathVariable Long id, @RequestBody ContactDto contactDto) {
        return userService.saveContact(id, contactDto);
    }

}
