//package nfa;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class ENEFA {
    
    private static HashSet<String> symbols = new HashSet<String>();
    private static HashMap<String, Transition> transition_table = new HashMap<>();
    private static HashMap<String,States> states_table = new HashMap<String,States>();
    private static HashMap<String,Set<String>> epsilon_table = new HashMap<String,Set<String>>();
    private static HashMap<String, Transition> inputTransition = new HashMap<>();
    private static HashMap<String,Set<String>> input_epsilon = new HashMap<String,Set<String>>();

    
    private static int in_trans = 0;
    private static int trans = 0;
    
    
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String,States> input_states_table = new HashMap<String,States>();
        
        //read input lines
        int number_of_states = Integer.parseInt(br.readLine());
        for (int i=0;i<number_of_states;i++){
            input_states_table.put(String.valueOf(i),new States(i));
        }
        
        //next is to read the transitions
        int number_of_transition = Integer.parseInt(br.readLine());
        for (int i=0;i<number_of_transition;i++){
            String[] temp = split(br.readLine());
            if(temp[1].equals("eps")){
                if(input_epsilon.containsKey(temp[0])){
                    input_epsilon.get(temp[0]).add(temp[2]);
                }else{
                    Set<String> tem = new HashSet<String>();
                    tem.add(String.valueOf(temp[2]));
                    input_epsilon.put(String.valueOf(temp[0]), tem);
                }
            }
            else{
            inputTransition.put(String.valueOf(i), new Transition(i,new States(Integer.parseInt(temp[0])),new States(Integer.parseInt(temp[2])),temp[1]));
            symbols.add(temp[1]);
            in_trans++;
            }
        }
        
        int number_of_finalStates = Integer.parseInt(br.readLine());
        for (int i=0;i<number_of_finalStates;i++){
            input_states_table.get(String.valueOf(i)).setFinal(true);
        }
        
        //sink state
        input_states_table.put(String.valueOf(101),new States(101));
        
        resolveEpsilon();
        
        transition(input_states_table);
        
        
        for (Transition t: transition_table.values()){
            System.out.println(t.id+" "+t.start+" "+t.Symbol+" "+t.end);
        }
        
        
    }
    
    private static void transition(HashMap<String, States> input_states_table) {
    
        for(States state : input_states_table.values()){
            Set<String> temp = new HashSet<>();
            temp.add(state.toString());
            if(epsilon_table.containsKey(state.toString())){
            temp.addAll(epsilon_table.get(state.toString()));}
            
            for(String s: temp){
                for(String symb: symbols){
                    
                    if(s.equals("101")){
                        transition_table.put(String.valueOf(trans), new Transition(trans,new States(101),new States(101),symb));
                    }
                    trans++;
                }
            }
        }
    
    }

    private static void resolveEpsilon() {
        
        for (String st : input_epsilon.keySet()){
            HashSet<String> exp = new HashSet<String>();exp.add(st);
            epsilon_table.put(st,recursion(st,exp,input_epsilon.get(st)));
        }
    
    }
    
    
    private static Set<String> recursion(String item,Set<String> exp,Set<String> imEps ){
        
        
        HashSet<String> temp = new HashSet<String>();
        temp.add(item);
        for (String s : imEps){
            temp.add(s);
            if(epsilon_table.containsKey(s)){
                temp.addAll(epsilon_table.get(s));
            }
            else if(exp.contains(s)){
            }
            else if(input_epsilon.containsKey(s)){
                exp.add(s);
                temp.addAll(recursion(s,exp,input_epsilon.get(s)));
            }
        }
        return temp;
    }

    
    public static class States{
        
        boolean isFinal = false;
        private int name;
        Set<Integer> epsilonClosure = new HashSet<>();
        
        States(int name){
            this.name = name;
            epsilonClosure.add(name);
        }
      
        @Override
        public String toString() { 
            return String.valueOf(this.name); 
        } 
        
        public void setFinal(boolean value){
            this.isFinal = value;
        }
        
        public void addEpsilon(int state){
            epsilonClosure.add(state);
            //Collections.sort(null, null);
        }
        
    }
    
    
    public static class Transition{
        
        int id;
        States start;
        States end;
        String Symbol;
        
        
        public Transition(int id, States start, States end, String symbol){
            this.id= id; this.start = start; this.end = end; this.Symbol = symbol;
        }
        
        @Override
        public String toString() { 
            return String.valueOf(this.start+"_"+this.Symbol+"_"+this.end); 
        } 
    }
    
    
     public static String[] split(String x){
         return x.split(" ");
     }
     
     public static String stripSpace(String x){
         return x.replaceAll(" ", "");
     }
    
}
