package com.fegan;

import com.fegan.participants.Dealer;
import com.fegan.participants.Player;

import java.util.List;

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
    Player testGamePlayer = new Player("Test game player");


//    @Test
//    void doesPlayerOrDealerHaveNaturalBlackJack() {
//        // Arrange (see above)
//        boolean didPlayerSplitHand = testGamePlayer.getSplitHands().isEmpty();
//        testGamePlayer.setHand(new ArrayList<>(List.of(
//                new Card()
//        )));
//        // Assert
//
//
//    }
}