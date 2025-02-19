package com.fegan.participants;

import com.fegan.Card;
import java.util.ArrayList;
import java.util.List;

public abstract class GameParticipant {
    protected int handScore;
    protected List<Card> hand;

    public GameParticipant() {
        handScore = 0;
        hand = new ArrayList<>();
    }
    public List<Card> getHand() {
        return hand;
    }
    public int getHandScore() {
        return handScore;
    }
}
