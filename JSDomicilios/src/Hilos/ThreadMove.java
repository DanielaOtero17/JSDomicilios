package Hilos;

import model.Graph;
import interfaz.MainInterfaz;

public class ThreadMove implements Runnable{
	
	private MainInterfaz p;
	private Graph g;
	
	public ThreadMove(MainInterfaz principal, Graph grafo) {
		p=principal;
		g=grafo;
	}

	@Override
	public void run() {
		while(true){
			try{
				
			}
			catch(Exception e){
				
			}
		}
	}

}
