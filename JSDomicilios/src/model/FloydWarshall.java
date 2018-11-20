package model;

import java.awt.Color;

import javax.swing.JOptionPane;

import interfaz.City;

public class FloydWarshall {

	private int[][] matrizAdjacencia;
	private int[][] matrizSalida;
	
	private  Matrices arboles;
	private int auxAumulado;
	private int subAcomulado;
	private Deliver auxi=null;
	private Deliver nodo[]; 

	private int tope;
	private int permanente;     
	private int nodoFin;
	private int subTope;

	public City p;

	public FloydWarshall(int[][] matriz,int tope,int permanente, int nodoFin, City pr) {
		this.matrizAdjacencia = matriz;
		this.tope = tope;
        this.nodo= new Deliver[tope]; 
        this.permanente = permanente;
        this.nodoFin = nodoFin;
        p = pr;
	}
	
	 public int getAcumulado(){       
		 return nodo[nodoFin].getAcumulado();
	 }

	public void executar() {
		
		for (int i = 0; i < tope; i++){
            nodo[i]= new Deliver("Js_Domic"); 
		}
		
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
                
                int n = matrizAdjacencia.length;		
                this.matrizSalida = new int[n][n];

		
                for (int k = 0; k < n; k++) {			
                	System.out.println("\n--- ITERACAO" + (k+1));			
                	for (int i = 0; i < n; i++) {				
                		for (int j = 0; j < n; j++) {					
                			if (i != j && j != k) {						
                				if (matrizAdjacencia[i][k] > 0 && matrizAdjacencia[k][j] > 0) {	
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
                					if (matrizSalida[i][j] == 0) {								
                						this.matrizSalida[i][j] = 1;
                						if(nodo[i].isEntregado()== true && nodo[i].isEtiqueta()== false){
                			                   if(nodo[i].getAcumulado()<=auxAumulado){
                			                       permanente= nodo[i].getClient();
                			                       auxAumulado= nodo[i].getAcumulado();
                			                   }
                			                }           
                			            subTope++;
                					}													
                				}					
                			}				
                		}			
                	}						
                	imprimir(matrizSalida);		
                }
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

	public void imprimir(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				System.out.print(matriz[i][j] + " ");
			}

			System.out.println();
		}
	}

}
