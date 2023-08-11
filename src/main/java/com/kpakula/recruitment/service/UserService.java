package com.kpakula.recruitment.service;

import com.kpakula.recruitment.exception.ContactExistsException;
import com.kpakula.recruitment.exception.UserAlreadyExistsException;
import com.kpakula.recruitment.exception.UserNotFoundException;
import com.kpakula.recruitment.model.Contact;
import com.kpakula.recruitment.dto.ContactDto;
import com.kpakula.recruitment.model.User;
import com.kpakula.recruitment.dto.UserDto;
import com.kpakula.recruitment.repository.ContactRepository;
import com.kpakula.recruitment.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;

    public UserService(UserRepository userRepository, ContactRepository contactRepository) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllPageable(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> pagedResult = userRepository.findAll(pageable);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    public User findByPesel(String pesel) {
        return findUserByPesel(pesel).orElseThrow(() -> new UserNotFoundException(pesel));
    }

    public void checkIfUserExist(String pesel) {
        findUserByPesel(pesel).ifPresent((user -> {throw new UserAlreadyExistsException(pesel);}));
    }

    public Optional<User> findUserByPesel(String pesel) {
        return userRepository.findByPesel(pesel);
    }

    public User save(UserDto userDto) {
        return userRepository.save(createUser(userDto));
    }

    public Contact saveContact(Long id, ContactDto contactDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        for (Contact c : user.getContacts()) {
            if (c.getType().equals(contactDto.getType())) {
                throw new ContactExistsException(c.getUserId(), c.getType());
            }
        }

        Contact contact = createContact(id, contactDto);
        return contactRepository.save(contact);
    }

    private Contact createContact(Long userId, ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setUserId(userId);
        contact.setType(contactDto.getType());
        contact.setValue(contactDto.getValue());

        return contact;
    }

    private User createUser(UserDto userDto) {
        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setPesel(userDto.getPesel());

        return user;
    }
}
