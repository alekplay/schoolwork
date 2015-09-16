/*
 * Problem Set 4 - Part II T 4
 * Aleksander Skj¿lsvik 
 * October 17th 2013
 */

public class StringManipulator {
    public static void printWithSpaces(String input) {
        for(int i=0; i<input.length(); i++) {
            System.out.print(input.charAt(i) + " ");
        }
        
    }
    
    public static void printStretched(String input) {
        for(int i=0; i<input.length(); i++) {
            for(int k=0; k<=i; k++) {
                System.out.print(input.charAt(i));
            }
        }
    }
    
    public static char middleChar(String input) {
        double charPlacement = input.length() / 2.0;
        int charOut = (int) Math.ceil(charPlacement);
        return input.charAt(charOut - 1);
    }
    
    public static String removeSubstring(String main, String remove) {
        return main.replaceFirst(remove, "");
    }
    
    public static void main(String[] args) {
        System.out.println(removeSubstring("ding-a-ling", "ing"));
    }
}