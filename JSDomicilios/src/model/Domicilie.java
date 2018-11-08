package model;

public class Domicilie<E> {

	private Domicilie<E> next, previous;
	private E data;
	
	
	public Domicilie(E data, Domicilie<E> previous,Domicilie<E> next ){
		this.data=data;
		this.next=next;
		this.previous=previous;
	}
	
	public Domicilie (E data){
		this (data,null,null);
	}
	
	public boolean hasNext(){
		return next!=null;
	}
	
	public boolean hasPrrevious(){
		return previous!=null;
	}
	
	public E getData(){
		return data;
	}
	
	public void setData(E data) {
		this.data = data;
	}

	public Domicilie<E> getNext() {
		return next;
	}

	public void setNext(Domicilie<E> next) {
		this.next = next;
	}

	public Domicilie<E> getPrevious() {
		return previous;
	}

	public void setPrevious(Domicilie<E> previous) {
		this.previous = previous;
	}
	
	public void delete(){
		data = null;
		next = null;
		previous = null;
	}
	
	public String toString(){
		return String.format("%s", data.toString());
	}
	
}
