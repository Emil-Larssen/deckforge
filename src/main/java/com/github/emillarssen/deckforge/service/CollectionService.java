package com.github.emillarssen.deckforge.service;


import org.springframework.stereotype.Service;

@Service
public class CollectionService {

    void addCardToCollection(Long cardId) {
        //retrieve user collection (helper function probably, will be used again)
        //retrieve card by cardId
        //add reference to card to user collection or increment quantity of card
    }

    long getOwnedQuantity(Long cardId) {
        return 0;
    }
}
