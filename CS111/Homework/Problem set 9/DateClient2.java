/*
 * DateClient2.java
 *
 * A sample client program for the Date class
 * 
 * Do not open this file until you have completed parts a-f.
 */
public class DateClient2 {
    public static void main(String[] args) {
        Date d1 = new Date(7, 4, 1776);
        System.out.println("d1 = " + d1);
        Date d2 = new Date(9, 11, 2001);
        System.out.println("d2 = " + d2);
        Date d3 = new Date(7, 4, 2012);
        System.out.println("d3 = " + d3);
        Date d4 = new Date(7, 4, 2012);
        System.out.println("d4 = " + d4);
        Date d5 = new Date(9, 1, 2012);
        System.out.println("d5 = " + d5);
        System.out.println();
        
        boolean d1d2 = d1.equals(d2);
        System.out.println("d1.equals(d2)   = " + d1d2);
        System.out.println("d1.equals(d3)   = " + d1.equals(d3));
        System.out.println("d3.equals(d4)   = " + d3.equals(d4));
        System.out.println("d4.equals(d5)   = " + d4.equals(d5));
        System.out.println();
        
        d1d2 = d1.isBefore(d2);
        System.out.println("d1.isBefore(d2) = " + d1d2);
        System.out.println("d2.isBefore(d1) = " + d2.isBefore(d1));
        System.out.println("d2.isBefore(d3) = " + d2.isBefore(d3));
        System.out.println("d4.isBefore(d4) = " + d4.isBefore(d4));
        System.out.println("d4.isBefore(d5) = " + d4.isBefore(d5));
        System.out.println();
        
        d1d2 = d1.isAfter(d2);
        System.out.println("d1.isAfter(d2)  = " + d1.isAfter(d2));
        System.out.println("d2.isAfter(d1)  = " + d2.isAfter(d1));
        System.out.println("d2.isAfter(d3) = " + d2.isAfter(d3));
        System.out.println("d4.isAfter(d4) = " + d4.isAfter(d4));
        System.out.println("d4.isAfter(d5) = " + d4.isAfter(d5));
    }
}