/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package writer;

import java.io.FileWriter;

/**
 *
 * @author boadu
 */
public class Writer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{    
           FileWriter fw=new FileWriter("E:\\test.in");
           String[] ary = "abcdefghijklmnopqrstuvwxyz".split("");
           for(int i=0;i<=99;i++){
                for(int j=0;j<=99;j++){
                    for(int k=0;k<=26;k++){
                        if(k ==0){
                            fw.write(i+" eps "+ j+ " \n"); 
                        }else{
                            fw.write(i+" "+ary[k-1]+" "+ j+ " \n"); 
                        }
                    }
               }
                
           } 
           fw.close();    
          }catch(Exception e){System.out.println(e);}    
          System.out.println("Success...");    
     }    
    
    
}
