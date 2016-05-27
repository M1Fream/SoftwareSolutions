import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;


public class PromPanel extends JPanel implements ActionListener{

	private JButton enterID;
	private JTextField idEntry; //TextField for user input
	
	private GUI myGUI;
	
	public PromPanel(GUI gui){
		myGUI = gui;
		
		//Set background color and opacity
		this.setBackground(new Color((float) 1.0, (float) 1.0, (float) 1.0)); 
		this.setOpaque(true);
		
		//Set size
		this.setSize(GUI.guiWidth, GUI.guiLength/2);
		
		//Make a label
		JLabel idLabel = new JLabel();
		idLabel.setText("Enter 6-digit student ID:"); //Displays text to show users where to type
		idLabel.setFont(Globals.font); //Set font
		this.add(idLabel);
		
		//Functionless filler
		this.add(Box.createHorizontalStrut(3));
		
		//TextField to enter student id
		idEntry = new JTextField(10); 
		idEntry.setToolTipText("Enter ID's here");
		idEntry.setFont(new Font("Times New Roman",Font.PLAIN,32));
		this.add(idEntry);

		//More filler!
		this.add(Box.createHorizontalStrut(25));
		
		//JButton for entering ID's
		enterID = new JButton(" Enter ID ");
		enterID.setHorizontalAlignment(AbstractButton.CENTER);
		enterID.setVerticalAlignment(AbstractButton.CENTER);
		enterID.setFont(Globals.font);
		this.add(enterID);
		enterID.addActionListener(this);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			Globals.studentList.add(new Student(Integer.parseInt(idEntry.getText())));				
			//Make confirmation dialog to ensure that it is the correct student.
			ConfirmationDialog conDialog = new ConfirmationDialog(myGUI,Globals.studentList.get(Globals.studentList.size()-1));
		} catch (IDoutOfRangeException e1) {
			ErrorDialog ed = new ErrorDialog();
		} catch (java.lang.NumberFormatException e1) {
			ErrorDialog ed = new ErrorDialog();
		}
		idEntry.setText("");
	}

	public JButton getSubmitButton() {
		return enterID;
	}
}
