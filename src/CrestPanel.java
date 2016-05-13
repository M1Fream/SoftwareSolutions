import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CrestPanel extends JPanel{
	private JLabel label;
	
	Image image;
	
	public CrestPanel(){
		this.setBackground(new Color((float) 1.0, (float) 1.0, (float) 1.0)); 
		this.setOpaque(true);
		
		try{
			image = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("crest.jpg"), "crest.jpg"));
		}catch(Exception e){ }
	
		label=new JLabel(new ImageIcon(image));
		
		Dimension d = new Dimension(230, 220);
		this.setPreferredSize(d);
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
