import java.util.LinkedList;

class Graph
{
	class Vertex
	{
		int index;
		int weight = 1;
		
		Vertex(int index)
		{
			this.index = index;
		}
		
		Vertex(int index, int weight)
		{
			this.index = index;
			this.weight = weight;
		}
	}
	
	final int numberOfVertices;
	LinkedList<Vertex>[] adjacencyList;
	
	@SuppressWarnings("unchecked")
	Graph(int numberOfVertices)
	{
		this.numberOfVertices = numberOfVertices;
		adjacencyList = new LinkedList[numberOfVertices];
		for(int i = 0; i < numberOfVertices; i++)
		{
			adjacencyList[i] = new LinkedList<Vertex>();
		}
	}

	void addEdge(int i, int j)
	{
		adjacencyList[i].add(new Vertex(j));
		adjacencyList[j].add(new Vertex(i));
	}

	void addEdge(int i, int j, int w)
	{
		adjacencyList[i].add(new Vertex(j, w));
		adjacencyList[j].add(new Vertex(i, w));
	}
	
	void addDirectedEdge(int i, int j)
	{
		adjacencyList[i].add(new Vertex(j));
	}
	
	void addDirectedEdge(int i, int j, int w)
	{
		adjacencyList[i].add(new Vertex(j, w));
	}
}
