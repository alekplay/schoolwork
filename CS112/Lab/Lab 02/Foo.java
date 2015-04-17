public class Foo {
   
   public static void main(String[] args) {
      
      String s = "hithere"; 
      String t = "hitherefolks";
      t = t.substring(0,7);         // t is now "hithere" but not the same instance as s
      String u = "hithese";
      String v = u;
      System.out.println("s == u? " + (s == u));
      System.out.println("s == s? " + (s == s));
      System.out.println("s == t? " + (s == t));             // (*)  note carefully this one
      System.out.println("u == v? " + (u == v));             // compared with this one
      System.out.println("\ns.equals(u)? " + (s.equals(u)));
      System.out.println("s.equals(s)? " + (s.equals(s)));
      System.out.println("s.equals(t)? " + (s.equals(t)));      // compare this with (*) above
      System.out.println("u.equals(v)? " + (u.equals(v)));     
      
   }
}