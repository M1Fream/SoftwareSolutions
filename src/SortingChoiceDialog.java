import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class SortingChoiceDialog extends JDialog implements PropertyChangeListener{
	private JOptionPane optionPane;
	
	//Strings to be used in optionPane
    private String btnString1 = " Sort and quit program ";
    private String btnString2 = " Cancel ";
    private String msgString1 = " Please select how you would like the data to be sorted ";
    
    private GUI myGUI;
	private SortingDialog mySortingDialog;
    
	private Choice sortingOptions;
	
	public SortingChoiceDialog(GUI gui, SortingDialog sd){
		//Set font
        UIManager.put("OptionPane.messageFont", Globals.font);
        UIManager.put("OptionPane.buttonFont", Globals.font);
        
		myGUI=gui;
		mySortingDialog=sd;
		
		//Set title
		this.setTitle("Select data sorting method");

		//Create Choice to let user select method of sorting
		sortingOptions = new Choice();
		sortingOptions.add(" ID ");
		sortingOptions.add(" Last Name ");
		sortingOptions.add(" Grade ");
		sortingOptions.setFont(Globals.font);
		
		Object[] array = {msgString1,sortingOptions};
		
	    //Create an array specifying the number of dialog buttons
	    //and their text.
	    Object[] options = {btnString1,btnString2};

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

	    //Register an event handler that reacts to option pane state changes.
	    optionPane.addPropertyChangeListener(this);
	       
	    //Set size of dialog and set resizable to false
	    setSize(800, 400);
	    this.setResizable(false);
	       
	    //Set location
	    this.setLocation(GUI.guiWidth/2-400, GUI.guiLength/2-200);
	     
	    this.setVisible(true);
	    this.setAlwaysOnTop(true);
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
            
            if(value == btnString1){ //If user selected "Sort and quit program"
            	//Get sorting method chosen by user
            	String selectedMethod = sortingOptions.getSelectedItem().trim();
            	
            	System.out.println(selectedMethod);
            	
            	//ArrayList to hold sorted Students
            	ArrayList<Student> sortedStudentList = new ArrayList<Student>(Globals.studentList);
            	
            	//Create new sorted Student list based on selected sorting method
            	if(selectedMethod.equals("ID")){
            		Collections.sort(sortedStudentList, new SortByID());
            	}else if(selectedMethod.equals("Last Name")){
            		Collections.sort(sortedStudentList, new SortByLastName());
            	}else if(selectedMethod.equals("Grade")){
            		Collections.sort(sortedStudentList, new SortByGrade());
            	}
            	
            	try {
					IO.write(sortedStudentList);
				} catch (IOException ex) {
					System.out.println(ex);
				}
            	
            	closeProgram();
            	this.close();
        	}else if(value == btnString2){ //If user selected "Cancel"
        		mySortingDialog.setVisible(true);;
        		this.close();
        	}
    	}
            
     }
	        
	public void close(){
	     this.setVisible(false);
	     this.dispose();
	}
	
	public void closeProgram(){
		this.setVisible(false);
		myGUI.dispose();
	}
}