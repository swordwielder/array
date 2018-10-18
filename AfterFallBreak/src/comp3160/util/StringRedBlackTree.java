package comp3160.util;
import java.util.LinkedList;
import java.util.Queue;

public class StringRedBlackTree extends RedBlackTree<String>
{	
	public String findClosest (String s)
	{
		if (root != null){
			@SuppressWarnings("rawtypes")
			Queue<Node> Q = new LinkedList<Node>();
			Q.add(root);
			Node<String> r = null;
			char [] s1 = s.toCharArray();
			char [] r1 = root.value.toCharArray();
			int min = editDistance(r1,s1);

			while(! Q.isEmpty()) {
				@SuppressWarnings("unchecked")
				Node<String> x = Q.remove();
				char [] s2 = x.value.toCharArray();
				if (min > editDistance(s1,s2)){
					min = editDistance(s1, s2);
					r = x;
				}
				if(x.leftChild != null) {
					Q.add(x.leftChild);
				}
				if(x.rightChild != null) {
					Q.add(x.rightChild);
				}
			}
			return r.value;
		}
		return null;
	}

	private int min(int a, int b, int c)
	{
		return (Math.min(Math.min(a, b), c));
	}

	private int editDistance(char[] s1, char []s2)
	{
		int m = s1.length;
		int n = s2.length;
		int [][] d = new int [m+1][n+1];
		for (int i =0; i <=m;i++) {
			d[i][0]=i;
		}
		for (int j =0; j<=n;j++)
		{
			d[0][j]=j;
		}
		for (int i =1; i<=m; i++)
		{
			for (int j=1; j<=n; j++)
			{
				int c = 0;
				if (s1[i-1] != s2[j-1])
				{
					c=1;
				}
				d[i][j] = min(d[i-1][j] +1, d[i][j-1]+1, d[i-1][j-1]+c);
			}
		}
		return (d[m][n]);
	}
}
