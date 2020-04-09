/*
This class creates the circle. It also has methods (parameter constructor, mutator, accessor) that allow the main to change certain aspects of the circle.
Finally, it paints/repaints the circle onto the screen.
 */
package assignment3; 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
 
public class paintComponent extends JComponent
{
     //instance fields
    private Color fillColor;
    private final Color borderColor;//border color never changes, so final
    private double x, y, radius, diameter;
    private double circle;
    private int choice;
    private double height, width, changeSpeedDistance;
    
    
    //parameter constructor
    public paintComponent(double inX, double inY, double inRadius, Color inBorder, Color inFill, double inSpeed)
    {
        x=inX;
        y=inY;
        radius=inRadius;
        borderColor=inBorder;
        fillColor=inFill;
        changeSpeedDistance=inSpeed;
    }
    
    //mutators
    public void setPaintComponent(double inX,double inY, double inRadius, double inSpeed)
    {
        
        x=inX-inRadius;//adjusts that the mouse pressed will be at center instead of the top left corner
        y=inY-inRadius;//I chose to do this in the class instead of the main because  I can make multiple circles and all of their centers will be adjusted
        radius=inRadius;//this is the most general option
        changeSpeedDistance=inSpeed;
    
    }
    
    public void setFill (Color inFillColor)
    {
        fillColor=inFillColor;
        repaint();
    }
    
    public void setX (double dx)
    {
   
        x = dx;
        repaint();
         
    }
    
    public void setY (double dy)
    {
        y = dy; 
        repaint();
    }
    
   
    public void setRadius(double inRadius)
    {
        radius=inRadius;
    }
    
    //accessors
    
   
    public double findX()//JComponent already has getX so I chose findX instead. Still an accessor
    {
        return x;
    }
    
    public double findY()
    {
        return y;
    }
    
    public double getRadius()
    {
        return radius;
    }
    public double getDiameter()
    {
        diameter=2*radius;
        return diameter;
    }
   

    public void paintComponent(Graphics g)//necessary for paint
    {
        
        Graphics2D g2 = (Graphics2D)g;
        
        
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 2*radius, 2*radius);
        g2.setColor(borderColor);
        g2.draw(circle);
        g2.setColor(fillColor);
        g2.fill(circle);
        
        
    }
}
