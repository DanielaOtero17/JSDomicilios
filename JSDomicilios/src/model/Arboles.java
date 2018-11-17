/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Arrays;

/**
 *
 * @author fredy_000
 */
public class Arboles {
   private int[][] mCoeficiente ;
   private int [][] mAdyacencia;
   private int [] cordeX ;
   

   private int [] cordeY ;
   private String [] nombre;
   private int [] enArbol;
   
   public Arboles(){
	   mCoeficiente = new int [51][51];
	   mAdyacencia  = new int [51][51];
	   cordeX  = new int [51];
	   cordeY = new int [51];
	   nombre = new String [51];
	   enArbol  = null;
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

	@Override
	public String toString() {
		return nombre +  ", ";
	}  
    
}
