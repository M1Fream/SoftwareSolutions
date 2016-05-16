import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;


public class ButtonPanel extends JPanel implements ActionListener{
	private JButton exitWithPass;
	
	public ButtonPanel(){
		this.setBackground(new Color((float) 1.0, (float) 1.0, (float) 1.0)); 
		this.setOpaque(true);
		
		//JButton for exiting program
		exitWithPass = new JButton(" Save and quit program "); 
		exitWithPass.setHorizontalAlignment(AbstractButton.CENTER);
		exitWithPass.setVerticalAlignment(AbstractButton.CENTER);
		exitWithPass.setFont(new Font("Times New Roman",Font.PLAIN,28));
		this.add(exitWithPass);
		exitWithPass.setToolTipText("Finished signing out students?");
		
		//Creates and adds bevel to Border of JButtons
		Border raisedbevel = BorderFactory.createSoftBevelBorder(BevelBorder.RAISED,Color.DARK_GRAY,Color.BLACK);
		Border loweredbevel = BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED,Color.DARK_GRAY,Color.BLACK);
		Border border = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
		exitWithPass.setBorder(border);
		
		//Add ActionListeners 
		exitWithPass.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GUI.setCustomDialogVisible(true);
		
	}
}