package interfaz;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Arboles;
import model.Deliver;
import model.Graph;
import model.Product;

public class Main extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private City city;
	
	private Products_Panel productsPanel;
	private Options_Panel optionsPanel;
	private Panel_Datos datos;
	
	public Main(){
		
		city = new City(this);		
		setLayout(new BorderLayout());
		setTitle("Wakanda");
		setSize(575,499);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		add(city,BorderLayout.CENTER);
		
		productsPanel=new Products_Panel(this);
		optionsPanel=new Options_Panel(this);
		
		datos = new Panel_Datos();
		
		add(optionsPanel,BorderLayout.WEST);
		
		add(datos,BorderLayout.EAST);
	}
	
	 public static void main(String[] args) {        
	        Main v = new Main();
	        v.setVisible(true);  
	 }
	 
	 
	
	 public void showFrame(){
		 productsPanel.setVisible(true);
	 }
	 
	
	 public void R_repaint(int tope, Arboles arboles){
		 city.R_repaint(tope, arboles); 
	 }
	
	public Deliver iniciarPedido(){
		Deliver deliver=null;
		ArrayList<Product> product= productsPanel.getProduct();
		deliver = new Deliver();
		deliver.setProducts(product);
		deliver.setName(productsPanel.getCliente());
		
		return deliver;
	}
	
	 public void salir(){
		 productsPanel.setVisible(false);
	 }
	 
	 public void acumulado(String text){
		 optionsPanel.setAcumulado(text);
	 }
	 
	 public void recorrer(){
		 city.pintarDijkstra();
	 }
	 
	 public void mostrarDatos(){
		datos.setDatos(city.getArboles().toString());
	 }
	
}
