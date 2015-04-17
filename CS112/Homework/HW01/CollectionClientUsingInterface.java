/* Program: CollectionClient.java
 * Author: Wayne Snyder
 * Date: 1/17/14
 * Class: CS 112
 * Purpose: Example for first lecture in CS 112: Client, Interface, and ADT for
 *       Collection ADT
 */

public class CollectionClientUsingInterface { 
   public static void main(String [] args) { 
      Collectable C = new Collection();             // verify interface

      System.out.println("Inserting 2, 3, and 13:"); 
      C.insert(2);  
      C.insert(3);  
      C.insert(13); 
    C.list();                           // had to comment out because not in the interface!
      System.out.println("Deleting 2:"); 
      C.delete(2);
    C.list(); 
      System.out.println("Inserting 42 and 2:"); 
      C.insert(42); 
      C.insert(2); 
     C.list(); 
      System.out.println("Deleting 13:");
      C.delete(13);
      C.list(); 
      System.out.println("Deleting 13 again:");
      C.delete(13); 
      C.list(); 
      if(C.member(2)) 
         System.out.println("Member: " + 2); 
      else
         System.out.println("Not a member: " + 2); 
      if(C.member(12)) 
         System.out.println("Member: " + 12); 
      else
         System.out.println("Not a member: " + 12); 
      C.delete(3);
      C.delete(42);
      C.delete(2);
      System.out.println("Deleted all elements:");
      C.list();

      
   }    
} 

// Collection is a simple container for integers; it allows duplicates, and if you delete
//    an int, it will only remove the first instance it comes to

 class Collection implements Collectable {  
// class Collection  { 
   private int [] A = new int[10]; 
   private int next = 0;  
   
   // insert a new int at the end of the list -- may run off the end of the array if it is full!
   
   public void insert(int k) { 
         A[next] = k;
         ++next;    
   } 
   
   // delete the first instance of a given int by scanning from left; if find the int, then
   //    move every later element left one slot
   
   public void delete(int k) {   
      for(int i = 0; i < next; ++i) {
         if(A[i] == k) {      // found it; move later elements over 
            for(int j = i+1; j < next; ++j) {
               A[j-1] = A[j];
            }
            --next;           // list has one fewer element
            break;            // delete only first instance of k, so terminate loop
         }
      }   
   } 
   
   // just a linear scan from left to right
   
   public boolean member(int k) {  
      for(int i = 0; i < next; ++i) {
         if(A[i] == k) {      // found it
            return true;
         }
      }
      return false;           // went through whole list and didn't find it
   }
   
   // for debugging....  shows the WHOLE array, and puts a "|" at the end of the elements actually
   //   stored
  
   public void list() {
      System.out.print("[");
      for(int i = 0; i < A.length; ++i) {
         if(i == next)
           System.out.print(" | " + A[i]);
         else if(i == 0)
           System.out.print(A[i] );
         else 
           System.out.print(", " + A[i] );
      }
      System.out.println("]"); 
      
   }
   
   // typical utility methods for showing status of the collection
   
   public int size() {
      return next;
   }
   
   public boolean isEmpty() {
      return (next == 0);
   }
} 


interface Collectable { 
   public void insert(int k) ; 
   public void delete(int k) ; 
   public boolean member(int k) ;
   public void list();
} 
