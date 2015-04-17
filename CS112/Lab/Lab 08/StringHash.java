/*File: StringHash.java 
 * Author: Wayne Snyder (snyder@cs.bu.edu) 
 * Date: 4/11/13
 * Purpose: This is a demonstration for lecture of various hash functions
 * Uses files:  JBox.java, JCanvas.java, JEventQueue.java
 * Platform: This was developed on Mac OS X 10.6.5 in Dr. Java
 * 
 */


import java.awt.*;
import javax.swing.*;
import java.util.EventObject;
import java.util.Random; 

public class StringHash {
  
  // for list of primes, see end of the class
  
  static int M = 137;            // size of table
  static int LCMultiplier = 7639;
  static int LCAddend = 0;
  
  // list of prime numbers permuted a bit, for the SeqLinCong method
  
  static int [] listOfMultipliers = { 
    7229,   743,    4583,    757,    6311,    1511,   7853,    787,    4423,    809, 
    811,    821,    823,    827,    829,   839,   853,    857,    859,   863, 
    877,    881,    883,    887,    907,    911,    919,   929,   937,    941, 
    947,    953,    967,    971,    977,    983,    991,    997,   1009,  1013, 
    1019,  1021,   1031,   1033,   1039,  1049,  1051,   1061,   1063,   1069, 
    1087,   1091,   1093,   1097,   1103,   1109,  1117,   1123,   1129,  1151, 
    1153,   1163,   1171,   1181,   1187,   1193,   1201,   1213,   1217,   1223, 
    1229,  1231,   1237,   1249,  1259,  1277,   1279,  1283,   1289,  1291, 
    1297,   1301,   1303,   1307,   1319,  1321,   1327,   1361,   1367,   1373, 
    1381,   1399,  1409,  1423,   1427,   1429,  1433,   1439,  1447,   1451, 
    1453,   1459,  1471,   1481,   1483,   1487,   1489,  1493,   1499 }; 
  
  static int nodeLength = 10;      // how long is an individual node in each bucket, in pixels
  static int unitLength = 10;      // how long is one count in histogram
  static int seed = LCAddend; 
  static JCanvas canvas, canvas2;
  static BasicStroke singlePixel = new BasicStroke(1); 
  
  static String [] blackList = { "s", "t", "ll", "m", "" }; 
  
  static Boolean member(String s, String [] list) {
    for(int i = 0; i<list.length; i++) 
      if(s.equals(list[i])) 
      return true; 
    return false;  
  }
  
  // Sample of hash functions
  
  // silly hash function: take the last character and mod it
  
  static int silly(String s, int M) {
    return s.charAt(s.length()-1) % M; 
  }
  
  // multiply all the int values for chars
  static int naiveMult(String s, int M) {
    int sum = 1;
    for(int i = 0; i< s.length(); i++) 
      sum *= s.charAt(i);
    return (int) (Math.abs(sum) % M);
  }
  
  // add all the int values for chars
  static int naiveAdd(String s, int M) {
    int sum = 0;
    for(int i = 0; i< s.length(); i++) 
      sum += s.charAt(i);
    return (int) (Math.abs(sum) % M);
  }
  
  // do naiveAdd, then linear congruential
  static int AddLinCong(String s, int M, int multiplier) {
    int sum = 0;
    for(int i = 0; i< s.length(); i++) 
      sum += (s.charAt(i) * multiplier) % M;;
    return  sum % M;
  }
  
  // use a list of prime numbers to multiply at each character
  static int SeqLinCong(String s, int M) { 
    int sum = 0;
    for(int i = 0; i< s.length(); i++)  {
      sum += (s.charAt(i) * listOfMultipliers[i]) % M; 
    }
    return sum % M;
  } 
  

  
  // Use folding on a string, summed 4 bytes at a time; returns int in range 0 .. M-1
  // This was copied from http://research.cs.vt.edu/AVresearch/hashing/strings.php
  
// Use folding on a string, summed 4 bytes at a time
  static int sfold(String s, int M) {
    int intLength = s.length() / 4;
    long sum = 0;
    for (int j = 0; j < intLength; j++) {
      char c[] = s.substring(j * 4, (j * 4) + 4).toCharArray();
      long mult = 1;
      for (int k = 0; k < c.length; k++) {
        sum += c[k] * mult;
        mult *= 256;
      }
    }
    
    char c[] = s.substring(intLength * 4).toCharArray();
    long mult = 1;
    for (int k = 0; k < c.length; k++) {
      sum += c[k] * mult;
      mult *= 256;
    }
    
    return (int) (Math.abs(sum) % M);
  }
  
  // The basic hash function used by the String library for hashCode()
  
  static int javaHash(String s, int M) {
    int h = 0;
    for (int i = 0; i < s.length(); i++) {
      h = 31*h + s.charAt(i);
    }
    return Math.abs(h) % M; 
  }
  
  // Java Hash but use sequence of primes instead of just 31
  static int javaHashSeq(String s, int M) {
    int h = 0;
    for (int i = 0; i < s.length(); i++) {
      h = listOfMultipliers[i]*h + s.charAt(i);
    }
    return Math.abs(h) % M; 
  }
  
  static String[] hashFuncs={"Silly", "Naive Add", "Naive Mult", "Add Lin Cong", 
    "Sequential LC", "Folding", "Java HashCode", "Seq Java HashCode"}; 
  
  static int hash(String s, int M, int Mult,  int alg) {
    switch(alg) {
      
      case 0:
        return silly(s,M); 
      case 1:
        return naiveAdd(s,M);
      case 2:
        return naiveMult(s,M);         
      case 3:
        return AddLinCong(s,M, Mult); 
      case 4:
        return SeqLinCong(s,M);
      case 5:
        return sfold(s,M);
      case 6:
        return javaHash(s, M); 
      case 7:
        return javaHashSeq(s,M); 
    } 
    return 0; 
  }
  
  
  
  
  static String [] processString(String str) {
    
    str = str.toLowerCase(); 
//  str = str.replace("]'); 
//    str = str.replace('-', ''); 
    str = str.replace('.', ' '); 
    str = str.replace(',', ' ');
    str = str.replace(';', ' ');
    str = str.replace(':', ' ');
    str = str.replace('\'', ' ');
    str = str.replace('"', ' ');
    str = str.replace('-', ' ');
    str = str.replace('!', ' ');
    str = str.replace('?', ' ');
    str = str.replace('(', ' ');
    str = str.replace(')', ' ');
    
    
    String[] result = str.split("\\s");
//  for (int i = 0; i < result.length; i++)
//      System.out.print("!"+result[i]);
    // System.out.println("!"); 
    return result; 
  }
  
  static class Node {
    
    public String item;
    public Node next;
    
    Node(String n) {
      item = n;
      next = null;
    }
    
    Node(String n, Node p) {
      item = n;
      next = p;
    }
  };
  
  static boolean member( String s, Node p ) {
    if( p == null ) 
      return false; 
    else if(s.equals(p.item))
      return true;
    else
      return member(s, p.next); 
  } 
  
  public static void main(String args[]){
   // System.out.println(9*8*7*6*5*4*3*2+1); 
    JFrame frame=new JFrame("Hash Function Comparison");
    frame.setSize(1800,1000);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBackground(new Color(255,240,180)); 
    JLabel l1=new JLabel("Hash Table Laboratory"); 
    l1.setFont(new Font("TimesRoman",Font.BOLD,32)); 
    JLabel l2=new JLabel("Instructions: Insert text in Input Box, select Hash Function, and press Hash to see hash table configuration");
    JLabel l3=new JLabel("Input Box"); 
    JLabel l4=new JLabel("Hash Table");
    JLabel l4b=new JLabel("Bucket Length Histogram"); 
    JLabel l5=new JLabel("Hash Function"); 
    JLabel l7=new JLabel("LC Multiplier"); 
    JLabel l8=new JLabel("LC Addend"); 
    l2.setFont(new Font("TimesRoman",Font.PLAIN,16)); 
    l3.setFont(new Font("TimesRoman",Font.PLAIN,16)); 
    l4.setFont(new Font("TimesRoman",Font.PLAIN,16));      l4b.setFont(new Font("TimesRoman",Font.PLAIN,20)); 
    l5.setFont(new Font("TimesRoman",Font.PLAIN,16)); 
    l7.setFont(new Font("TimesRoman",Font.PLAIN,16)); 
    l8.setFont(new Font("TimesRoman",Font.PLAIN,16)); 
    JButton b1 = new JButton("Clear");
    JButton b2 = new JButton("Hash");
    b2.setFont(new Font("TimesRoman",Font.PLAIN,16)); 
    JLabel l6 = new JLabel("Table Size (M<480)");
    l6.setFont(new Font("TimesRoman",Font.PLAIN,16)); 
    JLabel b3 = new JLabel("Statistics");
    b3.setFont(new Font("TimesRoman",Font.PLAIN,16)); 
    JTextArea t1 = new JTextArea();
    JTextArea t2 = new JTextArea();
    JBox.setSize(t2, 100, 30); 
    JFormattedTextField inM=new JFormattedTextField(new Integer(10));
    inM.setValue(M);
    JBox.setSize(inM,40,40,10000,0);
    JFormattedTextField inMult=new JFormattedTextField(new Integer(10));
    inMult.setValue(LCMultiplier);
    JBox.setSize(inMult,40,40,10000,0);
    String stats = "Hash Table Size: " + M; 
    t2.setText(stats); 
    
    JComboBox combo=new JComboBox(hashFuncs); 
    combo.setEditable(false);
    combo.setEnabled(true); 
    
    t1.setLineWrap(true); 
    JScrollPane left = new JScrollPane(t1); 
    JBox.setSize(left,400,400); // 500
    left.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    left.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    JScrollPane bottom = new JScrollPane(t2); 
    JBox.setSize(bottom,300,100); 
    left.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    left.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    canvas = new JCanvas(); 
    canvas.clear();
    canvas.setPaint(Color.black); 
    canvas2 = new JCanvas(); 
    canvas2.clear();
    canvas2.setPaint(Color.black); 
    JSplitPane right = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,canvas, canvas2); 
    right.setDividerLocation(300); 
    JBox.setSize(right,600,400); // 500
    JBox body=JBox.vbox(JBox.CENTER, 
                        JBox.vspace(20), 
                        l1,
                        JBox.vglue(),
                        l2,
                        JBox.vglue(),
                        JBox.hbox(JBox.CENTER,
                                  JBox.hglue(),
                                  JBox.hspace(15),
                                  JBox.vbox(JBox.CENTER, l3, JBox.vspace(15),left,
                                            JBox.vspace(15),
                                            b1),
                                  
                                  JBox.hspace(10), 
                                  JBox.vbox(JBox.CENTER,
                                            JBox.vglue(),
                                            b2,
                                            JBox.vglue(), 
                                            l6,
                                            JBox.vspace(10), 
                                            inM,
                                            JBox.vglue(),
                                            l5,
                                            combo,
                                            JBox.vglue(),
                                            l7,
                                            JBox.vspace(10), 
                                            inMult,
                                            JBox.vglue()
                                              
                                              
                                           ),
                                  JBox.hspace(10), 
                                  JBox.vbox(JBox.CENTER,JBox.hbox(l4,JBox.hspace(200), l4b), JBox.vspace(15),right,
                                            JBox.vspace(15),
                                            JBox.hbox(JBox.CENTER,b3,JBox.hspace(20), bottom)), 
                                  JBox.hglue() 
                                 ),
                        JBox.vspace(20)
                       ); 
    
    frame.add(body);
    frame.setVisible(true);
    
    
    JEventQueue events=new JEventQueue();
    events.listenTo(b1,"clearInput");
    events.listenTo(b2,"hash");
    while(true){
      EventObject event=events.waitEvent();
      String name=events.getName(event);
      if(name.equals("clearInput")){
        t1.setText("");    
      } else if(name.equals("hash")){
        canvas.clear(); canvas2.clear(); t2.setText(""); 
        String [] words = processString(t1.getText()); 
        M = (Integer) inM.getValue(); 
        LCMultiplier = (Integer) inMult.getValue(); 
        
        Node [] table = new Node[M];
        int [] bucketLength = new int[M]; 
        int lineWidth = (int)(479.0/M); 
        
        String hashF = (String) combo.getSelectedItem();
        int alg = -1; 
        for(int i = 0; i < hashFuncs.length; ++i) 
          if(hashF.equals(hashFuncs[i]))
          alg = i; 
        
        // draw horizontal scale for left window
        canvas.setStroke(singlePixel); 
        for(int i = 0; i <= 40; ++i) {
          if(i % 5 == 0)
            canvas.drawLine(10+i*nodeLength, 8, 10+i*nodeLength, 3);
          else if(i % 10 == 0)
            canvas.drawLine(10+i*nodeLength, 8, 10+i*nodeLength, 1);
          else
            canvas.drawLine(10+i*nodeLength, 8, 10+i*nodeLength, 5);
        }
        canvas.setStroke(new BasicStroke(lineWidth));
        int sum = 0; 
        if(!(hashF.equals("Random Java") || hashF.equals("LC Random")) ) {
          for(int i = 0; i < words.length; ++i) {
            if(words[i].length() == 0)
              continue;
            int h = hash( words[i], M, LCMultiplier,  alg); 
            if(h==0)
              System.out.println(words[i]); 
            
            if(!member(words[i],table[h])) {
              table[h] = new Node(words[i], table[h]);
              ++sum; 
              ++bucketLength[h]; 
              
              canvas.drawLine(10, 12+h*lineWidth, 10+bucketLength[h]*nodeLength, 12+h*lineWidth ); 
            }
            
          }
        }
        else {
          for(int i = 0; i < words.length; ++i) {
            
            int h = hash( words[i], M, LCMultiplier, 0); 
            
            if(!member(words[i],table[h])) {
              table[h] = new Node(words[i], table[h]);
              ++sum; 
            }
          }
          for(int i = 0; i < sum; ++i) {
            int h = hash( words[i], M, LCMultiplier,  alg); 
            ++bucketLength[h]; 
            canvas.drawLine(10, 12+h*lineWidth, 10+bucketLength[h]*nodeLength, 12+h*lineWidth ); 
          }
        }
        //         for(int i = 0; i < bucketLength.length; ++i)
        //             System.out.println(i + ": " + bucketLength[i]);  
        int min = 10000000; int max = 0; 
        
        int totalComps = 0; 
        for(int i = 0; i < bucketLength.length; ++i) { 
          if(bucketLength[i] > max)
            max = bucketLength[i];
          if(bucketLength[i] < min && bucketLength[i] != 0)
            min = bucketLength[i];
          totalComps = totalComps + bucketLength[i]*(bucketLength[i]+1)/2; 
        }
        int [] histo = new int[max+1]; 
        float mean = (float) sum/M; 
        float sumSq = 0;         // sum of the squares of diffs from mean
        for(int i = 0; i < bucketLength.length; ++i) {
          ++histo[bucketLength[i]]; 
          sumSq += (bucketLength[i] - mean)*(bucketLength[i] - mean); 
        }
        float stdev = (float) Math.sqrt(sumSq/M); 
        float mbl = sum/(float)M;
        stats = "Hash Table Size: " + M; 
        stats = stats + "\nNumber of keys = " + sum + "\nMean bucket length: " + mbl; 
        stats = stats + "\nOptimal lookup cost: " + mbl*(mbl+1)/2.0;
        stats = stats + "\nMean lookup cost: " + totalComps/(float)M;
        stats = stats + "\nMin lookup cost = " + min + "  Max lookup cost: " + max; 
        stats = stats + "\nSt Dev bucket length: " + stdev ; 
        t2.setText(stats); 
        // draw vertical scale
        canvas2.drawLine(50,45,50,445);
        for(int i = 0; i<20; i +=5) {
          int inc = i*20; 
          canvas2.drawString(i+"",10,50+inc); 
          canvas2.drawLine(30,45+inc,50,45+inc); 
          canvas2.drawLine(40,65+inc,50,65+inc);
          canvas2.drawLine(40,85+inc,50,85+inc);
          canvas2.drawLine(40,105+inc,50,105+inc);
          canvas2.drawLine(40,125+inc,50,125+inc);
        }
        canvas2.drawString("20",10,450);
        canvas2.drawLine(30,445,50,445); 
        
        // draw horizontal scale
        canvas2.drawLine(60,35,40*unitLength+60,35);
        
        for(int i = 0; i<=40; ++i) {
          int X = 60+i*unitLength; 
          if(i % 5 == 0) {
            canvas2.drawString(i+"",X-4,25);
            canvas2.drawLine(X,30,X,35);
          }
          else 
            canvas2.drawLine(X,32,X,35); 
        }
        
        
        for(int i = 0; i<=max; ++i) {
          canvas2.fillRect(60,40+i*20,histo[i]*unitLength,10); 
        }
        
      } 
    }
    
  }
  
  /*   List of primes to consider:
   * 
   *      2      3      5      7     11     13     17     19     23     29 
   31     37     41     43     47     53     59     61     67     71 
   73     79     83     89     97    101    103    107    109    113 
   127    131    137    139    149    151    157    163    167    173 
   179    181    191    193    197    199    211    223    227    229 
   233    239    241    251    257    263    269    271    277    281 
   283    293    307    311    313    317    331    337    347    349 
   353    359    367    373    379    383    389    397    401    409 
   419    421    431    433    439    443    449    457    461    463 
   467    479    487    491    499    503    509    521    523    541 
   547    557    563    569    571    577    587    593    599    601 
   607    613    617    619    631    641    643    647    653    659 
   661    673    677    683    691    701    709    719    727    733 
   739    743    751    757    761    769    773    787    797    809 
   811    821    823    827    829    839    853    857    859    863 
   877    881    883    887    907    911    919    929    937    941 
   947    953    967    971    977    983    991    997   1009   1013 
   1019   1021   1031   1033   1039   1049   1051   1061   1063   1069 
   1087   1091   1093   1097   1103   1109   1117   1123   1129   1151 
   1153   1163   1171   1181   1187   1193   1201   1213   1217   1223 
   1229   1231   1237   1249   1259   1277   1279   1283   1289   1291 
   1297   1301   1303   1307   1319   1321   1327   1361   1367   1373 
   1381   1399   1409   1423   1427   1429   1433   1439   1447   1451 
   1453   1459   1471   1481   1483   1487   1489   1493   1499   1511 
   1523   1531   1543   1549   1553   1559   1567   1571   1579   1583 
   1597   1601   1607   1609   1613   1619   1621   1627   1637   1657 
   1663   1667   1669   1693   1697   1699   1709   1721   1723   1733 
   1741   1747   1753   1759   1777   1783   1787   1789   1801   1811 
   1823   1831   1847   1861   1867   1871   1873   1877   1879   1889 
   1901   1907   1913   1931   1933   1949   1951   1973   1979   1987 
   1993   1997   1999   2003   2011   2017   2027   2029   2039   2053 
   2063   2069   2081   2083   2087   2089   2099   2111   2113   2129 
   2131   2137   2141   2143   2153   2161   2179   2203   2207   2213 
   2221   2237   2239   2243   2251   2267   2269   2273   2281   2287 
   2293   2297   2309   2311   2333   2339   2341   2347   2351   2357 
   2371   2377   2381   2383   2389   2393   2399   2411   2417   2423 
   2437   2441   2447   2459   2467   2473   2477   2503   2521   2531 
   2539   2543   2549   2551   2557   2579   2591   2593   2609   2617 
   2621   2633   2647   2657   2659   2663   2671   2677   2683   2687 
   */
}
