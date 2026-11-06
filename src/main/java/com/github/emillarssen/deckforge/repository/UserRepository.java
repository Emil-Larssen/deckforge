package com.github.emillarssen.deckforge.repository;

import com.github.emillarssen.deckforge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByUsername(String username);
}
