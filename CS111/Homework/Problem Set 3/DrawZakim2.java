/* CS 111 - Problem set 3
 * Part II - Task 6 - Second version
 * Aleksander Skj¿lsvik 
 */

public class DrawZakim2 {    
    public static void drawSpear(int size) {
        int totalSpaces = (7 * size) + (size - 1); // 7, 15, 23
        int spearHeight = size; //1, 2, 3
        
        for(int i=1; i<=spearHeight; i++) {
            
            printAllSpaces(totalSpaces);
            
            for(int j=1; j<=i; j++) {
                printSlash();
            }
            
            printLine();
            
            for(int n=1; n<=i; n++) {
                printBackSlash();
            }
            
            newLine();
            totalSpaces--;
        }
        
    }
    
    public static void drawTower(int size) {
        int totalSpaces = (7 * size); // 7, 14, 21
        int width = (3 * size) - (1 * (size - 1)); //3, 5, 7
        
        for(int i=1; i<=5; i++) {
            if(i%2 == 0) {
                printAllSpaces(totalSpaces);
                
                for(int n=1; n<=width; n++) {
                    printHyphen();
                }
                
                newLine();
                
            } else {
                for(int j=1; j<=2; j++) {
                    printAllSpaces(totalSpaces);
                    
                    for(int p=1; p<=width; p++) {
                        if((p%2) == 0) {
                            printColon();
                        } else {
                            printLine();
                        }
                    }
                    
                    newLine();
                }
            }
        }
    }
    
    public static void drawTowerBase(int size) {
        int totalSpaces = (7 * size) - 1; // 6, 13, 20
        int width = (3 * size) - (1 * (size - 1)); //3, 5, 7
        
        printAllSpaces(totalSpaces);
        printSlash();
        
        for(int n=1; n<=width; n++) {
            printHyphen();
        }
        
        printBackSlash();
        newLine();
    }
    
    public static void drawBridgeTop(int size) {
        int totalSpaces = (7 * size) - 2; // 5, 12, 19
        int height = (2*size) - 1;
        int numberOfCurlys = size + 1;
        
        for(int i=1; i<=height; i++) {
            
            printAllSpaces(totalSpaces);
            printSlash();
            
            for(int j=1; j<=numberOfCurlys; j++) {
                printLeftCurly();
            }
            
            printLine();
            
            for(int k=1; k<=numberOfCurlys; k++) {
                printRightCurly();
            }
            
            printBackSlash();
            
            numberOfCurlys++;
            totalSpaces--;
            
            newLine();
        }
    }
    
    public static void drawLegs(int size) {
        int totalSpaces = (size * 4) + (size - 1); // 4, 9, 14
        int distanceBetween = (3 * size) - (size - 1); // 3, 5, 7
        int distanceBeforeCable = 0;
        int distanceBetweenCable = distanceBetween + ((2* size) - 2); // 3, 7, 11
        int numLegs = 5 * size; // 5, 10, 15
        int numberOfCurlys = size + (size - 1); //1, 3, 5
        int dualCableStart = size + 1; // 2, 3, 4
        int dualCableStop = size * 3; //3, 6, 9
        
        for(int i=1; i<=numLegs; i++) {
            printAllSpaces(totalSpaces);
            
            printSlash();
            
            for(int k=1; k<=numberOfCurlys; k++) {
                printLeftCurly();
            }
            
            printSlash();
            
            if(i<=size) {
                for(int b=1; b<=distanceBetween; b++) {
                    printSpace();
                }
                distanceBetween += 2;
                
            } else if (i>=dualCableStart && i<=dualCableStop) {
                for(int c=1; c<=distanceBeforeCable; c++) {
                    printSpace();
                }
                
                printBackSlash();
                
                for(int d=1; d<=distanceBetweenCable; d++) {
                    printSpace();
                }
                
                printSlash();
                
                for(int e=1; e<=distanceBeforeCable; e++) {
                    printSpace();
                }
                
                distanceBetweenCable -= 2;
                distanceBeforeCable += 2;
  
            } else {
                for(int c=1; c<=distanceBeforeCable; c++) {
                    printSpace();
                }
                
                printLine();
                
                for(int d=1; d<=distanceBeforeCable; d++) {
                    printSpace();
                }
                
                distanceBeforeCable++;
            }
            
            printBackSlash();
            
            for(int k=1; k<=numberOfCurlys; k++) {
                printRightCurly();
            }
            
            printBackSlash();
            
            totalSpaces--;
            newLine();
            
        }
    }
    
    public static void drawFeet(int size) {
        int distanceBetween = 13 + (12 * (size - 1)); // 13, 25, 37
        int check = 0;
        int numberOfEquals = size * 2; // 2, 4, 6
        
        for(int i=1; i<=2; i++) {
            for(int j=1; j<=numberOfEquals; j++) {
                printEquals();
            }
            
            if(i==1) { 
                for (int l=0; l<2; l++) { 
                    for(int k=1; k<=(distanceBetween - 1)/2; k++) {
                        printSpace();
                    }
                    check++;
                    if(check == 1) {
                        printEquals();
                    }
                }
            }
        }
    }
    
    public static void printLine() {
        System.out.print("|");
    }
    
    public static void printColon() {
        System.out.print(":");
    }
    
    public static void newLine() {
        System.out.print("\n");
    }
    
    public static void printSpace() {
        System.out.print(" ");
    }
    
    public static void printSlash() {
        System.out.print("/");
    }
    
    public static void printBackSlash() {
        System.out.print("\\");
    }
    
    public static void printHyphen() {
        System.out.print("-");
    }
    
    public static void printLeftCurly() {
        System.out.print("{");
    }
    
    public static void printRightCurly() {
        System.out.print("}");
    }
    
    public static void printEquals() {
        System.out.print("=");
    }
    
    public static void printAllSpaces(int totalSpaces) {
        for(int a=1; a<=totalSpaces; a++) {
                printSpace();
            }
    }
    
    public static void main(String[] args) {
        int size = 2;
        
        drawSpear(size);
        drawTower(size);
        drawTowerBase(size);
        drawBridgeTop(size);
        drawLegs(size);
        drawFeet(size);
    }
    
}