/*
 * Test program for PS 4, problem 4.
 *
 * Put this program in the same folder as your StringManipulator.java.
 *
 * If it doesn't compile, that means that one or more of your methods
 * does not have the correct header -- i.e., either the name, the 
 * return type, or the parameters are incorrect.
 *
 * The correct results to these method calls are given in the assignment.
 * 
 * We encourage you to add additional test cases.
 */

public class Test {
    public static void main(String[] args) {
        System.out.println("** part a **");
        StringManipulator.printWithSpaces("method");
        System.out.println();
        
        System.out.println("** part b **");
        StringManipulator.printStretched("method");
        System.out.println();
        
        System.out.println("** part c, example 1 **");
        char mid1 = StringManipulator.middleChar("clock");
        System.out.println(mid1);
        System.out.println();
        
        System.out.println("** part c, example 2 **");
        System.out.println(StringManipulator.middleChar("Boston"));
        System.out.println();
        
        System.out.println("** part d, example 1 **");
        String str1 = StringManipulator.removeSubstring("ding-a-ling", "ing");
        System.out.println(str1);
        System.out.println();
        
        System.out.println("** part d, example 2 **");
        String str2 = StringManipulator.removeSubstring("variable", "var");
        System.out.println(str2);
        System.out.println();
        
        System.out.println("** part d, example 3 **");
        System.out.println(StringManipulator.removeSubstring("Boston", "ten"));
        System.out.println();
    }
}