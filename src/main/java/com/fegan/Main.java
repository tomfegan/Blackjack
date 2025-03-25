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

        // for the following manual tests to work, comment out dealer.shuffleCardDeck(); in playGame(method)



//        manual testing: player splits hand: first hand scores 17 and second hand scores 21 with 3 cards -> should tie with dealer who gets 21 with 3 or more cards
//        Player test = new Player("Test player");
//        List<Card> testDeck = new ArrayList<>();
//        testDeck.add(new Card(CardRank.TEN_OF_, CardSuit.CLUBS)); // dealer card 1
//        testDeck.add(new Card(CardRank.FIVE_OF_, CardSuit.SPADES)); // player card 1
//        testDeck.add(new Card(CardRank.THREE_OF_, CardSuit.CLUBS)); // dealer card 2
//        testDeck.add(new Card(CardRank.FIVE_OF_, CardSuit.CLUBS)); // player card 2
//        testDeck.add(new Card(CardRank.TWO_OF_, CardSuit.HEARTS)); // player split hand 1
//        testDeck.add(new Card(CardRank.QUEEN_OF_, CardSuit.HEARTS)); // player split hand 1
//        testDeck.add(new Card(CardRank.SIX_OF_, CardSuit.DIAMONDS)); // player split hand 2
//        testDeck.add(new Card(CardRank.QUEEN_OF_, CardSuit.HEARTS)); // player split hand 2
//        testDeck.add(new Card(CardRank.EIGHT_OF_, CardSuit.DIAMONDS));
//        Deck splittingAcesTest = new Deck(testDeck);
//        Dealer dealer2 = new Dealer(splittingAcesTest);
//        Game match2 = new Game(test, dealer2);
//        match2.playGame(new Scanner(System.in));

//        manual testing: player splits hand: first hand scores 21 with 2 cards -> should tie with dealer who gets 21 with more than 2 cards
//        Player test = new Player("Test player");
//        List<Card> testDeck = new ArrayList<>();
//        testDeck.add(new Card(CardRank.JACK_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.TEN_OF_, CardSuit.SPADES));
//        testDeck.add(new Card(CardRank.THREE_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.TEN_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.ACE_OF_, CardSuit.HEARTS));
//        testDeck.add(new Card(CardRank.QUEEN_OF_, CardSuit.HEARTS));
//        testDeck.add(new Card(CardRank.EIGHT_OF_, CardSuit.DIAMONDS));
//        Deck splittingAcesTest = new Deck(testDeck);
//        Dealer dealer2 = new Dealer(splittingAcesTest);
//        Game match2 = new Game(test, dealer2);
//        match2.playGame(new Scanner(System.in));

//        manual testing: player splits hand and scores 21 with 2 cards should lose to dealer who gets natural blackjack
//        Player test = new Player("Test player");
//        List<Card> testDeck = new ArrayList<>();
//        testDeck.add(new Card(CardRank.JACK_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.TEN_OF_, CardSuit.SPADES));
//        testDeck.add(new Card(CardRank.ACE_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.TEN_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.ACE_OF_, CardSuit.HEARTS));
//        testDeck.add(new Card(CardRank.QUEEN_OF_, CardSuit.HEARTS));
//        testDeck.add(new Card(CardRank.EIGHT_OF_, CardSuit.DIAMONDS));
//        Deck splittingAcesTest = new Deck(testDeck);
//        Dealer dealer2 = new Dealer(splittingAcesTest);
//        Game match2 = new Game(test, dealer2);
//        match2.playGame(new Scanner(System.in));

//        manual print testing for player getting two non-aces in starting hand and splitting
//        Player test = new Player("Test player");
//        List<Card> testDeck = new ArrayList<>();
//        testDeck.add(new Card(CardRank.TEN_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.SIX_OF_, CardSuit.HEARTS));
//        testDeck.add(new Card(CardRank.THREE_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.SIX_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.NINE_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.FIVE_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.SEVEN_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS));
//        testDeck.add(new Card(CardRank.TWO_OF_, CardSuit.SPADES));
//        testDeck.add(new Card(CardRank.SIX_OF_, CardSuit.DIAMONDS));
//        testDeck.add(new Card(CardRank.EIGHT_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.THREE_OF_, CardSuit.SPADES));
//        testDeck.add(new Card(CardRank.FOUR_OF_, CardSuit.DIAMONDS));
//        testDeck.add(new Card(CardRank.NINE_OF_, CardSuit.DIAMONDS));
//        Deck splittingAcesTest = new Deck(testDeck);
//        Dealer dealer2 = new Dealer(splittingAcesTest);
//        Game match2 = new Game(test, dealer2);
//        match2.playGame(new Scanner(System.in));

//        manual print testing for player getting two Aces in starting hand and splitting and then scoring 21 but losing as dealer has natural blackjack
//        Player test = new Player("Test player");
//        List<Card> testDeck = new ArrayList<>();
//        testDeck.add(new Card(CardRank.TEN_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.ACE_OF_, CardSuit.HEARTS));
//        testDeck.add(new Card(CardRank.ACE_OF_, CardSuit.SPADES));
//        testDeck.add(new Card(CardRank.ACE_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.JACK_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.SEVEN_OF_, CardSuit.CLUBS));
//        Deck splittingAcesTest = new Deck(testDeck);
//        Dealer dealer2 = new Dealer(splittingAcesTest);
//        Game match2 = new Game(test, dealer2);
//        match2.playGame(new Scanner(System.in));

//        manual print testing for player getting two Aces in starting hand and splitting
//        Player test = new Player("Test player");
//        List<Card> testDeck = new ArrayList<>();
//        testDeck.add(new Card(CardRank.NINE_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.ACE_OF_, CardSuit.HEARTS));
//        testDeck.add(new Card(CardRank.ACE_OF_, CardSuit.SPADES));
//        testDeck.add(new Card(CardRank.ACE_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.JACK_OF_, CardSuit.CLUBS));
//        testDeck.add(new Card(CardRank.SEVEN_OF_, CardSuit.CLUBS));
//        Deck splittingAcesTest = new Deck(testDeck);
//        Dealer dealer2 = new Dealer(splittingAcesTest);
//        Game match2 = new Game(test, dealer2);
//        match2.playGame(new Scanner(System.in));

//        manual print testing for dealer and player getting natural Blackjack in starting hands - should be a tie
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

//        manual print testing for dealer gets Blackjack and player scores 21 with >2 cards - Dealer should win
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

//        manual print testing for player gets Blackjack and dealer scores 21 with >2 cards - Player should win
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
