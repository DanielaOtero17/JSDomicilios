/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;

public class Pintar {   
    Color color;
     
   
    public Pintar(){          
    }
    
   public static void pintarCasita(Graphics g,int x,int y,String n){
	   ImageIcon fondo = new ImageIcon("data/casita.png");
		g.drawImage(fondo.getImage(), x, y, 17, 17, null);
		 
	    ((Graphics2D)g).setColor(Color.RED);
	    Font fuente=new Font("Monospaced",Font.BOLD, 16);
	    g.setFont(fuente);
	    ((Graphics2D)g).drawString(n, x, y); 
    }    
  
  public static void pintarLinea(Graphics g, int x1,int y1,int x2,int y2,int tam){
        int xAux = 0; int yAux = 0; 
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke stroke = new BasicStroke(3);
        ((Graphics2D)g).setStroke(stroke);  
        g.setColor(Color.BLACK);
       ((Graphics2D)g).drawLine(x1+10, y1+10, x2+10, y2+10);
       if(x1<=x2)
           xAux=((x2-x1)/2)+x1;       
        if(x1>x2)
           xAux=((x1-x2)/2)+x2;
        if(y1<y2)
           yAux=((y2-y1)/2)+y1;
        if(y1>=y2)
            yAux=((y1-y2)/2)+y2;        
        
        g.setColor(Color.CYAN);
        Font fuente=new Font("Monospaced",Font.BOLD, 15);
        g.setFont(fuente);
      ((Graphics2D)g).drawString(String.valueOf(tam), xAux, yAux);
  }   
  public static void pintarCamino(Graphics g, int x1,int y1,int x2,int y2, Color color){
      ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke stroke = new BasicStroke(3);
        ((Graphics2D)g).setStroke(stroke);
        g.setColor(color);
        g.drawLine(x1+10, y1+10, x2+10, y2+10);
  }
   public static void clickSobreNodo(Graphics g,int x,int y,String n,Color co){
	   
       ((Graphics2D)g).setColor(co);
        ((Graphics2D)g).setStroke(new BasicStroke(4));   
        ((Graphics2D)g).fillOval(x, y, 17, 17);        
        ((Graphics2D)g).setColor(Color.BLACK);
        ((Graphics2D)g).drawOval(x, y, 17, 17);
         
    }
}
