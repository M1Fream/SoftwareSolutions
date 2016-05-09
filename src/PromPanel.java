import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;


public class PromPanel extends JPanel implements ActionListener{

	private static String password;
	private JButton enterID;
	private JButton exitWithPass;
	private JTextField idEntry;
	
	private GUI myGUI;
	
	public String getPass(){
		return password;
	}
	
	public static void setPass(String newPass){
		password=newPass;
	}
	
	public PromPanel(GUI gui){
		myGUI=gui;
		
		//Set default color to white
		setBackground(new Color((float) 1.0, (float) 1.0, (float) 1.0)); 
		this.setOpaque(true);
		
		this.setSize(GUI.guiWidth, GUI.guiLength/2);
		
		JLabel idLabel = new JLabel();
		idLabel.setText("Enter 6-digit student ID:"); //Displays text to show users where to type
		this.add(idLabel);
		
		//TextField to enter student id
		idEntry = new JTextField(10); 
		idEntry.setToolTipText("Enter ID's here");
		this.add(idEntry);
		
		//Functionless filler
		this.add(Box.createHorizontalStrut(3));
		
		//JButton for entering ID's
		enterID = new JButton("Enter ID");
		enterID.setHorizontalAlignment(AbstractButton.CENTER);
		enterID.setVerticalAlignment(AbstractButton.CENTER);
		this.add(enterID,BorderLayout.SOUTH);
		enterID.addActionListener(this);
		
		//More filler!
		this.add(Box.createHorizontalStrut(25));
		
		//JButton for exiting program
		exitWithPass = new JButton("Save and quit program."); 
		exitWithPass.setHorizontalAlignment(AbstractButton.CENTER);
		exitWithPass.setVerticalAlignment(AbstractButton.CENTER);
		this.add(exitWithPass);
		exitWithPass.setToolTipText("Finished signing out students?");
		
		
		//Creates and adds bevel to Border of JButtons
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border border = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
		enterID.setBorder(border);
		exitWithPass.setBorder(border);
		
		//Add ActionListeners 
		exitWithPass.addActionListener(this);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(exitWithPass)) {
			GUI.setDialogVisible(true);
		}
		if (e.getSource().equals(enterID)) {
			try {
				Globals.studentList.add(new Student(Integer.parseInt(idEntry.getText())));
				
				//Make confirmation dialog to ensure that it is the correct student.
				ConfirmationDialog conDialog = new ConfirmationDialog(myGUI,Globals.studentList.get(Globals.studentList.size()-1));
			} catch (IDoutOfRangeException e1) {
				System.out.println("Bad id");// make a new dialogue to yell at the user
				System.out.println(idEntry.getText());
			} catch (java.lang.NumberFormatException e1) {
				System.out.println("Really bad id"); //make same box as above ^^^^^^^^^
			}
			idEntry.setText("");
		}
	}

	public JButton getSubmitButton() {
		return enterID;
	}
}
