package com.fegan.participants;

import com.fegan.Card;
import java.util.Scanner;

public class Player extends GameParticipant {
    final private String name;
    private String winRecord;
    private int gamesPlayed;
    public Player() {
        this("John Doe");
    }
    public Player(String name) {
        super();
        this.name = name;
        gamesPlayed = 0;
        winRecord = "No games played";
    }
    public void calculateCurrentHandScoreForPlayer() {
        int tempHandScore = 0;
        Scanner sc = new Scanner(System.in);
        for (Card card : hand) {
            if (card.getRank().equals(com.fegan.CardRank.ACE_OF_)) {
                System.out.println(hand);
                while (true) {
                    try {
                        System.out.printf("Do you want to treat the %s as 1 or 11?", card);
                        int aceValue = sc.nextInt();
                        if (aceValue == 1 || aceValue == 11) {
                            tempHandScore += aceValue;
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Please enter 1 or 11 - do not enter letters");
                        sc.nextLine();
                    }
                }
            } else {
                tempHandScore += card.getCardValue();
            }
        }
        handScore = tempHandScore;
        System.out.println("Your score is currently: " + handScore);
    }
    public boolean doesPlayerWantNextCard(Scanner scanner) {
        System.out.println("Do you want the next card? Please press Y if you do or any other key if you don't");
        if (scanner.next().substring(0, 1).toLowerCase().trim().equals("y")) {
            handScore = 0;
            return true;
        } else {
            return false;
        }
    }
}
