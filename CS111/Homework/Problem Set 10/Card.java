/*
 * card.java
 * Aleksander Skjoelsvik - alsk@bu.edu
 */

public class Card {
    private String color;
    private int value;
    
    public Card(String color, int value) {
        if((value < 0) || (value > 9)) {
            throw new IllegalArgumentException("The value must be between 0 and 9");
        }
        
        this.color = color;
        this.value = value;
    }
    
    public String getColor() {
        return this.color;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public Boolean matches(Card theOther) {
        if((this.getColor() == theOther.getColor()) || (this.getColor() == theOther.getColor())) {
            return true;
        } else {
            return false;
        }
    }
    
    public String toString() {
        return this.getColor() + " " +  this.getValue();
    }
}