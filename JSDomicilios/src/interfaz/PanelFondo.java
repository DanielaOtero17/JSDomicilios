package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelFondo extends JPanel implements ActionListener {
	
	private JButton comenzar;
	private Main main;
	
	public PanelFondo(Main m){
		
		JPanel panel = new JPanel();
		
		main = m;
		 
		 setLayout(new BorderLayout());
		 setSize(600,600);
		 
		 comenzar = new JButton("Comenzar");
		 comenzar.setBackground(Color.ORANGE);
		 
		 add(comenzar,BorderLayout.SOUTH);
		 comenzar.addActionListener(this);
		 comenzar.setActionCommand("Comenzar");
		
	}

	@Override
	public void paint(Graphics g){
		
		ImageIcon wakanda = new ImageIcon("data/wakanda.png");
		g.drawImage(wakanda.getImage(), 0, 0,600,600, null);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		String seleccion = e.getActionCommand();
		
		if(seleccion.equalsIgnoreCase("Comenzar")){
			this.setVisible(false);
			main.setVisible(true);		
			main.getAux().setVisible(false);
		}
	}
	
}
