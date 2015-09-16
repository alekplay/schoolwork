/* File: Queue.java
 * Date: 2/1/14
 * Author:  Wayne Snyder (snyder@bu.edu)
 * Class: CS 112, Fall 2014
 * Purpose: Starter code for Lab02
 * Edited by: Aleksander Skjoelsvik
 */


public class PriorityQueue {
    
    private final int SIZE = 10; 
    private Comparable [] A = new Comparable[SIZE]; 
    private int size = 0;                      // number of elements stored in array, NOT size of array
    private int front = 0; 
    private int next = 0;  
    
    // gives next index in array which wraps around in a ring; moves clockwise through indices
    private int nextSlot(int k) { 
        return ((k + 1) % A.length); 
    } 
    
    // put at the end of the sequence, going clockwise
    public void enqueue(Comparable n) { 
        if(size == A.length)               // Didn`t change anything for the first 5 lines
            expand(); 
        A[next] = n; 
        next = nextSlot(next); 
        ++size;
        
        int i = previousSlot(next);                                           // Make a variable i, which is the index where the current item was inserted
        while(i != front && (Integer)A[i] > (Integer)A[previousSlot(i)]) {    // While the index doesn`t equal the front (it is the first element) AND the item is greater than the one before it, run the loop. Typecasted into integers, guy at lab said it was ok
            Comparable temp = A[i];                                           // Make a variable temp containing one of the elements being swapped
            A[i] = A[previousSlot(i)];                                        // Set the item at i equal to the item before it
            A[previousSlot(i)] = temp;                                        // Set the item before the current equal to temp (the current(
            i = previousSlot(i);                                              // Set i to the previous slot, so we can continue swapping the element down the list
        }
    }
    
    public int previousSlot(int k) {                                         // previousSlot method, finds the previous slot
        if(k == 0) {                                                         // If k=0, which means the element you want the previous slot for is element 0
            return A.length-1;                                               // Return the length - 1, which means the elemnt before 0 (0-1-2-3-4-0-1-2-3-4-0 etc))
        } else {                                                             // If the slot you want the perviosu for isnt 0
            return (k-1) % A.length;                                         // Return the previous slot, following the code from the nextSlot method only changing k+1 to k-1
        }
    }
    
    // expand or shrink the array; insert into new array by dequeueing from the old
    private void expand() {
        Comparable[] B = new Comparable[2*A.length];  
        for(int i = 0, j = front; i < size; ++i,j = nextSlot(j)) {
            B[i] = A[j];
        }
        front = 0;               
        next = size; 
        A = B;
    }
    
    // dequeue and move front pointer clockwise; can underflow without warning and produce error elements
    public Comparable dequeue() { 
        Comparable temp = A[front]; 
        front = nextSlot(front);  
        --size;  
        return temp; 
    } 
    
    // just a wrapper for private value size
    public int size() { 
        return size; 
    }  
    
    // standard utility for data structures
    public boolean isEmpty() { 
        return (size == 0); 
    } 
    
    // debugging routine
    private void list() {
        System.out.println("\nListing of queue clockwise from 0 (on left) to " + (A.length - 1) + ":"); 
        for(int i = 0; i < A.length; ++i)
            System.out.print(A[i] + " ");
        System.out.println(); 
        System.out.println("next = " + next + " front = " + front);
        System.out.println(); 
        
    }
    
    // Unit Test for debugging this ADT
    
    public static void main(String [] args) {
        
        PriorityQueue P = new PriorityQueue(); 
        
        P.enqueue(1);
        P.dequeue();
        P.enqueue(1);
        P.dequeue();
        P.enqueue(1);
        P.dequeue();
        P.enqueue(1);
        P.dequeue();
        P.enqueue(5);
        int x = (Integer) P.dequeue();
        P.enqueue(3);
        P.enqueue(x);
        P.enqueue(4);
        int y = (Integer) P.dequeue();
        P.enqueue(1);
        P.enqueue(y);
        P.enqueue(x+y+3);
        P.enqueue(2);
        
        P.list();
        
        System.out.println("\nDequeue all elements; should print out (front is to left):\n5  4  1  3  11  2:");       
         while( !P.isEmpty() ) {
         System.out.print( P.dequeue() + "  "); 
         }
         System.out.println();
        
    } 
    
}