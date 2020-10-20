//package nfa;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class NFA {
    Map<Integer,States> States = new HashMap<>();
    Set<States> currentqueue = new HashSet<>();
    Set<States> nextqueue = new HashSet<>();
    boolean accept = false;
    
    
    private int numberOfStates;
    private int numberOfTransitions;
    private int numberOfFinalStates;
    private int numberofInputString;
    
    private String[] transitions;
    private String[] inputString;
    private int[] finalStates;

    public int getNumberOfStates() {
        return numberOfStates;
    }

    public void setNumberOfStates(int numberOfStates) {
        this.numberOfStates = numberOfStates;
    }

    public int getNumberOfTransitions() {
        return numberOfTransitions;
    }

    public void setNumberOfTransitions(int numberOfTransitions) {
        this.numberOfTransitions = numberOfTransitions;
    }

    public int getNumberOfFinalStates() {
        return numberOfFinalStates;
    }

    public void setNumberOfFinalStates(int numberOfFinalStates) {
        this.numberOfFinalStates = numberOfFinalStates;
    }

    public int getNumberofInputString() {
        return numberofInputString;
    }

    public void setNumberofInputString(int numberofInputString) {
        this.numberofInputString = numberofInputString;
    }

    public String[] getTransitions() {
        return transitions;
    }

    public void setTransitions(int x) {
        this.transitions = new String[x];
    }

    public String[] getInputString() {
        return inputString;
    }

    public void setInputString(int x) {
        this.inputString = new String[x];
    }

    public int[] getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(int x) {
        this.finalStates = new int[x];
    }
    
    
    
    public static void main(String[] args) throws IOException {
        
        NFA nfa = new NFA();
        boolean read = nfa.readInput();
        
        
        
        if(read==true){
            nfa.setState();
        for (int i=0;i<nfa.getNumberofInputString();i++){
            nfa.accept = false;
            nfa.nextqueue.clear();
            nfa.currentqueue.clear();
            nfa.currentqueue.add(nfa.States.get(0));
            //System.out.println(nfa.currentqueue);
            Set<States> ss = nfa.States.get(0).getEpsilonClosure2(nfa.currentqueue,new HashSet<States>());
            if (ss!=null){
                nfa.currentqueue.addAll(ss);
            }
            //System.out.println(nfa.currentqueue);
            if(nfa.getNumberOfStates()==nfa.getNumberOfFinalStates()){
                nfa.accept =true;
            }else{
            nfa.startTransition(nfa.getInputString()[i].split(""),nfa.getNumberOfStates());
            }
            if(nfa.accept){
                 System.out.println("accept");
            }else{
                 System.out.println("reject");
            }
        }
       }
        else{
            System.out.println("reject");
        } 
        
    }
    
    
    public void setState(){
        
        
        States s;
        //creating n states
        for (int i=0;i<this.getNumberOfStates();i++){
             s = new States(i);
             States.put(i,s);
        }
        
        
        //set transitions
         for (int i=0;i<this.getNumberOfTransitions();i++){
             String[] splited= this.getTransitions()[i].split("\\s+");
             if(splited[1].equals("eps")){
                 States.get(Integer.parseInt(splited[0])).setImmediateEpsilonClosure(States.get(Integer.parseInt(splited[2])));
             }else{
                 setTransition(Integer.parseInt(splited[0]),Integer.parseInt(splited[2]),splited[1]);
             }
                 
        }
             
             
        //set final states   
        for (int i=0;i<this.getNumberOfFinalStates();i++){
            States.get(this.getFinalStates()[i]).setFinal(true);
       }
        
        
        
    }
    
    public boolean readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        
        this.setNumberOfStates(Integer.parseInt(br.readLine()));
        if(this.getNumberOfStates()>0 && this.getNumberOfStates()<=100){
        this.setNumberOfTransitions(Integer.parseInt(br.readLine()));
        int tot = (this.getNumberOfStates()*this.getNumberOfStates())*27;
        if(this.getNumberOfTransitions()>=0 && this.getNumberOfTransitions()<=tot){
        this.setTransitions(this.getNumberOfTransitions());
        for (int i=0; i<this.getNumberOfTransitions();i++){
            this.getTransitions()[i] = br.readLine();
        }
        
        this.setNumberOfFinalStates(Integer.parseInt(br.readLine()));
        if(this.getNumberOfFinalStates()>=0 && this.getNumberOfFinalStates()<=this.getNumberOfStates()){
        this.setFinalStates(this.getNumberOfFinalStates());
        for (int i=0; i<this.getNumberOfFinalStates();i++){
            this.getFinalStates()[i] = Integer.parseInt(br.readLine());
        } 
        
        this.setNumberofInputString(Integer.parseInt(br.readLine()));
        if(this.getNumberofInputString()>=0 && this.getNumberofInputString()<=100){
        this.setInputString(this.getNumberofInputString());
        for (int i=0; i<this.getNumberofInputString();i++){
            this.getInputString()[i] = br.readLine();
        } 
        
        return true;
        }
        else{return false;}
        }
        else{return false;}
        }
        else{return false;}
        }
        else{return false;}
        
        
    }
    
   
     private void setTransition(int Start, int Destination, String key) {
         States s = States.get(Start);
         if(s.transition.containsKey(key)){
             s.transition.get(key).add(States.get(Destination));
         }else{
             HashSet<States> x = new HashSet<States>();
             x.add(States.get(Destination));
             s.transition.put(key,x);
         }
         
        
    }
    
    public void startTransition(String[] STRING,int num){
        
    for(String s : STRING){
        
        if(s.trim().equals("")){
            
        }
        
        else {
        for (States state : currentqueue){
            Set<States> states = state.Transition(s,num);
            if(states!=null){
                nextqueue.addAll(state.Transition(s,num));
            }
        }
    
        
            currentqueue = new HashSet<>(nextqueue);
            nextqueue.clear();
        }
        
    }
    
   for (States state : currentqueue){
       if(state.isFinal){
           accept =true;
           return;
        }
    }
}
    
    
    

    
    public class States{
        
        boolean isFinal = false;
        private int name;
        HashMap<String,Set<States>> transition;
        Set<States> epsilonClosure;
       
        
        States(){
        }
        
        States(int name){
            this.name = name;
            transition =  new HashMap<>();
            epsilonClosure = new HashSet<>();
        }
        
       
       
        
        
        @Override
        public String toString() { 
            return String.valueOf(this.name); 
        } 
        
        public void setFinal(boolean value){
            this.isFinal = value;
        }
        
        public void setImmediateEpsilonClosure(States state){
            this.epsilonClosure.add(state);
        }

        
        
        
        
        
        
    }
    
}
