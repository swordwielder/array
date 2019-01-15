import java.util.*;

public class FirstLastDigitSum {
    
    public static int sumFirstAndLastDigit(int number){
        
        if (number == 0){
            return 0;
        }else if (number < 0){
            return -1;
        }else{
            String test = Integer.toString(number);
            int a = Character.getNumericValue(test.charAt(0));
            int b = Character.getNumericValue(test.charAt(test.length()-1));
            return a+b;       
        }
    }  
}
