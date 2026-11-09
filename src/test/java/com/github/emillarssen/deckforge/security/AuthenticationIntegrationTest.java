package com.github.emillarssen.deckforge.security;

import com.github.emillarssen.deckforge.TestcontainersConfiguration;
import com.github.emillarssen.deckforge.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;


@SpringBootTest
@Import(TestcontainersConfiguration.class)
@AutoConfigureMockMvc
public class AuthenticationIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    // Verifies both successful authentication and
    // identification of the authenticated user.
    @Test
    void authenticatedSessionIdentifiesRegisteredUser() throws Exception {
        String username = "testuser";
        String password = "testpassword";


        userService.registerUser(username,password);

        mockMvc.perform(formLogin()
                        .user(username)
                        .password(password))
                .andExpect(authenticated().withUsername(username));
    }
}
