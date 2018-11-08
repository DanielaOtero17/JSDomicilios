package model;

public interface Iterator<E> {
	public E next();
	public boolean hasNext();
	public Iterator<E> concatenate(Iterator<E> secondIter);
	public int size();
}
