import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.JInternalFrame;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.*;
import java.io.*;

public class jPatchwork extends JFrame implements ActionListener, ItemListener
{
    JPanel contentPane;
    JTextArea output;
    JScrollPane scrollPane;
    JDesktopPane desktop;
    String newline = "\n";
    int LEFT;
    int hGap = 1;
    int vGap = 1;
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    JFileChooser fc = new JFileChooser();

    public jPatchwork()
    {
        super("jPatchWork");

        //indent the application boarder 50 pixels from each edge
        //of the screen.
        int inset = 50;
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                  screenSize.width  - inset*2,
                  screenSize.height - inset*2);

        //Set up the GUI.
        setJMenuBar(createMenuBar());
        setContentPane(createContentPane());

        //Make dragging a little faster but perhaps uglier.
        //desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }
    
    public Container createContentPane() {
        
        
        //Create the content-pane-to-be.
        contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        //Create a scrolled text area.  <--- this is for debugging purposes
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);
        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.SOUTH);
        
        //Add menu bar of buttons.
        contentPane.add(createButtonBar(), BorderLayout.NORTH);

        desktop = new JDesktopPane(); //a specialized layered pane
        createPatchFrame(); //create first "window"
        
        //contentPane.add(desktop, BorderLayout.CENTER);    // code to use for windowed Devices display ---- uncomment this line to use a window for Devices display.
        
        // SplitPane Devices menu starts here ---- uncomment this section to use a SplitPane for the Devices display.
        //Create a split pane with the two scroll panes in it.
        JPanel devices = new JPanel();
        devices.setBackground(Color.white);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, desktop, devices);
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(0.8);
        //splitPane.setDividerLocation(screenSize.width - 300);
        contentPane.add(splitPane, BorderLayout.CENTER);
        // SplitPane Devices menu ends here

        return contentPane;
    }

    protected JMenuBar createMenuBar()	//need to DRY this up
    {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        //Create the menu-bar-to-be.
        menuBar = new JMenuBar();

        //Build the File menu.
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription("This is the File menu");
        menuBar.add(menu);

        //a group of JMenuItems
        //New
        menuItem = new JMenuItem("New",KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
        menuItem.setActionCommand("New");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Open...
        menuItem = new JMenuItem("Open...",KeyEvent.VK_O);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menu.addSeparator();
        
        //Save
        menuItem = new JMenuItem("Save",KeyEvent.VK_S);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Save As...
        menuItem = new JMenuItem("Save As...",KeyEvent.VK_A);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menu.addSeparator();
        
        //Exit
        menuItem = new JMenuItem("Exit",KeyEvent.VK_E);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //About jPatchwork...
        menuItem = new JMenuItem("About jPatchwork...",KeyEvent.VK_P);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Build the Edit menu.
        menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_E);
        menu.getAccessibleContext().setAccessibleDescription("This is the Edit menu.");
        menuBar.add(menu);
        
        //a group of JMenuItems
        //Wire Mode
        cbMenuItem = new JCheckBoxMenuItem("Wire Mode");
        cbMenuItem.setMnemonic(KeyEvent.VK_F8);
        cbMenuItem.addItemListener(this);
        menu.add(cbMenuItem);

        
        //Text Mode
        cbMenuItem = new JCheckBoxMenuItem("Text Mode");
        cbMenuItem.addItemListener(this);
        menu.add(cbMenuItem);
        
        //Device Mode
        cbMenuItem = new JCheckBoxMenuItem("Device Mode");
        cbMenuItem.addItemListener(this);
        menu.add(cbMenuItem);

        //Select Mode
        cbMenuItem = new JCheckBoxMenuItem("Select Mode");
        cbMenuItem.addItemListener(this);
        menu.add(cbMenuItem);
        
        menu.addSeparator();
        
        //Undo
        menuItem = new JMenuItem("Undo",KeyEvent.VK_U);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menu.addSeparator();
        
        //Cut
        menuItem = new JMenuItem("Cut",KeyEvent.VK_C);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Copy
        menuItem = new JMenuItem("Copy",KeyEvent.VK_O);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Paste
        menuItem = new JMenuItem("Paste",KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Clear
        menuItem = new JMenuItem("Clear",KeyEvent.VK_L);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Build the Devices menu.
        menu = new JMenu("Devices");
        menu.setMnemonic(KeyEvent.VK_D);
        menu.getAccessibleContext().setAccessibleDescription(
                "This is the Devices menu.");
        menuBar.add(menu);
        
        //Show Devices
        cbMenuItem = new JCheckBoxMenuItem("Show Devices");
        //cbMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9));
        cbMenuItem.addItemListener(this);
        menu.add(cbMenuItem);
        
        //Edit...
        menuItem = new JMenuItem("Edit...");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menu.addSeparator();
        
        //New picture...
        menuItem = new JMenuItem("New picture...");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Replace picture...
        menuItem = new JMenuItem("Replace picture...");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menu.addSeparator();
        
        //New Device Library...
        menuItem = new JMenuItem("New Device Library...");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Import Device...
        menuItem = new JMenuItem("Import Device...");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Export Device...
        menuItem = new JMenuItem("Export Device...");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Remove Device...
        menuItem = new JMenuItem("Remove Device...");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Build the Tools menu.
        menu = new JMenu("Tools");
        menu.setMnemonic(KeyEvent.VK_T);
        menu.getAccessibleContext().setAccessibleDescription("This is the Tools menu.");
        menuBar.add(menu);
        
        //Compile...
        menuItem = new JMenuItem("Compile...");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menu.addSeparator();
        
        //Edit Output file...
        menuItem = new JMenuItem("Edit Output file...");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Edit Score file...
        menuItem = new JMenuItem("Edit Score file...");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Edit Include file...
        menuItem = new JMenuItem("Edit Include file...");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menu.addSeparator();
        
        //Run...
        menuItem = new JMenuItem("Run...");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Stop
        menuItem = new JMenuItem("Stop");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Build the Options menu.
        menu = new JMenu("Options");
        menu.setMnemonic(KeyEvent.VK_O);
        menu.getAccessibleContext().setAccessibleDescription("This is the Options menu.");
        menuBar.add(menu);
        
        //Show Loose Wires
        cbMenuItem = new JCheckBoxMenuItem("Show Loose Wires");
        cbMenuItem.addItemListener(this);
        menu.add(cbMenuItem);
        
        menu.addSeparator();
        
        //Zoom In
        menuItem = new JMenuItem("Zoom In");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Zoom Out
        menuItem = new JMenuItem("Zoom Out");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Normal View
        menuItem = new JMenuItem("Normal View");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menu.addSeparator();
        
        //Run Options...
        menuItem = new JMenuItem("Run Options...");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Preferences...
        menuItem = new JMenuItem("Preferences...");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Build the Help menu.
        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);
        menu.getAccessibleContext().setAccessibleDescription("This is the Help menu.");
        menuBar.add(menu);
        
        //Index
        menuItem = new JMenuItem("Index");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //CSound
        menuItem = new JMenuItem("CSound");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Using Help
        menuItem = new JMenuItem("Using Help");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        
        return menuBar;
    }
    
    protected JPanel createButtonBar()
    {
        Dimension buttonSize = new Dimension(35,35);
        
        //Create the button-panel-to-be.
        JPanel buttonPanel = new JPanel(new FlowLayout(LEFT, hGap, vGap));
        buttonPanel.setOpaque(true);

        //create Devices button
        ImageIcon devices = createImageIcon("assets/icons/devices.gif");
        JButton b1 = new JButton(devices);
        b1.setMnemonic(KeyEvent.VK_M);
        //b1.addActionListener(this);
        //b1.setActionCommand("devices"); //action identity
        b1.setPreferredSize(buttonSize);
        buttonPanel.add(b1);
        
        //create PatchCord button
        ImageIcon patchcord = createImageIcon("assets/icons/patchcord.gif");
        JButton b2 = new JButton(patchcord);
        b2.setPreferredSize(buttonSize);
        buttonPanel.add(b2);
        
        //create TextEdit button
        ImageIcon textedit = createImageIcon("assets/icons/textedit.gif");
        JButton b3 = new JButton(textedit);
        b3.setPreferredSize(buttonSize);
        buttonPanel.add(b3);
        
        //create NewInstrument button
        ImageIcon newinstrument = createImageIcon("assets/icons/newinstrument.gif");
        JButton b4 = new JButton(newinstrument);
        b4.setPreferredSize(buttonSize);
        buttonPanel.add(b4);
        
        //create Select button
        ImageIcon select = createImageIcon("assets/icons/select.gif");
        JButton b5 = new JButton(select);
        b5.setPreferredSize(buttonSize);
        buttonPanel.add(b5);
        
        //create Cut button
        ImageIcon cut = createImageIcon("assets/icons/cut.gif");
        JButton b6 = new JButton(cut);
        b6.setPreferredSize(buttonSize);
        buttonPanel.add(b6);
        
        //create Copy button
        ImageIcon copy = createImageIcon("assets/icons/copy.gif");
        JButton b7 = new JButton(copy);
        b7.setPreferredSize(buttonSize);
        buttonPanel.add(b7);
        
        //create Paste button
        ImageIcon paste = createImageIcon("assets/icons/paste.gif");
        JButton b8 = new JButton(paste);
        b8.setPreferredSize(buttonSize);
        buttonPanel.add(b8);
        
        //create Erase button
        ImageIcon erase = createImageIcon("assets/icons/erase.gif");
        JButton b9 = new JButton(erase);
        b9.setPreferredSize(buttonSize);
        buttonPanel.add(b9);
        
        //create Undo button
        ImageIcon undo = createImageIcon("assets/icons/undo.gif");
        JButton b10 = new JButton(undo);
        b10.setPreferredSize(buttonSize);
        buttonPanel.add(b10);
        
        //create Zoom button
        ImageIcon zoom = createImageIcon("assets/icons/zoom.gif");
        JButton b11 = new JButton(zoom);
        b11.setPreferredSize(buttonSize);
        buttonPanel.add(b11);
        
        
        //create Unzoom button
        ImageIcon unzoom = createImageIcon("assets/icons/unzoom.gif");
        JButton b12 = new JButton(unzoom);
        b12.setPreferredSize(buttonSize);
        buttonPanel.add(b12);

        //create Compile button
        ImageIcon compile = createImageIcon("assets/icons/compile.gif");
        JButton b13 = new JButton(compile);
        b13.setPreferredSize(buttonSize);
        buttonPanel.add(b13);
        
        //create Play button
        ImageIcon play = createImageIcon("assets/icons/play.gif");
        JButton b14 = new JButton(play);
        b14.setPreferredSize(buttonSize);
        buttonPanel.add(b14);
        
        //create Stop button
        ImageIcon stop = createImageIcon("");
        JButton b15 = new JButton(stop);
        b15.setPreferredSize(buttonSize);
        buttonPanel.add(b15);
        
        //create Help button
        ImageIcon help = createImageIcon("assets/icons/help.gif");
        JButton b16 = new JButton(help);
        b16.setPreferredSize(buttonSize);
        buttonPanel.add(b16);

        return buttonPanel;
    }

/*    //React to menu selections.  ORIGINAL
    public void actionPerformed(ActionEvent e) {
        if ("new".equals(e.getActionCommand())) { //new
            createFrame();
        } else { //quit
            quit();
        }
    }
*/    

    public void actionPerformed(ActionEvent e) {
        
        if ("New".equals(e.getActionCommand())) { //New
            createPatchFrame();
        } 
        if ("Open...".equals(e.getActionCommand())) { //Open
            int returnVal = fc.showOpenDialog(jPatchwork.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                output.append("Opening: " + file.getName() + "." + newline);
            } else {
                output.append("Open command cancelled by user." + newline);
            }
            //log.setCaretPosition(log.getDocument().getLength());

        } 
        if ("Edit...".equals(e.getActionCommand())) { //Edit...
            displayDeviceEditor();
        }
        
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Action event detected."
                   + newline
                   + "    Event source: " + source.getText()
                   + " (an instance of " + getClassName(source) + ")";
        output.append(s + newline);
        output.setCaretPosition(output.getDocument().getLength());
    }

    public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Item event detected."
                   + newline
                   + "    Event source: " + source.getText()
                   + " (an instance of " + getClassName(source) + ")"
                   + newline
                   + "    New state: "
                   + ((e.getStateChange() == ItemEvent.SELECTED) ?
                     "selected":"unselected");
        output.append(s + newline);
        output.setCaretPosition(output.getDocument().getLength());
    }

    //Create a new patch frame.
    protected void createPatchFrame() {
        PatchFrame patchFrame = new PatchFrame();
        patchFrame.setVisible(true); //necessary as of 1.3
        patchFrame.setBackground(Color.white);
        desktop.add(patchFrame);
        try {
            patchFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }
    
    protected void displayDeviceEditor() {
        //DeviceEditor deviceEditor = new DeviceEditor();
        DeviceEditor.createAndShowGUI();
    }

    //Quit the application.
    protected void quit() {
        System.exit(0);
    }
    
    // Returns just the class name -- no package info.
    protected String getClassName(Object o) {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = jPatchwork.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        //set style
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { }


        //Create and set up the window.
        jPatchwork frame = new jPatchwork();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display the window.
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