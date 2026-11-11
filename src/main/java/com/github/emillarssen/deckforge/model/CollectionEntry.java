package com.github.emillarssen.deckforge.model;


import jakarta.persistence.*;

@Entity
@Table(name = "collection_entries")
public class CollectionEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    private int quantity;

    protected CollectionEntry() {}

    public CollectionEntry(Collection collection, Card card, int quantity) {
        this.collection = collection;
        this.card = card;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
