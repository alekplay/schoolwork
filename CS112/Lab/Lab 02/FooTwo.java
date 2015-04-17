public class FooTwo {

    public static void main(String[] args) {
     
        String s = "able"; 
        String t = "aaron";
        String u = "ben"; 
        String v = "benjamin";
        String w = "aaron";
        System.out.println("(s.compareTo(t) == 0)?  " + (s.compareTo(t) == 0));
        System.out.println("(s.compareTo(u) != 0)?  " + (s.compareTo(u) != 0));        
        System.out.println("(s.compareTo(t) >  0)?  " + (s.compareTo(t) > 0));
        System.out.println("(t.compareTo(w) >= 0)?  " + (t.compareTo(w) >= 0));
        System.out.println("(v.compareTo(w) <= 0)?  " + (v.compareTo(w) <= 0));
        System.out.println("(u.compareTo(v) <  0)?  " +  (u.compareTo(v) < 0));
  
    }
}