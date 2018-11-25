package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Domicilie;
import model.Edge;
import model.FloydWarshall;
import model.Matrices;
import model.Deliver;
import model.Graph;
import model.Product;
import model.Vertex;

public class Main extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	private City city;
	private Products_Panel productsPanel;
	private Options_Panel optionsPanel;
	private Panel_Datos datos;
	private Graph<Domicilie<Deliver>,String> graph ;
	private PanelFondo imagenFondo;
	private JFrame aux;
	public Main(){
		
		city = new City(this);		
		setLayout(new BorderLayout());
		setTitle("JS_Domicilie");
		setSize(901,622);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		add(city,BorderLayout.CENTER);
		
		productsPanel=new Products_Panel(this);
		optionsPanel=new Options_Panel(this);
		imagenFondo = new PanelFondo(this);
		
		datos = new Panel_Datos();
		
		aux = new JFrame();
		
		add(optionsPanel,BorderLayout.WEST);
		
		add(datos,BorderLayout.SOUTH);
		
		graph = new Graph<Domicilie<Deliver>,String>(false);
	}
	
	 public Graph<Domicilie<Deliver>, String> getGraph() {
		return graph;
	}
	 
	 public void showStarted(){
		 
		 aux.setSize(600,600);
		 aux.setLayout(new BorderLayout());
		 aux.add(imagenFondo,BorderLayout.CENTER);	
		 
		 this.setVisible(false);
		 aux.setVisible(true);
	 }

	public static void main(String[] args) {
		 
		 Main v = new Main();
		 v.setVisible(true);
		 v.showStarted();
		 
//		 Graph<Domicilie,String> graph = new Graph<Domicilie,String>(false);
//		 
//			Vertex<Domicilie,String> v1 = graph.addVertex(new Domicilie<String>("Js_Domic"));
//			Vertex<Domicilie,String> v2 = graph.addVertex(new Domicilie<String>("Js_Domic"));
//			
//			Edge<Domicilie,String> e1[] = graph.addEdge(v1, v2);
//			System.out.println(graph);
	 }
	 
	 public JFrame getAux(){
		 
		 return aux;
	 }
	
	 public void showFrame(){
		 productsPanel.setVisible(true);
	 }
	 
	
	 public void R_repaint(int tope, Matrices arboles){
		 city.R_repaint(tope, arboles); 
	 }
	
	public Deliver iniciarPedido(){
		Deliver deliver=null;
		ArrayList<Product> product= productsPanel.getProduct();
		deliver = new Deliver(productsPanel.getCliente());
		deliver.setProducts(product);
		
		return deliver;
	}
	
	 public void salir(){
		 productsPanel.setVisible(false);
	 }
	 
	 public void acumulado(String text){
		 optionsPanel.setAcumulado(text);
	 }
	 
	 public void recorrerDijkstra(){
		 city.pintarDijkstra();
	 }
	 
	 public void recorrerFloyd(){
		 city.PintarFloyd();
	 }
	 
	 public void mostrarDatos(){
		datos.setDatos(city.getArboles().toString());
		 datos.setDatos2(graph.toString());
	 }

	public City getCity() {
		return city;
	
	}
	
	public Vertex<Domicilie<Deliver>, String > inicial(){
		Deliver d = new Deliver("Js_Domic");
		Domicilie<Deliver> d1 = new Domicilie<Deliver>(d);
		Vertex<Domicilie<Deliver>, String > v = new Vertex<Domicilie<Deliver>, String >(d1);
		v = graph.addVertex(d1);
		return v;
	}
	
	public void release(){
		city.R_paint();
	}
	
	public String nCliente (){
		return productsPanel.getCliente();
	}
	
	public void setCliente (){
		productsPanel.setCliente(null);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
