/*
 * BinaryTree.java
 * 
 * This is a binary tree
 * 
 * Made by: Aleksander Skjoelsvik
 * Based on code for binary tree provided by prof. Snyder on website
 */

public class BinaryTree {
    
    public static class Node {                                  // The node class
        public Article data;                                    // Article data, the content itself
        public Node left;                                       // The Node to the left of it
        public Node right;                                      // The node to the right of it
        
        public Node(Article a) {                                // Standard initialization for Node, passing only the article
            this.left = null;                                   // Initializing left and right to null, because there are no
            this.right = null;
            this.data = a;                                      // Setting data to the article passed
        }
        
        public Node(Article a, Node left, Node right) {         // Standard initialization for Node, passing article and nodes on each side
            this.data = a;                                      // Setting data to the article passed
            this.left = left;                                   // Setting left and right to the left and right node passed
            this.right = right;
            
        }
    }
    
    public Node root = null;                                    // Initializing the root node to null 
    public static int count = 0;                                // Count variable to keep track of how many results to print
    
    public void initialize(Article[] A) {                       // Initialize function, taken from DumbList.java. Takes an article
        for(int i = 0; i < A.length; ++i)                       // For each element in the article array (for loop runs 'till article length)
            root = add(root, A[i]);                             // Set the root to the element received when calling add, passing in the current root and the current article from array
    }
    
    public static Node add(Node t, Article a) {                 // Recursive add method, taking Node and article, returning Node (root)
        if (t == null) {                                        // If the node is null
            return new Node(a);                                 // Just return a new Node, without left or right children
        } else if (a.compareTo(t.data) < 0) {                   // If the current passed element is less than the element on the current node (a is in left subtree)
            t.left = add(t.left, a);                            // Recursivly go through the tree, passing in the left node and article
            return t;                                           // And return this node
        } else if (a.compareTo(t.data) > 0) {                   // If the current passed element is greater than the element on the current node (a is in right subtree)
            t.right = add(t.right, a);                          // Recursivly go through the tree, passing in the right node and article
            return t;                                           // And return this node
        } else {                                                // If none of these (it is equal)
            return t;                                           // Return the node
        }
    }
    
    // Height of a binary tree is number of links in 
    // longest path, note that empty tree has height -1.
    
    public static int height(Node t) {                          // Height function, returning the height of the passed node / tree
        if (t == null) {                                        // If the node is null
            return -1;                                          // Return -1
        } else {                                                // If not
            return 1 + maximum( height(t.left), height(t.right) ); // Return 1, plus the biggest of the height (recursivly) of the node to the left and to the right.
        }
    }
    
    private static int maximum(int n, int m) {                  // Maximum function, returning the greatest element of the two passed. Used for getting height
        if( m > n ){
            return m;
        } else {
            return n; 
        }
    }
    
    // Find a node with a given key and return a pointer 
    //to it or null if not found.
    
    public static Article lookup(Node t, String a) {            // Lookup function, returning an article where the title of the node is equal to the title passed
        if (t == null) {                                        // If node is null, return null
            return null;
        } else if (a.compareTo(t.data.getTitle()) < 0) {        // Else if, a is in left subtree
            return lookup(t.left, a);                           // Lookup (recursivly) to the left
        } else if (a.compareTo(t.data.getTitle()) > 0) {        // Else if a is in right subtree
            return lookup(t.right, a);                          // Lookup (recursivly) to the right
        } else {                                                // Else (if they match)
            return t.data;                                      // Return the current article
        }
    }
    
    public boolean member(String a) {                           // Member function, figures out if the passed string is a part of the current tree, using the lookup method
        return (lookup(root,a) != null);
    }
    
    // Recursively reconstructs tree without the key a in it
    public static Node remove(String a, Node t) {               // Remove method, takes a string and a node, and returns the new tree without that element in it
        if (t == null) {                                        // If Node is null
            return t;                                           // Return the node
        } else if (a.compareTo(t.data.getTitle()) < 0) {        // Else if a is in left subtree
            t.left = remove(a, t.left);                         // Recursivly go through the left subtree
            return t;                                           // And return the node
        } else if (a.compareTo(t.data.getTitle()) > 0) {        // Else if a is in right subtree
            t.right = remove(a, t.right);                       // Recursivly go through the right subtree
            return t;                                           // And return the node
        } else if (t.left == null) {                            // If there is no left child, reroute around this node
            return t.right;
        } else if (t.right == null) {                           // If there is no right child, rerout around this node
            return t.left;
        } else {                                                // Else if both children exist, move minimal key in right subtree up 
            Node min = findMin(t.right);                        // Preserve a pointer to this minimal node and then
            t.right = removeMin(t.right);                       // Reconstruct the right subtree without it
            min.left = t.left;                                  // Ultimatly, replace root node with min node
            min.right = t.right;
            return min;                                         // And return the node
        }
    }
    
    // Return a pointer to the minimal element in r
    public static Node findMin(Node r) {                        // Method finding the minimal node of the passed node, and returning it
        if(r.left == null) {                                    // This would be the minimal node since it has no children
            return r;                                           // Return this node (the minimal one)
        } else {                                                // If not minimal node
            return findMin(r.left);                             // Recursivly go to the left
        }
    }
    
    // Reconstruct the tree r without its minimal element
    public static Node removeMin(Node r) {                      // Method for removing the minimal element
        if(r.left == null) {                                    // If the element to the left is the minmal element
            return r.right;                                     // Return the node to the right
        } else {                                                // If not minmal node
            r.left = removeMin(r.left);                         // Recursivly go to the left
            return r;                                           // And return the node
        } 
    }
    
    public static void traverse(Node t, String s, String location) { // Method for traversing the tree, taking in a node, and the string to search for
        if(t != null && s != null) {                            // If the node and string aren't null
            traverse(t.left, s, location);                      // Recursivly traverse to the left
            
            String search;                                      // Initialize the string search
            if(location.equals("title")) {                      // If we're searching for something in the title
                search = t.data.getTitle().toLowerCase();       // Set the string search to the title in all lower case
            } else {                                            // If we're searching for something in the body
                search = t.data.getBody().toLowerCase();        // Set the string search to the title in all lower case
            } 
            s = s.toLowerCase();                                // Set the string we're searching for to all lower case
            
            if(search.contains(s)) {                            // If what we're searching in contains what we're searching for
                visit(t, s);                                    // Visit that node, incrementing the count
                count++;
            }
            
            traverse(t.right, s, location);                     // Recursivly traverse to the right
        }
    } 
    
    public static void visit(Node t, String s) {                // Method for visiting a node and printing its content 
        if(count < 10) {                                        // If the count is less than 10
            String print = t.data.toString().replaceAll("(?i)" + s, s.toUpperCase()); // Construct a string, where you replace all the occurances anywhere in the article of the search keyword with a capitalized version of the search keyword
            System.out.println(print);                          // Print out this article
        }
    }
}