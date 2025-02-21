package com.fegan;

import com.fegan.participants.Dealer;
import com.fegan.participants.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player sam = new Player("Sam");
        Deck oneCardDeck = new Deck(1);
        Dealer dealer1 = new Dealer(oneCardDeck);
        Game match1 = new Game(sam, dealer1);
        match1.playGame(new Scanner(System.in));


// manual print testing for dealer and player getting Blackjack in starting hands - should be a tie
//        Player test = new Player("Test player");
//        List<Card> testDeck = new ArrayList<>();
//        testDeck.add(new Card(CardRank.ACE_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.ACE_OF_, CardSuit.HEARTS));
//        testDeck.add(new Card(CardRank.TEN_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.JACK_OF_, CardSuit.CLUBS));
//        Deck bothBlackjackTestDeck = new Deck(testDeck);
//        Dealer dealer2 = new Dealer(bothBlackjackTestDeck);
//        Game match2 = new Game(test, dealer2);
//        match2.playGame(new Scanner(System.in));

// manual print testing for dealer gets Blackjack and player scores 21 with >2 cards - Dealer should win
//        Player test = new Player("Test player");
//        List<Card> testDeck2 = new ArrayList<>();
//        testDeck2.add(new Card(CardRank.ACE_OF_, CardSuit.CLUBS));
//        testDeck2.add(new Card(CardRank.ACE_OF_, CardSuit.HEARTS));
//        testDeck2.add(new Card(CardRank.TEN_OF_, CardSuit.CLUBS));
//        testDeck2.add(new Card(CardRank.EIGHT_OF_, CardSuit.CLUBS));
//        testDeck2.add(new Card(CardRank.TWO_OF_, CardSuit.CLUBS));
//        Deck dealerBlackjackPlayer21TestDeck = new Deck(testDeck2);
//        Dealer dealer3 = new Dealer(dealerBlackjackPlayer21TestDeck);
//        Game match3 = new Game(test, dealer3);
//        match3.playGame(new Scanner(System.in));

        // manual print testing for player gets Blackjack and dealer scores 21 with >2 cards - Player should win
//        Player test = new Player("Test player");
//        List<Card> testDeck3 = new ArrayList<>();
//        testDeck3.add(new Card(CardRank.ACE_OF_, CardSuit.CLUBS));
//        testDeck3.add(new Card(CardRank.ACE_OF_, CardSuit.HEARTS));
//        testDeck3.add(new Card(CardRank.EIGHT_OF_, CardSuit.CLUBS));
//        testDeck3.add(new Card(CardRank.TEN_OF_, CardSuit.CLUBS));
//        testDeck3.add(new Card(CardRank.TWO_OF_, CardSuit.CLUBS));
//        Deck playerBlackjackDealer21TestDeck = new Deck(testDeck3);
//        Dealer dealer4 = new Dealer(playerBlackjackDealer21TestDeck);
//        Game match4 = new Game(test, dealer4);
//        match4.playGame(new Scanner(System.in));
    }
}
