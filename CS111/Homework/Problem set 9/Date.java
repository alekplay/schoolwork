/*
 * Date.java - a blueprint class for objects that represent
 * an individual date.
 * 
 * starter code by: Computer Science 111, Boston University
 * 
 * completed by: Aleksander Skjoelsvik
 */

public class Date {
    /* a class constant for the smallest allowed year */
    public static final int MIN_YEAR = 1583;    
    
    /*
     * A class-constant array containing the names of the months.
     * The positions of the names in the array correspond to the 
     * numbers of the months. For example, "January" is at position 1
     * in the array because its month number is 1, and "December" is in 
     * position 12 because its month number is 12.
     * The string "none" appears in position 0, because there 
     * is no month that corresponds to the number 0.
     */
    public static final String[] MONTHS = {"none", "January", "February",
        "March", "April", "May", "June", "July", "August",
        "September", "October", "November", "December"};
    
    /*
     * A class-constant array containing the number of days in each
     * month. Here again, the positions of the values correspond to the 
     * numbers of the months. For example, NUM_DAYS[1] is 31, because
     * January (month 1) has 31 days, and NUM_DAYS[2] is 28, because
     * February usually has 28 days.
     * -1 appears in position 0, because there is no month that 
     * corresponds to the number 0.
     */
    public static final int[] NUM_DAYS = {-1, 31, 28, 31, 30, 31, 30,
        31, 31, 30, 31, 30, 31};
    
    /*
     * A class-constant array containing the names of the days of the week.
     */
    public static final String[] DAYS_OF_WEEK = {"Sunday", "Monday",
        "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    
    /*
     * dayOfWeekNumber - a static helper method that takes a month number, 
     * day number, and year as parameters, and returns the *number* of the
     * day of the week on which the corresponding date falls:
     * 0 for Sunday, 1 for Monday, 2 for Tuesday, etc.
     * The algorithm for computing the appropriate number comes from 
     * Robert Sedgewick and Kevin Wayne.
     */
    public static int dayOfWeekNumber(int month, int day, int year) {
        int y0 = year - (14 - month)/12;
        int x = y0 + y0/4 - y0/100 + y0/400;
        int m0 = month + 12*((14 - month)/12) - 2;
        
        return (day + x + (31*m0)/12) % 7;
    }    
    
    /* 
     * Implement the rest of the class below,
     * as specified in the assignment.
     */
    
    public static Boolean isLeapYear(int year) {
        if((year % 4) == 0) {
            if(((year % 100) == 0) && ((year % 400) != 0)) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    
    public static int numDaysInMonth(int month, int year) {
        int numDays = NUM_DAYS[month];
        if((numDays == 28) && (isLeapYear(year) == true)) {
            numDays++;
        }
        return numDays;
    }
    
    private int month;
    private int day;
    private int year;
    
    public Date(int month, int day, int year) {
        if(year < MIN_YEAR) {
            throw new IllegalArgumentException("The year must be after 1583");
        }
        if((month < 1) || (month > 12)) {
            throw new IllegalArgumentException("The month must be between 1 and 12");
        }
        if((day < 1) || (day > numDaysInMonth(month, year))) {
            throw new IllegalArgumentException("The day must be between 1 and " + numDaysInMonth(month, year));
        }
        
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    public int getMonth() {
        return this.month;
    }
    
    public int getDay() {
        return this.day;
    }
    
    public int getYear() {
        return this.year;
    }
    
    public String monthName() {
        return MONTHS[this.month];
    }
    
    public String dayOfWeekName() {
        int dayOfWeekNumber = dayOfWeekNumber(this.month, this.day, this.year);
        return DAYS_OF_WEEK[dayOfWeekNumber];
    }
    
    public String toString() {
        return dayOfWeekName() + ", " + monthName() + " " + getDay() + ", " + getYear();
    }
    
    public Boolean equals(Date date) {
        if((date.day == this.day) && (date.month == this.month) && (date.year == this.year)) {
            return true;
        } else {
            return false;
        }
    }
    
    public Boolean isBefore(Date date) {
        if(this.year < date.year) {
            return true;
        }
        if((this.month < date.month) && (this.year == date.year)) {
            return true;
        }
        if((this.day < date.day) && (this.month == date.month) && (this.year == date.year)) {
            return true;
        }
        
        return false;
    }
    
    public Boolean isAfter(Date date) {
        if((isBefore(date) == false) && (this.year != date.year) && (this.month != date.month) && (this.day != date.day)) {
            return true;
        }
        
        return false;
    }
    
    
}