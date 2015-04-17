/*
 * StringRecursion.java
 *
 * starter code by: Computer Science 111, Boston University
 * 
 * modified by:
 * username:
 *
 * A class that contains recursive methods that operate on strings.
 */

public class StringRecursion {
    /* 
     * You may want to add test code for your methods to this
     * main method, although doing so is not required. 
     * See the section of the assignment entitled
     * "Testing your methods".
     * 
     * IMPORTANT: If your method does not return anything
     * (i.e., it is a void method), you should NOT try
     * to call it from within a println command. Instead,
     * you should call it on its own line -- for example:
     *     printSeries(3, 8);
     */
    public static void main(String[] args) {
        System.out.println("test 1 gives: " + numOccur('s', "Mississippi"));
        System.out.println("test 2 gives: " + numOccur('e', "Mississippi"));                   
        System.out.println("test 3 gives: " + removeVowels("Mississippi"));
        System.out.println("test 4 gives: " + removeVowels("apple"));                   
    }
    
    /*
     * numOccur - a recursive method that returns the number of times 
     * that the character ch occurs in the String str.
     * 
     * The main method includes two examples of using this method.
     *
     * You can also test this method by entering
     * StringRecursion.numOccur(ch, str) -- where ch is replaced 
     * by a char and str is replaced by a string -- in the 
     * Interactions Pane.
     */
    public static int numOccur(char ch, String str) {
        // base case
        if (str == null || str.equals("")) {
            return 0;
        }
        
        // recursive case
        int numOccurInRest = numOccur(ch, str.substring(1));
        if (ch == str.charAt(0)) {
            return 1 + numOccurInRest;
        } else {
            return numOccurInRest;
        }
    }
    
    /*
     * removeVowels - a recursive method that creates the String
     * that results from removing vowels from the String str.
     * 
     * The main method includes two examples of using this method.
     *
     * You can also test this method by entering
     * StringRecursion.removeVowels(str) -- where str is replaced
     * by a string -- in the Interactions Pane.
     * 
     * NOTE: There are examples of debugging printlns included below,
     * but they have been commented out.
     */
    public static String removeVowels(String str) {
        // System.out.println("starting removeVowels(" + str + ")");
        if (str == null || str.equals("")) {
            // System.out.println("removeVowels(" + str + ") returns " + str);
            return str;
        }
        
        String removedFromRest = removeVowels(str.substring(1));
        char first = str.charAt(0);
        
        // We assume that the string is all lowercase.
        if (first == 'a' || first == 'e' || first == 'i' || first == 'o' || first == 'u') {
            // System.out.println("removeVowels(" + str + ") returns " + removedFromRest);
            return removedFromRest;
        } else {
            // System.out.println("removeVowels(" + str + ") returns " + (first + removedFromRest));
            return first + removedFromRest;
        }
    }
    
    /*
     * ****** ADD YOUR METHODS BELOW **********
     *
     * Make sure to use the exact method headers
     * given in the assignment.
     */
    
    public static void printWithSpaces(String str) {
        if(str == null || str.equals("")) {
            System.out.println(str);
            return;
        } else {
            char character = str.charAt(0);
            str = str.substring(1);
            System.out.print(character + " ");
            printWithSpaces(str);
        }
    }
    
    public static void printDecreaseIncrease(String str) {
        if(str == null || str.equals("")) {
            return;
        } else {
            System.out.println(str);
            String str1 = str;
            str = str.substring(0, str.length()-1);
            
            printDecreaseIncrease(str);
            System.out.println(str1);
        }
    }
    
    public static String shiftChars(String str, int offset) {
        if(str == null || str.equals("")) {
            return str;
        } else {
            char shiftedChar = (char)(str.charAt(0) + offset);
            str = str.substring(1);
            return shiftedChar + shiftChars(str, offset);
        }
    }
    
    public static boolean endsWith(String str, String suffix) {
        
        if(str == null || suffix == null || str.length() == 0) {
            return false;
        }
        
        int lengthStr = str.length();
        int lengthSuffix = suffix.length();
      
        if(lengthSuffix == 0) {
            return true;
        } else if(str.charAt(lengthStr - 1) == suffix.charAt(lengthSuffix - 1)) {
            str = str.substring(0, lengthStr - 1);
            suffix = suffix.substring(0, lengthSuffix - 1);
            endsWith(str, suffix);
            return true;
        } else {
            return false;
        }
    }
    
    public static int indexOf(char ch, String str, int fromIndex){ //DOESNT WORK
        if(str == null || str.length() == 0 || fromIndex >= str.length()) {
            System.out.println("-1");
            return -1;
        } else if(fromIndex < 0) {
            throw new IllegalArgumentException("index cannot be negative");
        }
        
        
        if(str.charAt(fromIndex) == ch) {
            System.out.println(fromIndex);
            return fromIndex;
        } else {
            fromIndex++;
            indexOf(ch, str, fromIndex);
            return -1;
        }
       
    }
}