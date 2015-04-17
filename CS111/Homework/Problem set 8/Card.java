/*
 * Card.java
 * 
 * A blueprint class to represent an individual playing card.
 * 
 * starter code by course staff, CS 111, Boston University
 * 
 * completed by: <your name>, <your username>
 *         date:
 * 
 */

public class Card {
    // constants for the ranks of non-numeric cards
    public static final int ACE = 1;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    
    // Arrays of strings for the rank names and abbreviations.
    // The name of the rank r is given by RANK_NAMES[r].
    // The abbreviation of the rank r is given by RANK_ABBREVS[r].
    // Note that there is a null value in position 0 of
    // each array, because no card has a rank of 0.
    private static final String[] RANK_NAMES = {
      null, "Ace", "2", "3", "4", "5", "6", 
      "7", "8", "9", "10", "Jack", "Queen", "King"
    };
    private static final String[] RANK_ABBREVS = {
      null, "A", "2", "3", "4", "5", "6",
      "7", "8", "9", "10", "J", "Q", "K"
    };
    
    // constants for the suits
    public static final int DIAMONDS = 0;
    public static final int HEARTS = 1;
    public static final int CLUBS = 2;
    public static final int SPADES = 3;
    
    // Arrays of strings for the suit names and abbreviations.
    // The name of the suit s is given by SUIT_NAMES[s].
    // The abbreviation of the suit s is given by SUIT_ABBREVS[s].
    private static final String[] SUIT_NAMES = {
      "Diamonds", "Hearts", "Clubs", "Spades"
    };
    private static final String[] SUIT_ABBREVS = {
      "D", "H", "C", "S"
    };

    /***** part b: getSuitNum *****/
    private static int getSuitNum(String suit) {
        // The return statement below is included so the starter code will compile.
        // Replace it with your implementation of the method.
        for(int i = 0; i < SUIT_NAMES.length; i++) {
            if(SUIT_NAMES[i].equalsIgnoreCase(suit)) {
                return i;
            }
        }
        return -1;
    }

    /***** Implement parts c-g below. *****/
        
    private int rank;
    private int suitNumber;
    private int value;
    
    public void setValue(int newValue) { //Mutator
        if(newValue < 0) {
            throw new IllegalArgumentException("Only positive values are allowed");
        }
        this.value = newValue;
    }
    
    public int getRank() { //Accessor
        return this.rank;
    }
    
    public int getSuitNum() {
        return this.suitNumber;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public String getSuit() {
        return SUIT_NAMES[this.suitNumber];
    }
    
    public String getAbbrev() {
        String abbrev = RANK_ABBREVS[this.rank] + SUIT_ABBREVS[this.suitNumber];
        return abbrev;
    }
    
    public Boolean isAce() {
        if(RANK_NAMES[this.rank].equals("Ace")) {
            return true;
        } else {
            return false;
        }
    }
    
    public Boolean isFaceCard() {
        if(RANK_NAMES[this.rank].equals("Jack") || RANK_NAMES[this.rank].equals("Queen") || RANK_NAMES[this.rank].equals("King")) {
            return true;
        } else {
            return false;
        }
    }
    
    public Card(int rank, int suitNumber, int value) {
        this.rank = rank;
        this.suitNumber = suitNumber;
        setValue(value);
    }
    
    public Card(int rank, int suitNumber) {
        this.rank = rank;
        if(suitNumber > 3) {
            throw new IllegalArgumentException("Only values up to 3 are allowed");
        }
        this.suitNumber = suitNumber;
        if(rank > 10) {
            rank = 10;
        }
        setValue(rank);
    }
    
    public Card(int rank, String suit) {
        this.rank = rank;
        this.suitNumber = getSuitNum(suit);
        if(rank > 10) {
            rank = 10;
        }
        setValue(rank);
    }
    
    public String toString() {
        return RANK_NAMES[rank] + " of " + SUIT_NAMES[suitNumber];
    }
    
    public Boolean sameSuitAs(Card card) {
        if(card == null) {
            return false;
        }
        if(card.suitNumber == this.suitNumber) {
            return true;
        } else {
            return false;
        }
    }
    
    public Boolean equals(Card card) {
        if(card == null) {
            return false;
        }
        if((card.rank == this.rank) && sameSuitAs(card) == true) {
            return true;
        } else {
            return false;
        }
    }
}