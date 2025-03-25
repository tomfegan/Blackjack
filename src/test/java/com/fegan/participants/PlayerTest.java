package com.fegan.participants;

import com.fegan.Card;
import com.fegan.CardRank;
import com.fegan.CardSuit;
import com.fegan.GameResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.when;

class PlayerTest {

    @Test // method being tested = canSplitTheirStartingHand()
    void testWhetherTheCanSplitTheirStartingHandMethodReturnsTrueWhenThePlayerIsDealtCardsOfTheSameRankAndFalseIfNot() {
        // Arrange
        Player testPlayer1 = new Player("Test player 1", new Scanner(System.in));
        testPlayer1.setHand(List.of(
                new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.ACE_OF_, CardSuit.CLUBS)
        ));
        Player testPlayer2 = new Player("Test player 2", new Scanner(System.in));
        testPlayer2.setHand(List.of(
                new Card(CardRank.TWO_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.ACE_OF_, CardSuit.CLUBS)
        ));
        Player testPlayer3 = new Player("Test player 3", new Scanner(System.in));
        testPlayer3.setHand(List.of(
                new Card(CardRank.TEN_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.JACK_OF_, CardSuit.DIAMONDS)
        ));
        // Act and Assert
        Assertions.assertTrue(testPlayer1.canSplitTheirStartingHand());
        Assertions.assertFalse(testPlayer2.canSplitTheirStartingHand());
        Assertions.assertFalse(testPlayer3.canSplitTheirStartingHand());

    }

    @Test // method being tested = doesPlayerWantNextCard()
    void testMethodReturnsTrueWhenPlayerEntersHWhenAskedIfTheyWantNextCard() {
        // Arrange
        Scanner mockScanner = Mockito.mock(Scanner.class);
        Player testPlayer4 = new Player("Test player 4", mockScanner);

        // Act and Assert
        // First mock input and test
        when(mockScanner.next()).thenReturn("HiT");
        Assertions.assertTrue(testPlayer4.doesPlayerWantNextCard(), "Expect true if user enters HiT");

        // Second mock input and test
        when(mockScanner.next()).thenReturn("hI");
        Assertions.assertTrue(testPlayer4.doesPlayerWantNextCard(), "Expect true if user enters hI");

        // Third mock input and test
        when(mockScanner.next()).thenReturn("HIYA");
        Assertions.assertTrue(testPlayer4.doesPlayerWantNextCard(), "Expect true if user enters HIYA");

        // Fourth mock input and test
        when(mockScanner.next()).thenReturn("Highlights");
        Assertions.assertTrue(testPlayer4.doesPlayerWantNextCard(), "Expect true if user enters Highlights");

        // Fifth mock input and test
        when(mockScanner.next()).thenReturn("hhhhhhhhHHHH");
        Assertions.assertTrue(testPlayer4.doesPlayerWantNextCard(), "Expect true if user enters hhhhhhhhHHHH");
    }

    @Test // method being tested = doesPlayerWantNextCard()
    void testThatMethodReturnsFalseWhenPlayerDoesNotEntersHWhenAskedIfTheyWantNextCard() {
        // Arrange
        Scanner mockScanner = Mockito.mock(Scanner.class);
        Player testPlayer5 = new Player("Test player 5", mockScanner);
        // Act and Assert
        // First mock input and test
        when(mockScanner.next()).thenReturn("-1");
        Assertions.assertFalse(testPlayer5.doesPlayerWantNextCard(), "Expect false if user enters -1");

        // Second mock input and test
        when(mockScanner.next()).thenReturn("200");
        Assertions.assertFalse(testPlayer5.doesPlayerWantNextCard(), "Expect false if user enters 200");

        // Third mock input and test
        when(mockScanner.next()).thenReturn("staND");
        Assertions.assertFalse(testPlayer5.doesPlayerWantNextCard(), "Expect false if user enters staND");

        // Fourth mock input and test
        when(mockScanner.next()).thenReturn("@");
        Assertions.assertFalse(testPlayer5.doesPlayerWantNextCard(), "Expect false if user enters @");
    }

    @Test // method under test = DoTheyWantToSplitTheirStartingHand()
    void testThatMethodReturnsTrueWhenPlayerEntersYWhenAskedDoTheyWantToSplitTheirStartingHand() {
        // Arrange
        Scanner mockScanner = Mockito.mock(Scanner.class);
        Player testPlayer6 = new Player("Test player 6", mockScanner);

        // Act and Assert
        // First mock input and test
        when(mockScanner.next()).thenReturn("YES");
        Assertions.assertTrue(testPlayer6.doTheyWantToSplitTheirStartingHand(), "Expect true if user enters YES");

        // Second mock input and test
        when(mockScanner.next()).thenReturn("y");
        Assertions.assertTrue(testPlayer6.doTheyWantToSplitTheirStartingHand(), "Expect true if user enters y");

        // Third mock input and test
        when(mockScanner.next()).thenReturn("YEP");
        Assertions.assertTrue(testPlayer6.doTheyWantToSplitTheirStartingHand(), "Expect true if user enters YEP");

        // Fourth mock input and test
        when(mockScanner.next()).thenReturn("Y");
        Assertions.assertTrue(testPlayer6.doTheyWantToSplitTheirStartingHand(), "Expect true if user enters Y");

        // Fifth mock input and test
        when(mockScanner.next()).thenReturn("yes");
        Assertions.assertTrue(testPlayer6.doTheyWantToSplitTheirStartingHand(), "Expect true if user enters yes");
    }

    @Test // method under test = DoTheyWantToSplitTheirStartingHand()
    void testThatMethodReturnsFalseWhenPlayerDoesNotEnterYWhenAskedDoTheyWantToSplitTheirStartingHand() {
        // Arrange
        Scanner mockScanner = Mockito.mock(Scanner.class);
        Player testPlayer7 = new Player("Test player 7", mockScanner);

        // Act and Assert
        // First mock input and test
        when(mockScanner.next()).thenReturn("ES");
        Assertions.assertFalse(testPlayer7.doTheyWantToSplitTheirStartingHand(), "Expect false if user enters ES");

        // Second mock input and test
        when(mockScanner.next()).thenReturn("-1");
        Assertions.assertFalse(testPlayer7.doTheyWantToSplitTheirStartingHand(), "Expect false if user enters -1");

        // Third mock input and test
        when(mockScanner.next()).thenReturn("@");
        Assertions.assertFalse(testPlayer7.doTheyWantToSplitTheirStartingHand(), "Expect false if user enters @");

        // Fourth mock input and test
        when(mockScanner.next()).thenReturn("null");
        Assertions.assertFalse(testPlayer7.doTheyWantToSplitTheirStartingHand(), "Expect false if user enters null");
    }

    @Test // method being tested = updateWinRecord()
    void testThatTheUpdateWinRecordMethodAppendsThePassedGameResultToThePlayersGameResults() {
        // Arrange
        Player testPlayer8 = new Player("Test player 8");
        testPlayer8.setGameResults(new StringBuilder("Test player 8 results:"));
        String exceptionMessage = "You can only pass a GameResult enum to the updateWinRecord() method";

        // Act and Assert 1
        testPlayer8.updateWinRecord(GameResult.WIN);
        Assertions.assertEquals("Test player 8 results:-Win", testPlayer8.getGameResults().toString());
        // Act and Assert 2
        testPlayer8.updateWinRecord(GameResult.LOSS);
        Assertions.assertEquals("Test player 8 results:-Win-Loss", testPlayer8.getGameResults().toString());
        // Act and Assert 3
        testPlayer8.updateWinRecord(GameResult.DRAW);
        Assertions.assertEquals("Test player 8 results:-Win-Loss-Draw", testPlayer8.getGameResults().toString());
        // Act and Assert 4
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testPlayer8.updateWinRecord(null);
            throw new IllegalArgumentException(exceptionMessage);
        });

    }
}
