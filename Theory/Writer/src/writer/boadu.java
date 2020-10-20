

/**
 *
 * @author boadu
 */
public class boadu {
    
     public static void main(String[] args) throws InterruptedException {
         int i = 1;
         while (i > 0){
             for(int j=1;j<i;j++){
                 System.out.println(j+" "+(i-j));
                  Thread.sleep(1000);
             }
             i++;
         }
         

     }
}
