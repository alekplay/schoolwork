public class unitTest1 {
    public static void main(String[] args) {
        Graph a = new Graph(6); // New game/graph with 6 verticies
        
        a.addEdge(5, 1, 1);  // Adding edge from 5___1
        a.addEdge(2, 3, -1); // Adding edge from 2---3
        a.addEdge(4, 5, 1);  // Adding edge from 4___5
        a.addEdge(1, 4, -1); // Adding edge from 1---4
        a.addEdge(1, 3, 1);  // Adding edge from 1___3
        a.addEdge(4, 3, -1); // Adding edge from 4---3
        a.addEdge(3, 5, 1);  // Adding edge from 3___5
        a.addEdge(5, 0, -1);  // Adding edge from 5---0
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