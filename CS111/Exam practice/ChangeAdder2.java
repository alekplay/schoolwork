public class ChangeAdder2 {
    public static void main(String[] args) {
        int quarters = 10;
        int dimes = 3;
        int nickels = 7;
        int pennies = 6;
        int cents;
        
        cents = 25*quarters + 10*dimes + 5*nickels + pennies;
        System.out.println("Total in cents is: " + cents);
        double dollars = cents / 100;
        System.out.println("Total in dollars is: $" + dollars);
    }
}