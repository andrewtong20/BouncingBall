/*
This class installs the menu for the bouncing ball program by passing in the paintComponent and Random Number Timer.
 */
package assignment3;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class menu extends JFrame
{
    private paintComponent component;//pass in from main
    private Timer colorGenerator;//pass in from main
    public static double changeSpeedDistance=1;
    
    public menu(paintComponent inComponent, Timer inTimerListener)
    {
        component=inComponent;
        colorGenerator=inTimerListener;
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        //menu
        JMenu createFileMenu= new JMenu("File");
        JMenu createEditMenu=new JMenu("Edit");
        JMenu createHelpMenu=new JMenu("Help");
        
        menuBar.add(createFileMenu);
        menuBar.add(createEditMenu);
        menuBar.add(createHelpMenu);
        
        //File submenu
        JMenuItem exitItem = new JMenuItem("Exit");
        createFileMenu.add(exitItem);
        //Edit submenus
        JMenuItem createRandomItem= new JMenuItem("Random");
        createEditMenu.add(createRandomItem);
        JMenuItem createRedItem= new JMenuItem("Red");
        createEditMenu.add(createRedItem);
        JMenuItem createGreenItem= new JMenuItem("Green");
        createEditMenu.add(createGreenItem);
        JMenuItem createBlueItem= new JMenuItem("Blue");
        createEditMenu.add(createBlueItem);
        JMenuItem createFastItem= new JMenuItem("Speed Up");
        createEditMenu.add(createFastItem);
        JMenuItem createSlowItem= new JMenuItem("Speed Down");
        createEditMenu.add(createSlowItem);
        //Help submenus       
        JMenuItem createAboutItem=new JMenuItem("About");
        createHelpMenu.add(createAboutItem);
        JMenuItem createHelpItem=new JMenuItem("Help");
        createHelpMenu.add(createHelpItem);
     
    class ExitItemListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            System.exit(0);
        }
    }
    ActionListener exitListener=new ExitItemListener();
    exitItem.addActionListener(exitListener);
        
       
    class randomGeneratorButton implements ActionListener//these classes are the same with the buttons from the main
    {
        public void actionPerformed(ActionEvent event)
        {
            colorGenerator.start();
        }
        
    }
    ActionListener randomListener=new randomGeneratorButton();
    createRandomItem.addActionListener(randomListener);


    class RedListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            colorGenerator.stop();
            component.setFill(Color.RED);
        }

    }
    ActionListener redListener=new RedListener();
    createRedItem.addActionListener(redListener);

    class BlueListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            colorGenerator.stop();
            component.setFill(Color.BLUE);
        }
    }
    ActionListener blueListener=new BlueListener();
    createBlueItem.addActionListener(blueListener);

    class GreenListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            colorGenerator.stop();
            component.setFill(Color.GREEN);
        }
    }
    ActionListener greenListener=new GreenListener();
    createGreenItem.addActionListener(greenListener);
    
    class FastListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            Assignment3.speedUp();//called from Assignment 3 so that the increase in speed is accounted for the same in the main as well
            
        }
    }
    ActionListener fastListener=new FastListener();
    createFastItem.addActionListener(fastListener);

    class SlowListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            Assignment3.speedDown();//called from Assignment 3 so that the decrease in speed is accounted for the same in the main as well
            
        }
    }
    ActionListener slowListener=new SlowListener();
    createSlowItem.addActionListener(slowListener);
    
    class AboutItemListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {          
                JOptionPane.showMessageDialog(null, "Andrew Tong \nAs3 \nMay 8, 2019 \nMr. Ritter"); //as per instructions
            }
        }
    ActionListener aboutListener=new AboutItemListener();
    createAboutItem.addActionListener(aboutListener);
    
    class HelpItemListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)//directions as per instructions
            {
                JOptionPane.showMessageDialog(null, "This program creates a bouncing ball. To draw, PRESS the mouse on the screen to determine the center of the circle. \nContinue holding and drag your mouse to a new location and RELEASE, drawing the radius. Be aware that you need to keep the circle in the frame for the circle to bounce. \nTo change the color of the circle, click any of the buttons or the menu under edit. To change the speed of the circle, click the corresponding buttons or under the edit menu. \nFor help, look under the help menu. To exit, click File-> exit or the red x. ");
            }
        }
    ActionListener helpListener=new HelpItemListener();
    createHelpItem.addActionListener(helpListener);
    
    
    } 
    
     
}
