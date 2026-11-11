package com.github.emillarssen.deckforge.repository;

import com.github.emillarssen.deckforge.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
