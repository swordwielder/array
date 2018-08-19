import java.util.*;

public class factorial{

	public static void main (String [] args)
	{

		System.out.println("enter a number ") ;
		Scanner input = new Scanner(System.in);
		int a = input.nextInt();

		System.out.println(" The factorial is " + factor(a));
		System.out.println("The facotiral for for loops is " + fact(a));
		
	}

	public static int factor(int n ){
		if (n >1){
			return  n *factor(n-1);
		}else{
			return 1;
		}
	}
	
	public static int fact( int n){
        	int counter = 1;
        	for (int i =1; i <= n; i++){
            		counter  *= i;
        	}
        	return counter;
        
	}
}
