package model;

import model.Edge;
import model.Vertex;

public class Vertex<E,T> implements Comparable<Vertex<E,T>> {

	private E data;
	private int status;
	private int color;
	private final int id;
	
	private Auxiliar<Edge<E,T>> inEdges,outEdges;
	private Domicilie<Vertex<E,T>> position;
	
	private Vertex<E,T> dijkstra_parent;
	private double dijkstra_value;
	private Edge<E,T> dijkstra_edge;
	
	public static final int UNVISITED = 0;
	public static final int VISITING = 1;
	public static final int VISITED = 2;
	
	
	protected static final int UNCOLORED = 0;
	
	public Vertex(E data, int id){
		this.data=data;
		this.id=id;
	}
	
	public Vertex(E data){
		this(data,0);
	}
	
	public Vertex<E,T>[] getNeighbors(){
		Vertex<E,T>[] neighbors = new Vertex[outEdges.getSize()];
		Iterator<Edge<E,T>> iter = outEdges.iterator();
		int index = 0;
		Edge<E,T> current = null;
		while(iter.hasNext()){
			current = iter.next();
			neighbors[index++] = current.getOpposite(this);
		}
		return neighbors;
	}
	
	public Iterator<Edge<E,T>> getOutEdges(){
		return outEdges.iterator();
	}
	
	public Iterator<Edge<E,T>> getInEdges(){
		return inEdges.iterator();
	}
	
	public Domicilie<Edge<E,T>> addOutEdge(Edge<E,T> e){
		return outEdges.addDomicilie(e);
	}
	
	public Domicilie<Edge<E,T>> addInEdge(Edge<E,T> e){
		return inEdges.addDomicilie(e);
	}
	
	public void removeInEdge(Domicilie <Edge<E,T>> node){
		inEdges.remove(node);
	}
	
	public void removeOutEdge(Domicilie <Edge<E,T>> node){
		outEdges.remove(node);
	}
	
	public E getData() {
		return data;
	}
	
	public Domicilie <Vertex<E,T>> getPosition() {
		return position;
	}
	
	public void setPosition(Domicilie <Vertex<E,T>> position) {
		this.position = position;
	}

	public void setData(E data) {
		this.data = data;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	
	public Vertex<E,T> getDijkstra_parent() {
		return dijkstra_parent;
	}
	
	public void setDijkstra_parent(Vertex<E,T> dijkstra_parent) {
		this.dijkstra_parent = dijkstra_parent;
	}
	
	public double getDijkstra_value() {
		return dijkstra_value;
	}
	public void setDijkstra_value(double dijkstra_value) {
		this.dijkstra_value = dijkstra_value;
	}
	public Edge<E,T> getDijkstra_edge() {
		return dijkstra_edge;
	}
	public void setDijkstra_edge(Edge<E,T> dijkstra_edge) {
		this.dijkstra_edge = dijkstra_edge;
	}

	public int getID(){
		return id;
	}
	
	
	@Override
	public int compareTo(Vertex<E, T> v) {
		if(v.getDijkstra_value() == getDijkstra_value())
			return 0;
		else if(v.getDijkstra_value() < getDijkstra_value())
			return 1;
		else
			return -1;
	}

	public String toString(){
		return String.format("<%s>", data.toString());
	}
	
}
