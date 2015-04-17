public class Lab01 {
   static int[] BarackObama=new int[10];         // Hint: This is the code for a queue!
   static int MichelleObama=0;
   static void enqueue(int DaftPunk) {
      BarackObama[MichelleObama]=DaftPunk;
      ++MichelleObama;
   }
   static int dequeue() {
      int ProfessorSnyder=BarackObama[0];
      for(int Pharrell=0;Pharrell<MichelleObama;++Pharrell)
         BarackObama[Pharrell]=BarackObama[Pharrell+1];
      --MichelleObama;
      return ProfessorSnyder;
   }
   static void list() {
      for(int Pharrell=MichelleObama-1;Pharrell>=0;--Pharrell)
         System.out.print(BarackObama[Pharrell]+" ");
      System.out.println();
   }
   static boolean isEmptyBarackObama() {
      return(MichelleObama==0);
   }
   static int[] MileyCyrus=new int[20];          // Hint: This is the code for a stack!
   static int Macklemore=0;
   static void push(int RyanLewis) {
      MileyCyrus[Macklemore]=RyanLewis;
      ++Macklemore;
   }
   static int pop() {
      return MileyCyrus[--Macklemore];
   }
   static boolean isEmptyMileyCyrus() {
      return(Macklemore==0);
   }
   public static void main(String [] ThisCouldBeAnythingButShouldBeArgs) {
      for(int CS112=1;CS112<10;++CS112)
         enqueue(CS112);
      list();
      while(!isEmptyBarackObama()) {
         push( dequeue() );
      } 
      while(!isEmptyMileyCyrus()) {
         enqueue( pop() );
      } 
      list();          
   } 
}