package model;


import interfaz.City;

import java.awt.Color;

import javax.swing.JOptionPane;

/**
 * 
 * @author Jhusseth
 *
 */

public class Algoritmo_Dijkstra {
   private  Matrices arboles;
   private int subTope;
   private Deliver auxi=null;
   private int auxAumulado;
   private int subAcomulado;
   private Deliver nodo[]; 
   private int tope;
   private int permanente;     
   private int nodoFin; 
   
   public City p;
   
    public Algoritmo_Dijkstra (Matrices arboles, int tope,int permanente, int nodoFin, City pr ){
        this.arboles = arboles;        
        this.tope = tope;
        this.nodo= new Deliver[tope]; 
        this.permanente = permanente;
        this.nodoFin = nodoFin;
        p = pr;
        
    }

    public int getAcumulado(){
        return nodo[nodoFin].getAcumulado(); 
    }
        
    public void dijkstra(){ 
         for (int i = 0; i < tope; i++)
                    nodo[i]= new Deliver(); 
         
        if(permanente != nodoFin){
             p.paint(p.getGraphics());
             p.R_repaint(tope, arboles);   
             Pintar.clickSobreNodo(p.getGraphics(), arboles.getCordeX(permanente), arboles.getCordeY(permanente), null,Color.GREEN);
            
        
            nodo[permanente].setEntregado(true);        
            nodo[permanente].setClient(permanente);       
            
            do{            
              subAcomulado=0;
              auxAumulado = 2000000000; 
              nodo[permanente].setEtiqueta(true); 
              for (int j = 0; j < tope; j++) {
                  if(arboles.getmAdyacencia(j, permanente)==1){
                        subAcomulado= nodo[permanente].getAcumulado()+arboles.getmCoeficiente(j, permanente);                                 
                        if(subAcomulado <= nodo[j].getAcumulado() && nodo[j].isEntregado()==true && nodo[j].isEtiqueta()== false){
                            nodo[j].setAcumulado(subAcomulado);
                            nodo[j].setEntregado(true);
                            nodo[j].setClient(j);
                            nodo[j].setPredecesor(nodo[permanente]);
                        }
                        else if( nodo[j].isEntregado()==false){
                            nodo[j].setAcumulado(subAcomulado);
                            nodo[j].setEntregado(true);
                            nodo[j].setClient(j);
                            nodo[j].setPredecesor(nodo[permanente]); 
                       }
                 }
              }
              for (int i = 0; i <tope; i++) {
                if(nodo[i].isEntregado()== true && nodo[i].isEtiqueta()== false){
                   if(nodo[i].getAcumulado()<=auxAumulado){
                       permanente= nodo[i].getClient();
                       auxAumulado= nodo[i].getAcumulado();
                   }
                }               
             }
            subTope++;                
          }
          while(subTope<tope+1);                   
            auxi= nodo[nodoFin];            
            if(auxi.getPredecesor() == null )            
            	JOptionPane.showMessageDialog(null,"No se Pudo LLegar Al Nodo "+nodoFin);                   
            while(auxi.getPredecesor() != null){                        
            	Pintar.pintarCamino(p.getGraphics(), arboles.getCordeX(auxi.getClient()), arboles.getCordeY(auxi.getClient()), arboles.getCordeX(auxi.getPredecesor().getClient()), arboles.getCordeY(auxi.getPredecesor().getClient()),Color.GREEN);             
            	Pintar.clickSobreNodo(p.getGraphics(), arboles.getCordeX(auxi.getClient()), arboles.getCordeY(auxi.getClient()), null,Color.GREEN);             
            	auxi=auxi.getPredecesor();                       
            }          
            Pintar.clickSobreNodo(p.getGraphics(), arboles.getCordeX(nodoFin), arboles.getCordeY(nodoFin), null,Color.GREEN);           
        }     
        else Pintar.clickSobreNodo(p.getGraphics(), arboles.getCordeX(nodoFin), arboles.getCordeY(nodoFin), null,Color.GREEN);    
    }
    
    
 
}
