package com.github.emillarssen.deckforge.repository;

import com.github.emillarssen.deckforge.model.Card;
import com.github.emillarssen.deckforge.model.Collection;
import com.github.emillarssen.deckforge.model.CollectionEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CollectionEntryRepository extends JpaRepository<CollectionEntry,Long> {

    Optional<CollectionEntry> findByCollectionAndCard(Collection collection, Card card);


}
