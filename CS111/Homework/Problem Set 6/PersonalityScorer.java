/*
 * Problem set 6 - Part 2 - Task 1
 * Aleksander Skj¿lsvik 
 */

import java.util.*;
import java.io.*;

public class PersonalityScorer {
    public static void readFile(String filename) throws FileNotFoundException {
        Scanner file = new Scanner(new File(filename));
        
        while(file.hasNextLine()) {
            String name = file.nextLine();
            String answer = file.nextLine();
            
            char[] answers = answer.toCharArray();
            
            int[] aCounts = new int[4];
            int[] bCounts = new int[4];
            
            int countFirst = 0;
            int countSecond = 0;
            int countThird = 0;
            int countFourth = 0;
            
            calculateResult(answers, aCounts, bCounts, countFirst, countSecond, countThird, countFourth);
            printResult(aCounts, bCounts, name);
        }
        
    }
    
    
    public static void calculateResult(char[] answers, int[] aCounts, int[] bCounts, int countFirst, int countSecond, int countThird, int countFourth) {
        for(int i=0; i<answers.length; i++) {
            if(i - (7*countFirst) ==  0) {
                if(answers[i] == 'A') {
                    aCounts[0]++;
                } else if(answers[i] == 'B') {
                    bCounts[0]++;
                }
                countFirst++;
            } else if(i - (7*countSecond) == 1 || i - (7*countSecond) == 2) {
                if(answers[i] == 'A') {
                    aCounts[1]++;
                } else if(answers[i] == 'B') {
                    bCounts[1]++;
                }
                if(i - (7*countSecond) == 2) {
                    countSecond++;
                }
            } else if(i - (7*countThird) == 3 || i - (7*countThird) == 4) {
                if(answers[i] == 'A') {
                    aCounts[2]++;
                } else if(answers[i] == 'B') {
                    bCounts[2]++;
                }
                if(i - (7*countThird) == 4) {
                    countThird++;
                }
            } else if(i - (7*countFourth) == 5 || i - (7*countFourth) == 6) {
                if(answers[i] == 'A') {
                    aCounts[3]++;
                } else if(answers[i] == 'B') {
                    bCounts[3]++;
                }
                if(i - (7*countFourth) == 6) {
                    countFourth++;
                }
            }
        }
    }
    
    public static void printResult(int[] aCounts, int[] bCounts, String name) {
        String[] leftLetters = {"E", "S", "T", "J"};
        String[] rightLetters = {"I", "N", "F", "P"};
        String result = "";
        
        System.out.println(name);
        for(int l = 0; l<4; l++) {
            if(aCounts[l] > bCounts[l]) {
                result += leftLetters[l];
            } else if(aCounts[l] < bCounts[l]) {
                result += rightLetters[l];
            } else {
                result += "X";
            }
            
            System.out.print(aCounts[l] + "A-");
            System.out.print(bCounts[l] + "B ");
        }
        System.out.print("= " + result);
        System.out.println();
        System.out.println();
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the Personality Scorer");
        System.out.println("Please enter the name of the file wich contains the data");
        
        String filename = console.next();
        
        readFile(filename);
    }
}
