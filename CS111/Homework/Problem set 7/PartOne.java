/*
 * PartOne.java
 *
 * Computer Science 111, Boston University
 * 
 * A class that contains methods from Part I of PS 7.
 */

public class PartOne {
    public static void printSomething(int n) {
        if (n <= 0) {
            return;
        }
        
        printSomething(n - 1);
        System.out.println((char)('a' + n - 1));
    }
    
    public static int mystery(int a, int b) {
        if (a < 0) {
            return 1;
        } else {
            return 2 + mystery(a - b, b);
        }
    }
}