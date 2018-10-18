package comp3160.util;

public class BinarySearchTree<E extends Comparable<E>>
{
	protected Node<E> root;

	protected Node<E> insert(E value)
	{
		if(root == null)
		{
			root = new Node<E>();
			root.value = value;
			return root;
		}
		Node<E> p = root;
		Node<E> q = null;
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
		Node<E> x = new Node<E>();
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
		return x;
	}

	private Node<E> search(E value, Node<E> x)
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
	
	public void add(E value)
	{
		insert(value);
	}
	
	public boolean contains(E value)
	{
		return search(value, root) != null;
	}
}
