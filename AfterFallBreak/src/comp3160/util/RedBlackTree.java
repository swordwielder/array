package comp3160.util;

public class RedBlackTree<E extends Comparable<E>> extends BinarySearchTree<E>
{
	private void rotateLeft(Node<E> x)
	{
		Node<E> y = x.rightChild;
		x.rightChild = y.leftChild;
		if(y.leftChild != null)
		{
			y.leftChild.parent = x;
		}
		y.parent = x.parent;
		if(x.parent == null)
		{
			root = y;
		}
		else
		{
			Node<E> z = x.parent;
			if(x == z.leftChild)
			{
				z.leftChild = y;
			}
			else
			{
				z.rightChild = y;
			}
		}
		y.leftChild = x;
		x.parent = y;
	}

	private void rotateRight(Node<E> x)
	{
		Node<E> y = x.leftChild;
		x.leftChild = y.rightChild;
		if(y.rightChild != null)
		{
			y.rightChild.parent = x;
		}
		y.parent = x.parent;
		if(x.parent == null)
		{
			root = y;
		}
		else
		{
			Node<E> z = x.parent;
			if(x == z.rightChild)
			{
				z.rightChild = y;
			}
			else
			{
				z.leftChild = y;
			}
		}
		y.rightChild = x;
		x.parent = y;
	}

	public Node<E> insert(E value)
	{
		Node<E> x = super.insert(value);
		x.color = 'r';
		while(x != root && x.parent.color == 'r')
		{
			Node<E> grandparent = x.parent.parent;
			if(x.parent == grandparent.leftChild)
			{
				Node<E> uncle = grandparent.rightChild;
				if(uncle != null && uncle.color == 'r')
				{
					x.parent.color = 'b';
					uncle.color = 'b';
					grandparent.color = 'r';
					x = grandparent;
				}
				else
				{
					if(x == x.parent.rightChild)
					{
						x = x.parent;
						rotateLeft(x);
					}
					else
					{
						x.parent.color = 'b';
						grandparent.color = 'r';
						rotateRight(grandparent);
					}
				}
			}
			else
			{
				Node<E> uncle = grandparent.leftChild;
				if(uncle != null && uncle.color == 'r')
				{
					x.parent.color = 'b';
					uncle.color = 'b';
					grandparent.color = 'r';
					x = grandparent;
				}
				else
				{
					if(x == x.parent.leftChild)
					{
						x = x.parent;
						rotateRight(x);
					}
					else
					{
						x.parent.color = 'b';
						grandparent.color = 'r';
						rotateLeft(grandparent);
					}
				}
			}
		}
		root.color = 'b';
		return x;
	}
}
