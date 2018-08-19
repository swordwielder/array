import java.util.*;

public class factorial{

	public static void main (String [] args)
	{

		System.out.println("enter a number ") ;
		Scanner input = new Scanner(System.in);
		int a = input.nextInt();

		System.out.println(" The factorial is " + factor(a));
		
	}

	public static int factor(int n ){
		if (n >1){
			return  n *factor(n-1);
		}else{
			return 1;
		}
	}
}
