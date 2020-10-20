

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class CFGP {

    public static Map<String, Set<String>> rules = new HashMap<>();
    public static List<String> strings = new ArrayList<>();
    static String start;
    
    
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        readInput();
        
        for (String s: rules.keySet()){
            //System.out.println(s+ " "+rules.get(s));
        }
        
        
        //Set<String> keep = zip(new String[]{"A","B"},new String[]{"A","B","S"});
       
        
        //System.out.println(setToString(keep)); 
        //System.out.println(start); 
        
        for (String s: strings){
         boolean dec = check(s,start);
         if(dec)System.out.println("yes");
         else System.out.println("no");
        }
        
        
    }
    
    
    public static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        
        int numberofRules = Integer.parseInt(br.readLine());
        
        for (int i=0; i<numberofRules;i++){
            Set<String> s = new HashSet<>() ;
            String[]  line = split(br.readLine());
            if(i==0)start = line[0];
            if(rules.containsKey(line[1]))
                s = rules.get(line[1]);
                s.add(line[0]);
                rules.put(line[1],s);
        }
       
        int numberofString = Integer.parseInt(br.readLine());
        
        for (int i=0; i<numberofString;i++){
            strings.add(br.readLine());
        }
        
        br.close();
    }
    
    public static String[] split(String x){
         return x.split(" ");
     }
    
    public static String[] split2(String x){
         return x.split(",");
     }
    
    public static Set<String> zip(String[] a, String[] b){
            Set<String> cartprod =  new HashSet<>();
        for (int i=0; i<a.length;i++){
            for(int j=0;j<b.length;j++){
                Set<String> x =  rules.get(a[i]+b[j]);
                if(x!=null){
                    if(!x.isEmpty()){
                        cartprod.addAll(x);
                    }
                }
                
            }
        }
        if(cartprod==null || cartprod.isEmpty())cartprod.add("");
        return cartprod;
        
    }
    
    public static String setToString(Set<String> x){
        return String.join(",", x);
    }

    private static boolean check(String s, String start) {
        boolean dec = false;
        int len = s.length();
    String[][] table = new String[len+1][len+1];
        for(int i=1; i<=len;i++){
            Set<String>  ss = rules.get(s.substring(i-1, i));
            if(ss!=null){
                if(!ss.isEmpty()){
                    table[i][1] = setToString(ss);
                }else table[i][1] = "";
            }else table[i][1] = "";
        }
        
        for(int i = 2;i<=len;i++ ){
            for(int j=1;j<=(len-i+1);j++){
                for(int k = 1; k<=(i-1);k++){
                    table[j][i] = prune(table[j][i],zip(split2(table[j][k]),split2(table[j+k][i-k])));
                   }
            }
        }
        
        for (int i=0;i<table.length;i++)
        
        if(table[1][len].contains(start))dec =true;
    
        return dec;
    }

    private static String prune(String s, Set<String> zip) {
    //System.out.println(zip);
        for(String x : zip){
            if(s==null){
                s = x;
            }
            else if(!s.contains(x)){
                if(!s.equals("null")){
                s = s +","+x;
                }
            }
        }
     return s;
    }

    
    
}
