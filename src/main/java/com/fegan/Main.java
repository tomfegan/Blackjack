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
        Player james = new Player("James");
        Dealer dealer1 = new Dealer(oneCardDeck);
        Game match1 = new Game(james, dealer1);
        match1.playGame(new Scanner(System.in));
        Game match2 = new Game(james, dealer1);
        match2.playGame(new Scanner(System.in));
//        Game match3 = new Game(james, dealer1);
//        match3.playGame(new Scanner(System.in));

    }
}
