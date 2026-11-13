package com.github.emillarssen.deckforge.controller;


import com.github.emillarssen.deckforge.TestcontainersConfiguration;
import com.github.emillarssen.deckforge.model.Card;
import com.github.emillarssen.deckforge.repository.CardRepository;
import com.github.emillarssen.deckforge.repository.UserRepository;
import com.github.emillarssen.deckforge.service.CollectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
public class CollectionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserRepository userRepository;


    @Test
    void AuthenticatedUserCanAddCardThroughHttp() throws Exception {
        //Create card in global collection
        Card card = cardRepository.save(new Card("Lightning Bolt"));

        //Register User
        String username = "testusername";
        String password = "testpassword";
        mockMvc.perform(post("/register")
                .param("username", username)
                .param("password", password)
                .with(csrf()))
                        .andExpect(status().is3xxRedirection());
        assertTrue(userRepository.existsByUsername(username));

        //Authenticate User

        //send post request

        //verify successfull completion of the request
    }
}
