package PatchCable;

import java.applet.*;
import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;
 
public class PatchCable extends Applet implements MouseListener, MouseMotionListener {
    int x0, y0, x1, y1; 
    //public void init() {
	    public PatchCable() {
	    addMouseListener(this); 
	    addMouseMotionListener(this);
    }
    public void mouseDragged(MouseEvent e)
    {
 
	    x1 = e.getX();
	    y1 = e.getY();
	    repaint();
    }
    public void mouseMoved(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited (MouseEvent e) { }
    public void mousePressed(MouseEvent e) {
	x0 = e.getX();
	y0 = e.getY(); 
	System.out.println("Mouse pressed at: (" + 
			   x0 + ", " + y0 + ")" ); 
    }
    public void mouseReleased(MouseEvent e) {
	x1 = e.getX(); 
	y1 = e.getY(); 
	System.out.println("Mouse released at: (" +
			   x1 + ", " + y1 + ")" ); 
	this.repaint(); 
    }
   public void paint(Graphics g) {
	   g.setColor(Color.BLACK);
	g.drawLine(x0, y0, x1, y1); 
    }
    
    public static void main(String[] argv)
    {
	    JFrame f = new JFrame("Test");
	    f.getContentPane().add(new PatchCable());
	    f.setVisible(true);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}