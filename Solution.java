package test;

import java.util.*;
import java.io.*;
import java.math.*;

public class Solution {
	public static void main (String [] args) {
		Scanner in = new Scanner(System.in);
		 
		System.out.println("Enter the number of temperatuer to analyze");
		int n = in.nextInt();
		
		if (n > 0 ) {
			int [] a = new int [n];
			for (int i =0; i< n; i++) {
				System.out.println("Enter the temperature");
				a[i] = in.nextInt();
			}
			int temp = a[0];
			
			for (int i = 0; i<a.length; i++) {
				if (Math.abs(temp) > Math.abs(a[i])) {
					temp = a[i];
				}
				if (Math.abs(temp) == Math.abs(a[i])){
					if (temp < a[i]) {
						temp = a[i];
					}
				}
			}
			
			while (in.hasNext()) {
				System.out.println(" next line is: " + in.nextLine());
			}
			
			System.out.println(temp);
		}else {
			System.out.println(n);
		}
		
		
		
		
	}
	
	
	
}
