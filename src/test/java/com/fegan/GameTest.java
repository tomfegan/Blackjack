package com.fegan;

import com.fegan.participants.Dealer;
import com.fegan.participants.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

class GameTest {

    List<Card> gameCardList = List.of(
            new Card(CardRank.ACE_OF_, CardSuit.CLUBS),
            new Card(CardRank.TWO_OF_, CardSuit.CLUBS),
            new Card(CardRank.THREE_OF_,CardSuit.CLUBS),
            new Card(CardRank.FOUR_OF_,CardSuit.CLUBS),
            new Card(CardRank.FIVE_OF_,CardSuit.CLUBS),
            new Card(CardRank.SIX_OF_,CardSuit.CLUBS),
            new Card(CardRank.SEVEN_OF_,CardSuit.CLUBS),
            new Card(CardRank.EIGHT_OF_,CardSuit.CLUBS),
            new Card(CardRank.NINE_OF_,CardSuit.CLUBS),
            new Card(CardRank.TEN_OF_,CardSuit.CLUBS),
            new Card(CardRank.JACK_OF_,CardSuit.CLUBS),
            new Card(CardRank.QUEEN_OF_,CardSuit.CLUBS),
            new Card(CardRank.KING_OF_,CardSuit.CLUBS),

            new Card(CardRank.ACE_OF_, CardSuit.SPADES),
            new Card(CardRank.TWO_OF_, CardSuit.SPADES),
            new Card(CardRank.THREE_OF_,CardSuit.SPADES),
            new Card(CardRank.FOUR_OF_,CardSuit.SPADES),
            new Card(CardRank.FIVE_OF_,CardSuit.SPADES),
            new Card(CardRank.SIX_OF_,CardSuit.SPADES),
            new Card(CardRank.SEVEN_OF_,CardSuit.SPADES),
            new Card(CardRank.EIGHT_OF_,CardSuit.SPADES),
            new Card(CardRank.NINE_OF_,CardSuit.SPADES),
            new Card(CardRank.TEN_OF_,CardSuit.SPADES),
            new Card(CardRank.JACK_OF_,CardSuit.SPADES),
            new Card(CardRank.QUEEN_OF_,CardSuit.SPADES),
            new Card(CardRank.KING_OF_,CardSuit.SPADES),

            new Card(CardRank.ACE_OF_, CardSuit.HEARTS),
            new Card(CardRank.TWO_OF_, CardSuit.HEARTS),
            new Card(CardRank.THREE_OF_,CardSuit.HEARTS),
            new Card(CardRank.FOUR_OF_,CardSuit.HEARTS),
            new Card(CardRank.FIVE_OF_,CardSuit.HEARTS),
            new Card(CardRank.SIX_OF_,CardSuit.HEARTS),
            new Card(CardRank.SEVEN_OF_,CardSuit.HEARTS),
            new Card(CardRank.EIGHT_OF_,CardSuit.HEARTS),
            new Card(CardRank.NINE_OF_,CardSuit.HEARTS),
            new Card(CardRank.TEN_OF_,CardSuit.HEARTS),
            new Card(CardRank.JACK_OF_,CardSuit.HEARTS),
            new Card(CardRank.QUEEN_OF_,CardSuit.HEARTS),
            new Card(CardRank.KING_OF_,CardSuit.HEARTS),

            new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
            new Card(CardRank.TWO_OF_, CardSuit.DIAMONDS),
            new Card(CardRank.THREE_OF_,CardSuit.DIAMONDS),
            new Card(CardRank.FOUR_OF_,CardSuit.DIAMONDS),
            new Card(CardRank.FIVE_OF_,CardSuit.DIAMONDS),
            new Card(CardRank.SIX_OF_,CardSuit.DIAMONDS),
            new Card(CardRank.SEVEN_OF_,CardSuit.DIAMONDS),
            new Card(CardRank.EIGHT_OF_,CardSuit.DIAMONDS),
            new Card(CardRank.NINE_OF_,CardSuit.DIAMONDS),
            new Card(CardRank.TEN_OF_,CardSuit.DIAMONDS),
            new Card(CardRank.JACK_OF_,CardSuit.DIAMONDS),
            new Card(CardRank.QUEEN_OF_,CardSuit.DIAMONDS),
            new Card(CardRank.KING_OF_,CardSuit.DIAMONDS)
    );
    Deck testGameDeck = new Deck(gameCardList);
    Dealer testGameDealer = new Dealer(testGameDeck);
    Player testGamePlayer = new Player("Test game player", new Scanner(System.in));





    // test case 3: dealer starting hand is Blackjack and player is not Blackjack - even if score is 21 - so dealer wins
    // test case 4: player starting hand has same rank - split
    // test case 5: dealer dealt ace and treats as 11 but then treats as 1 if next card would make hand bust
    // test case 6: both cards in players starting hand have the same rank (Aces)
    // test case 7: both cards in players starting hand have the same rank (non-Aces)




    @Test // method being tested = decideWinner()
    void checkThatTheDecideWinnerMethodCorrectlyDeterminesDealerAndPlayerDrawWhenTheyBothHaveNaturalBlackjacks() {
        // Arrange - capturing print to console while testing this method
        Player testPlayer1 = new Player("Test player 1", new Scanner(System.in));
        testPlayer1.setHand(List.of(
                new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.TEN_OF_, CardSuit.CLUBS)
        ));
        testPlayer1.setHandScore(21);

        Dealer testDealer1 = new Dealer(new Deck(gameCardList));
        testDealer1.setHand(List.of(
                new Card(CardRank.TEN_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.ACE_OF_, CardSuit.CLUBS)
        ));
        testDealer1.setHandScore(21);

        Game testGame = new Game(testPlayer1, testDealer1);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        // Reassign the "standard" output stream
        System.setOut(printStream);
        PrintStream originalSystemOut = System.out;

        // Act and Assert
        try {
            testGame.decideWinner();
            String capturedOutput = outputStream.toString().trim();
            String[] outputsByLine = capturedOutput.split("\n");
            Assertions.assertEquals("Push! Dealer and Test player 1 got natural blackjacks.", outputsByLine[0]);
        } finally {
            // Reassign the "standard" output stream so printing to console resumes after test completed
            System.setOut(originalSystemOut);
        }
    }

    @Test // method being tested = decideWinner()
    void checkThatTheDecideWinnerMethodCorrectlyDeterminesDealerWinsWhenTheyGetNaturalBlackjackAndPlayerSplitsHandAndScores21WithTwoCards() {
        // Arrange - capturing print to console while testing this method
        Player testPlayer2 = new Player("Test player 2", new Scanner(System.in));
        testPlayer2.setSplitHands(List.of(
                List.of(new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                        new Card(CardRank.TEN_OF_, CardSuit.CLUBS)),
                List.of(new Card(CardRank.EIGHT_OF_, CardSuit.DIAMONDS),
                        new Card(CardRank.ACE_OF_, CardSuit.CLUBS))
        ));
        testPlayer2.setHand(List.of(new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.TEN_OF_, CardSuit.CLUBS)));
        testPlayer2.setHandScore(21);

        Dealer testDealer2 = new Dealer(new Deck(gameCardList));
        testDealer2.setHand(List.of(
                new Card(CardRank.TEN_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.ACE_OF_, CardSuit.SPADES)
        ));
        testDealer2.setHandScore(21);

        Game testGame2 = new Game(testPlayer2, testDealer2);

        ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
        PrintStream printStream2 = new PrintStream(outputStream2);
        // Reassign the "standard" output stream
        System.setOut(printStream2);
        PrintStream originalSystemOut2 = System.out;

        // Act and Assert
        try {
            testGame2.decideWinner();
            String capturedOutput = outputStream2.toString().trim();
            String[] outputsByLine = capturedOutput.split("\n");
            Assertions.assertEquals("Dealer wins with natural blackjack as Test player 2 does not have a natural blackjack.", outputsByLine[0]);
        } finally {
            // Reassign the "standard" output stream so printing to console resumes after test completed
            System.setOut(originalSystemOut2);
        }
    }
    @Test // method being tested = decideWinner()
    void checkThatTheDecideWinnerMethodCorrectlyDeterminesPlayerWinsWhenTheyHaveNaturalBlackjackAndDealerDoesNot() {
        // Arrange - capturing print to console while testing this method
        Player testPlayer3 = new Player("Test player 3", new Scanner(System.in));
        testPlayer3.setHand(List.of(
                new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.TEN_OF_, CardSuit.CLUBS)
        ));
        testPlayer3.setHandScore(21);

        Dealer testDealer3 = new Dealer(new Deck(gameCardList));
        testDealer3.setHand(List.of(
                new Card(CardRank.TEN_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.THREE_OF_, CardSuit.CLUBS)
        ));
        testDealer3.setHandScore(13);

        Game testGame = new Game(testPlayer3, testDealer3);

        ByteArrayOutputStream outputStream3 = new ByteArrayOutputStream();
        PrintStream printStream3 = new PrintStream(outputStream3);
        // Reassign the "standard" output stream
        System.setOut(printStream3);
        PrintStream originalSystemOut3 = System.out;

        // Act and Assert
        try {
            testGame.decideWinner();
            String capturedOutput = outputStream3.toString().trim();
            String[] outputsByLine = capturedOutput.split("\n");
            Assertions.assertEquals("Test player 3 wins with natural blackjack as dealer does not have a natural blackjack.", outputsByLine[0]);
        } finally {
            // Reassign the "standard" output stream so printing to console resumes after test completed
            System.setOut(originalSystemOut3);
        }
    }
    @Test // method being tested = decideWinner()
    void checkThatTheDecideWinnerMethodCorrectlyDeterminesDealerWinsWhenTheyGetNaturalBlackjackAndPlayerScores21WithMoreThanTwoCards() {
        // Arrange - capturing print to console while testing this method
        Player testPlayer4 = new Player("Test player 4", new Scanner(System.in));
        testPlayer4.setHand(List.of(new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.FIVE_OF_, CardSuit.CLUBS),
                new Card(CardRank.FIVE_OF_, CardSuit.HEARTS)));
        testPlayer4.setHandScore(21);

        Dealer testDealer4 = new Dealer(new Deck(gameCardList));
        testDealer4.setHand(List.of(
                new Card(CardRank.TEN_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.ACE_OF_, CardSuit.SPADES)
        ));
        testDealer4.setHandScore(21);

        Game testGame4 = new Game(testPlayer4, testDealer4);

        ByteArrayOutputStream outputStream4 = new ByteArrayOutputStream();
        PrintStream printStream4 = new PrintStream(outputStream4);
        // Reassign the "standard" output stream
        System.setOut(printStream4);
        PrintStream originalSystemOut4 = System.out;

        // Act and Assert
        try {
            testGame4.decideWinner();
            String capturedOutput = outputStream4.toString().trim();
            String[] outputsByLine = capturedOutput.split("\n");
            Assertions.assertEquals("Dealer wins with natural blackjack as Test player 4 does not have a natural blackjack.", outputsByLine[0]);
        } finally {
            // Reassign the "standard" output stream so printing to console resumes after test completed
            System.setOut(originalSystemOut4);
        }
    }
}