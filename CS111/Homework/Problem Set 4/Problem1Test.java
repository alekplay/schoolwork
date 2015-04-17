/*
 ***** THIS FILE IS FOR TESTING PURPOSES ONLY. *****
 * 
 * Put the final version of your method for problem 1 
 * in your ps4_partI.txt file.
 * 
 * Do NOT submit it in this file.
 */

public class Problem1Test {
    /*** Put your method for problem 1b below ***/
    
    public static double discountPrice(double originalPrice, int percentDiscount) {
        double newPrice = originalPrice - (((double)percentDiscount/100)*originalPrice); 
        return newPrice;
    }
    
 
    /*
     * This main method includes several tests for your method.
     * 
     * It will not compile until you have added a correct definition
     * for the method above.
     * 
     * We encourage you to add additional tests as well.
     */ 
    public static void main(String[] args) {
        double price = 50.0;
        int discount = 10;
        System.out.println(" first price = " + discountPrice(price, discount));
        
        System.out.println("second price = " + discountPrice(7.50, 20));
        
        double priceWithTax = discountPrice(200, 15) * 1.05;
        System.out.println(" third price = " + priceWithTax);
    }
}