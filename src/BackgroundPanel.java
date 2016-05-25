import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


//Modified from http://www.coderanch.com/t/660351/Wiki/Background-Image-JPanel
class BackgroundPanel extends JPanel{ 
	private String myImageName;
	private JLabel label;
	
	Image image;
	
	public BackgroundPanel(String s){
		this.setBackground(new Color((float) 1.0, (float) 1.0, (float) 1.0)); 
		this.setOpaque(true);
		
		if(s.equals("falcon")){
			try{
				myImageName="falcon";
				image = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("falcon.jpg"), "falcon.jpg"));
			}catch (Exception e) { }
		}else if(s.equals("falcon2")){
			try{
				myImageName="falcon2";
				image = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("falcon2.jpg"), "falcon2.jpg"));
			}catch (Exception e) { }
		}else if(s.equals("falcon3")){
			try{
				myImageName="falcon3";
				image = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("falcon3.jpg"), "falcon3.jpg"));
			}catch (Exception e) { }
		}else{ //THIS CODE SHOULD NOT BE REACHED
			System.out.println("Error: invalid image for BackgroundPanel");
		}
		label=new JLabel(new ImageIcon(image));
		
		Dimension d = new Dimension(GUI.guiWidth, 156);
		this.setPreferredSize(d);
		this.setMinimumSize(d);
		this.setMaximumSize(d);
		
		this.setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g); 
		if (image != null)
			g.drawImage(image, this.getWidth()/2-150,this.getHeight()/2-78,300,156,this);
		else
			System.out.println("Error");
	}
	
	private void setPic(Image image) {
		label.setIcon(new ImageIcon(image));
	}
	
	private void resetPic(){
		try {
			setPic(javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource(myImageName+".jpg"), myImageName+".jpg")));
		} catch (Exception e){ }
	}
	
	public void rainbowBackground(){ //For the lolz
		try {
			setPic(javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource(myImageName+"Rainbow.gif"), myImageName+"Rainbow.gif")));
		}catch(Exception e){ }
		resetPic();
	}
	
}