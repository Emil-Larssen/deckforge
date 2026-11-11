package com.github.emillarssen.deckforge.service;

import com.github.emillarssen.deckforge.TestcontainersConfiguration;
import com.github.emillarssen.deckforge.model.Card;
import com.github.emillarssen.deckforge.repository.CardRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Import(TestcontainersConfiguration.class)
@Transactional
class CollectionServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;


    @Test
    void addedCardShouldAppearInCollectionOfAuthenticatedUser(){
        String username = "testuser";
        String password = "testpassword";
        //Register User
        userService.registerUser(username, password);

        //Create card in global collection
        Card card = cardRepository.save(new Card("Lightning Bolt"));

        //Authentication
        Authentication authentication = authenticationConfiguration
                .getAuthenticationManager()
                .authenticate(
                        UsernamePasswordAuthenticationToken.unauthenticated(
                                username, password
                        )
                );
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        try {
            //add card from global collection to users collection
            collectionService.addCardToCollection(card.getId());

            //Verify that reference to the card exists in the users collection with a quantity of ONE.
            assertEquals(1, collectionService.getOwnedQuantity(card.getId()));
        } finally {
            SecurityContextHolder.clearContext();
        }
    }
}
