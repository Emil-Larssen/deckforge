package com.github.emillarssen.deckforge.model;


import jakarta.persistence.*;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    protected Card(){
    }

    public Card(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }
}
