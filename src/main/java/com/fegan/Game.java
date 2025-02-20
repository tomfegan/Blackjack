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
        dealer.dealStartingHands(player);
        player.calculateCurrentHandScoreForPlayer();

        while (player.getHandScore() <= 21 && player.doesPlayerWantNextCard(scanner)) {
            dealer.dealNextCardAndAddToHand(player);
            player.calculateCurrentHandScoreForPlayer();
            System.out.println("Your hand is: " + player.getHand());
            System.out.println(player.getHandScore() <= 21 ? "Your score is currently: " +
                    player.getHandScore() : "You went bust!");
            if (player.getHandScore() > 21) {
                break;
            }
        }
        dealer.calculateCurrentHandScore();

        while (player.getHandScore() <= 21 && dealer.getHandScore() <= player.getHandScore() && dealer.getHandScore() < 21) { // last condition for when player and dealer score 21 - it stops dealer dealing next card and going bust
            dealer.dealNextCardAndAddToHand();
            dealer.calculateCurrentHandScore();
            if (dealer.getHandScore() > 21) {
                break;
            }
        }
        System.out.println("Dealer's hand: " + dealer.getHand());

        winner();
        setUpNewGame();
    }
    private void winner() {
        if (player.getHandScore() == dealer.getHandScore()) {
            System.out.println("Tie!");
            player.setWinRecord(player.getWinRecord().append("D"));
        } else if (player.getHandScore() > 21 && dealer.getHandScore() > 21) {
            System.out.println("Tie! Both bust");
            player.setWinRecord(player.getWinRecord().append("D"));
        } else if (player.getHandScore() > 21 && dealer.getHandScore() <= 21) {
            System.out.println("Player bust, dealer wins!");
            player.setWinRecord(player.getWinRecord().append("L"));
        } else if (player.getHandScore() <= 21 && dealer.getHandScore() > 21) {
            System.out.println("Dealer Bust, player wins!");
            player.setWinRecord(player.getWinRecord().append("W"));
        } else if (player.getHandScore() <= 21 && player.getHandScore() > dealer.getHandScore()) {
            System.out.println("Player wins");
            player.setWinRecord(player.getWinRecord().append("W"));
        } else if (dealer.getHandScore() <= 21 && player.getHandScore() < dealer.getHandScore()) {
            System.out.println("Dealer wins");
            player.setWinRecord(player.getWinRecord().append("L"));
        }
        System.out.println(player.getWinRecord());
        System.out.println("----------------");
    }
    public void setUpNewGame() {
        player.setHand(new ArrayList<>());
        player.setHandScore(0);
        dealer.setHand(new ArrayList<>());
        dealer.setHandScore(0);
        dealer.getCardDeck().setIndex(0);
        dealer.shuffleCardDeck();
        System.out.println("Ready for next game!");
        System.out.println(dealer.getCardDeck());
    }
}


