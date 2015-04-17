public class Node {
       public char item;
       public Node next;

       Node() {                 // this would be the default, put here for reference
          item = 0; 
          next = null;      
       } 

       Node(char n) {
          item = n;
          next = null;
       }

       Node(char n, Node p) {
          item = n;
          next = p;
       }
   };