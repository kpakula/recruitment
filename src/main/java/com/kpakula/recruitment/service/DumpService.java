package com.kpakula.recruitment.service;

import com.kpakula.recruitment.model.User;
import com.kpakula.recruitment.model.UserDump;
import com.kpakula.recruitment.repository.UserDumpRepository;
import com.kpakula.recruitment.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class DumpService {
    private final UserDumpRepository userDumpRepository;
    private final UserRepository userRepository;

    public DumpService(UserDumpRepository userDumpRepository, UserRepository userRepository) {
        this.userDumpRepository = userDumpRepository;
        this.userRepository = userRepository;
    }

    public UserDump createUserDumpFile() {
        try {
            UserDump userDump = createUserDump();
            saveUsersToFile(userDump.getPath());

            userDumpRepository.save(userDump);
            return userDump;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveUsersToFile(String path) throws IOException {
        FileOutputStream fos  = new FileOutputStream(path);
        OutputStreamWriter osw = new OutputStreamWriter(fos);

        List<User> users = userRepository.findAll();
        for (User user: users) {
            osw.write(user.toString() + "\n");
        }

        osw.close();
    }

    private UserDump createUserDump() {
        UserDump userDump = new UserDump();
        userDump.setUuid(UUID.randomUUID());
        userDump.setCreatedDate(Instant.now());
        userDump.setPath("user-dump-" + userDump.getUuid() +  ".txt");
        return userDump;
    }
}
