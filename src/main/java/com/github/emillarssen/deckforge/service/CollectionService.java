package com.github.emillarssen.deckforge.service;


import com.github.emillarssen.deckforge.model.Card;
import com.github.emillarssen.deckforge.model.Collection;
import com.github.emillarssen.deckforge.model.CollectionEntry;
import com.github.emillarssen.deckforge.repository.CardRepository;
import com.github.emillarssen.deckforge.repository.CollectionEntryRepository;
import com.github.emillarssen.deckforge.repository.CollectionRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CollectionService {

    private final CollectionRepository collectionRepository;
    private final CardRepository cardRepository;
    private final CollectionEntryRepository collectionEntryRepository;

    public CollectionService(CollectionRepository collectionRepository, CardRepository cardRepository, CollectionEntryRepository collectionEntryRepository) {
        this.collectionRepository = collectionRepository;
        this.cardRepository = cardRepository;
        this.collectionEntryRepository = collectionEntryRepository;
    }

    @Transactional
    void addCardToCollection(Long cardId) {
        //retrieve authenticated users collection
        Collection collection = getAuthenticatedUsersCollection();
        //retrieve card by cardId
        Card card = getCardById(cardId);
        //add card or increment quantity of card in user collection
        Optional<CollectionEntry> existingEntry = collectionEntryRepository.findByCollectionAndCard(collection, card);
        if (existingEntry.isPresent()) {
            CollectionEntry entry = existingEntry.get();
            entry.setQuantity(entry.getQuantity() + 1);
            collectionEntryRepository.save(entry);
        }
        else{
            collectionEntryRepository.save(new CollectionEntry(collection, card,1));
        }
    }

    long getOwnedQuantity(Long cardId) {
        Collection collection = getAuthenticatedUsersCollection();
        Card card = getCardById(cardId);
        Optional<CollectionEntry> existingEntry = collectionEntryRepository.findByCollectionAndCard(collection, card);
        return existingEntry.map(CollectionEntry::getQuantity).orElse(0);
    }


    private Collection getAuthenticatedUsersCollection() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return collectionRepository.findByOwner_Username(username).orElseThrow(()
                -> new IllegalStateException("No collection found for authenticated user: " + username));
    }

    private Card getCardById(Long cardId){
        return cardRepository.findById(cardId).orElseThrow(()
                -> new IllegalArgumentException("Card not found for ID: " + cardId));
    }
}
