package model;

import java.util.LinkedList;
import java.util.Queue;

public class Graph<E,T>{
	
	private Auxiliar<Vertex<E,T>> vertexList;
	private Auxiliar<Edge<E,T>> edgeList;
	
	private boolean directed;
	private boolean isCyclic;
	private boolean isConnected;
	private int connectedComponents;
	
	private int unique_id = 0;
	
	public Graph(boolean directed) {
		vertexList = new  Auxiliar<Vertex<E,T>>();
		edgeList = new  Auxiliar<Edge<E,T>>();
		this.directed = directed;
	}
	
	public Vertex<E,T> addVertex(E data){
		return addVertex(data,0);
	}
	
	private Vertex<E,T> addVertex(E data, int id){
		Vertex<E,T> vertex = new Vertex<E,T>(data, id);
		Domicilie<Vertex<E,T>> node = vertexList.addDomicilie(vertex);
		vertex.setPosition(node);
		return vertex;
	}
	
	public Edge<E,T>[] addEdge(Vertex<E,T> v1, Vertex<E,T> v2, T label, double weight){
		Edge<E,T> edges[] = new Edge[directed ? 1 : 2];
		
		edges[0] = new Edge<E,T>(v1, v2);
		edges[0].setLabel(label);
		edges[0].setWeight(weight);
		edges[0].setPosition(edgeList.addDomicilie(edges[0]));
		
		if(!directed){
			edges[1] = new Edge<E,T>(v2, v1);
			edges[1].setLabel(label);
			edges[1].setWeight(weight);
			edges[1].setPosition(edgeList.addDomicilie(edges[1]));
		}
		
		return edges;
	}
	
	public Edge<E,T>[] addEdge(Vertex<E,T> v1, Vertex<E,T> v2){
		return addEdge(v1, v2, null, 0.0);
	}
	
	public Vertex<E,T>[] BFS(Vertex<E,T> vertex){
		
		Iterator<Vertex<E,T>> iterV = vertices();
		while(iterV.hasNext())
			iterV.next().setStatus(Vertex.UNVISITED);
		
		Iterator<Edge<E,T>> iterE = edges();
		while(iterE.hasNext())
			iterE.next().setStatus(Edge.UNDISCOVERED);
		
		Auxiliar<Vertex<E,T>> BFS_list = new Auxiliar<>();
		
		Queue<Vertex<E,T>> q = new LinkedList<Vertex<E,T>>();
		q.add(vertex);
		vertex.setStatus(Vertex.VISITING);
		while(!q.isEmpty()){
			Vertex<E,T> polled = q.poll();
			BFS_list.addDomicilie(polled);
			polled.setStatus(Vertex.VISITED);
			
			Iterator<Edge<E,T>> incidentEdges = polled.getOutEdges();
			while(incidentEdges.hasNext()){
				Edge<E,T> edge = incidentEdges.next();
				Vertex<E,T> oppositeVertex = edge.getV2();

				if(oppositeVertex.getStatus() == Vertex.UNVISITED){
					
					edge.setStatus(Edge.DISCOVERED);
					oppositeVertex.setStatus(Vertex.VISITING);
					q.offer(oppositeVertex);
				
				}
				else{
					if(edge.getStatus() == Edge.UNDISCOVERED)
						edge.setStatus(Edge.CROSS);
				}
			}
		}
		Iterator<Vertex<E,T>> BFS_iter = BFS_list.iterator();
		Vertex<E,T> BFS[] = new Vertex[BFS_iter.size()];
		int index = 0;
		while(BFS_iter.hasNext())
			BFS[index++] = BFS_iter.next();
		return BFS;
	}
	
	public Vertex<E,T>[] BFS(){
		Vertex<E,T>[] BFS = new Vertex[vertexList.getSize()];
		int index = 0;
		
		Iterator<Vertex<E,T>> iterV = vertices();
		while(iterV.hasNext())
			iterV.next().setStatus(Vertex.UNVISITED);
		
		Iterator<Edge<E,T>> iterE = edges();
		while(iterE.hasNext())
			iterE.next().setStatus(Edge.UNDISCOVERED);
		
		iterV = vertices();
		while(iterV.hasNext()){
			Vertex<E,T> current = iterV.next();
			if(current.getStatus() == Vertex.UNVISITED){
				Queue<Vertex<E,T>> q = new LinkedList<Vertex<E,T>>();
				q.add(current);
				current.setStatus(Vertex.VISITING);
				while(!q.isEmpty()){
					Vertex<E,T> polled = q.poll();
					BFS[index++] = polled;
					polled.setStatus(Vertex.VISITED);
					
					Iterator<Edge<E,T>> incidentEdges = polled.getOutEdges();
					while(incidentEdges.hasNext()){
						Edge<E,T> edge = incidentEdges.next();
						Vertex<E,T> oppositeVertex = edge.getV2();
						
						if(oppositeVertex.getStatus() == Vertex.UNVISITED){
							edge.setStatus(Edge.DISCOVERED);
							oppositeVertex.setStatus(Vertex.VISITING);
							q.offer(oppositeVertex);
						}

						else{
							if(edge.getStatus() == Edge.UNDISCOVERED)
								edge.setStatus(Edge.CROSS);
						}
					}	
						
				}
			}
		}
		return BFS;
	}
	
	public Vertex<E,T>[] DFS(){
		Vertex<E,T>[] DFS = new Vertex[vertexList.getSize()];
		int index[] = {0};
		
		this.connectedComponents = 0;
		this.isCyclic = false;
		
		
		Iterator<Vertex<E,T>> iterV = vertices();
		while(iterV.hasNext()){
			Vertex<E,T> currentV = iterV.next();
			currentV.setStatus(Vertex.UNVISITED);
			currentV.setColor(Vertex.UNCOLORED);
		}
		
	
		Iterator<Edge<E,T>> iterE = edges();
		while(iterE.hasNext())
			iterE.next().setStatus(Edge.UNDISCOVERED);
		
		iterV = vertices();
		while(iterV.hasNext()){
			Vertex<E,T> current = iterV.next();
			if(current.getStatus() == Vertex.UNVISITED){
				
				this.connectedComponents++;
				this.isConnected = this.connectedComponents == 1;
				DFS(current, DFS, index);
			}
		}
		return DFS;
	}
	
	private void DFS(Vertex<E,T> v, Vertex<E,T>[] DFS, int[] index){
		
		v.setColor(connectedComponents);
		v.setStatus(Vertex.VISITING);
		DFS[index[0]++] = v;
		
		Iterator<Edge<E,T>> incidentEdges = v.getOutEdges();
		while(incidentEdges.hasNext()){
			Edge<E,T> edge = incidentEdges.next();
			Vertex<E,T> oppositeVertex = edge.getV2();
			
			if(oppositeVertex.getStatus() == Vertex.UNVISITED){
				edge.setStatus(Edge.DISCOVERED);
				oppositeVertex.setStatus(Vertex.VISITING);
				DFS(oppositeVertex, DFS,index);
			}else{
				
				if(
						(!directed && oppositeVertex.getStatus() == Vertex.VISITED) ||
						(directed && oppositeVertex.getStatus() == Vertex.VISITING && v.getColor() == oppositeVertex.getColor()) // Third condition is for DiGraph (Check earlier this method...)
				){
					isCyclic = true;
				}
				
				if(edge.getStatus() == Edge.UNDISCOVERED)
					edge.setStatus(Edge.CROSS);
			}
		}
		
		v.setStatus(Vertex.VISITED);
	}
	
	

	public Vertex<E,T>[] DFS(Vertex<E,T> vertex){
		
		Iterator<Vertex<E,T>> iterV = vertices();
		while(iterV.hasNext()){
			Vertex<E,T> currentV = iterV.next();
			currentV.setStatus(Vertex.UNVISITED);
			currentV.setColor(Vertex.UNCOLORED);
		}
		
		Iterator<Edge<E,T>> iterE = edges();
		while(iterE.hasNext())
			iterE.next().setStatus(Edge.UNDISCOVERED);
		
		Auxiliar<Vertex<E,T>> DFS_list = new Auxiliar<>();
		
		
		DFS(vertex, DFS_list);
		
		Iterator<Vertex<E,T>> iter_DFS = DFS_list.iterator();
		Vertex<E,T> DFS[] = new Vertex[iter_DFS.size()];
		int index = 0;
		while(iter_DFS.hasNext())
			DFS[index++] = iter_DFS.next();
		
		return DFS;
	}
	
	private void DFS(Vertex<E,T> vertex, Auxiliar<Vertex<E,T>> DFS_list){
		vertex.setStatus(Vertex.VISITING);
		DFS_list.addDomicilie(vertex);
		
		Iterator<Edge<E,T>> incidentEdges = vertex.getOutEdges();
		while(incidentEdges.hasNext()){
			Edge<E,T> edge = incidentEdges.next();
			Vertex<E,T> oppositeVertex = edge.getV2();
			
			// Recur on neighbor if not visited
			if(oppositeVertex.getStatus() == Vertex.UNVISITED){
				edge.setStatus(Edge.DISCOVERED);
				oppositeVertex.setStatus(Vertex.VISITING);
				DFS(oppositeVertex, DFS_list);
			}else{
				
				/// Mark edge as cross if the undiscovered
				if(edge.getStatus() == Edge.UNDISCOVERED)
					edge.setStatus(Edge.CROSS);
			}
		}
		
		// Mark vertex as visited if more neighbors needs to be visited
		vertex.setStatus(Vertex.VISITED);
	}
	
	public Iterator<Vertex<E,T>> vertices() {
		return vertexList.iterator();
	}
	
	public Iterator<Edge<E,T>> edges() {
		return edgeList.iterator();
	}
	
	public Vertex<E,T>[] vertices_array(){
		Vertex<E,T>[] tmp = new Vertex[vertexList.getSize()];
		Iterator<Vertex<E,T>> iter = vertices();
		int index = 0;
		while(iter.hasNext())
			tmp[index++] = iter.next();
		return tmp;
	}
	
	public Edge<E,T>[] edges_array(){
		Edge<E,T>[] tmp = new Edge[edgeList.getSize()];
		Iterator<Edge<E,T>> iter = edges();
		int index = 0;
		while(iter.hasNext())
			tmp[index++] = iter.next();
		return tmp;
	}
	
	public String toString(){
		String output = "Vertices:\n";
		for(Vertex<E,T> v : vertices_array())
			output += String.format("%s ", v.toString());
		
		output += "\n\nEdges:\n";
		
		for(Edge<E,T> e : edges_array()){
			output += String.format("%s\n", e.toString());
		}
		return output;
	}
	
}
