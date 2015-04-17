/* File: PriorityQueueClient.java
 * Date: 2/1/14
 * Author:  Wayne Snyder (snyder@bu.edu)
 * Class: CS 112, Fall 2014
 * Purpose: Client to test PriorityQueue.java for Lab02; this will be embedded in a unit
 *     test in the class itself. 
 */
 
@SuppressWarnings("unchecked") 

   public class PriorityQueueClient {
   
   public static void main(String [] args) {
      
      PriorityQueue PQ = new PriorityQueue(); 
          
      PQ.enqueue(2);
      PQ.enqueue(3);
      PQ.dequeue();
      PQ.enqueue(6);
      PQ.enqueue(7);      
      PQ.enqueue(9);
      PQ.enqueue(10); 
      PQ.dequeue();  
      
      System.out.println("Test isEmpty and size; should print out:\nfalse 4\n" + PQ.isEmpty() + " " + PQ.size());
      PQ.enqueue(4); 
      PQ.enqueue(5);  
      Integer x = (Integer) PQ.dequeue();
      PQ.enqueue(8); 
      PQ.enqueue(x+3); 
      PQ.enqueue(5);  
      Integer y = (Integer) PQ.dequeue();
      PQ.enqueue(8); 
      PQ.enqueue(x*y); 
      PQ.enqueue(1); 
      
      
      System.out.println("\nTest enqueue and dequeue; should print out:\n108  8  8  7  6  5  5  4  2  1:");       
      while( !PQ.isEmpty() ) {
         System.out.print( PQ.dequeue() + "  "); 
      }
      System.out.println(); 
      
   System.out.println("\nTest isEmpty and size again; should print out:\ntrue 0\n" + PQ.isEmpty() + " " + PQ.size());
       
   } 
   
}