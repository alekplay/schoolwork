/* CS 111 - Problem set 3
 * Part II - Task 6 - First version
 * Aleksander Skj¿lsvik 
 */

public class DrawZakim {
    public static void drawSpear() {
        int totalSpaces = 15;
        int spearHeight = 2;
        
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
    
    public static void drawTower() {
        int totalSpaces = 14;
        
        for(int i=1; i<=5; i++) {
            if(i%2 == 0) {
                printAllSpaces(totalSpaces);
                
                for(int n=1; n<=5; n++) {
                    printHyphen();
                }
                
                newLine();
                
            } else {
                for(int j=1; j<=2; j++) {
                    printAllSpaces(totalSpaces);
                    
                    for(int p=1; p<=5; p++) {
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
    
    public static void drawTowerBase() {
        int totalSpaces = 13;
        
        printAllSpaces(totalSpaces);
        printSlash();
        
        for(int n=1; n<=5; n++) {
            printHyphen();
        }
        
        printBackSlash();
        newLine();
    }
    
    public static void drawBridgeTop() {
        int totalSpaces = 12;
        int check = 3;
        
        for(int i=1; i<=3; i++) {
            
            printAllSpaces(totalSpaces);
            printSlash();
            
            for(int j=check; j<=5; j++) {
                printLeftCurly();
            }
            
            printLine();
            
            for(int k=check; k<=5; k++) {
                printRightCurly();
            }
            
            printBackSlash();
            
            check--;
            totalSpaces--;
            
            newLine();
        }
    }
    
    public static void drawLegs() {
        int totalSpaces = 9;
        int distanceBetween = 5;
        int distanceBeforeCable = 0;
        int distanceBetweenCable = 7;
        
        for(int i=1; i<=10; i++) {
            printAllSpaces(totalSpaces);
            
            printSlash();
            
            for(int k=1; k<=3; k++) {
                printLeftCurly();
            }
            
            printSlash();
            
            if(i<3) {
                for(int b=1; b<=distanceBetween; b++) {
                    printSpace();
                }
                distanceBetween += 2;
                
            } else if (i>=3 && i<=6) {
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
            
            for(int k=1; k<=3; k++) {
                printRightCurly();
            }
            
            printBackSlash();
            
            totalSpaces--;
            newLine();
            
        }
    }
    
    public static void drawFeet() {
        int distanceBetween = 25;
        int check = 0;
        
        for(int i=1; i<=2; i++) {
            for(int j=1; j<=4; j++) {
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
        drawSpear();
        drawTower();
        drawTowerBase();
        drawBridgeTop();
        drawLegs();
        drawFeet();
    }
    
}