/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author boadu
 */
public class NFAtoDFA {
    
    public static Map<String, State> table = new HashMap<>();
    public static List<String> symbols = new ArrayList<>();
    
    
    
   
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       
        NFAtoDFA nfatodfa = new NFAtoDFA();
        nfatodfa.table.get("");
        
        readInput();
        
        for (String key: table.keySet()){
            System.out.println(table.get(key));
        }
        
        
    }
    
    public static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        
        //read input lines
        int n = Integer.parseInt(br.readLine());
        //Sink state
        State st = new State(new int[]{100});
        NFAtoDFA.table.put(st.name, st);
        
        //create n states
        for (int i=0;i<n;i++){
            st = new State(new int[]{i});
            NFAtoDFA.table.put(st.name, st);
            
        }
        
        
        //next is to read the transitions
        n = Integer.parseInt(br.readLine());
        for (int i=0;i<n;i++){
            String temp[] = treatTransition(br.readLine()); 
            NFAtoDFA.symbols.add(temp[1]);
             for (String key: table.keySet()){
                 
        }
        }
        
        
        
        
    }
    
    
    public boolean tableHas(Map<String, Map<String,String>> table,String Start,String TransitionSymbol){
        boolean has = false;
        if(!table.containsKey(Start))return has;
        else{
            if(!table.get(Start).containsKey(TransitionSymbol))return has;
            else return true;
        }
    }
    
    public static String[] treatTransition(String transitionLine){
             String[] splited= transitionLine.split("\\s+");
        return splited;
    }
    
    
    
    
    public static class State{
        
        //Sinks states is given number 100;
        
        String name;
        int[] states;
        Map<String,String> transition;
        
        
        
        public State(int[] states){
            this.name = convertArrayToStringUsingStreamAPI(states); 
            this.states = states;
            transition =  new HashMap<>();
        }
        
        
        public  String convertArrayToStringUsingStreamAPI(int[] intArray) {
            String joinedString = "";
            for(int i : intArray){
                joinedString = joinedString+ String.valueOf(i);
            }
            return joinedString;
        }
        

    @Override
    public String toString() {
        return this.name; 
    }
    
        
        
    }
    
   
    
    

   
    
}
