package model;

public class Auxiliar <E> {
	
	private Domicilie<E> head,tail;
	private int size;
	
	public Auxiliar(){
		size=0;
		head=null;
		tail =null;
	}
	
	public Domicilie addDomicilie(E data){
		Domicilie<E> node = new Domicilie<E>(data);
		
		if(size==0){
			head=node;
		}
		else{
			tail.setNext(node);
			node.setPrevious(tail);
		}
		tail =node;
		size++;
		return node;
	}
	
	public Domicilie addFirst(E data){
		Domicilie<E> node = new Domicilie<E>(data);
		
		if(size > 0)
			head.setPrevious(node);
			
		node.setNext(head);
		head=node;
		size++;
		return node;
	}
	
	public int getSize(){
		return size;
	}

	public Domicilie<E> getHead() {
		return head;
	}

	public void setHead(Domicilie<E> head) {
		this.head = head;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public String toString(){
		String output = "[";
		Domicilie<E> tmp = head;
		
		while(tmp != null){
			output += tmp.toString();
			if(tmp.getNext() != null)
				output += ", ";
			tmp = tmp.getNext();
		}
		output += "]";
		return output;
	}
	

	public Iterator<E> iterator(){
		

		return new Iterator<E>() {
			private Domicilie<E> position = head;
			

			public E next(){
				Domicilie<E> node = position;
				position = position.getNext();
				return node.getData();
			}
			
			public boolean hasNext(){
				return position != null;
			}
			
			public Iterator<E> concatenate(Iterator<E> secondIter){
				Auxiliar<E> newList = new Auxiliar<E>();
				while(this.hasNext())
					newList.addDomicilie(this.next());
				while(secondIter.hasNext())
					newList.addDomicilie(secondIter.next());
				return newList.iterator();
			}
			
			public int size(){
				return Auxiliar.this.getSize();
			}
			
			public String toString(){
				return Auxiliar.this.toString();
			}
		};
	}

}
