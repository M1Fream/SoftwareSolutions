import java.awt.*;
import javax.swing.*;


public class GUI {
	
	public static void main(String[] args){
		JFrame frame = new JFrame("Prom Sign Out"); //Instantiate frame for GUI
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);//Default is to do nothing on close (WindowListener handles closing and asks for password)
	
		frame.setLayout(new BorderLayout()); //Set frame's layout
		
		PromPanel promPanel = new PromPanel(); 
		frame.add(promPanel,BorderLayout.CENTER); //Add PromPanel to center of frame
		
		frame.setSize(1650, 1080); //Default frame size
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Sets frame to max size
		frame.setVisible(true); //Makes frame actually visible
	}
	
	
}
