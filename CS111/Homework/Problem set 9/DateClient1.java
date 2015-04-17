/*
 * DateClient1.java
 *
 * A sample client program for the Date class.
 * 
 * Do not open this file until you have completed parts a-e.
 */
public class DateClient1 {
    public static void main(String[] args) {
        /*** part b ***/
        /*** Note: These methods are static, so we call them using the class name. ***/
        boolean leap2010 = Date.isLeapYear(2010);
        System.out.println("is 2010 a leap year?: " + leap2010);
        System.out.println("is 1900 a leap year?: " + Date.isLeapYear(1900));
        System.out.println("is 2000 a leap year?: " + Date.isLeapYear(2000));
        System.out.println("is 2008 a leap year?: " + Date.isLeapYear(2008));
        System.out.println();
        int decDays = Date.numDaysInMonth(12, 2010);
        System.out.println("Dec. has " + decDays + " days");
        System.out.println("Feb. 2011 has " + Date.numDaysInMonth(2, 2011) + " days");
        System.out.println("Feb. 2012 has " + Date.numDaysInMonth(2, 2012) + " days");
        System.out.println();
        
        /*** parts c, d, and e ***/
        Date d = new Date(11, 28, 2013);
        System.out.println("Thanksgiving is on " + d);  // toString will be called
        int mon = d.getMonth();
        System.out.println("           month: " + mon);
        int day = d.getDay();
        System.out.println("             day: " + day);
        int year = d.getYear();
        System.out.println("            year: " + year);    
        System.out.println("      month name: " + d.monthName());    
        System.out.println("day of week name: " + d.dayOfWeekName());
        System.out.println();
        
        System.out.println("Creating three other Date objects:");
        Date d1 = new Date(7, 4, 1776);
        System.out.println("d1 = " + d1);
        Date d2 = new Date(9, 11, 2001);
        System.out.println("d2 = " + d2);
        Date d3 = new Date(1, 31, 2014);
        System.out.println("d3 = " + d3);
        System.out.println();
        
        // Try to create an invalid date.
        System.out.println("Trying to create a Date for February 30, 2012...");
        try {
            Date d4 = new Date(2, 30, 2012);
            System.out.println("The required exception was not thrown.");
        } catch(Exception e) {
            System.out.println("The required exception was thrown.");
        }
        System.out.println();
    }
}