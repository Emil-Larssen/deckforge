package com.github.emillarssen.deckforge.service;

import com.github.emillarssen.deckforge.TestcontainersConfiguration;
import com.github.emillarssen.deckforge.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@Import(TestcontainersConfiguration.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void userCanRegister() {
        String username = "testuser";
        String password = "testpassword";

        userService.registerUser(username, password);

        assertTrue(userRepository.existsByUsername(username));
    }

    @Test
    void registeredUserCanLogin(){

    }
}
