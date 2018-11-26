package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

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
import model.Pintar;
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
	private Info_Products info;
	public Main(){
		
		
		setLayout(new BorderLayout());
		setTitle("JS_Domicilie");
		setSize(901,622);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		info = new Info_Products(this);
		city = new City(this);		
		productsPanel= new Products_Panel(this);
		optionsPanel=new Options_Panel(this);
		imagenFondo = new PanelFondo(this);
		
		datos = new Panel_Datos();
		
		aux = new JFrame();
		
		add(city,BorderLayout.CENTER);
		add(optionsPanel,BorderLayout.WEST);
		
		add(datos,BorderLayout.EAST);
		
		graph = new Graph<Domicilie<Deliver>,String>(true);
	}
	
	 public Graph<Domicilie<Deliver>, String> getGraph() {
		return graph;
	}
	 
	 public void showStarted(){
		 
		 aux.setSize(600,600);
		 aux.setLayout(new BorderLayout());
		 aux.add(imagenFondo,BorderLayout.CENTER);
		 aux.setLocationRelativeTo(null);
		 
		 this.setVisible(false);
		 aux.setVisible(true);
	 }

	public static void main(String[] args) {
		
		 Main v = new Main();
		 v.setVisible(true);
//		 v.showStarted();
		 

	 }
	 
	 public JFrame getAux(){
		 
		 return aux;
	 }
	
	 public void showFrame(){
		 info.setVisible(true);
	 }
	 
	 public Products_Panel getProductsPanel(){
		 
		 return productsPanel;
	 }
	 
	 public Product[] products(){
		 
		 Product[] products = productsPanel.getProducts();
		 return products;
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
		Deliver d = new Deliver("<< Js_Domic >>");
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
	
	public void setCliente (int tope){
		productsPanel.setCliente("Entrega:" + tope);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	public void añadirVertices(int tope){
		
		for (int j = 0; j <tope-1; j++){
			Domicilie<Deliver> d = new Domicilie<Deliver>(new Deliver(city.getArboles().getNombre(j)));
			graph.addVertex(d);	
		}
	}
	
	public void añadirAristas(int id,int id2,int tope){	
		Vertex<Domicilie<Deliver>,String> v = null;
		Vertex<Domicilie<Deliver>,String> v2 = null;	
		Domicilie<Deliver> d = new Domicilie<Deliver>(new Deliver(city.getArboles().getNombre(id)));		
		Domicilie<Deliver> d2 = new Domicilie<Deliver>(new Deliver(city.getArboles().getNombre(id2)));	
		v = graph.addVertex(d);	
		v2 =graph.addVertex(d2);
		for (int j = 0; j < tope; j++) { 		
			for (int k = 0; k < tope; k++) {     				
				if(city.getArboles().getmAdyacencia(j, k) == 1){							
					Edge<Domicilie<Deliver>,String> e1[] = graph.addEdge(v, v2,null,city.getArboles().getmCoeficiente(j, k));							
				}			
		
			}
		}
	}
}
