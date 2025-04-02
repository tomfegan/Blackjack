package com.fegan.participants;

import com.fegan.Card;
import com.fegan.CardRank;
import com.fegan.Deck;
import com.fegan.GameResult;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Player extends GameParticipant {
    final private String name;
    private StringBuilder gameResults;
    private List<List<Card>> splitHands;
    private Scanner sc;
    public Player() {
        this("John Doe", new Scanner(System.in));
    }
    public Player(String name) {
        this(name, new Scanner(System.in));
    }
    public Player(String name, Scanner sc) {
        super();
        this.name = name;
        gameResults = this.name.endsWith("s") ? new StringBuilder(this.name + "' win record:")
                                                    : new StringBuilder(this.name + "'s win record:");
        splitHands = new ArrayList<>(2);
        this.sc = sc;
    }
    /*tested*/public int chooseWhereToSplitDeck(Deck gameDeck) {
        int chosenCutPosition = 0;
        System.out.printf("Please enter a number between 1 and %d to cut the deck, or enter 0 if you do not wish to cut the deck%n", gameDeck.getListOfCards().size());
        while (true) {
            try {
                chosenCutPosition = sc.nextInt();
                if (chosenCutPosition < 0 || chosenCutPosition > gameDeck.getListOfCards().size()) {
                    System.out.println("Print debugging: player entered " + chosenCutPosition);
                    System.out.printf("There are %1$d cards in the deck so you can only cut it between 1 and %1$d - " +
                                    "if you do not wish to cut the deck, please enter 0%n", gameDeck.getListOfCards().size());
                    continue;
                }
                System.out.println("Print debugging: player entered " + chosenCutPosition);
                break;
            } catch (InputMismatchException ime) {
                System.out.println("Print debugging: player entered " + chosenCutPosition);
                sc.nextLine();
                System.out.println("Please enter an integer (not letters or special characters)");
            }
        }
        return gameDeck.getListOfCards().size() - chosenCutPosition;
    }
    /*tested*/public boolean canSplitTheirStartingHand() {
        if (getHand().size() == 2) {
            boolean canPlayerSplitHand = getHand().getFirst().getRank().equals(getHand().getLast().getRank());
            System.out.println(canPlayerSplitHand ? "%s can split starting hand".formatted(name) :
                    "%s cannot split starting hand".formatted(name));
            return canPlayerSplitHand;
        } else {
            return false;
        }
    }
    /*tested*/public boolean doTheyWantToSplitTheirStartingHand() {
        System.out.println("Do you want to split your hand? Press Y to split your hand or any other key to continue with 1 hand");
        String playerChoice = sc.next().toLowerCase().substring(0, 1);
        if (playerChoice.equals("y")) {
            System.out.printf("%s wants to split starting hand%n", name);
        } else {
            System.out.printf("%s does not want to split hand%n", name);
        }
        return playerChoice.equals("y");
    }
    /*tested*/public void splitStartingHand() {
        if (canSplitTheirStartingHand()) {
            // create ArrayList of ArrayLists
            splitHands.add(new ArrayList<>());
            splitHands.add(new ArrayList<>());
            // Add the Card at index 0 in hand to ArrayList at index 0
            splitHands.getFirst().add(getHand().getFirst());
            // Add the Card at index 1 in hand to ArrayList at index 1
            splitHands.getLast().add(getHand().getLast());
        } else {
            System.out.println("Did not split hand as both cards were not the same rank, or there were more or less than 2 cards in the hand");
        }
    }
    /*tested*/public void playSplitHandsAndUpdateHandScoreWithBestHand(Dealer dealer) {
        int bestSplitHandValue = 0;

        for (List<Card> splitHand : splitHands) {
            int currentSplitHandValue = 0;

            // Check if the hand being split contains Aces as different rules apply to splitting Aces than other cards
            if (splitHand.getFirst().getRank().equals(CardRank.ACE_OF_)) {
                System.out.printf("%s split Aces so the dealer will deal one more card to each hand%n", name);
                Card card = dealer.getNextCardFromDeck(dealer.getCardDeck());
                dealer.addCardToHand(card, splitHand);
                currentSplitHandValue = calculateScoreForPlayerHand(splitHand);
                System.out.printf("Current hand %s scored %d%n", splitHand, currentSplitHandValue);
            } else {
                System.out.printf("%s split non-ace starting hand%n", name);
                currentSplitHandValue = playerAsksDealerToHitOrStands(dealer, splitHand);
            }

            if (bestSplitHandValue == 0) {
                bestSplitHandValue = currentSplitHandValue;
            } else if (bestSplitHandValue > 21 && currentSplitHandValue <= 21) {
                bestSplitHandValue = currentSplitHandValue;
            } else if (currentSplitHandValue > 21 && bestSplitHandValue > 21) {
                if (currentSplitHandValue < bestSplitHandValue) {
                    bestSplitHandValue = currentSplitHandValue;
                }
            } else if (currentSplitHandValue <= 21) {
                if (currentSplitHandValue > bestSplitHandValue) {
                    bestSplitHandValue = currentSplitHandValue;
                }
            }
        }
        System.out.printf("%s split hands = %s%n", name, splitHands);
        // Set best split hand score to the player's hand score attribute - this will then work with decideResultAndUpdateGameResult() method
        setHandScore(bestSplitHandValue);
    }
    /*tested*/public int assignAceValueToOneOrEleven() {
        int dynamicAceValue = 0;
        while (true) {
            try {
                System.out.println("Do you want to treat the Ace as 1 or 11?");
                int aceValue = sc.nextInt();
                if (aceValue == 1 || aceValue == 11) {
                    dynamicAceValue += aceValue;
                    break;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Please enter 1 or 11 - do not enter letters");
                sc.nextLine();
            }
        }
        return dynamicAceValue;
    }
    /*tested*/public int calculateScoreForPlayerHand(List<Card> handToHaveScoreCalculated) {
        int tempHandScore = 0;

        for (Card card : handToHaveScoreCalculated) {
            if (card.getRank().equals(com.fegan.CardRank.ACE_OF_)) {
                tempHandScore += this.assignAceValueToOneOrEleven();
            } else {
                tempHandScore += card.getCardValue();
            }
        }
        return tempHandScore;
    }
    /*tested*/public boolean doesPlayerWantNextCard() {
        System.out.println("Enter H if you want dealer to give you another card (hit) or any key to stand (not take another card)");
        if (sc.next().substring(0, 1).toLowerCase().trim().equals("h")) {
            System.out.printf("%s asked for another card%n", name);
            return true;
        } else {
            System.out.printf("%s did not want another card%n", name);
            return false;
        }
    }

    @Override
    public void printCardsInCurrentHand(List<Card> currentHand) {
        StringBuilder displayHandAsCommaSeparatedList = new StringBuilder("Current hand for " + name + ": ");
        for (int i = 0; i < currentHand.size(); i++) {
            if (i < currentHand.size() - 2) {
                displayHandAsCommaSeparatedList.append(currentHand.get(i)).append(", ");
            } else if (i < currentHand.size() - 1) {
                displayHandAsCommaSeparatedList.append(currentHand.get(i)).append(" and ");
            } else {
                displayHandAsCommaSeparatedList.append(currentHand.get(i));
            }
        }
        System.out.println(displayHandAsCommaSeparatedList);
    }

    /*tested*/public int playerAsksDealerToHitOrStands(Dealer dealer, List<Card> playerHand) {
        int score = getHandScore();
        while (doesPlayerWantNextCard()) {
            Card card = dealer.getNextCardFromDeck(dealer.getCardDeck());
            dealer.addCardToHand(card, playerHand);
            System.out.printf("Current hand for %s: %s%n", name, playerHand);
            score = calculateScoreForPlayerHand(playerHand);
            System.out.println(score <= 21 ? "Current score for " + name + " is " +
                    score : name + " went bust with " + score + "!");
            if (score > 21) {
                break;
            }
        }
        return score;
    }
    // getters
    public String getName() {
        return name;
    }
    public StringBuilder getGameResults() {
        return gameResults;
    }
    public List<List<Card>> getSplitHands() {
        return splitHands;
    }
    // setters
    /*tested*/public void updateWinRecord(GameResult gameResult) {
        if (gameResult == null) {
            throw new IllegalArgumentException("You can only pass a GameResult enum to the updateWinRecord() method");
        } else {
            this.gameResults.append("-").append(gameResult);
        }
    }
    public void setGameResults(StringBuilder gameResults) {
        this.gameResults = gameResults;
    }
    public void setSplitHands(List<List<Card>> splitHands) {
        this.splitHands = splitHands;
    }
}
