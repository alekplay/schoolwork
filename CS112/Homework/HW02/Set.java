/* Set.java
 * Edited by: Aleksander Skjoelsvik 
 * Date: 2/6/2014
 */

@SuppressWarnings("unchecked")                              // suppress the warning that the hack with generic items causes......

    public class Set<Item> implements Setable<Item> {       // only place where interface Setable is used... just to check the contract.....
    
    // various declarations of variables etc.
    private final int SIZE = 10;                            // Standard size of array, no other directions given so assumed 10
    private Item[] A = (Item[]) new Object[SIZE];           // Creating an array with name A of type Item (using the "hack" from the book, to avoid generic array creation)
    private int size = 0;                                   // Var to keep track of size
    private int next = 0;                                   // Var to keep track of where to place next item
    private int nextItem = 0;                               // Var to keep track of next item to return, as if you were deleting it. Can be compared to front from other tasks, but you're not moving the front
    
    public Set() {}                                         // Default constructor
    
    public Set(Item[] item) {                               // Constructor which takes an array of Items and inserts each into the set as an initialization step
        for(int i = 0; i < item.length; i++) {              // Go through each element in the item given (previously called A, renamed to item)
            insert(item[i]);                                // Insert them into
        }
    }
    
    public void insert(Item k) {                            // Insert function that takes an item to insert
        if(size == A.length) {                              // If the size is equal to the length:
            expand();                                       // Expand the array
        }
        if(member(k) == false) {                            // If the item passed doesn't already exist in the array:
            A[next] = k;                                    // Add the item passed into the next spot in the array
            next++;                                         // Increment next and size
            size++;
        }
    }
    
    public void delete(Item k) {                            // Delete function that takes an item to delete
        if(member(k) == true) {                             // If the item is present in the array
            int i = 0;                               
            while(!A[i].equals(k)) {                        // While the item at i (starting at 0) doesn't equal the item passed (to find the matching item)
                i++;                                        // Increment i. Will stop where the item passed equals the item in array
            }
            
            for(int j = i; j < size; j++) {                 // A for loop that runs from the item to be deleted to the end
                A[j] = A[j+1];                              // Move everything up
            }
            next--;                                         // Decrement next and size
            size--;
        }
    }
    
    public boolean member(Item k) {                         // Member function that takes an item to check if exists in array
        for(int i = 0; i < size; i++) {                     // Run through the entire array
            if(A[i].equals(k)) {                            // If the element is present at current spot, return true
                return true;
            }
        }
        return false;                                       // If haven't returned anything, return false because the element wasn't found
    }
    
    public boolean isEmpty() {                              // isEmpty function to check if the array is emtpy
        if(size == 0) {                                     // If the size is 0, the array is empty
            return true;                                    // Return true (isEmpty)
        } else {
            return false;                                   // Else return false
        }
    }
    
    public int size() {                                     // size function that returns the number of elements in the array (size)
        return size;                                        // Return the content of the size variable (which increments and decrements in pace with adding and removing elements from the array)
    }
    
    public Set<Item> union(Set<Item> S) {                   // Union function that returns a new set which consists of the calling set plus the set S (passed in). 
        S.reset();                                          // Reset both sets
        reset();
        Set<Item> newSet = new Set<Item>();                 // Make a new set
        while(hasNext()) {                                  // While there still are elements in the calling set
            newSet.insert(next());                          // Insert them into the new set
        }
        while(S.hasNext()) {                                // While there still are elements in the passed set S
            newSet.insert(S.next());                        // Insert them into the newSet (the insert function checks for duplicates)
        }
        return newSet;                                      // return the newSet
    }
    
    public Set<Item> intersection(Set<Item> S) {            // intersection function that returns a new set which consists of the elements that are the same of the two sets
        S.reset();                                          // Reset both sets
        reset();
        Set<Item> newSet = new Set<Item>();                 // Make a new set
        while(hasNext()) {                                  // While there still are elements in the calling set
            Item item = next();                             // Store the current item in a variable called item
            if(S.member(item)) {                            // If the current item exists in S:
                newSet.insert(item);                        // Insert it into the newSet
            }
        }
        return newSet;                                      // Return the new set
    }
    
    public Set<Item> difference(Set<Item> S) {              // Difference function that returns the elements that exists in the calling set but not in the passing set
        S.reset();                                          // Reset both sets
        reset();
        Set<Item> newSet = new Set<Item>();                 // Make a new set
        while(hasNext()) {                                  // While there are elements in the calling set
            Item item = next();                             // Store the current item in a variable called item
            if(!S.member(item)) {                           // If the current item doesn't exist in S
                newSet.insert(item);                        // Insert it into the new set
            }
        }
        return newSet;                                      // Return new set
    }
    
    public boolean equals(Set<Item> S) {                    // Equals function that returns true if both sets are completely equal
        S.reset();                                          // Reset both sets
        reset();
        if(S.size() == size()) {                            // If they are the same sizes
            int numOfEqualItems = 0;                        // Make a variable that stores the number of equal items
            while(hasNext()) {                              // While there are elements in the calling set
                if(S.member(next())) {                      // If the curernt item exists in S
                    numOfEqualItems++;                      // Increment the number of equal items
                }
            }
            if(numOfEqualItems == size) {                   // If there are the same number of equals as the size (all items are equal(
                return true;                                // Return true
            }
        }
        return false;                                       // If nothing has been returned, return false because they're not equal
    }
    
    public void reset() {                                   // Reset function that
        nextItem = 0;                                       // Sets nextItem back to 0
    }
    
    public boolean hasNext() {                              // hasNext function that sees if there are more elements in the array
        if(nextItem >= next) {                              // If the var nextItem is greater than or equal to the location of where the next item would be inserted
            return false;                                   // Return false, because tehre are no more elements
        } else {
            return true;                                    // If not return false, because there are more elements
        }
    }
    
    public Item next() {                                    // next function that returns the element in nextItem spot and increments nextItem
        return A[nextItem++];                               // Return the element of A that is in nextItem spot and increment
    }
    
    private void expand() {                                 // expand function that increases the size of the array
        Item[] B = (Item[]) new Object[A.length * 2];       // Create a new array of type item using the hack from the book, that is twice the length of the current array
        for(int i = 0; i < size; ++i) {                     // Run through all the elements in the current array
            B[i] = A[i];                                    // Assign each one of them to the new array
        }
        next = size;                                        // Set next to the size (for security jic)
        A = B;                                              // Reasign the value of A to the one of B 
    }
    
    public boolean subset(Set<Item> S) {                    // Subset function that returns true if the calling set is a subset of the passed set
        S.reset();                                          // Reset both sets
        reset();
        if(S.size() >= size()) {                            // If the passed set is bigger or equal to the calling set (which it must be if the calling set would be a subset)
            int numOfEqualItems = 0;                        // Make a variable to keep track of the number of equal items
            while(hasNext()) {                              // While there still are items in the calling set
                if(S.member(next())) {                      // If the next item in current set exists in S
                    numOfEqualItems++;                      // Increase the var numOfEqualItems to keep track of number of equal items
                }
            }
            if(numOfEqualItems == size) {                   // If the numOfEqualItems var is equal to the size (all elements are present in S)
                return true;                                // Return true
            }
        }
        return false;                                       // If nothing has been returned yet, return false because it is not a subset
    }
}

// interface, put in file here so don't lose it!
// this interface is just to check if you have satisfied the assignment, and the
// only place you should use it is in the first line of the class, as shown above.

interface Setable<Item> {
    public void insert(Item k);       // if k is already in the set, do nothing; else insert it
    public void delete(Item k);       // if k is NOT in the set, do nothing; else delete it
    public boolean member(Item k);    // return true if k is in set, false otherwise
    public boolean isEmpty();         // return true if set has no members, false otherwise
    public int size();                // return number of items in the set
    public Set<Item> union(Set<Item> S);          // create a new Set consisting of the union of this set and S
    public Set<Item> intersection(Set<Item> S);   // create a new Set consisting of the intersection of this set and S
    public Set<Item> difference(Set<Item> S);     // create a new Set consisting of the set difference of this set and S (this - S)
    public boolean equals(Set<Item> S);           // return true if this set is equal to S, and false otherwise
    public void reset();              // reset the explicit iterator (see below)
    public boolean hasNext();         // return true if there is a next item in the iteration of this set
    public Item next();               // return the next item in the iteration of all items in the set
}