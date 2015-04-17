import java.util.*;

public class unitTest {
    public static void main(String[] args) {
        String dbPath = "articles/";
        DatabaseIterator db = setupDatabase(dbPath);
        BinaryTree L = new BinaryTree(); // replace this with the BinaryTree constructor instead 
        
        Article[] A = getArticleList(db); 
        L.initialize(A); // this should initialize the BinaryTree, analogous to how the DumbList initialize method works now.
        System.out.println("L.height() = " + L.height(L.root));

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