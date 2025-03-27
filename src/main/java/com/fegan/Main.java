package com.fegan;

import com.fegan.participants.Dealer;
import com.fegan.participants.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player sam = new Player("Sam", scanner);
        Deck oneCardDeck = new Deck(2);
        Dealer dealer1 = new Dealer(oneCardDeck);
        Game match1 = new Game(sam, dealer1);
        match1.playGame();
    }
}
