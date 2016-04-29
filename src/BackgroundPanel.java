import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;


//Modified from http://www.coderanch.com/t/660351/Wiki/Background-Image-JPanel
class BackgroundPanel extends JPanel{ 
	Image image;
	
	public BackgroundPanel(){
		
		try{
			image = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("falcon.jpg"), "falcon.jpg"));
		}catch (Exception e) { /*handled in paintComponent()*/ }
	}
 
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g); 
		if (image != null)
			g.drawImage(image, this.getWidth()/2-300,this.getHeight()/2-156,600,312,this);
		else
			System.out.println("Error");
	}
}