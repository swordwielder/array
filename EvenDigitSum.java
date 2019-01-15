import java.util.*;

public class EvenDigitSum {
    // write your code here
    public static int getEvenDigitSum(int number){
        
        if (number ==0){
            return 0;
        }else if (number < 0){
            return -1;
        }else{
            String test = Integer.toString(number);
            
            int a =0;
            for (int i =0; i < test.length(); i++){
                int temp = Character.getNumericValue(test.charAt(i));
                
                if (temp%2==0){
                    a += temp;
                }
            }
            
            return a;
            
        }
    }
    
}
