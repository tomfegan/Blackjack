package com.fegan.participants;

import com.fegan.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player extends GameParticipant {
    final private String name;
    private StringBuilder winRecord;
    private List<List<Card>> splitHands;
    public Player() {
        this("John Doe");
    }
    public Player(String name) {
        super();
        this.name = name;
        winRecord = this.name.endsWith("s") ? new StringBuilder(this.name + "' win record: ") : new StringBuilder(this.name + "'s win record: ");
        splitHands = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void splitStartingHand() {
        System.out.println("You want to split the deck - you have 2 hands");
//        splitHands.getFirst().add(hand.getFirst());
//        splitHands.getFirst().add(hand.getLast());
//        System.out.println(splitHands);
    } // implementation required
    public void calculateCurrentHandScoreForPlayer() {
        int tempHandScore = 0;
        Scanner sc = new Scanner(System.in);
        for (Card card : hand) {
            if (card.getRank().equals(com.fegan.CardRank.ACE_OF_)) {
                printCardsInCurrentHand();
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
//        System.out.println(this.name.endsWith("s") ?
//                this.name + "' hand score is currently: " + handScore :
//                this.name + "'s hand score is currently: " + handScore);
    }
    public boolean doesPlayerWantNextCard(Scanner scanner) {
        System.out.println("Enter H if you want dealer to give you another card (hit) or any key to stand (not take another card)");
        if (scanner.next().substring(0, 1).toLowerCase().trim().equals("h")) {
            handScore = 0;
            System.out.printf("%s asked for another card%n", name);
            return true;
        } else {
            System.out.printf("%s did not want another card%n", name);
            return false;
        }
    }
    public StringBuilder getWinRecord() {
        return winRecord;
    }
    public void setWinRecord(StringBuilder winRecord) {
        this.winRecord = winRecord;
    }
    @Override
    public void printCardsInCurrentHand() {
        StringBuilder displayHandAsCommaSeparatedList = new StringBuilder("Current hand for " + name + ": face up cards are ");
        for (int i = 0; i < hand.size(); i++) {
            if (i < hand.size() - 2) {
                displayHandAsCommaSeparatedList.append(hand.get(i)).append(", ");
            } else if (i < hand.size() - 1) {
                displayHandAsCommaSeparatedList.append(hand.get(i)).append(" and ");
            } else {
                displayHandAsCommaSeparatedList.append(hand.get(i));
            }
        }
        System.out.println(displayHandAsCommaSeparatedList);
    }
}
