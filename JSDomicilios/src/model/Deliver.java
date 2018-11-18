package model;

import java.util.ArrayList;

public class Deliver {
	
	private int client;
	private String name;
	private ArrayList<Product> products;
	private boolean entregado;
	private int acumulado;
	private Deliver Predecesor;
	private boolean etiqueta;

	public Deliver() {
		this.client = -1;
		entregado =false;
		this.etiqueta = false;
	    this.Predecesor  = null;
	    this.acumulado =0; 
	    this.products=null;
	    this.name=null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public int  getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}

	public boolean isEntregado() {
		return entregado;
	}

	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}
	
	public boolean isEtiqueta() {
        return etiqueta;
    }

    public int getAcumulado() {
        return acumulado;
    }

    public Deliver getPredecesor() {
        return Predecesor;
    }

	@Override
	public String toString() {
		return  name ;
	}
	
	public void setEtiqueta(boolean etiqueta) {
        this.etiqueta = etiqueta;
    }

    public void setAcumulado(int acomulado) {
        this.acumulado = acomulado;
    }

    public void setPredecesor(Deliver Predecesor) {
        this.Predecesor = Predecesor;
    }
}
