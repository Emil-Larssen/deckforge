package com.github.emillarssen.deckforge.service;

import com.github.emillarssen.deckforge.TestcontainersConfiguration;
import com.github.emillarssen.deckforge.repository.CollectionRepository;
import com.github.emillarssen.deckforge.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@Import(TestcontainersConfiguration.class)
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CollectionRepository collectionRepository;

    @Test
    void userCanRegister() {
        String username = "testuser";
        String password = "testpassword";

        userService.registerUser(username, password);

        assertTrue(userRepository.existsByUsername(username));
    }

    @Test
    void registeredUserHasACollection() {
        String username = "testuser";

        userService.registerUser(username, "testpassword");

        assertTrue(collectionRepository.existsByOwner_Username(username));
    }
}
