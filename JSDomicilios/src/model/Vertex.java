package model;

public class Vertex<E,T> {

	private E data;
	private int status;
	private int color;
	private final int id;
	
	private Auxiliar<Edge<E,T>> inEdges,outEdges;
	private Domicilie<Vertex<E,T>> position;
	
	public static final int UNVISITED = 0;
	public static final int VISITING = 1;
	public static final int VISITED = 2;
	
	
	protected static final int UNCOLORED = 0;
	
	public Vertex(E data, int id){
		this.data=data;
		this.status = UNVISITED;
		this.color = 0;
		this.id=id;
		inEdges = new Auxiliar<Edge<E,T>>();
		outEdges = new Auxiliar<Edge<E,T>>();
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
	

	public int getID(){
		return id;
	}
	
	public String toString(){
		return String.format("<%s>", data.toString());
	}
	
}
