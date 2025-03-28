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

    public void playGame() {
        gameStartMessage(gameNumber);
        dealer.shuffleCardDeck();

        // Print debugging -> delete after unit testing suite added
        System.out.println("Print debugging: deck after dealer shuffle " + dealer.getCardDeck());

        dealer.cutDeck(player.chooseWhereToSplitDeck(dealer.getCardDeck()));

        // Print debugging -> delete after unit testing suite added
        System.out.println("Print debugging: deck after player cut " + dealer.getCardDeck());

        dealer.dealAndRevealStartingHands(player);

        // Calculate and set the player's handScore field to check for natural blackjack
        player.setHandScore(player.calculateScoreForPlayerHand(player.getHand())); // calculateScoreForPlayerHand() method asks user how they wish to treat Aces and returns the score of the hand passed to it as an argument

        // Print debugging -> delete after unit testing suite added
        System.out.println("Print debugging: player's starting hand score = " + player.getHandScore());

        // Calculate and set the dealer's handScore field to check for natural blackjack
        dealer.calculateAndSetDealersHandScore();

        // Check dealer and player do not have natural Blackjacks (as they beat any other hand)
        if (!doesPlayerHaveNaturalBlackJack() && !doesDealerHaveNaturalBlackJack()) {
            if (player.canSplitTheirStartingHand() && player.doTheyWantToSplitTheirStartingHand()) {
                player.setHandScore(0); // This resets hand score to zero as once the player splits their starting hand, their handscore is no longer the result of the two starting cards. The hand score will be set by the splitStartingHand() method that is subsequently called

                // Print debugging -> delete after unit testing suite added
                System.out.println("Print debugging: player's starting hand score after deciding to split = " + player.getHandScore());

                player.splitStartingHand();
                player.playSplitHandsAndUpdateHandScoreWithBestHand(dealer);
                // Print debugging -> delete after unit testing suite added
                System.out.println("Print debugging: player's hand score after splitting their starting hand = " + player.getHandScore());

            } else {
                System.out.println(player.getName() + " starting hand score: " + player.getHandScore());
                // Keep asking player if they want another card
                player.setHandScore(player.playerAsksDealerToHitOrStands(dealer, player.getHand()));
            }
            System.out.printf("Dealer reveals their hole (face down) card - it is %s%n", dealer.getHand().getLast());
            dealer.calculateAndSetDealersHandScore();
            dealer.printCardsInCurrentHand(dealer.getHand());
            dealer.executeDealersPredeterminedHitAndStandRules();
        }
        decideResultAndUpdateGameResults();
        playNewGame(new Scanner(System.in));
    }
    private boolean doesPlayerHaveNaturalBlackJack() {
        return player.getHandScore() == 21 && player.getHand().size() == 2 && player.getSplitHands().isEmpty();
    }
    private boolean doesDealerHaveNaturalBlackJack() {
        return dealer.getHandScore() == 21 && dealer.getHand().size() == 2;
    }
    private void gameStartMessage(int numOfGames) {
        if (numOfGames == 1) {
            System.out.printf("Welcome %s to Blackjack!%n", player.getName());
        } else {
            System.out.printf("Blackjack - game %d%n", gameNumber);
        }
    }

    /*tested*/public void decideResultAndUpdateGameResults() {
        String resultMessage = "";
        if (doesPlayerHaveNaturalBlackJack() && doesDealerHaveNaturalBlackJack()) { // test (1)
            resultMessage = "Push! Dealer and " + player.getName() + " got natural blackjacks.";
            player.updateWinRecord(GameResult.DRAW);
        } else if (doesPlayerHaveNaturalBlackJack() && !doesDealerHaveNaturalBlackJack()) { // tests (3), (11)
            resultMessage = player.getName() + " wins with natural blackjack as dealer does not have a natural blackjack.";
            player.updateWinRecord(GameResult.WIN);
        } else if (doesDealerHaveNaturalBlackJack() && !doesPlayerHaveNaturalBlackJack()) { // tests (2), (4)
            resultMessage = "Dealer wins with natural blackjack as " + player.getName() + " does not have a natural blackjack.";
            player.updateWinRecord(GameResult.LOSS);
        } else if (player.getHandScore() > 21 && dealer.getHandScore() > 21) { // test (5)
            resultMessage = "Both bust but dealer wins as per the rules in the README.md file.";
            player.updateWinRecord(GameResult.LOSS);
        } else if (player.getHandScore() > 21 && dealer.getHandScore() <= 21) { // test (6)
            resultMessage = player.getName() + " bust, dealer wins with " + dealer.getHandScore() + ".";
            player.updateWinRecord(GameResult.LOSS);
        } else if (player.getHandScore() <= 21 && dealer.getHandScore() > 21) { // test (7)
            resultMessage = "Dealer Bust, " + player.getName() + " wins with " + player.getHandScore() + ".";
            player.updateWinRecord(GameResult.WIN);
        } else if (player.getHandScore() == dealer.getHandScore()) { // tests (8), (12)
            resultMessage = "Push! Both %s and dealer scored %d.%n".formatted(player.getName(), player.getHandScore());
            player.updateWinRecord(GameResult.DRAW);
        } else if (player.getHandScore() <= 21 && player.getHandScore() > dealer.getHandScore()) { // test (9)
            resultMessage = "%s wins %d to %d.%n".formatted(player.getName(), player.getHandScore(), dealer.getHandScore());
            player.updateWinRecord(GameResult.WIN);
        } else if (dealer.getHandScore() <= 21 && player.getHandScore() < dealer.getHandScore()) { // test (10)
            resultMessage = "Dealer wins %d to %d.%n".formatted(dealer.getHandScore(), player.getHandScore());
            player.updateWinRecord(GameResult.LOSS);
        }
        System.out.println(resultMessage);
        System.out.println(player.getGameResults());
        System.out.println("""
                  *
                  *
                  *
               *  *  *
                * * *
                  *""");

    }
    private void playNewGame(Scanner scanner) {
        System.out.println("Do you want to play another game?");
        String newGame = scanner.next().substring(0, 1).toLowerCase();
        if (newGame.equals("y")) {
            setUpNewGame();
            playGame();
        }
    }
    private void setUpNewGame() {
        player.getHand().clear();
        player.setHandScore(0);
        player.getSplitHands().clear();
        dealer.getHand().clear();
        dealer.setHandScore(0);
        dealer.getCardDeck().setIndex(0);
        System.out.println("Ready for next game!");
        gameNumber++;
    }
}


