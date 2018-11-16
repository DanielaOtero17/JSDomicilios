package interfaz;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Products_Panel extends JFrame implements ActionListener {

	public final static int HIGH=2;
	public final static int WIDTH=3;
	public final static String BUY="buy";
	public final static String ACCEPT="accept";
	private JButton[][]  butsAdd;
	private JButton butAccept;
	private JLabel[][] labImages;
	//private JTextField[][] txtQuantity;
	private MainInterfaz interfaz;
	private JPanel aux;
	private JPanel[][] matPanel;
	private ImageIcon icono;
	
	public Products_Panel(MainInterfaz m){
		icono = new ImageIcon("Imagenes/iconoWakanda.jpg");
		this.setIconImage(icono.getImage());
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
		matPanel=new JPanel[HIGH][WIDTH];
		getContentPane().setLayout(new BorderLayout());
		butAccept=new JButton("Accept");
		butAccept.addActionListener(this);
		butAccept.setActionCommand(ACCEPT);
		interfaz=m;
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
				butsAdd[i][j].setActionCommand(BUY);
				
				labImages[i][j]=new JLabel();
				ImageIcon icono=new ImageIcon("Imagenes/images/image"+contador+".png");
				labImages[i][j].setIcon(icono);
				matPanel[i][j]=new JPanel();
				matPanel[i][j].setLayout(new BorderLayout());
				matPanel[i][j].add(labImages[i][j], BorderLayout.CENTER);
				matPanel[i][j].add(butsAdd[i][j], BorderLayout.SOUTH);

				aux.add(matPanel[i][j]);
				contador++;
				
			}
		}

		add(aux, BorderLayout.CENTER);
		add(butAccept, BorderLayout.SOUTH);
	pack();	
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
