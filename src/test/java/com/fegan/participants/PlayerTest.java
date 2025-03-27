package com.fegan.participants;

import com.fegan.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.when;

class PlayerTest {

    @Test
    @DisplayName("method being tested = canSplitTheirStartingHand()")
    void testWhetherTheCanSplitTheirStartingHandMethodReturnsTrueWhenThePlayerIsDealtCardsOfTheSameRankAndFalseIfNot() {
        // Arrange
        Player testPlayer1 = new Player("Test player 1");
        testPlayer1.setHand(List.of(
                new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.ACE_OF_, CardSuit.CLUBS)
        ));
        Player testPlayer2 = new Player("Test player 2");
        testPlayer2.setHand(List.of(
                new Card(CardRank.TWO_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.ACE_OF_, CardSuit.CLUBS)
        ));
        Player testPlayer3 = new Player("Test player 3");
        testPlayer3.setHand(List.of(
                new Card(CardRank.TEN_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.JACK_OF_, CardSuit.DIAMONDS)
        ));
        Player testPlayer4 = new Player("Test player 4");
        testPlayer4.setHand(List.of(
                new Card(CardRank.TEN_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.TEN_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.TEN_OF_, CardSuit.DIAMONDS)
        ));
        Player testPlayer5 = new Player("Test player 5");
        testPlayer5.setHand(List.of(
                new Card(CardRank.JACK_OF_, CardSuit.DIAMONDS)
        ));
        // Act and Assert
        Assertions.assertTrue(testPlayer1.canSplitTheirStartingHand());
        Assertions.assertFalse(testPlayer2.canSplitTheirStartingHand());
        Assertions.assertFalse(testPlayer3.canSplitTheirStartingHand());
        Assertions.assertFalse(testPlayer4.canSplitTheirStartingHand());
        Assertions.assertFalse(testPlayer5.canSplitTheirStartingHand());
    }



    @Test
    @DisplayName("method being tested = doesPlayerWantNextCard()")
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
    @Test
    @DisplayName("method being tested = doesPlayerWantNextCard()")
    void testThatMethodReturnsFalseWhenPlayerDoesNotEnterHWhenAskedIfTheyWantNextCard() {
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
    @Test
    @DisplayName("method under test = DoTheyWantToSplitTheirStartingHand()")
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
    @Test
    @DisplayName("method under test = DoTheyWantToSplitTheirStartingHand()")
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
    @Test
    @DisplayName("method being tested = updateWinRecord()")
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
    @Test
    @DisplayName("method being tested = chooseWhereToSplitDeck()")
    void testThatTheChooseWhereToSplitDeckMethodReturnsAPositiveIntegerBetweenZeroAndTheNumberOfCardsInTheDeck() {
        // Arrange
        Scanner mockScanner9 = Mockito.mock(Scanner.class);
        Player testPlayer9 = new Player("Test player 9", mockScanner9);

        List<Card> gameCardList9 = List.of(
                new Card(CardRank.ACE_OF_, CardSuit.CLUBS),
                new Card(CardRank.TWO_OF_, CardSuit.CLUBS),
                new Card(CardRank.THREE_OF_, CardSuit.CLUBS),
                new Card(CardRank.FOUR_OF_, CardSuit.CLUBS),
                new Card(CardRank.FIVE_OF_, CardSuit.CLUBS),
                new Card(CardRank.SEVEN_OF_, CardSuit.CLUBS),
                new Card(CardRank.EIGHT_OF_, CardSuit.CLUBS),
                new Card(CardRank.SIX_OF_, CardSuit.SPADES),
                new Card(CardRank.SEVEN_OF_, CardSuit.SPADES),
                new Card(CardRank.EIGHT_OF_, CardSuit.SPADES),
                new Card(CardRank.NINE_OF_, CardSuit.SPADES),
                new Card(CardRank.TEN_OF_, CardSuit.SPADES),
                new Card(CardRank.JACK_OF_, CardSuit.SPADES),
                new Card(CardRank.QUEEN_OF_, CardSuit.SPADES),
                new Card(CardRank.KING_OF_, CardSuit.SPADES),
                new Card(CardRank.TEN_OF_, CardSuit.HEARTS),
                new Card(CardRank.JACK_OF_, CardSuit.HEARTS),
                new Card(CardRank.QUEEN_OF_, CardSuit.HEARTS),
                new Card(CardRank.QUEEN_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.KING_OF_, CardSuit.DIAMONDS)
        );
        Deck testGameDeck9 = new Deck(gameCardList9);

        // Act and Assert

        // First mock input and test
        when(mockScanner9.nextInt()).thenReturn(2);
        Assertions.assertEquals(18, testPlayer9.chooseWhereToSplitDeck(testGameDeck9));
        // Second mock input and test
        when(mockScanner9.nextInt()).thenReturn(0);
        Assertions.assertEquals(20, testPlayer9.chooseWhereToSplitDeck(testGameDeck9));
        // Third mock input and test
        when(mockScanner9.nextInt()).thenReturn(-1).thenReturn(1);
        Assertions.assertEquals(19, testPlayer9.chooseWhereToSplitDeck(testGameDeck9));
        // Fourth mock input and test
        when(mockScanner9.nextInt()).thenReturn(21).thenReturn(1);
        Assertions.assertEquals(19, testPlayer9.chooseWhereToSplitDeck(testGameDeck9));
        // Fifth mock input and test
        when(mockScanner9.nextInt())
                .thenThrow(new InputMismatchException()) // Simulates player entering a string to nextInt()
                .thenReturn(-1)
                .thenReturn(21)
                .thenReturn(5);
        Assertions.assertEquals(15, testPlayer9.chooseWhereToSplitDeck(testGameDeck9));
    }
    @Test
    @DisplayName("method being tested = assignAceValueToOneOrEleven()")
    void testThatTheAssignAceValueToOneOrElevenReturnsAnIntValueOfOneOrEleven() {
        // Arrange
        Scanner mockScanner10 = Mockito.mock(Scanner.class);
        Player testPlayer10 = new Player("Test player 10", mockScanner10);

        // Act and Assert 1
        when(mockScanner10.nextInt()).thenReturn(11);
        Assertions.assertEquals(11, testPlayer10.assignAceValueToOneOrEleven());
        // Act and Assert 2
        when(mockScanner10.nextInt()).thenReturn(1);
        Assertions.assertEquals(1, testPlayer10.assignAceValueToOneOrEleven());
        // Act and Assert 3
        when(mockScanner10.nextInt()).thenReturn(2).thenReturn(11);
        Assertions.assertEquals(11, testPlayer10.assignAceValueToOneOrEleven());
        // Act and Assert 4
        when(mockScanner10.nextInt()).thenReturn(-1).thenReturn(1);
        Assertions.assertEquals(1, testPlayer10.assignAceValueToOneOrEleven());
        // Act and Assert 5
        when(mockScanner10.nextInt()).thenReturn(111).thenReturn(11);
        Assertions.assertEquals(11, testPlayer10.assignAceValueToOneOrEleven());
        // Act and Assert 6
        when(mockScanner10.nextInt())
                .thenThrow(new InputMismatchException("This simulates test player 10 entering a non-integer value"))
                .thenReturn(11);
        Assertions.assertEquals(11, testPlayer10.assignAceValueToOneOrEleven());
    }
    @Test
    @DisplayName("method being tested = calculateScoreForPlayerHand()")
    void testThatTheCalculateScoreForPlayerHandMethodReturnsTheCorrectHandScore() {
        // Arrange
        Scanner mockScanner11To13 = Mockito.mock(Scanner.class);

        Player testPlayer11 = new Player("Test player 11", mockScanner11To13);
        testPlayer11.setHand(List.of(
                new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.EIGHT_OF_, CardSuit.CLUBS)
        ));
        Player testPlayer12 = new Player("Test player 12", mockScanner11To13);
        testPlayer12.setHand(List.of(
                new Card(CardRank.TWO_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.TEN_OF_, CardSuit.CLUBS),
                new Card(CardRank.FIVE_OF_, CardSuit.HEARTS)
        ));
        Player testPlayer13 = new Player("Test player 13", mockScanner11To13);
        testPlayer13.setHand(List.of(
                new Card(CardRank.TEN_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.JACK_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.TWO_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.ACE_OF_, CardSuit.CLUBS),
                new Card(CardRank.FIVE_OF_, CardSuit.HEARTS)
        ));
        // Act and Assert 1 - hand contains Ace
        when(mockScanner11To13.nextInt()).thenReturn(11);
        Assertions.assertEquals(19,
                testPlayer11.calculateScoreForPlayerHand(testPlayer11.getHand()));

        // Act and Assert 2 - hand does not contain Aces
        Assertions.assertEquals(17,
                testPlayer12.calculateScoreForPlayerHand(testPlayer12.getHand()));

        // Act and Assert 3 - hand contains 1 Ace (treated as 1) and hand score exceeds 21
        when(mockScanner11To13.nextInt()).thenReturn(1);
        Assertions.assertEquals(28,
                testPlayer13.calculateScoreForPlayerHand(testPlayer13.getHand()));
    }

    @Test
    @DisplayName("(1) method being tested = splitStartingHand()")
    void testThatTheSplitStartingHandMethodSplitsAPairOfAces() {
        // Arrange
        Player testPlayer1 = new Player("Test player 1", new Scanner(System.in));
        testPlayer1.setHand(List.of(
                new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.ACE_OF_, CardSuit.CLUBS)
        ));
        // Act
        testPlayer1.splitStartingHand();
        // Assert
        Assertions.assertEquals("[[A♦], [A♣]]", testPlayer1.getSplitHands().toString());
    }
    @Test
    @DisplayName("(2) method being tested = splitStartingHand()")
    void testThatTheSplitStartingHandMethodSplitsAHandThatHasTwoCardsOfTheSameNonAceRank() {
        // Arrange
        Player testPlayer1 = new Player("Test player 1", new Scanner(System.in));
        testPlayer1.setHand(List.of(
                new Card(CardRank.FOUR_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR_OF_, CardSuit.CLUBS)
        ));
        // Act
        testPlayer1.splitStartingHand();
        // Assert
        Assertions.assertEquals("[[4♦], [4♣]]", testPlayer1.getSplitHands().toString());
    }
    @Test
    @DisplayName("(3) method being tested = splitStartingHand()")
    void testThatTheSplitStartingHandMethodDoesNotSplitAHandThatHasTwoCardsOfDifferentRanks() {
        // Arrange
        Player testPlayer1 = new Player("Test player 1", new Scanner(System.in));
        testPlayer1.setHand(List.of(
                new Card(CardRank.FIVE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.SEVEN_OF_, CardSuit.CLUBS)
        ));
        // Act
        testPlayer1.splitStartingHand();
        // Assert
        Assertions.assertEquals("[]", testPlayer1.getSplitHands().toString());
    }
    @Test
    @DisplayName("(4) method being tested = splitStartingHand()")
    void testThatTheSplitStartingHandMethodDoesNotSplitAHandThatHasLessThanTwoCards() {
        // Arrange
        Player testPlayer1 = new Player("Test player 1", new Scanner(System.in));
        testPlayer1.setHand(List.of(
                new Card(CardRank.FIVE_OF_, CardSuit.DIAMONDS)
        ));
        // Act
        testPlayer1.splitStartingHand();
        // Assert
        Assertions.assertEquals("[]", testPlayer1.getSplitHands().toString());
    }
    @Test
    @DisplayName("(5) method being tested = splitStartingHand()")
    void testThatTheSplitStartingHandMethodDoesNotSplitAHandThatHasMoreThanTwoCards() {
        // Arrange
        Player testPlayer1 = new Player("Test player 1", new Scanner(System.in));
        testPlayer1.setHand(List.of(
                new Card(CardRank.FIVE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.FIVE_OF_, CardSuit.HEARTS),
                new Card(CardRank.FIVE_OF_, CardSuit.CLUBS)
        ));
        // Act
        testPlayer1.splitStartingHand();
        // Assert
        Assertions.assertEquals("[]", testPlayer1.getSplitHands().toString());
    }
}
