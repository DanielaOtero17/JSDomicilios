package interfaz;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import model.Product;

import javax.swing.border.LineBorder;

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
	private JLabel[][] labPrecios;
	private Main p;
	private JPanel aux;
	private JPanel[][] matPanel;
	private ArrayList<Product> product;
	private String cliente;
	
	private JPanel panel;
	private JLabel txt;
	
	private double acumulado;
	
	private Product[] pro;
	
	public Products_Panel(Main m){
		setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Tienda");
		
		pro = new Product[6];
		pro[0] = new Product("Pizza Italiana",30000);
		pro[1] = new Product("Pizza Napolitana",35000);
		pro[2] = new Product("Pizza Especial",40000);
		pro[3] = new Product("Refresco",6500);
		pro[4] = new Product("Arroz chino",50000);
		pro[5] = new Product("Papas Fritas",5000);
		cliente=null;
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.setBackground(Color.BLACK);
		panel.setForeground(Color.WHITE);
		JLabel l = new JLabel(" Valor Total De la Compra: ");
		l.setForeground(Color.WHITE);
		panel.add(l);
		txt = new JLabel();
		
		txt.setForeground(Color.WHITE);
		txt.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel.add(txt);
		
		product = new ArrayList<Product>();
		
		acumulado =0;
		
		setLocationRelativeTo(null);
		setSize(397, 370);
		matPanel=new JPanel[HIGH +1][WIDTH];
		getContentPane().setLayout(new BorderLayout());
		butAccept=new JButton("Accept");
		butAccept.setForeground(Color.BLACK);
		butAccept.setBackground(Color.GREEN);
		butAccept.addActionListener(this);
		butAccept.setActionCommand(ACCEPT);
		p=m;
		aux=new JPanel();
		aux.setBackground(Color.WHITE);
		aux.setLayout(new GridLayout(HIGH, WIDTH));
		butsAdd=new JButton[HIGH][WIDTH];
		labImages=new JLabel[HIGH][WIDTH];
		labPrecios=new JLabel[HIGH][WIDTH];
		int contador=0;
		for(int i=0; i<HIGH; i++){
			for(int j=0; j<WIDTH; j++){
				butsAdd[i][j]=new JButton(pro[contador].getNombre());
				butsAdd[i][j].addActionListener(this);
				butsAdd[i][j].setActionCommand("buy" + contador);
				butsAdd[i][j].setForeground(Color.BLACK);
				butsAdd[i][j].setBackground(Color.RED);
				
				labImages[i][j]=new JLabel();
				ImageIcon icono=new ImageIcon("data/images/image"+contador+".png");
				labImages[i][j].setIcon(icono);
				labImages[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				
				labPrecios[i][j]=new JLabel("$ " + pro[contador].getValor());
				labPrecios[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				labPrecios[i][j].setForeground(Color.BLACK);
				
				matPanel[i][j]=new JPanel();
				matPanel[i][j].setBackground(Color.WHITE);
				matPanel[i][j].setLayout(new BorderLayout());
				matPanel[i][j].add(labImages[i][j], BorderLayout.NORTH);
				matPanel[i][j].add(labPrecios[i][j], BorderLayout.CENTER);
				matPanel[i][j].add(butsAdd[i][j], BorderLayout.SOUTH);
				matPanel[i][j].setBorder(new LineBorder(Color.LIGHT_GRAY));

				aux.add(matPanel[i][j]);
				contador++;
				
			}
		}

		add(aux, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		add(butAccept, BorderLayout.SOUTH);
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		 String command = e.getActionCommand();
		 if(command.equals("buy0")){
			 product.add(pro[0]);
			 JOptionPane.showMessageDialog(this, "Añadido");
			 compra(pro[0].getValor());
		 }
		 if(command.equals("buy1")){
			 product.add(pro[1]);
			 JOptionPane.showMessageDialog(this, "Añadido");
			 compra(pro[1].getValor());
		 }
		 if(command.equals("buy2")){
			 product.add(pro[2]);
			 JOptionPane.showMessageDialog(this, "Añadido");
			 compra(pro[2].getValor());
		 }
		 if(command.equals("buy3")){
			 product.add(pro[3]);
			 JOptionPane.showMessageDialog(this, "Añadido");
			 compra(pro[3].getValor());
		 }
		 if(command.equals("buy4")){
			 product.add(pro[4]);
			 JOptionPane.showMessageDialog(this, "Añadido");
			 compra(pro[4].getValor());
		 }
		 if(command.equals("buy5")){
			 product.add(pro[5]);
			 JOptionPane.showMessageDialog(this, "Añadido");
			 compra(pro[5].getValor());
		 }
		 
		if(command.equals(ACCEPT)){
			try{
				String Cliente = JOptionPane.showInputDialog(this, "Registre el nombre: ");
				if(!(Cliente.equals(" "))){
					setCliente(Cliente);
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
	
	public void compra(double data){
		acumulado += data;
		txt.setText("$  " +acumulado);
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
