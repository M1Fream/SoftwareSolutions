import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;


public class PromPanel extends JPanel implements ActionListener{

	private JButton enterID;
	private JTextField idEntry;
	
	private GUI myGUI;
	
	public PromPanel(GUI gui){
		myGUI = gui;
		
		this.setBackground(new Color((float) 1.0, (float) 1.0, (float) 1.0)); 
		this.setOpaque(true);
		
		this.setSize(GUI.guiWidth, GUI.guiLength/2);
		
		JLabel idLabel = new JLabel();
		idLabel.setText("Enter 6-digit student ID:"); //Displays text to show users where to type
		idLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
		this.add(idLabel);
		
		//TextField to enter student id
		idEntry = new JTextField(10); 
		idEntry.setToolTipText("Enter ID's here");
		idEntry.setFont(new Font("Times New Roman",Font.PLAIN,20));
		this.add(idEntry);
		
		//Functionless filler
		this.add(Box.createHorizontalStrut(3));
		
		//JButton for entering ID's
		enterID = new JButton("Enter ID");
		enterID.setHorizontalAlignment(AbstractButton.CENTER);
		enterID.setVerticalAlignment(AbstractButton.CENTER);
		enterID.setFont(new Font("Times New Roman",Font.PLAIN,20));
		this.add(enterID);
		enterID.addActionListener(this);
		
		//More filler!
		this.add(Box.createHorizontalStrut(25));
		
		//Creates and adds bevel to Border of JButtons
		Border raisedbevel = BorderFactory.createSoftBevelBorder(BevelBorder.RAISED);
		Border loweredbevel = BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED);
		Border border = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
		enterID.setBorder(border);
		
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
