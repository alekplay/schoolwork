/* TermFrequencyTable.java
 * 
 * Problem three | Homework 10 | CS112 | prof. Snyder
 * 
 * Made by: Aleksander Skjoelsvik
 * 
 * Wed April 16th.
 * 
 * Based on articletable by me
 */

public class TermFrequencyTable {
    
    private static final int N = 101;                             // Size of table, prime number
    private int numItems = 0;                                     // Number of items in table
    
    private class Node {                                          // Node class, contains a term, an integer array of term frequencies, next and next2 pointers
        String term;
        int[] termFreq = new int[2];
        Node next;
        Node next2;
        
        public Node(String term, Node next, Node next2) {         // Node implemtation
            this.term = term;
            this.next = next;
            this.next2 = next2;
        }
    }
    
    private Node[] A = new Node[N];                               // Initializing the array.
    private Node head = null;                                     // Node named head. This will point to the most recently added element
    private Node root = null;                                     // Node named root. This root will point to the first element
    private Node p = null;                                        // Pointer node. Starts at root, iterates through the main linked list up to head
    
    private Node insertInBucket(String term, Node n, int docNum) { // Recursive bucket insert, returns a new linked list with the new node in it
        if(n == null) {                                           // Base case, if the node is null, insert the new node.
            ++numItems;                                           // Increment number of items
            if(root == null) {                                    // If root has not been set (THIS WILL ONLY RUN THE FIRST TIME)
                head = new Node(term, null, null);                // Set the head to a new node containing the term, and pointing to null
                root = new Node(null, null, head);                // Set the root to a node pointing to the head
                p = root;                                         // Set the pointer to the root
                ++head.termFreq[docNum];                          // Increment the term frequency of the newly inserted term for the current document number
            } else {                                              // If root has been set (THIS WILL NORMALLY RUN)
                head.next2 = new Node(term, null, null);          // Set the next2 of the head (previosly inserted node) to a new node pointing to null containing the article
                head = head.next2;                                // Reset the head to the new node
                ++head.termFreq[docNum];                          // Increment the term frequency
            } 
            return head;                                          // Return the head (for the linked list in the bucket)
        } else if(term.equalsIgnoreCase(n.term)) {                // Already exists in list, so don't insert it, just incremnt
            ++n.termFreq[docNum];                                 // Increment the term frequency counter for the current node on the current document number
            return n;
        } else {                                                  // Recursive case, go through the linked list
            n.next = insertInBucket(term, n.next, docNum);
            return n;
        }
    }
    
    private static int hash(String term) {                        // Hash function, takes a term and returns the spot in the array it should be placed
        int hash = 0;                                             // Int variable, will contain the hash value
        term = term.toLowerCase();                                // Set the term to lower case
        for (int i = 0; i < term.length(); i++) {                 // Go through each character in the term
            hash += term.charAt(i);                               // Add the integer value of this term to the hash variable
        }
        return Math.abs((hash * 10891)) % N;                      // return the absolute value of the hash value times a large prime number modula the table size

    }
    
    public void insert(String term, int docNum) {                 // Insert, takes a term and document number and inserts it in the appropriate spot in the array
        A[hash(term)] = insertInBucket(term, A[hash(term)], docNum);
    }
    
    public double cosineSimilarity() {                            // Cosine similarity method, returns the similarity between the two documents
        double result = 0;                                        // This will contain the final result
        double numerator = 0;                                     // This will contain the numerator of the function
        double denominatorA = 0;                                  // This will contain the first part of the denominator of the function
        double denominatorB = 0;                                  // This will contain the second part of the denominator of the function
        double denominator = 0;                                   // This will contain the denominator of the function
        
        while(hasNext()) {                                        // Go through each node in the table
            int[] termFreq = next();                              // Get the termfrequency of the next node and store it in an int array
            numerator += (termFreq[0] * termFreq[1]);             // Multiply them together and add them to the numerator
            denominatorA += (Math.pow(termFreq[0], 2));           // Square of the first one, and add it to the first part of the denominaotr
            denominatorB += (Math.pow(termFreq[1], 2));           // Square of the last one, and addit to the second part of the denominator
        }
        
        denominatorA = Math.sqrt(denominatorA);                   // Take the square root of the first part of the denominator and save it to self
        denominatorB = Math.sqrt(denominatorB);                   // Take the square root of the second part of the denominator and save it to self
        denominator = denominatorA * denominatorB;                // Multiply them together and save in denominator
        
        result = numerator / denominator;                         // Get the result by dividing numerator over denominator
 
        if(Double.isNaN(result)) {                                // If the result was NaN (one of the denominators were 0 etc). Shouldn't happen but just in case
            result = 0.0;                                         // Set the result to 0
        }
        
        return result;                                            // Return the result
    }
    
    private boolean hasNext() {                                   // Returns true if there are more articles 
        return (p.next2 != null);
    }
    
    private int[] next() {                                        // Returns the termFreq of the next item
        p = p.next2;                                              // Set the pointer to the next item
        return p.termFreq;                                        // Return the termFreq of that item (THIS WILL NOT SKIP AN ITEM, SINCE P STARTS AT THE ROOT WHICH CONTAINS NOTHING)
    }
}