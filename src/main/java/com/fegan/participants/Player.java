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
        gameResults = this.name.endsWith("s") ? new StringBuilder(this.name + "' win record:") : new StringBuilder(this.name + "'s win record:");
        splitHands = new ArrayList<>(2);
    }
    public int chooseWhereToSplitDeck(Deck gameDeck) {
        int chosenCutPosition = 0;
        Scanner sc = new Scanner(System.in);
        System.out.printf("Please enter a number between 1 and %d to cut the deck%n", 52 * gameDeck.getPacks());
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
    public boolean canSplitTheirStartingHand() {
        boolean canPlayerSplitHand = hand.getFirst().getRank().equals(hand.getLast().getRank());
        System.out.println(canPlayerSplitHand ? "%s can split starting hand".formatted(name) :
                "%s cannot split starting hand".formatted(name));
        return canPlayerSplitHand;
    }
    public boolean doTheyWantToSplitTheirStartingHand() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to split your hand? Press Y to split your hand or any other key to continue with 1 hand");
        String playerChoice = sc.next().toLowerCase().substring(0, 1);
        if (playerChoice.equals("y")) {
            System.out.printf("%s wants to split starting hand%n", name);
        } else {
            System.out.printf("%s does not want to split hand%n", name);
        }
        return playerChoice.equals("y");
    }
    public void splitStartingHand(Dealer dealer) {
        // create ArrayList of ArrayLists
        splitHands.add(new ArrayList<>());
        splitHands.add(new ArrayList<>());
        // Add the Card at index 0 in hand to ArrayList at index 0
        splitHands.getFirst().add(hand.getFirst());
        // Add the Card at index 1 in hand to ArrayList at index 1
        splitHands.getLast().add(hand.getLast());

        int bestSplitHandValue = 0;

        for (List<Card> splitHand : splitHands) {
            int currentSplitHandValue = 0;

            // Check if the hand being split contains Aces as different rules apply to splitting aces than other cards
            if (splitHand.getFirst().getRank().equals(CardRank.ACE_OF_)) {
                System.out.printf("%s split Aces so the dealer will deal one more card to each hand%n", name);
                Card card = dealer.getNextCardFromDeck(dealer.getCardDeck());
                dealer.addCardToHand(card, splitHand);
                currentSplitHandValue = calculateScoreForPlayerHand(splitHand);
                System.out.printf("(1) Current hand %s scored %d%n", splitHand, currentSplitHandValue);
            } else {
                System.out.printf("%s split non-ace starting hand%n", name);
                currentSplitHandValue = playerAsksDealerToHitOrStands(new Scanner(System.in), dealer, splitHand);
            }
            if (currentSplitHandValue > bestSplitHandValue && currentSplitHandValue <= 21) {
                bestSplitHandValue = currentSplitHandValue;
            }
        }
        System.out.printf("(3) %s split hands = %s%n", name, splitHands);
        // Set best split hand score to the player's hand score attribute - this will then work with decideWinner() method
        handScore = bestSplitHandValue;
    }
    private int assignAceValueToOneOrEleven() {
        int dynamicAceValue = 0;
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Do you want to treat the Ace as 1 or 11?");
                int aceValue = sc.nextInt();
                if (aceValue == 1 || aceValue == 11) {
                    dynamicAceValue += aceValue;
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please enter 1 or 11 - do not enter letters");
                sc.nextLine();
            }
        }
        return dynamicAceValue;
    }
    public int calculateScoreForPlayerHand(List<Card> handToHaveScoreCalculated) {
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
    public boolean doesPlayerWantNextCard(Scanner scanner) {
        System.out.println("Enter H if you want dealer to give you another card (hit) or any key to stand (not take another card)");
        if (scanner.next().substring(0, 1).toLowerCase().trim().equals("h")) {
            System.out.printf("%s asked for another card%n", name);
            return true;
        } else {
            System.out.printf("%s did not want another card%n", name);
            return false;
        }
    }
    @Override
    public void printCardsInCurrentHand(List<Card> currentHand)  {
        StringBuilder displayHandAsCommaSeparatedList = new StringBuilder("(4) Current hand for " + name + ": ");
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
    public int playerAsksDealerToHitOrStands(Scanner scanner, Dealer dealer, List<Card> playerHand) {
        int score = 0;
        while (doesPlayerWantNextCard(scanner)) {
            Card card = dealer.getNextCardFromDeck(dealer.getCardDeck());
            dealer.addCardToHand(card, playerHand);
            System.out.printf("(8) Current hand for %s: %s%n", name, playerHand);
            score = calculateScoreForPlayerHand(playerHand);
            System.out.println(score <= 21 ? "(2) Current score for " + name + " is " +
                    score : "(2) " + name + " went bust with " + score + "!");
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
    public void setWinRecord(StringBuilder gameResults) {
        this.gameResults.append("-").append(gameResults);
    }
}
