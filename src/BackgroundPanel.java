import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;


//Modified from http://www.coderanch.com/t/660351/Wiki/Background-Image-JPanel
class BackgroundPanel extends JPanel{ 
	Image image;
	
	public BackgroundPanel(String s){
		setBackground(new Color((float) 1.0, (float) .9801921560314, (float) .9801921560314)); 
		this.setOpaque(true);
		if(s.equals("falcon")){
			try{
				image = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("falcon.jpg"), "falcon.jpg"));
			}catch (Exception e) { /*handled in paintComponent()*/ }
		}else if(s.equals("falcon2")){
			try{
				image = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("falcon2.jpg"), "falcon2.jpg"));
			}catch (Exception e) { /*handled in paintComponent()*/ }
		}else if(s.equals("falcon3")){
			try{
				image = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("falcon3.jpg"), "falcon3.jpg"));
			}catch (Exception e) { /*handled in paintComponent()*/ }
		}else{ //THIS CODE SHOULD NOT BE REACHED
			System.out.println("Error: invalid image for BackgroundPanel");
		}
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