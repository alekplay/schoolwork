/*
 * DumbList.java
 * 
 * This is an unordered list of articles. Dumb, dumb, dumb. You will rewrite this as a binary tree. 
 *
 * Author: Alexander Breen (abreen@bu.edu) and Wayne Snyder (waysnyder@gmail.com)
 * Date: March 24, 2014
 * 
 * Edited by: Aleksander Skjoelsvik - U54904431
 */

public class DumbList {
   
   public static class Node {
      public Article data;
      public Node next;
      
      public Node(Article data, Node n) {
         this.data = data;
         this.next = n;
      }
      
      public Node(Article data) {
         this(data, null);
      }
   }
   
   public Node root = null;
   
   public void initialize(Article[] A) {
      for(int i = 0; i < A.length; ++i) 
         add(A[i]); 
   }
   
   public void add(Article a) {
      root = new Node(a, root); 
   } 
   
   
   public boolean member(Article a) {
      return (lookup(root,a.getTitle()) != null); 
   }
   
   public boolean member(String title) {
      return (lookup(root,title) != null); 
   }
   
   public Article lookup(String title) {
      Node n = lookup(root,title); 
      if(n != null)
         return n.data; 
      return null; 
   }
   
   private  Node lookup(Node t, String key) {
      if (t == null)
         return null;
      else if (key.compareTo(t.data.getTitle()) == 0) {
         return t;
      } else 
         return lookup(t.next,key); 
   }
   
   public  Node lookup(Node t, Article a) {
      if (t == null)
         return null;
      else if (a.getTitle().compareTo(t.data.getTitle()) == 0) {
         return t;
      } else 
         return lookup(t.next, a);
   }
   
   public void remove(String t) {
      root = delete(t, root); 
   }
  
   public int length() {

         return length(root); 
   }
   public int length(Node t) {
      if(t == null)
         return 0;
      else
         return 1 + length(t.next); 
   }
   
   // Recursively reconstructs tree without the key n in it
   
   public  Node delete(String title, Node t) {
      if (t == null)                             // Case 1: tree is null
         return t;
      else if (title.compareTo(t.data.getTitle()) == 0)  
         return t.next; 
      else {
         t.next = delete(title, t.next); 
         return t;
      }
   }
   
 
   
   
}
