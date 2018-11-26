package Implementacion;

public class Prim {

	// Se implementa la clase Grafo en JAVA. No existe como tal en el lenguaje.
	// Implementaci�n del Algoritmo de Prim utilizando lista de adyacencia.

	public class Algoritmos {
	    public Grafo<T> arbolExpMinPrim(Grafo<T> g, T inicio) {
	        // Obtengo la cantidad total de v�rtices
	        int verticesTotales = g.getVertices().size();
	        Vertice<T> vOrigen = g.buscarVertice(inicio);
	        if (vOrigen != null) {
	            Grafo<T> gNuevo = new Grafo<>(g.isDirigido());
	            g.getVertices().stream().forEach((v) -> {
	                gNuevo.agregarVertice(v.getContenido());
	            });
	            
	            // Para esta implementaci�n, los pesos son n�meros enteros.
	            PriorityQueue<Arco<T>> cola = new PriorityQueue<>((Arco a1, Arco a2) -> Integer.compare(a1.getPeso(), a2.getPeso()));

	            int cont = 0;
	            
	            while (cont < verticesTotales) {
	                for (Iterator<Arista> it = vOrigen.getArcos().iterator(); it.hasNext();) {
	                    Arista arc = it.next();
	                    if (!arc.getLlegada().isVisitado()) {
	                        cola.offer(arc);
	                    }
	                }
	                
	                Arista arco = cola.poll();
	                if (!arco.getDestino().isVisitado()) {
	                    arco.getDestino().setVisitado(true);
	                    gNuevo.agregarArco(arco.getPrevio().getContenido(), arco.getDestino().getContenido(), arco.getPeso());
	                    cont ++;
	                    vOrigen = arco.getDestino();
	                }
	            }
	            return gNuevo;
	            
	        }
	        return null;
	    }
	}
}
