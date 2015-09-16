/* CS112 - LAB 01 - January 27th 2014
 * Edited by: Aleksander Skjoelsvik
 */

public class IntStack implements IntStackable{
    private int[] array2 = new int[20];          // Setting up the array
    private int var2 = 0;                      // Setting up the variable
    
    public void push(int i) {                // Defining the function push
        array2[var2] = i;
        ++var2;
    }
    
    public int pop() {                               // Defining the function pop
        return array2[--var2];
    }
    
    public boolean isEmptyArray2() {            // Defining the function isEmptyarray2
        return(var2 == 0);
    }
}

interface IntStackable {                            // The interface for IntStack
    public void push(int i);
    public int pop();
    public boolean isEmptyArray2();
}