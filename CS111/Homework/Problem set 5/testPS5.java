import java.util.*;
import java.io.*;

public class testPS5 {
    public static void main (String[] args) {
        File f = new File("problem4.txt");
        Scanner input = new Scanner(f);
        int count = 0;
        while (input.hasNext()) {
            System.out.println(input.next());
            count++;
        }
        System.out.println("count: " + count);
    }
    
}