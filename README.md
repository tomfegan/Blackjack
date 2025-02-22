<<Rules of this Blackjack game>>

--EXTENDING THE APPLICATION--
Should I add a cutDeck(int index) method to allow the player to determine where the random deck should be cut?

--HANDLING ACES DYNAMICALLY--
-> aces can be counted as both 1 and 11
-> the player decides whether an ace will count as 1 or 11 – this will determine the ‘soft total’ for their hand
-> the dealer will treat aces as 11 unless doing so makes their soft total > 21 (ace will then be assigned a value of 1)

--CARD DECK--
-> I have limited the number of card decks to 4 and guaranteed there will be at least 1 in the Deck constructor

--STARTING HANDS--
-> all cards are dealt face up to the player - the dealer does not base decisions on whether to hit or stand on the
player's score (see predetermined rules for the dealer)
-> the dealer’s first card is dealt face up (up card) and the second is dealt face down

--HIT OR STAND--
-> A hand that does not contain an Ace is called a ‘hard hand’ because the hand has only one possible 
value e.g., a 10 and 7 is a ‘hard 17’
-> A hand that contains an Ace is called a ‘soft hand’ because it has two possible values (called a ‘soft total’) 
e.g., an Ace and 6 hand is a ‘soft 17’

*Player:-
-> decides whether to hit or stand via the console in this application
-> can stand at any score and does not have to hit till hand score >=17
-> can change the value of any aces in their hand to give them the closest score to 21

*Dealer:-
Breakdown of predetermined rules for the dealer for this application (based on Soft 17 rules):
(1) The dealer will treat aces as 11 (unless they go "bust")
(2) The dealer must keep hitting (draw card/s) until their hand (soft and hard) total is 17 or higher
(3) If the dealer's hand total is between 17 and 21 (inclusive), they must stand (not draw any more cards)
(4) If the dealer's hand exceeds 21, they go "bust" and the player wins - if the dealer has an ace, they can stop 
going bust by assigning a value of 1 and then hit.
(5) The dealer’s actions are independent of the player's decisions; they strictly adhere to these predetermined rules.

--SPLITTING--
The player can split their starting hand if both cards have the same rank (numerical value) 
-> the player can only draw one more card if splitting Aces
-> The player must play their left-most hand first by hitting or standing one or more times – then the 
card to the right is played likewise. The outcome of each hand is treated separately.
-> the dealer cannot split their starting hand

If you split Aces and a 10/picture card is drawn, the total is 21, not Blackjack.

--WINNING HANDS--
If the player goes bust, they lose immediately regardless of whether the dealer goes bust or not.

Player loses if their score is lower than the dealer’s

If the dealer has a natural blackjack, they immediately beat the player if they do not have natural blackjacks.

In the event both player and dealer have natural blackjacks, it is a tie (known as a ‘push’)

A 'natural' blackjack (Ace && 10 || Jack || Queen || King) beats unnatural 21 (21 made from more than 2 cards)
-> if the player has a natural blackjack, the dealer loses unless they were also dealt a natural blackjack
-> if the dealer has a natural blackjack, the player loses unless they were also dealt a natural blackjack
-> In the event both player and dealer have naturals, it is a tie (known as a ‘push’)

If you split Aces and a 10/picture card is drawn, the total is 21, not Blackjack.

--GAME EXCLUSIONS--
Wagers are excluded from the game--a winRecord shows the player's success across multiple games
-> the player cannot choose to double down or surrender in this application
-> the player cannot take insurance (a bet that the dealer has a natural blackjack) if the dealer's up card is an Ace
-> player cannot early or late surrender
-> If the player splits a pair of aces and receives another ace as the next card, a table running the
RSA (resplitting aces) rule allows them to split yet another pair of aces, into a third or even fourth hand
-> ‘H17’ game 
-> 5-card Charlie rule












