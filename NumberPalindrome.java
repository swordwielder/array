import java.util.*;

public class NumberPalindrome {
    
    
    public static boolean isPalindrome(int number){
        
        int absolute = Math.abs(number);
        
        String test = Integer.toString(absolute);
        int j = test.length()-1;
        for (int i =0; i<test.length(); i++){
            if (test.charAt(i)!=test.charAt(j)){
                return false;
            }
            if (i ==j){
                break;
            }
            j--;
            
        }
        return true;
    }
    
    public static void main (String [] args){
        
        System.out.println(isPalindrome(-1221));
        System.out.println(isPalindrome(707));
        System.out.println(isPalindrome(11212));
        
    }

}
