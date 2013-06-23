//import java.awt.*;
import java.awt.event.*;
//import javax.swing.*;
import javax.swing.JInternalFrame;
class PatchFrame extends JInternalFrame {
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;

    public PatchFrame() {
        super("Document #" + (++openFrameCount), 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable

        //...Create the GUI and put it in the window...
        
        // Add mouse click listener
        MouseClickListener listener = new MouseClickListener();
        addMouseListener(listener);

        //...Then set the window size or call pack...
        setSize(500,500);

        //Set the window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    }
    

 
    
private class MouseClickListener extends MouseAdapter
    {
    int xPos1;
    int yPos1;
        public void mouseClicked(MouseEvent event)
        {
            xPos1 = event.getX();
            yPos1 = event.getY();
            System.out.println("xPos1="+xPos1+"   yPos1="+yPos1);
            /*if(counter == 0)
            {
                egg.setFrame(xPos[counter] - EGG_WIDTH / 2, yPos[counter] - EGG_HEIGHT / 2,  EGG_WIDTH, EGG_HEIGHT);
            }*/
            repaint();
         }
         public String getMousePosition(MouseEvent event)
         {
             String mouseEcho = ("mouseEcho: xPos1="+xPos1+"   yPos1="+yPos1);
             System.out.println(mouseEcho);
             return mouseEcho;
         }
    }
    
}