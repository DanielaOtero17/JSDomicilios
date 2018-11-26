package interfaz;

import javax.swing.*;

import model.Product;

import java.awt.*;
import java.awt.event.*;


public class Info_Products extends JFrame implements ActionListener{

	private Main VentanaPrincipal;
	private JTextArea info;
	private JButton comprar;
	
	
	public Info_Products(Main v){
		
		setSize(400,250);
		setLayout(new BorderLayout());
		setResizable(false);
		setTitle("Productos");
		
		comprar = new JButton("COMPRAR");
		
		info = new JTextArea("INFORMACIÓN DE PRODUCTOS Y PRECIOS\n\n");
		
		VentanaPrincipal = v;
		Product[] pro = new Product[6];
		pro[0] = new Product("Pizza Italiana",30000);
		pro[1] = new Product("Pizza Napolitana",35000);
		pro[2] = new Product("Pizza Especial",40000);
		pro[3] = new Product("Refresco",6500);
		pro[4] = new Product("Arroz chino",50000);
		pro[5] = new Product("Papas Fritas",5000);
		
		for(int i=0; i< pro.length; i++){
		
		info.append( pro[i].getNombre());
		info.append("                      ");
		info.append( pro[i].getValor()+"\n");
			
		}
	info.setBackground(Color.BLACK);
	info.setFont(new Font("Times New Roman",20,20));
	info.setForeground(Color.WHITE);
	comprar.setBackground(Color.WHITE);
		add(info,BorderLayout.CENTER);
		add(comprar,BorderLayout.SOUTH);
		
		comprar.setActionCommand("Comprar");
		comprar.addActionListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		String seleccion = e.getActionCommand();
		
		if(seleccion.equalsIgnoreCase("comprar")){
			
			VentanaPrincipal.getProductsPanel().setVisible(true);
			this.setVisible(false);
		}
		
	}
}
