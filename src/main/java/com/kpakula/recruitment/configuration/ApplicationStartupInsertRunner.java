package com.kpakula.recruitment.configuration;

import com.kpakula.recruitment.model.Contact;
import com.kpakula.recruitment.model.ContactType;
import com.kpakula.recruitment.model.User;
import com.kpakula.recruitment.repository.ContactRepository;
import com.kpakula.recruitment.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ApplicationStartupInsertRunner implements CommandLineRunner {
    private static final int MIN = 2;
    private static final int MAX = 5;
    private final UserRepository userRepository;

    public ApplicationStartupInsertRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        List<User> users = new ArrayList<>();
//        for (int i = 0; i < 1; i++) {
//            User user = new User();
//            user.setFirstname("Firstname" + (i+1));
//            user.setLastname("Lastname" + (i+1));
//            user.setPesel(Long.toString(getRandomPesel(10000000000L, 99999999999L)));
//            Set<Contact> contacts = new HashSet<>();
//
//            int counter = getNextInt(MIN, MAX);
//            List<ContactType> contactTypes = Arrays.asList(ContactType.values());
//
//            for (int j = 0; j < counter; j++) {
//                ContactType contactType = contactTypes.get(j);
//                Contact contact = new Contact();
//                contact.setType(contactType);
//                contact.setValue(contactType.name() + " value");
//                contacts.add(contact);
//            }
//
//            user.setContacts(contacts);
//            users.add(user);
//        }
//        userRepository.saveAll(users);
    }

    private Long getRandomPesel(Long min, Long max) {
        Random random = new Random();
        return random.nextLong(max - min) + min;
    }

    private int getNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }


}
