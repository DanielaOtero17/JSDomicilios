package interfaz;

import model.Edge;
import model.Vertex;
import model.Graph;

import java.io.*;
import javax.swing.*;
import java.awt.*;

public class MainInterfaz extends JFrame{
	
	private ImageIcon icono;
	private PanelGrafico imagenFondo;
	private Options_Panel optionsPanel;
	private Products_Panel productsPanel;
	public MainInterfaz(){
		getContentPane().setBackground(Color.WHITE);
	
		
		setSize(1000,700);
		setResizable(false);
		setTitle("B I E N V I E N V E N I D O ~ J S  D O M I C I L I O S ~ B I E N V E N I D O");
		
		icono = new ImageIcon("Imagenes/iconoWakanda.jpg");
		
		imagenFondo = new PanelGrafico();		
		imagenFondo.setBackground(Color.WHITE);
		
		this.setIconImage(icono.getImage());
		
		getContentPane().setLayout(new BorderLayout());
		
		getContentPane().add(imagenFondo,BorderLayout.CENTER);
		
		optionsPanel=new Options_Panel(this);
		optionsPanel.setBackground(Color.WHITE);
		getContentPane().add(optionsPanel, BorderLayout.SOUTH);
		
		
		productsPanel=new Products_Panel(this);
	}
	
	public void showFrame(){
		productsPanel.setVisible(true);
	}

	public static void main(String[] args) {
		
		Graph<String,String> graph = new Graph<String,String>(false);
		
		Vertex<String,String> v1 = graph.addVertex("Domic-1");	
		Vertex<String,String> v2 = graph.addVertex("Domic-2");
		Vertex<String,String> v3 = graph.addVertex("Domic-3");
		Vertex<String,String> v4 = graph.addVertex("Domic-4");
		Vertex<String,String> v5 = graph.addVertex("Domic-5");
		Vertex<String,String> v6 = graph.addVertex("Domic-6");
		
		graph.addEdge(v1, v4);
		graph.addEdge(v4, v6);
		graph.addEdge(v2,v5);
		graph.addEdge(v5, v4);
		graph.addEdge(v1, v3);
		
		System.out.println("Graph print: \n");
		System.out.println(graph);
		
		System.out.println("Tours in the graph \n");
		
		System.out.println("BFS:");
		for(Vertex<String,String> v : graph.BFS())
			System.out.print(v + " ");
		
		System.out.println("\n\nDFS");
		for(Vertex<String,String> v : graph.DFS())
			System.out.print(v + " ");

		MainInterfaz main = new MainInterfaz();
		main.setVisible(true);
	}

}
