package interfaz;

import java.awt.*;
import java.io.*;

import javax.swing.ImageIcon;

public class worker {

	private int x;
	private int y;
	private ImageIcon imagen;
	
	public worker(int posX, int posY){
		
		x = posX;
		y= posY;
		imagen = new ImageIcon("Imagenes/Thanos.png");	
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ImageIcon getImagen() {
		return imagen;
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}
	
	public void mover(int a, int b)
	{
		
		setX(x+a);
		setY(y-b);
	}

}
	
