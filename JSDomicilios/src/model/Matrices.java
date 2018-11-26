/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

public class Matrices {
   private int[][] mCoeficiente ;
   private int [][] mAdyacencia;
   private int [] cordeX ;
   

   private int [] cordeY ;
   private String [] nombre;
   private int [] enArbol;
   
   public Matrices(){
	   mCoeficiente = new int [51][51];
	   mAdyacencia  = new int [51][51];
	   cordeX  = new int [51];
	   cordeY = new int [51];
	   nombre = new String [51];
	   enArbol  = null;
	   
	   setCordeX(0,309);	              					
	   setCordeY(0,223);
	   setNombre(0, "Js_Domic");
    }

	public int getmCoeficiente(int i, int j ) {
        return mCoeficiente[i][j];
    }

    public int getmAdyacencia(int i,int j) {
        return mAdyacencia[i][j];
    }

    public int getCordeX(int i) {
        return cordeX[i];
    }

    public int getCordeY(int i) {
        return cordeY[i];
    }

    public String getNombre(int i) {
        return nombre[i];
    }

    public int getEnArbol(int i) {
        return enArbol[i];
    }

    public void setmCoeficiente(int i,int j ,int mCoeficiente) {
        this.mCoeficiente[i][j] = mCoeficiente;
    }

    public void setmAdyacencia(int i,int j ,int mAdyacencia) {
        this.mAdyacencia[i][j] = mAdyacencia;
    }

    public void setCordeX(int i,int cordeX) {
        this.cordeX[i] = cordeX;
    }

    public void setCordeY(int i, int cordeY) {
        this.cordeY[i] = cordeY;
    }

    public void setNombre(int i,String nombre) {
        this.nombre[i] = nombre;
    }

    public void setEnArbol(int i,int enArbol) {
        this.enArbol[i] = enArbol;
    }
    public void crearEnArbol(int i){
       enArbol = new int [i]; 
    }
    
    public int[][] getmAdyacencia() {
    	return mAdyacencia;
    }

    public void setmAdyacencia(int[][] mAdyacencia) {
    	this.mAdyacencia = mAdyacencia;
    }

	@Override
	public String toString() {
		int contador =0;
		String m ="ID " +"        " +  "Nombre " + "\n";
		while(nombre[contador]!=null){
			m += contador + "    " + nombre[contador] + "\n";
			contador++; 
		}
		return m;
	}  
    
}
