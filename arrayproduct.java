

import java.util.*;
public class arrayproduct {
	
	public static ArrayList<Integer> list1 = new ArrayList<Integer>();
	public static ArrayList<Integer> count = new ArrayList<Integer>();
	
	public static void main( String [] args) {
		
		
		int [] b = {1,2,3,4,5};
		
		int [] a = {1,2,3,1,1,1};
		mode(a);
		
		//print(list1);
		//print(count);
		int [][]c = appearance(list1,count);
		
		display(c);
		
		String s = "anagram";
		String t= "nagaram";
		
		
		System.out.print(isAnagram(s,t));
		
		
	}
	
	public static int [] products(int [] a) {
		
		int counter =1;
		int [] b = new int [a.length];
		
		for (int i =0; i<a.length; i++) {
			for (int j =0; j< a.length; j++) {
				if (i !=j ) {
					counter = counter*a[j];
				}
			}
			b[i]=counter;
			counter =1;
		}
		
		return b;
	}
	
	public static boolean powerOfFour(int a) {
		if (a==1) {
			return true;
		}else if(a % 4 != 0 || a==0) {
			return false;
		}else {
			return powerOfFour(a/4);
		}
	}
	
	public static void mode (int [] a) {
		int [] b = sort(a);
		int current = a[0];
		int counter = 0;
		int currentposition =0;
		if (list1.isEmpty()) {
			list1.add(a[0]);
		}
		for (int i = currentposition ; i <b.length; i++) {
			if (current == a[i])
				counter++;
			if (current !=a[i]) {
				list1.add(a[i]);
				count.add(counter);
				current =a[i];
				counter=1;
			}
		}
		count.add(counter);
	}
	
	public static int [][]appearance(ArrayList<Integer> fir,ArrayList<Integer>sec ){
		int [][] a = new int[2][fir.size()];
		
		for (int i=0; i<a[0].length; i++) {
			a[0][i] = fir.get(i);
		}
		for (int i=0; i<a[1].length; i++) {
			a[1][i] = sec.get(i);
		}
		
		return a;
	}
	
	public static void print(ArrayList<Integer> a) {
		
		for (int i =0; i< a.size(); i++) {
			System.out.print(a.get(i) + " ");
		}
		System.out.println();
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
	
	public static boolean Palindrome(String text) {
	    String clean = text.replaceAll("\\s+", "").toLowerCase();
	    int length = clean.length();
	    int forward = 0;
	    int backward = length - 1;
	    while (backward > forward) {
	        char forwardChar = clean.charAt(forward++);
	        char backwardChar = clean.charAt(backward--);
	        if (forwardChar != backwardChar)
	            return false;
	    }
	    return true;
	}
	
	public static boolean isAnagram(String firstWord, String secondWord) {
	     char[] word1 = firstWord.replaceAll("[\\s]", "").toCharArray();
	     char[] word2 = secondWord.replaceAll("[\\s]", "").toCharArray();
	     Arrays.sort(word1);
	     Arrays.sort(word2);
	     return Arrays.equals(word1, word2);
	}

}
