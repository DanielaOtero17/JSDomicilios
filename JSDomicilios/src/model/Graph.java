package model;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


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
		return addVertex(data,unique_id++);
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
	

	public void removeVertex(Vertex<E,T> vertex){
		
		Iterator<Edge<E,T>> iterOutEdges = vertex.getOutEdges();
		while(iterOutEdges.hasNext()){
			Edge<E,T> currentE = iterOutEdges.next();
			Vertex<E,T> vTo = currentE.getV2();
			
			vTo.removeInEdge(currentE.getIncidentV2());
			
			edgeList.remove(currentE.getPosition());
		}
		
		Iterator<Edge<E,T>> iterInEdges = vertex.getInEdges();
		while(iterInEdges.hasNext()){
			Edge<E,T> currentE = iterInEdges.next();
			Vertex<E,T> vFrom = currentE.getV1();
			
			vFrom.removeOutEdge(currentE.getIncidentV1());
			edgeList.remove(currentE.getPosition());
		}
		vertexList.remove(vertex.getPosition());
	}
	
	public void removeEdge(Edge<E,T> edge){
		edge.getV1().removeOutEdge(edge.getIncidentV1());
		edge.getV2().removeInEdge(edge.getIncidentV2());
		edgeList.remove(edge.getPosition());
	}
	
	
	public boolean isDirected() {
		return directed;
	}
	
	public boolean isCyclic(){
		DFS();
		return isCyclic;
	}
	
//	public boolean isConnected(){
//		if(directed)
//			BFS_DiGraph_helper();
//		else
//			DFS();
//		return isConnected;
//	}
//	
//	public int connectedComponents(){
//		if(directed)
//			BFS_DiGraph_helper();
//		else
//			DFS();
//	}
	

	public void dijkstra(Vertex<E,T> v){
		
		Iterator<Vertex<E,T>> iterV = vertices();
		while(iterV.hasNext()){
			Vertex<E,T> currentV = iterV.next();
			currentV.setStatus(Vertex.UNVISITED);
			currentV.setDijkstra_value(Double.MAX_VALUE);
			currentV.setDijkstra_parent(null);
		}
		
		Iterator<Edge<E,T>> iterE = edges();
		while(iterE.hasNext())
			iterE.next().setStatus(Edge.UNDISCOVERED);
		
		v.setDijkstra_value(0);
		
		PriorityQueue<Vertex<E,T>> pq = new PriorityQueue<>();
		
		pq.offer(v);
		v.setStatus(Vertex.VISITING);
		v.setDijkstra_parent(v);
		while(!pq.isEmpty()){
			
			Vertex<E,T> polled = pq.poll();
			v.setStatus(Vertex.VISITED);
			Iterator<Edge<E,T>> incidentEdges = polled.getOutEdges();
			
			while(incidentEdges.hasNext()){
				Edge<E,T> edge = incidentEdges.next();
				Vertex<E,T> oppositeVertex = edge.getV2();
				double pathCost = edge.getWeight() + polled.getDijkstra_value();
				
				if(oppositeVertex.getStatus() == Vertex.UNVISITED){
					oppositeVertex.setDijkstra_value(pathCost);
					oppositeVertex.setDijkstra_edge(edge);
					edge.setStatus(Edge.DISCOVERED);
					oppositeVertex.setStatus(Vertex.VISITING);
					oppositeVertex.setDijkstra_parent(polled);
					pq.offer(oppositeVertex);
				
				}
				else if(oppositeVertex.getStatus() == Vertex.VISITING){
					
					if(oppositeVertex.getDijkstra_value() > pathCost){
						oppositeVertex.setDijkstra_value(pathCost);
						edge.setStatus(Edge.DISCOVERED);
						oppositeVertex.setDijkstra_parent(polled);
						oppositeVertex.getDijkstra_edge().setStatus(Edge.FORWARD); // Mark previous edge as FORWARD
						oppositeVertex.setDijkstra_edge(edge); // Update edge that makes it shortest path
					}
				}
			}
		}
	}
	
	public Edge<E,T>[] dijkstra(Vertex<E,T> vFrom, Vertex<E,T> vTo){
		this.dijkstra(vFrom);
		Stack<Edge<E,T>> path = new Stack<>();
		Vertex<E,T> current = vTo;
		
		while(current.getDijkstra_edge() != null){
			path.push(current.getDijkstra_edge());
			current = current.getDijkstra_parent();
		}
		
		Edge<E,T>[] edges = new Edge[path.size()];
		int index =  0;
		while(!path.isEmpty())
			edges[index++] = path.pop();
		return edges;
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
