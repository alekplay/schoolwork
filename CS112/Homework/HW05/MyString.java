/* MyString class
 * CS112 homework feb 27th 2014
 * Made by: Aleksander Skjoelsvik
 */

public class MyString implements Stringy {          // Class definition, implements interface given
    public static class Node {                      // Definition for Node class (found on website)
        public char item;
        public Node next;
        
        Node() {
            item = 0; 
            next = null;      
        } 
        
        Node(char n, Node p) {
            item = n;
            next = p;
        }
    }
    
    private Node head;                              // Head variable keeps track of the first node
    private int size;                               // Size variable keeps track of the size
    
    public MyString() {                             // Empty constructor, creates empty MyString
        size = 0;
        head = new Node();
    }
    
    public MyString(String str) {                  // Main constructor, creates MyString from string given
        size = str.length();
        head = MyStringHelper(str, 0);             // Head will be result from helper method
    }
    
    private Node MyStringHelper(String str, int i) { // Helper method, takes the string and an int as parameters
        if(i == size) {                              // If the i is equal to the size (you're finished with the string)
            Node p = new Node();                     // Create a new empty node
            return p;                                // And return it
        } else {
            Node p = new Node(str.charAt(i), MyStringHelper(str, ++i)); // Create a new node, where the item is the current character, and the next is another node as a result from a recursive call to MyStringHelper where you increment i
            return p;
        }
    }
    
    public String toString() {                      // Creates string from MyString
        return toStringHelper(head);                // Calls helper method, passing in the head of the MyString
    }
    
    private String toStringHelper(Node node) {     // toStringHelper
        if(node.item == 0) {                       // If the item is 0 (we've gone through all the elements in the myString)
            return "";                             // Return an empty string
        } else {
            return node.item + toStringHelper(node.next); //If not, return the current item of the MyString and concatenate on the result of the recursive call where you pass the next item in the MyString
        }
    }
    
    public char charAt(int index) {                // charAt function, finds the char at specified positino
        if(index >= size) {                        // If the index passed is greater than the size
            return 0;                              // Return 0, because it cannot be found
        } else {
            return charAtHelper(index, 0, head);   // If not, call the helper method passing the index, 0 and the head
        }
    }
    
    private char charAtHelper(int index, int i, Node node) { //Helper method to charAt
        if(i == index) {                           // If the i is equal to the index
            return node.item;                      // Return the current item because you're where you're at
        } else {
            return charAtHelper(index, ++i, node.next); // If not, call the function recursivly incrementing i and pass the next item of the myStirng
        }
    }
    
    public int compareTo(MyString anotherMyString) { // Compare to method, compares two substrings
        return compareToHelper(head, anotherMyString.head); //Call helper method, pass in both heads
    }
    
    private int compareToHelper(Node thisNode, Node anotherNode) { // Helper method for compareTo
        if(thisNode.item > anotherNode.item) {      // If the current element of this node is greater than the current element of the other node
            return 1;                               // Return 1, because this is bigger
        } else if(thisNode.item < anotherNode.item) { // If the current element of this node is smaller than the current element of the other node
            return -1;                              // Return -1, because the other is bigger
        } else if(thisNode.item == 0 && anotherNode.item != 0) { // If the current element of this node doesn't exist, but the other does
            return -1;                              // Return -1 because other is bigger
        } else if(thisNode.item != 0 && anotherNode.item == 0) { // If the current element of other node doesn't exist, but this one does
            return 1;                               // Return 1, because this is bigger
        } else if(thisNode.item == 0 && anotherNode.item == 0){ //If both is empty
            return 0;                               // Return 0, because they're equal
        } else {                                    // If none the above
            return compareToHelper(thisNode.next, anotherNode.next); // Just call the function again recursivly, passing next elements of both nodes
        }
    }
    
    public MyString concat(MyString anotherMyString) { //Concat, adds the other string to the end of this
        MyString newMyString = new MyString();       // Create new empty stirng
        newMyString.head = concatHelper(head, anotherMyString.head); //Set the head to the result of the call to the helper method, passing both heads
        newMyString.size = size + anotherMyString.size; // The size is equal to the size of this one plus the size of the other one
        return newMyString;                          // Return the new string
    }
    
    private Node concatHelper(Node thisNode, Node anotherNode) { // Helper method taking both heads
        if(thisNode.item == 0 && anotherNode.item == 0) { // If the current item of both nodes don't exist
            Node p = new Node();                     // Create a new empty node 
            return p;
        } else if(thisNode.item == 0 && anotherNode.item != 0) { //If the current item of this node doesn't exist but the other one does
            Node p = new Node(anotherNode.item, concatHelper(thisNode, anotherNode.next)); // Create a new node, where the item is that of the other node, and the next is recursive call passing in thisNode and the next item of the other node
            return p;
        } else {                                     // If none of the above
            Node p = new Node(thisNode.item, concatHelper(thisNode.next, anotherNode)); //Create a new node, where the item is that of this node, and the next is recursive call passing in the next of thisNode and anotherNode
            return p;
        }
    }
    
    public MyString replace(char oldChar, char newChar) { // Replace method, replacing oldChar with newChar in MyString
        MyString newMyString = new MyString();       // Create new empty stirng
        newMyString.head = replaceHelper(head, oldChar, newChar); // Set head to value of call to helper, passing in head, the old char and new char
        newMyString.size = size;                     // Size is the same
        return newMyString;                          // Return the new string
    }
    
    private Node replaceHelper(Node node, char oldChar, char newChar) { // Helper method for replace, taking node and two chars
        if(node.item == 0) {                        // If the current item doesn't exist
            Node p = new Node();                    // Return a new empty node
            return p;
        } else {                                    // If it does
            if(node.item == oldChar) {              // And if the item equals the old char
                Node p = new Node(newChar, replaceHelper(node.next, oldChar, newChar)); // Return a new node where the item is the new char, and then next is recursive call passing the next element of node and both chars
                return p;
            } else {                                // If the current item doesnt equal the old char
                Node p = new Node(node.item, replaceHelper(node.next, oldChar, newChar)); // Return a new node, nothing is different. Just next is recurisve call
                return p;
            }
        }
    }
    
    public int indexOf(char ch) {                  // Index of method returning the index of a character
        return indexOfHelper(head, ch, 0);         // Call to helper passing in head, the character and 0
    }
    
    private int indexOfHelper(Node node, char ch, int i) { //IndexOf helper
        if(i == size) {                           // If the i equals the size (you're at the end)
            return -1;                            // Return -1 because it hasn't been found
        } else if(node.item == ch) {              // If the current item of the node equals the char
            return i;                             // Return index of current elemtn
        } else {                                  // If not
            return indexOfHelper(node.next, ch, ++i); // Call recursivly passing next node
        }
    }
    
    public int indexOf(MyString str) {           // IndexOf method returning the index of a mystring
        return indexOfHelper(head, str.head, 0); // Calling a helper passing both heads and 0
    }
    
    private int indexOfHelper(Node thisNode, Node anotherNode, int i) { //indexOfHelper
        if(thisNode.item == 0) {                 // If the current item doesn't exist (you've run off)
            if(i == 0) {                         // And the i is 0
                return -1;                       // Return -1 (hasn't been found)
            } else {                             // If found
                return 0;                        // Return 0
            }
        } else if(thisNode.item == anotherNode.item) { // If the item of this node equals the item of the other node
            if(i >= 1) {                         // If i is greater than or equal to 1
                return indexOfHelper(thisNode.next, anotherNode.next, i); //Call recursivly itself, passing the next of both nodes and i
            } else { // If not greater than or equal to 1
                return indexOfHelper(thisNode.next, anotherNode.next, ++i); // Call recursivly itself, passing the next of both nodes and increment i
            }
        } else { // If items are not equal
            return 1 + indexOfHelper(thisNode.next, anotherNode, 0); // Return 1 (because it still hasn't been found) + the value of the recursive call passing next of thisNode (not anotherNode) and 0 to reset the i counter
        }
    }
    
    public int length() {   //Returns length of myStringh
        return size;
    }
    
    public MyString substring(int beginIndex, int endIndex) { //Substring method returning a new mystring consisting of the elements between the indexes in this substring
        MyString newMyString = new MyString(); //Create new empty mystring
        newMyString.head = substringHelper(head, beginIndex, endIndex, 0); //Set head to result of helper, passing head, the two indexes and 0
        newMyString.size = endIndex - beginIndex; // Set length to the end-begin indexes
        return newMyString; //Return new stirng
    }
    
    private Node substringHelper(Node node, int beginIndex, int endIndex, int i) { //Helper for substring
        if(i == endIndex) { //If the i is equal to the end index (You've run off the elements to create a new string of
            Node p = new Node(); //Return an empty node
            return p;
        } else if(i >= beginIndex) { //If the i is greater than or equal to the begin index (all elements here should be added to new string)
            Node p = new Node(node.item, substringHelper(node.next, beginIndex, endIndex, ++i)); //Return a new node with this current item, and next is recurisve call incrementing the node and i
            return p;
        } else { //If you haven't reached beign index yet
            return substringHelper(node.next, beginIndex, endIndex, ++i); //Call recursivly incrementing node and i
        }
    }
    
    public static MyString valueOf(int i) { //Value of finding the mystring value of a passed integer
        String s = "" + i; // Turn value into string (NOT LOOP SO ITS OK)
        MyString newMyString = new MyString(s); //Run normal constructor on string
        return newMyString; //Return new string
    }
    
    /*public static MyString valueOf(int i) { //This is what I tried recursivly, but it didn't seem to work. Kind find out how tho
        MyString newMyString = new MyString();
        newMyString.head = valueOfHelper(i);
        newMyString.size = 4;
        return newMyString;
    }
    
    private static Node valueOfHelper(int i) {
        if(i == 0) {
            Node p = new Node();
            return p;
        } else {
            System.out.println("adding: " + i%10);
            Node p = new Node((char)(i%10), valueOfHelper(i/10));
            return p;
        }
    }*/
    
    public static int parseInt(MyString s) throws NumberFormatException{ //parse int function converting mystring to int
        return parseIntHelper(s.head, 0); //Call the helper method, passing in the head of the string and 0
    }
    
    private static int parseIntHelper(Node node, int i) throws NumberFormatException{ //Helper for parseInt
        if(node.next == null) { //If the next item of the node is null, (no more items)
            return i; //Return the i which will be the value
        } else { //If still more items in node
            return parseIntHelper(node.next, i*10 + (node.item-48)); //Call recursivly, whith next item of node and the i will be 10 times the i (current value) plus the current item of the node - 48 (because the char value of a integer is not the actual integer value. You get it by taking away 48 from it)
        }
    }
}