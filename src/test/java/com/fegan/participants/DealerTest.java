package com.fegan.participants;

import com.fegan.Card;
import com.fegan.CardRank;
import com.fegan.CardSuit;
import com.fegan.Deck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DealerTest {


    private List<Card> cardList = List.of(
            new Card(CardRank.ACE_OF_, CardSuit.CLUBS),
            new Card(CardRank.TWO_OF_, CardSuit.CLUBS),
            new Card(CardRank.THREE_OF_, CardSuit.CLUBS),
            new Card(CardRank.FOUR_OF_, CardSuit.CLUBS),
            new Card(CardRank.FIVE_OF_, CardSuit.CLUBS),
            new Card(CardRank.SIX_OF_, CardSuit.CLUBS),
            new Card(CardRank.SEVEN_OF_, CardSuit.CLUBS),
            new Card(CardRank.EIGHT_OF_, CardSuit.CLUBS),
            new Card(CardRank.NINE_OF_, CardSuit.CLUBS),
            new Card(CardRank.TEN_OF_, CardSuit.CLUBS),
            new Card(CardRank.JACK_OF_, CardSuit.CLUBS),
            new Card(CardRank.QUEEN_OF_, CardSuit.CLUBS),
            new Card(CardRank.KING_OF_, CardSuit.CLUBS),

            new Card(CardRank.ACE_OF_, CardSuit.SPADES),
            new Card(CardRank.TWO_OF_, CardSuit.SPADES),
            new Card(CardRank.THREE_OF_, CardSuit.SPADES),
            new Card(CardRank.FOUR_OF_, CardSuit.SPADES),
            new Card(CardRank.FIVE_OF_, CardSuit.SPADES),
            new Card(CardRank.SIX_OF_, CardSuit.SPADES),
            new Card(CardRank.SEVEN_OF_, CardSuit.SPADES),
            new Card(CardRank.EIGHT_OF_, CardSuit.SPADES),
            new Card(CardRank.NINE_OF_, CardSuit.SPADES),
            new Card(CardRank.TEN_OF_, CardSuit.SPADES),
            new Card(CardRank.JACK_OF_, CardSuit.SPADES),
            new Card(CardRank.QUEEN_OF_, CardSuit.SPADES),
            new Card(CardRank.KING_OF_, CardSuit.SPADES),

            new Card(CardRank.ACE_OF_, CardSuit.HEARTS),
            new Card(CardRank.TWO_OF_, CardSuit.HEARTS),
            new Card(CardRank.THREE_OF_, CardSuit.HEARTS),
            new Card(CardRank.FOUR_OF_, CardSuit.HEARTS),
            new Card(CardRank.FIVE_OF_, CardSuit.HEARTS),
            new Card(CardRank.SIX_OF_, CardSuit.HEARTS),
            new Card(CardRank.SEVEN_OF_, CardSuit.HEARTS),
            new Card(CardRank.EIGHT_OF_, CardSuit.HEARTS),
            new Card(CardRank.NINE_OF_, CardSuit.HEARTS),
            new Card(CardRank.TEN_OF_, CardSuit.HEARTS),
            new Card(CardRank.JACK_OF_, CardSuit.HEARTS),
            new Card(CardRank.QUEEN_OF_, CardSuit.HEARTS),
            new Card(CardRank.KING_OF_, CardSuit.HEARTS),

            new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
            new Card(CardRank.TWO_OF_, CardSuit.DIAMONDS),
            new Card(CardRank.THREE_OF_, CardSuit.DIAMONDS),
            new Card(CardRank.FOUR_OF_, CardSuit.DIAMONDS),
            new Card(CardRank.FIVE_OF_, CardSuit.DIAMONDS),
            new Card(CardRank.SIX_OF_, CardSuit.DIAMONDS),
            new Card(CardRank.SEVEN_OF_, CardSuit.DIAMONDS),
            new Card(CardRank.EIGHT_OF_, CardSuit.DIAMONDS),
            new Card(CardRank.NINE_OF_, CardSuit.DIAMONDS),
            new Card(CardRank.TEN_OF_, CardSuit.DIAMONDS),
            new Card(CardRank.JACK_OF_, CardSuit.DIAMONDS),
            new Card(CardRank.QUEEN_OF_, CardSuit.DIAMONDS),
            new Card(CardRank.KING_OF_, CardSuit.DIAMONDS)
    );
    private Deck testDeck = new Deck(cardList);
    private Dealer testDealer = new Dealer(testDeck);
    private Player testPlayer = new Player("Test player");

    @Test
    @DisplayName("method being tested = getNextCardFromDeck()")
    void testGetNextCardFromDeckRetrievesTheNextCardInTheDeckAndIncrementsIndex() {
        // Arrange (see class fields)

        // Act and Assert
        Assertions.assertEquals(new Card(CardRank.ACE_OF_, CardSuit.CLUBS).toString(), testDealer.getNextCardFromDeck(testDeck).toString());
        Assertions.assertEquals(1, testDeck.getIndex());

        Assertions.assertEquals(new Card(CardRank.TWO_OF_, CardSuit.CLUBS).toString(), testDealer.getNextCardFromDeck(testDeck).toString());
        Assertions.assertEquals(2, testDeck.getIndex());

        Assertions.assertNotEquals(new Card(CardRank.FOUR_OF_, CardSuit.CLUBS).toString(), testDealer.getNextCardFromDeck(testDeck).toString());
        Assertions.assertNotEquals(4, testDeck.getIndex());
    }

    @Test
    @DisplayName("method being tested = addCardToHand()")
    void testWhetherTheAddCardToHandMethodAddsThePassedCardToThePassedHand() {
        // Arrange
        List<Card> emptyTestHand = new ArrayList<>();
        Card firstTestCardToAddToPassedCardList = new Card(CardRank.FIVE_OF_, CardSuit.HEARTS);
        Card secondTestCardToAddToPassedCardList = new Card(CardRank.FIVE_OF_, CardSuit.HEARTS);

        // Act and Assert on side effects of this void method
        testDealer.addCardToHand(firstTestCardToAddToPassedCardList, emptyTestHand);
        Assertions.assertEquals(firstTestCardToAddToPassedCardList, emptyTestHand.getFirst());
        Assertions.assertEquals(1, emptyTestHand.size());

        // Act and Assert on side effects of this void method
        testDealer.addCardToHand(secondTestCardToAddToPassedCardList, emptyTestHand);
        Assertions.assertEquals(firstTestCardToAddToPassedCardList, emptyTestHand.get(0));
        Assertions.assertEquals(secondTestCardToAddToPassedCardList, emptyTestHand.get(1));
        Assertions.assertEquals(2, emptyTestHand.size());

        // Edge case - what if the passed Card is null?
        // Refactor - could @BeforeEach improve this test?
    }

    @Test
    @DisplayName("(1) method being tested = calculateAndSetDealersHandScore()")
    void testThatTheCalculateAndSetDealersHandScoreMethodCorrectlyUpdatesTheDealersHandScoreWhenHandContainsAnAceAndTreatingItAsElevenWouldMakeHandBust() {
        // Arrange
        testDealer.setHand(List.of(
                new Card(CardRank.ACE_OF_, CardSuit.HEARTS),
                new Card(CardRank.FIVE_OF_, CardSuit.HEARTS),
                new Card(CardRank.SIX_OF_, CardSuit.HEARTS),
                new Card(CardRank.NINE_OF_, CardSuit.HEARTS)
        ));
        // Act
        testDealer.calculateAndSetDealersHandScore();
        //Assert
        Assertions.assertEquals(21, testDealer.getHandScore());
    }

    @Test
    @DisplayName("(2) method being tested = calculateAndSetDealersHandScore()")
    void testThatTheCalculateAndSetDealersHandScoreMethodCorrectlyUpdatesTheDealersHandScoreWhenHandContainsAcesAndTreatingThemAsElevenWouldMakeHandBust() {
        // Arrange
        testDealer.setHand(List.of(
                new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.FIVE_OF_, CardSuit.CLUBS),
                new Card(CardRank.FOUR_OF_, CardSuit.HEARTS),
                new Card(CardRank.NINE_OF_, CardSuit.HEARTS),
                new Card(CardRank.ACE_OF_, CardSuit.SPADES)
        ));
        // Act
        testDealer.calculateAndSetDealersHandScore();
        //Assert
        Assertions.assertEquals(20, testDealer.getHandScore());
    }

    @Test
    @DisplayName("(3) method being tested = calculateAndSetDealersHandScore()")
    void testThatTheCalculateAndSetDealersHandScoreMethodCorrectlyUpdatesTheDealersHandScoreWhenHandContainsAnAceAndScoreIsLessThan22WhenTreatingItAsEleven() {
        // Arrange
        testDealer.setHand(List.of(
                new Card(CardRank.ACE_OF_, CardSuit.HEARTS),
                new Card(CardRank.TWO_OF_, CardSuit.HEARTS),
                new Card(CardRank.THREE_OF_, CardSuit.HEARTS)
        ));
        // Act
        testDealer.calculateAndSetDealersHandScore();
        //Assert
        Assertions.assertEquals(16, testDealer.getHandScore());
    }

    @Test
    @DisplayName("(4) method being tested = calculateAndSetDealersHandScore()")
    void testThatTheCalculateAndSetDealersHandScoreMethodCorrectlyUpdatesTheDealersHandScoreWhenHandDoesNotContainAces() {
        // Arrange
        testDealer.setHand(List.of(
                new Card(CardRank.KING_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.TWO_OF_, CardSuit.CLUBS),
                new Card(CardRank.THREE_OF_, CardSuit.HEARTS),
                new Card(CardRank.THREE_OF_, CardSuit.SPADES)
        ));
        // Act
        testDealer.calculateAndSetDealersHandScore();
        //Assert
        Assertions.assertEquals(18, testDealer.getHandScore());
    }

    @Test
    @DisplayName("(5) method being tested = calculateAndSetDealersHandScore()")
    void testThatTheCalculateAndSetDealersHandScoreMethodCorrectlyUpdatesTheDealersHandScoreWhenHandDoesNotContainAcesAndScoreIsGreaterThan21() {
        // Arrange
        testDealer.setHand(List.of(
                new Card(CardRank.KING_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.SEVEN_OF_, CardSuit.CLUBS),
                new Card(CardRank.EIGHT_OF_, CardSuit.HEARTS)
        ));
        // Act
        testDealer.calculateAndSetDealersHandScore();
        //Assert
        Assertions.assertEquals(25, testDealer.getHandScore());
    }

    @Test
    @DisplayName("(1) method being tested = dealAndRevealStartingHands()")
    void testThatTheDealAndRevealStartingHandsMethodDealsCardsAtIndex0And2ToDealerAndIndex1And3ToPlayerAndSetsBothHandsWithTheseCards() {
        // Act
        testDealer.dealAndRevealStartingHands(testPlayer);
        // Assert
        Assertions.assertEquals("[A♣, 3♣]", testDealer.getHand().toString());
        Assertions.assertEquals("[2♣, 4♣]", testPlayer.getHand().toString());
        Assertions.assertEquals(4, testDealer.getCardDeck().getIndex());
    }

    @Test
    @DisplayName("(1) method being tested = executeDealersPredeterminedHitAndStandRules()")
    void testThatTheExecuteDealersPredeterminedHitAndStandRulesAddsCardsToTheirHandContainingAnAceTillScoreIsAtLeast17AndUpdatesDealersHandScore() {
        // Arrange
        List<Card> startingTestHand = new ArrayList<>();
        startingTestHand.add(new Card(CardRank.ACE_OF_, CardSuit.CLUBS));
        startingTestHand.add(new Card(CardRank.TWO_OF_, CardSuit.CLUBS));
        List<Card> testCardList = new ArrayList<>();
        testCardList.add(new Card(CardRank.KING_OF_, CardSuit.SPADES));
        testCardList.add(new Card(CardRank.SEVEN_OF_, CardSuit.CLUBS));
        Deck testDeckForExecuteDealersPredeterminedHitAndStandRulesMethod = new Deck(testCardList);
        Dealer testDealer2 = new Dealer(testDeckForExecuteDealersPredeterminedHitAndStandRulesMethod);
        // Act
        testDealer2.setHand(startingTestHand);
        testDealer2.setHandScore(13);
        testDealer2.getCardDeck().setIndex(0);
        testDealer2.executeDealersPredeterminedHitAndStandRules();
        // Assert
        Assertions.assertEquals(20, testDealer2.getHandScore());
    }

    @Test
    @DisplayName("(2) method being tested = executeDealersPredeterminedHitAndStandRules()")
    void testThatTheExecuteDealersPredeterminedHitAndStandRulesAddsOneCardToTheirHandAsThatIsWhenTheirHandScoreIsAtLeast17AndThenUpdatesDealersHandScore() {
        // Arrange
        List<Card> startingTestHand = new ArrayList<>();
        startingTestHand.add(new Card(CardRank.JACK_OF_, CardSuit.CLUBS));
        startingTestHand.add(new Card(CardRank.THREE_OF_, CardSuit.CLUBS));
        List<Card> testCardList = new ArrayList<>();
        testCardList.add(new Card(CardRank.KING_OF_, CardSuit.SPADES));
        Deck testDeckForExecuteDealersPredeterminedHitAndStandRulesMethod = new Deck(testCardList);
        Dealer testDealer2 = new Dealer(testDeckForExecuteDealersPredeterminedHitAndStandRulesMethod);
        testDealer2.setHand(startingTestHand);
        testDealer2.setHandScore(13);
        testDealer2.getCardDeck().setIndex(0);
        // Act
        testDealer2.executeDealersPredeterminedHitAndStandRules();
        // Assert
        Assertions.assertEquals(23, testDealer2.getHandScore());
    }

    @Test
    @DisplayName("(3) method being tested = executeDealersPredeterminedHitAndStandRules()")
    void testThatTheExecuteDealersPredeterminedHitAndStandRulesAddsMultipleCardsToTheirHandAsThatIsWhenTheirHandScoreIsAtLeast17AndThenUpdatesDealersHandScore() {
        // Arrange
        List<Card> startingTestHand = new ArrayList<>();
        startingTestHand.add(new Card(CardRank.TWO_OF_, CardSuit.CLUBS));
        startingTestHand.add(new Card(CardRank.THREE_OF_, CardSuit.CLUBS));
        List<Card> testCardList = new ArrayList<>();
        testCardList.add(new Card(CardRank.FOUR_OF_, CardSuit.SPADES));
        testCardList.add(new Card(CardRank.SEVEN_OF_, CardSuit.SPADES));
        testCardList.add(new Card(CardRank.FIVE_OF_, CardSuit.SPADES));
        Deck testDeckForExecuteDealersPredeterminedHitAndStandRulesMethod = new Deck(testCardList);
        Dealer testDealer2 = new Dealer(testDeckForExecuteDealersPredeterminedHitAndStandRulesMethod);
        testDealer2.setHand(startingTestHand);
        testDealer2.setHandScore(5);
        testDealer2.getCardDeck().setIndex(0);
        // Act
        testDealer2.executeDealersPredeterminedHitAndStandRules();
        // Assert
        Assertions.assertEquals(21, testDealer2.getHandScore());
    }

    @Test
    @DisplayName("(4) method being tested = executeDealersPredeterminedHitAndStandRules()")
    void testThatTheExecuteDealersPredeterminedHitAndStandRulesDoesNotAddsCardsToDealersHandWhenDealersStartingHandScoreIsMoreThan16AndThenUpdatesDealersHandScore() {
        // Arrange
        List<Card> startingTestHand = new ArrayList<>();
        startingTestHand.add(new Card(CardRank.ACE_OF_, CardSuit.CLUBS));
        startingTestHand.add(new Card(CardRank.EIGHT_OF_, CardSuit.CLUBS));
        Deck testDeckForExecuteDealersPredeterminedHitAndStandRulesMethod = new Deck(new ArrayList<>());
        Dealer testDealer2 = new Dealer(testDeckForExecuteDealersPredeterminedHitAndStandRulesMethod);
        testDealer2.setHand(startingTestHand);
        testDealer2.setHandScore(19);
        testDealer2.getCardDeck().setIndex(0);
        // Act
        testDealer2.executeDealersPredeterminedHitAndStandRules();
        // Assert
        Assertions.assertEquals(19, testDealer2.getHandScore());
    }



}