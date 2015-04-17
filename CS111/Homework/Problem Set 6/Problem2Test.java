/*
 ***** THIS FILE IS FOR TESTING PURPOSES ONLY. *****
 * 
 * Put the final version of your method for problem 1 
 * in your ps6_partI.txt file.
 * 
 * Do NOT submit it in this file.
 */

import java.util.*;

public class Problem2Test {
    /* This class constant will be used by your method for problem 2b. */
    public static final String[] SCHOOL_ABBREVS = {"CAS", "CFA", "CGS",
        "COM", "ENG", "GMS", "GRS", "GSM", "LAW", "MED", "MET", "SAR", "SED",
        "SHA", "SMG", "SPH", "SSW", "STH"};
    
    /*** Put your methods for problem 2 below ***/
    
    public static boolean hasNegative(int[] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] < 0) {
                return true;
            }
        }
        return false;
    }
    
    
    /*
     * This main method includes several tests for your method.
     * 
     * It will not compile until you have added correct definitions
     * for all three of the methods in problem 2.
     * 
     * We encourage you to add additional tests as well.
     */ 
    public static void main(String[] args) {
        
        /* tests for problem 2c */
        int[] data3 = {2, 4, -6, 8, 10, 12};
        boolean hasNeg3 = hasNegative(data3);
        System.out.println(hasNeg3);              // should get true
        int[] data = {1, 2, 3, 4, 5};
        System.out.println(hasNegative(data));    // should get false
    }
}