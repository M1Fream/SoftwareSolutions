import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** container for image
 * does basically nothing else*/
public class CrestPanel extends JPanel{ // Just look at button panel, it has the same code
	private JLabel label; //Label to hold image
	
	Image image; // Another image named image, YAY
	
	public CrestPanel(){
		//Set background color and opacity
		this.setBackground(Globals.white); 
		this.setOpaque(true);
		
		//Set image to crest.jpg 
		try{
			image = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("crest.jpg"), "crest.jpg"));
		}catch(Exception e){ }
	
		//Set label to contain image
		label=new JLabel(new ImageIcon(image));
		
		//Constrain dimensions of this
		Dimension d = new Dimension(230, 220);
		this.setPreferredSize(d); //This again
		this.setMinimumSize(d);
		this.setMaximumSize(d);
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g); 
		if (image != null)
			g.drawImage(image, this.getWidth()/2-115,this.getHeight()/2-110,230,220,this);
		else
			System.out.println("Error");
	}
}