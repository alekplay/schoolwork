/*
 * PersonClient.java
 *
 * A sample client program for the Person class
 * 
 * Do not open this file until you have completed parts g-i.
 */

public class PersonClient {
    public static void main(String[] args) {
        Person kate = new Person("Kate Winslet", new Date(10, 5, 1975));
        System.out.println("kate = " + kate);   // toString() will be called
        String name = kate.getName();
        System.out.println("name: " + name);
        Date dob = kate.getDOB();
        System.out.println(" dob: " + dob);
        System.out.println();
        
        Date birthday = kate.getBirthdayIn(2013);
        System.out.println("Her birthday this year is: " + birthday);
        System.out.println();
        
        Date july4 = new Date(7, 4, 2013);        
        Date thanksgiving = new Date(11, 28, 2013);
        int age = kate.getAgeOn(july4);
        System.out.println("On July 4th she was " + age + " years old.");
        System.out.println("On her birthday she was " + kate.getAgeOn(birthday) + ".");
        System.out.println("On Thanksgiving she will be " + kate.getAgeOn(thanksgiving) + ".");
    }
}