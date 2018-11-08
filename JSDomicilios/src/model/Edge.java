package model;

import model.Vertex;

public class Edge<E,T> {
	
	private Vertex<E,T> v1, v2;
	private T label;
	private double weight;
	private int status;
	
	public static final int UNDISCOVERED = 0;
	public static final int DISCOVERED = 1;
	public static final int BACK = 2;
	public static final int FORWARD = 3;
	public static final int CROSS = 4;
	
	public Domicilie<Edge<E,T>> position;
	public Domicilie<Edge<E,T>> incidentV1, incidentV2;
	
	
	public Edge(Vertex<E,T> v1, Vertex<E,T> v2){
		this.v1=v1;
		this.v2=v2;
		this.incidentV1=this.v1.addOutEdge(this);
		this.incidentV2=this.v2.addInEdge(this);
	}
	
	public Vertex<E,T> getOpposite(Vertex<E,T> v){
		
		if(v != v1 && v != v2)
			return null;
		return v1 == v ? v2 : v1;
	}

	public Vertex<E, T> getV1() {
		return v1;
	}

	public void setV1(Vertex<E, T> v1) {
		this.v1 = v1;
	}

	public Vertex<E, T> getV2() {
		return v2;
	}

	public void setV2(Vertex<E, T> v2) {
		this.v2 = v2;
	}

	public T getLabel() {
		return label;
	}

	public void setLabel(T label) {
		this.label = label;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Domicilie<Edge<E, T>> getPosition() {
		return position;
	}

	public void setPosition(Domicilie<Edge<E, T>> position) {
		this.position = position;
	}
	
	
	public String getStatusString() {
		String statusString[] = {"Undiscovered","Discovered", "Back", "Forward","Cross"};
		return statusString[status];
	}
	
	public Domicilie<Edge<E,T>> getIncidentV1() {
		return incidentV1;
	}
	public Domicilie<Edge<E,T>> getIncidentV2() {
		return incidentV2;
	}
	
	public String toString(){
		return label == null ? String.format("(%s, %s)", v1.toString(),v2.toString()) : String.format("(%s)", label);
	}
}
