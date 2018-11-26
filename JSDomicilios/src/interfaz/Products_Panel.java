package interfaz;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import model.Product;

public class Products_Panel extends JFrame implements ActionListener {
/**
 * 
 */
	private static final long serialVersionUID = 1L;
	
	public final static int HIGH=2;
	public final static int WIDTH=3;
	
	public final static String ACCEPT="accept";
	private JButton[][]  butsAdd;
	private JButton butAccept;
	private JLabel[][] labImages;
	private Main p;
	private JPanel aux;
	private JPanel[][] matPanel;
	
	private ArrayList<Product> product;
	private String cliente;
	
	public Products_Panel(Main m){
		setBackground(Color.WHITE);
		setVisible(false);
		setResizable(false);
		String[] data = new String[6];
		data[0]="P. Italiana";
		data[1]="P. Napolitana";
		data[2]="P. especial";
		data[3]="Gaseosa";
		data[4]="Arroz Chino";
		data[5]="Papitas";
		cliente=null;
		product = new ArrayList<Product>();
		
		matPanel=new JPanel[HIGH][WIDTH];
		setLayout(new BorderLayout());
		butAccept=new JButton("Accept");
		butAccept.addActionListener(this);
		butAccept.setActionCommand(ACCEPT);
		p=m;
		aux=new JPanel();
		aux.setBackground(Color.WHITE);
		aux.setLayout(new GridLayout(HIGH, WIDTH));
		butsAdd=new JButton[HIGH][WIDTH];
		labImages=new JLabel[HIGH][WIDTH];
		int contador=0;
		for(int i=0; i<HIGH; i++){
			for(int j=0; j<WIDTH; j++){
				butsAdd[i][j]=new JButton(data[contador]);
				butsAdd[i][j].addActionListener(this);
				butsAdd[i][j].setActionCommand("buy" + contador);
				
				labImages[i][j]=new JLabel();
				ImageIcon icono=new ImageIcon("data/images/image"+contador+".png");
				labImages[i][j].setIcon(icono);
				matPanel[i][j]=new JPanel();
				matPanel[i][j].setBackground(Color.WHITE);
				matPanel[i][j].setLayout(new BorderLayout());
				matPanel[i][j].add(labImages[i][j], BorderLayout.CENTER);
				matPanel[i][j].add(butsAdd[i][j], BorderLayout.SOUTH);

				aux.add(matPanel[i][j]);
				contador++;
				
			}
		}
		getContentPane().add(aux, BorderLayout.CENTER);
		getContentPane().add(butAccept, BorderLayout.SOUTH);
	pack();	
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		String command = e.getActionCommand();
		File archivo = new File("data/Factura.txt");
		JTextArea info2 = new JTextArea("FACTURA\n");
		
		int total =0;
			if(command.equals("buy0")){
				 product.add(new Product("Italiana",30000));
				 
				 JOptionPane.showMessageDialog(this, "Añadido");
			 }
			 if(command.equals("buy1")){
				 product.add(new Product("Napolitana",35000));
				 
				 JOptionPane.showMessageDialog(this, "Añadido");
			 }
			 if(command.equals("buy2")){
				 product.add(new Product("Especial",40000));
				
				 JOptionPane.showMessageDialog(this, "Añadido");
			 }
			 if(command.equals("buy3")){
				 product.add(new Product("Refresco",6500));
				 
				 JOptionPane.showMessageDialog(this, "Añadido");
			 }
			 if(command.equals("buy4")){
				 product.add(new Product("Arroz chino",50000));
				 
				 JOptionPane.showMessageDialog(this, "Añadido");
			 }
			 if(command.equals("buy5")){
				 product.add(new Product("Papas Fritas",5000));
				 
				 JOptionPane.showMessageDialog(this, "Añadido");
			 }
			 
			if(command.equals(ACCEPT)){
				try{
					String Cliente = JOptionPane.showInputDialog(this, "Cliente: ");
					if(!(Cliente.equals(" "))){
						setCliente(Cliente);
				
				for(int i=0; i<product.size();i++){
					
					total += product.get(i).getValor();
		info2.append("\n" + product.get(i).getNombre() + "                 " + product.get(i).getValor());
				}
				
		info2.append("\n" + "Cantidad de Productos comprados" + "          " + product.size());
		info2.append("\n" + "Total a pagar" + "                            " + total);
		
		info2.setEditable(false);
		
		JScrollPane js = new JScrollPane(info2);
		
		info2.setText("FACTURA\n");
		
						JFrame aux = new JFrame();
				
				aux.setSize(300,300);
				aux.setLayout(new BorderLayout());
				aux.add(js, BorderLayout.CENTER);
		
				aux.setVisible(true);
						p.salir();
						
					}
					else{
						JOptionPane.showMessageDialog(this, "Escriba un nombre");
					}
					}
					catch(Exception ex){
						JOptionPane.showMessageDialog(this, "Thanks ...¡¡");
					}
			 }
		 
	}


	public String getCliente() {
		return cliente;
	}
	
	public void setCliente(String product) {
		this.cliente = product;
	}


	public ArrayList<Product> getProduct() {
		return product;
	}


	public void setProduct(ArrayList<Product> product) {
		this.product = product;
	}
}
