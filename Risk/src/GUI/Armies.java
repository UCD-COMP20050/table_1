package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Armies extends JComponent {
	
	public Armies(Dimension panel_size, ArrayList<Army> armies){
		this.panel_size = panel_size;
		this.setPreferredSize(this.panel_size);
		this.armies = armies;
	}
	@Override
	public void paintComponent(Graphics g){
		this.drawArmies(this.initialiseGFX2D(g));
	}
	private Graphics2D initialiseGFX2D(Graphics g){	
		super.paintComponent(g);
		Graphics2D gfx2d = (Graphics2D)g;	
		gfx2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);	
		return gfx2d;
	}	
	private void drawArmies(Graphics2D gfx2d){
		for (Army army : armies){
			Integer x = army.getCountry().getXCoords();
			Integer y = army.getCountry().getYCoords();
			String size = String.valueOf(army.getSize());
			Integer diameter = (army.getCountry().getRadius() / 8) * army.getSize();
			
			Ellipse2D.Double countryicon = new Ellipse2D.Double(
					x, y, diameter, diameter);
			gfx2d.setPaint(army.getPlayer().getPlayerColour());
			gfx2d.fill(countryicon);
			
			gfx2d.setPaint(Color.black);
			gfx2d.setFont(army.getCountry().getFont());
			gfx2d.drawString(size, x, y);
		}
	}
	private ArrayList<Army> armies;
	private Dimension panel_size;
	private static final long serialVersionUID = 1L;
}
