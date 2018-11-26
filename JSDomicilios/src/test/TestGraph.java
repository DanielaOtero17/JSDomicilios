package test;
import static org.junit.Assert.*;

import org.junit.Test;

import model.Deliver;
import model.Domicilie;
import model.Edge;
import model.Graph;
import model.Vertex;

public class TestGraph{
	
	private Graph<Domicilie<Deliver>,String> graph;
	private Vertex<Domicilie<Deliver>,String> v1;
	private Vertex<Domicilie<Deliver>,String> v6;
	
	private Edge<Domicilie<Deliver>,String> e1[];
	private Edge<Domicilie<Deliver>,String> e5[];

	public void setup(){
		
		graph = new Graph<Domicilie<Deliver>,String>(false);
		
		v1 = graph.addVertex(new Domicilie<Deliver>(new Deliver("A")));
		Vertex<Domicilie<Deliver>,String> v2 = graph.addVertex(new Domicilie<Deliver>(new Deliver("B")));
		
		Vertex<Domicilie<Deliver>,String> v3 = graph.addVertex(new Domicilie<Deliver>(new Deliver("C")));
		Vertex<Domicilie<Deliver>,String> v4 = graph.addVertex(new Domicilie<Deliver>(new Deliver("D")));
		
		Vertex<Domicilie<Deliver>,String> v5 = graph.addVertex(new Domicilie<Deliver>(new Deliver("E")));
		v6 = graph.addVertex(new Domicilie<Deliver>(new Deliver("F")));
		
		e1 = graph.addEdge(v1, v2,null,12);
		
		Edge<Domicilie<Deliver>,String> e2[] = graph.addEdge(v2, v3,null,27);
		
		Edge<Domicilie<Deliver>,String> e3[] = graph.addEdge(v3, v4,null,87);
		
		Edge<Domicilie<Deliver>,String> e4[] = graph.addEdge(v4, v5,null,32);
		
		e5 = graph.addEdge(v5, v6,null,38);
		
		
		
//		System.out.println();
//		System.out.println("Dijkstra");
//		Vertex<Domicilie<Deliver>,String> v[] = graph.vertices_array();
//		for(Edge<Domicilie<Deliver>,String> e : graph.dijkstra(v[0], v[2])){
//			System.out.println(e);
//		}
//		System.out.println("-------------------------------");
//		System.out.println(String.format("Total distance: %.2f meters", v[2].getDijkstra_value()));
//		System.out.println("-------------------------------");
//		
//		System.out.println("Is cyclic: "+graph.isCyclic());
		
	}
	@Test
	public void sceneOne(){
		setup();
		
		assertEquals(6,graph.vertices_array().length);
		
		graph.removeVertex(v1);
		
		assertEquals(5,graph.vertices_array().length);
	}
	
	@Test
	public void sceneTwo(){
		setup();
		
		assertEquals(10,graph.edges_array().length);
		
		graph.removeEdge(e1[0]);
		graph.removeEdge(e1[1]);
		
		assertEquals(8,graph.edges_array().length);
		
	}
	@Test
	public void sceneThree(){
		setup();
		Vertex<Domicilie<Deliver>,String> v7 = new Vertex<>(new Domicilie<Deliver>(new Deliver("H")));
		
		assertEquals(true, graph.contain(v1));
		assertEquals(false, graph.contain(v7));

	}
	@Test
	public void sceneFour(){
		setup();
		Graph<Domicilie<Deliver>,String> graph2 = new Graph<Domicilie<Deliver>,String>(false);
		
		assertEquals(false, graph.isEmpty());
		
		assertEquals(true, graph2.isEmpty());

	}
	@Test
	public void sceneFive(){
		setup();
		
		assertEquals(10,graph.edges_array().length);
		
		graph.addEdge(v1, v6);
		
		assertEquals(12,graph.edges_array().length);
	}
	@Test
	public void sceneSix(){
		setup();
		Vertex<Domicilie<Deliver>,String> v7;
		
		assertEquals(6,graph.vertices_array().length);
	
		v7 = graph.addVertex(new Domicilie<Deliver>(new Deliver("G")));
		
		assertEquals(7,graph.vertices_array().length);
	}
	@Test
	public void sceneSeven(){
		
		Graph<Domicilie<Deliver>,String> graph1 = new Graph<Domicilie<Deliver>,String>(false);
		
		Vertex<Domicilie<Deliver>,String> v7 = graph1.addVertex(new Domicilie<Deliver>(new Deliver("A")));
		Vertex<Domicilie<Deliver>,String> v8 = graph1.addVertex(new Domicilie<Deliver>(new Deliver("B")));
		
		Vertex<Domicilie<Deliver>,String> v9 = graph1.addVertex(new Domicilie<Deliver>(new Deliver("C")));
		Vertex<Domicilie<Deliver>,String> v10 = graph1.addVertex(new Domicilie<Deliver>(new Deliver("D")));
		
		Vertex<Domicilie<Deliver>,String> v11 = graph1.addVertex(new Domicilie<Deliver>(new Deliver("E")));
		Vertex<Domicilie<Deliver>,String> v12 = graph1.addVertex(new Domicilie<Deliver>(new Deliver("F")));
		
		Edge<Domicilie<Deliver>,String> e7[] = graph1.addEdge(v7, v9,null,1);
		Edge<Domicilie<Deliver>,String> e8[] = graph1.addEdge(v8, v10,null,3);
		Edge<Domicilie<Deliver>,String> e9[] = graph1.addEdge(v11, v12,null,4);
		Edge<Domicilie<Deliver>,String> e10[] = graph1.addEdge(v7, v12,null,5);
		
		Vertex<Domicilie<Deliver>,String> array[] = new Vertex[graph1.vertices_array().length];
		array[0]=v7;array[1]=v9;array[2]=v12;array[3]=v11;array[4]=v8;array[5]=v10;
		
		assertArrayEquals(array, graph1.BFS());
		
	}
	@Test
	public void sceneEight(){
		

		Graph<Domicilie<Deliver>,String> graph1 = new Graph<Domicilie<Deliver>,String>(false);
		
		Vertex<Domicilie<Deliver>,String> v7 = graph1.addVertex(new Domicilie<Deliver>(new Deliver("A")));
		Vertex<Domicilie<Deliver>,String> v8 = graph1.addVertex(new Domicilie<Deliver>(new Deliver("B")));
		
		Vertex<Domicilie<Deliver>,String> v9 = graph1.addVertex(new Domicilie<Deliver>(new Deliver("C")));
		Vertex<Domicilie<Deliver>,String> v10 = graph1.addVertex(new Domicilie<Deliver>(new Deliver("D")));
		
		Vertex<Domicilie<Deliver>,String> v11 = graph1.addVertex(new Domicilie<Deliver>(new Deliver("E")));
		Vertex<Domicilie<Deliver>,String> v12 = graph1.addVertex(new Domicilie<Deliver>(new Deliver("F")));
		
		Edge<Domicilie<Deliver>,String> e7[] = graph1.addEdge(v7, v9,null,1);
		Edge<Domicilie<Deliver>,String> e8[] = graph1.addEdge(v8, v10,null,3);
		Edge<Domicilie<Deliver>,String> e9[] = graph1.addEdge(v11, v12,null,4);
		Edge<Domicilie<Deliver>,String> e10[] = graph1.addEdge(v7, v12,null,5);
		
		Vertex<Domicilie<Deliver>,String>[] array = new Vertex[graph1.vertices_array().length];;
		array[0]=v7;array[1]=v9;array[2]=v12;array[3]=v11;array[4]=v8;array[5]=v10;
		
		assertArrayEquals(array, graph1.DFS());
	
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void sceneNine(){

		Graph<Domicilie<Deliver>,String> graph1 = new Graph<Domicilie<Deliver>,String>(false);
		
		Vertex<Domicilie<Deliver>,String> v7 = graph1.addVertex(new Domicilie<Deliver>(new Deliver("A")));
		Vertex<Domicilie<Deliver>,String> v8 = graph1.addVertex(new Domicilie<Deliver>(new Deliver("B")));
		
		Vertex<Domicilie<Deliver>,String> v9 = graph1.addVertex(new Domicilie<Deliver>(new Deliver("C")));
		Vertex<Domicilie<Deliver>,String> v10 = graph1.addVertex(new Domicilie<Deliver>(new Deliver("D")));
		
		Vertex<Domicilie<Deliver>,String> v11 = graph1.addVertex(new Domicilie<Deliver>(new Deliver("E")));
		Vertex<Domicilie<Deliver>,String> v12 = graph1.addVertex(new Domicilie<Deliver>(new Deliver("F")));
		
		Edge<Domicilie<Deliver>,String> e7[] = graph1.addEdge(v7, v8,null,1);
		Edge<Domicilie<Deliver>,String> e8[] = graph1.addEdge(v8, v9,null,30);
		Edge<Domicilie<Deliver>,String> e9[] = graph1.addEdge(v9, v10,null,4);
		Edge<Domicilie<Deliver>,String> e10[] = graph1.addEdge(v11, v7,null,5);
		
		Vertex<Domicilie<Deliver>,String> v[] = graph1.vertices_array();
		
		for(Edge<Domicilie<Deliver>,String> e : graph1.dijkstra(v[0], v[3])){
			System.out.println(e);
		}
		
		graph1.dijkstra(v[0], v[3]);
		Double vr = 35.0;
		Double vdj = v[3].getDijkstra_value();
		
		assertEquals(vr,vdj);
		
	}
}
