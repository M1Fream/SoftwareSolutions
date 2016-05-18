import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class ErrorDialog extends JDialog implements PropertyChangeListener {
	private JOptionPane optionPane;
	
	private String btnString = " OK ";
	private String msgString1;
	
	public ErrorDialog(){

		this.setTitle("Invalid ID");
		this.setAlwaysOnTop(true);
		
		//Set font
		UIManager.put("OptionPane.messageFont", new Font("Times New Roman",Font.PLAIN,32));
        UIManager.put("OptionPane.buttonFont", new Font("Times New Roman",Font.PLAIN,32));
    	
		msgString1 = "Error: 6-digit ID not entered";
		
		Object[] options = {btnString};
		
		//Create the JOptionPane.
    	optionPane = new JOptionPane(msgString1,
                JOptionPane.ERROR_MESSAGE,
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
        setSize(700, 200);
        this.setResizable(false);
        this.setLocation(GUI.guiWidth/2-350, GUI.guiLength/2-100);
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
            	close();
            }
        }
    }
    
    public void close(){
    	//Delete dialog box
    	this.setVisible(false);
    	this.dispose();
    }
}
