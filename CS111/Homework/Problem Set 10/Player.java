/*
 * Player.java
 * Aleksander Skjoelsvik - alsk@bu.edu
 */

import java.util.*;

public class Player {
    private String name;
    private Card[] cards;
    private int numCards;
    
    public Player(String name) {
        this.name = name;
        this.cards = new Card[BUno.MAX_CARDS];
        this.numCards = 0;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getNumCards() {
        return numCards;
    }
    
    public String toString() {
        return getName();
    }
    
    public Card addCardToHand(Card card) {
        if(getNumCards() >= BUno.MAX_CARDS) {
            throw new IllegalStateException("Cannot hold more than 10 cards");
        }
        
        this.cards[this.getNumCards()] = card;
        this.numCards++;
        return card;
    }
    
    public Card getCardFromHand(int index) {
        if(index > this.getNumCards() - 1) {
            throw new IndexOutOfBoundsException("The player don't hold that many cards");
        }
        
        return cards[index];
    }
    
    public void printHand() {
        System.out.println(this.getName() + "'s hand:");
        for(int i=0; i<this.getNumCards(); i++) {
            System.out.println(i + ": " + this.toString());
        }
    }
    
    public int getHandValue() {
        int value = 0;
        for(int i = 0; i < getNumCards(); i++) {
            value += cards[i].getValue();
        }
        
        if(this.getNumCards() >= BUno.MAX_CARDS) {
            value += BUno.MAX_CARDS_PENALTY;
        }
        
        return value;
    }
    
    public Card removeCardFromHand(int index) {
        if(index > getNumCards() - 1) {
            throw new IndexOutOfBoundsException("The player don't hold that many cards");
        }
        
        Card removed = cards[index];
        
        for(int i = index; i < getNumCards() - 1; i++) {
            cards[i] = cards[i++];
        }
        
        cards[getNumCards() - 1] = null;
        
        this.numCards--;
        
        return removed;
    }
    
    public int getPlay(Scanner console, Card onTop) {
        int nextMove;
        while(true) {
            System.out.println("What do you want to do next? Enter -1 to draw a card, or the index of the card in your hand you want to get rid of");
            nextMove = console.nextInt();
            if((nextMove > -1) && (nextMove <= this.getNumCards())) {
                break;
            }
        }
        
        return nextMove;
        
    }
}