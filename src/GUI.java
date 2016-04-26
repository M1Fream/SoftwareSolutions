import java.awt.*;
import javax.swing.*;


public class GUI extends JFrame{
	private JLabel fail;
	
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
			PromPanel promPanel = new PromPanel(); 
			add(promPanel,BorderLayout.CENTER); 
		
			setSize(1280, 1024); //Default frame size
			setExtendedState(JFrame.MAXIMIZED_BOTH); //Set frame to max size 
			setVisible(true); //Make frame actually visible
			
			fail = new JLabel("Invalid");
			add(fail);
			fail.setVisible(false);
			
			pd = new PasswordDialog(this);
			pd.setVisible(true);
			
			cd = new CustomDialog(this, promPanel.getPass());
	}
			
	//Toggle cd's visibility
	public static void setDialogVisible(boolean b){
		cd.setVisible(b);
	}
	
	public void close(){
		setVisible(false);
		dispose();
	}
	
	public void badID() {
		fail.setVisible(true);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		fail.setVisible(false);
	}
}
