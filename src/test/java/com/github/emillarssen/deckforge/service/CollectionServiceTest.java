package com.github.emillarssen.deckforge.service;

import com.github.emillarssen.deckforge.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Import(TestcontainersConfiguration.class)
class CollectionServiceTest {

    @Autowired
    private CollectionService collectionService;

    //TODO, User must be specified to identify collection to modify
    @Test
    void addedCardShouldAppearInCollection(){
        collectionService.addCardToCollection(1L);
        assertEquals(1, collectionService.getOwnedQuantity(1L));
    }
}
