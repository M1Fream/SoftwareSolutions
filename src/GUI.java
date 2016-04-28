import java.awt.*;
import javax.swing.*;


public class GUI extends JFrame{
	private static CustomDialog cd;
	private static PasswordDialog pd;
	
	public GUI(String string){
		super(string);
	}
	
	public void main(){
			//JFrame frame = new JFrame("Prom Sign Out"); //Create frame for GUI
			setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //Default is do nothing on close
			//Set frame's layout
			setLayout(new BorderLayout()); 
		
			//Add PromPanel to center of frame
			PromPanel promPanel = new PromPanel(this); 
			add(promPanel,BorderLayout.CENTER); 
		
			setSize(1280, 1024); //Default frame size
			setExtendedState(JFrame.MAXIMIZED_BOTH); //Set frame to max size 
			setVisible(true); //Make frame actually visible
			
			pd = new PasswordDialog(this);
			pd.setVisible(true);
			
			cd = new CustomDialog(this, promPanel.getPass());
			getRootPane().setDefaultButton(promPanel.getSubmitButton());
	}
			
	//Toggle cd's visibility
	public static void setDialogVisible(boolean b){
		cd.setVisible(b);
	}
	
	public void close(){
		setVisible(false);
		dispose();
	}
	
}