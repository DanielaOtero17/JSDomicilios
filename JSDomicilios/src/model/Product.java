package model;

public class Product {
	
	private String name;
	private double value;
	
	public Product(String nombre, double valor) {
		this.name = nombre;
		this.value = valor;
	}
	
	@Override
	public String toString() {
		return name;
	}
	public String getNombre() {
		return name;
	}
	public void setNombre(String nombre) {
		this.name = nombre;
	}
	public double getValor() {
		return value;
	}
	public void setValor(double valor) {
		this.value = valor;
	}
}
