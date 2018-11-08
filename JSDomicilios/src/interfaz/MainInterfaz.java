package interfaz;

import model.Edge;
import model.Vertex;
import model.Graph;

public class MainInterfaz {

	public static void main(String[] args) {
		
		Graph<String,String> graph = new Graph<String,String>(false);
		
		Vertex<String,String> v1 = graph.addVertex("A");	
		Vertex<String,String> v2 = graph.addVertex("B");
		Vertex<String,String> v3 = graph.addVertex("C");
		Vertex<String,String> v4 = graph.addVertex("D");
		Vertex<String,String> v5 = graph.addVertex("E");
		Vertex<String,String> v6 = graph.addVertex("F");
		
		graph.addEdge(v1, v4);
		graph.addEdge(v4, v6);
		graph.addEdge(v2,v5);
		graph.addEdge(v5, v4);
		graph.addEdge(v1, v3);
		
		System.out.println("Print graph state\n");
		System.out.println(graph);
		
		System.out.println("BFS:");
		for(Vertex<String,String> v : graph.BFS())
			System.out.print(v + " ");
		
		System.out.println("\n\nDFS");
		for(Vertex<String,String> v : graph.DFS())
			System.out.print(v + " ");

	}

}
