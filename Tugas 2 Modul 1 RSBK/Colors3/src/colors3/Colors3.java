/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Colors3;

/**
 *
 * @author Rio
 */
import java.awt.*; 
import java.awt.event.*; 
import java.io.*; 
 
public class Colors3 extends Canvas implements Serializable {
     private Color color;   
     private boolean rect;     
     public Colors3()
     {        
         rect=false; 
         setSize(200,100);         
         change();
     }
       public boolean getRect()
       {        
           return rect;
       }     
    public void setRect(boolean flag)
    {            
        this.rect=flag; repaint();
    }     
    public void change()
    {          
        color = randomColor();  
        repaint();
    }     
    private Color randomColor()
    {        
        int r=(int)(255*Math.random());         
        int g =(int)(255*Math.random());          
        int b=(int)(255*Math.random());
        return new Color(r,g,b);
    }     
    public void paint(Graphics g)
    {            
        Dimension d = getSize();         
        int h=d.height;          
        int w=d.width;          
        g.setColor(color);         
        if(rect)
            {             
                g.fillPolygon(new int[]{99,0,0}, new int[]{50,99,1},3);
            }
               
        else{            
                g.fillPolygon(new int[]{99,0,0}, new int[]{50,99,1},3);
            }    
    }
}
