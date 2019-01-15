import java.util.*;

public class SharedDigit {
    // write your code here
    public static boolean hasSharedDigit(int a, int b ){
        if (a >99 || a <10 || b > 99 || b < 10){
            return false;
        } else{
            String first = Integer.toString(a);
            String second = Integer.toString(b);   
            if (first.charAt(0) == second.charAt(0)){
                return true;
            }else if (first.charAt(0)==second.charAt(1)){
                return true;
            }else if (first.charAt(1)==second.charAt(0)){
                return true;    
            }else if (first.charAt(1)==second.charAt(1)){
                return true;
            }else{
                return false;   
            }
        }
    }
    
}
