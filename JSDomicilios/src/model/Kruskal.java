package model;

import java.util.PriorityQueue;

public class Kruskal<E,T> extends ArbolRecubridorMinimo<E,T> {
	public int acumulado =0;
	
    @Override
    public Graph<E,T> obtenerARM(Graph<E,T> G) throws IllegalArgumentException {
        if(!G.isConnected()) throw new IllegalArgumentException("Error al generar arbol recubridor minimo: el grafo no es conexo.");
        
        int n = G.vertices_array().length;

        Graph<E,T> arbol = new Graph<E,T>(false);
        
        for(int i =0;i<n;i++){
        	arbol.addVertex(G.vertices_array()[i].getData());
        }

        int m = G.edges_array().length;
        if (m > 0) {
            PriorityQueue<Edge<E,T>> aristas = new PriorityQueue<>(m);
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (G.areAdjacent(G.vertices_array()[i],G.vertices_array()[j])) {
                    	Edge<E,T> e = new Edge<>(G.vertices_array()[i], G.vertices_array()[j]);
                        aristas.add(e);
                    }
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
        for(int i =0;i<arbol.edges_array().length;i++){
        	acumulado += arbol.edges_array()[i].getWeight();
        }
        return arbol;
    }

}
