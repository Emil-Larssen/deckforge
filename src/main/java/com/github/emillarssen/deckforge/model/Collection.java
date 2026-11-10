package com.github.emillarssen.deckforge.model;

import jakarta.persistence.*;

@Entity
@Table (name = "collections")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User owner;

    protected Collection() {}

    public Collection(User user) {
        this.owner = user;
    }
}
