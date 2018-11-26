package model;

import java.util.*;


public class Kruskal<E,T>{

	public boolean HayCiclo(Graph<E,T> g,Edge<E,T> aVerificar,Vertex<E,T> terminal,String N)
	 {
	 	ArrayList<Vertex<E,T>> aux= new ArrayList<>();
	 	
	 	for(int i =0; i<terminal.getNeighbors().length;i++){
	 		
	 		aux.add(terminal.getNeighbors()[i]);
	 	}
	 
	 	if(aux.size()==0)
	 		return false;
	 
	 	if(terminal.existeEnlace(aVerificar.getPartida())!=-1)
	 		return true;
	 
	 	for(int i=0;i<aux.size();i++)
	 	{
	 		Enlace nodo=aux.get(i);
	 
	 		if(nodo.getDestino().equals(N)==false)
	 			if( HayCiclo(g,aVerificar,g.getNodo(nodo.getDestino()),terminal.getIndex()))
	 								return true;
	 	}
	 
	 	return false;
	 }
	
}
