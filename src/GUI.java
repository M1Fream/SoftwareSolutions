import java.awt.*;
import javax.swing.*;


public class GUI extends JFrame{
	private static CustomDialog cd;
	private static PasswordDialog pd;
	private static BackgroundPanel bp;
	private static PromPanel promPanel;
	
	public static int guiWidth = 1280;
	public static int guiLength = 1024;
	
	public GUI(String string){
		super(string);
	}
	
	public void main(){
			this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //Default is do nothing on close
			this.setAlwaysOnTop(true); //Always top window
			this.setLayout(new BorderLayout()); //Set frame's layout
			this.setBackground(new Color((float) 1.0, (float) 1.0, (float) 1.0));
			
			//JPanels to store stuff
			JPanel p = new JPanel();
			p.setOpaque(true);
			p.setBackground(new Color((float) 1.0, (float) 1.0, (float) 1.0));
			p.setLayout(new BorderLayout());
			
			Dimension d = new Dimension(guiWidth,guiLength);
			p.setPreferredSize(d);
			p.setMinimumSize(d);
			p.setMaximumSize(d);
			
			JPanel p2 = new JPanel();
			p2.setOpaque(true);
			p2.setBackground(new Color((float) 1.0, (float) 1.0, (float) 1.0));
			p2.setLayout(new BorderLayout());
			
			JPanel p3 = new JPanel();
			p3.setOpaque(true);
			p3.setBackground(new Color((float) 1.0, (float) 1.0, (float) 1.0));
			p3.setLayout(new BorderLayout());
			
			//Add PromPanel
			promPanel = new PromPanel(this);
			p.add(promPanel,BorderLayout.NORTH);
			
			//Add BackgroundPanel with falcon.jpg, falcon2.jpg, or falcon3.jpg, based on constructor argument
			bp = new BackgroundPanel("falcon");
			//p2.add(bp,BorderLayout.NORTH);
			p3.add(bp,BorderLayout.CENTER);
			
			//Add ButtonPanel
			p3.add(new ButtonPanel(),BorderLayout.NORTH);
			
			//Add CrestPanel
			//p3.add(new CrestPanel(),BorderLayout.CENTER);
			p2.add(new CrestPanel(),BorderLayout.NORTH);
			
			//Put it all together
			p2.add(p3,BorderLayout.CENTER);
			p.add(p2,BorderLayout.CENTER);
			this.add(p,BorderLayout.NORTH);
			
			this.setSize(1280, 1024); //Default frame size
			
			this.setExtendedState(JFrame.MAXIMIZED_BOTH); //Set frame to max size 
			this.setVisible(true); //Make frame actually visible
			
			StartDialog sd = new StartDialog(this);
			sd.setVisible(true);
			sd.setAlwaysOnTop(true);
			
			pd = new PasswordDialog(this);
			
			cd = new CustomDialog(this);
			this.getRootPane().setDefaultButton(promPanel.getSubmitButton());

	}
			
	//Toggle visibility
	public static void setCustomDialogVisible(boolean b){
		cd.setVisible(b);
	}
	
	public static void setPasswordDialogVisible(boolean b){
		pd.setVisible(b);
	}

	public void close(){
		this.setVisible(false);
		this.dispose();
	}
	
	public void rainbowBackground(){
		bp.rainbowBackground();
	}
	
	public PromPanel getPromPanel(){
		return promPanel;
	}
	
	public CustomDialog getCustomDialog(){
		return cd;
	}
}