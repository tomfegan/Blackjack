package com.fegan.participants;

import com.fegan.Card;
import com.fegan.CardRank;
import com.fegan.CardSuit;
import com.fegan.Deck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DealerTest {

    List<Card> cardList = List.of(
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
    Deck testDeck = new Deck(cardList);
    Dealer testDealer1 = new Dealer(testDeck);

    @Test
    void TestGetNextCardFromDeckRetriesTheNextCardInTheDeckAndIncrementsIndex() {
        // Arrange (see @BeforeAll)

//        List<Card> cardList = List.of(
//                new Card(CardRank.ACE_OF_, CardSuit.CLUBS),
//                new Card(CardRank.TWO_OF_, CardSuit.CLUBS),
//                new Card(CardRank.THREE_OF_,CardSuit.CLUBS),
//                new Card(CardRank.FOUR_OF_,CardSuit.CLUBS),
//                new Card(CardRank.FIVE_OF_,CardSuit.CLUBS),
//                new Card(CardRank.SIX_OF_,CardSuit.CLUBS),
//                new Card(CardRank.SEVEN_OF_,CardSuit.CLUBS),
//                new Card(CardRank.EIGHT_OF_,CardSuit.CLUBS),
//                new Card(CardRank.NINE_OF_,CardSuit.CLUBS),
//                new Card(CardRank.TEN_OF_,CardSuit.CLUBS),
//                new Card(CardRank.JACK_OF_,CardSuit.CLUBS),
//                new Card(CardRank.QUEEN_OF_,CardSuit.CLUBS),
//                new Card(CardRank.KING_OF_,CardSuit.CLUBS),
//
//                new Card(CardRank.ACE_OF_, CardSuit.SPADES),
//                new Card(CardRank.TWO_OF_, CardSuit.SPADES),
//                new Card(CardRank.THREE_OF_,CardSuit.SPADES),
//                new Card(CardRank.FOUR_OF_,CardSuit.SPADES),
//                new Card(CardRank.FIVE_OF_,CardSuit.SPADES),
//                new Card(CardRank.SIX_OF_,CardSuit.SPADES),
//                new Card(CardRank.SEVEN_OF_,CardSuit.SPADES),
//                new Card(CardRank.EIGHT_OF_,CardSuit.SPADES),
//                new Card(CardRank.NINE_OF_,CardSuit.SPADES),
//                new Card(CardRank.TEN_OF_,CardSuit.SPADES),
//                new Card(CardRank.JACK_OF_,CardSuit.SPADES),
//                new Card(CardRank.QUEEN_OF_,CardSuit.SPADES),
//                new Card(CardRank.KING_OF_,CardSuit.SPADES),
//
//                new Card(CardRank.ACE_OF_, CardSuit.HEARTS),
//                new Card(CardRank.TWO_OF_, CardSuit.HEARTS),
//                new Card(CardRank.THREE_OF_,CardSuit.HEARTS),
//                new Card(CardRank.FOUR_OF_,CardSuit.HEARTS),
//                new Card(CardRank.FIVE_OF_,CardSuit.HEARTS),
//                new Card(CardRank.SIX_OF_,CardSuit.HEARTS),
//                new Card(CardRank.SEVEN_OF_,CardSuit.HEARTS),
//                new Card(CardRank.EIGHT_OF_,CardSuit.HEARTS),
//                new Card(CardRank.NINE_OF_,CardSuit.HEARTS),
//                new Card(CardRank.TEN_OF_,CardSuit.HEARTS),
//                new Card(CardRank.JACK_OF_,CardSuit.HEARTS),
//                new Card(CardRank.QUEEN_OF_,CardSuit.HEARTS),
//                new Card(CardRank.KING_OF_,CardSuit.HEARTS),
//
//                new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
//                new Card(CardRank.TWO_OF_, CardSuit.DIAMONDS),
//                new Card(CardRank.THREE_OF_,CardSuit.DIAMONDS),
//                new Card(CardRank.FOUR_OF_,CardSuit.DIAMONDS),
//                new Card(CardRank.FIVE_OF_,CardSuit.DIAMONDS),
//                new Card(CardRank.SIX_OF_,CardSuit.DIAMONDS),
//                new Card(CardRank.SEVEN_OF_,CardSuit.DIAMONDS),
//                new Card(CardRank.EIGHT_OF_,CardSuit.DIAMONDS),
//                new Card(CardRank.NINE_OF_,CardSuit.DIAMONDS),
//                new Card(CardRank.TEN_OF_,CardSuit.DIAMONDS),
//                new Card(CardRank.JACK_OF_,CardSuit.DIAMONDS),
//                new Card(CardRank.QUEEN_OF_,CardSuit.DIAMONDS),
//                new Card(CardRank.KING_OF_,CardSuit.DIAMONDS)
//        );
//        Deck testDeck = new Deck(cardList);
//        Dealer testDealer1 = new Dealer(testDeck);

        // Assert and Act
        Assertions.assertEquals(new Card(CardRank.ACE_OF_, CardSuit.CLUBS).toString(), testDealer1.getNextCardFromDeck(testDeck).toString());
        Assertions.assertEquals(1, testDeck.getIndex());

        Assertions.assertEquals(new Card(CardRank.TWO_OF_, CardSuit.CLUBS).toString(), testDealer1.getNextCardFromDeck(testDeck).toString());
        Assertions.assertEquals(2, testDeck.getIndex());

        Assertions.assertNotEquals(new Card(CardRank.FOUR_OF_, CardSuit.CLUBS).toString(), testDealer1.getNextCardFromDeck(testDeck).toString());
        Assertions.assertNotEquals(4, testDeck.getIndex());
    }

    @Test
    void testWhetherTheAddCardToHandMethodAddsThePassedCardToThePassedHand() {
        // Arrange
        List<Card> emptyTestHand = new ArrayList<>();
        Card firstTestCardToAddToPassedCardList = new Card(CardRank.FIVE_OF_, CardSuit.HEARTS);
        Card secondTestCardToAddToPassedCardList = new Card(CardRank.FIVE_OF_, CardSuit.HEARTS);

        // Assert and Act on side effects of this void method
        testDealer1.addCardToHand(firstTestCardToAddToPassedCardList, emptyTestHand);

        Assertions.assertEquals(firstTestCardToAddToPassedCardList, emptyTestHand.getFirst());
        Assertions.assertEquals(1, emptyTestHand.size());

        testDealer1.addCardToHand(secondTestCardToAddToPassedCardList, emptyTestHand);

        Assertions.assertEquals(firstTestCardToAddToPassedCardList, emptyTestHand.get(0));
        Assertions.assertEquals(secondTestCardToAddToPassedCardList, emptyTestHand.get(1));
        Assertions.assertEquals(2, emptyTestHand.size());

        // Edge case - what if the passed Card is null?
        // Refactor - could @BeforeEach improve this test?
    }
}