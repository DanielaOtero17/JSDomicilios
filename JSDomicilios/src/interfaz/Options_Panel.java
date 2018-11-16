package interfaz;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Options_Panel extends JPanel implements ActionListener{

	public final static String START="start";
	public final static String TRAFFIC="traffic";
	public final static String DISTANCE="distance";
	public final static String ORDER="order";
	private JButton butStart;
	private JButton butTraffic;
	private JButton butDistance;
	private JButton butOrder;
	private MainInterfaz interfaz;
	
	public Options_Panel(MainInterfaz m){
		setBackground(Color.WHITE);
		interfaz=m;
		setBorder(BorderFactory.createTitledBorder("Options"));
		setLayout(new GridLayout(1,4));
		butStart=new JButton("Start");
		butTraffic=new JButton("Lower traffic");
		butDistance=new JButton("Lower distance");
		butOrder=new JButton("Order");
		
		add(butStart);
		add(butTraffic);
		add(butDistance);
		add(butOrder);
		
		butStart.addActionListener(this);
		butStart.setActionCommand(START);
		butTraffic.addActionListener(this);
		butTraffic.setActionCommand(TRAFFIC);
		butDistance.addActionListener(this);
		butDistance.setActionCommand(DISTANCE);
		butOrder.addActionListener(this);
		butOrder.setActionCommand(ORDER);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		String comando=a.getActionCommand();
		if(comando.equals(START)){
			
		}else if(comando.equals(TRAFFIC)){
			
		}else if(comando.equals(DISTANCE)){
			
		}else if(comando.equals(ORDER)){
		 interfaz.showFrame();
		}
		
	}
}
