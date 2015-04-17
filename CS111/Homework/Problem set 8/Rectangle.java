/*
 * Rectangle.java
 * 
 * Computer Science 111, Boston University
 * 
 * A class that acts as a blueprint for objects that represent a rectangle.
 * This class is comparable to the built-in Rectangle class
 * from the java.awt package.
 */

public class Rectangle {
    /* 
     * Fields that capture the state of a Rectangle object.
     * We make them private to prevent direct access from outside the class.
     */
    private int x;
    private int y;
    private int width;
    private int height;

    /* 
     * A constructor that allows clients to specify initial values for all
     * of the fields.  It calls the mutator methods, so that they
     * can perform the necessary error-checking.
     */
    public Rectangle(int initialX, int initialY, int initialWidth, int initialHeight) {
        this.setLocation(initialX, initialY);
        this.setWidth(initialWidth);
        this.setHeight(initialHeight);
    }

    /* 
     * A constructor that takes just the width and height, 
     * and assumes that the coordinates are (0, 0).
     * It calls the other constructor to do the work.
     */
    public Rectangle(int initialWidth, int initialHeight) {
        this(0, 0, initialWidth, initialHeight);
    }
    
    /*
     * getX - an accessor method for a Rectangle's x coordinate.
     */
    public int getX() {
        return this.x;
    }
    
    /*
     * getY - an accessor method for a Rectangle's y coordinate.
     */
    public int getY() {
        return this.y;
    }
    
    /* 
     * getWidth - an accessor method for a Rectangle's width.
     */
    public int getWidth() {
        return this.width;
    }
    
    /*
     * getHeight - an accessor method for a Rectangle's height.
     */
    public int getHeight() {
        return this.height;
    }         

    /*
     * area - an accessor method for a Rectangle's area.
     */
    public int area() {
        return this.width * this.height;
    }
    
    /*
     * perimeter - an accessor method for a Rectangle's perimeter.
     */
    public int perimeter() {
        return (2*this.width + 2*this.height);
    }
    
    /*
     * setLocation - a mutator method that changes a Rectangle's 
     * x and y coordinates.
     *
     * precondition: both newX and newY must be non-negative
     */
    public void setLocation(int newX, int newY) {
        if (newX < 0 || newY < 0) {
            throw new IllegalArgumentException();
        }

        this.x = newX;
        this.y = newY;
    }
    
    /*
     * setWidth - a mutator method that changes a Rectangle's width.
     *
     * precondition: newWidth must be positive
     */
    public void setWidth(int newWidth) {
        if (newWidth <= 0) {
            throw new IllegalArgumentException();
        }

        this.width = newWidth;
    }

    /*
     * setHeight - a mutator method that changes a Rectangle's height.
     *
     * precondition: newHeight must be positive
     */
    public void setHeight(int newHeight) {
        if (newHeight <= 0) {
            throw new IllegalArgumentException();
        }
        
        this.height = newHeight;
    }
    
    /*
     * grow - a mutator method that changes a Rectangle's width and height,
     * adding dWidth to the current width and dHeight to the current height.
     * 
     * It uses setWidth and setHeight so that they can perform the
     * necessary error-checking.
     *
     * precondition: the resulting width and height must be positive.
     */
    public void grow(int dWidth, int dHeight) {
        this.setWidth(this.width + dWidth);
        this.setHeight(this.height + dHeight);
    }
    
    /*
     * isSquare - returns true if the Rectangle is a square, and false otherwise.
     */
    public boolean isSquare() {
        return (this.height == this.width);
        
        
    }
    
    /*
     * equals - returns true if the Rectangle specified by the parameter
     * is equivalent to the calling object, and false otherwise.
     * 
     * Note: this method does *not* override the default equals method,
     * because its parameter is of type Rectangle.
     */
    public boolean equals(Rectangle other) {
        return (other != null           
             && this.x == other.x 
             && this.y == other.y 
             && this.width == other.width 
             && this.height == other.height);
    }
   
    /*
     * toString - returns a string representation of the Rectangle 
     * of the form "width x height".
     */
     public String toString() {
         return this.width + " x " + this.height;
     }
}