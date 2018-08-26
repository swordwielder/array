package test;
import java.util.*;
import java.math.*;
import java.io.*;

public class LuckyNumber {
	
	public static void main (String [] args) {
		Scanner in = new Scanner(System.in);
		int a,b;
		System.out.println("Enter first number");
		a = in.nextInt();
		System.out.println("Enter second number");
		b = in.nextInt();
		if(a==b) {
			System.out.println(0);
		}else {
			System.out.println( lucky(a,b) );
		}
	}
	
	public static int lucky(int a, int b) {
		int c = b-a;
		int d = a;
		String first;
		
		for (int i = 0; i<c; i++) {
			first = Integer.toString(d);
				for(int j=0; j< first.length(); j++ ) {
					
					
				}
			
		}
		
		return 1;
	}
}
