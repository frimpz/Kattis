

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author boadu
 */
public class SetOperations {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        List<String> c = new ArrayList<>();
        List<String> d = new ArrayList<>();
        
        readInput(c,d);
        
        SetOperations setops = new SetOperations();
        display(Sort(Union(c,d)));
        System.out.println("");
        display(Sort(Intersect(c,d)));
        System.out.println("");
        display(Sort(Crossproduct(c,d)));
    }
    
    
    public static List<String> Union(List<String> a, List<String> b){
        List<String> temp = new ArrayList<>(a);
        for (String i : b){
           if(!a.contains(i)){
               temp.add(i);
           }
       }
        return temp;
    }
    
    public static List<String> Intersect(List<String> a, List<String> b){
       List<String> found = new ArrayList<>();
       List<String> temp = new ArrayList<>(a);
       for (String i : a){
           if(!b.contains(i)){
               found.add(i);
           }
       }
        temp.removeAll(found);
        return temp;
        
    }
    
    public static List<String> Crossproduct(List<String> a, List<String> b){
       List<String> temp = new ArrayList<>();
       for (String i : a){
           for (String j : b){
           temp.add(i+" "+j);
       }
       }
        return temp;
    }
    
    /**
     *
     * @param data
     * @return
     */
    public static List<String> Sort(List<String> data){
       List<String> temp = new ArrayList<>(data); 
       Collections.sort(temp);
       return temp; 
    }
    
    public static void readInput(List<String> a, List<String> b){
        Scanner sc = new Scanner(System.in);
            int len = sc.nextInt();
            for (int x= 0 ; x<len;x++){
                a.add(sc.next());
            }
            len = sc.nextInt();
            for (int x= 0 ; x<len;x++){
                b.add(sc.next());
            }
        
    }
    
    public static void display(List<String> a){
        for (String i : a){
           System.out.println(i);
       }
        
    }
  
}
