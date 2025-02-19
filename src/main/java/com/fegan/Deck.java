package com.fegan;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> cardDeck = new ArrayList<>();
    private int packs;
    private int index;
    public Deck() {
        this(1);
    }
    public Deck(int packs) {
        if (packs > 4) {
            this.packs = 4;
        } else if (packs < 1) {
            this.packs = 1;
        } else {
            this.packs = packs;
        }
        cardDeck = generateOrderedCardDeck();
        index = 0;
    }
    private List<Card> generateOrderedCardDeck() {
        for (int i = 0; i < packs; i++) {
            for (com.fegan.CardSuit cs : com.fegan.CardSuit.values()) {
                for (com.fegan.CardRank cr : com.fegan.CardRank.values()) {
                    cardDeck.add(new Card(cr, cs));
                }
            }
        }
        return cardDeck;
    }
    @Override
    public String toString() {
        return String.format("%s%n", cardDeck);
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public int getIndex() {
        return index;
    }
    public List<Card> getCardDeck() {
        return cardDeck;
    }
}
