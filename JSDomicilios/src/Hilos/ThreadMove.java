package Hilos;

import model.Deliver;
import model.Graph;
import interfaz.MainInterfaz;

public class ThreadMove implements Runnable{
	
	private MainInterfaz p;
	private Deliver d;
	
	public ThreadMove(MainInterfaz principal, Deliver deliver) {
		p=principal;
		d=deliver;
	}

	@Override
	public void run() {
		while(!d.isEntregado()){
			try{
				
			}
			catch(Exception e){
				
			}
		}
	}

}
