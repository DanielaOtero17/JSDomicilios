package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PaneInicio extends JPanel implements ActionListener{
	
	private JButton comenzar;
	private MainInterfaz ventana;
	
	public PaneInicio(MainInterfaz v){
		
		ventana = v;
		
		setSize(1000,500);
		
		comenzar = new JButton("Comenzar");
		
		setLayout(new BorderLayout());
		
		add(comenzar,BorderLayout.SOUTH);
		
		comenzar.addActionListener(this);
		comenzar.setActionCommand("comenzar");		
	}
	
	@Override
	public void paint(Graphics g){
		
		ImageIcon wakanda = new ImageIcon("Imagenes/wakanda.png");
		g.drawImage(wakanda.getImage(), 0, 0,1000,500, null);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String event = e.getActionCommand();
		
		if(event.equalsIgnoreCase("comenzar")){
			
			this.setVisible(false);
			ventana.setVisible(true);
			
		}
		
	}

}
