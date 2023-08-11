package com.kpakula.recruitment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpakula.recruitment.model.Contact;
import com.kpakula.recruitment.model.ContactType;
import com.kpakula.recruitment.model.User;
import com.kpakula.recruitment.repository.UserRepository;
import com.kpakula.recruitment.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private UserRepository userRepository;

    private final User user1 = new User(1L, "user1", "user1", "111111111111", Set.of(
            new Contact(1L, 1L, ContactType.EMAIL, "email@email.com"),
            new Contact(2L, 1L, ContactType.RESIDENCE_ADDRESS, "Krakow, Poland"),
            new Contact(3L, 1L, ContactType.REGISTERED_ADDRESS, "Krakow, Poland"),
            new Contact(4L, 1L, ContactType.PRIVATE_PHONE_NUMBER, "+48 XXX XXX XXX"),
            new Contact(5L, 1L, ContactType.BUSINESS_PHONE_NUMBER, "+48 XXX XXX XXX")
    ));
    private final User user2 = new User(2L, "user2", "user2", "111111111112", Set.of(
            new Contact(6L, 2L, ContactType.EMAIL, "email@email.com"),
            new Contact(7L, 2L, ContactType.RESIDENCE_ADDRESS, "Krakow, Poland")
    ));
    private final User user3 = new User(3L, "user3", "user3", "111111111113", Set.of(
            new Contact(8L, 3L, ContactType.PRIVATE_PHONE_NUMBER, "+48 XXX XXX XXX"),
            new Contact(9L, 3L, ContactType.BUSINESS_PHONE_NUMBER, "+48 XXX XXX XXX")
    ));


    @Test
    public void getAllUsers_success() throws Exception {
        List<User> users = new ArrayList<>(Arrays.asList(user1, user2, user3));

        Mockito.when(userRepository.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)));
    }
}
