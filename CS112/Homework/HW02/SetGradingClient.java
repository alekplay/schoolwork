/* Program: SetGradingClient.java
 * Author: Wayne Snyder
 * Date: 1/30/14
 * Class: CS 112
 * Purpose: This is the grading client to test Set.java, Problem B.2 in HW 02
 */

public class SetGradingClient {
   
   public static void printSet(Set S) {
      S.reset();
      System.out.print("{ ");
      while(S.hasNext()) {
         System.out.print(S.next());
         if(S.hasNext())
            System.out.print(", ");
         else
            System.out.print(" ");
      }
      System.out.println("}"); 
   }
   
   public void printlnSet(Set S) {
      printSet(S);
      System.out.println();
   }
   
   // Unit Test
   public static void main(String [] args) {
      Integer [] A = { 10, 3, 6 };
      Set<Integer> S = new Set<Integer>(A); 
      
      Set<Integer> T = new Set<Integer>(); 
      T.insert(2); 
      T.insert(3);
      T.insert(0);
      
      Integer [] B = { 8, 0, 9 }; 
      Set<Integer> U = new Set<Integer>(B); 
      
      Integer [] C = { 3, 6 }; 
      Set<Integer> V = new Set<Integer>(C); 
      Set<Integer> W = new Set<Integer>(); 
      W.insert(6);
      W.insert(3);
      
      Set<Integer> X = new Set<Integer>(); 
      
      System.out.println("The sets in the following pairs should match (order does not matter):\n");
      System.out.print("S: { 10, 3, 6 }\nS: ");
      printSet(S); 
      System.out.print("\nT: { 2, 3, 0 }\nT: ");
      printSet(T); 
      System.out.print("\nU: { 8, 0, 9 }\nU: ");
      printSet(U); 
      System.out.print("\nV: { 3, 6 }\nV: ");
      printSet(V);
      System.out.print("\nW: { 6, 3 }\nW: ");
      printSet(W);
      System.out.print("\nX: { }\nX: ");
      printSet(X);
      
      System.out.print("\nS union T: { 10, 3, 6, 2, 0 }\nS union T: ");
      printSet(S.union(T)); 
      System.out.print("\nS union V: { 10, 3, 6 }\nS union V: ");
      printSet(S.union(V));
      System.out.print("\nS intersect T: { 3 }\nS intersect T: ");
      printSet(S.intersection(T));
      System.out.print("\nS intersect U: { }\nS intersect U: ");
      printSet(S.intersection(U));
      System.out.print("\nU difference T: { 8, 9 }\nU difference T: " );
      printSet(U.difference(T));
      System.out.print("\nT difference U: { 2, 3 }\nT difference U: " );
      printSet(T.difference(U));
      
      System.out.print("\nW subset T? false\nV subset T? " );
      System.out.println( W.subset(T));
      System.out.print("\nW subset S? true\nV subset S? " );
      System.out.println(W.subset(S));
      System.out.print("\nX subset U? true\nX subset U? " );
      System.out.println(X.subset(U));
      
      System.out.print("\nX equalTo V? false\nX equalTo V? " );
      System.out.println(X.equals(V));
      System.out.print("\nW equalTo V? true\nW equalTo V? " );
      System.out.println(W.equals(V));
      System.out.print("\nS symmetric-difference T = (S difference T) union (T difference S): { 10, 6, 2, 0 }\nS symmetric-difference T = (S difference T) union (T difference S): " );
      printSet(S.difference(T).union(T.difference(S)));
      System.out.print("\n(S.difference V) subset S: true\n(S.difference V) subset S: " );
      System.out.println(S.difference(V).subset(S));
      System.out.print("\nS union T union U union V union W union X: { 10, 3, 6, 2, 0, 8, 9 }\nS union T union U union V union W union X: " );
      printSet(((((S.union(T)).union(U)).union(V)).union(W)).union(X));
      
      
   } 
   
}