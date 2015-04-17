public class CollectionClient {
    public static void main(String [] args) { 
        Collectable C = new Collection();             // verify interface
        
        System.out.println("Inserting 2, 3, and 13:"); 
        C.insert(2);  
        C.insert(3);  
        C.insert(13); 
        C.list();                           // had to comment out because not in the interface!
        System.out.println("Deleting 2:"); 
        C.delete(2);
        C.list(); 
        System.out.println("Inserting 42 and 2:"); 
        C.insert(42); 
        C.insert(2); 
        C.list(); 
        System.out.println("Deleting 13:");
        C.delete(13);
        C.list(); 
        System.out.println("Deleting 13 again:");
        C.delete(13); 
        C.list(); 
        if(C.member(2)) {
            System.out.println("Member: " + 2); 
        }else{
            System.out.println("Not a member: " + 2); 
        }
        if(C.member(12)) {
            System.out.println("Member: " + 12); 
        }else{
            System.out.println("Not a member: " + 12);
        }
        C.delete(3);
        C.delete(42);
        C.delete(2);
        System.out.println("Deleted all elements:");
        C.list();
        /*System.out.println("Inserting 8 elements");
        C.insert(2);
        C.insert(5);
        C.insert(6);
        C.insert(3);
        C.insert(7);
        C.insert(4);
        C.insert(10);
        C.insert(8);
        C.list();
        System.out.println("Inserting 3 more elements (total 11)");
        C.insert(12);
        C.insert(6);
        C.insert(18);
        C.list();
        System.out.println("Inserting 10 more elements (total 21)");
        C.insert(1);
        C.insert(6);
        C.insert(34);
        C.insert(23);
        C.insert(76);
        C.insert(23);
        C.insert(12);
        C.insert(3);
        C.insert(7);
        C.insert(12);
        C.list();
        System.out.println("Deleting 12 elements");
        C.delete(1);
        C.delete(6);
        C.delete(34);
        C.delete(23);
        C.delete(76);
        C.delete(23);
        C.delete(12);
        C.delete(3);
        C.delete(7);
        C.delete(12);
        C.delete(2);
        C.delete(6);
        C.list();*/


        
        
    }    
} 