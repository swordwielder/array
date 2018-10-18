import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class BinarySearchTree<E extends Comparable<E>> {
	class Node {
		Node leftChild;
		Node rightChild;
		Node parent;
		E value;

		@Override
		public String toString() {
			return value.toString();
		}
	}

	Node root;

	void insert(E value) {
		if(root == null) {
			root = new Node();
			root.value = value;
			return;
		}
		Node p = root;
		Node q = null;
		while(p != null) {
			q = p;
			if(value.compareTo(p.value) < 0) {
				p = p.leftChild;
			}
			else {
				p = p.rightChild;
			}
		}
		Node x = new Node();
		x.parent = q;
		x.value = value;
		if(value.compareTo(q.value) < 0) {
			q.leftChild = x;
		}
		else {
			q.rightChild = x;
		}
	}

	Node search(E value, Node x) {
		if(x == null) {
			return null;
		}
		else if(value.equals(x.value)) {
			return x;
		}
		else if(value.compareTo(x.value) < 0) {
			return search(value, x.leftChild);
		}
		else {
			return search(value, x.rightChild);
		}
	}

	Node search(E value) {
		return search(value, root);
	}

	/*
	 * This method should only be called by delete.
	 * It removes a node x.
	 * In the event where x has a child, the method joins x's parent and child.
	 * A node with two children cannot be disjoint.
	 */
	void disjoin(Node x) {
		Node y = null;
		if(x.leftChild != null) {
			y = x.leftChild;
		}
		else {
			y = x.rightChild;
		}
		if(y != null) {
			y.parent = x.parent;
		}
		if(x.parent == null) {
			root = y;
		}
		else if(x == x.parent.leftChild) {
			x.parent.leftChild = y;
		}
		else {
			x.parent.rightChild = y;
		}
	}

	void delete(E value) {
		Node x = search(value);
		if(x == null) {
			return;
		}
		if(x.leftChild == null || x.rightChild == null) {
			disjoin(x);
		}
		else { // x has two children. Its successor must have at most one child.
			Node y = successor(x);
			x.value = y.value;
			disjoin(y);
		}
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

	Node predecessor(Node x) {
		Node p = null;
		Node q = null;
		if(x.leftChild != null) {
			return max(x.leftChild);
		}
		else {
			p = x.parent;
			q = x;
			while(p != null && q == p.leftChild) {
				q = p;
				p = p.parent;
			}
		}
		return p;
	}

	Node successor(Node x) {
		Node p = null;
		Node q = null;
		if(x.rightChild != null) {
			return min(x.rightChild);
		}
		else {
			p = x.parent;
			q = x;
			while(p != null && q == p.rightChild) {
				q = p;
				p = p.parent;
			}
		}
		return p;
	}

	int height(Node x) {
		if(x == null) {
			return - 1;
		}
		int leftHeight = height(x.leftChild);
		int rightHeight = height(x.rightChild);
		int maxHeight = Math.max(leftHeight, rightHeight);
		return maxHeight + 1;
	}

	boolean balanced(Node x) {
		if(x == null) {
			return true;
		}
		int leftHeight = height(x.leftChild);
		int rightHeight = height(x.rightChild);
		boolean leftBalanced = balanced(x.leftChild);
		boolean rightBalanced = balanced(x.rightChild);
		return leftBalanced && rightBalanced 	&& Math.abs(leftHeight - rightHeight) <= 1;
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
	
	void breadthFirstSearch() {
		Queue<Node> Q = new LinkedList<Node>();
		if(root != null) {
			Q.add(root);
		}
		while(! Q.isEmpty()) {
			Node x = Q.remove();
			System.out.print(x.value + " ");
			if(x.leftChild != null) {
				Q.add(x.leftChild);
			}
			if(x.rightChild != null) {
				Q.add(x.rightChild);
			}
		}
	}
	
	
	int height()
	{
		return height(root);
	}
	
	int maxH ()
	{
		Node x = root;
		Stack<Node> Q = new Stack<Node>();
		Q.push(x);
		int Lh =0, Rh=0;
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

	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.insert(10);
		tree.insert(15);
		tree.insert(7);
		tree.insert(1);
		tree.insert(4);
		tree.insert(3);
		tree.insert(9);
		tree.insert(8);
		tree.insert(20);
		tree.insert(16);
		tree.insert(17);
		tree.insert(8);
		tree.insert(14);
		tree.insert(14);
		tree.postorder(tree.root);
		System.out.println("The height for tree is " + tree.height());
		System.out.println("Balanced? " + tree.balanced(tree.root));
		System.out.println("The height is " + tree.maxH());
		String a = "something";
		char[] b = a.toCharArray();
		for (int i=0; i<b.length; i++)
		{
			System.out.println(b[i]);
		}
		
	}
}
