package interfaz;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import model.Product;

public class Options_Panel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static String START="start";
	public final static String TRAFFIC="traffic";
	public final static String DISTANCE="distance";
	public final static String ORDER="order";
	private JButton butStart;
	private JButton butTraffic;
	private JButton butDistance;
	private JButton butOrder;
	private Main interfaz;
	
	private JTextField acumulado;
	
	public void setAcumulado(String acumulado) {
		this.acumulado.setText(acumulado);
	}

	public Options_Panel(Main m){
		setBackground(Color.WHITE);
		interfaz=m;
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Options", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
		setLayout(new BorderLayout());
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(Color.WHITE);
		add(panelBotones,BorderLayout.NORTH);
		panelBotones.setLayout(new GridLayout(4, 1,0, 7));
		butStart=new JButton("Start");
		panelBotones.add(butStart);
		butStart.setFont(new Font("Kalinga", Font.PLAIN, 11));
		butStart.setBackground(Color.WHITE);
		
		butStart.addActionListener(this);
		butStart.setActionCommand(START);
		butTraffic=new JButton("Lower traffic");
		panelBotones.add(butTraffic);
		butTraffic.setFont(new Font("Kalinga", Font.PLAIN, 11));
		butTraffic.setBackground(Color.WHITE);
		butTraffic.addActionListener(this);
		butTraffic.setActionCommand(TRAFFIC);
		butDistance=new JButton("Lower distance");
		panelBotones.add(butDistance);
		butDistance.setFont(new Font("Kalinga", Font.PLAIN, 11));
		butDistance.setBackground(Color.WHITE);
		butDistance.addActionListener(this);
		butDistance.setActionCommand(DISTANCE);
		butOrder=new JButton("Order");
		panelBotones.add(butOrder);
		butOrder.setFont(new Font("Kalinga", Font.PLAIN, 11));
		butOrder.setBackground(Color.WHITE);
		butOrder.addActionListener(this);
		butOrder.setActionCommand(ORDER);
		panelBotones.setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 1, 0, 0));
		
		acumulado = new JTextField();
		acumulado.setText("0");
		acumulado.setBackground(new Color(255, 250, 205));
		acumulado.setHorizontalAlignment(SwingConstants.CENTER);
		acumulado.setEditable(false);
		
		JLabel label_2 = new JLabel();
		label_2.setBackground(Color.WHITE);
		panel.add(label_2);
		
		JPanel panelText = new JPanel();
		panel.add(panelText);
		panelText.setBackground(Color.WHITE);
		panelText.setLayout(new BorderLayout());
		JLabel label = new JLabel("Distancia (km)");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBackground(Color.WHITE);
		panelText.add(label,BorderLayout.CENTER);
		panelText.add(acumulado,BorderLayout.SOUTH);
		panelText.setBackground(Color.WHITE);
		
		JLabel domi = new JLabel("© JS Domicilies ©");
		domi.setVerticalAlignment(SwingConstants.BOTTOM);
		domi.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(domi);
		
		JPanel panelAux = new JPanel();
		panel.add(panelAux);
		panelAux.setBackground(Color.WHITE);
		ImageIcon img = new ImageIcon("data/logo.png");
		JLabel lb = new JLabel(img);
		lb.setHorizontalAlignment(SwingConstants.TRAILING);
		lb.setBounds(80, 80, 80, 80);
		panelAux.add(lb);
		
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		String comando=a.getActionCommand();
		if(comando.equals(START)){
			interfaz.recorrer();
		}else if(comando.equals(TRAFFIC)){
			interfaz.mostrarDatos();
			
		}else if(comando.equals(DISTANCE)){
			
		}else if(comando.equals(ORDER)){
		 interfaz.showFrame();
		}
		
	}
}
