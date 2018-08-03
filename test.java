package test;
import java.util.*;
public class test {
	
	public static int add(int m) {
		int a = m+10;
		return a;
	}
	public static void main (String [] args) {
		Scanner bucker = new Scanner(System.in);
		
		System.out.println("enter a number!");
		int l= 0;
		boolean loop = true;
		do {		
			try { 
				l = bucker.nextInt();
				System.out.println("you put: " + l);
				loop = false;
			} catch (InputMismatchException efre) {
				System.err.println("You entered a non integer, enter a different number ");
				bucker.next();
			}
		}while (loop);
		int result = add(l);
		System.out.println("The result is  "+  result );
		
		rando();
	}
	
	public static void rando() {
		int ray [] = {1,9,4,4,5,2};
		int [] m;
		m= new int [] { 9,7,2,5,8,3 };
		int [][] doub = combo(m,ray );
		display(doub);
		display(combo(sort(ray),sort(m)));
		int []b = new int [20];
		int[]c= new int [20];
		c = populate(c);
		b= populate(b);
		
		display(combo(b,c));
		display(combo(sort(b),sort(c)));
		
		LinkedList<String> hero= new LinkedList<String>();
		
	}
	
	public static int [][] combo(int [] m, int[] p ){
		int [][] a = new int [2][m.length];
			for (int i = 0; i < m.length; i++) {
				a[0][i] = m[i];
				a[1][i] = p[i];
			}
			return a;
	}
	
	public static void display(int [][]l ) {
		for (int i =0; i<l.length;i++) {
			for (int j=0; j<l[i].length;j++){
				System.out.print(l[i][j] +" ");
			}
			System.out.println();
		}
	}
	
	public static int [] sort(int [] a ) {
		int temp;
		int counter =0;
		for (int j =0; j<a.length;j++) {
			for (int i =1;i<a.length-counter; i++) {
				if ( a[i] < a[i-1] ) {
					temp = a[i-1];
					a[i-1] = a[i];
					a[i] = temp;
				}
			}
			counter++;
		}
		return a;
	}
	
	public static int [] populate(int []a) {
		for (int i=0; i<a.length; i++) {
			int r = (int)(Math.random()*20);
			a[i]=r;
		}
		return a;
	}
	
	
}