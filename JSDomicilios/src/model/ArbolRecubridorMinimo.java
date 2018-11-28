package model;

public interface ArbolRecubridorMinimo<E,T> {
    public abstract Graph<E,T> obtenerARM(Graph<E,T> G,int inicio);
}
