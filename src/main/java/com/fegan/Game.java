package com.fegan;

import com.fegan.participants.Dealer;
import com.fegan.participants.Player;

import java.util.Scanner;

public class Game {
    private Player player;
    private Dealer dealer;
    private int gameNumber = 1;
    public Game(Player player, Dealer dealer) {
        this.dealer = dealer;
        this.player = player;
    }
    public void playGame(Scanner scanner) {
        gameStartMessage(gameNumber);
        dealer.shuffleCardDeck();

        // Print debugging -> delete after unit testing suite added
        System.out.println("Print debugging: deck after dealer shuffle " + dealer.getCardDeck());

        // temporarily commented out so I don't have to enter a number when running the application while I am manually testing
        dealer.cutDeck(player.chooseWhereToSplitDeck(dealer.getCardDeck()));
        System.out.println("Print debugging: deck after player cut " + dealer.getCardDeck());
        dealer.dealAndRevealStartingHands(player);

        // Calculate and set the player's handScore field to check for natural blackjack
        player.setHandScore(player.calculateScoreForPlayerHand(player.getHand())); // calculateScoreForPlayerHand() method asks user how they wish to treat Aces and returns the score of the hand passed to it as an argument

        // Calculate and set the dealer's handScore field to check for natural blackjack
        dealer.calculateAndSetDealersHandScore();

        // Check dealer and player do not have natural Blackjacks (as they beat any other hand)
        if (!doesPlayerOrDealerHaveNaturalBlackJack()) {
            if (player.canSplitTheirStartingHand() && player.doTheyWantToSplitTheirStartingHand()) {
                player.splitStartingHand(dealer);
            } else {
                System.out.println("(6) " + player.getName() + " starting hand score: " + player.getHandScore());
                // Keep asking player if they want another card
//                player.playerAsksDealerToHitOrStands(scanner, dealer, player.getHand());
//                // Set the player's hand score when they "stand"
////                player.setHandScore(player.calculateScoreForPlayerHand(player.getHand()));
//                player.setHandScore(player.getHandScore());

                player.setHandScore(player.playerAsksDealerToHitOrStands(scanner, dealer, player.getHand()));
            }
            System.out.printf("(7) Dealer reveals their hole (face down) card - it is %s%n", dealer.getHand().getLast());
            dealer.calculateAndSetDealersHandScore();
            dealer.printCardsInCurrentHand(dealer.getHand());
            dealer.executeDealersPredeterminedHitAndStandRules();
        }
        decideWinner();
        playNewGame(scanner);
    }
    private boolean doesPlayerOrDealerHaveNaturalBlackJack() {
        return (player.getHandScore() == 21 && player.getHand().size() == 2 /*&& player.getSplitHands().isEmpty())*/ ||
                (dealer.getHandScore() == 21 && player.getHand().size() == 2));
    }
    private void gameStartMessage(int numOfGames) {
        if (numOfGames == 1) {
            System.out.printf("Welcome %s to Blackjack!%n", player.getName());
        } else {
            System.out.printf("Blackjack - game %d%n", gameNumber);
        }
    }
    private void decideWinner() {
        if ((player.getHand().size() == 2) && (player.getHandScore() == 21) && (dealer.getHand().size() == 2) && (dealer.getHandScore() == 21)) {
            System.out.println("Push! Dealer and " + player.getName() + " got natural blackjacks!");
            player.setWinRecord(new StringBuilder(String.valueOf(GameResult.DRAW)));
        } else if ((player.getHand().size() == 2) && (player.getHandScore() == 21) && (dealer.getHand().size() == 2) && (dealer.getHandScore() != 21)) {
            System.out.println(player.getName() + " wins with natural blackjack as dealer does not have a natural blackjack!");
            player.setWinRecord(new StringBuilder(String.valueOf(GameResult.WIN)));
        } else if ((dealer.getHand().size() == 2) && (dealer.getHandScore() == 21) && (player.getHand().get(0).getCardValue() + player.getHand().get(1).getCardValue() != 21)) {
            System.out.println("1) Dealer wins with natural blackjack as " + player.getName() + " does not have a natural blackjack!");
            player.setWinRecord(new StringBuilder(String.valueOf(GameResult.LOSS)));
        } else if (player.getHandScore() > 21 && dealer.getHandScore() > 21) {
            System.out.println("Both bust but dealer wins as per the rules in the README.md file");
            player.setWinRecord(new StringBuilder(String.valueOf(GameResult.LOSS)));
        } else if (player.getHandScore() > 21 && dealer.getHandScore() <= 21) {
            System.out.println(player.getName() + " bust, dealer wins with " + dealer.getHandScore());
            player.setWinRecord(new StringBuilder(String.valueOf(GameResult.LOSS)));
        } else if (player.getHandScore() <= 21 && dealer.getHandScore() > 21) {
            System.out.println("Dealer Bust, " + player.getName() + " wins with " + player.getHandScore() + "!");
            player.setWinRecord(new StringBuilder(String.valueOf(GameResult.WIN)));
        } else if (player.getHandScore() == dealer.getHandScore()) {
            System.out.printf("Push! Both %s and dealer scored %d%n", player.getName(), player.getHandScore());
            player.setWinRecord(new StringBuilder(String.valueOf(GameResult.DRAW)));
        } else if (player.getHandScore() <= 21 && player.getHandScore() > dealer.getHandScore()) {
            System.out.printf("%s wins %d to %d%n", player.getName(), player.getHandScore(), dealer.getHandScore());
            player.setWinRecord(new StringBuilder(String.valueOf(GameResult.WIN)));
        } else if (dealer.getHandScore() <= 21 && player.getHandScore() < dealer.getHandScore()) {
            System.out.printf("Dealer wins %d to %d%n", dealer.getHandScore(), player.getHandScore());
            player.setWinRecord(new StringBuilder(String.valueOf(GameResult.LOSS)));
        }
        System.out.println(player.getGameResults());
        System.out.println("----------------");
    }
    private void playNewGame(Scanner scanner) {
        System.out.println("Do you want to play another game?");
        String newGame = scanner.next().substring(0, 1).toLowerCase();
        if (newGame.equals("y")) {
            setUpNewGame();
            playGame(scanner);
        }
    }
    private void setUpNewGame() {
        player.getHand().clear();
        player.setHandScore(0);
        dealer.getHand().clear();
        dealer.setHandScore(0);
        dealer.getCardDeck().setIndex(0);
        System.out.println("Ready for next game!");
        gameNumber++;
    }
}


