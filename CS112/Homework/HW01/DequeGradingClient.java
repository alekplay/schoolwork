/* File: DequeGradingClient.java
 * Date: 1/28/14
 * Author:  Wayne Snyder (snyder@bu.edu)
 * Class: CS 112, Spring 2014
 * Purpose: Grading client for Problem B.3 in HW 01
 */

public class DequeGradingClient { 
   
   public static void main(String[] args) {
      
      Dequeable D = new Deque(); 
      
      // First test that each end works properly:
      
      
      D.enqueueFront(1); 
      D.enqueueFront(2);
      D.enqueueFront(3); 
      D.enqueueFront(4); 
      System.out.println("\nFirst test size and isEmpty\nShould print out:\n4  false:"); 
      System.out.println(D.size() + "  " + D.isEmpty()); 
      
      System.out.println("\nNow test enqueueFront and dequeueFront\nShould print out:\n4  3  2  1");            
      
      while( !D.isEmpty() ) {
         System.out.print( D.dequeueFront() + "  "); 
      }
      System.out.println(); 
      
      System.out.println("\nNow test size and isEmpty again\nShould print out:\n0  true"); 
      System.out.println(D.size() + "  " + D.isEmpty()); 
      
      System.out.println("\nNow test enqueueRear and dequeueRear\nShould print out:\n14  13  12  11");      
      D.enqueueRear(11); 
      D.enqueueRear(12);
      D.enqueueRear(13);
      D.enqueueRear(14);
      
      while( !D.isEmpty() ) {
         System.out.print( D.dequeueRear() + "  "); 
      }
      
      System.out.println(); 
      
      for(int i = 0; i < 100; ++i) {
         D.enqueueRear(i);
      }
      System.out.println("\nNow test resizing, inserting 0.. 99\nShould print out:\n100\n" + D.size()); 
      
      System.out.println("\nNow should print out:\n0 99 1 98 2 97 3 96 4 95");     
      for(int i = 0; i < 5; ++i) {
         System.out.print(D.dequeueFront() + " ");
         System.out.print(D.dequeueRear() + " ");
      }
      System.out.println();
      System.out.println("\nNow should print out:\n90\n" + D.size()); 
      for(int i = 0; i < 40; ++i) {
         D.dequeueFront();
         D.dequeueRear();
      }
      System.out.println("\nNow should print out:\n45 54 46 53 47 52 48 51 49 50"); 
      while( !D.isEmpty() ) {
         System.out.print(D.dequeueFront() + " ");
         if(D.isEmpty()) break;
         System.out.print(D.dequeueRear() + " ");
      }
      System.out.println();
      
   } 
   
}
