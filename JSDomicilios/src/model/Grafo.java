package model;

import Interfaces.*;
import java.util.*;

public class Grafo<N> implements IGraph<N>{

	private List<Edge> edges;
	private List<Node> nodes;
	private Node<N> node;
	
	public Grafo(){
		
		 edges = new ArrayList<Edge>();
		 nodes = new ArrayList<Node>();
		
		
	}
	@Override
	public void crearGrafo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarArista(N nodo1, N nodo2) {
		
		
		
	}

	@Override
	public void agregarNodo(N nuevoNodo) {
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public Grafo eliminarNodo( N nodo) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Grafo eliminarArista(N nodo1, N nodo2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contain() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean adyacentes( N nodo1, N nodo2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean predecesor( N nodo1, N nodo2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sucesor( N nodo1, N nodo2) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
