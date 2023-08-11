package com.kpakula.recruitment.controller;

import com.kpakula.recruitment.model.User;
import com.kpakula.recruitment.model.UserDump;
import com.kpakula.recruitment.repository.UserDumpRepository;
import com.kpakula.recruitment.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/dumps")
public class DumpController {
    private final UserDumpRepository userDumpRepository;
    private final UserRepository userRepository;

    public DumpController(UserDumpRepository userDumpRepository, UserRepository userRepository) {
        this.userDumpRepository = userDumpRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    public UserDump createUserDump() {
        try {
            UserDump userDump = new UserDump();
            userDump.setUuid(UUID.randomUUID());
            userDump.setCreatedDate(Instant.now());
            userDump.setPath("user-dump-" + userDump.getUuid() +  ".txt");


            FileOutputStream fos  = new FileOutputStream(userDump.getPath());
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            List<User> users = userRepository.findAll();
            for (User user: users) {
                osw.write(user.toString() + "\n");
            }

            osw.close();
            userDumpRepository.save(userDump);
            return userDump;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
