package interfaz;

import java.awt.*;
import javax.swing.*;

public class PanelGrafico extends JPanel{
	
	private MainInterfaz ventana;
	
	public PanelGrafico(MainInterfaz v){
		
		ventana = v;
		setBackground(Color.WHITE);
		
		setSize(700,700);
		
	}
	public void refresh()
	{
		
		removeAll();
	
	}

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		int x = ventana.getThanos().getX();
		int y = ventana.getThanos().getY();
		
		ImageIcon fondo = new ImageIcon("Imagenes/fondo WAKANDA.png");		
		g.drawImage(fondo.getImage(), 0, 0, 990, 600, null);
		g.drawImage(ventana.getThanos().getImagen().getImage(), x,y,100,100,null);

	}
	
	

}
