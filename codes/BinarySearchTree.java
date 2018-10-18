import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import BinarySearchTrees.Node;

//import BinarySearchTrees.Node;

class BinarySearchTree<E extends Comparable<E>>
{
	class Node
	{
		Node leftChild;
		Node rightChild;
		Node parent;
		E value;
		
		@Override
		public String toString()
		{
			return value.toString();
		}
	}

	Node root;

	BinarySearchTree()
	{
		// Empty constructor.
	}

	BinarySearchTree(E[] array)
	{
		for (int i =0; i <array.length; i++){
			if (array[i] != null)
				insert(array[i]);
		}
	}

	void insert(E value)
	{
		if(root == null)
		{
			root = new Node();
			root.value = value;
			return;
		}
		Node p = root;
		Node q = null;
		while(p != null)
		{
			q = p;
			if(value.compareTo(p.value) < 0)
			{
				p = p.leftChild;
			}
			else
			{
				p = p.rightChild;
			}
		}
		Node x = new Node();
		x.parent = q;
		x.value = value;
		if(value.compareTo(q.value) < 0)
		{
			q.leftChild = x;
		}
		else
		{
			q.rightChild = x;
		}
	}

	Node max()
	{
		return max(root);
	}

	Node max(Node x)
	{
		if (x == null)
			return null;
		Node p = x;
		while (p.rightChild != null) {
			p = p.rightChild;
		}
		return p;
	}

	Node min(Node x)
	{
		if(x == null)
		{
			return null;
		}
		Node p = x;
		while(p.leftChild != null)
		{
			p = p.leftChild;
		}
		return p;
	}

	Node min()
	{
		return min(root);
	}

	int size()
	{
		return size(root);
	}

	int size(Node x)
	{
		if (x != null) {
			return 1+size(x.leftChild)+size(x.rightChild);
		} else 
			return 0;
	}

	Node successor(Node x)
	{
		Node p = null;
		Node q = null;
		if(x.rightChild != null)
		{
			return min(x.rightChild);
		}
		else
		{
			p = x.parent;
			q = x;
			while(p != null && q == p.rightChild)
			{
				q = p;
				p = p.parent;
			}
		}
		return p;
	}

	Node search(E value, Node x)
	{
		if(x == null)
		{
			return null;
		}
		else if(value.equals(x.value))
		{
			return x;
		}
		else if(value.compareTo(x.value) < 0)
		{
			return search(value, x.leftChild);
		}
		else
		{
			return search(value, x.rightChild);
		}
	}

	Node search(E value)
	{
		return search(value, root);
	}

	Node predecessor(Node x)
	{
		Node a = null;
		Node b = null;
		if (x.leftChild != null) {
			a = x.leftChild;
			while (a.rightChild != null) {
				a = a.rightChild;
			}
			return a;
		} else {
			a = x.parent;
			b = x;
			while (a != null && b == a.leftChild) {
				b = a;
				a = a.parent;
			}
			return a;
		}
	}

	int height (Node x) 
	{
		int leftTree=-1;
		int rightTree=-1;
		if (x.leftChild != null)
			leftTree = height(x.leftChild);
		if (x.rightChild != null)
			rightTree = height(x.rightChild);
		if (leftTree > rightTree)
			return 1+leftTree;
		else 
			return 1+rightTree;
	}
	
	int maxH ()
	{
		Node x = root;
		Stack<Node> Q = new Stack<Node>();
		Q.push(x);
		int h =0, Lh =0, Rh=0;
		while (!Q.empty()){
			Node a = Q.pop();
			if(a.leftChild != null)
			{
				Lh++;
				Q.push(a.leftChild);
			}
			if(a.rightChild != null)
			{
				Rh++;
				Q.push(a.rightChild);
			}
			if (a.rightChild == null && a.leftChild == null)
				return Math.max(Lh, Rh);
		}	
		return -1;
	}

	int height()
	{
		Node x = root;
		if (x == null)
			return -1;
		else if(x.leftChild==null && x.rightChild==null)
			return 0;
		else
			return height(x);
	}
	
	boolean hasSameElements(BinarySearchTree<E> secondTree)
	{
		Node a = root;
		Node b = secondTree.root;

		if (root == null && secondTree.root == null) 
			return true;
		else if (size() != secondTree.size()) {
			return false;
		} else {
			if (a !=null ){
				a = min();
				b = secondTree.min();
				while(successor(a) != null) {
					if (a.value != b.value)
						return false;
					a = successor(a);
					b = successor(b);
				}
				if (a.value != b.value)
					return false;
				return true;
			}
		}
		return false;
	}
	
	void preorder(Node x) {
		if(x != null) {
			System.out.print(x.value + " ");
			preorder(x.leftChild);
			preorder(x.rightChild);
		}
	}

	void inorder(Node x) {
		if(x != null) {
			inorder(x.leftChild);
			System.out.print(x.value + " ");
			inorder(x.rightChild);
		}
	}

	void postorder(Node x) {
		if(x != null) {
			postorder(x.leftChild);
			postorder(x.rightChild);
			System.out.print(x.value + " ");
		}
	}

	public static void main(String[] args)
	{
		BinarySearchTree<Integer> tree1 = new BinarySearchTree<Integer>();
		tree1.insert(4);
		tree1.insert(1);
		tree1.insert(10);
		tree1.insert(15);
		
		BinarySearchTree<Integer> tree2 = new BinarySearchTree<Integer>();
		tree2.insert(10);
		tree2.insert(15);
		tree2.insert(8);
		tree2.insert(1);
		tree2.insert(4);
		
		
		//Comparable [] a = new Comparable [10];
		//BinarySearchTree<Integer> tree3 = new BinarySearchTree(a);
		
		System.out.println("The height for tree 2 is " + tree2.height());
		System.out.println("The max  for tree 1 is: " + tree1.max() + ", The Sucessor: " + tree1.successor(tree1.max()) + ", The Predecessor: " + tree1.predecessor(tree1.max()));
		System.out.println("The min for tree 1 is: " + tree1.min() + ", The Sucessor: " + tree1.successor(tree1.min()) + ", The Predecessor: " + tree1.predecessor(tree1.min()));
		System.out.println("Does Tree 1 has the same elemts as tree 2? " + tree1.hasSameElements(tree2));
		//System.out.println("Does Tree 1 has the same elements as tree 3? " + tree1.hasSameElements(tree3)); 
		tree2.preorder(tree2.root);
		
	}
}
