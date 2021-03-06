import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

/** container for important button*/
public class ButtonPanel extends JPanel implements ActionListener{
	private JButton exitWithPass;
	
	public ButtonPanel(){
		//Set background color and opacity
		this.setBackground(Globals.white); 
		this.setOpaque(true);
		
		//JButton for exiting program
		exitWithPass = new JButton(" Save and quit program "); 
		exitWithPass.setHorizontalAlignment(AbstractButton.CENTER);
		exitWithPass.setVerticalAlignment(AbstractButton.CENTER);
		exitWithPass.setFont(Globals.font);
		this.add(exitWithPass);
		exitWithPass.setToolTipText("Finished signing out students?");
		
		//Add ActionListeners 
		exitWithPass.addActionListener(this);
	
		//Constrain dimensions of this
		Dimension d = new Dimension(GUI.guiLength,100);
		this.setPreferredSize(d); // really, really, really set it to be this size
		this.setMaximumSize(d); // anotha one
		this.setMinimumSize(d); // and anotha one
	}

	@Override
	public void actionPerformed(ActionEvent e) { //If button is pressed, set CustomDialog to visible
		GUI.setCustomDialogVisible(true);
	}
}