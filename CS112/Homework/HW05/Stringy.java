/* File: Stringy.java
 * Date: 2/22/14
 * Author:  Wayne Snyder (snyder@bu.edu)
 * Class: CS 112, Fall 2014
 * Assignment:  HW 05
 * Purpose: This is an interface specifying a subset of the methods for the Java String class; these
 *       will be implemented using linked lists of characters. 
 * Related classes:  MyString.java
 */

public interface Stringy {
    
    public char charAt(int index);                     // Returns the char value at the specified index.
    
    public int compareTo(MyString anotherMyString);    // Compares two MyStrings lexicographically. It should return -1, 0, or 1 depending on whether
    //     this myString is less, equal, or greater than anotherMyString. 
    
    public MyString concat(MyString str);              //  Concatenates a copy of the specified MyString to the end of this MyString.
    //  If str is empty, just return this MyString; otherwise, make a copy
    //  of this MyString and append a copy of str to it. 
    
    public MyString replace(char oldChar, char newChar);  // if oldchar does not occur in this MyString, return this;
    // else make a copy of this MyString and replace all occurrences
    // of oldChar by newChar
    
    public int indexOf(char ch);                 // Returns the index within this MyString of the first occurrence of the specified character
    //  or -1 if it does not occur.
    
    public int indexOf(MyString str);           // Returns the index within this MyString of the first occurrence of the specified substring
    // or -1 if it does not occur.
    
    public int length();                        // Returns the length of this MyString.
    
    public MyString substring(int beginIndex, int endIndex);  // Returns a new MyString that is a substring of this string
    // the first index is inclusive and the second is exclusive, i.e., 
    //  substring(2, 4) of a MyString of "abcdefg" would be a Mystring of "cd" NOT "cde".
    
    // Java doesn't allow static methods in interfaces, but if it did, the two static methods you have
    // to implement would look like this:
    
    //  public static MyString valueOf(int i);  // Returns the MyString representation of the int argument; note that the digits should be
    // in their normal order (NOT reversed as in the last assignment).
    
    //  public static int parseInt(MyString s) throws NumberFormatException;  
    // Returns the integer corresponding to the MyString argument
    // or an exception if input is ill-formed (i.e., does not represent an integer); note that
    // the integer may be positive or negative or 0; don't worry about the case of "+234" which
    // is a legal integer in Java.  
}