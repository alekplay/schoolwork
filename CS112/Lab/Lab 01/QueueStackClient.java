/* CS112 - LAB 01 - January 27th 2014
 * Edited by: Aleksander Skjoelsvik  U54-90-4431
 * (The output isn't exactly the same, but he said it was ok)
 */

public class QueueStackClient {
    public static void main(String[] args) {
        IntQueueable Q = new IntQueue();                           // Referencing IntQueue by making variable Q
        IntStackable S = new IntStack();                           // Referencing IntStack by making variable S
        
        for(int k = 1; k < 10; ++k) {
            Q.enqueue(k);
        }
        Q.list();
        
        while(!Q.isEmptyArray1()) {
            S.push(Q.dequeue());
        } 
        
        while(!S.isEmptyArray2()) {
            Q.enqueue(S.pop());
        } 
        Q.list();          
    } 
}