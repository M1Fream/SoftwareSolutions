import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;


public class PromPanel extends JPanel implements ActionListener{

	private static String password;
	
	
	public String getPass(){
		return password;
	}
	
	public static void setPass(String newPass){
		password=newPass;
	}
	
	//Default no-args PromPanel constructor
	public PromPanel(){
		//Set default color to white-ish
		setBackground(new Color((float) 1.0, (float) .9801921560314, (float) .9801921560314)); 
		
		JTextPane idLabel = new JTextPane();
		idLabel.setText("Enter 6-digit student ID:"); //Displays text "Enter 6-digit student ID:" to show users where to type
		this.add(idLabel,BorderLayout.WEST);
		
		JTextField idEntry = new JTextField(6); //TextField to enter student id
		idEntry.setToolTipText("Enter ID's here");
		this.add(idEntry,BorderLayout.EAST);
		
		//JButton for entering ID's
		JButton enterID = new JButton("Enter ID");
		enterID.setHorizontalAlignment(AbstractButton.CENTER);
		enterID.setVerticalAlignment(AbstractButton.CENTER);
		this.add(enterID,BorderLayout.SOUTH);
		
		//JButton for exiting program
		JButton exitWithPass = new JButton("Save and quit program."); 
		exitWithPass.setHorizontalAlignment(AbstractButton.CENTER);
		exitWithPass.setVerticalAlignment(AbstractButton.CENTER);
		this.add(exitWithPass,BorderLayout.SOUTH);
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
		GUI.setDialogVisible(true);
	}
}
