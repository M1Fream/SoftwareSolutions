import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


//Modified from http://www.coderanch.com/t/660351/Wiki/Background-Image-JPanel
class BackgroundPanel extends JPanel{ 
	private String myImageName;
	//private Timer timer;
	private JLabel label;
	
	Image image;
	
	public BackgroundPanel(String s){
		setBackground(new Color((float) 1.0, (float) .9801921560314, (float) .9801921560314)); 
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
		/*
		timer = new Timer(1000,new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setColor("red");
			}
		});
		timer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setColor("orange");
			}
		});
		timer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setColor("yellow");
			}
		});
		timer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setColor("green");
			}
		});
		timer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setColor("blue");
			}
		});
		timer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setColor("purple");
			}
		});
		timer.setRepeats(false);*/
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g); 
		if (image != null)
			g.drawImage(image, this.getWidth()/2-300,this.getHeight()/2-156,600,312,this);
		else
			System.out.println("Error");
	}
	
	private void setPic(Image image) {
	    /*this.image = image;
	    this.repaint();*/
		label.setIcon(new ImageIcon(image));
	}
	
	private void setColor(String color){
		color = color.substring(0, 1).toUpperCase()+color.substring(1).toLowerCase(); //Ensure color name is formatted properly
		try {
			setPic(javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource(myImageName+color+".jpg"), myImageName+color+".jpg")));
		} catch (Exception e){ }
	}
	
	private void resetPic(){
		try {
			setPic(javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource(myImageName+".jpg"), myImageName+".jpg")));
		} catch (Exception e){ }
	}
	
	public void rainbowBackground(){ //For the lolz
		setColor("red");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setColor("orange");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setColor("yellow");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setColor("green");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setColor("blue");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setColor("purple");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//timer.start();
		resetPic();
	}
	
}