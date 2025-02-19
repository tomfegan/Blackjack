package com.fegan;

import com.fegan.participants.Dealer;
import com.fegan.participants.Player;
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
    }
    private void winner() {
        if (player.getHandScore() == dealer.getHandScore()) {
            System.out.println("Tie!");
        } else if (player.getHandScore() > 21 && dealer.getHandScore() > 21) {
            System.out.println("Tie! Both bust");
        } else if (player.getHandScore() > 21 && dealer.getHandScore() <= 21) {
            System.out.println("Player bust, dealer wins!");
        } else if (player.getHandScore() <= 21 && dealer.getHandScore() > 21) {
            System.out.println("Dealer Bust, player wins!");
        } else if (player.getHandScore() <= 21 && player.getHandScore() > dealer.getHandScore()) {
            System.out.println("Player wins");
        } else if (dealer.getHandScore() <= 21 && player.getHandScore() < dealer.getHandScore()) {
            System.out.println("Dealer wins");
        }
    }
}


