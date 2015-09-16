/* CS112 - LAB 01 - January 27th 2014
 * Edited by: Aleksander Skjoelsvik
 */

public class IntQueue implements IntQueueable {
    private int[] array1 = new int[10];                               // Setting up the array
    private int var1 = 0;                                         // Setting up the variable
    
    public void enqueue(int n) {                                    // Defining the function enqueue
        array1[var1] = n;
        ++var1;
    }
    
    public int dequeue() {                                                 // Defining the function dequeue
        int check = array1[0];
        
        for(int k = 0 ; k < var1; ++k) {
            array1[k] = array1[k++];
        }
        --var1;
        return check;
    }
    
    public void list() {                                                   // Defining the function list
        for(int l = var1-1; l >= 0; --l) {
            System.out.print(array1[l] + " ");
        }
        System.out.println();
    }
    
    public boolean isEmptyArray1() {                                  // Defining the function isEmptyarray1
        return(var1 == 0);
    }
}

interface IntQueueable {                                                   // The interface for IntQueue
    public void enqueue(int n);
    public int dequeue();
    public void list();
    public boolean isEmptyArray1();
}