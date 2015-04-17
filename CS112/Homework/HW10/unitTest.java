/* Unit Test for Article Table
 * 
 * Used for homework 10
 * 
 * Aleksander Skjoelsvik
 * 
 * I basically used the database of articles, inserted 10 of them. Printed them out. Looked up something. Deleted it. Then looked it up again
 */

import java.util.*;

public class unitTest {
    public static void main(String[] args) {
        String dbPath = "articles/";
        DatabaseIterator db = setupDatabase(dbPath);
        ArticleTable L = new ArticleTable();
        
        Article[] A = getArticleList(db); 
        L.initialize(A);
        
        System.out.println("Printing 10 of " + L.numItems + " items");
        for(int i = 0; i < 10; i++) {
            Article a = L.next();
            System.out.println(a);
        }
        
        System.out.println();
        System.out.println("Resetting");
        System.out.println();
        
        L.reset();
        
        System.out.println();
        System.out.println("Has jewel before deletion: " + L.lookup("Jewel"));
        System.out.println();
        
        L.delete("Jewel");
        
        System.out.println("Printing 10 of " + L.numItems + "items");
        for(int i = 0; i < 10; i++) {
            Article a = L.next();
            System.out.println(a);
        }
        
        System.out.println();
        System.out.println("Has jewel after deletion: " + L.lookup("Jewel"));
    }
    
    private static DatabaseIterator setupDatabase(String path) {
        return new DatabaseIterator(path);
    }
    
    private static Article[] getArticleList(DatabaseIterator db) {
        
        // count how many articles are in the directory
        int count = db.getNumArticles(); 
        
        // now create array
        Article[] list = new Article[count];
        for(int i = 0; i < count; ++i)
            list[i] = db.next();
        
        // now shuffle the array: generate two random indices and swap
        // doing it 1M times should be enough!
        
        Random R = new Random(); 
        
        for(int i = 0; i < 1000000; ++i) {
            int j = R.nextInt(count);
            int k = R.nextInt(count);
            Article temp = list[j];
            list[j] = list[k];
            list[k] = temp; 
        }
        
        
        //   System.out.println("Database input: " + count + " articles.");
        return list; 
    }
}