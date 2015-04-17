public class TracingMethodCalls {
    public static int compute(int x, int y) {
        x += 3;
        y = 2*y - x;
 System.out.println(x + " " + y);
        return x;
    }

    public static void main(String[] args) {
        int x = 1;
 int y = 3;
 x = compute(x, y);
 System.out.println(x + " " + y);
 compute(y, y);
 System.out.println(x + " " + y);
 y = 3 + 4 * compute(y, x);
 System.out.println(x + " " + y);
    }
}