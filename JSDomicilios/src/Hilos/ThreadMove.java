package Hilos;

import model.Deliver;
import model.Graph;
import interfaz.*;

public class ThreadMove extends Thread{
	
	private MainInterfaz p;
	private Deliver d;
	private worker thanos;
	
	
	public ThreadMove(MainInterfaz principal, Deliver deliver) {
		p=principal;
		d=deliver;
		thanos = p.getThanos();
	}

	public void run() {
		
		int posIniX=thanos.getX();
		int posIniY = thanos.getY();
		
		
		while(thanos.getX()!=d.getPosX() || thanos.getY()!=d.getPosY()){
	
				if(thanos.getX() > d.getPosX()){
				thanos.setX(thanos.getX()-1);
				}
				if(thanos.getX() < d.getPosX()){
					thanos.setX(thanos.getX()+1);
					}
				if(thanos.getY() > d.getPosY()){
				thanos.setY(thanos.getY()-1);
				}
				if(thanos.getY() < d.getPosY()){
					thanos.setY(thanos.getY()+1);
					}
			
			try {
				
				Thread.sleep(30);
			}
			catch (InterruptedException e)
			{
				
			}
			p.getPanelGrafico().repaint();
			p.repaint();
		}
		
		while(thanos.getX()!= posIniX || thanos.getY() != posIniY){
		if(thanos.getX() > posIniX ){
			thanos.setX(thanos.getX()-1);
			}
			if(thanos.getX() < posIniX){
				thanos.setX(thanos.getX()+1);
				}
			if(thanos.getY() > posIniY){
			thanos.setY(thanos.getY()-1);
			}
			if(thanos.getY() < posIniY){
				thanos.setY(thanos.getY()+1);
				}
			try {
				
				Thread.sleep(50);
			}
			catch (InterruptedException e)
			{
				
			}
			p.getPanelGrafico().repaint();
			p.repaint();
			
	}
	}
}
