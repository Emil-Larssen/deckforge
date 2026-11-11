package com.github.emillarssen.deckforge.repository;


import com.github.emillarssen.deckforge.TestcontainersConfiguration;
import com.github.emillarssen.deckforge.model.Card;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
@Transactional
class CardRepositoryTest {

    @Autowired
    private CardRepository cardRepository;

    @Test
    void createdCardIsPersisted(){
        Card card = new Card("Lightning Bolt");

        Card savedCard = cardRepository.save(card);

        assertTrue(cardRepository.existsById(savedCard.getId()));
    }
}
