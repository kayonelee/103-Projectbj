import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Welcome to Kayone's Blackjack Table!");

        // CREATING THE PLAYING DECK
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        //DEALER & PLAYER-CREATING HANDS
        Deck playerHand = new Deck();
        Deck dealerHand = new Deck();

        double playerMoney = 100.00;

        // GAME LOOPS

        while(playerMoney > 0){
            //TAKE PLAYERS BET
            System.out.println("You have $" + playerMoney + ", enter your bet amount:");
            double playerBet = userInput.nextDouble();
            //IF PLAYER BETS MORE THAN WHAT THEY HAVE
            if(playerBet > playerMoney) {
                System.out.println("You cannot bet more than you have!");
                break;
            }

            boolean endRound = false;

            // STARTS DEALING-PLAYER GETS TWO CARDS
            playerHand.draw(playingDeck);
            playerHand.draw(playingDeck);

            // STARTS DEALING-DEALER GETS TWO CARDS (ONE WILL BE HIDDEN)
            dealerHand.draw(playingDeck);
            dealerHand.draw(playingDeck);

            while(true){
                System.out.println("Your hand: ");
                System.out.println(playerHand.toString());
                System.out.println("Your hand is valued at: " + playerHand.cardValue());

                //PRINT DISPLAY DEALER HAND (SHOW ONE CARD IN THE BEGINNING ONLY)
                System.out.println();
                System.out.println("Dealer Hand: " + dealerHand.getCard(0).toString() + " and [Hidden Card]");

                //PRINT WHAT DOES THE PLAYER WANT TO DO?
                System.out.println();
                System.out.println("Enter (1) to Hit or (2) to Stand:");
                int response = userInput.nextInt(); //GETTING THE RESPONSE

                //IF THEY HIT (1)
                if(response ==1) {
                    playerHand.draw(playingDeck); // IF HIT THEN DRAW CARD
                    //PRINT WHAT CARD PLAYER JUST DREW (zero base -1 index)
                    System.out.println("You draw a: " + playerHand.getCard(playerHand.deckSize()-1).toString());
                    //IF OVER 21 THEN BUST
                    if(playerHand.cardValue() > 21) {
                        System.out.println("BUST! Currently valued at: " + playerHand.cardValue());
                        //SUBTRACTING BET FROM MONEY
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }

                if(response==2) {
                    break;
                }
            }

            // REVEAL DEALER CARDS
            System.out.println("Dealer hands: " + dealerHand.toString());
            //SEE IF DEALER HAS MORE POINTS THAN PLAYER-DEALER WINS
            if(dealerHand.cardValue() > playerHand.cardValue() && endRound == false) {
                System.out.println("DEALER WINS!");
                //SUBTRACTING BET FROM MONEY
                playerMoney -= playerBet;
                endRound = true;
            }

            //Dealer draws 16, stand at 17
            while((dealerHand.cardValue() < 17) && endRound == false) {
                dealerHand.draw(playingDeck);
                System.out.println("Dealer draws: " + dealerHand.getCard(dealerHand.deckSize()-1).toString());
            }
            // DISPLAY TOTAL VALUE FOR DEALER
            System.out.println("Dealer's hand is valued at " + dealerHand.cardValue());
            //DETERMINE IF DEALER BUSTED
            if(dealerHand.cardValue() > 21 && endRound == false) {
                System.out.println();
                System.out.println("Dealer BUSTS! You WIN");
                //ADDING BET TO MONEY
                playerMoney += playerBet;
                endRound = true;
            }

            //TIE/PUSH---------
            if((playerHand.cardValue() == dealerHand.cardValue() && endRound == false)) {
                System.out.println("PUSH!!");
                endRound = true;
            }

            if(playerHand.cardValue() > dealerHand.cardValue() && endRound == false) {
                System.out.println("YOU WIN THIS ROUND!");
                //ADDING BET TO MONEY
                playerMoney += playerBet;
                endRound = true;
            } else if (endRound == false){
                System.out.println("YOU LOST THIS ROUND!");
                //SUBTRACTING BET TO MONEY
                playerMoney -= playerBet;
                endRound = true;
            }

            playerHand.moveAllToDeck(playingDeck);
            dealerHand.moveAllToDeck(playingDeck);
            System.out.println();
            System.out.println("End of hand.");
        }
        System.out.println("GAME OVER! You have no more funds!");
        userInput.close();
    }
}