 /* File: Stackable.java
  * Date: 2/6/13
  * Author:  Wayne Snyder (snyder@bu.edu)
  * Class: CS 112, Spring 2013
  * Purpose: This is the interface for a standard generic linked-list stack, similar to p.149 in textbook
  * Related classes:   Stack.java
  */

public interface Stackable<Item>{

   public  boolean isEmpty(); 
   public Item top(); 
   public Item pop() throws StackUnderflowException;
   public void push(Item item); 

}



