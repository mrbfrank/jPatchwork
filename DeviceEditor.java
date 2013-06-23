import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;


public class DeviceEditor extends JFrame{   //used to extend JComponent

    static private MyGlassPane myGlassPane;
    
    public DeviceEditor()
    {
        setLocation(300,300);
    }

    public static void addComponentsToPane(Container pane) {

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;

        //c.gridx = 0;
        //c.gridy = 0;
        //pane.add(button, c);
        
        // Device:
        JLabel deviceLabel = new JLabel("Device:", JLabel.CENTER);
        pane.add(deviceLabel, c);
        
        JTextField textField = new JTextField(20);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        pane.add(textField, c);
        
        // Code:
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        JLabel codeLabel = new JLabel("Code:", JLabel.CENTER);
        pane.add(codeLabel, c);
        
        JTextField codeText = new JTextField(20);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        pane.add(codeText, c);
        
        
        // var1:
        JTextField var1 = new JTextField(10);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        pane.add(var1, c);
        
        // Center Panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        
        
        
        //c.ipady = 40;
        c.ipadx = 400;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 9;
        pane.add(panel, c);
        
        c.ipadx = 0;       //reset to default
        c.ipady = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        
        
        // var2:
        JTextField var2 = new JTextField(10);
        c.gridx = 0;
        c.gridy = 3;
        pane.add(var2, c);
        
        // var3:
        JTextField var3 = new JTextField(10);
        c.gridx = 0;
        c.gridy = 4;
        pane.add(var3, c);
        
        // var4:
        JTextField var4 = new JTextField(10);
        c.gridx = 0;
        c.gridy = 5;
        pane.add(var4, c);
        
        // var5:
        JTextField var5 = new JTextField(10);
        c.gridx = 0;
        c.gridy = 6;
        pane.add(var5, c);
        
        // var6:
        JTextField var6 = new JTextField(10);
        c.gridx = 0;
        c.gridy = 7;
        pane.add(var6, c);
        
        // var7:
        JTextField var7 = new JTextField(10);
        c.gridx = 0;
        c.gridy = 8;
        pane.add(var7, c);
        
        // var8:
        JTextField var8 = new JTextField(10);
        c.gridx = 0;
        c.gridy = 9;
        pane.add(var8, c);
        
        // var9:
        JTextField var9 = new JTextField(10);
        c.gridx = 0;
        c.gridy = 10;
        pane.add(var9, c);
        
        // var10:
        JTextField var10 = new JTextField(10);
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 1;
        pane.add(var10, c);         
        
        // var11:
        JTextField var11 = new JTextField(10);
        c.gridx = 3;
        c.gridy = 3;
        pane.add(var11, c);   
               
        // var12:
        JTextField var12 = new JTextField(10);
        c.gridx = 3;
        c.gridy = 4;
        pane.add(var12, c);        
        
        // var13:
        JTextField var13 = new JTextField(10);
        c.gridx = 3;
        c.gridy = 5;
        pane.add(var13, c);
        
        // var14:
        JTextField var14 = new JTextField(10);
        c.gridx = 3;
        c.gridy = 6;
        pane.add(var14, c);
        
        // var15:
        JTextField var15 = new JTextField(10);
        c.gridx = 3;
        c.gridy = 7;
        pane.add(var15, c);
        
        // var16:
        JTextField var16 = new JTextField(10);
        c.gridx = 3;
        c.gridy = 8;
        pane.add(var16, c);
        
        // var17:
        JTextField var17 = new JTextField(10);
        c.gridx = 3;
        c.gridy = 9;
        pane.add(var17, c);
        
        // var18:
        JTextField var18 = new JTextField(10);
        c.gridx = 3;
        c.gridy = 10;
        pane.add(var18, c);
        

        JButton button = new JButton("OK");
        c.gridx = 0;
        c.gridy = 11;
        pane.add(button, c);
        
        button = new JButton("Next");
        c.gridx = 1;
        c.gridy = 11;
        c.weightx = 0.5;
        pane.add(button, c);
        
        button = new JButton("Previous");
        c.gridx = 2;
        c.gridy = 11;
        c.weightx = 0.5;
        pane.add(button, c);
        
        button = new JButton("Cancel");
        c.gridx = 3;
        c.gridy = 11;
        c.weightx = 0;
        pane.add(button, c);

    }

    //Override the JPanel's paintComponent method so we can perform a custom paint job.
    protected void paintComponent(Graphics g) {
       /* if (isOpaque()) { //paint background
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        } */
        //super.paintComponent;
        Ellipse2D e1 = new Ellipse2D.Float(100,100,100,100);
        g.setColor(Color.RED);
        g.drawLine(0,0,500,500);
        //g.draw(e1);
        
        g.fillRect(500, 500, 500, 500);
            
    }
            
       

    public static void createAndShowGUI() {
        //Suggest that the L&F (rather than the system)
        //decorate all windows.  This must be invoked before
        //creating the JFrame.  Native look and feels will
        //ignore this hint.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("Device Editor");
        frame.setLocation(300,300);                                 //experimental location code
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //add our components the content pane.
        addComponentsToPane(frame.getContentPane());
        
        //setup glasspane
        //myGlassPane = new MyGlassPane(frame.getContentPane());
        //changeButton.addItemListener(myGlassPane);
        //frame.setGlassPane(myGlassPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

//experimental class
class MyGlassPane extends JComponent { // implements ItemListener {
    Point point;

    /*React to change button clicks.
    public void itemStateChanged(ItemEvent e) {
        setVisible(e.getStateChange() == ItemEvent.SELECTED);
    } */

    protected void paintComponent(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(20, 20, 2000, 2000);
        
        /*if (point != null) {
            g.setColor(Color.red);
            g.fillOval(point.x - 10, point.y - 10, 20, 20);
        } */
    }

    public void setPoint(Point p) {
        point = p;
    }

    public MyGlassPane(Container contentPane) {
        //CBListener listener = new CBListener(this, contentPane);
        //addMouseListener(listener);
        //addMouseMotionListener(listener);
    }
}

//experimantal class too, we're full of experiments over here
/**
 * Listen for all events that our check box is likely to be
 * interested in.  Redispatch them to the check box.
 */
class CBListener extends MouseInputAdapter {
    Toolkit toolkit;
    Component liveButton;
    JMenuBar menuBar;
    MyGlassPane glassPane;
    Container contentPane;

    public CBListener(
                      MyGlassPane glassPane, Container contentPane) {
        toolkit = Toolkit.getDefaultToolkit();
        this.glassPane = glassPane;
        this.contentPane = contentPane;
    }

    public void mouseMoved(MouseEvent e) {
        redispatchMouseEvent(e, false);
    }

    public void mouseDragged(MouseEvent e) {
        redispatchMouseEvent(e, false);
    }

    public void mouseClicked(MouseEvent e) {
        redispatchMouseEvent(e, false);
    }

    public void mouseEntered(MouseEvent e) {
        redispatchMouseEvent(e, false);
    }

    public void mouseExited(MouseEvent e) {
        redispatchMouseEvent(e, false);
    }

    public void mousePressed(MouseEvent e) {
        redispatchMouseEvent(e, false);
    }

    public void mouseReleased(MouseEvent e) {
        redispatchMouseEvent(e, true);
    }

    //A more finished version of this method would
    //handle mouse-dragged events specially.
    private void redispatchMouseEvent(MouseEvent e,
                                      boolean repaint) {
        Point glassPanePoint = e.getPoint();
        Container container = contentPane;
        Point containerPoint = SwingUtilities.convertPoint(
                                        glassPane,
                                        glassPanePoint,
                                        contentPane);
        if (containerPoint.y < 0) { //we're not in the content pane
            if (containerPoint.y + menuBar.getHeight() >= 0) { 
                //The mouse event is over the menu bar.
                //Could handle specially.
            } else { 
                //The mouse event is over non-system window 
                //decorations, such as the ones provided by
                //the Java look and feel.
                //Could handle specially.
            }
        } else {
            //The mouse event is probably over the content pane.
            //Find out exactly which component it's over.  
            Component component = 
                SwingUtilities.getDeepestComponentAt(
                                        container,
                                        containerPoint.x,
                                        containerPoint.y);
                            
            if ((component != null) 
                && (component.equals(liveButton))) {
                //Forward events over the check box.
                Point componentPoint = SwingUtilities.convertPoint(
                                            glassPane,
                                            glassPanePoint,
                                            component);
                component.dispatchEvent(new MouseEvent(component,
                                                     e.getID(),
                                                     e.getWhen(),
                                                     e.getModifiers(),
                                                     componentPoint.x,
                                                     componentPoint.y,
                                                     e.getClickCount(),
                                                     e.isPopupTrigger()));
            }
        }
        
        //Update the glass pane if requested.
        if (repaint) {
            glassPane.setPoint(glassPanePoint);
            glassPane.repaint();
        }
    }
}

