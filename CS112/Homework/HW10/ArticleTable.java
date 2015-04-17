/*
 * ArticleTable.java
 * 
 * This is a hash table
 * 
 * For homework 10 | CS112 | BU
 * 
 * Made by: Aleksander Skjoelsvik - U54904431
 * Based on code for HashTable on website by prof. Snyder
 */

import java.util.*;

public class ArticleTable implements Iterator<Article>  {
    
    private static final int N = 2503;                             // Size of table, prime number
    public static int numItems = 0;                                // Number of items in table
    
    private static class Node {                                    // Node class, contains article and two next pointers (one for the linked list in each bucket and one for the master linked list)
        Article a;
        Node next; 
        Node next2;
        
        public Node(Article a, Node next, Node next2) {            // Node implementation class
            this.a = a;
            this.next = next;
            this.next2 = next2;
        }     
    }
    
    private static Node[] A = new Node[N];                          // Initializing the array of nodes, using the size given.
                                                                    // The three following nodes are for the main linked list.
    private static Node head = null;                                // Node named head. This will always be the most recent added node.
    private static Node root = null;                                // Node named root. This will always be the pointing to the first added node.
    private static Node p = null;                                   // Node named p. This iterates through the main linked list from the root to the head
     
    static void initialize(Article[] array) {                       // Initialize method, taking an array of articles and inserting them each into the hashtable
        for(Article article : array) {                              // For each article in array as article
            insert(article);                                        // Insert article
        }
    }
    
    private static Node insertInBucket(Article a, Node n) {         // Recursive bucket insert, takes an article and node, and returns the new linked list with the node in it
        if(n == null) {                                             // Base case, if the node equals to null, we're gonna create the new node
            ++numItems;                                             // Increment the number of items
            if(root == null) {                                      // If the root is not set, run this bit: (this ONLY RUNS THE VERY FIRST TIME)
                head = new Node(a, null, null);                     // The head (most recent node) equals a new node with containing passed article, pointing to null
                root = new Node(null, null, head);                  // Set the root to a new node pointing to the head
                p = root;                                           // Set the pointer to the root.
            } else {                                                // If root is set (THIS WILL RUN NORMALLY)
                head.next2 = new Node(a, null, null);               // Set the next2 of the head (previosly added node) to a new node pointing to null and containing article
                head = head.next2;                                  // Reassign the head to this newly inserted node
            }
            return head;                                            // Return the head (so we also add it to the linked list of the bucket
        } else if(a.getTitle().equalsIgnoreCase(n.a.getTitle())) {  // The node already exists in list, so don't insert it.
            return n;  
        } else {                                                    // Recursive case, go through each node recursivly till' you reach the base case (node==null). That will be the end of the bucket list
            n.next = insertInBucket(a, n.next);                     // Also, set the next of the current node to the node returned by the list (so we maintain our linked list structure for the bucket)
            return n;         
        }
    }
    
    private static Node deleteFromBucket(String title, Node n) {   // Recursive bucket delete. Takes a title and node, and returnes a new linked list without the article with title in it
        if(n == null) {                                            // If node is null, we've reached the end of the lniked list and article has not been found. Just return null.
            return null;
        } else if(title.equalsIgnoreCase(n.a.getTitle())) {        // We found the article we're deleting
            --numItems;                                            // Decrease the number of items
            return n.next;                                         // Return the NEXT node. That way we're rerouting the linked list AROUND the node we're deleting, eliminating it from the structure.
        } else {                                                   // Recursive case, just go through the linked list 
            n.next = deleteFromBucket(title, n.next);
            return n;
        }
    }
    
    private static Node deleteFromList(String title, Node n) {     // Recursive delete from main linked list. Takes a title and node, and returns a new linked list without the article with title in it
        if(n == null) {                                            // If node is null, we've reached the end of the linked list and article has not been found
            return null;
        } else if (title.equalsIgnoreCase(n.a.getTitle())) {       // We found the article we're deleting
            return n.next2;                                        // Return the NEXT node. Same as before
        } else {                                                   // Recursive case, just go through the linked list
            n.next2 = deleteFromList(title, n.next2);
            return n;
        }
    }
    
    private static Node lookup(String title, Node n) {             // Recursive lookup. Returns node where the article with title is stored
        if(n == null) {                                            // If node is null, we've reached the end of the linked list and article has not been found. Just return.
            return null;
        } else if(title.equalsIgnoreCase(n.a.getTitle())) {        // We found the article we're looking for
            return n;                                              // Return the node
        } else {                                                   // Recursive case, go through the linked list
            return lookup(title, n.next);
        }
    }
    
    
    private static int hash(String title) {                         // Hash function, takes a title and returns the appropriate spot in the array as int
        int hash = 0;                                               // Set the hash variable, will contain hash value
        title = title.toLowerCase();                                // Set the title to lower case
        for (int i = 0; i < title.length(); i++) {                  // Go through each character in the title
            hash += title.charAt(i);                                // Add the integer value of the current character to the hash variable
        }
        return Math.abs((hash * 10891)) % N;                        // Set the hash variable to the absolute value of the (old) hash value times a large prime number modula the table size
    }
    
    public static void insert(Article a) {                          // Insert function, inserts the article in the appropriate spot in the array
        A[hash(a.getTitle())] = insertInBucket(a, A[hash(a.getTitle())]);
    }  
    
    public static void delete(String title) {                       // Delete function, deletes the article with title from the appropriate spot in the array
        A[hash(title)] = deleteFromBucket(title, A[hash(title)]);
        root.next2 = deleteFromList(title, root.next2);
    }
    
    public static Article lookup(String title) {                    // Lookup function, searches the appropriate spot in the array for the article with title
        Node a = lookup(title, A[hash(title)]);
        if(a != null) {
            return a.a;
        } else {
            return null;
        }
    }
    
    public void reset() {                                           // Resets the pointer back to root
        p = root;
    }
    
    public boolean hasNext() {                                      // Returns true if p is not pointing to the last item
        return (p.next2 != null);
    }
    
    public Article next() {                                         // Returns the next article, following the main linked list
        p = p.next2;                                                // Set the p to the next node
        return p.a;                                                 // Return the article of that node (this doesn't skip any articles, since p starts at root which doesn't contain one. But it does return the NEXT node correctly)
    }
    
    public void remove() {                                           // Not implemented, just put it here to satisfy the interface
        throw new UnsupportedOperationException();
    }
}