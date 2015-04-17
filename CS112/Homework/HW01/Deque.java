import java.util.Arrays;

public class Deque implements Dequeable {
    static private final int SIZE = 10; 
    static private int [] A = new int[SIZE]; 
    static private int size = 0;                      // number of elements stored in array, NOT size of array
    static private int front = 0; 
    static private int next = 0;      
    
    // Gives next index in array which wraps around in a ring; moves clockwise through indices
    public int nextSlot(int k) { 
        return ((k + 1) % A.length); 
    } 
    
    // Add element to the front of queue
    public void enqueueFront(int n) { 
        if(size == A.length) {
            resize(1);
        }
        for(int i = front; i <= next; i++) {  // Go through array from front to next
            int temp = A[i];                  // Store the current number in a temporary array
            A[i] = n;                         // Reasign the current space to new number
            n = temp;                         // Make original number new number
        }
        next = nextSlot(next); 
        ++size;                               // Increase size of array
    } 
    
    // Add element to the rear of queue
    public void enqueueRear(int n) {        // Nothing has changed
        if(size == A.length) {
            resize(1);
        }
        A[next] = n;
        next = nextSlot(next);
        ++size;
    } 
    
    // Remove elements from front of queue
    public int dequeueFront() {            // Nothing has changed
        /*if(size < (A.length / 4) && (A.length > 10)) {
            resize(2);
        }*/
        int temp = A[front];
        front = nextSlot(front);
        --size;
        return temp;
    }
    
    // Remove elements from rear of queue
    public int dequeueRear() {
        /*if(size < (A.length / 4) && (A.length > 10)) {
            resize(2);
        }*/
        int temp = A[--next];
        --size;
        return temp;
    }
    
    public void resize(int command) {
        int newSize;
        if(command == 1) {
            newSize = A.length * 2;
        } else {
            newSize = A.length / 2;
        }
        
        int[] newArr = new int[newSize];
        for(int i = 0; i < A.length-1; i++) {
            newArr[i] = dequeueFront();
        }
        next = A.length;
        size = A.length;
        front = 0;
        A = newArr;
    }
    
    // just a wrapper for private value size
    public int size() { 
        return size; 
    }  
    
    // standard utility for data structures
    public boolean isEmpty() { 
        return (size == 0); 
    } 
    
    public void showDetails() {
        System.out.println();
        System.out.println("Front: " + front);
        System.out.println("Next: " + next);
        System.out.println("Size: " + size);
        System.out.println("A.length: " + A.length);
        System.out.println();
    }
}