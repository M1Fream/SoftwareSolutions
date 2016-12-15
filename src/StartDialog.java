import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class StartDialog extends JDialog implements PropertyChangeListener{
	private JOptionPane optionPane;
	
	private boolean firstMessage = true; //Whether or not program is showing first message
	
	//Strings to be used in optionPane
	private String btnString = " OK ";
	private String msgString1;
	
	private GUI myGUI;
	
	public StartDialog(GUI gui){
		myGUI = gui;
		
		//Set font
		UIManager.put("OptionPane.messageFont", Globals.font);
        UIManager.put("OptionPane.buttonFont", Globals.font);
    	
        //Set title and put on top
		this.setTitle("Welcome");
		this.setAlwaysOnTop(true);
		
		msgString1 = " Welcome to the Software Solutions Student Sign-Out Program! ";
		
		Object[] options = {btnString};
		
		//Create the JOptionPane.
    	optionPane = new JOptionPane(msgString1,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.YES_NO_OPTION,
                null,
                options,
                options[0]);
        
    	//Make this dialog display it.
        setContentPane(optionPane);
    	
    	//Handle window closing correctly.
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                /*
                 * Instead of directly closing the window,
                 * we're going to change the JOptionPane's
                 * value property.
                 */
                    optionPane.setValue(new Integer(
                                        JOptionPane.CLOSED_OPTION));
            }
        });
        
        //Register an event handler that reacts to option pane state changes.
        optionPane.addPropertyChangeListener(this);
        
        //Set size of dialog and set resizable to false
        setSize(900, 300);
        this.setResizable(false);
        
        //Set location and visibility
        this.setLocation(GUI.guiWidth/2-450, GUI.guiLength/2-150);
    	this.setVisible(true);
    	
	}
	
	/** This method reacts to state changes in the option pane. */
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();
        if (isVisible()
         && (e.getSource() == optionPane)
         && (JOptionPane.VALUE_PROPERTY.equals(prop) ||
             JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            Object value = optionPane.getValue();

            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                //ignore reset
                return;
            }
            //Reset the JOptionPane's value.
            //If you don't do this, then if the user
            //presses the same button next time, no
            //property change event will be fired.
            optionPane.setValue(
                    JOptionPane.UNINITIALIZED_VALUE);
            if(btnString.equals(value)){
        		if(firstMessage){//If showing first message
        			optionPane.setMessage("Please press the button and select a save location for the data.");
        			
        			firstMessage=false;
        		}else{//If showing second message
        			//Adapted from: http://www.codejava.net/java-se/swing/show-simple-open-file-dialog-using-jfilechooser
        			JFileChooser fileChooser = new JFileChooser();
        			fileChooser.setCurrentDirectory(new File("."));
        			fileChooser.setDialogTitle("Select save location");
        			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            	
        			int result = fileChooser.showOpenDialog(this);
            	
        			if (result == JFileChooser.APPROVE_OPTION) {
            			Globals.savePath = fileChooser.getSelectedFile();
            			try {
            	    	IO.init();
							close();
            			} catch (IOException e2) {
            				SaveLocationErrorDialog se = new SaveLocationErrorDialog(myGUI);
            			}	
        			}
        		}
            }
        }
    }
	
	public void close(){//Close window and make password dialog visible
        this.setVisible(false);
        PasswordDialog pd = new PasswordDialog(myGUI);
        pd.setVisible(true);
        myGUI.setResizable(false);
        this.dispose();
    }
}