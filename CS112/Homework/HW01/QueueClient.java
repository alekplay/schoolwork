/* File: QueueClient.java
 * Date: 1/28/14
 * Author:  Wayne Snyder (snyder@bu.edu)
 * Class: CS 112, Fall 2014
 * Purpose: Example of integer queue represented by a ring buffer
 */

public class QueueClient {
    public static void main(String [] args) {
        Dequeable Q = new Deque();
        
        /*System.out.println("\nShould print out:\n3  4  5  6  7  8  9  10  11  12:"); 
        
        Q.enqueue(1); 
        Q.enqueue(2);
        Q.enqueue(3);
        
        Q.dequeue();
        Q.dequeue(); 
        
        Q.enqueue(4); 
        Q.enqueue(5);  
        Q.enqueue(6);
        Q.enqueue(7);
        Q.enqueue(8);  
        Q.enqueue(9);
        Q.enqueue(10); 
        Q.enqueue(11); 
        Q.enqueue(12); 
        
        while( !Q.isEmpty() ) {
            System.out.print(Q.dequeue() + "  "); 
        }
        System.out.println();*/
        
        Q.enqueueRear(1);
        Q.enqueueRear(2);
        Q.enqueueRear(3);
        Q.enqueueRear(4);
        Q.enqueueRear(5);
        Q.enqueueRear(6);
        Q.enqueueRear(7);
        Q.enqueueRear(8);
        Q.enqueueRear(9);
        Q.enqueueRear(9);
        Q.enqueueRear(9);
        
        

        while(!Q.isEmpty()) {
            System.out.print(Q.dequeueRear() + "  "); 
        }
        
       
        
        
    } 
}
