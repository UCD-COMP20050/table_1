package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

import Player.Player;

public class MapPanel extends JPanel {
	public MapPanel(Dimension panel_size){
		this.panel_size = panel_size;
		MapConstants.setScaling(panel_size.getHeight() / MapConstants.FRAME_HEIGHT);		
		this.setPreferredSize(panel_size);
		this.countries = new ArrayList<Country>();
		for (int i = 0; i < MapConstants.COUNTRY_COORD.length; i++){
			countries.add(new Country(i, countries, panel_size));
		}
		for (int i = 0; i < MapConstants.COUNTRY_COORD.length; i++){
			countries.get(i).setAdjacentCountries(MapConstants.ADJACENT[i]);
		}
		this.armies = new ArrayList<Army>();
		this.linkscomponent = new Links(panel_size, countries, armies);
		this.add(linkscomponent);	
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D gfx2d = (Graphics2D)g;
		gfx2d.setColor(new Color(0,191,255));
		gfx2d.fill(new Rectangle(0, 0, (int)panel_size.getWidth(), (int)panel_size.getHeight()));	
	}
	public ArrayList<Army> getArmies(){
		return armies;
	}
	public ArrayList<Country> getCountries(){
		return countries;
	}
	public void setArmies(Country country, Player player, Integer armysize){
		boolean found = false;
		int i = 0;
		while (!found && i < armies.size()){
			if (armies.get(i).getCountry() == country){
				found = true;
				armies.get(i).setPlayer(player);
				armies.get(i).setSize(armysize);
			}
			else {
				i++;
			}
		}
		if (!found){
			armies.add(new Army(armysize, player, country));
		}
	}	
	private ArrayList<Army> armies;
	private ArrayList<Country> countries;
	private Links linkscomponent;
	private Dimension panel_size;
	private static final long serialVersionUID = 1L;
}