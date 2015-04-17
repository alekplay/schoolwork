/*
 * BlockLetterWriter.java
 * Aleksander Skj¿lsvik
 * alsk@bu.edu
 * This program prints out the phrase "DA DOO RON RON"
 */


public class BlockLetterWriter{
    //Class for writing the letter D
    public static void writeD() {
        System.out.println("-----");
        System.out.println("|    \\");
        System.out.println("|     |");
        System.out.println("|     |");
        System.out.println("|     |");
        System.out.println("|    /");
        System.out.println("-----");
    }
    
    //Class for writing A
    public static void writeA() {
        System.out.println("+-----+");
        System.out.println("|     |");
        System.out.println("|     |");
        System.out.println("+-----+");
        System.out.println("|     |");
        System.out.println("|     |");
        System.out.println("|     |");   
    }
    
    //Class for writing O
    public static void writeO() {
        System.out.println("+-----+");
        System.out.println("|     |");
        System.out.println("|     |");
        System.out.println("|     |");
        System.out.println("|     |");
        System.out.println("+-----+");
    }
    
    //Class for writing R
    public static void writeR() {
        System.out.println("+----");
        System.out.println("|    \\");
        System.out.println("|     |");
        System.out.println("|____/");
        System.out.println("|  \\");
        System.out.println("|    \\");
        System.out.println("|      \\");
    }
    
    //Class for writing N, using the previous letters
    public static void writeN() {
        System.out.println("|     |");
        System.out.println("|\\    |");
        System.out.println("| \\   |");
        System.out.println("|  \\  |");
        System.out.println("|   \\ |");
        System.out.println("|    \\|");
        System.out.println("|     |");
    }
    
    //Class for writing DA, using the previous letters
    public static void writeDA() {
        writeD();
        System.out.println("");
        writeA();
    }
    
    //Class for writing DOO, using the previous letters
    public static void writeDOO() {
        writeD();
        System.out.println("");
        writeO();
        System.out.println("");
        writeO();
    }
    
    //Class for writing RON, using the previous letters
    public static void writeRON() {
        writeR();
        System.out.println("");
        writeO();
        System.out.println("");
        writeN();
    }
    
    //Class for making the space between the words
    public static void writeSpace() {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
    
    //Main class, prints the entire thing using the other classes
    public static void main(String[] args) {
        writeDA();
        writeSpace();
        writeDOO();
        writeSpace();
        writeRON();
        writeSpace();
        writeRON();
    }
} 
