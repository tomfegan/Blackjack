package com.fegan.participants;

import com.fegan.Card;
import com.fegan.Deck;

import java.util.Collections;

public class Dealer extends GameParticipant {
    private Deck cardDeck;
    private boolean containsAce;
    public Dealer(Deck orderedCardDeck) {
        super();
        cardDeck = orderedCardDeck;
    }
    public void calculateCurrentHandScore() {
        int handScoreWhenAceEqualsOne = 0;
        int handScoreWhenAceEqualsEleven = 0;

        for (Card card : getHand()) {
            if (card.getRank().equals(com.fegan.CardRank.ACE_OF_)) {
                containsAce = true;
                if (handScore + 11 > 21) {
                    handScoreWhenAceEqualsOne += 1;
                } else {
                    handScoreWhenAceEqualsEleven += 11;
                }
            } else {
                handScoreWhenAceEqualsOne += card.getCardValue();
                handScoreWhenAceEqualsEleven += card.getCardValue();
            }
        }
        if (handScoreWhenAceEqualsEleven > handScoreWhenAceEqualsOne && handScoreWhenAceEqualsEleven <= 21) {
            handScore = handScoreWhenAceEqualsEleven;
            System.out.println("Dealer's hand score when ACE treated as 11: " + handScore);
        } else {
            handScore = (handScoreWhenAceEqualsOne);
            if (containsAce) {
                System.out.println("Dealer's hand score when ACE treated as 1: " + handScore);
            } else {
                System.out.println("Dealer's hand score (no ACE in hand): " + handScore);
            }
        }
    }
    public void shuffleCardDeck() {
        Collections.shuffle(cardDeck.getCardDeck());
    }
    public void dealStartingHands(Player player) {
        int numberOfCardsDealtAtStartToHumanAndDealer = 4;
        for (int i = 0; i < numberOfCardsDealtAtStartToHumanAndDealer; i++) {
            if (i % 2 == 0) {
                dealNextCardAndAddToHand();
            } else {
                dealNextCardAndAddToHand(player);
            }
        }
        /* if (player.getHand().get(0).equals(player.getHand().get(1))) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Do you want to split your hand? Press Y to split your hand or any other key to continue with 1 hand");
            String split = sc.next();
            if (split.equals("y")) {
                create two hands here, with 1 card each
            }
        }
        */
        System.out.print("One of the dealer's starting hand is: " + hand.getFirst());
        System.out.print("The other (blind) card is: " + hand.getLast());

        System.out.println("Your starting hand is: " + player.getHand());
    } // bug: this needs to cope with splitting the starting hand if both cards are the same rank
    public void dealNextCardAndAddToHand(Player player) {
        Card card = cardDeck.getCardDeck().get(cardDeck.getIndex());
        player.getHand().add(card);
        cardDeck.setIndex(cardDeck.getIndex() + 1);
    }
    public void dealNextCardAndAddToHand() {
        Card card = cardDeck.getCardDeck().get(cardDeck.getIndex());
        hand.add(card);
        cardDeck.setIndex(cardDeck.getIndex() + 1);
    }
}
