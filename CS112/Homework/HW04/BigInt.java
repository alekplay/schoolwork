/* File: BigInt.java
 * Date: 2/15/14
 * Author:  Wayne Snyder (snyder@bu.edu)
 * Class: CS 112, Spring 2014
 * Purpose: This is the template for HW 04, a class which is a container for several static methods
 *          for adding arrays of integers which represent large integers.
 * Edited by: Aleksander Skjoelsvik / Feb 19th 2014
 */

public class BigInt  { 
    private static boolean recursiveOn = true; // Just a flag to test iterative and recursive adds
    // When a client uses add, it will be the recursive one.
    
    // Returns true if s is a non-empty string of characters of digits, e.g., '0', '1', ...., '9'
    public static boolean legalBigInt(String s) {
        if(s == null || s == "") {                                               // If the string is empty, return false
            return false;
        }
        
        for(int i = 0; i < s.length(); i++) {                                    // Go through each element in the string
            if(!Character.isDigit(s.charAt(i))) {                                // If the character at the current spot in the array is NOT a digit (integer) (using javas built in Character isDigit method):
                return false;                                                    // Return false.
            }
        }
        
        return true;                                                             // If nothing has been returned yet (which means every element is an integer), return true
    }
    
    // Turns a String of digits into a reversed array of Integers (one Integer = one digit)
    public static Integer[] stringToArray(String s) {  
        if(!legalBigInt(s)) {                                                    // If it isn't a string of arrays (using function above), return null
            return null;
        }
        
        Integer[] intArray = new Integer[s.length()];                           // Create new array of same length as string (each element is going to have their own spot)
        for(int i = 0; i < s.length(); i++) {                                   // Run through each element in the string
            char currentChar = s.charAt(s.length()-(i+1));                      // Convert the last element (decreasing) in the string into a character and store in variable
            intArray[i] = Character.getNumericValue(currentChar);               // Assign that character to the next spot in the array (increasing)
        }
        
        return intArray;                                                        // Return the array 
    }
    
    // Returns the string representation of a BigInt array
    public static String arrayToString(Integer[] A) {
        String s = "";                                                          // Create an empty string
        
        for(int i = A.length-1; i >= 0; i--) {                                  // Run through each element in the array, from end to start (decreasing)
            s += A[i];                                                          // Assign them to the next available spot in string (increasing)
        }
        
        return s;                                                               // Return the string
    }
    
    // Just a wrapper for testing both iterative and recursive methods
    public static Integer[] add(Integer[] A, Integer[] B) {
        if(recursiveOn)
            return addRecursive(A,B);
        else
            return addIterative(A,B); 
    }
    
    // Add big Integers A and B represented as reversed arrays of digits, and return the sum in same form; 
    // Assumes neither is empty array, since started as legal integers
    // This one is iterative, next one will be recursive
    private static Integer[] addIterative(Integer[] A, Integer[] B) {
        int size = 0;                                                           // Create a size variable, set it to 0 for now
        if(A.length > B.length) {                                               // If A is bigger than B
            size = A.length;                                                    // Set size to A's length
        } else {                                                                // If B is bigger than A
            size = B.length;                                                    // Set size to B's length
        }
        
        int carry = 0;                                                          // Define a variable carry, set it to 0 for now
        int sum;                                                                // Define a variable sum, don't assign it a value
        Integer[] intArray = new Integer[size];                                 // Create a new integer array with length of the longest passed array (A or B, whichever is biggest)
        
        for(int i = 0; i < intArray.length; i++) {                              // Run through the length of the new array (also length of biggest passed array)
            if(A.length > i && B.length > i) {                                  // If both A and B are greater than the current element of the loop (i)
                sum = A[i] + B[i] + carry;                                      // Add the value of current spot in A and B, and the carry, and assign it to the sum
            } else if(A.length <= i) {                                          // If not, but if A's length is equal to or smaller than the current element of the loop (no more elements in A)
                sum = B[i] + carry;                                             // Add the value of the current spot in B and carry, and assign it to sum 
            } else {                                                            // If not, which means B's length is equal to or smaller than the current element of the loop (no more elements in B)
                sum = A[i] + carry;                                             // Add the value of the current spot in A and carry, and assign it to sum
            }
            carry = 0;                                                          // Reset the carry to 0
            
            if(sum > 9) {                                                       // If the sum is greater than 9 (double digit requires seperate locations in the array)
                sum -= 10;                                                      // Decrease the sum by 10 (to get the last digit in the sum variable, for example 13-10 = 3, we want 3)
                carry = 1;                                                      // Set the carry to 1, because we're carrying over 1 to the next spot in the array
            }
            
            intArray[i] = sum;                                                  // Assign the sum to the current location in the new intArray
        }
         
        if(carry != 0) {                                                        // If there is a carry left over (after all the additions have been done and all elements from both arrays have been assigned)
            Integer[] intArray2 = new Integer[intArray.length + 1];             // Create a new integer array that's one row longer
            
            for(int i = 0; i < intArray.length; i++) {                          // Run through all the elements of the old array
                intArray2[i] = intArray[i];                                     // Assign them to the new array
            }
            
            intArray2[intArray.length] = carry;                                 // Add the carry to the last spot of the new array
            intArray = intArray2;                                               // Make the name intArray link to intArray2
        }
        
        return intArray;                                                        // Return the new array
    }
    
    // Add big ints A and B represented as arrays of digits, and returns the sum in same form; 
    // Assumes neither is empty array, since started as legal integers
    private static Integer[] addRecursive(Integer[] A, Integer[] B) {
        String array1 = arrayToString(A);                                       // Store the first array in a string, using arrayToString method
        String array2 = arrayToString(B);                                       // Store the second array in a string, using arrayToString method
        Integer[] intArray = stringToArray(addRecursiveHelper(array1, array2, 0)); //Call helper method with the two new strings, and 0 (to represent the carry). Convert the response to an array, and store in a new integer array
        return intArray;                                                        // Return the new integer array
    }
    
    private static String addRecursiveHelper(String A1, String A2, Integer carry) { // Helper method for addRecursive, taking two strings (representing integers) and an integer carry
        if((A1.equals("") || A1 == null) && (A2.equals("") || A2 == null)) {        // If both strings are empty
            if(carry != 0) {                                                        // And if there's a carry
                return carry + "";                                                  // Return the carry
            } else {                                                                // If there's no carry
                return "";                                                          // Return an empty string
            }
        } else if(A1.equals("") || A1 == null) {                                    // Else if only one string (A1) is empty
            if(carry != 0) {                                                        // And if there's a carry
                return (Integer.parseInt(A2) + carry) + "";                         // Add the integer value of the rest in the string plus the carry, convert it into string and return. Using javas built in parseInt function to convert string to int
            } else {                                                                // If there's no carry
                return A2;                                                          // Return a string consisting of the value of A2
            }
        } else if(A2.equals("") || A2 == null) {                                    // Else if only one string (A2) is empty
            if(carry != 0) {                                                        // And if there's a carry
                return (Integer.parseInt(A1) + carry) + "";                         // Add the integer value of the rest in the string plus the carry, convert it into string and return. Using javas built in parseInt function to convert string to int
            } else {                                                                // If there's no carry
                return A1;                                                          // Return a string consisting of the value of A1
            }
        }
                                                                                    // If none of the base cases above have been triggered
        Integer temp = Character.getNumericValue(A1.charAt(A1.length()-1));         // Store the numeric value of the last int in the first string, in a variable called temp. Using javas built in getNumericValue to convert char value to int
        Integer temp2 = Character.getNumericValue(A2.charAt(A2.length()-1));        // Store the numeric value of the last int in the second string, in a variable called temp. Using javas built in getNumericValue to convert char value to int
        Integer sum = temp + temp2 + carry;                                         // Calculate the sum of temp, temp2 and the carry and assign it to variable sum
        carry = 0;                                                                  // Reset the carry back to 0
        
        if(sum > 9) {                                                               // If the sum is greater than 9 (double digit requires seperate locations in the array)
            sum -= 10;                                                              // Decrease the sum by 10 (to get the last digit in the sum variable, for example 13-10 = 3, we want 3)
            carry = 1;                                                              // Set the carry to 1, because we're carrying over 1 to the next spot in the array
        }
        
        return addRecursiveHelper(A1.substring(0, A1.length()-1), A2.substring(0, A2.length()-1), carry) + sum; // Then call itself recursvily, taking away the last element of both strings and passing in the new carry. After that, add the sum to the string. Return this 
    }
    
    // Just a debugging print method for arrays
    private static void printArray(Integer[]A) {
        for(int i = 0; i < A.length; ++i)
            System.out.print(A[i]);
        System.out.println(); 
    }
    
    // Unit test
    public static void main(String [] args) {
        
        System.out.println("\nTesting legalBigInt....\n"); 
        
        System.out.println("Testing \"23\" .... should be \ntrue: \n" + legalBigInt("23")); 
        System.out.println(); 
        System.out.println("Testing \"\" ....  should be \nfalse: \n" + legalBigInt("")); 
        System.out.println(); 
        System.out.println("Testing \"-234\" ....  should be \nfalse: \n" + legalBigInt("-234"));
        System.out.println(); 
        System.out.println("Testing \"Hi!234\" ....  should be \nfalse: \n" + legalBigInt("Hi!234")); 
        System.out.println(); 
        System.out.println("Testing \"2312312312312312312312312398765433\" ....  should be \ntrue: \n" + legalBigInt("2312312312312312312312312398765433")); 
        System.out.println(); 
        
        System.out.println("\nTesting stringToArray....");    
        Integer[] A = {4, 3, 2, 1}; 
        
        System.out.println("\n              .... should be\n54321:");   
        A = stringToArray("12345"); 
        printArray(A); 
        System.out.println("\n              .... should be\n1:"); 
        A = stringToArray("1"); 
        printArray(A); 
        System.out.println("\n              .... should be\n555555554444444433333333222222211111111:"); 
        A = stringToArray("111111112222222333333334444444455555555"); 
        printArray(A);
        
        System.out.println("\nTesting arrayToString...."); 
        
        A = stringToArray("12345"); 
        System.out.println("\n           .... should be\n12345:");
        System.out.println(arrayToString(A) ); 
        System.out.println("\n           .... should be\n1:");
        A = stringToArray("1"); 
        System.out.println(arrayToString(A) ); 
        System.out.println("\n           .... should be\n11111222223333344444555556666677777888889999900000:");
        A = stringToArray("11111222223333344444555556666677777888889999900000"); 
        System.out.println(arrayToString(A) );
        
        System.out.println("\nTesting addIterative...."); 
        recursiveOn = false; 
        int i = 2, j = 1, k = i+j; 
        A = add(stringToArray(String.valueOf(i)), stringToArray(String.valueOf(j))); 
        System.out.println("\n" + i + " + " + j + " = \n" + k +":");
        System.out.println(arrayToString(A) );
        
        i = 2; j = 3; k = i+j;
        A = add(stringToArray(String.valueOf(i)), stringToArray(String.valueOf(j))); 
        System.out.println("\n" + i + " + " + j + " = \n" + k +":");
        System.out.println(arrayToString(A) ); 
        
        i = 2349474; j = 482723; k = i+j;
        A = add(stringToArray(String.valueOf(i)), stringToArray(String.valueOf(j))); 
        System.out.println("\n" + i + " + " + j + " = \n" + k +":");
        System.out.println(arrayToString(A) ); 
        
        i = 9999; j = 1; k = i+j;
        A = add(stringToArray(String.valueOf(i)), stringToArray(String.valueOf(j))); 
        System.out.println("\n" + i + " + " + j + " = \n" + k +":");
        System.out.println(arrayToString(A) ); 
        
        i = 99898; j = 0; k = i+j;
        A = add(stringToArray(String.valueOf(i)), stringToArray(String.valueOf(j))); 
        System.out.println("\n" + i + " + " + j + " = \n" + k +":");
        System.out.println(arrayToString(A) ); 
        
        j = 234947400; i = 482723; k = i+j;
        A = add(stringToArray(String.valueOf(i)), stringToArray(String.valueOf(j))); 
        System.out.println("\n" + i + " + " + j + " = \n" + k +":");
        System.out.println(arrayToString(A) ); 
        
        j = 9999; i = 1; k = i+j;
        A = add(stringToArray(String.valueOf(i)), stringToArray(String.valueOf(j))); 
        System.out.println("\n" + i + " + " + j + " = \n" + k +":");
        System.out.println(arrayToString(A) ); 
        
        j = 99898; i = 0; k = i+j;
        A = add(stringToArray(String.valueOf(i)), stringToArray(String.valueOf(j))); 
        System.out.println("\n" + i + " + " + j + " = \n" + k +":");
        System.out.println(arrayToString(A) );     
        
        A = add(stringToArray("987732099543217654287655"), stringToArray("1234512345123451234512345")); 
        System.out.println("\n987732099543217654287655 + 1234512345123451234512345 = \n2222244444666668888800000:");
        System.out.println(arrayToString(A) );   
        
        System.out.println("\nTesting addRecursive...."); 
        recursiveOn = true; 
        i = 2; j = 1; k = i+j; 
        A = add(stringToArray(String.valueOf(i)), stringToArray(String.valueOf(j))); 
        System.out.println("\n" + i + " + " + j + " = \n" + k +":");
        System.out.println(arrayToString(A) );
        
        i = 2; j = 3; k = i+j;
        A = add(stringToArray(String.valueOf(i)), stringToArray(String.valueOf(j))); 
        System.out.println("\n" + i + " + " + j + " = \n" + k +":");
        System.out.println(arrayToString(A) ); 
        
        i = 2349474; j = 482723; k = i+j;
        A = add(stringToArray(String.valueOf(i)), stringToArray(String.valueOf(j))); 
        System.out.println("\n" + i + " + " + j + " = \n" + k +":");
        System.out.println(arrayToString(A) ); 
        
        i = 9999; j = 1; k = i+j;
        A = add(stringToArray(String.valueOf(i)), stringToArray(String.valueOf(j))); 
        System.out.println("\n" + i + " + " + j + " = \n" + k +":");
        System.out.println(arrayToString(A) ); 
        
        i = 99898; j = 0; k = i+j;
        A = add(stringToArray(String.valueOf(i)), stringToArray(String.valueOf(j))); 
        System.out.println("\n" + i + " + " + j + " = \n" + k +":");
        System.out.println(arrayToString(A) ); 
        
        j = 234947400; i = 482723; k = i+j;
        A = add(stringToArray(String.valueOf(i)), stringToArray(String.valueOf(j))); 
        System.out.println("\n" + i + " + " + j + " = \n" + k +":");
        System.out.println(arrayToString(A) ); 
        
        j = 9999; i = 1; k = i+j;
        A = add(stringToArray(String.valueOf(i)), stringToArray(String.valueOf(j))); 
        System.out.println("\n" + i + " + " + j + " = \n" + k +":");
        System.out.println(arrayToString(A) ); 
        
        j = 99898; i = 0; k = i+j;
        A = add(stringToArray(String.valueOf(i)), stringToArray(String.valueOf(j))); 
        System.out.println("\n" + i + " + " + j + " = \n" + k +":");
        System.out.println(arrayToString(A) );     
        
        A = add(stringToArray("987732099543217654287655"), stringToArray("1234512345123451234512345")); 
        System.out.println("\n987732099543217654287655 + 1234512345123451234512345 = \n2222244444666668888800000:");
        System.out.println(arrayToString(A) );
    }
    
    
}