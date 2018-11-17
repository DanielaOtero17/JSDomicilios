package interfaz;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class Panel_Datos extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextArea datos;

	public Panel_Datos(){
		setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Datos Entrega", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)), "Datos Entrega", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		datos = new JTextArea();
		datos.setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		add(datos,BorderLayout.CENTER);
	}

	public void setDatos(String datos) {
		this.datos.setText(datos);
	}
}
