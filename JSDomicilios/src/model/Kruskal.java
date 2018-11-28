package model;

import java.util.PriorityQueue;

public class Kruskal<E,T> implements ArbolRecubridorMinimo<E,T> {
	private int acumulado =0;
	
    public int getAcumulado() {
		return acumulado;
	}

	@Override
    public Graph<E,T> obtenerARM(Graph<E,T> G,int inicio) throws IllegalArgumentException {
    	
//        if((G.isConnected())){
//        	throw new IllegalArgumentException("Error al generar arbol recubridor minimo: el grafo no es conexo.");
//        }
        
        int n = G.vertices_array().length;

        Graph<E,T> arbol = new Graph<E,T>(true);
        
        for(int i =0;i<n;i++){
        	arbol.addVertex(G.vertices_array()[i].getData());
        }
        

        int m = G.edges_array().length;
       
        if (m > 0) {
            PriorityQueue<Edge<E,T>> aristas = new PriorityQueue<>(m);
            for (int i = inicio; i < n +inicio - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (G.areAdjacent(G.vertices_array()[i],G.vertices_array()[j])) {
                    	int peso =G.edges_array()[j].getWeight();
                    	Edge<E,T> e = new Edge<>(G.vertices_array()[i], G.vertices_array()[j]);
                    	e.setWeight(peso);
                        aristas.add(e);
                    }
                }
            }
            Edge<E,T> aristaPesoMin = null;
            while (!aristas.isEmpty()) { 
                aristaPesoMin = aristas.poll();
                System.out.println(aristaPesoMin.getWeight());
                Vertex<E,T> vOrigen = aristaPesoMin.getV1();
                Vertex<E,T> vDestino = aristaPesoMin.getV2();
                if(!(arbol.camino(vOrigen, vDestino).isEmpty())){
                	arbol.addEdge(vOrigen,vDestino,null,aristaPesoMin.getWeight());                }
            }
        }
        for(int i =0;i<arbol.edges_array().length-1;i++){
        	acumulado += arbol.edges_array()[i].getWeight();
        	 System.out.println(acumulado);
        }
        return arbol;
    }

}
