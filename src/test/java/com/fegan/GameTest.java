package com.fegan;

import com.fegan.participants.Dealer;
import com.fegan.participants.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

class GameTest {

    private List<Card> gameCardList = List.of(
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
    private Dealer testDealer = new Dealer(new Deck(gameCardList));
    private Player testPlayer = new Player("Test player");
    private Game testGame = new Game(testPlayer, testDealer);

    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PrintStream printStream = new PrintStream(outputStream);
    private PrintStream originalSystemOut = System.out;

    @Test
    @DisplayName("(1) method being tested = decideResultAndUpdateGameResult()")
    void testThatTheDecideResultAndUpdateGameResultMethodCorrectlyDeterminesDealerAndPlayerDrawWhenTheyBothHaveNaturalBlackjacksAndADrawIsAddedToGameResult() {
        // Arrange
        testPlayer.setHand(List.of(
                new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.TEN_OF_, CardSuit.HEARTS)
        ));
        testPlayer.setHandScore(21);

        testDealer.setHand(List.of(
                new Card(CardRank.TEN_OF_, CardSuit.SPADES),
                new Card(CardRank.ACE_OF_, CardSuit.CLUBS)
        ));
        testDealer.setHandScore(21);

        // Reassign the "standard" output stream
        System.setOut(printStream);

        // Act and Assert
        try {
            testGame.decideResultAndUpdateGameResult();
            String capturedOutput = outputStream.toString().trim();
            String[] outputsByLine = capturedOutput.split("\n");
            // check result is correct
            Assertions.assertEquals("Push! Dealer and Test player got natural blackjacks.", outputsByLine[0]);
            // check gameResult is updated correctly
            Assertions.assertEquals("Test player's win record:-Draw", testPlayer.getGameResults().toString());

        } finally {
            // Reassign the "standard" output stream so printing to console resumes after test completed
            System.setOut(originalSystemOut);
        }
    }


    @Test
    @DisplayName("(2) method being tested = decideResultAndUpdateGameResult()")
    void checkThatTheDecideResultAndUpdateGameResultMethodCorrectlyDeterminesDealerWinsWhenTheyGetNaturalBlackjackAndPlayerSplitsHandAndScores21WithTwoCardsAndsALossToGameResult() {
        // Arrange

        // Simulate player splitting Aces so splitHands is not empty so that when checking for natural blackjack, it returns false
        testPlayer.setSplitHands(List.of(
                List.of(new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                        new Card(CardRank.TEN_OF_, CardSuit.CLUBS)),
                List.of(new Card(CardRank.EIGHT_OF_, CardSuit.DIAMONDS),
                        new Card(CardRank.ACE_OF_, CardSuit.CLUBS))
        ));
        testPlayer.setHand(List.of(
                new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.TEN_OF_, CardSuit.CLUBS)));
        testPlayer.setHandScore(21);

        testDealer.setHand(List.of(
                new Card(CardRank.TEN_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.ACE_OF_, CardSuit.SPADES)
        ));
        testDealer.setHandScore(21);

        // Reassign the "standard" output stream
        System.setOut(printStream);

        // Act and Assert
        try {
            testGame.decideResultAndUpdateGameResult();
            String capturedOutput = outputStream.toString().trim();
            String[] outputsByLine = capturedOutput.split("\n");
            // check result is correct
            Assertions.assertEquals("Dealer wins with natural blackjack as Test player does not have a natural blackjack.", outputsByLine[0]);
            // check gameResult is updated correctly
            Assertions.assertEquals("Test player's win record:-Loss", testPlayer.getGameResults().toString());

        } finally {
            // Reassign the "standard" output stream so printing to console resumes after test completed
            System.setOut(originalSystemOut);

        }
    }

    @Test
    @DisplayName("(3) method being tested = decideResultAndUpdateGameResult()")
    void checkThatTheDecideResultAndUpdateGameResultMethodCorrectlyDeterminesPlayerWinsWhenTheyHaveNaturalBlackjackAndDealerDoesNotAndAddsAWinToGameResult() {
        // Arrange
        testPlayer.setHand(List.of(
                new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.TEN_OF_, CardSuit.CLUBS)
        ));
        testPlayer.setHandScore(21);
        testDealer.setHandScore(13);

        // Reassign the "standard" output stream
        System.setOut(printStream);

        // Act and Assert
        try {
            testGame.decideResultAndUpdateGameResult();
            String capturedOutput = outputStream.toString().trim();
            String[] outputsByLine = capturedOutput.split("\n");
            // check result is correct
            Assertions.assertEquals("Test player wins with natural blackjack as dealer does not have a natural blackjack.", outputsByLine[0]);
            // check gameResult is updated correctly
            Assertions.assertEquals("Test player's win record:-Win", testPlayer.getGameResults().toString());

        } finally {
            // Reassign the "standard" output stream so printing to console resumes after test completed
            System.setOut(originalSystemOut);
        }
    }

    @Test
    @DisplayName("(4) method being tested = decideResultAndUpdateGameResult()")
    void checkThatTheDecideResultAndUpdateGameResultMethodCorrectlyDeterminesDealerWinsWhenTheyGetNaturalBlackjackAndPlayerScores21WithMoreThanTwoCardsAndALossIsAddedToGameResult() {
        // Arrange
        testPlayer.setHand(List.of(
                new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.FIVE_OF_, CardSuit.CLUBS),
                new Card(CardRank.FIVE_OF_, CardSuit.HEARTS)));
        testPlayer.setHandScore(21);

        testDealer.setHand(List.of(
                new Card(CardRank.TEN_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.ACE_OF_, CardSuit.SPADES)
        ));
        testDealer.setHandScore(21);

        // Reassign the "standard" output stream
        System.setOut(printStream);

        // Act and Assert
        try {
            testGame.decideResultAndUpdateGameResult();
            String capturedOutput = outputStream.toString().trim();
            String[] outputsByLine = capturedOutput.split("\n");
            // check result is correct
            Assertions.assertEquals("Dealer wins with natural blackjack as Test player does not have a natural blackjack.", outputsByLine[0]);
            // check gameResult is updated correctly
            Assertions.assertEquals("Test player's win record:-Loss", testPlayer.getGameResults().toString());

        } finally {
            // Reassign the "standard" output stream so printing to console resumes after test completed
            System.setOut(originalSystemOut);
        }
    }

    @Test
    @DisplayName("(5) method being tested = decideResultAndUpdateGameResult()")
    void checkThatTheDecideResultAndUpdateGameResultMethodCorrectlyDeterminesDealerWinsWhenBothPlayerAndDealerScoreIsGreaterThan21AndAddsALossToGameResult() {
        // Arrange
        testPlayer.setHandScore(22);
        testDealer.setHandScore(28);

        // Reassign the "standard" output stream
        System.setOut(printStream);

        // Act and Assert
        try {
            testGame.decideResultAndUpdateGameResult();
            String capturedOutput = outputStream.toString().trim();
            String[] outputsByLine = capturedOutput.split("\n");
            // check result is correct
            Assertions.assertEquals("Both bust but dealer wins as per the rules in the README.md file.", outputsByLine[0]);
            // check gameResult is updated correctly
            Assertions.assertEquals("Test player's win record:-Loss", testPlayer.getGameResults().toString());
        } finally {
            // Reassign the "standard" output stream so printing to console resumes after test completed
            System.setOut(originalSystemOut);
        }
    }

    @Test
    @DisplayName("(6) method being tested = decideResultAndUpdateGameResult()")
    void checkThatTheDecideResultAndUpdateGameResultMethodCorrectlyDeterminesDealerWinsWhenPlayerScoreIsGreaterThan21AndDealerScoresLessThan22AndAddsALossToGameResult() {
        // Arrange
        testPlayer.setHandScore(26);
        testDealer.setHandScore(17);

        // Reassign the "standard" output stream
        System.setOut(printStream);

        // Act and Assert
        try {
            testGame.decideResultAndUpdateGameResult();
            String capturedOutput = outputStream.toString().trim();
            String[] outputsByLine = capturedOutput.split("\n");
            // check result is correct
            Assertions.assertEquals("Test player bust, dealer wins with 17.", outputsByLine[0]);
            // check gameResult is updated correctly
            Assertions.assertEquals("Test player's win record:-Loss", testPlayer.getGameResults().toString());
        } finally {
            // Reassign the "standard" output stream so printing to console resumes after test completed
            System.setOut(originalSystemOut);
        }
    }

    @Test
    @DisplayName("(7) method being tested = decideResultAndUpdateGameResult()")
    void checkThatTheDecideResultAndUpdateGameResultMethodCorrectlyDeterminesPlayerWinsWhenScoreIsLessThan22AndDealerScoresGreaterThan21AndAddsAWinToGameResult() {
        // Arrange
        testPlayer.setHandScore(14);
        testDealer.setHandScore(26);

        // Reassign the "standard" output stream
        System.setOut(printStream);

        // Act and Assert
        try {
            testGame.decideResultAndUpdateGameResult();
            String capturedOutput = outputStream.toString().trim();
            String[] outputsByLine = capturedOutput.split("\n");
            // check result is correct
            Assertions.assertEquals("Dealer Bust, Test player wins with 14.", outputsByLine[0]);
            // check gameResult is updated correctly
            Assertions.assertEquals("Test player's win record:-Win", testPlayer.getGameResults().toString());
        } finally {
            // Reassign the "standard" output stream so printing to console resumes after test completed
            System.setOut(originalSystemOut);
        }
    }

    @Test
    @DisplayName("(8) method being tested = decideResultAndUpdateGameResult()")
    void checkThatTheDecideResultAndUpdateGameResultMethodCorrectlyDeterminesPlayerWinsWhenPlayerAndDealerScoreTheSameAndLessThan22AndAddsADrawToGameResult() {
        // Arrange
        testPlayer.setHandScore(18);
        testDealer.setHandScore(18);

        // Reassign the "standard" output stream
        System.setOut(printStream);

        // Act and Assert
        try {
            testGame.decideResultAndUpdateGameResult();
            String capturedOutput = outputStream.toString().trim();
            String[] outputsByLine = capturedOutput.split("\n");
            // check result is correct
            Assertions.assertEquals("Push! Both Test player and dealer scored 18.", outputsByLine[0]);
            // check gameResult is updated correctly
            Assertions.assertEquals("Test player's win record:-Draw", testPlayer.getGameResults().toString());
        } finally {
            // Reassign the "standard" output stream so printing to console resumes after test completed
            System.setOut(originalSystemOut);
        }
    }

    @Test
    @DisplayName("(9) method being tested = decideResultAndUpdateGameResult()")
    void checkThatTheDecideResultAndUpdateGameResultMethodCorrectlyDeterminesPlayerWinsWhenPlayerScoresLessThan22AndMoreThanDealerScoresAndAddsAWinToGameResult() {
        // Arrange
        testPlayer.setHandScore(19);

        testDealer.setHandScore(17);

        // Reassign the "standard" output stream
        System.setOut(printStream);

        // Act and Assert
        try {
            testGame.decideResultAndUpdateGameResult();
            String capturedOutput = outputStream.toString().trim();
            String[] outputsByLine = capturedOutput.split("\n");
            // check result is correct
            Assertions.assertEquals("Test player wins 19 to 17.", outputsByLine[0]);
            // check gameResult is updated correctly
            Assertions.assertEquals("Test player's win record:-Win", testPlayer.getGameResults().toString());
        } finally {
            // Reassign the "standard" output stream so printing to console resumes after test completed
            System.setOut(originalSystemOut);
        }
    }

    @Test
    @DisplayName("(10) method being tested = decideResultAndUpdateGameResult()")
    void checkThatTheDecideResultAndUpdateGameResultMethodCorrectlyDeterminesDealerWinsWhenDealerScoresLessThan22AndMoreThanPlayerScoresAndAddsALossToGameResult() {
        // Arrange
        testPlayer.setHandScore(17);
        testDealer.setHandScore(19);

        // Reassign the "standard" output stream
        System.setOut(printStream);

        // Act and Assert
        try {
            testGame.decideResultAndUpdateGameResult();
            String capturedOutput = outputStream.toString().trim();
            String[] outputsByLine = capturedOutput.split("\n");
            // check result is correct
            Assertions.assertEquals("Dealer wins 19 to 17.", outputsByLine[0]);
            // check gameResult is updated correctly
            Assertions.assertEquals("Test player's win record:-Loss", testPlayer.getGameResults().toString());
        } finally {
            // Reassign the "standard" output stream so printing to console resumes after test completed
            System.setOut(originalSystemOut);
        }
    }

    @Test
    @DisplayName("(11) method being tested = decideResultAndUpdateGameResult()")
    void checkThatTheDecideResultAndUpdateGameResultMethodCorrectlyDeterminesPlayerWinsWhenTheyHaveNaturalBlackjackAndDealerScores21WithMoreThan2CardsAndAddsAWinToGameResult() {
        // Arrange
        testPlayer.setHand(List.of(
                new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.TEN_OF_, CardSuit.CLUBS)
        ));
        testPlayer.setHandScore(21);

        testDealer.setHand(List.of(
                new Card(CardRank.FIVE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.SIX_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.TEN_OF_, CardSuit.CLUBS)
        ));
        testDealer.setHandScore(21);

        // Reassign the "standard" output stream
        System.setOut(printStream);

        // Act and Assert
        try {
            testGame.decideResultAndUpdateGameResult();
            String capturedOutput = outputStream.toString().trim();
            String[] outputsByLine = capturedOutput.split("\n");
            // check result is correct
            Assertions.assertEquals("Test player wins with natural blackjack as dealer does not have a natural blackjack.", outputsByLine[0]);
            // check gameResult is updated correctly
            Assertions.assertEquals("Test player's win record:-Win", testPlayer.getGameResults().toString());

        } finally {
            // Reassign the "standard" output stream so printing to console resumes after test completed
            System.setOut(originalSystemOut);
        }
    }

    @Test
    @DisplayName("(12) method being tested = decideResultAndUpdateGameResult()")
    void checkThatTheDecideResultAndUpdateGameResultMethodCorrectlyDeterminesADrawWhenDealerScores21WithMoreThan2CardsAndPlayerScores21With2CardsAfterSplittingHandAndAddsADrawToGameResult() {
        // Arrange

        // Simulate player splitting Aces so splitHands is not empty so that when checking for natural blackjack, it returns false
        testPlayer.setSplitHands(List.of(
                List.of(new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                        new Card(CardRank.TEN_OF_, CardSuit.CLUBS)),
                List.of(new Card(CardRank.EIGHT_OF_, CardSuit.DIAMONDS),
                        new Card(CardRank.ACE_OF_, CardSuit.CLUBS))
        ));
        testPlayer.setHand(List.of(
                new Card(CardRank.ACE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.TEN_OF_, CardSuit.CLUBS)));
        testPlayer.setHandScore(21);

        testDealer.setHand(List.of(
                new Card(CardRank.FIVE_OF_, CardSuit.DIAMONDS),
                new Card(CardRank.FIVE_OF_, CardSuit.HEARTS),
                new Card(CardRank.ACE_OF_, CardSuit.SPADES)
        ));
        testDealer.setHandScore(21);

        // Reassign the "standard" output stream
        System.setOut(printStream);

        // Act and Assert
        try {
            testGame.decideResultAndUpdateGameResult();
            String capturedOutput = outputStream.toString().trim();
            String[] outputsByLine = capturedOutput.split("\n");
            // check result is correct
            Assertions.assertEquals("Push! Both Test player and dealer scored 21.", outputsByLine[0]);
            // check gameResult is updated correctly
            Assertions.assertEquals("Test player's win record:-Draw", testPlayer.getGameResults().toString());

        } finally {
            // Reassign the "standard" output stream so printing to console resumes after test completed
            System.setOut(originalSystemOut);

        }
    }

}