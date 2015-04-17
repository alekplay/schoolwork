/* Homework 11 - CS112 - Spring 2014 - Prof. Snyder - BU
 * Graph Class
 * Made by: Aleksander Skjoelsvik and Raphael Baysa
 */

public class Graph {
    private static int[][] B;                                                // This is a 3D array containing all edges (connected through to corners)
    public static int N;                                                     // This is the amount of corners (and therefore edges)
    
    public Graph(int N) {                                                    // Initialize the board (graph) given the size N
        this.N = N;
        this.B = new int[N][N];
    }
    
    public void addEdge(int u, int v, int w) {                               // Adding an edge between u and v of color w
        if((u != v) && (this.B[u][v] == 0)) {                                // If u does not equal v (connecting to itself), and it doesn't already exist                     
            this.B[u][v] = w;                                                // Add the edge of color w between u and v
            this.B[v][u] = w;                                                // Add the edge of color w between v and u
        }
    }
    
    public void removeEdge(int u, int v) {                                   // Removes the edge between u and v
        if(u != v) {                                                         // If u does not equal v (connecting to itself)
            this.B[u][v] = 0;                                                // Set the edge (color) to be 0, therefore no edge between u and v
            this.B[v][u] = 0;                                                // ---""--- between v and u
        }
    }
    
    public int getEdge(int u, int v) {                                       // Gets the color of the edge between u and v
        return this.B[u][v];
    }
    
    public boolean isEdge(int u, int v) {                                    // Returns true if there is an edge of any color between u and v
        return (this.B[u][v] != 0);                                          // Return true if the color of the edge between u and v does not equal 0
    }
    
    public int degree(int v) {                                               // Get the number of edges connected to the corner v
        int num = 0;                                                         // Initialize the number of edges to 0
        for(int i = 0; i < N; i++) {                                         // Go through each possible edge
            if(isEdge(v, i)) {                                               // If there is an edge
                ++num;                                                       // Increment number of edge
            }
        }
        return num;                                                          // Return the number of edges
    }
    
    public int degree(int v, int w) {                                        // Gets the number of edges connected to the corner v of color w
        int num = 0;                                                         // Initialize the number of edges to 0
        for(int i = 0; i < N; i++) {                                         // Go through each possible edge
            if((isEdge(v, i)) && (getEdge(v, i) == w)) {                     // If there is an edge, and the edge is of color w
                ++num;                                                       // Increment number of edges
            }
        }
        return num;                                                          // Return number of edges
    }
    
    public void printEdges() {                                               // Prints the graph
        for(int a = 0; a < N; a++) {                                         // Print out the header/ location (0, 1, 2, 3 etc)
            System.out.print("\t " + a);
        }
        System.out.println();                                                // Drop a line
        
        for(int b = 0; b < N; b++) {                                         // Go through each of the horizontal lines
            System.out.println("");                                          // Drop another line
            System.out.print(b + ":");                                       // Print the location
            
            for(int c = 0; c < N; c++) {                                     // Go through each of the individual elements
                if(isEdge(b, c)) {                                           // If there is an edge, print it
                    System.out.print("\t " + B[b][c]);
                } else {                                                     // If there is no edge, just skip ahead (using tab)
                    System.out.print("\t");
                }
            }
            
            System.out.println();                                            // Print another blank line
        }
    }
    
    public boolean isCycleOfLength(int n, int w) {                           // Returns true if there is a cycle of length n connecting edges of color w anywhere on the board
        for(int i = 0; i < N; i++) {                                         // Go through each of the possible edges
            for(int j = 0; j < N; j++) {
                if(getEdge(i, j) == w) {                                     // If the edge is of color w
                    boolean check = isCycleHelper(i, j, n, w, i);            // Call the helper and store its value in a bool
                    if(check) {                                              // If the helper returned true, it means we found a cycle, so return true
                        return true;
                    }
                }
            }
        }
        return false;                                                        // If we didn't return true by now it means we did not find a cycle
    } 
    
    private boolean isCycleHelper(int u, int v, int n, int w, int original) { // Helper taking the edge (connected from u-v), the size of the cycle, the color, and an original storing what corner we called this method from
        if(n <= 1 && v == original) {                                        // If we have made n moves (the number of edges connecting the cycle), and we're back at the original corner, return true because we have a cycle
            return true;
        } else if(n <= 1) {                                                  // If we have made n moves (number of edges connecting the cycle), but not returned to the original node, return false because we did not find a cycle 
            return false;
        } else {                                                             // If we haven't made n moves yet (still searching)
            for(int i = 0; i < N; i++) {                                     // Go through each of the possible edges connecting FROM v, which is the end corner of the edge that caleld this method
                if((getEdge(v, i) == w) && (i != u)) {                       // If the edge between these two are of the color we're looking for and it isn't connecting to itself
                    boolean check = isCycleHelper(v, i, --n, w, original);   // Check if there is an edge (recursivly, passing in the v and i as the edge (instead of u and v), and decrementing the n (number of edges connecting the cycle)
                    if(check) {                                              // If that check returned true, return true, because we found a cycle
                        return true;
                    }
                }
            }
        }
        return false;                                                        // If we made it here, it means we did not find a cycle, so return false
    }
    
    
    public static void main(String[] args) {
        Graph a = new Graph(6); // New game/graph with 6 verticies
        
        a.addEdge(5, 1, 1);  // Adding edge from 5___1
        a.addEdge(2, 3, -1); // Adding edge from 2---3
        a.addEdge(4, 5, 1);  // Adding edge from 4___5
        a.addEdge(1, 4, -1); // Adding edge from 1---4
        a.addEdge(1, 3, 1);  // Adding edge from 1___3
        a.addEdge(4, 3, -1); // Adding edge from 4---3
        a.addEdge(3, 5, 1);  // Adding edge from 3___5
        a.addEdge(5, 0, -1); // Adding edge from 5---0
        a.addEdge(0, 1, 1);  // Adding edge from 0___1
        a.addEdge(2, 5, -1); // Adding edge from 2---5
        a.addEdge(1, 2, 1);  // Adding edge from 1___2
        
        // Now there's a cycle of three (triangle) for player 1 from 1___3___5___1
        // There are no cycles for player -1
        System.out.println("Cycle of 3 for player 1 (should return true): " + a.isCycleOfLength(3, 1));
        System.out.println("Cycle of 3 for player -1 (should return false): " + a.isCycleOfLength(3, -1));
        
        a.removeEdge(3,5); // Removing edge from 3___5, this was for player 1
        
        // Now there are no cycles of three (triangle) for player 1, as 1___3xxx5___1
        System.out.println("Cycle of 3 for player 1 (should return false): " + a.isCycleOfLength(3, 1));
        
        System.out.println("Edge color between 3 and 1 (should be 1): " + a.getEdge(3,1)); // Color of the edge between 3___1
        
        System.out.println("Is there an edge between 3 and 0 (should return false): " + a.isEdge(3,0)); // Edge between 3xxx0
        System.out.println("Is there an edge between 2 and 3 (should return true): " + a.isEdge(2,3));  // Edge between 2---3
        
        System.out.println("Number of edges from 1 (should be 5): " + a.degree(1)); // Number of edges from corner 1
        
        System.out.println("Number of -1 (colored) edges from 1 (should be 1): " + a.degree(1, -1)); // Number of edges of color -1 from corner 1
        
        // Printing the entire document
        System.out.println("----\n\nPrinting board:\n----");
        a.printEdges();
    }
}