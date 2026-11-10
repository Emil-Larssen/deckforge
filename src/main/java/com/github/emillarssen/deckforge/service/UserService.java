package com.github.emillarssen.deckforge.service;

import com.github.emillarssen.deckforge.model.Collection;
import com.github.emillarssen.deckforge.model.User;
import com.github.emillarssen.deckforge.repository.CollectionRepository;
import com.github.emillarssen.deckforge.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CollectionRepository collectionRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, CollectionRepository collectionRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.collectionRepository = collectionRepository;
    }
    @Transactional
    public void registerUser(String username, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(username, hashedPassword);
        userRepository.save(user);

        Collection collection = new Collection(user);
        collectionRepository.save(collection);
    }

}
