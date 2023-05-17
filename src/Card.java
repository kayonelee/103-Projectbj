import java.util.Random;

public class Card {

    private Suits suit;
    private Values value;

    //CREATE ARRAYS FOR VALUES AND SUITS-NEED THIS IN ORDER TO GENERATE RANDOM CARDS
    private Values[] values = Values.values();
    private Random randomValues = new Random();
    private Suits[] suits = Suits.values();
    private Random randomSuits = new Random();

    //CONSTRUCTOR
    public Card(Suits suit, Values value) {
        this.value = value;
        this.suit = suit;
    }

    //RANDOM CARD GENERATED
    public Card(Card card) {
        this.suit = card.getRandomSuit();
        this.value = card.getRandomValue();
    }

    public Suits getSuit() {
        return this.suit;
    }

    public Values getValue() {
        return this.value;
    }

    public Values getRandomValue() {
        return values[randomValues.nextInt(values.length)];
    }

    public Suits getRandomSuit() {
        return suits[randomSuits.nextInt(values.length)];
    }

    //RETURN SUIT AND VALUE
    public String toString() {
        return this.suit.toString() + "-" + this.value.toString();
    }
}