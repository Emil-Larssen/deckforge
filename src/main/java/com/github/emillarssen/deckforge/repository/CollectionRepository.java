package com.github.emillarssen.deckforge.repository;

import com.github.emillarssen.deckforge.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
    boolean existsByOwner_Username(String username);

    Optional<Collection> findByOwner_Username(String username);
}
