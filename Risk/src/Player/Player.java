package Player;

import java.awt.Color;
import java.util.EmptyStackException;

import GUI.Output;

/*
	Make sure that the team names and student numbers are included as comments in the header of all
	source files and the documentation file.

  
	Class stores information about the players.
	i.e. Name, Colour. 
*/

public class Player {

	private String playerName = "";
	private Color playerColour = null;
	
	public void setPlayerName(Output gui) {
		gui.updateGameInfoPanel("Enter player name:");
		
		while (gui.isInputStackEmpty()) {
			
			try {
				//playerName = gui.getPreviousInputFromStack();
				playerName = gui.getTextField().getText();
			}
			
			catch (EmptyStackException e) { }
			
		}
		
		gui.popInputStack();
		gui.updateGameInfoPanel("Player " + playerName + " has joined the game");
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerColour(Color _playerColour) {
		playerColour = _playerColour;
	}
	
	public Color getPlayerColour() {
		return playerColour;
	}
	
}

