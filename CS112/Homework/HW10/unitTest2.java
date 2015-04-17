/* Unit Test for Term Frequency Table
 * 
 * Used for homework 10
 * 
 * Aleksander Skjoelsvik
 * 
 * I basically inserted a three different things: 
 *      First two documents with the exact same text, but multiple words - returned 1
 *      Then two documents with the exact same text and only one word - returned 1
 *      Then two documents with totally differnt texts and multiple words - returned 0
 * Then I printed the similarity
 */

public class unitTest2 {
    public static void main(String[] args) {
        TermFrequencyTable t = new TermFrequencyTable();
        
        t.insert("I", 0);
        t.insert("have", 0); // 1
        /*t.insert("have", 0); // 2
        t.insert("have", 0); // 3
        t.insert("have", 0); // 4
        t.insert("have", 0); // 5
        t.insert("have", 0); // 6
        t.insert("have", 0); // 7
        t.insert("have", 0); // 8
        t.insert("have", 0); // 9
        t.insert("have", 0); // 10
        t.insert("have", 0); // 11
        t.insert("have", 0); // 12
        t.insert("have", 0); // 13
        t.insert("have", 0); // 14
        t.insert("have", 0); // 15
        t.insert("have", 0); // 16
        t.insert("have", 0); // 17
        t.insert("have", 0); // 18
        */t.insert("a", 0);
        t.insert("dog", 0);
        t.insert(".", 0);
        t.insert("I", 0);
        t.insert("really", 0);
        t.insert("like", 0);
        t.insert("my", 0);
        t.insert("dog", 0);
        t.insert(".", 0);
        t.insert("He", 0);
        t.insert("is", 0);
        t.insert("a", 0);
        t.insert("very", 0);
        t.insert("nice", 0);
        t.insert("dog", 0);
        
        t.insert("I", 1);
        t.insert("have", 1); // 1
        /*t.insert("have", 1); // 2
        t.insert("have", 1); // 3
        t.insert("have", 1); // 4
        t.insert("have", 1); // 5
        t.insert("have", 1); // 6
        t.insert("have", 1); // 7
        t.insert("have", 1); // 8
        t.insert("have", 1); // 9
        t.insert("have", 1); // 10
        t.insert("have", 1); // 11
        t.insert("have", 1); // 12
        t.insert("have", 1); // 13
        t.insert("have", 1); // 14
        t.insert("have", 1); // 15
        t.insert("have", 1); // 16
        t.insert("have", 1); // 17
        t.insert("have", 1); // 18
        */t.insert("a", 1);
        t.insert("dog", 1);
        t.insert(".", 1);
        t.insert("I", 1);
        t.insert("really", 1);
        t.insert("like", 1);
        t.insert("my", 1);
        t.insert("dog", 1);
        t.insert(".", 1);
        t.insert("He", 1);
        t.insert("is", 1);
        t.insert("a", 1);
        t.insert("very", 1);
        t.insert("nice", 1);
        t.insert("dog", 1);
        
        /*t.insert("two", 0);
        t.insert("bad", 0);
        t.insert("nighs", 0);
        t.insert("are", 0);
        t.insert("terrible", 0);
        t.insert("extremely", 0);
        t.insert("awful", 0);
        
        t.insert("one", 1);
        t.insert("nice", 1);
        t.insert("day", 1);
        t.insert("is", 1);
        t.insert("good", 1);
        t.insert("very", 1);
        t.insert("delightful", 1);*/
        
        System.out.println("cosineSimilarity: " + t.cosineSimilarity());
    }
}