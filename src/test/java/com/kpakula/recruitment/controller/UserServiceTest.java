package com.kpakula.recruitment.controller;

import com.kpakula.recruitment.dto.UserDto;
import com.kpakula.recruitment.exception.UserAlreadyExistsException;
import com.kpakula.recruitment.exception.UserNotFoundException;
import com.kpakula.recruitment.model.User;
import com.kpakula.recruitment.repository.ContactRepository;
import com.kpakula.recruitment.repository.UserRepository;
import com.kpakula.recruitment.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private ContactRepository contactRepository;
    private UserService userService;

    @BeforeEach
    void setup() {
        userService = new UserService(userRepository, contactRepository);
    }

    @Test
    void willThrowWhenPeselAlreadyExist() {
        // given
        String PESEL = "999999999999";

        given(userRepository.findByPesel(PESEL)).willReturn(Optional.of(new User(1L, "test1", "test2", "99999999999", Set.of())));

        // when - then
        Assertions.assertThatThrownBy(() -> userService.checkIfUserExist(PESEL))
                .isInstanceOf(UserAlreadyExistsException.class)
                .hasMessageContaining("User with pesel: " + PESEL + " already exists");
    }

    @Test
    void willThrowWhenUserWithThatPeselNotExist() {
        // given
        String PESEL = "999999999999";

        given(userRepository.findByPesel(PESEL)).willReturn(Optional.empty());

        // when - then
        Assertions.assertThatThrownBy(() -> userService.findByPesel(PESEL))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("Could not find user with pesel: " + PESEL);
    }

    @Test
    void canAddUser() {
        // given
        UserDto userDto = createUserDto();

        // when
        userService.save(userDto);

        // then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        Mockito.verify(userRepository).save(userArgumentCaptor.capture());
        User user = userArgumentCaptor.getValue();

        Assertions.assertThat(user.getPesel()).isEqualTo(userDto.getPesel());
    }

    @Test
    void canGetAllUsers() {
        // when
        userService.findAll();
        // then
        Mockito.verify(userRepository).findAll();
    }

    private UserDto createUserDto() {
        UserDto userDto = new UserDto();
        userDto.setFirstname("User1");
        userDto.setLastname("User1");
        userDto.setPesel("999999999999");

        return userDto;
    }
}
