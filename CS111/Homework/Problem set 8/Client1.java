public class Client1 {
    public static void processCard(Card c) {
        int rank = c.getRank();
        System.out.println("   getRank: " + rank);
        int suitNum = c.getSuitNum();
        System.out.println("getSuitNum: " + suitNum);
        int value = c.getValue();
        System.out.println("  getValue: " + value);
        String suit = c.getSuit();
        System.out.println("   getSuit: " + suit);
        String abbrev = c.getAbbrev();
        System.out.println(" getAbbrev: " + abbrev);
        boolean ace = c.isAce();
        System.out.println("     isAce: " + ace);
        boolean faceCard = c.isFaceCard();
        System.out.println("isFaceCard: " + faceCard);
        System.out.println();
    }
    
    public static void main(String[] args) {  
        System.out.println("card c1 (Jack of Spades with value of 15):");
        Card c1 = new Card(11
                               , 3, 15);     // first constructor
        processCard(c1);
        
        System.out.println("card c2 (5 of Diamonds with default value):");
        Card c2 = new Card(5, 0);          // second constructor
        processCard(c2);
        
        System.out.println("card c3 (Queen of Hearts with default value):");
        Card c3 = new Card(12, "hearts");  // third constructor
        processCard(c3);
        
        System.out.println("card c4 (Ace of Clubs with default value):");
        Card c4 = new Card(1, "CLUBS");    // third constructor
        processCard(c4);
        
        System.out.println("changing the value of c4 to 11...");
        c4.setValue(11);
        System.out.println("c4's value is now " + c4.getValue());
        System.out.println();
        
        // Try to create an invalid Card. You may want to add additional tests
        // for invalid values.
        System.out.println("Trying to create a Card with a suit of 5...");
        try {
            Card c5 = new Card(7, 5);
        } catch(IllegalArgumentException e) {
            System.out.println("Caught an IllegalArgumentException...");
        }
        System.out.println();
        
        // Try to make an invalid change.
        System.out.println("Trying to give c3 a negative value...");
        try {
            c3.setValue(-10);
        } catch(IllegalArgumentException e) {
            System.out.println("Caught an IllegalArgumentException...");
            System.out.println("c3's value should be unchanged: " + c3.getValue());
        }
    }
}