public class Client2 {
    public static void main(String[] args) {
        Card c1 = new Card(7, Card.SPADES);        
        Card c2 = new Card(Card.JACK, Card.SPADES);
        Card c3 = new Card(Card.ACE, Card.CLUBS);
        Card c4 = new Card(Card.ACE, Card.DIAMONDS, 11);
        Card c5 = new Card(Card.JACK, Card.SPADES, 11);

        // test the toString() method. 
        // Note that we *don't* need to call it explicitly.
        // It gets called automatically as part of the concatenation.
        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        System.out.println("c3 = " + c3);
        System.out.println("c4 = " + c4);
        System.out.println("c5 = " + c5);
        System.out.println();
        
        // test the sameSuitAs() method
        System.out.println("c1.sameSuitAs(c1) = " + c1.sameSuitAs(c1));
        System.out.println("c1.sameSuitAs(c2) = " + c1.sameSuitAs(c2));
        System.out.println("c3.sameSuitAs(c4) = " + c3.sameSuitAs(c4));
        System.out.println("c2.sameSuitAs(c5) = " + c2.sameSuitAs(c5));
        System.out.println("c2.sameSuitAs(null) = " + c2.sameSuitAs(null));
        System.out.println();
        
        // test the sameSuitAs() method
        System.out.println("c1.equals(c1) = " + c1.equals(c1));
        System.out.println("c1.equals(c2) = " + c1.equals(c2));
        System.out.println("c3.equals(c4) = " + c3.equals(c4));
        System.out.println("c2.equals(c5) = " + c2.equals(c5));
        System.out.println("c2.equals(null) = " + c2.equals(null));
    }
}