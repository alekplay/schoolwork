/*
 * BirthdayCalculator - a program for determining the days on
 * which people's birthdays fall in various years, and their
 * ages in those years.
 * 
 * It is a client program of both the Date and Person classes.
 *  
 * Computer Science 111, Boston University
 * 
 * printBirthdays method completed by: Aleksander Skjoelsvik - alsk
 */

import java.util.*;

public class BirthdayCalculator {
    /*
     * getPeople - reads in the information (name and dob) 
     * for count different people using the specified Scanner 
     * and returns an array of Person objects containing
     * that information
     */
    public static Person[] getPeople(int count, Scanner console) {
        Person[] people = new Person[count];
        
        for (int i = 0; i < count; i++) {
            System.out.println("person " + (i + 1) + ": ");
            System.out.print("  name: ");
            String name = console.nextLine();
            
            System.out.print("  date of birth (m/d/yyyy): ");
            String dobString = console.nextLine();
            String[] dobComponents = dobString.split("/");
            int month = Integer.parseInt(dobComponents[0]);
            int day = Integer.parseInt(dobComponents[1]);
            int year = Integer.parseInt(dobComponents[2]);
            
            Date dob = new Date(month, day, year);
            people[i] = new Person(name, dob);
        }
        
        return people;
    }
    
    /*
     * printBirthdays - takes an array of Person objects and
     * a year as parameters, and prints the names of the people
     * in the array, along with their birthdays and ages in the
     * specified year.
     * 
     * See the sample runs for what this method's output
     * should look like.
     */
    public static void printBirthdays(Person[] people, int year) {
        for(int i = 0; i < people.length; i++) {
            Person currentPerson = people[i];
            String name = currentPerson.getName();
            Date dobNow = currentPerson.getBirthdayIn(year);
            int age = currentPerson.getAgeOn(dobNow);
            
            
            System.out.println(name + ": " + dobNow + " (" + age + " years old)");
        }
    }
    
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        
        /* Create a Date object for today. You don't need to understand this code.*/
        GregorianCalendar cal = new GregorianCalendar();
        Date today = new Date(cal.get(cal.MONTH) + 1, cal.get(cal.DAY_OF_MONTH), cal.get(cal.YEAR));
        
        System.out.println("Welcome to the Birthday Calculator!");
        System.out.println("Today is " + today + ".");
        System.out.println();
        
        // Get information about the people.
        System.out.print("How many people do you want to process? ");
        int numPeople = console.nextInt();
        console.nextLine();
        Person[] people = getPeople(numPeople, console);
        System.out.println();
        
        // Print out their birthdays this year.
        System.out.println("Here are their birthdays this year:");
        printBirthdays(people, today.getYear());
        System.out.println();
        
        // Print out their birthdays in some other year.
        System.out.print("Enter a year >= " + Date.MIN_YEAR + ": ");
        int otherYear = console.nextInt();
        System.out.println("Here are their birthdays in that year:");
        printBirthdays(people, otherYear);
    }
}  