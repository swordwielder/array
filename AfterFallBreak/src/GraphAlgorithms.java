class GraphAlgorithms
{
	static class VertexStructure
	{
		int index;
		int distance = Integer.MAX_VALUE;
		VertexStructure predecessor = null;

		VertexStructure(int index)
		{
			this.index = index;
		}
	}

	static class ArrayPriorityQueue
	{
		int size;
		int [] a;
		
		ArrayPriorityQueue(VertexStructure[] vertex)
		{
			a = new int [vertex.length];
			for (int i=0; i< a.length; i++) {
				a[i] = (vertex[i].distance);
			}
			size = a.length;
		}

		boolean isEmpty()
		{
			return (size==0);
		}

		int remove()
		{
			int smallestDistance = Integer.MAX_VALUE;
			int indexReturn = 0;
			if (isEmpty())
				return -1;
			for (int i=0; i<a.length; i++){
				if (smallestDistance > a[i] && a[i]!=-1){
					smallestDistance = a[i];
					indexReturn =i;
				}
			}
			a[indexReturn]=-1;
			size--;
			return indexReturn;
		}

		void update(VertexStructure v)
		{
			a[v.index] = v.distance;
		}
	}

	private static void printPath(VertexStructure initial, VertexStructure current)
	{
		if(initial.equals(current))
		{
			System.out.print("v" + initial.index + " ");
		}
		else if(current.predecessor == null)
		{
			System.out.print("No path from v" + initial.index + " to v" + current.index);
		}
		else
		{
			printPath(initial, current.predecessor);
			System.out.print("v" + current.index + " ");
		}
	}

	public static void dijkstra(Graph G, int i0)
	{
		VertexStructure[] vertex = new VertexStructure[G.numberOfVertices];
		for(int i = 0; i < G.numberOfVertices; i++)
		{
			vertex[i] = new VertexStructure(i);
		}
		vertex[i0].distance = 0;
		ArrayPriorityQueue Q = new ArrayPriorityQueue(vertex);
		while(! Q.isEmpty())
		{
			int i = Q.remove();
			for(Graph.Vertex v : G.adjacencyList[i])
			{
				int j = v.index;
				int w_ij = v.weight;
				//vertex[i].distance < Integer.MAX_VALUE && 
				if(vertex[j].distance > vertex[i].distance + w_ij)
				{
					vertex[j].distance = vertex[i].distance + w_ij;
					vertex[j].predecessor = vertex[i];
					Q.update(vertex[j]);
				}
			}
		}
		for(int i = 0; i < G.numberOfVertices; i++)
		{
			printPath(vertex[i0], vertex[i]);
			System.out.println("");
		}
	}

	public static void main(String[] args)
	{
		System.out.println("First Graph:");
		Graph first = new Graph (5);
		first.addDirectedEdge(0,1,10);
		first.addDirectedEdge(0,3,30);
		first.addDirectedEdge(0,4,100);
		first.addDirectedEdge(1,2,50);
		first.addDirectedEdge(2,4,10);
		first.addDirectedEdge(3,2,20);
		first.addDirectedEdge(3,4,60);
		dijkstra(first, 0);
		
		System.out.println("\n" +"Second Graph: ");
		Graph second = new Graph(13);
		second.addEdge(0,1,3);
		second.addEdge(0,3,3);
		second.addEdge(0,5,3);
		second.addEdge(1,2,9);
		second.addEdge(1,3,1);
		second.addEdge(1,4,3);
		second.addEdge(2,4,3);
		second.addEdge(2,7,9);
		second.addEdge(3,5,5);
		second.addEdge(3,6,7);
		second.addEdge(4,6,4);
		second.addEdge(4,7,7);
		second.addEdge(5,8,1);
		second.addEdge(5,10,7);
		second.addEdge(6,8,2);
		second.addEdge(6,9,1);
		second.addEdge(7,9,1);
		second.addEdge(7,12,5);
		second.addEdge(8,10,6);
		second.addEdge(8,11,3);
		second.addEdge(9,11,4);
		second.addEdge(9,12,4);
		second.addEdge(10,11,1);
		second.addEdge(11,12,7);
		dijkstra(second, 0);
	}
}
