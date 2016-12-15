import java.awt.Choice;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PeriodPanel extends JPanel{
	private static Choice periodOptions;
	
	public PeriodPanel(){
		//Set background color and opacity
		this.setBackground(Globals.white); 
		this.setOpaque(true);
			
		//Set size
		this.setSize(GUI.guiWidth, GUI.guiLength/2);
			
		//Make a label
		JLabel periodLabel = new JLabel();
		periodLabel.setText("Select the current period:"); //Displays text showing users to select period
		periodLabel.setFont(Globals.font);
		this.add(periodLabel);
			
		//Function-less filler
		this.add(Box.createHorizontalStrut(3));
				
		periodOptions = new Choice(); //Choice to select which period it is currently
		//Add all of the periods
		periodOptions.add("     1 ");
		periodOptions.add("     2 ");
		periodOptions.add("     3 ");
		periodOptions.add("     4 ");
		periodOptions.add(" Lunch  ");
		periodOptions.add("     5 ");
		periodOptions.add("     6 ");
		periodOptions.add("     7 ");
		periodOptions.add("     8 ");
		periodOptions.setFont(Globals.font); //Set font
		this.add(periodOptions);
	}
	
	public static String getPeriod(){
		return periodOptions.getSelectedItem().trim();
	}
}