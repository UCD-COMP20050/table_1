package GUI;

/*
	Team Name: table_1
	Student Numbers: 14480278, 14461158.
	
	The class that contains the map components
*/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
//import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.SwingUtilities;
import javax.swing.SwingUtilities;

public class MapPanel extends JPanel {
	public MapPanel(Output output){
		this.output = output;
		MapConstants.setScaling(output.getPanelSize().getHeight() / MapConstants.FRAME_HEIGHT);		
		this.setPreferredSize(output.getPanelSize());
		output.setLinks(new Links(output));
		this.add(output.getLinks());	
	}
	@Override
	public void paintComponent(Graphics g) {
		//get image locally
		URL url = MapPanel.class.getResource("/worldmap.png");
		
		try {
			image = ImageIO.read(url.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		super.paintComponent(g);
		
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		Graphics2D gfx2d = (Graphics2D)g;
		gfx2d.fill(new Rectangle(0, 0, (int)output.getPanelSize().getWidth()+60, (int)output.getPanelSize().getHeight()));	
		Image scaled = image.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
		g.drawImage(scaled, 0, 0, null);
		gfx2d.setColor(new Color(0,191,255));
	}
	
	private Output output;
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
}
