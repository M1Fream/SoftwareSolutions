import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class ConfirmationDialog extends JDialog implements PropertyChangeListener {
	private JOptionPane optionPane;

    private String btnString1 = "Yes";
    private String btnString2 = "No";
    private String msgString1;
    
    private GUI myGUI;
    private Student myStudent;
    
    public ConfirmationDialog(GUI gui,Student s){
    	super(gui, true);
    	myGUI = gui;
    	myStudent = s;
    	
    	this.setTitle("Confirm student");
    	
    	//Create an array specifying the number of dialog buttons and their text.
    	Object[] options = {btnString1,btnString2};
    	
    	//Message specific for ID just entered.
    	msgString1 = "Are you "+myStudent.getName()+"?";
    	
    	//Create the JOptionPane.
    	optionPane = new JOptionPane(msgString1,
                JOptionPane.QUESTION_MESSAGE,
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
        setSize(250, 150);
        this.setResizable(false);
        this.setLocation(GUI.guiWidth/2-250, GUI.guiLength/2-75);
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
            if (btnString2.equals(value)) { //If user didn't confirm name
            	Globals.studentList.remove(Globals.studentList.size()-1); //Remove the student who was not confirmed
            	close();
            }else if (btnString1.equals(value)){
            	if(Globals.studentList.get(Globals.studentList.size()-1).getID()==372290){
            		myGUI.rainbowBackground();
            	}
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
