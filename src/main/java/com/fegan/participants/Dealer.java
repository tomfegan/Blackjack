package com.fegan.participants;

import com.fegan.Card;
import com.fegan.Deck;

import java.util.Collections;
import java.util.List;

public class Dealer extends GameParticipant {
    private Deck cardDeck;
    public Dealer(Deck orderedCardDeck) {
        super();
        cardDeck = orderedCardDeck;
    }
    public Deck getCardDeck() {
        return cardDeck;
    }
    /*tested*/public void calculateAndSetDealersHandScore() {
        boolean doesHandContainAce = false;
        setHandScore(0);
        int aces = 0;
        for (Card card : getHand()) {
            if (card.getRank().equals(com.fegan.CardRank.ACE_OF_)) {
                doesHandContainAce = true;
                setHandScore(getHandScore() + 11);
                aces++;
            } else {
                setHandScore(getHandScore() + card.getCardValue());
            }
        }

        if (getHandScore() > 21 && doesHandContainAce) {
            setHandScore(getHandScore() - (10 * aces));
            System.out.printf("The dealer has treated the Ace in their hand as 1 - dealer's hand is a 'soft %d'%n",
                    getHandScore());
        } else if (doesHandContainAce) {
            if (getHand().size() > 2) { // this condition stops the ACE being revealed when it is the face down card in the dealer's starting hand
                System.out.printf("The dealer has treated the Ace in their hand as 11 - dealer's hand is a 'soft %d'%n",
                        getHandScore());
            }
        } else {
            if (getHand().size() > 2) {
                System.out.printf("The dealer's current hand score is a 'hard %d'%n", getHandScore());
            }
        }
    }
    public void shuffleCardDeck() {
        Collections.shuffle(cardDeck.getListOfCards());
        System.out.println("Dealer has shuffled the deck");
    }
    public void cutDeck(int index) {
        Collections.rotate(cardDeck.getListOfCards(), index);
    }
    /*tested*/public void dealAndRevealStartingHands(Player player) {
        int numberOfCardsDealtAtStartToHumanAndDealer = 4; // refactor: should not hard code this value
        for (int i = 0; i < numberOfCardsDealtAtStartToHumanAndDealer; i++) {
            if (i % 2 == 0) {
                addCardToHand(getNextCardFromDeck(cardDeck), getHand());
            } else {
                addCardToHand(getNextCardFromDeck(cardDeck), player.getHand());
            }
        }
        System.out.printf("Dealer's starting hand: face UP card is %s%n", getHand().getFirst()); // hole card is second card dealt to dealer
        player.printCardsInCurrentHand(player.getHand());
    }
    /*tested*/public Card getNextCardFromDeck(Deck deck) {
        int index = deck.getIndex();
        Card nextCard = deck.getListOfCards().get(index);
        deck.setIndex(index + 1);
        return nextCard;
    }
    /*tested*/public void addCardToHand(Card card, List<Card> hand) {
        hand.add(card);
    }

    @Override
    public void printCardsInCurrentHand(List<Card> cards) {
        StringBuilder displayHandAsCommaSeparatedList = new StringBuilder("The dealer's current hand is ");
        for (int i = 0; i < getHand().size(); i++) {
            if (i < getHand().size() - 2) {
                displayHandAsCommaSeparatedList.append(getHand().get(i)).append(", ");
            } else if (i < getHand().size() - 1) {
                displayHandAsCommaSeparatedList.append(getHand().get(i)).append(" and ");
            } else {
                displayHandAsCommaSeparatedList.append(getHand().get(i));
            }
        }
        System.out.println(displayHandAsCommaSeparatedList);
    }

    /*tested*/public void executeDealersPredeterminedHitAndStandRules() {
        while (getHandScore() < 17) { // soft 17 rules
            Card card = getNextCardFromDeck(cardDeck);
            addCardToHand(card, getHand());
            System.out.println("Dealer drew another card...");
            printCardsInCurrentHand(getHand());
            calculateAndSetDealersHandScore();
        }
    }

}
