import java.util.*;

public class ComputerPlayer extends Player {
    
    public ComputerPlayer(String name) {
        super(name);
    }
    
    public void printHand() {
        System.out.println(this.getName() + "'s hand: " + getNumCards());
    }
    
    public int getPlay(Scanner console, Card onTop) {
        int nextMove;
        for(int i=0; i < this.getNumCards(); i++) {
            if(cards[i].matches(onTop)) {
                nextMove = cards[i];
                break;
            } else {
                nextMove = -1;
            }
        }
        
        return nextMove;
    }
}