

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author boadu
 */
public class NfaDfa {

    public static Map<String,String> table = new HashMap<>();
    public static Set<String> symbols = new  HashSet<>();
    public static Set<String> state = new  HashSet<>();
    public static Set<String> finalStates = new  HashSet<>();
    public static List<Integer> mfinalStates = new ArrayList<>();
    public static Map<String,List<String>> epsilons = new HashMap<>();
   
    public static void main(String[] args) throws IOException {
       
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //read input lines
        int states = Integer.parseInt(br.readLine());
        //next is to read the transitions
        int transitions = Integer.parseInt(br.readLine());
        
        for (int i=0;i<transitions;i++){
            String [] temp = treatTransition(br.readLine());
        }
        
        
        int _finalStates = Integer.parseInt(br.readLine());
         for (int i=0;i<_finalStates;i++){
            finalStates.add(br.readLine());
        }
        
        
        //Sink state
        for (int i=0;i<states;i++){
            state.add(String.valueOf(i));
        }
      
        
        
        
        
        
        for (int i=0;i<states;i++){
            state.add(String.valueOf(i));
        }
       
        
        
         
         
        for (String st : state){
            for (String s : symbols){
                //epsilons of state.
                List<String> temp = epsilons.get(st);
                if(temp!=null){
                    if(!temp.isEmpty()){
                        for (String xlx : temp){
                            if(!table.containsKey(st+s)){
                                table.put(st+s, xlx);
                    
                            }else{
                                table.put(st+s, table.get(st+s)+","+xlx);
                             }
                        } 
                    }
                }
            }
            
        }
        
       
        for (String s : symbols){
            table.put("100"+s, "100");
             for (String st : state){
                if(!table.containsKey(st+s)){
                    table.put(st+s, "100");
                    
                }
            }
        }
        
        getEpsilonClosure();
        
        getClosure2();
        
        sortStates();
       
        doRecurssion();  
        
        
        
        
        
        System.out.println("*******start***********");
        for(String x: table.keySet()){
            System.out.println(x+" "+ table.get(x));
        }
        System.out.println("******************");
        
        
        List<String> myState = new ArrayList<>(state);
        System.out.println(myState.size());
        System.out.println(myState.size()*symbols.size());
        for (String key : myState){
            for(String sym : symbols){
                System.out.println(myState.indexOf(key) +" "+ sym+" "+myState.indexOf(table.get(key+sym)));
            }
        }
        
        for (String key : state){
                String[] x = split(key);
                for (int i=0; i<x.length;i++){
                          if(finalStates.contains(x[i])){
                              mfinalStates.add(myState.indexOf(key));
                          }
                }
        }
        
        
        System.out.println(myState.size()-mfinalStates.size());
        for (int i=0;i<myState.size();i++){
            if(mfinalStates.contains(i)){}
            else System.out.println(i);
        }
       
      
    }
    
     public static String[] treatTransition(String transitionLine){
             String[] splited= transitionLine.split("\\s+");
        return splited;
    }
    
     
     public static void doRecurssion(){
         
         //System.out.println("heb "+state);
         
         boolean isnewState = false;
         Set<String> newState = new  HashSet<>();
         
         
         for (String st : table.keySet()){
            if(!state.contains(table.get(st))){
                state.add(table.get(st));
            }
            
        }
         
        for (String s : symbols){
             for (String st : state){
                if(!table.containsKey(st+s)){
                    
                    String[] ss = split(st);
                    TreeSet<String> temp = new TreeSet<>();
                    for (int i=0;i<ss.length;i++){
                        temp.add(ss[i]);
                    }
                    table.put(st+s, StatefromSet(temp,s));
                    newState.add(st+s);
                    isnewState = true;
                }
            }
        } 
         
        if(isnewState){
            doRecurssion();
         }
     }
     
     public static String[] split(String x){
         return x.split(",");
     }
     
     public static String StatefromSet(TreeSet<String> x,String sym){
         String st = "";
         for (String ss: x){
             String tem = table.get(ss+sym);
             if(tem==null){
                 if(st==""){
                     st = "100";
                 }
             }
             else{
                 if(st == "100")st = tem;
                 else if(st == "")st = tem;
                 else{
                     if(tem!="100" && hasit(st,tem)==false){
                        st = st+","+tem;
                     }
                 }
             }
         }
         
         return st;
     }

    private static boolean hasit(String st, String tem) {
        String[] tm= split(st);
        
        for (int i=0;i<tm.length;i++){
            if(tem.equals(tm[i])){
                return true;
            }else if(st.equals(tem)){
                return true;
            }
        }
        return false;
    }

    private static void getEpsilonClosure() {
        boolean flag = false;
        for (String ss: state){
            List<String> x = epsilons.get(ss);
            if(x!=null){
                if(!x.isEmpty()){
                    for(String ppp : x){
                        for (String sym  : symbols){
                            boolean y  = Union(ss,ppp,sym);
                            if(flag == false) flag = y;
                        }
                    }
                }
            }
        }
        
        if(flag == true)getEpsilonClosure();
    
    }
    
    private static void getClosure2(){
        boolean flag = false;
        for (String ss: state){
            for (String sym  : symbols){
                String[] temp = split(table.get(ss+sym));
                for(int i=0;i<temp.length;i++){
                    List<String> zz = epsilons.get(temp[i]);
                        if(zz!=null){
                            if(!zz.isEmpty()){
                                for (String kk : zz){
                                    if(table.containsKey(ss+sym)){
                                        if(!Arrays.asList(split(table.get(ss+sym))).contains(kk)){
                                            if(kk!="100"){
                                                table.put(ss+sym,table.get(ss+sym)+","+kk);
                                                flag = true;
                                            }
                                        }else{
                                            
                                        }
                                    }
                                }
                            }
                        }
                    
                    }
                
                }
            }
        
        if(flag == true)getClosure2();
    }
    
    
    private static boolean Union(String a, String b,String symbol){
        /*System.out.println("*******start***********");
        for(String x: table.keySet()){
            System.out.println(x+" "+ table.get(x));
        }
        System.out.println("******************");*/
        
        boolean flag = false;
        String[] aa = split(table.get(a+symbol));
        String[] bb = split(table.get(b+symbol));
        List<String> list = Arrays.asList(aa);
        
        for(String is: bb){
            if(!is.equals("100")){
            if(!list.contains(is)){
                flag =true;
            if(table.containsKey(a+symbol)){
                if(table.get(a+symbol).equals("100")){
                    table.put(a+symbol, is);
                }else{
                   table.put(a+symbol, table.get(a+symbol)+","+is); 
                }
            }else{
                table.put(a+symbol, is);
            }
            }
        }
        }
        /*System.out.println("**********end********");
        for(String x: table.keySet()){
            System.out.println(x+" "+ table.get(x));
        }
        System.out.println("******************");*/
        
        return flag;
    }

    public static void sortStates(){
        for (String s : table.keySet()){
            String state = "";
           String[] temp = split(table.get(s));
           Arrays.sort(temp);
           for (int i=0;i<temp.length;i++){
               if(state.equals(""))state = temp[i];
               else state = state +","+ temp[i];
           }
           table.put(s, state);
        }
    }

    
}
