package model;

public class Deliver {
	
	private String client;
	private int posX;
	private int posY;
	private Product[] products;
	private boolean entregado;
	private int direccion;
	
	public static final int IZQ =0;
	public static final int DER =1;
	public static final int ABAJO =2;
	public static final int ARRIBA =3;

	public Deliver(String client,int posX, int posY, Product[] products) {
		this.client = client;
		this.posX=posX;
		this.posY=posY;
		this.products = products;
		entregado =false;
		direccion =0;
	}

	public Product[] getProducts() {
		return products;
	}

	public void setProducts(Product[] products) {
		this.products = products;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public boolean isEntregado() {
		return entregado;
	}

	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}

	@Override
	public String toString() {
		return  client;
	}
	
	public void mover(){
		
	}
	
	

}
