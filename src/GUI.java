import java.awt.*;
import javax.swing.*;

/** Main gui for the project
 * controls everything else from it's main function */
public class GUI extends JFrame{
	private static CustomDialog cd;
	private static PasswordDialog pd;
	private static BackgroundPanel bp;
	private static SignoutPanel promPanel;
	
	//Width and length of gui
	public static int guiWidth = 1280;
	public static int guiLength = 1024;
	
	public GUI(String string){
		super(string);
	}
	/** Main method for the entire program, called from Main and runs everything
	 */
	public void main(){
			this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //Default is do nothing on close
			this.setAlwaysOnTop(true); //Always top window
			this.setLayout(new BorderLayout()); //Set frame's layout
			
			//Set background color
			this.setBackground(Globals.white);
			
			//Set up JPanel
			JPanel p = new JPanel();
			p.setOpaque(true);
			p.setBackground(Globals.white);
			p.setLayout(new BorderLayout());
			
			Dimension d = new Dimension(guiWidth,guiLength); //Make dimension
			p.setPreferredSize(d); //Set dimension
			p.setMinimumSize(d); //Really set dimension
			p.setMaximumSize(d); //Really REALLY set dimension
			
			//Set up another JPanel
			JPanel p2 = new JPanel();
			p2.setOpaque(true);
			p2.setBackground(Globals.white);
			p2.setLayout(new BorderLayout());
			
			//How about another?
			JPanel p3 = new JPanel();
			p3.setOpaque(true);
			p3.setBackground(Globals.white);
			p3.setLayout(new BorderLayout());
			
			//Tired yet?
			JPanel p4 = new JPanel();
			p4.setOpaque(true);
			p4.setBackground(Globals.white);
			p4.setLayout(new BorderLayout());
			
			//And you thought we were done
			JPanel p5 = new JPanel();
			p5.setOpaque(true);
			p5.setBackground(Globals.white);
			p5.setLayout(new BorderLayout());
			
			//As a wise man said, "Anotha one"
			JPanel p6 = new JPanel();
			p6.setOpaque(true);
			p6.setBackground(Globals.white);
			p6.setLayout(new BorderLayout());
			
			//I'm out of witty comments to make :(
			JPanel p7 = new JPanel();
			p7.setOpaque(true);
			p7.setBackground(Globals.white);
			p7.setLayout(new BorderLayout());
			
			//Let's make another dimension
			Dimension d2 = new Dimension(guiWidth,guiLength-200); 
			p.setPreferredSize(d2); //Set the size
			p.setMaximumSize(d2); //Do it again
			p.setMinimumSize(d2); //Third time is the charm
			
			
			//Invisible box
			p.add(Box.createRigidArea(new Dimension(0,50)),BorderLayout.NORTH);
			
			//Add PromPanel
			promPanel = new SignoutPanel(this);
			p2.add(promPanel,BorderLayout.NORTH);
			
			//Invisible box 2
			p3.add(Box.createRigidArea(new Dimension(0,50)),BorderLayout.NORTH);
			
			//Add CrestPanel
			p4.add(new CrestPanel(),BorderLayout.NORTH);
			
			p5.add(new PeriodPanel(),BorderLayout.NORTH);
			
			//Invisible box 3
			p6.add(Box.createRigidArea(new Dimension(0,50)),BorderLayout.NORTH);
			//p5.add(Box.createRigidArea(new Dimension(0,50)),BorderLayout.NORTH);
			
			//Add BackgroundPanel with falcon.jpg, falcon2.jpg, or falcon3.jpg, based on constructor argument
			bp = new BackgroundPanel("falcon");
			p7.add(bp,BorderLayout.CENTER);
			//p6.add(bp,BorderLayout.CENTER);
			
			//Add ButtonPanel
			p7.add(new ButtonPanel(),BorderLayout.NORTH);
			//p6.add(new ButtonPanel(),BorderLayout.NORTH);
			
			//Put it all together
			p6.add(p7,BorderLayout.CENTER);
			p5.add(p6,BorderLayout.CENTER); //Add one
			p4.add(p5,BorderLayout.CENTER); //Add another
			p3.add(p4,BorderLayout.CENTER); //Let's do it again
			p2.add(p3,BorderLayout.CENTER); //And again
			p.add(p2,BorderLayout.CENTER); //And again
			this.add(p,BorderLayout.NORTH); //Anddddddd one last time
			
			this.setSize(1280, 1024); //Set default frame size
			
			this.setExtendedState(JFrame.MAXIMIZED_BOTH); //Set frame to max size 
			this.setVisible(true); //Make frame actually visible
			
			
			//Create dialogs	
			StartDialog sd = new StartDialog(this);
			sd.setVisible(true);
			sd.setAlwaysOnTop(true);
			
			pd = new PasswordDialog(this);
			
			cd = new CustomDialog(this);
			this.getRootPane().setDefaultButton(promPanel.getSubmitButton());
	}
			
	//Toggle visibility of CustomDialog
	public static void setCustomDialogVisible(boolean b){
		cd.setVisible(b);
	}
	
	//Toggle visibility of PasswordDialog
	public static void setPasswordDialogVisible(boolean b){
		pd.setVisible(b);
	}
	/** Closes program*/
	public void close(){
		this.setVisible(false);
		this.dispose();
	}
	
	public void rainbowBackground(){ //Not fully-functioning
		bp.rainbowBackground();
	}
	
	public SignoutPanel getPromPanel(){
		return promPanel;
	}
	
	public CustomDialog getCustomDialog(){
		return cd;
	}
}