import java.util.Arrays;

public class testOutput {
    public static void main(String[] args) {
        System.out.println("Duplicate of string hei: " + duplicate("hei"));
        System.out.println("Length of string hei: " + size("hei"));
        System.out.println("String hei without vowels: " + noVowels("hei"));
        System.out.println("Reverse of string hei: " + reverse("hei"));
        System.out.println("Is ei substring of hei? " + isSubstring("ei", "hei"));
        System.out.println("Inserting z into aaaa at 2: " + insertInto('z', 2, "aaaa"));
        System.out.println("Replacing \"be\" with \"bop\" in \"beware of beetles\": " + replace("be", "bop", "beware of beetles"));
    }
    
    public static String duplicate(String s) {
        if (s == null || s.equals(""))
            return "";
        String first = s.substring(0, 1);
        return first + duplicate(s.substring(1));
    } 
    
    public static int size(String s) {
        if (s == null || s.equals("")) {
            return 0;
        } 
        return 1 + size(s.substring(1));
    }
    
    public static String noVowels(String s) {
        if (s == null || s.equals("")) {
            return "";
        }
        
        String removed = noVowels(s.substring(1));
        char first = s.charAt(0);
        
        if (first == 'a' || first == 'e' || first == 'i'
                || first == 'o' || first == 'u') {
            return removed;
        } else {
            return first + removed;
        }
    }
    
    public static String reverse(String s) {
        if (s == null || s.equals("")) {
            return "";
        }
        
        return reverse(s.substring(1)) + s.charAt(0);
    }
    
    public static boolean isSubstring(String s1, String s2) {
        if(s1 == null || s1.equals("")) {
            return true;
        } else if(s2== null || s2.equals("")) {
            return false;
        } else if(size(s2) < size(s1)) {
            return false;
        }
        
        String s1First = s1.substring(0,1);
        String s2First = s2.substring(0,1);
        
        if(s2First.equals(s1First)) {
            return isSubstring(s1.substring(1), s2.substring(1));
        } else {
            return isSubstring(s1, s2.substring(1));
        }
    }
    
    public static String insertInto(char c, int i, String s){
        return insertIntoHelper(c, i, s, 0);
    }
    
    
    public static String insertIntoHelper(char c, int i, String s, int k){
        if (s == null || s.equals("")){
            return "";
        }
        
        if(k == i) {
            return c + "" + s.charAt(0) + insertIntoHelper(c, i, s.substring(1), ++k);
        } else {
            return s.charAt(0) + insertIntoHelper(c, i, s.substring(1), ++k);
        }
    }
    
    public static String replace(String a, String b, String s) {
        if (s == null || s.equals("")) {
            return "";
        }
        
        if(size(s) < size(a)) {
            return s.substring(0,1) + replace(a, b, s.substring(1));
        } else if(isSubstring(s.substring(0, size(a)), a)) {
            return b + replace(a, b, s.substring(size(a)));
        } else {
            return s.substring(0,1) + replace(a, b, s.substring(1));
        }
    }
}

