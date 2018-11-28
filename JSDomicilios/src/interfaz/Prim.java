package interfaz;

import java.util.Iterator;
import java.util.PriorityQueue;

import model.ArbolRecubridorMinimo;
import model.Edge;
import model.Graph;
import model.Vertex;

public class Prim<E,T> implements ArbolRecubridorMinimo<E, T>{

private int acumulado =0;
	
    public int getAcumulado() {
		return acumulado;
	}

	@Override
	public Graph<E, T> obtenerARM(Graph<E, T> G, int inicio) {
//		if((G.isConnected())){
//        	throw new IllegalArgumentException("Error al generar arbol recubridor minimo: el grafo no es conexo.");
//        }
        
        int n = G.vertices_array().length;

        Graph<E,T> arbol = new Graph<E,T>(false);
        
        for(int i =0;i<n;i++){
        	arbol.addVertex(G.vertices_array()[i].getData());
        }
        

        int m = G.edges_array().length;
       
        if (m > 0) {
            PriorityQueue<Edge<E,T>> aristas = new PriorityQueue<>();
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    	Edge<E,T> e = new Edge<>(G.vertices_array()[i], G.vertices_array()[j]);
                        aristas.add(e);
                }
            }
            Edge<E,T>aristaPesoMin = null;
            while (!aristas.isEmpty()) { 
                aristaPesoMin = aristas.poll();
                Vertex<E,T> vOrigen = aristaPesoMin.getV1();
                Vertex<E,T> vDestino = aristaPesoMin.getV2();
                if(!(arbol.camino(vOrigen, vDestino).isEmpty())){
                    arbol.addEdge(vOrigen,vDestino);
                }
            }
        }
        for(int i =0;i<arbol.edges_array().length/2;i++){
        	acumulado += G.edges_array()[i].getWeight();
        	 System.out.println(acumulado);
        }
        return arbol;
		
	}
	
	
}
