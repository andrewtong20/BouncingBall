/*
Andrew Tong
Mr. Ritter
May 8, 2019
As3

This program allows the user to create a bouncing ball. It allows the user to change/randomize the color through menus and buttons.
It allows the user to set a center and the radius of the ball through a mouse press/release.
It provides help/about information through a menu.

To do these tasks the program has constructors to make a circle. It has mouse listeners for the center/radius of the circle.
It has methods to change the color/speed of the circle and calls it through buttons/menus.

 */  
package assignment3;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.JOptionPane;

public class Assignment3 {

    public static double dx=1;//dx and dy switch between positive and negative for the direction
    public static double dy=1;
    public static double width;
    public static double height;
    public static double changeSpeedDistance=1;//this is a distance (with direction determined by dx/dy) to be added onto x or y
    private static final int FRAME_WIDTH=1200;
    private static final int FRAME_HEIGHT=1000;
    
    public static void main(String[] args) 
    {
        
        paintComponent component=new paintComponent(0,0,0,Color.BLACK, Color.GRAY,1);
        
        class randomListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                Random random=new Random();

                int r=random.nextInt(256);//ints only option
                int g=random.nextInt(256);
                int b=random.nextInt(256);

                Color randomColor = new Color(r, g, b);
                component.setFill(randomColor);

            }

        }
        
        ActionListener colorGeneratorObject = new randomListener();
        final int colorDelay = 250; // slow to not stress the eyes
        Timer colorGenerator = new Timer(colorDelay, colorGeneratorObject);
    
        JFrame frame= new menu(component,colorGenerator);//passes the menu information into the main
        
        frame.setSize(1000, 1000);
        frame.setTitle("Bouncing Ball");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout (new BorderLayout());
        
        JPanel buttonPanel = new JPanel();
        JButton buttonRandom = new JButton ("Random");
        JButton buttonRed = new JButton ("Red");
        JButton buttonGreen = new JButton ("Green");
        JButton buttonBlue = new JButton ("Blue");  
        JButton buttonFast = new JButton ("Speed Up");
        JButton buttonSlow = new JButton ("Speed Down");
        
        buttonPanel.add(buttonRandom);
        buttonPanel.add(buttonRed);
        buttonPanel.add(buttonGreen);
        buttonPanel.add(buttonBlue);
        buttonPanel.add(buttonFast);
        buttonPanel.add(buttonSlow);

        frame.add(buttonPanel, BorderLayout.SOUTH); 

        class TimerListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                
                width = frame.getContentPane().getWidth();//width and height is dynamic meaning that it user can change and ball will still bounce
                height = frame.getContentPane().getHeight();
                
                component.setX(component.findX()+changeSpeedDistance*dx);
                component.setY(component.findY()+changeSpeedDistance*dy);
                
                if ((component.findX() + component.getDiameter())>=width)//these change the direction of the changeSpeedDistance if hits the wall
                {
                    dx*=-1;
                }
                
                if ((component.findY()+component.getDiameter())>=(height-45))//bounds check to account for south buttons
                {
                    dy*=-1;
                }
                
                if (component.findX()<=0)
                {
                    dx*=-1;
                }
                
                if (component.findY()<=0)
                {
                    dy*=-1;
                }
           
            }
        }

        ActionListener listenerMovement = new TimerListener();

        final int moveDelay = 25; //small delay for best movement quality
        Timer move = new Timer(moveDelay, listenerMovement);
        
        
        class buttonFastListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                if (changeSpeedDistance>32)//bounds check so the circle can still be visibly bouncing without glitching
                {
                    JOptionPane.showMessageDialog(null, "You are trying to go too fast. The circle will not move any faster.");
                    changeSpeedDistance=32;//keeps the circle at one speed instead of going higher to prevent glitching
                }

                else
                {
                changeSpeedDistance*=2;//continuous doubling of speed to make speedUp noticeably faster but the increase isn't too fast so that it glitches out too fast
                }
            }
        }
        
        ActionListener listenerFast = new buttonFastListener();
        buttonFast.addActionListener(listenerFast);
        
        class buttonSlowListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {

                if (changeSpeedDistance<.1)//This bounds check is to prevent the circle from seeming to stop
                {
                    JOptionPane.showMessageDialog(null,"You are going too slow. The circle will not go any slower.");
                    changeSpeedDistance=.1;

                }
                else
                {
                changeSpeedDistance*=.5;//everytime the circle's speed is halved. this also means that the circle will NEVER reach 0 speed or negative speed.
                }
            }
        }
        
        ActionListener listenerSlow=new buttonSlowListener();
        buttonSlow.addActionListener(listenerSlow);
        
        class MousePressListener implements MouseListener
        {
            //instance fields
            
            private double x1,y1,x2,y2;
            
            public void mousePressed(MouseEvent event)
            {
                x1=event.getX();
                y1=event.getY();
                component.setX(x1);
                component.setY(y1);
                
                
                move.stop();//makes sure that ball doesn't move during the press stage
                component.setPaintComponent(0.0,0.0,0.0,0.0);
           
            }
            
            public void mouseReleased(MouseEvent event)
            {
                x2=event.getX();
                y2=event.getY();
                component.setX(x2);
                component.setY(y2);
                double radius=Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));//distance formula
                width=frame.getContentPane().getWidth();
                height=frame.getContentPane().getHeight();
               
                //bound check to fit into frame as per instructions
                if ((x2+2*radius)>=width)//Because the circle location is at the top left corner, adding 2*radius accounts for this
                {
                    move.stop();
                    component.setPaintComponent(0.0,0.0,0.0,0.0);//remove circle from window if not in bounds for aesthetic pleasure
                    JOptionPane.showMessageDialog(null, "Your circle is out of bounds. Draw another circle.");

                }
                
                else if (x2<=0)//each wall is checked
                {
                    move.stop();
                    component.setPaintComponent(0.0,0.0,0.0,0.0);
                    JOptionPane.showMessageDialog(null, "Your circle is out of bounds. Draw another circle.");
                    
                }
                
                else if ((y2+2*radius)>=(height-45))//account for panel of 45 pixels
                {
                    move.stop();
                    component.setPaintComponent(0.0,0.0,0.0,0.0);
                    JOptionPane.showMessageDialog(null, "Your circle is out of bounds. Draw another circle.");
                    
                }
                
                else if (y2<=0)
                {
                    move.stop();
                    component.setPaintComponent(0.0,0.0,0.0,0.0);
                    JOptionPane.showMessageDialog(null, "Your circle is out of bounds. Draw another circle.");
                    
                }
                else
                {
                    component.setPaintComponent(x1, y1, radius,changeSpeedDistance);
                    move.start();//if bounds check pass, circle is painted and the timer starts
                   
                }
                
    
            }
            
            
            public void mouseClicked(MouseEvent event) {}
            public void mouseEntered(MouseEvent event) {}
            public void mouseExited(MouseEvent event) {}
        }
        
        MouseListener centerListener=new MousePressListener();
        component.addMouseListener(centerListener);
 
    //Buttons
    class randomGeneratorButton implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            colorGenerator.start();
        }
    }


    class buttonRedListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            colorGenerator.stop();//red, blue, green have to override random color generator
            component.setFill(Color.RED);
        }
    }

    class buttonBlueListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            colorGenerator.stop();
            component.setFill(Color.BLUE);
        }
    }

    class buttonGreenListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            colorGenerator.stop();
            component.setFill(Color.GREEN);
        }
    }
    
    ActionListener listenerRandom=new randomGeneratorButton();
    buttonRandom.addActionListener(listenerRandom);


    ActionListener listenerRed = new buttonRedListener();
    buttonRed.addActionListener(listenerRed);


    ActionListener listenerBlue = new buttonBlueListener();
    buttonBlue.addActionListener(listenerBlue);


    ActionListener listenerGreen = new buttonGreenListener();
    buttonGreen.addActionListener(listenerGreen);
    
    frame.add(component);
    frame.setVisible(true);
    
    
    
    }
    
    public static void speedUp()//methods to be used in the menu, outside main to to be accessed from different file
    {
        if (changeSpeedDistance>32)//bounds check so the circle can still be visibly bouncing without glitching
        {
            JOptionPane.showMessageDialog(null, "You are trying to go too fast. The circle will not move any faster.");
            changeSpeedDistance=32;//keeps the circle at one speed instead of going higher to prevent glitching
        }

        else
        {
        changeSpeedDistance*=2;//continuous doubling of speed to make speedUp noticeably faster but the increase isn't too fast so that it glitches out too fast
        }
        
    }
    
    public static void speedDown()
    {

        if (changeSpeedDistance<.1)//This bounds check is to prevent the circle from seeming to stop
        {
            JOptionPane.showMessageDialog(null,"You are going too slow. The circle will not go any slower.");
            changeSpeedDistance=.1;

        }
        else
        {
        changeSpeedDistance*=.5;//everytime the circle's speed is halved. this also means that the circle will NEVER reach 0 speed or negative speed.
        }
    }
    
    
    
}

