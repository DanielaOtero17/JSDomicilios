package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JOptionPane;


public class Graph<E,T>{
	
	private Auxiliar<Vertex<E,T>> vertexList;
	private Auxiliar<Edge<E,T>> edgeList;
	
	private boolean directed;
	private boolean isCyclic;
	private boolean isConnected;
	private int connectedComponents;
	
	private String[] vertices ;
	private String[] cordeX ;

	private String[] cordeY ;
	
	private int[][] coeficiente ;
	
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
		if(!contain(vertex)){
			Domicilie<Vertex<E,T>> node = vertexList.addDomicilie(vertex);
			vertex.setPosition(node);
		}
		else{
//			JOptionPane.showMessageDialog(null, "Mismo Vertice");
		}
		
		return vertex;
		
	}
	
	public Edge<E,T>[] addEdge(Vertex<E,T> v1, Vertex<E,T> v2, T label, int weight){
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
		return addEdge(v1, v2, null, 0);
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
						(directed && oppositeVertex.getStatus() == Vertex.VISITING && v.getColor() == oppositeVertex.getColor())
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
	
	public boolean isConnected(){
		if(directed)
			BFS_DiGraph_helper();
		else
			DFS();
		return isConnected;
	}
	
	private Vertex<E,T>[] BFS_DiGraph_helper() {
		Vertex<E,T>[] BFS = new Vertex[vertexList.getSize()];
		int index = 0;
		
		this.connectedComponents = 0;
		
		Iterator<Vertex<E,T>> iterV = vertices();
		while (iterV.hasNext())
			iterV.next().setStatus(Vertex.UNVISITED);

		Iterator<Edge<E,T>> iterE = edges();
		while (iterE.hasNext())
			iterE.next().setStatus(Edge.UNDISCOVERED);

		iterV = vertices();
		while (iterV.hasNext()) {
			Vertex<E,T> current = iterV.next();
			if (current.getStatus() == Vertex.UNVISITED) {
				
				this.connectedComponents++;
				this.isConnected = this.connectedComponents == 1;
				
				Queue<Vertex<E,T>> q = new LinkedList<Vertex<E,T>>();
				q.add(current);
				current.setStatus(Vertex.VISITING);
				while (!q.isEmpty()) {
					Vertex<E,T> polled = q.poll();
					BFS[index++] = polled;
					polled.setStatus(Vertex.VISITED);

					Iterator<Edge<E,T>> inOutEdges = polled.getOutEdges().concatenate(polled.getInEdges());
					while (inOutEdges.hasNext()) {
						Edge<E,T> edge = inOutEdges.next();
						Vertex<E,T> oppositeVertex = edge.getOpposite(polled);
						if (oppositeVertex.getStatus() == Vertex.UNVISITED) {
							edge.setStatus(Edge.DISCOVERED);
							oppositeVertex.setStatus(Vertex.VISITING);
							q.offer(oppositeVertex);
						} else {
							if (edge.getStatus() == Edge.UNDISCOVERED)
								edge.setStatus(Edge.CROSS);
						}
					}
				}
			}
		}
		return BFS;
	}

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
						oppositeVertex.getDijkstra_edge().setStatus(Edge.FORWARD); 
						oppositeVertex.setDijkstra_edge(edge);
					}
				}
			}
		}
	}
	

	public boolean areAdjacent(Vertex<E,T> v1, Vertex<E,T> v2){
		Vertex<E,T> v = directed || (v1.getOutEdges().size() < v2.getOutEdges().size()) ? v1 : v2;
		
		Iterator<Edge<E,T>> iterOutE = v.getOutEdges();
		while(iterOutE.hasNext())
			if( (v == v1 && iterOutE.next().getV2() == v2) || (v == v2 && iterOutE.next().getV2() == v1) ){
				return true;
			}	
		return false;
	}
	
	public Edge<E,T>[] dijkstra(Vertex<E,T> vFrom, Vertex<E,T> vTo){
		this.dijkstra(vFrom);
		Stack<Edge<E,T>> path = new Stack<>();
		Vertex<E,T> current = vTo;
		
		while(current.getDijkstra_edge() != null){
			path.push(current.getDijkstra_edge());
			current = current.getDijkstra_parent();}
		
		Edge<E,T>[] edges = new Edge[path.size()];
		int index =  0;
		while(!path.isEmpty())
			edges[index++] = path.pop();
		return edges;
	}
	
	public boolean contain(Vertex<E,T> v){
		
		
		for(int i =0;i<vertices_array().length;i++){
			if(v.getData().toString().equals(vertices_array()[i].getData().toString())){
				return true;
			}
		}
		return false;
	}
	

	public boolean contain(Edge<E, T> e){
		
		for(int i =0;i<edges_array().length;i++){
			if(e.position.getData().equals(edges_array()[i].getPosition().getData())){
				return true;
			}
		}
		return false;
	}
	
	public boolean isEmpty(){
		Iterator<Vertex<E,T>> iter = vertices();
		while(iter.hasNext()){
			if(iter.next()!=null){
				return false;
			}
		}
		return true;
	}
	
	
	public String toString(){
		String output = "         Vertices:\n";
		for(Vertex<E,T> v : vertices_array())
			output += String.format("%s ", v.toString())+ "\n";
//		String output = "";
		output += "            Edges:\n";
		
		for(Edge<E,T> e : edges_array()){
			output += String.format("%s\n", e.toString());
		}
		return output;
	}
	
	public ArrayList<Vertex<E,T>> camino(Vertex<E,T> idOrigen, Vertex<E,T> idDestino) throws IllegalArgumentException {
        if (!(contain(idOrigen))) {
            throw new IllegalArgumentException("Error en camino: algún vértice no existe.");
        }
        if (idOrigen == idDestino) {
            throw new IllegalArgumentException("Error en camino: deben ser identificadores diferentes.");
        }

        int[] predecesores = new int[this.vertices_array().length];

        ArrayList<Vertex<E,T>> Q = new ArrayList<>();
        boolean[] visitado = new boolean[vertices_array().length];
        for (int i = 0; i < visitado.length; i++) {
            visitado[i] = false;
        }
        Vertex<E,T> v =idDestino;
        Q.add(v);
        visitado[idDestino.getID()] = true;
        Vertex<E,T> t = null;
        while (!Q.isEmpty() && (t = Q.get(0)).getID() != idOrigen.getID()) {
            Q.remove(t);
            
            for (Vertex<E,T> u : vertices_array()) {
                if (!visitado[u.getID()]) {
                    predecesores[u.getID()] = t.getID();
                    visitado[u.getID()] = true;
                    Q.add(u);
                }
            }
        }
        ArrayList<Vertex<E,T>> camino = new ArrayList<>();
        int id = t.getID();
        if (id == idOrigen.getID()) {
            while (id != idDestino.getID()) {
                camino.add(idDestino);
                id = predecesores[id];
            }
            camino.add(idOrigen);
        }
        return camino;
    }
	
	
	public int[][] cargarMatriz(File archivo)throws Exception {
		FileReader reader = new FileReader(archivo);
		BufferedReader leitor = new BufferedReader(reader);

		String line;
		Integer fila = 0;
		Integer columna;
		
		Integer numeroDeVertices = Integer.parseInt(leitor.readLine().trim());
		
		vertices = (line = leitor.readLine()).split(" ");
		
		cordeX = (line = leitor.readLine()).split(" "); 
		cordeY = (line = leitor.readLine()).split(" ");
		
		int[][] matrizDeAdjacencia = new int[numeroDeVertices][numeroDeVertices];
		
		boolean  termino =false;
		while (!termino) {
			columna = 0;
			line = leitor.readLine();

			String[] splitted = line.split("\\s");

			for (int i = 0; i < splitted.length; i++) {

					
				Integer valor = Integer.parseInt(splitted[i].trim());
				
				matrizDeAdjacencia[fila][columna] = valor;

				
				columna++;
			}

			fila++;
			
			if(fila==51){
				termino=true;
			}
		}
		
		fila=0;
		
		Integer numeroDeCoeficientes = Integer.parseInt(leitor.readLine().trim());
		
		int[][] matrizDeCoeficientes = new int[numeroDeCoeficientes ][numeroDeCoeficientes ];
		
				
		while ((line = leitor.readLine())!=null) {
			columna = 0;
			String[] splitted = line.split("\\s");

			for (int i = 0; i < splitted.length; i++) {

					
				Integer valor = Integer.parseInt(splitted[i].trim());
				
				matrizDeCoeficientes[fila][columna] = valor;

				columna++;
			}

			fila++;
		}
	
		coeficiente =matrizDeCoeficientes;
		
		return matrizDeAdjacencia;
	}

	public String[] getVertices() {
		return vertices;
	}
	
	public int[][] getmCoeficiente() {
		return coeficiente;
	}
	
	public void escribir(int[][] matrizA,int[][] matrizI, String[] nombres, int [] cordX, int[] cordY ) {
		 FileWriter fichero = null;
	        PrintWriter pw = null;
	        Calendar c = Calendar.getInstance();
	        String dia =Integer.toString(c.get(Calendar.DATE));
	        String mes =Integer.toString(c.get(Calendar.MONTH));
	        String año =Integer.toString(c.get(Calendar.YEAR));
	        
	        int hora=c.get(Calendar.HOUR_OF_DAY);
	        int minuto=c.get(Calendar.MINUTE);
	        try
	        {
	            fichero = new FileWriter(new File("data/" +"Entregas " + año +"-"+ mes+"-"+ dia + "_" + hora +"."+ minuto + ".txt"));
	            pw = new PrintWriter(fichero);
	            
	            pw.print(matrizA.length+"");
	    		for (int x=0; x < nombres.length; x++) {
	    			pw.print(nombres[x]+ " ");
	    	    }
	    		pw.println(); 
	    		for (int x=0; x < cordX.length; x++) {
	    			pw.print(cordX[x] + " ");
	    	    }
	    		pw.println();
	    	    for (int y=0; y < cordY.length; y++) {
	    	    	pw.print(cordY[y] + " ");
	    	    }
	    	    pw.println();
	    	    for (int x=0; x < matrizA.length; x++) {
	    	    	pw.print("");
	    	        for (int y=0; y < matrizA[x].length; y++) {
	    	        	pw.print(matrizA[x][y]+ " ");
	    	          if (y!=matrizA[x].length) System.out.print(" ");
	    	        }
	    	        pw.println("");
	    	    }
	    	    
	    	    pw.println(matrizI.length+"");
	    	    
	    	    for (int x=0; x < matrizI.length; x++) {
	    	    	 pw.print("");
	    	        for (int y=0; y < matrizI[x].length; y++) {
	    	        	pw.print(matrizI[x][y] + " ");
	    	          if (y!=matrizI[x].length) System.out.print(" ");
	    	        }
	    	        pw.println("");
	    	    }
	    	    
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           try {
	        	   
	           if (null != fichero)
	              fichero.close();
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }
	}
		
	public String[] getCordeX() {
		return cordeX;
	}

	public String[] getCordeY() {
		return cordeY;
	}
}
