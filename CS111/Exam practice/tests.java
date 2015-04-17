import java.util.*;

public class tests {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        
        System.out.println("How many grades? ");
        int maxNumGrades = console.nextInt();
        int[] grades = new int[maxNumGrades];
        
        int total = 0;
        int numGrades = 0;
        
        while(numGrades < maxNumGrades) {
            System.out.println("Enter a grade (or -1 to quit): ");
            grades[numGrades] = console.nextInt();
            if(grades[numGrades] == -1) {
                ended(maxNumGrades, numGrades, total, grades);
                break;
            }
            total += grades[numGrades];
            numGrades++;
        }
        
        result(maxNumGrades, numGrades, total, grades);
    }
    
    public static void ended(int maxNumGrades, int numGrades, int total, int[] grades) {
        System.out.println("You have chosen to end this program, however:");
    }
    
    public static void result(int maxNumGrades, int numGrades, int total, int[] grades) {
        System.out.println("These are your inputs");
        System.out.println("Number of grades: " + numGrades + " (" + maxNumGrades + ")");
        System.out.println("Total: " + total);
        System.out.print("The grades: ");
        for(int i = 0; i < grades.length; i++) {
            System.out.print(grades[i] + " ");
        }
    }
}