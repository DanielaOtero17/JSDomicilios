package interfaz;

import model.*;
import Hilos.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class MainInterfaz extends JFrame{
	
	private ImageIcon icono;
	private PanelGrafico imagenFondo;
	private Options_Panel optionsPanel;
	private Products_Panel productsPanel;
	private worker thanos;
	private Deliver deliver;
	private JFrame aux;
	
	public MainInterfaz() throws ClassNotFoundException, IOException{
		getContentPane().setBackground(Color.WHITE);
	
		
		setSize(1000,700);
		setResizable(false);
		setTitle("B I E N V I E N V E N I D O ~ J S  D O M I C I L I O S ~ B I E N V E N I D O");
		
		
		icono = new ImageIcon("Imagenes/iconoWakanda.jpg");
		
		imagenFondo = new PanelGrafico(this);		
		imagenFondo.setBackground(Color.WHITE);
		
		this.setIconImage(icono.getImage());
		
		getContentPane().setLayout(new BorderLayout());
		
		getContentPane().add(imagenFondo,BorderLayout.CENTER);
		
		optionsPanel=new Options_Panel(this);
		optionsPanel.setBackground(Color.WHITE);
		getContentPane().add(optionsPanel, BorderLayout.SOUTH);
		
		
		productsPanel=new Products_Panel(this);
		
		thanos = new worker(352,200);
		
		Product producto1 = new Product("Pizza",1800);
		Product producto2 = new Product("Gaseosa",500);
		Product[] productos = new Product[2];
		productos[0] = producto1;
		productos[1] = producto2;
		deliver = new Deliver("Casita", 760,126,productos);
	}
	
	public void showFrame(){
		productsPanel.setVisible(true);
	}
	
	public void openApp() throws ClassNotFoundException, IOException{
		
		aux = new JFrame();
		PaneInicio panel = new PaneInicio(this);
		
		aux.setSize(1000,560);
		aux.add(panel,BorderLayout.CENTER);
		
		this.setVisible(false);
		aux.setVisible(true);
		
	}
	
	public void moveWorker(){
		
			ThreadMove hilo = new ThreadMove(this, deliver);
			hilo.start();
	}

	public worker getThanos(){
		
		return thanos;
	}
	
	public void refrescar()

	{
		imagenFondo.refresh();
	}
	
	public PanelGrafico getPanelGrafico(){
		
		return imagenFondo;
	}
	public static void main(String[] args) throws ClassNotFoundException, IOException {
//		
//		Graph<Deliver,String> graph = new Graph<Deliver,String>(false);
//		
//		Product[] a1= new Product[]{}; 
//		
//		Vertex<Deliver,String> v1 = graph.addVertex(null);	
//		Vertex<Deliver,String> v2 = graph.addVertex(null);
//		Vertex<Deliver,String> v3 = graph.addVertex(null);
//		Vertex<Deliver,String> v4 = graph.addVertex(null);
//		Vertex<Deliver,String> v5 = graph.addVertex(null);
//		Vertex<Deliver,String> v6 = graph.addVertex(null);
//		
//		graph.addEdge(v1, v4);
//		graph.addEdge(v4, v6);
//		graph.addEdge(v2,v5);
//		graph.addEdge(v5, v4);
//		graph.addEdge(v1, v3);
//		
//		System.out.println("Graph print: \n");
//		System.out.println(graph);
//		
//		System.out.println("Tours in the graph \n");
//		
//		System.out.println("BFS:");
//		for(Vertex<Deliver,String> v : graph.BFS())
//			System.out.print(v + " ");
//		
//		System.out.println("\n\nDFS");
//		for(Vertex<Deliver,String> v : graph.DFS())
//			System.out.print(v + " ");

		MainInterfaz main = new MainInterfaz();
		
		main.openApp();
	}

}
