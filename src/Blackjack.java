import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {

        System.out.println("Welcome to Blackjack!");

        // Create the playing deck
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffleDeck();

        // Create hands for the player and the dealer - hands are created from methods that are made in the deck class
        Deck playerHand = new Deck();
        Deck dealerHand = new Deck();

        // Game loops



    }
}