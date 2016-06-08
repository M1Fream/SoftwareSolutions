import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class SignoutPanel extends JPanel implements ActionListener{

	private JButton enterID;
	private JTextField idEntry; //TextField for user input	
	private static Choice periodOptions;

	private GUI myGUI;
	
	public SignoutPanel(GUI gui){
		myGUI = gui;
		
		//Set background color and opacity
		this.setBackground(new Color((float) 1.0, (float) 1.0, (float) 1.0)); 
		this.setOpaque(true);
		
		//Set size
		this.setSize(GUI.guiWidth, GUI.guiLength/2);
		/*
		//Make a label
		JLabel periodLabel = new JLabel();
		periodLabel.setText("Select the current period:"); //Displays text showing users to select period
		periodLabel.setFont(Globals.font);
		this.add(periodLabel);
		
		//Function-less filler
		this.add(Box.createHorizontalStrut(3));
		
		periodOptions = new Choice(); //Choice to select which period it is currently
		//Add all of the periods
		periodOptions.add("    1 ");
		periodOptions.add("    2 ");
		periodOptions.add("    3 ");
		periodOptions.add("    4 ");
		periodOptions.add(" Lunch  ");
		periodOptions.add("    5 ");
		periodOptions.add("    6 ");
		periodOptions.add("    7 ");
		periodOptions.add("    8 ");
		periodOptions.setFont(Globals.font); //Set font
		this.add(periodOptions);
		*/
		//More filler!
		this.add(Box.createHorizontalStrut(40));
		
		//Make another label
		JLabel idLabel = new JLabel();
		idLabel.setText("Enter 6-digit student ID:"); //Displays text to show users where to type
		idLabel.setFont(Globals.font); //Set font
		this.add(idLabel);
		
		//Function-less filler
		this.add(Box.createHorizontalStrut(3));
		
		//TextField to enter student id
		idEntry = new JTextField(10);
		idEntry.setToolTipText("Enter ID's here");
		idEntry.setFont(Globals.font);		
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
	
		idEntry.requestFocusInWindow();
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			Globals.studentList.add(new Student(Integer.parseInt(idEntry.getText())));				
			//Make confirmation dialog to ensure that it is the correct student.
			ConfirmationDialog conDialog = new ConfirmationDialog(myGUI,Globals.studentList.get(Globals.studentList.size()-1));
		} catch (IDoutOfRangeException e1) {
			IDErrorDialog ed = new IDErrorDialog();
		} catch (java.lang.NumberFormatException e1) {
			IDErrorDialog ed = new IDErrorDialog();
		}
		idEntry.setText("");
		idEntry.requestFocusInWindow();
	}

	public JButton getSubmitButton() {
		return enterID;
	}
	
	/*public static String getPeriod(){
		return periodOptions.getSelectedItem().trim();
	}*/
}
