/* File: MutIntBSCollectionLab03.java
 * Date: 1/26/13
 * Class: Boston University CS 112, Spring 2013
 * Author: Wayne Snyder (snyder@bu.edu)
 * Purpose:  In lab 03 we will find the bugs!
 * Credits: The binary search algorithm is taken almost verbatim from p.47 in 
 *          Algorithms by Sedgewick and Wayne
 * Edited by: Aleksander Skjoelsvik
 */

public class MutIntBSCollectionLab03 {
    
    // Initialize A to "empty" array with 10 slots; note that A will initially contain 0's, which
    // are really "null values" that don't represent data in the collection
    
    private final int SIZE = 10; 
    private int [] A = new int[SIZE];
    
    // next keeps track of next available slot as we insert and delete; so the "useful" members
    // of the array are in A[0] .. A[next-1]; next is also the size of collection
    
    private int next = 0; 
    
    // member returns true iff n is in the list A; uses keyLocation as helper function
    // note that must check if location is legal (location < next)
    
    public boolean member(int key) {
        int loc = keyLocation(key);                 // find where key should be          
        return (loc < next && A[loc] == key);   // see if it is there 
    }
    
    // return location where key should be in the array if it were present;
    // if the key in fact is there and location < next, then it is a member, 
    // if not, this is the place it should occur when inserting;
    // note that location will be in range [0 .. next] 
    
    private int keyLocation(int key) {
        int lo = 0; 
        int hi = next-1; 
        // loop will terminate when key is found or hi < lo, in which case A[lo] is where n should be
        while (lo <= hi) {
            // key is in A[lo..hi] or not present.
            int mid = (lo + hi) / 2;                 
            if (key < A[mid]){
                hi = mid - 1;
            } else if (key > A[mid]) {
                lo = mid + 1;
            } else {                    // if not < and not > must be equal!
                return mid;
            }
        }
        return lo;           // note that lo could equal next if that is the location where it would go 
    } 
    
    
    // returns number of integers in the collection
    
    public int size() {
        return next; 
    }
    
    // inserts the integer into the collection if there is room,
    // and does nothing otherwise; returns true iff successfully inserted 
    
    public boolean insert(int n) {
        if (next == A.length) {           // if array is full, just return false
            return false;
        } else {
            int loc = keyLocation(n);               // A[loc] is where n should be inserted
            for(int i = next; i > loc; --i) { 
                A[i] = A[i-1];                 // move A[loc..next-1] to A[loc+1..next], working from R to L
            }
            A[loc] = n;
            ++next;
            return true;
        }
    }
    
    // deletes integer n from the collection if it exists
    // and does nothing otherwise; returns true iff successfully deleted
    
    public boolean delete(int key) {
        int loc = keyLocation(key);               // A[loc] is where n would be if it is in A
        if (A[loc] != key) {                      
            return false;                           // key is not in A!
        } else {
            for(int i = loc+1; i < next; ++i) {  
                A[i-1] = A[i];                 // move A[loc+1..next-1] to A[loc..next-2], working from L to R
            }
            --next;
            return true;
        }
    }
    
    // For LAB 02: You have to turn this into a method
    //  public String toString() { .....    }
    // which returns a String that can be printed out in the unit test:
    //    ....
    //    System.out.println(C.toString); 
    //    ....
    // instead of calling C.printArray()
    
    private String printArray() {
        String string = "\tA: [ ";
        for (int i = 0; i < A.length; ++i) {
            if (i == next) {
                string += "| "; 
            }
            string += A[i] + " "; 
        }
        string += "]  next: " + next; 
        return string;
    }
    
    
    
    // Unit test: Create an instance of this class and test all possible conditions: beginning of list, 
    // end, middle, duplicates, and not in list
    // Note: This unit test is same as for IntBSCollection
    
    public static void main(String [] args) {
        
        MutIntBSCollectionLab03 C = new MutIntBSCollectionLab03();   // can not make C a MutableCollection if using printArray
        
        int n;    // just a temp to keep track of what member we are testing
        
        // test insert, including case of inserting when it is full
        for (int i = 10; i < 21; ++i) {
            n = i; 
            System.out.print("Insert  \t" + n + "\t"); 
            System.out.println(C.insert(n)); 
        }
        
        // test size
        System.out.print("Size?    \t\t");  
        System.out.println(C.size());   
        
        // test member on usual cases: beginning, end, middle, not a member
        n = 10; 
        System.out.print("Member? \t" + n + "\t");  
        System.out.println(C.member(n));
        
        n = 19; 
        System.out.print("Member? \t" + n + "\t");  
        System.out.println(C.member(n));  
        
        n = 13; 
        System.out.print("Member? \t" + n + "\t");  
        System.out.println(C.member(n)); 
        
        n = 20; 
        System.out.print("Member? \t" + n + "\t");  
        System.out.println(C.member(n)); 
        
        n = 4; 
        System.out.print("Member? \t" + n + "\t");  
        System.out.println(C.member(n));
        
        // test delete on usual cases: beginning, end, middle, not a member
        n = 10; 
        System.out.print("Delete  \t" + n + "\t");  
        System.out.println(C.delete(n));
        
        
        n = 19; 
        System.out.print("Delete  \t" + n + "\t");  
        System.out.println(C.delete(n)); 
        
        
        n = 13; 
        System.out.print("Delete  \t" + n + "\t");  
        System.out.println(C.delete(n)); 
        
        
        n = 4; 
        System.out.print("Delete  \t" + n + "\t");  
        System.out.println(C.delete(n));
        
        
        System.out.print("Size?    \t\t");  
        System.out.println(C.size()); 
        
        for (int i = 0; i < 11; ++i) {
            n = i + 10; 
            System.out.print("Delete  \t" + n + "\t"); 
            System.out.println(C.delete(n));  
        }
        
        System.out.print("Size?    \t\t");  
        System.out.println(C.size()); 
        
        n = 18; 
        System.out.print("Member? \t" + n + "\t");  
        System.out.println(C.member(n));
        
        System.out.println("Um.... something wrong with the last answer!!"); 
        
    }  
    
}