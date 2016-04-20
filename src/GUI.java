import java.awt.*;

import javax.swing.*;


public class GUI extends JFrame{
	private JLabel fail;
	
	public GUI(String string) {
		super(string);
		main();
	}

	public void main(){
		//JFrame frame = new JFrame("Prom Sign Out"); //Instantiate frame for GUI
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);//Default is to do nothing on close (WindowListener handles closing and asks for password)
	
		setLayout(new BorderLayout()); //Set frame's layout
		
		//PromPanel promPanel = new PromPanel(); 
		//add(promPanel,BorderLayout.CENTER); //Add PromPanel to center of frame
		
		setSize(1280, 1024); //Default frame size
		setExtendedState(JFrame.MAXIMIZED_BOTH); //Sets frame to max size
		setVisible(true); //Makes frame actually visible
		
		fail = new JLabel("Invalid");
		add(fail);
		fail.setVisible(false);
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
