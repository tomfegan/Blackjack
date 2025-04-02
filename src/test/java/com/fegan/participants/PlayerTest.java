package com.fegan.participants;

import com.fegan.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerTest {

    @Mock
    Scanner mockScanner;
    @InjectMocks
    Player testPlayer = new Player("Test player");

    @Test
    @DisplayName("method being tested = canSplitTheirStartingHand()")
    void testWhetherTheCanSplitTheirStartingHandMethodReturnsTrueWhenThePlayerIsDealtCardsOfTheSameRankAndFalseIfNot() {
        // Arrange 1
        testPlayer.setHand(List.of(
                new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.ACE_OF_, CardSuit.CLUBS)
        ));
        // Act and Assert 1
        Assertions.assertTrue(testPlayer.canSplitTheirStartingHand());

        // Arrange 2
        testPlayer.setHand(List.of(
                new Card(CardRank.TWO_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.ACE_OF_, CardSuit.CLUBS)
        ));
        // Act and Assert 2
        Assertions.assertFalse(testPlayer.canSplitTheirStartingHand());

        // Arrange 3
        testPlayer.setHand(List.of(
                new Card(CardRank.TEN_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.JACK_OF_, CardSuit.DIAMONDS)
        ));
        // Act and Assert 3
        Assertions.assertFalse(testPlayer.canSplitTheirStartingHand());

        // Arrange 4
        testPlayer.setHand(List.of(
                new Card(CardRank.TEN_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.TEN_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.TEN_OF_, CardSuit.DIAMONDS)
        ));
        // Act and Assert 4
        Assertions.assertFalse(testPlayer.canSplitTheirStartingHand());

        // Arrange 5
        testPlayer.setHand(List.of(
                new Card(CardRank.JACK_OF_, CardSuit.DIAMONDS)
        ));
        // Act and Assert 5
        Assertions.assertFalse(testPlayer.canSplitTheirStartingHand());
    }

    @Test
    @DisplayName("(1) method being tested = doesPlayerWantNextCard()")
    void testMethodReturnsTrueWhenPlayerEntersHWhenAskedIfTheyWantNextCard() {
        // Arrange - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        // Arrange (cont), Act and Assert
        // First mock input and test
        when(mockScanner.next()).thenReturn("HiT");
        Assertions.assertTrue(testPlayer.doesPlayerWantNextCard(), "Expect true if user enters HiT");

        // Second mock input and test
        when(mockScanner.next()).thenReturn("hI");
        Assertions.assertTrue(testPlayer.doesPlayerWantNextCard(), "Expect true if user enters hI");

        // Third mock input and test
        when(mockScanner.next()).thenReturn("HIYA");
        Assertions.assertTrue(testPlayer.doesPlayerWantNextCard(), "Expect true if user enters HIYA");

        // Fourth mock input and test
        when(mockScanner.next()).thenReturn("Highlights");
        Assertions.assertTrue(testPlayer.doesPlayerWantNextCard(), "Expect true if user enters Highlights");

        // Fifth mock input and test
        when(mockScanner.next()).thenReturn("hhhhhhhhHHHH");
        Assertions.assertTrue(testPlayer.doesPlayerWantNextCard(), "Expect true if user enters hhhhhhhhHHHH");
    }

    @Test
    @DisplayName("(2) method being tested = doesPlayerWantNextCard()")
    void testThatMethodReturnsFalseWhenPlayerDoesNotEnterHWhenAskedIfTheyWantNextCard() {
        // Arrange - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        // Arrange (cont), Act and Assert

        // First mock input and test
        when(mockScanner.next()).thenReturn("-1");
        Assertions.assertFalse(testPlayer.doesPlayerWantNextCard(), "Expect false if user enters -1");

        // Second mock input and test
        when(mockScanner.next()).thenReturn("200");
        Assertions.assertFalse(testPlayer.doesPlayerWantNextCard(), "Expect false if user enters 200");

        // Third mock input and test
        when(mockScanner.next()).thenReturn("staND");
        Assertions.assertFalse(testPlayer.doesPlayerWantNextCard(), "Expect false if user enters staND");

        // Fourth mock input and test
        when(mockScanner.next()).thenReturn("@");
        Assertions.assertFalse(testPlayer.doesPlayerWantNextCard(), "Expect false if user enters @");
    }

    @Test
    @DisplayName("(3) method under test = DoTheyWantToSplitTheirStartingHand()")
    void testThatMethodReturnsTrueWhenPlayerEntersYWhenAskedDoTheyWantToSplitTheirStartingHand() {
        // Arrange - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        // Arrange (cont), Act and Assert

        // First mock input and test
        when(mockScanner.next()).thenReturn("YES");
        Assertions.assertTrue(testPlayer.doTheyWantToSplitTheirStartingHand(), "Expect true if user enters YES");

        // Second mock input and test
        when(mockScanner.next()).thenReturn("y");
        Assertions.assertTrue(testPlayer.doTheyWantToSplitTheirStartingHand(), "Expect true if user enters y");

        // Third mock input and test
        when(mockScanner.next()).thenReturn("YEP");
        Assertions.assertTrue(testPlayer.doTheyWantToSplitTheirStartingHand(), "Expect true if user enters YEP");

        // Fourth mock input and test
        when(mockScanner.next()).thenReturn("Y");
        Assertions.assertTrue(testPlayer.doTheyWantToSplitTheirStartingHand(), "Expect true if user enters Y");

        // Fifth mock input and test
        when(mockScanner.next()).thenReturn("yes");
        Assertions.assertTrue(testPlayer.doTheyWantToSplitTheirStartingHand(), "Expect true if user enters yes");
    }

    @Test
    @DisplayName("(4) method under test = DoTheyWantToSplitTheirStartingHand()")
    void testThatMethodReturnsFalseWhenPlayerDoesNotEnterYWhenAskedDoTheyWantToSplitTheirStartingHand() {
        // Arrange - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        // Arrange (cont), Act and Assert
        // First mock input and test
        when(mockScanner.next()).thenReturn("ES");
        Assertions.assertFalse(testPlayer.doTheyWantToSplitTheirStartingHand(), "Expect false if user enters ES");

        // Second mock input and test
        when(mockScanner.next()).thenReturn("-1");
        Assertions.assertFalse(testPlayer.doTheyWantToSplitTheirStartingHand(), "Expect false if user enters -1");

        // Third mock input and test
        when(mockScanner.next()).thenReturn("@");
        Assertions.assertFalse(testPlayer.doTheyWantToSplitTheirStartingHand(), "Expect false if user enters @");

        // Fourth mock input and test
        when(mockScanner.next()).thenReturn("null");
        Assertions.assertFalse(testPlayer.doTheyWantToSplitTheirStartingHand(), "Expect false if user enters null");
    }

    @Test
    @DisplayName("method being tested = updateWinRecord()")
    void testThatTheUpdateWinRecordMethodAppendsThePassedGameResultToThePlayersGameResults() {
        // Arrange
        testPlayer.setGameResults(new StringBuilder("Test player results:"));
        String exceptionMessage = "You can only pass a GameResult enum to the updateWinRecord() method";

        // Act and Assert 1
        testPlayer.updateWinRecord(GameResult.WIN);
        Assertions.assertEquals("Test player results:-Win", testPlayer.getGameResults().toString());
        // Act and Assert 2
        testPlayer.updateWinRecord(GameResult.LOSS);
        Assertions.assertEquals("Test player results:-Win-Loss", testPlayer.getGameResults().toString());
        // Act and Assert 3
        testPlayer.updateWinRecord(GameResult.DRAW);
        Assertions.assertEquals("Test player results:-Win-Loss-Draw", testPlayer.getGameResults().toString());
        // Act and Assert 4
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testPlayer.updateWinRecord(null);
            throw new IllegalArgumentException(exceptionMessage);
        });
    }

    @Test
    @DisplayName("method being tested = chooseWhereToSplitDeck()")
    void testThatTheChooseWhereToSplitDeckMethodReturnsAPositiveIntegerBetweenZeroAndTheNumberOfCardsInTheDeck() {
        // Arrange - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
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

        // Arrange (cont), Act and Assert

        // First mock input and test
        when(mockScanner.nextInt()).thenReturn(2);
        Assertions.assertEquals(18, testPlayer.chooseWhereToSplitDeck(testGameDeck9));
        // Second mock input and test
        when(mockScanner.nextInt()).thenReturn(0);
        Assertions.assertEquals(20, testPlayer.chooseWhereToSplitDeck(testGameDeck9));
        // Third mock input and test
        when(mockScanner.nextInt()).thenReturn(-1).thenReturn(1);
        Assertions.assertEquals(19, testPlayer.chooseWhereToSplitDeck(testGameDeck9));
        // Fourth mock input and test
        when(mockScanner.nextInt()).thenReturn(21).thenReturn(1);
        Assertions.assertEquals(19, testPlayer.chooseWhereToSplitDeck(testGameDeck9));
        // Fifth mock input and test
        when(mockScanner.nextInt())
                .thenThrow(new InputMismatchException()) // Simulates player entering a string to nextInt()
                .thenReturn(-1)
                .thenReturn(21)
                .thenReturn(5);
        Assertions.assertEquals(15, testPlayer.chooseWhereToSplitDeck(testGameDeck9));
    }

    @Test
    @DisplayName("method being tested = assignAceValueToOneOrEleven()")
    void testThatTheAssignAceValueToOneOrElevenReturnsAnIntValueOfOneOrEleven() {
        // Arrange 1 - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        when(mockScanner.nextInt()).thenReturn(11);
        // Act and Assert 1
        Assertions.assertEquals(11, testPlayer.assignAceValueToOneOrEleven());

        // Arrange 2 - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        when(mockScanner.nextInt()).thenReturn(1);
        // Act and Assert 2
        Assertions.assertEquals(1, testPlayer.assignAceValueToOneOrEleven());

        // Arrange 3 - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        when(mockScanner.nextInt()).thenReturn(2).thenReturn(11);
        // Act and Assert 3
        Assertions.assertEquals(11, testPlayer.assignAceValueToOneOrEleven());

        // Arrange 4 - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        when(mockScanner.nextInt()).thenReturn(-1).thenReturn(1);
        // Act and Assert 4
        Assertions.assertEquals(1, testPlayer.assignAceValueToOneOrEleven());


        // Arrange 5 - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        when(mockScanner.nextInt()).thenReturn(111).thenReturn(11);
        // Act and Assert 5
        Assertions.assertEquals(11, testPlayer.assignAceValueToOneOrEleven());

        // Arrange 6 - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        when(mockScanner.nextInt())
                .thenThrow(new InputMismatchException("This simulates test player 10 entering a non-integer value"))
                .thenReturn(11);
        // Act and Assert 6
        Assertions.assertEquals(11, testPlayer.assignAceValueToOneOrEleven());
    }

    @Test
    @DisplayName("method being tested = calculateScoreForPlayerHand()")
    void testThatTheCalculateScoreForPlayerHandMethodReturnsTheCorrectHandScore() {
        // Arrange - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        testPlayer.setHand(List.of(
                new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.EIGHT_OF_, CardSuit.CLUBS)
        ));
        when(mockScanner.nextInt()).thenReturn(11);

        // Act and Assert 1 - hand contains Ace
        Assertions.assertEquals(19,
                testPlayer.calculateScoreForPlayerHand(testPlayer.getHand()));

        // Arrange
        testPlayer.setHand(List.of(
                new Card(CardRank.TWO_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.TEN_OF_, CardSuit.CLUBS),
                new Card(CardRank.FIVE_OF_, CardSuit.HEARTS)
        ));
        // Act and Assert 2 - hand does not contain Aces
        Assertions.assertEquals(17,
                testPlayer.calculateScoreForPlayerHand(testPlayer.getHand()));


        // Arrange - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        testPlayer.setHand(List.of(
                new Card(CardRank.TEN_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.JACK_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.TWO_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.ACE_OF_, CardSuit.CLUBS),
                new Card(CardRank.FIVE_OF_, CardSuit.HEARTS)
        ));
        when(mockScanner.nextInt()).thenReturn(1);

        // Act and Assert 3 - hand contains 1 Ace (treated as 1) and hand score exceeds 21
        Assertions.assertEquals(28,
                testPlayer.calculateScoreForPlayerHand(testPlayer.getHand()));
    }

    @Test
    @DisplayName("(1) method being tested = splitStartingHand()")
    void testThatTheSplitStartingHandMethodSplitsAPairOfAces() {
        // Arrange - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        testPlayer.setHand(List.of(
                new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.ACE_OF_, CardSuit.CLUBS)
        ));
        // Act
        testPlayer.splitStartingHand();
        // Assert
        Assertions.assertEquals("[[A♦], [A♣]]", testPlayer.getSplitHands().toString());
    }

    @Test
    @DisplayName("(2) method being tested = splitStartingHand()")
    void testThatTheSplitStartingHandMethodSplitsAHandThatHasTwoCardsOfTheSameNonAceRank() {
        // Arrange - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        testPlayer.setHand(List.of(
                new Card(CardRank.FOUR_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR_OF_, CardSuit.CLUBS)
        ));
        // Act
        testPlayer.splitStartingHand();
        // Assert
        Assertions.assertEquals("[[4♦], [4♣]]", testPlayer.getSplitHands().toString());
    }

    @Test
    @DisplayName("(3) method being tested = splitStartingHand()")
    void testThatTheSplitStartingHandMethodDoesNotSplitAHandThatHasTwoCardsOfDifferentRanks() {
        // Arrange - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        testPlayer.setHand(List.of(
                new Card(CardRank.FIVE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.SEVEN_OF_, CardSuit.CLUBS)
        ));
        // Act
        testPlayer.splitStartingHand();
        // Assert
        Assertions.assertEquals("[]", testPlayer.getSplitHands().toString());
    }

    @Test
    @DisplayName("(4) method being tested = splitStartingHand()")
    void testThatTheSplitStartingHandMethodDoesNotSplitAHandThatHasLessThanTwoCards() {
        // Arrange - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        testPlayer.setHand(List.of(
                new Card(CardRank.FIVE_OF_, CardSuit.DIAMONDS)
        ));
        // Act
        testPlayer.splitStartingHand();
        // Assert
        Assertions.assertEquals("[]", testPlayer.getSplitHands().toString());
    }

    @Test
    @DisplayName("(5) method being tested = splitStartingHand()")
    void testThatTheSplitStartingHandMethodDoesNotSplitAHandThatHasMoreThanTwoCards() {
        // Arrange - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        testPlayer.setHand(List.of(
                new Card(CardRank.FIVE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.FIVE_OF_, CardSuit.HEARTS),
                new Card(CardRank.FIVE_OF_, CardSuit.CLUBS)
        ));
        // Act
        testPlayer.splitStartingHand();
        // Assert
        Assertions.assertEquals("[]", testPlayer.getSplitHands().toString());
    }

    @Test
    @DisplayName("(1) method being tested = playSplitHandsAndUpdateHandScoreWithBestHand()")
    void testThatMethodPlaySplitHandsAndUpdateHandScoreWithBestHandReturns21FromSplitsAceHandScoresOf18And21() {
        // Arrange - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        List<List<Card>> testSplitsHands = new ArrayList<>();

        List<Card> oneTestSplitHand = new ArrayList<>();
        oneTestSplitHand.add(new Card(CardRank.ACE_OF_, CardSuit.CLUBS));

        List<Card> otherTestSplitHand = new ArrayList<>();
        otherTestSplitHand.add(new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS));

        testSplitsHands.add(oneTestSplitHand);
        testSplitsHands.add(otherTestSplitHand);

        testPlayer.setSplitHands(testSplitsHands);

        List<Card> testCardList = new ArrayList<>();
        testCardList.add(new Card(CardRank.KING_OF_, CardSuit.SPADES));
        testCardList.add(new Card(CardRank.SEVEN_OF_, CardSuit.CLUBS));
        testCardList.add(new Card(CardRank.SEVEN_OF_, CardSuit.HEARTS));
        testCardList.add(new Card(CardRank.SEVEN_OF_, CardSuit.DIAMONDS));
        testCardList.add(new Card(CardRank.SEVEN_OF_, CardSuit.DIAMONDS));
        testCardList.add(new Card(CardRank.SEVEN_OF_, CardSuit.SPADES));
        testCardList.add(new Card(CardRank.SEVEN_OF_, CardSuit.HEARTS));
        testCardList.add(new Card(CardRank.SEVEN_OF_, CardSuit.CLUBS));
        testCardList.add(new Card(CardRank.SEVEN_OF_, CardSuit.DIAMONDS));
        testCardList.add(new Card(CardRank.SEVEN_OF_, CardSuit.HEARTS));
        testCardList.add(new Card(CardRank.SEVEN_OF_, CardSuit.SPADES));

        Deck testDeck14 = new Deck(testCardList);
        Dealer testDealer14 = new Dealer(testDeck14);

        when(mockScanner.nextInt()).thenReturn(11);
        // Act
        testPlayer.playSplitHandsAndUpdateHandScoreWithBestHand(testDealer14);;
        // Assert
        Assertions.assertEquals(21, testPlayer.getHandScore());
    }

    @Test
    @DisplayName("(2) method being tested = playSplitHandsAndUpdateHandScoreWithBestHand()")
    void testThatMethodPlaySplitHandsAndUpdateHandScoreWithBestHandReturns20FromSplitsNonAceHandScoresOf23And20() {
        // Arrange - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        List<List<Card>> testSplitsHands = new ArrayList<>();

        List<Card> oneTestSplitHand = new ArrayList<>();
        oneTestSplitHand.add(new Card(CardRank.FOUR_OF_, CardSuit.CLUBS));

        List<Card> otherTestSplitHand = new ArrayList<>();
        otherTestSplitHand.add(new Card(CardRank.FOUR_OF_, CardSuit.DIAMONDS));

        testSplitsHands.add(oneTestSplitHand);
        testSplitsHands.add(otherTestSplitHand);

        testPlayer.setSplitHands(testSplitsHands);

        List<Card> testCardList = new ArrayList<>();
        testCardList.add(new Card(CardRank.KING_OF_, CardSuit.SPADES));
        testCardList.add(new Card(CardRank.NINE_OF_, CardSuit.CLUBS));
        testCardList.add(new Card(CardRank.SEVEN_OF_, CardSuit.HEARTS));
        testCardList.add(new Card(CardRank.NINE_OF_, CardSuit.DIAMONDS));

        Deck testDeck = new Deck(testCardList);
        Dealer testDealer = new Dealer(testDeck);

        when(mockScanner.next())
                .thenReturn("h")
                .thenReturn("h")
                .thenReturn("h")
                .thenReturn("h")
                .thenReturn("s");

        // Act
        testPlayer.playSplitHandsAndUpdateHandScoreWithBestHand(testDealer);
        // Assert
        Assertions.assertEquals(20, testPlayer.getHandScore());

    }

    @Test
    @DisplayName("(2) method being tested = playSplitHandsAndUpdateHandScoreWithBestHand()")
    void testThatMethodPlaySplitHandsAndUpdateHandScoreWithBestHandReturns21FromSplitsNonAceHandScoresOf20And21() {
        // Arrange - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        List<List<Card>> testSplitsHands = new ArrayList<>();

        List<Card> oneTestSplitHand = new ArrayList<>();
        oneTestSplitHand.add(new Card(CardRank.EIGHT_OF_, CardSuit.CLUBS));

        List<Card> otherTestSplitHand = new ArrayList<>();
        otherTestSplitHand.add(new Card(CardRank.EIGHT_OF_, CardSuit.DIAMONDS));

        testSplitsHands.add(oneTestSplitHand);
        testSplitsHands.add(otherTestSplitHand);

        testPlayer.setSplitHands(testSplitsHands);

        List<Card> testCardList = new ArrayList<>();
        testCardList.add(new Card(CardRank.THREE_OF_, CardSuit.SPADES));
        testCardList.add(new Card(CardRank.NINE_OF_, CardSuit.CLUBS));
        testCardList.add(new Card(CardRank.SEVEN_OF_, CardSuit.HEARTS));
        testCardList.add(new Card(CardRank.SIX_OF_, CardSuit.DIAMONDS));
        testCardList.add(new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS));

        Deck testDeck16 = new Deck(testCardList);
        Dealer testDealer16 = new Dealer(testDeck16);

        when(mockScanner.next())
                .thenReturn("h")
                .thenReturn("h")
                .thenReturn("s")
                .thenReturn("h")
                .thenReturn("h")
                .thenReturn("s");
        // Act
        testPlayer.playSplitHandsAndUpdateHandScoreWithBestHand(testDealer16);
        // Assert
        Assertions.assertEquals(21, testPlayer.getHandScore());

    }

    @Test
    @DisplayName("(1) method being tested = playerAsksDealerToHitOrStands()")
    void testThatThePlayerAsksDealerToHitOrStandsMethodReturns19WhenPlayerHitsTwiceAndTreatsAceAsElevenAndThenOne() {
        // Arrange - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        List<Card> testPlayersHand = new ArrayList<>();
        testPlayersHand.add(new Card(CardRank.FIVE_OF_, CardSuit.HEARTS));
        testPlayersHand.add(new Card(CardRank.FOUR_OF_, CardSuit.CLUBS));
        testPlayer.setHand(testPlayersHand);
        testPlayer.setHandScore(9);

        List<Card> testCardListFromWhichToDrawCardsFrom = new ArrayList<>();
        testCardListFromWhichToDrawCardsFrom.add(new Card(CardRank.ACE_OF_, CardSuit.SPADES));
        testCardListFromWhichToDrawCardsFrom.add(new Card(CardRank.NINE_OF_, CardSuit.CLUBS));

        Deck testDeck = new Deck(testCardListFromWhichToDrawCardsFrom);
        Dealer testDealer = new Dealer(testDeck);

        when(mockScanner.next()).thenReturn("h").thenReturn("h").thenReturn("s"); // player asks for 2 more cards and then stands
        when(mockScanner.nextInt()).thenReturn(11).thenReturn(1); // player assigns 11 to Ace and then assigns 1 to stop them going bust

        // Act and Assert
        Assertions.assertEquals(19, testPlayer.playerAsksDealerToHitOrStands(testDealer, testPlayersHand));
    }
    @Test
    @DisplayName("(2) method being tested = playerAsksDealerToHitOrStands()")
    void testThatThePlayerAsksDealerToHitOrStandsMethodReturns20WhenPlayerStands() {
        // Arrange - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        List<Card> testPlayersHand = new ArrayList<>();
        testPlayersHand.add(new Card(CardRank.JACK_OF_, CardSuit.HEARTS));
        testPlayersHand.add(new Card(CardRank.TEN_OF_, CardSuit.DIAMONDS));
        testPlayer.setHand(testPlayersHand);
        testPlayer.setHandScore(20);

        List<Card> testCardListFromWhichToDrawCardsFrom = new ArrayList<>();
        Deck testDeck = new Deck(testCardListFromWhichToDrawCardsFrom);
        Dealer testDealer = new Dealer(testDeck);

        when(mockScanner.next()).thenReturn("s"); // player does not ask for more cards i.e., stands after being dealt starting hand

        // Act and Assert
        Assertions.assertEquals(20, testPlayer.playerAsksDealerToHitOrStands(testDealer, testPlayersHand));
    }
    @Test
    @DisplayName("(3) method being tested = playerAsksDealerToHitOrStands()")
    void testThatThePlayerAsksDealerToHitOrStandsMethodStopsAskingIfPlayerWantsANewCardWhenScoreIsGreaterThan21() {
        // Arrange - @ExtendWith(MockitoExtension.class), @Mock, @InjectMocks
        List<Card> testPlayersHand = new ArrayList<>();
        testPlayersHand.add(new Card(CardRank.TWO_OF_, CardSuit.SPADES));
        testPlayersHand.add(new Card(CardRank.FOUR_OF_, CardSuit.CLUBS));
        testPlayer.setHand(testPlayersHand);
        testPlayer.setHandScore(6);

        List<Card> testCardListFromWhichToDrawCardsFrom = new ArrayList<>();
        testCardListFromWhichToDrawCardsFrom.add(new Card(CardRank.SIX_OF_, CardSuit.CLUBS));
        testCardListFromWhichToDrawCardsFrom.add(new Card(CardRank.THREE_OF_, CardSuit.HEARTS));
        testCardListFromWhichToDrawCardsFrom.add(new Card(CardRank.SEVEN_OF_, CardSuit.DIAMONDS));
        testCardListFromWhichToDrawCardsFrom.add(new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS));

        Deck testDeck = new Deck(testCardListFromWhichToDrawCardsFrom);
        Dealer testDealer = new Dealer(testDeck);

        when(mockScanner.next()).thenReturn("h").thenReturn("h").thenReturn("h"); // player asks for 3 more cards - as this makes them go bust, they are not asked if they want another card

        // Act and Assert
        Assertions.assertEquals(22, testPlayer.playerAsksDealerToHitOrStands(testDealer, testPlayersHand));
    }
}


