/* Homework 11 - CS112 - Spring 2014 - Prof. Snyder - BU
 * Player Class
 * Made by: Aleksander Skjoelsvik and Raphael Baysa
 */

public class Player {

    public Move chooseMove(Graph g) {                                                  // Picks the best move based on values returned by minMax
        int max = -100000;                                                             // Initializes the max variable to the worst possible outcome
        Move best = null;                                                              // This will contain the best move
        Move kill = null;                                                              // This will contain any valid move, if the only remaining move forces the computer to loose
        for(int i = 0; i < g.N; i++) {                                                 // Go through each possible edge
            for(int j = 0; j < g.N; j++) { 
                if((!g.isEdge(i, j)) && (i != j)) {                                    // If there isn't an edge here, nor does it connect to itself
                    kill = new Move(i, j);                                             // Set the kill-move (backup solution, valid move)
                    g.addEdge(j, i, -1);                                               // Add the current edge to the graph
                    if(!g.isCycleOfLength(3, -1)) {                                    // Check if we made a cycle, we don't want this
                        int val = minMax(g, 1, -100000, 100000);                       // Find the best possible outcome if we go through with this move and store it in val
                        if(val >= max) {                                               // If the value returned by the minmax function is greater than the best we have
                            best = new Move(i, j);                                     // Store the current move in a variable, this is our best move so far
                            max = val;                                                 // Update the max
                        }
                    }
                    g.removeEdge(i, j);                                                // Remove the edge after we have tested it
                }
            }
        }
        
        if(best == null) {                                                             // If the best is not initialized, this means any move we make will force the computer to loose
            best = kill;                                                               // Set the best move to the kill move, this will end the game but it is a valid move
        }
        return best;                                                                   // Return the move
    }
    
    public int eval(Graph G) {                                                         // Evaluate the current graph and give it a number based on the likelyhood of the computer (positive num) or player (negative num) to win
        if(G.isCycleOfLength(3, -1)) {                                                 // If the computer made a cycle, return the worst possible value for the computer
            return -100000;
        } else if (G.isCycleOfLength(3, 1)) {                                          // If the player made a cycle, return the best possible value for the computer
            return 100000;
        }
        
        int a = 0;                                                                     // Initialize the arrays that will store each player and the computers graph evaluations
        int b = 0; 
        
        for(int i = 0; i < G.N; i++) {                                                 // Go through each of the corners
            int first = G.degree(i, -1);                                               // Get the amount of red (computer) edges connected to that corner
            int second = G.degree(i, 1);                                               // Get the amount of blue (player) edges connected to that corner
            
            if(first > 1) {                                                            // If it is greater than one (more than one edge), add it to the variable
                a += first;
            }
            if(second > 1) {                                                           // ---""---
                b += second;
            }
        }
        
        return b - a;                                                                  // Return the difference (positiv value = good for comp | negative value = good for player)
    }
    
    private int minMax(Graph g, int depth, int alpha, int beta) {                      // Finds the best possible outcome given a specific graph, using alpha-beta pruning
        if(depth >= 5) {                                                               // If depth is 5, stop and evaluate the board
            return eval(g);
        } else if(depth % 2 == 0) {                                                    // We're at a max "node" (computer)
            int val = -100000;                                                         // This is the worst value we can get, we'll try to find something better
            for(int i = 0; i < g.N; i++) {                                             // Go through each of the possible edges
                for(int j = 0; j < g.N; j++) {
                    if((!g.isEdge(i, j)) && (i != j)) {                                // If that edge doesn't already exist nor is it connectd to itself
                        alpha = Math.max(alpha, val);                                  // Set the alpha to the biggest of alpha and the previous val
                        if(beta <= alpha) {                                            // If beta is smaller than or equal to alpha, stop, as there is no need to continue traversing. We can't get better values anyways
                            break;
                        }
                        
                        g.addEdge(i, j, -1);                                           // Add the edge
                        if(!g.isCycleOfLength(3, -1)) {                                // If we didn't just make a cycle (we don't want this)
                            val = Math.max(val, minMax(g, depth+1, alpha, beta));      // Get the value of the board if we would've picked this move (running iterativly through minmax), and compare it to the current best (max)
                        }
                        g.removeEdge(i, j);                                            // Remove the edge, we have tested it
                    }
                }
            }
            return val;                                                                // Return the value, which will be the best possible outcome of the board
        } else {                                                                       // Same as above, except this is for minimizing "nodes" (player)
            int val = 100000;
            for(int i = 0; i<(g.N); i++) {
                for(int j = 0; j<g.N; j++) {
                    if((!g.isEdge(i, j) && (i != j))) {
                        beta = Math.min(beta, val);                                    // Using beta instead and finding the lowest (this is best for the player)
                        if(beta <= alpha) { 
                            break;
                        }
                        
                        g.addEdge(i, j, 1);
                        if(!g.isCycleOfLength(3, 1)) {
                            val = Math.min(val, minMax(g, depth+1, alpha, beta));     // Finding the lowest instead
                        }
                        g.removeEdge(i, j);
                    }
                }
            }
            return val;
        }
    }
}