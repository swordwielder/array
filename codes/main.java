import java.util.HashSet;
import java.util.Set;

public class main
{
	public static void main2 (String [] args){
		
		Set<Integer> A = new HashSet<Integer>(5);
		A.add(1);
		A.add(2);
		A.add(5);
		A.add(5);
		A.add(8);
		A.add(3);
		System.out.println(A);
		
		Set<Integer> B = new HashSet<Integer>();
		B.add(2);
		B.add(4);
		B.add(9);
		B.add(8);
		System.out.println(B);
		
		// membership
		System.out.println(A.contains(2));
		System.out.println(A.contains(4));
		
		// A union B
		Set<Integer> unionAB = new HashSet<Integer>();
		unionAB.addAll(A);
		unionAB.addAll(B);
		System.out.println(unionAB);
		
		// A intersect B
		Set<Integer> intersectAB = new HashSet<Integer>();
		intersectAB.addAll(A);
		intersectAB.retainAll(B);
		System.out.println(intersectAB);
		
		// A - B
		Set<Integer> diffAB = new HashSet<Integer>();
		diffAB.addAll(A);
		diffAB.removeAll(B);
		System.out.println(diffAB);
	}
}