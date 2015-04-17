/* MiniGoogle.java
 * 
 * Problem four | Homework 10 | CS112 | prof. Snyder
 * 
 * Made by: Aleksander Skjoelsvik - U54904431
 * 
 * Thu April 17th.
 * 
 * Based on: Minipedia.java
 * 
 * I did not add comments for the code that is not mine.
 * 
 * I am using a modified version of the MaxHeap code found on Alex' site. I uploaded that as well.
 */

import java.util.*;

public class MiniGoogle {
    
    private static int numShuffles = 1000000;
    
    private static Article[] getArticleList(DatabaseIterator db) {
        
        // Count how many articles are in the directory
        int count = db.getNumArticles(); 
        
        // Create array
        Article[] list = new Article[count];
        for(int i = 0; i < count; ++i){
            list[i] = db.next();
        }
        
        // Shuffle the array: generate two random indices and swap. Doing it 1M times should be enough!
        Random R = new Random(); 
        for(int i = 0; i < numShuffles; ++i) {
            int j = R.nextInt(count);
            int k = R.nextInt(count);
            Article temp = list[j];
            list[j] = list[k];
            list[k] = temp; 
        }
        
        return list; 
    }
    
    
    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);
        
        String dbPath = "articles/";
        
        DatabaseIterator db = setupDatabase(dbPath);
        
        System.out.println("Read " + db.getNumArticles() + " articles from disk.");
        
        ArticleTable L = new ArticleTable();                        // Using ArticleTable instead of BinaryTree!
        Article[] A = getArticleList(db);
        L.initialize(A);
        
        System.out.println("Created in-memory hash-table.");
        
        int choice = -1;
        do {
            System.out.println();
            System.out.println("Welcome to Minipedia!");
            System.out.println("=====================");
            System.out.println("Make a selection from the " + "following options:");
            System.out.println();
            System.out.println("Manipulating the database");
            System.out.println("-------------------------");
            System.out.println("    1. add a new article");
            System.out.println("    2. remove an article");
            System.out.println();
            System.out.println("Searching the database");
            System.out.println("----------------------");
            System.out.println("    3. search by exact article title");
            System.out.println("    4. not implemented");
            System.out.println("    5. phrase search in article content");
            System.out.println();
            
            System.out.print("Enter a selection (1-4, or 0 to quit): ");
            
            choice = user.nextInt();
            user.nextLine();
            
            switch (choice) {
                case 0:
                    return;
                    
                case 1:
                    addArticle(user, L);
                    break;
                    
                case 2:
                    removeArticle(user, L);
                    break;
                    
                case 3:
                    titleSearch(user, L);
                    break;
                    
                case 4:
                    titleContentSearch(user, L);
                    break;
                case 5:
                    articleContentSearch(user, L);
                    break;
                    
                    
                    
                default:
                    break;
            }
            
            choice = -1;
            
        } while (choice < 0 || choice > 6);
        
    }
    
    
    private static DatabaseIterator setupDatabase(String path) {
        return new DatabaseIterator(path);
    }
    
    private static void addArticle(Scanner s, ArticleTable D) {             // Using ArticleTable instead of BinaryTree!
        System.out.println();
        System.out.println("Add an article");
        System.out.println("==============");
        
        System.out.print("Enter article title: ");
        String title = s.nextLine();
        
        System.out.println("You may now enter the body of the article.");
        System.out.println("Press return two times when you are done.");
        
        String body = "";
        String line = "";
        do {
            line = s.nextLine();
            body += line + "\n";
        } while (!line.equals(""));
        
        D.insert(new Article(title, body));                                   // Add the new article
    }
    
    
    private static void removeArticle(Scanner s, ArticleTable D) {            // Using ArticleTable instead of BinaryTree!
        System.out.println();
        System.out.println("Remove an article");
        System.out.println("=================");
        
        System.out.print("Enter article title: ");
        String title = s.nextLine();
        
        
        D.delete(title);                                                    // Remove object with the passed title
    }
    
    
    private static void titleSearch(Scanner s, ArticleTable D) {              // Using ArticleTable instead of BinaryTree!
        System.out.println();
        System.out.println("Search by article title");
        System.out.println("=======================");
        
        System.out.print("Enter article title: ");
        String title = s.nextLine();
        
        Article a = D.lookup(title);                                       // Lookup an article based on the exact title
        if(a != null)
            System.out.println(a);
        else {
            System.out.println("Article not found!"); 
            return; 
        }
        
        System.out.println("Press return when finished reading.");
        s.nextLine();
    }
    
    
    private static void articleContentSearch(Scanner s, ArticleTable D) {   // Using ArticleTable instead of BinaryTree!
        System.out.println();
        System.out.println("Search by article content");
        System.out.println("=========================");
        
        System.out.print("Enter keyword: ");
        String phrase = s.nextLine();
        
        MaxHeap heap = new MaxHeap();                                       // Create a new maxHeap, this will be used to store the search results
        
        D.reset();                                                          // Reset the pointer
        while(D.hasNext()) {                                                // Go through each article in the hashtable
            Article q = D.next();                                           // Store the article in q
            double similarity = getCosineSimilarity(phrase, q.getBody());   // Get the similarity between the phrase and the body of the article
            
            if(similarity > 0) {                                            // If the similarity is greater than 0 (there are some similar words)
                heap.insert(q, similarity);                                 // Insert the article and similarity"score" into the heap 
            }
        }
        
        if(!heap.isEmpty()) {                                               // If the heap is not empty (there were some results)
            int size = 3;                                                   // Store 3 in a variable named size. This is the number of results we will print
            if(heap.size() < 3) {                                           // If there are less than 3 results
                size = heap.size();                                         // Reset the size variable to the number of results (only print max 3 results)
            }
            
            for(int i = 0; i < size; i++) {                                 // For each results
                MaxHeap.Node current = heap.removeMax();                    // Get the node by removing the maximum value fromt the heap (this will be the one with highest similarity)
                System.out.println("Similarity: " + current.s);             // Print out the similarity
                System.out.println(current.a);                              // Then print out the article
            } 
        } else {                                                            // Else, the heap is empty and there were no results
            System.out.println("No articles found!");                       // Print this to the user
        }
    }
    
    private static void titleContentSearch(Scanner s, ArticleTable D) {     // Using ArticleTable instead of BinaryTree! Not implemented, instructions unclear!
        System.out.println();
        System.out.println("(Not implemented) Search by keyword in article title");
        System.out.println("=========================");
        
        System.out.print("(Not implemented) Enter keyword: ");
        String title = s.nextLine();
        
        System.out.println("This function is not implemented!");
    }
    
    
    private static double getCosineSimilarity(String s, String t) {          // Function for getting the cosine similarit between a searchphrase and the article body
        String phrase = preprocess(s);                                       // Process the phrase and body, removing everything except letters, digits and whitespaces
        String body = preprocess(t); 
        TermFrequencyTable table = new TermFrequencyTable();                 // Create a new term frequency table
        
        StringTokenizer tokenPhrase = new StringTokenizer(phrase);           // Tokenize the phrase, this will split up the string by individual words
        while(tokenPhrase.hasMoreTokens()) {                                 // For each word
            String token1 = tokenPhrase.nextToken();                         // Store the word in a string variable named token1
            if(blacklisted(token1) == false) {                               // If this word is NOT blacklisted
                table.insert(token1, 0);                                     // Insert it into the term frequency table, giving it a document id of 0
            }
        }
        
        StringTokenizer tokenBody = new StringTokenizer(body);               // Tokenize the body ,this will split up the string by individual words
        while(tokenBody.hasMoreTokens()) {                                   // For each word
            String token2 = tokenBody.nextToken();                           // Store the word in a string variable named token2
            if(blacklisted(token2) == false) {                               // If the word is NOT blacklisted
                table.insert(token2, 1);                                     // Insert it into the term frequency table, giving it a document id of 1
            }
        }
        
        return table.cosineSimilarity();                                     // Return the cosine similarity of the newly created term frequqncy table
        
    }
    
    private static String preprocess(String s) {                             // Take a string, turn it into all lower case, and remove all characters except for letters, digits, and whitespace
        s = s.toLowerCase();                                                 // Turn to lower case
        String newString = "";                                               // Create a new string, newString, this will be the one without illegal characters
        for(int i = 0; i < s.length(); i++) {                                // For each character in the string
            Character currentChar = s.charAt(i);                             // Store the character
            if(Character.isWhitespace(currentChar) || Character.isDigit(currentChar) || Character.isLetter(currentChar)) {  // If it is either a whitespace, digit or letter:
                newString += currentChar;                                    // Add it to the newString
            } 
        }
        return newString;                                                    // Return the new string
    }
    
    private static boolean blacklisted(String s) {                           // Determine if the string s is a member of the blacklist (given at the bottom of this assignment)
        for(int i = 0; i < blackList.length; i++) {                          // For each blacklisted word
            if(s.equalsIgnoreCase(blackList[i])) {                           // If the passed word is blacklisted
                return true;                                                 // Return true
            }
        }
        return false;                                                        // Otherwise (not blacklisted word) return false
    }
    
    private static final String [] blackList = { "the", "of", "and", "a", "to", "in", "is",  // Blacklisted words
        "you", "that", "it", "he", "was", "for", "on", "are", "as", "with", 
        "his", "they", "i", "at", "be", "this", "have", "from", "or", "one", 
        "had", "by", "word", "but", "not", "what", "all", "were", "we", "when", 
        "your", "can", "said", "there", "use", "an", "each", "which", "she", 
        "do", "how", "their", "if", "will", "up", "other", "about", "out", "many", 
        "then", "them", "these", "so", "some", "her", "would", "make", "like", 
        "him", "into", "time", "has", "look", "two", "more", "write", "go", "see", 
        "number", "no", "way", "could", "people",  "my", "than", "first", "water", 
        "been", "call", "who", "oil", "its", "now", "find", "long", "down", "day", 
        "did", "get", "come", "made", "may", "part" }; 
    
}