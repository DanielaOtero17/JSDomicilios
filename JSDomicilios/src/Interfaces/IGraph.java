package Interfaces;

public interface IGraph <N>{
	
                                                                             
	public void crearGrafo ();
	public void agregarArista(N nodo1,N nodo2);
	public void agregarNodo( N nuevoNodo);
	public Object eliminarNodo( N nodo);
	public Object eliminarArista( N nodo1, N nodo2);
	public boolean isEmpty();
	public boolean contain();
	public boolean adyacentes(N nodo1, N nodo2);
	public boolean predecesor( N nodo1,N nodo2);
	public boolean sucesor(N nodo1, N nodo2);
	
}
