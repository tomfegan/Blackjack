package com.fegan;

import com.fegan.participants.Dealer;
import com.fegan.participants.Player;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Player player; // should I replace Player player with List<Player> players to allow multiple players?
    private Dealer dealer;
    public Game(Player player, Dealer dealer) {
        this.dealer = dealer;
        this.player = player;
    }
    public void playGame(Scanner scanner) {
        dealer.shuffleCardDeck();
        dealer.dealStartingHands(player);
        player.calculateCurrentHandScoreForPlayer();
        dealer.calculateCurrentHandScore();
        if (checkForNaturalBlackJacks()) { // the game ends if the dealer and/or player are dealt natural blackjacks
            winner();
        } else {
            System.out.println(player.getName() + " starting hand score: " + player.getHandScore());
            player.playerHitOrStand(scanner, dealer);
            System.out.printf("Dealer reveals their hole (face down) card - it is %s%n", dealer.getHand().getLast());
            dealer.printCardsInCurrentHand();
            dealer.executeDealersPredeterminedHitAndStandRules();
            winner();
        }
        playNewGame(scanner);
    }
    private boolean checkForNaturalBlackJacks() {
        return player.getHandScore() == 21 || dealer.getHandScore() == 21;
    }
    private void winner() {
        // test case 1: player starting hand is Blackjack and dealer is not Blackjack so player wins
        // test case 2: player and dealer starting hands are Blackjack so tie
        // test case 3: dealer starting hand is Blackjack and player is not Blackjack - even if score is 21 - so dealer wins
        // test case 4: dealer and/or player starting hands have same rank - split
        // test case 5: dealer dealt ace and treats as 11 but then treats as 1 when next card dealt would make hand bust if ace was 11

        if ((player.getHand().size() == 2) && (player.getHandScore() == 21) && (dealer.getHand().size() == 2) && (dealer.getHandScore() == 21)) {
            System.out.println("Push! Dealer and " + player.getName() + " got natural blackjacks!");
            player.setWinRecord(player.getWinRecord().append("D"));
        } else if ((player.getHand().size() == 2) && (player.getHandScore() == 21) && (dealer.getHand().size() == 2) && (dealer.getHandScore() != 21)) {
            System.out.println(player.getName() + " wins with natural blackjack as dealer does not have a natural blackjack !");
            player.setWinRecord(player.getWinRecord().append("W"));
        } else if ( (dealer.getHand().size() == 2) && (dealer.getHandScore() == 21) && (player.getHand().get(0).getCardValue() + player.getHand().get(1).getCardValue() != 21)) {
            System.out.println("Dealer wins with natural blackjack as " + player.getName() + " does not have a natural blackjack!");
            player.setWinRecord(player.getWinRecord().append("L"));
        } else if (player.getHandScore() > 21 && dealer.getHandScore() > 21) {
            System.out.println("Both bust but dealer wins as per the rules in the README.md file");
            player.setWinRecord(player.getWinRecord().append("L"));
        } else if (player.getHandScore() > 21 && dealer.getHandScore() <= 21) {
            System.out.println(player.getName() + " bust, dealer wins!");
            player.setWinRecord(player.getWinRecord().append("L"));
        } else if (player.getHandScore() <= 21 && dealer.getHandScore() > 21) {
            System.out.println("Dealer Bust, " + player.getName() + " wins!");
            player.setWinRecord(player.getWinRecord().append("W"));
        } else if (player.getHandScore() == dealer.getHandScore()) {
            System.out.printf("Push! Both %s and dealer scored %d%n", player.getName(), player.getHandScore());
            player.setWinRecord(player.getWinRecord().append("D"));
        } else if (player.getHandScore() <= 21 && player.getHandScore() > dealer.getHandScore()) {
            System.out.printf("%s wins %d to %d%n",player.getName(), player.getHandScore(), dealer.getHandScore());
            player.setWinRecord(player.getWinRecord().append("W"));
        } else if (dealer.getHandScore() <= 21 && player.getHandScore() < dealer.getHandScore()) {
            System.out.printf("Dealer wins %d to %d%n",dealer.getHandScore(), player.getHandScore());
            player.setWinRecord(player.getWinRecord().append("L"));
        }
        System.out.println(player.getWinRecord());
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
        player.setHand(new ArrayList<>());
        player.setHandScore(0);
        dealer.setHand(new ArrayList<>());
        dealer.setHandScore(0);
        dealer.getCardDeck().setIndex(0);
        dealer.shuffleCardDeck();
        System.out.println("Ready for next game!");
    }
}


