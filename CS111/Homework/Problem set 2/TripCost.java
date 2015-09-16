/**
 * TripCost.java
 * Computer Science 111, Boston University
 * 
 * base code provided by the course staff
 * 
 * algorithm implemented by: Aleksander Skj¿lsvik
 * 
 * This program computes the cost of a trip, based on
 * the relevant values.
 */

import java.util.*;

public class TripCost {
    public static void main(String[] args) {
        int gasPrice;       // price of a gallon of gas in cents
        int epaRating;      // EPA mileage rating
        int distance;       // distance travelled to the nearest mile

        // Read the values from the user.
        Scanner console = new Scanner(System.in);
        System.out.print("Enter the gas price in cents: ");
        gasPrice = console.nextInt();
        System.out.print("Enter the car's EPA mileage rating: ");
        epaRating = console.nextInt();
        System.out.print("Enter the distance travelled (to the nearest mile): ");
        distance = console.nextInt();
        
        /*
         * The lines above read the gas price, EPA rating, and distance 
         * from the user and store them in variables.
         * Fill in the rest of the program below, using those
         * variables to compute and print the cost of the trip,
         * as specified in the assignment.
         */
        
        double price100 = (double) gasPrice / 100;
        double distanceEpa = (double) distance / epaRating;
        double cost = price100 * distanceEpa;
        
        System.out.print("The cost of the trip is $");
        if((cost % 1) == 0) {
           System.out.print((int) cost);
        } else {
           System.out.print(cost);
        }
   
    }
}