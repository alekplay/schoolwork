/*
 * Deck.java
 *
 * Computer Science 111, Boston University
 * 
 * A blueprint class for objects that represent a deck of BUno cards.
 *
 * YOU SHOULD NOT MODIFY THIS FILE.
 */

import java.util.*;

public class Deck {
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 9;
    public static final String[] COLORS = {"blue", "green", "red", "yellow"};
    public static final int NUM_CARDS = COLORS.length * (MAX_VALUE - MIN_VALUE + 1);
    
    /* fields for the deck */
    private Card[] cards;
    private int numCardsLeft;   // number of cards not yet drawn
    private Random rand;        // random-number generator used when shuffling     
    
    /*
     * Deck constructor - takes a numeric seed for the random-number
     * generator. If the seed is -1, it is not used, which means
     * you will get a different ordering each time the program is run.
     */
    public Deck(int seed) {
        cards = new Card[NUM_CARDS];
        int count = 0;
        
        for (int colorIndex = 0; colorIndex < COLORS.length; colorIndex++) {
            for (int val = MIN_VALUE; val <= MAX_VALUE; val++) {
                cards[count] = new Card(COLORS[colorIndex], val);
                count++;
            }
        }

        numCardsLeft = NUM_CARDS;
        
        if (seed == -1) {
            rand = new Random();
        } else {
            rand = new Random(seed);
        }
    }

    /*
     * Deck constructor that takes no parameters. This is useful when
     * you don't want to specify a seed for the random-number generator.
     * It calls the other constructor, passing it the special seed of -1.
     */
    public Deck() {
        this(-1);
    }
    
    /*
     * reset - restores the deck to a full set of cards. 
     * This method should typically be followed by a call to shuffle(), 
     * or else the cards will be re-dealt in the same order as they were 
     * the first time this deck was used.
     */
    public void reset() {
        numCardsLeft = NUM_CARDS;
    }

    /*
     * shuffle - rearranges the cards in the deck
     */
    public void shuffle() {
        // Swap each card in the deck with a randomly chosen other card.
        for (int i = 0; i < NUM_CARDS; i++) {
            int swapWith = Math.abs(rand.nextInt()) % NUM_CARDS;
            Card temp = cards[i];
            cards[i] = cards[swapWith];
            cards[swapWith] = temp;
        }
    }

    /*
     * drawCard - returns a single card from the deck. 
     * Note that the cards aren't actually removed from the cards array. 
     * Rather, we use the numCardsLeft field to determine which 
     * element of the array should be returned. This means that we
     * end up going from right to left through the array -- returning
     * the last card, then the second-to-last, etc.
     */
    public Card drawCard() {
        if (numCardsLeft == 0) {
            reset();
            shuffle();
        }
        
        numCardsLeft--;
        return cards[numCardsLeft];
    }
}