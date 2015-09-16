/* CS 111 - Problem set 3
 * Part II - Task 5
 * Aleksander Skj¿lsvik
 */

public class MethodPractice {
    
    public static void printPattern(char ch, int numRows) {
        //This for loop runs the amount of times specified in "numRows"
        for(int i=1; i<=numRows; i++) {
            //This for loop runs the same amount of times as the number which the previous loop is on 
            for(int j = 1; j<=i; j++) {
                //Prints out the character specified in "ch"
                System.out.print(ch);
            }
            //New line
            System.out.print("\n");
        }
    }
    
    public static double triangleArea(double base, double height) {
        //Calculates the answer based on the formula base*height/2 and stores it in variable answer
        double answer = base * height / 2;
        //Returns answer 
        return answer;
    }
    
    public static void main(String[] args) {       
        //Runs function printPattern with different arguments
        printPattern('#', 4);
        System.out.println();
        printPattern('@', 7);
        System.out.println();
        printPattern('a', 10);
        System.out.println();
        printPattern('$', 5);
        System.out.println();
        
        int base = 10;
        int height = 6;
        //Prints result of function triangleArea with different arguments
        System.out.println("area1 = " + triangleArea(base, height));
        System.out.println("area2 = " + triangleArea(7, 3));
        System.out.println("area3 = " + triangleArea(23, 12));
        System.out.println("area4 = " + triangleArea(45, 67));
        
    }
}