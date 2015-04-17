/*
 * Palindrome.java
 * 
 * Author: Cody Doucette <doucette@bu.edu>
 * Boston University CS 112, Summer I 2013
 * 
 * Purpose: This is an exercise in debugging by implementing a
 * palindrome-checker using a stack data structure.
 * 
 * Edited by: Aleksander Skjoelsvik
 */

import java.util.Stack;               // this is the built-in Java generic Stack

public class Palindrome {
    
    /**
     * palindrome: returns a boolean representing whether @str
     * is a palindrome. str should contain only alphanumeric
     * characters.
     */
    private static boolean palindrome(String str) {
        
        Stack<Character> s = new Stack<Character>();
        
        /* Push first half of str onto stack. */
        for (int i = 0; i < str.length() / 2; i++) {
            s.push(str.charAt(i));
        }
        
        int i = (str.length() / 2);   // Defining i outside loop so we can correct it if the length of string is odd
        if(str.length() % 2 == 1) {   // Checking if string length is odd
            i++;                      // Increment i if string length is odd
        }
        
        /* Pop second half of str from stack and compare. */
        for (; i < str.length(); i++) { //Run through loop from i to string length
            char c = s.pop();          //Pop off an item from the string and store in c
            if (c != str.charAt(i))    //Check if c is not equal to the item at i in str
                return false;          //return false (not a palindrome)
        }
        
        return true; 
    }   
    
    
    public static void main(String[] args) {
        
        System.out.println("Is redder a palindrome? Should be true:");
        System.out.println(palindrome("redder"));
        
        System.out.println("Is reddest a palindrome? Should be false:");
        System.out.println(palindrome("reddest"));
        
        System.out.println("Is ada a palindrome? Should be true:");
        System.out.println(palindrome("ada"));
        
    }
    
}