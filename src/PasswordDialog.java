import java.awt.Font;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class PasswordDialog extends JDialog implements ActionListener,PropertyChangeListener {
	private String typedText = null; //Contains text typed by user
    private JTextField textField; //TextField for user to type in
	private String passwordAttempt;
	
    private JOptionPane optionPane;

    //Strings to be used in optionPane
    private String btnString1 = " Enter ";
    private String msgString1 = "Please enter the password you'd like to use to exit the program: ";
    
    private GUI myGUI;
    
    //Get text typed by user
    public String getValidatedText() {
        return typedText;
    }
    
	public PasswordDialog(GUI gui) {
		super(gui,true);
		//Set font
        UIManager.put("OptionPane.messageFont", new Font("Times New Roman",Font.PLAIN,32));
        UIManager.put("OptionPane.buttonFont", new Font("Times New Roman",Font.PLAIN,32));
        
		myGUI=gui;
		
		//Set title
		this.setTitle("Set password");
		
		//Create an array of the text and components to be displayed.
		textField = new JTextField(10);
		//Set font
		textField.setFont(new Font("Times New Roman",Font.PLAIN,32));
		
        Object[] array = {msgString1, textField};
        
        //Create an array specifying the number of dialog buttons
        //and their text.
        Object[] options = {btnString1};

        //Create the JOptionPane.
        optionPane = new JOptionPane(array,
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
        
      //Ensure the text field always gets the first focus.
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent ce) {
                textField.requestFocusInWindow();
            }
        });

        //Register an event handler that puts the text into the option pane.
        textField.addActionListener(this);

        //Register an event handler that reacts to option pane state changes.
        optionPane.addPropertyChangeListener(this);
        
        //Set size of dialog and set resizable to false
        setSize(900, 300);
        this.setResizable(false);
        
        //Set location
        this.setLocation(GUI.guiWidth/2-450, GUI.guiLength/2-150);
    }

    /** This method handles events for the text field. */
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(btnString1);
    }

    /** This method reacts to state changes in the option pane. */
    public void propertyChange(PropertyChangeEvent e) {
    	String prop = e.getPropertyName();
    	if (isVisible() && (e.getSource() == optionPane) && (JOptionPane.VALUE_PROPERTY.equals(prop) || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
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
            	
            	if(passwordAttempt!=null){//If an attempted password has been typed (for verifying password)
            		if (btnString1.equals(value)) {
                    	typedText = textField.getText();
                    	if (passwordAttempt.equals(typedText)) {
	                    	//we're done; clear and dismiss the dialog
                    		myGUI.getCustomDialog().setPassword(passwordAttempt);
	                    	this.close();
                    	} else {
	                    	//text was invalid
	                		textField.selectAll();
	                    	JOptionPane.showMessageDialog(PasswordDialog.this, "Sorry, that isn't the password you typed.", "Let's try again", JOptionPane.ERROR_MESSAGE);
	                    	Object[] array = {msgString1, textField};
	            			optionPane.setMessage(array);
	                    	typedText = null;
	                    	textField.requestFocusInWindow();
	                    	
	                    	//Reset passwordAttempt
	                    	passwordAttempt=null;
                    	}
            		} else { //user closed dialog
            			typedText = null;
            			clearText();             
            		}
            	} else {//If passwordAttempt hasn't been filled or has been reset
            		if (btnString1.equals(value)) {
            			passwordAttempt = textField.getText();
            			Object[] array = {"Enter the password again to confirm: ", textField};
            			optionPane.setMessage(array);
            			UIManager.put("OptionPane.messageFont", new Font("Times New Roman",Font.PLAIN,32));
            	        UIManager.put("OptionPane.buttonFont", new Font("Times New Roman",Font.PLAIN,32));
            			textField.grabFocus();
            		} else { //user closed dialog or clicked cancel
            			typedText = null;   
            		}
            	clearText();  
            }
        }
    }

    /** This method clears the dialog and hides it. */
    public void clearText(){
        textField.setText(null);
	}
	
    public void close(){
    	textField.setText(null);
        this.setVisible(false);
        this.dispose();
    }
}