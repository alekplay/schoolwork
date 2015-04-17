public class Foo {
    private int a;
    private double b;
    
    public Foo(int a, double b) {
        setA(a);
        setB(b);
    }
    
    public double bar() {
        return this.a * this.b;
    }
    
    public int getA() {
        return this.a;
    }
    
    public double getB() {
        return this.b;
    }
    
    public void setA(int a) {
        if(a % 2 == 0) {
            this.a = a;
        } else {
            throw new IllegalArgumentException("A must be even");
        }
    }
    
    public void setB(double b) {
        if(b >= 0) {
            this.b = b;
        } else {
            throw new IllegalArgumentException("B must be greater than or equal to 0");
        }
    }
}