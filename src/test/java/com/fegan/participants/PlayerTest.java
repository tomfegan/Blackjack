package com.fegan.participants;

import com.fegan.Card;
import com.fegan.CardRank;
import com.fegan.CardSuit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void canSplitTheirStartingHand() {
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
        // Assert and Act
        Assertions.assertTrue(testPlayer1.canSplitTheirStartingHand());
        Assertions.assertFalse(testPlayer2.canSplitTheirStartingHand());
        Assertions.assertFalse(testPlayer3.canSplitTheirStartingHand());
    }



    }
