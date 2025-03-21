package com.fegan;

public enum CardRank {
    ACE_OF_,
    TWO_OF_,
    THREE_OF_,
    FOUR_OF_,
    FIVE_OF_,
    SIX_OF_,
    SEVEN_OF_,
    EIGHT_OF_,
    NINE_OF_,
    TEN_OF_,
    JACK_OF_,
    QUEEN_OF_,
    KING_OF_;

    @Override
    public String toString() {
        return switch (this) {
            case ACE_OF_ -> "A";
            case TWO_OF_ -> "2";
            case THREE_OF_ -> "3";
            case FOUR_OF_ -> "4";
            case FIVE_OF_ -> "5";
            case SIX_OF_ -> "6";
            case SEVEN_OF_ -> "7";
            case EIGHT_OF_ -> "8";
            case NINE_OF_ -> "9";
            case TEN_OF_ -> "10";
            case JACK_OF_ -> "J";
            case QUEEN_OF_ -> "Q";
            case KING_OF_ -> "K";
        };
    }
}
