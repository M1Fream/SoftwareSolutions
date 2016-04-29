import java.awt.*;
import javax.swing.*;


public class GUI extends JFrame{
	private static CustomDialog cd;
	private static PasswordDialog pd;
	
	public static int guiWidth = 1280;
	public static int guiLength = 1024;
	
	public GUI(String string){
		super(string);
	}
	
	public void main(){
			//JFrame frame = new JFrame("Prom Sign Out"); //Create frame for GUI
			this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //Default is do nothing on close
			//Set frame's layout
			this.setLayout(new BorderLayout()); 
		
			//Add PromPanel
			PromPanel promPanel = new PromPanel();
			this.add(promPanel,BorderLayout.CENTER);
		
			//Add BackgroundPanel
			BackgroundPanel bp = new BackgroundPanel();
			bp.setVisible(true);
			this.add(bp,BorderLayout.SOUTH);
			
			this.setSize(1280, 1024); //Default frame size
			
			this.setExtendedState(JFrame.MAXIMIZED_BOTH); //Set frame to max size 
			this.setVisible(true); //Make frame actually visible
			
			pd = new PasswordDialog(this);
			pd.setVisible(true);
			
			cd = new CustomDialog(this, promPanel.getPass());
			this.getRootPane().setDefaultButton(promPanel.getSubmitButton());
					
	}
			
	//Toggle cd's visibility
	public static void setDialogVisible(boolean b){
		cd.setVisible(b);
	}
	
	public void close(){
		this.setVisible(false);
		this.dispose();
	}
	
}