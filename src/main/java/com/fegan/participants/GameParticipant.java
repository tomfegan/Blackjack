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
    public void setHandScore(int handScore) {
        this.handScore = handScore;
    }
    public void setHand(List<Card> hand) {
        this.hand = hand;
    }
    public abstract void printCardsInCurrentHand();

//    @Override
//    public String toString() {
//        String displayHandAsCommaSeparatedList = "Current hand: ";
//        for (Card card : hand) {
//            displayHandAsCommaSeparatedList += card + ", ";
//        }
//        System.out.println(displayHandAsCommaSeparatedList);
//        return displayHandAsCommaSeparatedList;
//    }
}
