package com.fegan.participants;

import com.fegan.Card;
import com.fegan.CardRank;
import com.fegan.Deck;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Player extends GameParticipant {
    final private String name;
    private StringBuilder gameResults;
    private List<List<Card>> splitHands;

    public Player() {
        this("John Doe");
    }

    public Player(String name) {
        super();
        this.name = name;
        gameResults = this.name.endsWith("s") ? new StringBuilder(this.name + "' win record: ") : new StringBuilder(this.name + "'s win record: ");
        splitHands = new ArrayList<>(2);
    }

    public int chooseWhereToSplitDeck(Deck gameDeck) {
        int chosenCutPosition = 0;
        Scanner sc = new Scanner(System.in);
        System.out.printf("Dealer has shuffled the deck. Please enter a number between 1 and %d to cut the deck", 52 * gameDeck.getPacks());
        while (true) {
            try {
                chosenCutPosition = sc.nextInt();
                if (chosenCutPosition < 1 || chosenCutPosition >= 52 * gameDeck.getPacks()) {
                    System.out.printf("There are %d packs in the deck so you can only cut it between 1 and %d%n", gameDeck.getPacks(), 52 * gameDeck.getPacks());
                    continue;
                }
                break;
            } catch (InputMismatchException ime) {
                sc.nextLine();
                System.out.println("Please enter an integer (not letters or special characters)");
            }
        }
        return (52 * gameDeck.getPacks()) - chosenCutPosition;
    }

    public String getName() {
        return name;
    }

    public List<List<Card>> getSplitHands() {
        return splitHands;
    }

    public void splitStartingHand(Dealer dealer) {
        System.out.printf("You want to split the deck - you have 2 hands: 1 is %s and the other is %s%n",
                hand.getFirst(), hand.getLast());
        splitHands.add(new ArrayList<>());
        splitHands.add(new ArrayList<>());
        splitHands.getFirst().add(hand.getFirst());
        splitHands.getLast().add(hand.getLast());
        System.out.println(splitHands.toString());

        int highestSplitHandValue = 0;
        int index = dealer.getCardDeck().getIndex();

        for (List<Card> splitHand : splitHands) {

            if (splitHand.getFirst().getRank().equals(CardRank.ACE_OF_)) {
                /*Aces treated as 11 and only allowed 1 more card -> user shouldn't be asked how to treat aces
                or if they want to hit or stand*/
                // this method needs to assign ACE to 11 as only 1 card can be drawn if splitting aces
                // this must only allow one card to be dealt for each hand and 21 scored this way is not a natural blackjack
                int currentSplitHandValue = 0;
                System.out.printf("%s split their aces%n", name);
                Card card = dealer.getCardDeck().getListOfCardsFieldFromDeckClass().
                        get(index);
                dealer.getCardDeck().setIndex(index++);
                splitHand.add(card);
                currentSplitHandValue = 11 + splitHand.getLast().getCardValue();
                if (currentSplitHandValue > highestSplitHandValue) {
                    highestSplitHandValue = currentSplitHandValue;
                }
                System.out.printf("As %s split aces, they have to hit once - dealer has therefore dealt another card " +
                        "and %s current split hand is %s with a score of %d %n", name, name, splitHand, currentSplitHandValue);
            } else {
                System.out.printf("%s split their non-ace starting hand%n", name);
                Scanner sc = new Scanner(System.in);
                int currentSplitHandScore = splitHand.getFirst().getCardValue();

                while (currentSplitHandScore <= 21 && doesPlayerWantNextCard(sc)) { // bug: this isnt updating the index correctly to deal the next card
                    Card card = dealer.getCardDeck().getListOfCardsFieldFromDeckClass().
                            get(index);
                    dealer.getCardDeck().setIndex(index++);
                    splitHand.add(card);
                    System.out.printf("%s current split hand is %s with a score of %d - %n", name, splitHand, currentSplitHandScore);

                    if (card.getRank().equals(com.fegan.CardRank.ACE_OF_)) {
                        while (true) {
                            try {
                                System.out.printf("Do you want to treat the %s as 1 or 11?", card);
                                int aceValue = sc.nextInt();
                                if (aceValue == 1 || aceValue == 11) {
                                    currentSplitHandScore += aceValue;
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println("Please enter 1 or 11 - do not enter letters");
                                sc.nextLine();
                            }
                        }
                    } else {
                        currentSplitHandScore += splitHand.getLast().getCardValue();
                    }

                    System.out.printf("%s current split hand is %s with a score of %d - %n", name, splitHand, currentSplitHandScore);
                    if (currentSplitHandScore > highestSplitHandValue && currentSplitHandScore <= 21) {
                        highestSplitHandValue = currentSplitHandScore;
                    }
                    if (currentSplitHandScore > 21) {
                        break;
                    }
                }
            }
            handScore = highestSplitHandValue; // setting higher split hand score to the player's hand score attribute - this will then work with winner() method
        }
    }

    public void calculateCurrentHandScoreForPlayer() {
        int tempHandScore = 0;
        Scanner sc = new Scanner(System.in);
        for (Card card : hand) {
            if (card.getRank().equals(com.fegan.CardRank.ACE_OF_)) {
                while (true) {
                    try {
                        System.out.printf("Do you want to treat the %s as 1 or 11?", card);
                        int aceValue = sc.nextInt();
                        if (aceValue == 1 || aceValue == 11) {
                            tempHandScore += aceValue;
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Please enter 1 or 11 - do not enter letters");
                        sc.nextLine();
                    }
                }
            } else {
                tempHandScore += card.getCardValue();
            }
        }
        handScore = tempHandScore;
//        System.out.println(this.name.endsWith("s") ?
//                this.name + "' hand score is currently: " + handScore :
//                this.name + "'s hand score is currently: " + handScore);
    }

    public boolean doesPlayerWantNextCard(Scanner scanner) {
        System.out.println("Enter H if you want dealer to give you another card (hit) or any key to stand (not take another card)");
        if (scanner.next().substring(0, 1).toLowerCase().trim().equals("h")) {
            handScore = 0;
            System.out.printf("%s asked for another card%n", name);
            return true;
        } else {
            System.out.printf("%s did not want another card%n", name);
            return false;
        }
    }

    public StringBuilder getGameResults() {
        return gameResults;
    }

    public void setWinRecord(StringBuilder gameResults) {
        this.gameResults.append("-").append(gameResults);
    }

    @Override
    public void printCardsInCurrentHand() {
        StringBuilder displayHandAsCommaSeparatedList = new StringBuilder("Current hand for " + name + ": face up cards are ");
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

    public void playerHitOrStand(Scanner scanner, Dealer dealer) {
        while (handScore <= 21 && doesPlayerWantNextCard(scanner)) {
            dealer.dealNextCardAndAddToHand(this);
            printCardsInCurrentHand();
            calculateCurrentHandScoreForPlayer();
            System.out.println(handScore <= 21 ? "Current score for " + name + " is " +
                    handScore : name + " went bust!");
            if (handScore > 21) {
                break;
            }
        }
    }
}
