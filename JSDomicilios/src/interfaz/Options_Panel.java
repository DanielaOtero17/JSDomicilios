package interfaz;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Options_Panel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	public final static String START="start";
	public final static String CARGAR="cargar";
	public final static String DISTANCE="distance";
	public final static String ORDER="order";
	private JButton butStart;
	private JButton butUpload;
	private JButton butDistance;
	private JButton butOrder;
	private Main interfaz;
	
	public final static String REPAINT="repain";
	
	private JTextField acumulado;
	
	private JButton repaint;
	
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
		panelBotones.setLayout(new GridLayout(5, 1,0, 7));
		butOrder=new JButton("Order");
		panelBotones.add(butOrder);
		butOrder.setFont(new Font("Kalinga", Font.PLAIN, 11));
		butOrder.setBackground(Color.WHITE);
		butOrder.addActionListener(this);
		butOrder.setActionCommand(ORDER);
		butStart=new JButton("Start");
		
		butStart.setFont(new Font("Kalinga", Font.PLAIN, 11));
		butStart.setBackground(Color.WHITE);
		panelBotones.add(butStart);
		
		butStart.addActionListener(this);
		butStart.setActionCommand(START);
		
		repaint = new JButton("Refresh");
		repaint.setFont(new Font("Kalinga", Font.PLAIN, 11));
		repaint.setBackground(Color.WHITE);
		repaint.addActionListener(this);
		repaint.setActionCommand(REPAINT);
		butDistance=new JButton("Lower distance");
		panelBotones.add(butDistance);
		butDistance.setFont(new Font("Kalinga", Font.PLAIN, 11));
		butDistance.setBackground(Color.WHITE);
		butDistance.addActionListener(this);
		butDistance.setActionCommand(DISTANCE);
		butUpload=new JButton("Load city");
		panelBotones.add(butUpload);
		butUpload.setFont(new Font("Kalinga", Font.PLAIN, 11));
		butUpload.setBackground(Color.WHITE);
		butUpload.addActionListener(this);
		butUpload.setActionCommand(CARGAR);
		panelBotones.setBackground(Color.WHITE);
		
		panelBotones.add(new JLabel());
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout());
		
		acumulado = new JTextField();
		acumulado.setText("0");
		acumulado.setBackground(new Color(255, 250, 205));
		acumulado.setHorizontalAlignment(SwingConstants.CENTER);
		acumulado.setEditable(false);
		
		JPanel panelText = new JPanel();
		
		panelText.setBackground(Color.WHITE);
		panelText.setLayout(new BorderLayout());
		JLabel label = new JLabel("Distancia (km)");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBackground(Color.WHITE);
		panelText.add(label,BorderLayout.CENTER);
		panelText.add(acumulado,BorderLayout.SOUTH);
		panelText.setBackground(Color.WHITE);
		
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new GridLayout(2,1));
		
		JLabel domi = new JLabel("© JS Domicilies ©");
		domi.setVerticalAlignment(SwingConstants.BOTTOM);
		domi.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(domi,BorderLayout.NORTH);
		
		
		
		panelAux.setBackground(Color.WHITE);
		ImageIcon img = new ImageIcon("data/logo.png");
		JLabel lb = new JLabel(img);
		lb.setHorizontalAlignment(SwingConstants.TRAILING);
		lb.setBounds(80, 80, 80, 80);
		panelAux.add(lb,BorderLayout.CENTER);
		
		panel.add(panelText,BorderLayout.NORTH);
		
		panel.add(panelAux,BorderLayout.CENTER);
		
		panel.add(repaint,BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		String comando=a.getActionCommand();
		if(comando.equals(START)){
			try{
				String cliente = JOptionPane.showInputDialog(this, " Inserte:  \n " + "1: Dijkstra      2 : FloydWarsshall");
				if(cliente!=""){
					if(cliente.equals("1")){
						interfaz.recorrerDijkstra();
					}
					else if(cliente.equals("2")){
						interfaz.recorrerKruskal();
					}
					else{
						JOptionPane.showMessageDialog(this, "Solo se puede escribir 1 ò 2");
					}
				}
				else{
					JOptionPane.showMessageDialog(this, "No puede haber campos vacios");
				}
				}
				catch(Exception ex){
					ex.printStackTrace();
					JOptionPane.showMessageDialog(this, "Thanks ...¡¡");
				}
		}
		else if(comando.equals(CARGAR)){
			interfaz.cargar();
			
		}else if(comando.equals(DISTANCE)){
			
		}else if(comando.equals(ORDER)){
		 interfaz.showFrame();
		}
		else if(comando.equals(REPAINT)){
			interfaz.release();
		}
		
	}
}
