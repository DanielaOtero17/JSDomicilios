package interfaz;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Domicilie;
import model.Edge;
import model.Graph;
import model.Matrices;
import model.Deliver;
import model.Pintar;
import model.Product;
import model.Vertex;

public class Main extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private City city;
	private Products_Panel productsPanel;
	private Options_Panel optionsPanel;
	private Panel_Datos datos;
	private Graph<Domicilie<Deliver>,String> graph ;
	
	public Main(){
		
		
		setLayout(new BorderLayout());
		setTitle("JS_Domicilie");
		setSize(901,622);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		city = new City(this);		
		productsPanel= new Products_Panel(this);
		optionsPanel=new Options_Panel(this);
		
		datos = new Panel_Datos();
		
		
		add(city,BorderLayout.CENTER);
		add(optionsPanel,BorderLayout.WEST);
		
		add(datos,BorderLayout.EAST);
		
		graph = new Graph<Domicilie<Deliver>,String>(true);
	}
	
	 public Graph<Domicilie<Deliver>, String> getGraph() {
		return graph;
	}

	public static void main(String[] args) {
		
		 Main v = new Main();
		 v.setVisible(true);
		 

	 }
	
	 public void showFrame(){
		 productsPanel.setVisible(true);
	 }
	 
	 public Products_Panel getProductsPanel(){
		 
		 return productsPanel;
	 }
	 
	 public Product[] products(){
		 
		 Product[] products = new Product[productsPanel.getProduct().size()];
		 
		 for(int i =0;i<productsPanel.getProduct().size();i++){
			 products[i]=productsPanel.getProduct().get(i);
		 }
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
	 
	 public void recorrerKruskal(){
		 city.PintarKruskal();
	 }
	 
	 public void mostrarDatos(){
		datos.setDatos(city.getArboles().toString());
		 datos.setDatos2(graph.toString());
	 }

	public City getCity() {
		return city;
	
	}
	
	public Panel_Datos panelDatos(){
		return datos;
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
	

	public void cargar() {
		try{
		JFileChooser fc = new JFileChooser("./data");
		fc.setDialogTitle("Ciudad");
		int resultado = fc.showOpenDialog(this);
		if (resultado == JFileChooser.APPROVE_OPTION) {
			File archivo = fc.getSelectedFile();
			if (archivo != null) {
				File file = new File("data/" + archivo.getName());
				
				int [][] m = graph.cargarMatriz(file);
				
				
				int [] cx =new int[graph.getCordeX().length];
				int [] cy =new int[graph.getCordeY().length];
				
				for(int x =0;x<graph.getCordeX().length;x++){
				
					cx[x]= Integer.parseInt(graph.getCordeX()[x]);	
				}
				for(int y =0;y<graph.getCordeY().length;y++){
				
					cy[y]= Integer.parseInt(graph.getCordeY()[y]);		
				}
				
				city.getArboles().setmAdyacencia(m);
				city.getArboles().setNombre(graph.getVertices());
				city.getArboles().setCordeX(cx);;
				city.getArboles().setCordeY(cy);;
				
//				for (int x=0; x < m.length; x++) {
//					for (int y=0; y < m[0].length; y++) {
//						city.getArboles().getmAdyacencia()[x][y]=m[x][y];
//					}
//				}
//				
//				for (int x=0; x < m.length-1; x++) {
//			    	city.getArboles().getNombre()[x]=graph.getVertices()[x];
//				}
//				
//				for (int x=0; x < cx.length; x++) {
//			    	city.getArboles().setCordeX(x, cx[x]);
//				}
//				
//				for (int x=0; x < cy.length; x++) {
//					city.getArboles().setCordeY(x, cy[x]);
//				}
				pintar(city.getArboles());
				city.setTope(m.length-1);
				
				imprimir(m);
				mostrarDatos();
			}
		}
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,"Ocurrio algun error");
		}
	}
	
	public void imprimir(int[][] matriz) {
		System.out.println(matriz.length+"");
		for (int x=0; x < matriz.length-1; x++) {
	    	System.out.print(city.getArboles().getNombre()[x] + " ");
	    }
	    System.out.println(); 
		for (int x=0; x < matriz.length; x++) {
	    	System.out.print(city.getArboles().getCordeX(x) + " ");
	    }
	    System.out.println();
	    for (int x=0; x < matriz.length; x++) {
	    	System.out.print(city.getArboles().getCordeY(x) + " ");
	    }
	    System.out.println();
	    for (int x=0; x < matriz.length; x++) {
	        System.out.print("");
	        for (int y=0; y < matriz[x].length; y++) {
	          System.out.print(matriz[x][y]);
	          if (y!=matriz[x].length-1) System.out.print(" ");
	        }
	        System.out.println("");
	    }
	}
	
	public void pintar(Matrices arboles){
		 
		 for (int j = 0; j < 50; j++) {  
			 for (int k = 0; k < 50; k++) {     
				 if(arboles.getmAdyacencia(j, k) == 1)
					 Pintar.pintarLinea(city.getGraphics(),arboles.getCordeX(j),arboles.getCordeY(j), arboles.getCordeX(k), arboles.getCordeY(k),arboles.getmCoeficiente(j, k));
			 }
		 } 
		 for (int j = 0; j < 50; j++)    
			 Pintar.pintarCasita(city.getGraphics(), arboles.getCordeX(j),arboles.getCordeY(j),String.valueOf(arboles.getNombre(j))); 
	 }
}
