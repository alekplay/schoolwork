/**
 * LadderLength.java
 * Computer Science 111, Boston University
 * 
 * base code provided by the course staff
 * 
 * algorithm implemented by: Aleksander Skj¿lsvik
 * 
 * This program computes the necessary length of a ladder, given
 * the height of the point to be reached and the angle at which the
 * ladder will be positioned.
 */

import java.util.*;

public class LadderLength {
    public static void main(String[] args) {
        int height;     // the height to be reached in feet
        int angle;      // the angle at which the ladder will be positioned
        
        // Read the values from the user.
        Scanner console = new Scanner(System.in);
        System.out.print("Enter the height to the nearest foot: ");
        height = console.nextInt();
        System.out.print("Enter the angle to the nearest degree: ");
        angle = console.nextInt();      
        
        /*
         * The lines above read the height and angle from the user
         * and store them in the variables height and angle.
         * Fill in the rest of the program below, using those
         * variables to compute and print the length of the ladder.
         */

         double degrees = angle * Math.PI / 180;
         double lengthFeet = (double) height / Math.sin(degrees);
         double lengthYards = lengthFeet / 3;
         int lengthYf = (int) lengthYards;
         double lengthyF = (lengthYards - lengthYf) * 3;

         
         System.out.println("The required length is:");
         System.out.println(lengthFeet + " feet");
         System.out.println(lengthYards + " yards");
         System.out.println(lengthYf + " yards and " + lengthyF + " feet");
    }
}
