/* Homework 1 - CS112 - BU - Prof. Snyder
 * Edited/made by: Aleksander Skjoelsvik - U54904431
 * Contributors: none
 * Using Javas built in binarySearch function, assuming it's ok. Didn't say we couldn't, and KISS!
 */

import java.util.Arrays;

public class Collection implements Collectable{
    // class Collection  { 
    private int [] A = new int[10]; 
    private int next = 0;  
    private int arrayLength;                   // Had to make a new variable for the arrayLength in case of resizing
    
    // Insert a element where it fits based on size
    
    public void insert(int k) {
        int insertionPoint = next;              // Set the insertionPoint to the next available spot, won't be changed unless: 
        for(int i = 0; i < next; ++i) {         // Search through the elements from 0 to where the next available spot is
            if(A[i] > k) {                      // If any element in that loop is greater than the one to be inserted
                insertionPoint = i;             // Set the insertionPoint to the current spot
                break;                          // Break, we have what we want
            }
        }
        System.out.println("next = " + next);
        if(next >= A.length) {                  // If the next spot to put something in the array is greater or equal to its length, call resizeArray and pass the argument 1 for upscaling
            resizeArray(1);
        }
        
        int temp;
        for(int j = insertionPoint; j <= next; j++) { //Search through array from insertionPoint to next available spot
            temp = A[j];                              // Store the current number into variable temp
            A[j] = k;                                 // Insert the new number in temps place
            k = temp;                                 // Set the new number to be inserted to what was in the previous spot
        }
        next++;                                       // Inc next
    } 
    
    public void resizeArray(int doWhat) {               // Custom function to resize array, works for both downscale and upscale
        if(doWhat == 1) {                               
            arrayLength = A.length * 2;             // If parameter passed is 1, we're upscaling. Set the new size double the current size
        } else {
            arrayLength = A.length / 2;             // If parameter passed is 2, we're downscaling. Set the new size half the current size
        }
        int newArray[] = new int[arrayLength];          // Make a new array that size
        for(int i = 0; i < next; i++) {             // Run through all the elements in current array
            newArray[i] = A[i];                         // Assign them to new array
        }
        A = newArray;                                   // Make A (name of old array) reference the new array
    }
    
    public void delete(int k) {                        // Function for deleting an element in the array
        for(int i = 0; i < next; ++i) {
            if(A[i] == k) {                            // found it; move later elements over 
                for(int j = i+1; j < next; ++j) {
                    A[j-1] = A[j];
                }
                --next;           // list has one fewer element
                if ((next < (A.length / 4)) && (A.length > 10)) { //If the next is now set at a size that's less than 1/4 of the length of the array AND the array is bigger than 10
                    resizeArray(2);                               // Call function resizeArray and pass parameter 2 (downscale)
                }
                break;            // delete only first instance of k, so terminate loop
            }
        }
    } 
    
    // Binary search method
    
    public boolean member(int k) {  
        int point = Arrays.binarySearch(A, 0, next, k);               // KISS put into effect (using java's built in binarySearch function)
        if(point >= 0) {                                              // It returns a positive value if found, negative if not
            return true;
        } else {
            return false;
        }
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