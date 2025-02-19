package com.fegan;

import com.fegan.participants.Dealer;
import com.fegan.participants.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Deck oneCardDeck = new Deck(1);
        Dealer dealer = new Dealer(oneCardDeck);
        dealer.shuffleCardDeck();
        System.out.println(oneCardDeck);
        Game match1 = new Game(new Player("James"), new Dealer(oneCardDeck));
        match1.playGame(new Scanner(System.in));
    }
}
