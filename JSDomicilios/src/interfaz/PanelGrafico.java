package interfaz;

import java.awt.*;
import javax.swing.*;

public class PanelGrafico extends JPanel{
	
	
	public PanelGrafico(){
		setBackground(Color.WHITE);
		
		setSize(700,700);
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		
		ImageIcon fondo = new ImageIcon("Imagenes/fondo WAKANDA.png");
		ImageIcon thanos = new ImageIcon("Imagenes/Thanos.png");
		g.drawImage(fondo.getImage(), 0, 0, 990, 600, null);
		
		g.drawImage(thanos.getImage(), 352,200,100,100,null);

	}
	

}
