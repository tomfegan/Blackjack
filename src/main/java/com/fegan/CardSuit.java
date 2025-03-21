package com.fegan;

public enum CardSuit {
    SPADES,
    HEARTS,
    DIAMONDS,
    CLUBS;

    @Override
    public String toString() {
        return switch (this) {
            case SPADES -> "\u2660";
            case HEARTS -> "\u2665";
            case DIAMONDS -> "\u2666";
            case CLUBS -> "\u2663";
        };

    }
}
