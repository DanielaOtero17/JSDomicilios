
package model;
import interfaz.City;

import java.awt.Color;
/**
 * 
 * @author Jhusseth
 *
 */

public class Algoritmo_Prim {
    
    private int cumulado;
   private int aristaMenor;
   private int  fin;
   private boolean estaNodo=false;
   private boolean aumentaTamano;
   private int nodoApuntado;  
   private int nodoApuntador;
   private int tamano;
   private int arsitaMayor;
   private  Matrices Matrices;
   private int tope;
   private  int  nodoOrigen;
   
   public City p;
   
   
   public Algoritmo_Prim(Matrices arbol ,int origen, int top ,int aristaMayor,City pr ){
       this.cumulado = 0;
       this.aristaMenor = 0;
       this.fin = 0;
       this.estaNodo=false;
       this.aumentaTamano = false;
       this.nodoApuntado = 0;  
       this.nodoApuntador = 0;
       this.tamano = 1;
       this. arsitaMayor=aristaMayor;
       this.Matrices = arbol;
       this.tope = top;
       p = pr;
       this.nodoOrigen=origen;
   }

    public int getCumulado() {
        return cumulado;
    }
  
   
  public void prim(){
	  
       p.paint(p.getGraphics());
       p.R_repaint(tope,Matrices);
       Matrices.crearEnArbol(tope);
       Matrices.setEnArbol(0, nodoOrigen);
       
       do{
           this.aristaMenor = this.arsitaMayor;
           this.fin=2;
            for (int j = 0; j < tamano; j++) {
                for (int k = 0; k < tope; k++){
                    if(Matrices.getmAdyacencia(k, Matrices.getEnArbol(j))==1){
                        for (int h = 0; h < tamano; h++) {
                             if(Matrices.getEnArbol(h)==k ){
                                 this.estaNodo=true; 
                                 break;
                             }
                        }
                        if(estaNodo==false){
                            if(Matrices.getmCoeficiente(k, Matrices.getEnArbol(j))<=aristaMenor && Matrices.getmCoeficiente(k, Matrices.getEnArbol(j))>0 ){
                                 aristaMenor=Matrices.getmCoeficiente(k, Matrices.getEnArbol(j));       
                                 this.nodoApuntado=k;
                                 this.aumentaTamano=true;
                                 this.nodoApuntador=Matrices.getEnArbol(j);
                                 this.fin=1;
                            }
                        }
                        this.estaNodo=false;
                    }
                }
            }//fin  for (int j = 0; j < tamano; j++)              
         if(aumentaTamano==true){
                    Pintar.pintarCamino(p.getGraphics(),Matrices.getCordeX(nodoApuntador), Matrices.getCordeY(nodoApuntador),Matrices.getCordeX(nodoApuntado), Matrices.getCordeY(nodoApuntado),Color.red); 
                    Pintar.clickSobreNodo(p.getGraphics(),Matrices.getCordeX(nodoApuntador), Matrices.getCordeY(nodoApuntador), null,Color. red);
                    Pintar.clickSobreNodo(p.getGraphics(),Matrices.getCordeX(nodoApuntado), Matrices.getCordeY(nodoApuntado), null, Color.red);                                  
                    Matrices.setEnArbol(tamano, nodoApuntado);
                    this.tamano++;
                    this.aumentaTamano=false;
                    this.cumulado += this.aristaMenor;
         }
        }while(fin<2);
   }
    
}
