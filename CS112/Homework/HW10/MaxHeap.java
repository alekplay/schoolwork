/* 
 * MaxHeap.java
 *
 * A simple implementation of an array-backed max-heap.
 *
 * Authors: Wayne Snyder (snyder@cs.bu.edu)
 *          Alexander Breen (abreen@bu.edu)
 * Date: April 3, 2014
 * 
 * Modified by: Aleksander Skjoelsvik for homework 10 cs112 bu
 * I have not added comments as this is not my code. I simply added a node class to contain articles and their similarity, and change the code accordingly
 */ 

import java.util.*;

public class MaxHeap {
    
    private final int SIZE = 10;                 // initial length of array
    private int next = 0;                        // index of next item to be inserted
    
    private Node[] A = new Node[SIZE];           // The array is now an array of nodes
    
    public static class Node {                   // Node class, node contains the similarity and article
        Article a;
        double s;
         
        public Node(Article a, double s) {       // Node implementation class
            this.a = a;
            this.s = s;
        }     
    }
    
    /*
     * Utility methods
     */
    
    private void expand() {
        Node[] B = new Node[A.length * 2];     // Node array
        
        for (int i = 0; i < A.length; i++)
            B[i] = A[i];
        
        A = B; 
    }
    
    private static int parent(int i)     { return (i - 1) / 2; }
    private static int leftChild(int i)  { return 2 * i + 1; }
    private static int rightChild(int i) { return 2 * i + 2; }
    
    private boolean isLeaf(int i) { return leftChild(i) >= next; }
    private boolean isRoot(int i) { return i == 0; }
    
    private int maxChild(int i) {
        if (leftChild(i) >= next)
            return -1;
        
        if (rightChild(i) >= next)
            return leftChild(i);
        
        Node left = A[leftChild(i)];
        Node right = A[rightChild(i)];
        
        if (left.s > right.s)
            return leftChild(i);
        else
            return rightChild(i); 
    }
    
    private void swap(int i, int j) {
        Node temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    
    
    /*
     * Interface methods
     */
    
    /*
     * Returns true when the heap is empty.
     */
    public boolean isEmpty() {
        return next == 0;
    }
    
    
    /*
     * Returns the number of items in the heap.
     */
    public int size() {
        return next;
    }
    
    
    /*
     * Inserts an integer into the heap. This method will
     * insert the item into the next available array position
     * and shift up, swapping the next item while it is
     * greater than its parent.
     */
    public void insert(Article a, double s) {
        if (size() == A.length)
            expand(); 
        
        A[next] = new Node(a, s);                         // Create a new node using given article and similarity
        
        int i = next;
        while (!isRoot(i) && A[i].s > A[parent(i)].s) {   // Comparing nodes/articles using their similarity
            swap(i, parent(i));
            i = parent(i);
        }
        
        next++;
    }
    
    
    /*
     * Remove the maximum element from the heap by swapping it with the
     * last element in level order. This method sifts the new root down,
     * swapping it with its maximum child while it is still less than
     * its maximum child.
     */
    public Node removeMax() {
        next--;
        swap(0, next);
        
        int i = 0;
        while (!isLeaf(i) && A[i].s < A[maxChild(i)].s) {              // Comparing nodes/ articles using their similarity
            int m = maxChild(i);
            
            swap(i, m);     // swap i with its max child
            i = m;          // A[i] is now at A[m]; follow it from m
        }
        
        return A[next];
    }
    
    
    /*
     * Given an array, this method adds the elements of the array
     * into a max-heap and removes them in descending order into
     * a new array. The method returns the new, heapsorted array.
     *
     * A more space-efficient heapsort would "heapify" the elements
     * already in an array, then do the root removals on that
     * array, until the heap is empty. Then that array would be in
     * sorted order.
     */
    public static Node[] heapsort(Node[] arr) {
        MaxHeap h = new MaxHeap();
        
        for (int i = 0; i < arr.length; i++)
            h.insert(arr[i].a, arr[i].s);                // Insert article and similarity
        
        Node[] sorted = new Node[arr.length];            // Node array
        
        for (int i = sorted.length - 1; i >= 0; i--)
            sorted[i] = h.removeMax();
        
        return sorted;
    }
}