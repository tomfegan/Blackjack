package com.fegan.participants;

import com.fegan.Card;
import com.fegan.Deck;

import java.util.Collections;
import java.util.Scanner;

public class Dealer extends GameParticipant {
    private Deck cardDeck;
    public Dealer(Deck orderedCardDeck) {
        super();
        cardDeck = orderedCardDeck;
    }
    public Deck getCardDeck() {
        return cardDeck;
    }
    public void calculateCurrentHandScore() {
        boolean doesHandContainAce = false;
        handScore = 0;
        int aces = 0;
        for (Card card : getHand()) {
            if (card.getRank().equals(com.fegan.CardRank.ACE_OF_)) {
                doesHandContainAce = true;
                handScore += 11;
                aces++;
            } else {
                handScore += card.getCardValue();
            }
        }

        if (handScore > 21 && doesHandContainAce) {
            handScore = handScore - (10 * aces);
            System.out.printf("The dealer has treated the ace in their hand as 1 - dealer's hand is a 'soft %d'%n", handScore);
        } else if (doesHandContainAce) {
            System.out.printf("The dealer has treated the ace in their hand as 11 - dealer's hand is a 'soft %d'%n", handScore);
        }
    }
    public void shuffleCardDeck() {
        Collections.shuffle(cardDeck.getCardDeck());
        System.out.println("Dealer has shuffled the deck");
    }
    public void dealStartingHands(Player player) {
        int numberOfCardsDealtAtStartToHumanAndDealer = 4;
        for (int i = 0; i < numberOfCardsDealtAtStartToHumanAndDealer; i++) {
            if (i % 2 == 0) {
                dealNextCardAndAddToHand(this);
            } else {
                dealNextCardAndAddToHand(player);
            }
        }
        System.out.printf("Dealer's starting hand: face UP card is %s%n", hand.getFirst());
//        System.out.printf("Dealer's starting hand: face DOWN card is %s%n", hand.getLast()); // this should be removed
        player.printCardsInCurrentHand();

        if (player.getHand().getFirst().getRank().equals(player.getHand().getLast().getRank())) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Do you want to split your hand? Press Y to split your hand or any other key to continue with 1 hand");
            String split = sc.next();
            if (split.equals("y")) {
                player.splitStartingHand();
            }
        }
    } // splitHand() method is invoked by this method but has not been implemented
    public void dealNextCardAndAddToHand(GameParticipant participant) {
        Card card = cardDeck.getCardDeck().get(cardDeck.getIndex());
        if (participant instanceof Dealer) {
            hand.add(card);
        } else if (participant instanceof Player) {
            participant.getHand().add(card);
        }
        cardDeck.setIndex(cardDeck.getIndex() + 1);
    }
    @Override
    public void printCardsInCurrentHand() {
        StringBuilder displayHandAsCommaSeparatedList = new StringBuilder("The dealer's current hand is ");
        for (int i = 0; i < hand.size(); i++) {
            if (i < hand.size() - 2) {
                displayHandAsCommaSeparatedList.append(hand.get(i)).append(", ");
            } else if (i < hand.size() - 1) {
                displayHandAsCommaSeparatedList.append(hand.get(i)).append(" and ");
            } else {
                displayHandAsCommaSeparatedList.append(hand.get(i));
            }
        }
        System.out.println(displayHandAsCommaSeparatedList);
    }
}
