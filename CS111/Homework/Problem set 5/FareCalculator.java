/* Problem set 5 - Part II - Task 5
 * Aleksander Skj¿lsvik
 */

import java.util.*;

public class FareCalculator {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the MBTA Fare Calculator!");
        System.out.println("Ride types: ");
        System.out.println("1) Bus");
        System.out.println("2) Subway");
        System.out.println("3) Commuter Rail");
        System.out.println("");
        
        int rideType;
        int age;
        int zone = 0;
        String charlieCard = "n";
        int price;
        
        System.out.println("Enter the type of ride (1-3): ");
        rideType = console.nextInt();
        
        System.out.println("Enter the age of the rider: ");
        age = console.nextInt();

        
        if(rideType == 1) {
            if (age < 12) {
                price = 0;
            } else if (age > 11 && age < 19) {
                price = 75;
            } else if (age > 18 && age < 65) {
                if(charlieCard == "y") {
                    price = 150;
                } else {
                    price = 200;
                }
            } else {
                price = 75;
            }
        } else if (rideType == 2) {
            if(age < 12) {
                price = 0;
            } else if (age > 11 && age < 19) {
                price = 100;
            } else if (age > 18 && age < 65) {
                if(charlieCard == "y") {
                    price = 200;
                } else {
                    price = 250;
                }
            } else {
                price = 100;
            }
        } else {
            System.out.println("What zone (use 0 for zone 1A)? ");
            zone = console.nextInt();
            
            if(zone == 0) {
                price = 200;
            } else if((zone % 2) != 0) {
                price = 550 + (125 * (zone / 2));
            } else {
               price = (550 + (125 * (zone / 2))) - 75;
            }
            
            if(age > 64) {
                price = price / 2;
            } else if (age < 12) {
                price = 0;
            }
        }
        
        double dollarPrice = price / 100.0;
        System.out.println("The fare is: $" + dollarPrice);
        
    }
}