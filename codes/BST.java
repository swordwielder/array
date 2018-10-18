import java.util.LinkedList;
import java.util.Queue;

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

	int size(Node x) {
		if(x == null) {
			return 0;
		}
		else {
			return size(x.leftChild) + size(x.rightChild) + 1;
		}
	}

	Node min(Node x) {
		if(x == null) {
			return null;
		}
		Node p = x;
		while(p.leftChild != null) {
			p = p.leftChild;
		}
		return p;
	}

	Node max(Node x) {
		if(x == null) {
			return null;
		}
		Node p = x;
		while(p.rightChild != null) {
			p = p.rightChild;
		}
		return p;
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

	public static void main(String[] args) {
		//BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
	}
}
