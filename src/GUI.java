import java.awt.*;
import javax.swing.*;


public class GUI extends JFrame{
	private static CustomDialog cd;
	private static PasswordDialog pd;
	private static BackgroundPanel bp;
	
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
			PromPanel promPanel = new PromPanel(this);
			this.add(promPanel,BorderLayout.NORTH);
		
			//Add BackgroundPanel with falcon.jpg, falcon2.jpg, or falcon3.jpg, based on constructor argument
			bp = new BackgroundPanel("falcon");
			bp.setVisible(true);
			this.add(bp,BorderLayout.CENTER);
			
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
	
	public void rainbowBackground(){
		bp.rainbowBackground();
	}
}