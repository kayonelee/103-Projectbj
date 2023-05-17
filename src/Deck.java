import java.util.ArrayList;
import java.util.Random;

public class Deck {
//ARRAY LIST OF CARDS-INSTANCE VARS
    private ArrayList<Card> deck;

//CONSTRUCTOR-NO PARAMETERS
    public Deck() {
        this.deck = new ArrayList<Card>();
    }
//METHOD CREATING A DECK OF CARDS
    public void createFullDeck() {
        // GENERATE CARDS USING FOR EACH LOOP
        for(Suits cardSuit : Suits.values()) { // FOUR SUITS
            for(Values cardValues : Values.values()) { // THIRTEEN VALUES ( 13X4=52 CARDS)
                // ADD A NEW CARD TO THE MIX
                this.deck.add(new Card(cardSuit, cardValues));
            }
        }
    }

//METHOD FOR SHUFFLE-USING A TEMP DECK TO HOLD SHUFFLE CARDS
    public void shuffle(){
        ArrayList<Card> tmpDeck = new ArrayList<>();
        // USE RANDOM
        Random random = new Random();
        int randomCardIndex = 0;
        int originalSize = this.deck.size();
        for(int i = 0; i < originalSize; i++) {
            // GENERATE RANDOM INDEX
            randomCardIndex = random.nextInt((this.deck.size()-1 - 0) + 1) + 0;
            tmpDeck.add(this.deck.get(randomCardIndex));
            // REMOVE FROM ORIGINAL DECK
            this.deck.remove(randomCardIndex);
        }
        this.deck = tmpDeck;
    }

    public Card getCard(int i){
        return this.deck.get(i);
    }

    public void removeCard(int i){
        this.deck.remove(i);
    }

    public void addCard(Card addCard) {
        this.deck.add(addCard);
    }

    public Card takeCard() {
        Card cardToTake = new Card(deck.get(0));
        deck.remove(0);
        return cardToTake;
    }

    //METHOD RETURNING AMOUNT OF CARDS IN THE DECK
    public int deckSize() {
        return this.deck.size();
    }

    // DRAWS FROM THE DECK
    public void draw(Deck comingFrom) {
        this.deck.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    // RETURN TOTAL VALUE OF CARDS IN DECK
    public int cardValue() {
        int totalValue = 0;
        int aces = 0;

        for(Card aCard : this.deck) {
            switch(aCard.getValue()) {
                case TWO: totalValue += 2;
                    break;
                case THREE: totalValue += 3;
                    break;
                case FOUR: totalValue += 4;
                    break;
                case FIVE: totalValue += 5;
                    break;
                case SIX: totalValue += 6;
                    break;
                case SEVEN: totalValue += 7;
                    break;
                case EIGHT: totalValue += 8;
                    break;
                case NINE: totalValue += 9;
                    break;
                case TEN: totalValue += 10;
                    break;
                case JACK: totalValue += 10;
                    break;
                case QUEEN: totalValue += 10;
                    break;
                case KING: totalValue += 10;
                    break;
                case ACE: aces += 1;
                    break;
            }
        }
        for(int i =0; i < aces; i++) {
            if(totalValue > 10){
                totalValue += 1;
            }
            else {
                totalValue += 11;
            }
        }

        return totalValue;
    }

    // MOVE CARDS BACK INTO DECK TO CONTINUE PLAYING
    public void moveAllToDeck(Deck moveTo) {
        int deckSize = this.deck.size();

        // PUT CARDS INTO DECK
        for(int i = 0; i < deckSize; i++) {
            moveTo.addCard(this.getCard(i));
        }
        for(int i = 0; i < deckSize; i++) {
            this.removeCard(0);
        }
    }

    public String toString() {
        String cardListOutput = "";
        for(Card aCard : this.deck) {
            cardListOutput += "\n " + aCard.toString();
        }
        return cardListOutput;
    }
}
