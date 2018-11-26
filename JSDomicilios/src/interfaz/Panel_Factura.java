package interfaz;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Panel_Factura extends JFrame implements ActionListener{

	private JTextField factura;
	private String info;
	private File archivo;
	
	public Panel_Factura(){
		
		archivo = new File("data/Factura.txt");
	}
	
	public void addInfoArchivo() throws IOException{
		
		
		FileWriter fr = new FileWriter(archivo);
		BufferedWriter bw = new BufferedWriter(fr);
	
		bw.write("Factura");
		bw.newLine();
		
		fr.close();
		bw.close();
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
