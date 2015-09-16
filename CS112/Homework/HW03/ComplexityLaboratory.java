/* ComplexityLaboratory - CS112 BU HW03 - SNYDER
 * Made by: Aleksander Skjoelsvik / Feb 13th 2014
 */

import java.util.Random;

public class ComplexityLaboratory {
    private static int counter;                                                  // Create counter variable of type int
    
    private static Integer[] generateRandomArray(int size) {                     // Create an array with size elements and fill it with random four-digit numbers
        Random R = new Random();                                                 // Initializes the random generator
        Integer[] A = new Integer[size];                                         // Create a new integer array of specified size
        for(int i = 0; i < A.length; i++) {                                      // For loop for as long as the array is to insert elements
            A[i] = R.nextInt(10000);                                             // Generate random number from 0-9999, adds it to current spot in array
        }
        return A;                                                                // Return array
    }
    
    /*
     * main 
     */
    
    public static void main(String[] args) {
        Grapher G = new Grapher("Comparison of Average Case Sort for Five Algorithms");      // Initialize the grapher with a title
        int[] A = new int[42];                                                   // Create arrays for the 5 different sort methods
        int[] B = new int[42];
        int[] C = new int[42];
        int[] D = new int[42];
        int[] E = new int[42];                                                   // ...
        int i = 0;                                                               // Int i to keep track of position in array
        for(int N = 0; N <= 100; N+=5) {                                         // For loop that runs 20 times to fill in array and do the sorts
            A[i] = N;                                                            // Assign N to the current element of the array, to each of the 5 arrays
            B[i] = N;
            C[i] = N;
            D[i] = N;
            E[i] = N;                                                            // ...
             
            counter = 0;                                                         // Reset the counter
            selectionSort(generateRandomArray(N));                               // Do the selectionSort with an array of size N with random elements
            A[i+1] = counter;                                                    // Add the counter (number of comparisons) to the next element in the array A
            
            counter = 0;                                                         // Reset the counter
            insertionSort(generateRandomArray(N));                               // Do the insertionSort with an array of size N with random elements
            B[i+1] = counter;                                                    // Add the counter (number of comparisons) to the next element in the array B
             
            counter = 0;                                                         // Reset the counter
            shellSort(generateRandomArray(N));                                   // Do the shellSort with an array of size N with random elements
            C[i+1] = counter;                                                    // Add the counter (number of comparisons) to the next element in the array C
             
            counter = 0;                                                         // Reset the counter
            mergeSort(generateRandomArray(N));                                   // Do the mergeSort with an array of size N with random elements
            D[i+1] = counter;                                                    // Add the counter (number of comparisons) to the next element in the array D
             
            counter = 0;                                                         // Reset the counter
            quickSort(generateRandomArray(N));                                   // Do the quickSort with an array of size N with random elements
            E[i+1] = counter;                                                    // Add the counter (number of comparisons) to the next element in the array E
            
            i+=2;                                                                // Increment i by two, so we're ready for the next "round" of sorts
        }
        G.drawCurve(A, "Selection Sort");                                        // Draw the graphs with the correct info from the appropriate array
        G.drawCurve(B, "Insertion Sort");                                   
        G.drawCurve(C, "Shell Sort");
        G.drawCurve(D, "Merge Sort");
        G.drawCurve(E, "Quick Sort");                                            // ....
    }
    
    /*
     * selectionSort - Got the code from the textbook website as linked to in the homework file. Changed names of methods
     */
    
    private static void selectionSort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i+1; j < N; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
    }
    
    /*
     * insertionSort - Got the code from the textbook website as linked to in the homework file. Changed names of methods
     */
    
    private static void insertionSort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }
    
    /*
     * shellSort - Got the code from the textbook website as linked to in the homework file. Changed names of methods
     */
    
    private static void shellSort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        
        while (h < N/3) h = 3*h + 1; 
        
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            h /= 3;
        }
    }
    
    /*
     * mergeSort - Got the code from the textbook website as linked to in the homework file. Changed names of methods
     */
    
    private static void mergeSort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        mergeSort(a, aux, 0, a.length-1);
    }
    
    private static void mergeSort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, aux, lo, mid);
        mergeSort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }
    
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }
        
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }
    
    /*
     * quickSort - Got the code from the textbook website as linked to in the homework file. Changed names of methods
     */
    
    private static void quickSort(Comparable[] a) {
        quickSort(a, 0, a.length - 1);
    }
    
    private static void quickSort(Comparable[] a, int lo, int hi) { 
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        quickSort(a, lo, j-1);
        quickSort(a, j+1, hi);
    }
    
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) { 
            while (less(a[++i], v))
                if (i == hi) break;
            
            while (less(v, a[--j]))
                if (j == lo) break;
            
            if (i >= j) break;
            
            exch(a, i, j);
        }
        
        exch(a, lo, j);
        
        return j;
    }
    
    /*
     * Helper methods - Got the code from the textbook website as linked to in the homework file.
     */
    
    private static boolean less(Comparable v, Comparable w) {
        ++counter;
        return (v.compareTo(w) < 0);
    }
    
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}