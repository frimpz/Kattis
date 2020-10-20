
//package turingcuberec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author boadu
 */
public class TuringCubeRec {

    
    static TuringMachineTape input;
    static TuringMachineTape tape1;
    static TuringMachineTape tape2;
    static TuringMachineTape tape3;
    static char symbol ='a';
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = new TuringMachineTape(br);
        TuringMachineUtility.insertBegin(input);
        tape1 = new TuringMachineTape(); 
        TuringMachineUtility.insertBegin(tape1);
        tape2 = new TuringMachineTape(); 
        TuringMachineUtility.insertBegin(tape2);
        tape3 = new TuringMachineTape(); 
        TuringMachineUtility.insertBegin(tape3);
        
        
        /*
        start copying
        x^3 + 3x^2 +3^x +1
        */
        
        for(;;){
            

            while(input.read() != TuringMachineTape.BLANK_SYMBOL && tape3.read()!= TuringMachineTape.BLANK_SYMBOL){
                input.right();
                tape3.right();
            }
            if(input.read()== TuringMachineTape.BLANK_SYMBOL && tape3.read()== TuringMachineTape.BLANK_SYMBOL){
                TuringMachineTape.accept();
            }
            else if(input.read()== TuringMachineTape.BLANK_SYMBOL && tape3.read()!= TuringMachineTape.BLANK_SYMBOL){
                TuringMachineTape.reject();
            }
            if(input.read()!= TuringMachineTape.BLANK_SYMBOL && tape3.read()== TuringMachineTape.BLANK_SYMBOL){
                tape3.write(symbol);
                tape3.right();
                TuringMachineUtility.rewind(tape2);
                copyTapetoTape3Times(tape3,tape2);
                TuringMachineUtility.rewind(tape1);
                copyTapetoTape3Times(tape3,tape1);
                TuringMachineUtility.rewind(tape1);
                tape2.write(symbol);
                tape2.right();
                copyTapetoTape2Times(tape2,tape1);
                tape1.write(symbol);
                input.right();
            }
            /*
        dump all tapes
        */
        //input.debug();
        //tape1.debug();
        //tape2.debug();
        //tape3.debug();
            
            TuringMachineUtility.rewind(input);
            TuringMachineUtility.rewind(tape3);
        }
        
        
        
    }
    
     public static  void copyTapetoTape3Times(TuringMachineTape cpyTo, TuringMachineTape cpyFrm){
        while(cpyFrm.read() != TuringMachineTape.BLANK_SYMBOL){
                    cpyTo.write(cpyFrm.read());
                    cpyTo.right();
                    cpyTo.write(cpyFrm.read());
                    cpyTo.right();
                    cpyTo.write(cpyFrm.read());
                    cpyTo.right();
                    cpyFrm.right();
        }    
    }
     
      public static void copyTapetoTape2Times(TuringMachineTape cpyTo, TuringMachineTape cpyFrm){
            while(cpyFrm.read() != TuringMachineTape.BLANK_SYMBOL){
                    cpyTo.write(cpyFrm.read());
                    cpyTo.right();
                    cpyTo.write(cpyFrm.read());
                    cpyTo.right();
                    cpyFrm.right();
                }
    }
    
}
