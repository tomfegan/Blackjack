package com.fegan;

public class Card {
    private CardRank rank;
    private CardSuit suit;
    private int cardValue;

    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
        cardValue = switch (rank) {
            case ACE_OF_ -> 1;
            case TWO_OF_ -> 2;
            case THREE_OF_ -> 3;
            case FOUR_OF_ -> 4;
            case FIVE_OF_ -> 5;
            case SIX_OF_ -> 6;
            case SEVEN_OF_ -> 7;
            case EIGHT_OF_ -> 8;
            case NINE_OF_ -> 9;
            default -> 10;
        };;
    }
    public int getCardValue() {
        return cardValue;
    }
    @Override
    public String toString() {
        return String.format("%s%s%n", rank, suit);
    }
    public CardRank getRank() {
        return rank;
    }

}
