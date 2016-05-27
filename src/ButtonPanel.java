import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;


public class ButtonPanel extends JPanel implements ActionListener{
	private JButton exitWithPass;
	
	public ButtonPanel(){ 
		//Set background color and opacity
		this.setBackground(new Color((float) 1.0, (float) 1.0, (float) 1.0)); 
		this.setOpaque(true);
		
		//JButton for exiting program
		exitWithPass = new JButton(" Save and quit program "); 
		exitWithPass.setHorizontalAlignment(AbstractButton.CENTER);
		exitWithPass.setVerticalAlignment(AbstractButton.CENTER);
		exitWithPass.setFont(new Font("Times New Roman",Font.PLAIN,32));
		this.add(exitWithPass);
		exitWithPass.setToolTipText("Finished signing out students?");
		
		//Add ActionListeners 
		exitWithPass.addActionListener(this);
	
		//Constrain dimensions of this
		Dimension d = new Dimension(GUI.guiLength,100);
		this.setPreferredSize(d);
		this.setMaximumSize(d);
		this.setMinimumSize(d);
	}

	@Override
	public void actionPerformed(ActionEvent e) { //If button is pressed, set CustomDialog to visible
		GUI.setCustomDialogVisible(true);
	}
}
