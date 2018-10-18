package comp3160.util;

class Node<E>
{
	protected Node<E> leftChild;
	protected Node<E> rightChild;
	protected Node<E> parent;
	protected E value;
	protected char color;
	
	@Override
	public String toString()
	{
		return value.toString();
	}
}
