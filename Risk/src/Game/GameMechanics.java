package Game;

/*
	Team Name: table_1
	Student Numbers: 14480278, 14461158.
	
	GameMechanics class: handles most of the logic of a game of RISK.
*/

import java.util.ArrayList;

import javax.swing.JTextField;

import Deck.Deck;
import Dice.Die;
import GUI.MapConstants;
import GUI.Output;
import Input.Input;
import Turns.Turns;

public class GameMechanics implements Main.GameMechanics {
	
	private JTextField tf;
	private Output output;
	private Input input;
	private ArrayList<Country> countrylist;
	private ArrayList<Army> armylist;
	private ArrayList<Player> playerlist;
	private Deck deck;
	private Die die;
	private Reinforce reinforcemechanics;
	private Integer initialhumanarmysize = 36;
	private Integer initialbotarmysize = 24;
	
	public GameMechanics(){
		this.tf = new JTextField();
		this.armylist = new ArrayList<Army>();
	}
	
	@Override
	public JTextField getInputField(){
		return tf;
	}
	
	@Override
	public void setOutput(Output output){
		this.output = output;
	}
	
	@Override
	public Output getOutput(){
		return this.output;
	}
	
	@Override
	public void setInput(Input input){
		this.input = input;
	}
	
	@Override
	public Input getInput(){
		return this.input;
	}
	
	@Override
	public void setCountryList(){
		
		this.countrylist = new ArrayList<Country>();
		
		for (int i = 0; i < MapConstants.COUNTRY_COORD.length; i++){
			countrylist.add(new Country(i, countrylist, output.getPanelSize()));
		}
		
		for (int i = 0; i < MapConstants.COUNTRY_COORD.length; i++){
			countrylist.get(i).setAdjacentCountries(MapConstants.ADJACENT[i]);
		}
	}
	
	@Override
	public ArrayList<Country> getCountryList(){
		return this.countrylist;
	}
	
	@Override
	public void setArmyList(Player player, Country country, Integer armysize){
		
		boolean found = false;
		int i = 0;
		
		while (!found && i < armylist.size()) {
			if (armylist.get(i).getCountry() == country) {
				found = true;
				
				if (armylist.get(i).getPlayer() != player) {
					armylist.get(i).getPlayer().removePlacedArmy(armylist.get(i));
					armylist.get(i).setPlayer(player);
					player.addPlacedArmies(armylist.get(i));
				}
				
				armylist.get(i).setSize(armysize);
				output.updateMapPanel();
				player.setAvailableArmies(player.getAvailableArmies() - armysize);

			} else {
				i++;
			}
		}
		
		if (!found) {
			Army newarmy = new Army(armysize, player, country);
			armylist.add(newarmy);
			output.updateMapPanel();
			player.addPlacedArmies(newarmy);
			player.setAvailableArmies(player.getAvailableArmies() - armysize);
		}
	}
	
	@Override
	public ArrayList<Army> getArmyList(){
		return this.armylist;
	}
	
	@Override
	public void setPlayerList(ArrayList<Player> playerlist){
		this.playerlist = playerlist;
	}
	
	@Override
	public ArrayList<Player> getPlayerList(){
		return this.playerlist;
	}
	
	@Override
	public void setDeck(){
		this.deck = new Deck();
		this.deck.setCountryList(this.countrylist);
	}
	
	@Override
	public void setDice(){
		this.die = new Die();
	}
	
	@Override
	public Die getDice(){
		return this.die;
	}
	
	@Override
	public Integer getInitialHumanArmySize(){
		return this.initialhumanarmysize;
	}
	
	@Override
	public Integer getInitialBotArmySize(){
		return this.initialbotarmysize;
	}
	
	@Override
	public void initialiseGameMap(){
		
		while (!deck.isEmpty()) {
			
			int firstplayer = this.decideFirstPlayer();
			
			// swap player1 and player2's positions in the list so that player2 goes first.
//			if(firstplayer == 1){
//				Collections.swap(playerlist, 0, 1);
//			}
			
			output.updateGameInfoPanel(playerlist.get(0).getPlayerName() + " draws first!");
			
			for (Player player : playerlist) {
				
				if (player.getHuman()){
					output.updateGameInfoPanel("\n" + player.getPlayerName() + " enter 'draw' to draw a card");
					
					while (!input.getInputCommand().equals("draw")) {
						output.updateGameInfoPanel("That's not a command, " + player.getPlayerName() + " try using 'draw'");
					}
					
					for (int i = 0; i < 9; i++) {
						Country card = deck.getCountryCard();
						this.setArmyList(player, card, 1);
						output.updateGameInfoPanel(player.getPlayerName() + " drew territory card:  " + card.getName().toUpperCase());
					}
							
				}
				
				else {
					output.updateGameInfoPanel("\nDrawing cards for " + player.getPlayerName());
					
					for (int i = 0; i < 6; i++) {
						Country card = deck.getCountryCard();
						this.setArmyList(player, card, 1);
						output.updateGameInfoPanel(player.getPlayerName() + " drew territory card:  " + card.getName().toUpperCase());
					}
				}
							
			}
		}
	}
	
	/* This method handles the turn based logic for the two players */
	public void turns() {
		Turns gameTurns = new Turns(this.playerlist, this);
		
		//gameTurns.setTurnOrder();
		
		int i=0;
		for(i=0;i<gameTurns.getPlayerList().size();i++){
			gameTurns.placeReinforcements(gameTurns.getPlayerList().get(i));
		}
		output.updateGameInfoPanel("Turn sequence has ended!");
	}
	
	
	@Override
	public void setReinforceMechanics() {
		this.reinforcemechanics = new Reinforce(this);
	}
	
	@Override
	public void reinforce() {
		Integer players2reinforce;
		Integer index = this.decideFirstPlayer();
		
		this.reinforcemechanics.setReinforcements(playerlist.get(index));
		
		do{
			players2reinforce = 6;
			
			for (Player player : playerlist){
				
				if (player.getAvailableArmies() > 0) {
					this.reinforcemechanics.setReinforcements(player);
				} 
				
				else {
					players2reinforce--;
				}
			}
			
		} while(players2reinforce > 0);
	}
	
	public int decideFirstPlayer(){
		
		boolean draw;
		int index = 0;
		int player1die = 0;
		int player2die = 0;
		
		do{
			
			for (int i = 0; i < 2; i++){
				
				this.getOutput().updateGameInfoPanel("\n" + playerlist.get(i).getPlayerName() + " type 'roll' to roll the dice");
				
				while (!input.getInputCommand().equals("roll")) {
					output.updateGameInfoPanel("That's not a command, try using 'roll'");
				}
				
				die.roll();
				
				if(i==0){	
					player1die = die.getFace();
				}
				
				else {
					player2die = die.getFace();
				}
				
				this.getOutput().updateGameInfoPanel(playerlist.get(i).getPlayerName() + " rolled a " + String.valueOf(die.getFace()));
			}
			
			if (player1die==player2die){
				draw = true;
				this.getOutput().updateGameInfoPanel("It's a draw! Let's roll again!");
			}
			
			else if (player1die > player2die){
				draw = false;
				index = 0;
				this.getOutput().updateGameInfoPanel("\n" + playerlist.get(index).getPlayerName() + " rolled the highest!");
			}
			
			else {
				draw = false;
				index = 1;
				this.getOutput().updateGameInfoPanel("\n" + playerlist.get(index).getPlayerName() + " rolled the highest!");
			}	
			
		} while (draw);
		
		return index;
	}
	
}
