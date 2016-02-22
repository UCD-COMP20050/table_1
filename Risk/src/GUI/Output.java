package GUI;

/*
	Team Name: table_1
	Student Numbers: 14480278, 14461158, 14745991
	
	Create the GUI and add the various components, e.g. Map, Game Info, Text Field.
	We use a stack structure to store commands entered in the Text Field.
*/

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Input.Input;
import Listeners.TextActionListener;
import Player.Player;

import java.util.Stack;

public class Output extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField tf;
	private JPanel input_panel;
	private JPanel game_info_panel;
	private JScrollPane scrollablepanel;
	private JTextArea game_info = new JTextArea(5,109);
	private MapPanel map_panel;
	private int gameinfoheight = 100;
	private JLabel input = new JLabel("User input");
	private Dimension map_size;
	private Stack<String> inputHistory = new Stack<String>();
	
	public Output() {
		
		//Set map dimensions using current screensize
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int map_width = (int)(screensize.getWidth() - 100);
		int map_height = (int)(screensize.getHeight() - gameinfoheight - 160);
		this.map_size = new Dimension(map_width, map_height);
		
		this.setTitle("Risk");
	
		//create panels
		this.game_info_panel = new JPanel();
		this.scrollablepanel = new JScrollPane(game_info_panel);
		this.input_panel = new JPanel();
		this.map_panel = new MapPanel(map_size);
		
		//create text field to be added to user input panel.
		this.tf = new JTextField("");
		tf.setPreferredSize(new Dimension(400,24));
		
		//add the labels to the panels
		game_info_panel.add(game_info);
		input_panel.add(input);
		
		//make JTextArea uneditable and change background to opaque.
		game_info.setEditable(false);
		game_info.setOpaque(false);
		
		//add text field to user input panel.
		input_panel.add(tf);
		
		//create a new panel which consists of panels "game_info_panel" and "input_panel" on top of one another
		JSplitPane bottom_panels = new JSplitPane(JSplitPane.VERTICAL_SPLIT, 
		        true, scrollablepanel, input_panel);
		        
		//create a new panel which consists of "bottom_panels" beneath "map_panel"
		JSplitPane full_gui= new JSplitPane(JSplitPane.VERTICAL_SPLIT, 
		            true, map_panel, bottom_panels);
		
		//set dimensions for panels
		map_panel.setPreferredSize(map_size);
		scrollablepanel.setPreferredSize(new Dimension(map_width, gameinfoheight));
		
		//prevent panels from being resizeable
		bottom_panels.setEnabled(false);
		game_info_panel.setEnabled(false);
		full_gui.setEnabled(false);
	 	
	 	//add gui to jframe
		this.getContentPane().add(full_gui);
		
		//set what happens when "X" is clicked in right top corner.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		
		//ensures window can't be resized and dimensions aren't ruined.
		this.setResizable(false);
		
		//make gui visible
		this.setVisible(true);
 
		// Text field stuff
		tf.addActionListener(new TextActionListener(this));	
	}
	
	//takes user input as argument and adds it to the stack "inputHistory"
	public void addInputToHistory(String input) {
		inputHistory.add(input);
	}
	
	public boolean isInputStackEmpty() {
		return inputHistory.isEmpty();
	}
	
	public void popFromInputStack() {
		inputHistory.pop();
	}
	
	public void updateGameInfoPanel(String updatedText) {
		game_info.append("\n" + updatedText);
	}
	
	//used to make TextField accessible from Listener class.
	public JTextField getTextField(){
		return this.tf;
	}
	
	//change Armies displayed on a particular country
	public void setArmies(Player player, int country_index, Integer armysize){
		for (Country country : map_panel.getCountries()){
			if (country.getID() == country_index){
				this.map_panel.setArmies(country, player, armysize);
			}
		}	
	}
	
}
