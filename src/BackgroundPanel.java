import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


//Modified from http://www.coderanch.com/t/660351/Wiki/Background-Image-JPanel
class BackgroundPanel extends JPanel{ 
	private String myImageName; //Name of jpg used for background
	private JLabel label; //Label to hold image
	
	Image image; // This image is an image
	
	public BackgroundPanel(String s){
		//Set background color and opacity
		this.setBackground(new Color((float) 1.0, (float) 1.0, (float) 1.0)); 
		this.setOpaque(true);
		
		//Determine which Falcon jpg to use based on String from constructor
		if(s.equals("falcon")){ // try a bunch of images because why not
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
		//Set label to contain image
		label=new JLabel(new ImageIcon(image));
		
		//Constrain dimensions of this
		Dimension d = new Dimension(GUI.guiWidth, 156);
		this.setPreferredSize(d);
		this.setMinimumSize(d);
		this.setMaximumSize(d);
		
		this.setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g){ // protected is magic
		super.paintComponent(g); // it was hard to write, it should be hard to understand
		if (image != null)
			g.drawImage(image, this.getWidth()/2-150,this.getHeight()/2-78,300,156,this);  //actually draw the image
		else
			System.out.println("Error"); //oh no
	}
	
	//Used to change background picture
	private void setPic(Image image) {
		label.setIcon(new ImageIcon(image));
	}
	
	//Reset picture based on default specified in constructor
	private void resetPic(){
		try {
			setPic(javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource(myImageName+".jpg"), myImageName+".jpg")));
		} catch (Exception e){ } //catch it and do... NOTHING! YAY!
	}
	
	public void rainbowBackground(){ //Never got this working
		try { // don't worry we (probably) didn't use it
			setPic(javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource(myImageName+"Rainbow.gif"), myImageName+"Rainbow.gif")));
		}catch(Exception e){ }
		resetPic();
	}
	
}