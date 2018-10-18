
import java.util.LinkedList;

public class ADSSet
{
	private ADSLinkedList[] hashTable;
	private int m;
	
	public ADSSet(int hashTableLength)
	{
		hashTable = new ADSLinkedList[hashTableLength];
		m = hashTableLength;
		for(int i = 0; i < hashTable.length; i++)
		{
			hashTable[i] = new ADSLinkedList();
		}
	}
	
	private int h(int k)
	{
		return k % m; 
	}
	
	public void add(int element)
	{
		int index = h(element);
		if(! hashTable[index].list.contains(element))
		{
			hashTable[index].list.add(element);
		}
	}
	
	public void remove(int element)
	{
		int index = h(element);
		hashTable[index].list.remove(element);
	}
	
	public boolean contains(int element)
	{
		int index = h(element);
		return hashTable[index].list.contains(element);
	}
	
	public double printDistribution()
	{
		int sum = 0;
		for(int i = 0; i < hashTable.length; i++)
		{
			sum = sum + hashTable[i].list.size();
			System.out.println(i + "\t" + hashTable[i].list.size());
		}
		return (double)sum / hashTable.length;
	}
}

class ADSLinkedList
{
	LinkedList<Integer> list = new LinkedList<Integer>();
}
