import java.util.*;

public class Test {
    public static void main(String[] args) {
        
       Scanner console = new Scanner(System.in);
    System.out.print("Enter three numbers: ");
    int a = console.nextInt();
    int b = console.nextInt();
    int c = console.nextInt();

    if (a > b) {
        if (b > c) {
            System.out.println("Spam Please!");
        } else {
            System.out.println("It's a late parrot!");
        }
    } else if (b > c) {
        System.out.println("Cheese Shoppe");
        if (a >= c) {
            System.out.println("Cheddar");
        }
        if (a < b) {
            System.out.println("Gouda");
        } else if (c == b) {
            System.out.println("Swiss");
        }
    } else {
        System.out.println("Trees");
        if (a == b) {
            System.out.println("Chestnut");
        } else {
            System.out.println("Larch");
        }
    }
    System.out.println("Done");

    }

}