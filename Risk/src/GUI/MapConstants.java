package GUI;

import java.awt.Color;

/*
	Team Name: table_1
	Student Numbers: 14480278, 14461158.
	
	The class that contains the map constants
*/

public class MapConstants {
	
	public static final int NUM_PLAYERS = 2;
	public static final int NUM_NEUTRALS = 4;
	public static final int NUM_PLAYERS_PLUS_NEUTRALS = NUM_PLAYERS + NUM_NEUTRALS;
	public static final int NUM_COUNTRIES = 42;
	public static final int INIT_COUNTRIES_PLAYER = 9;
	public static final int INIT_COUNTRIES_NEUTRAL = 6;
	public static final int INIT_UNITS_PLAYER = 36;
	public static final int INIT_UNITS_NEUTRAL = 24;
	
	public static final String[] COUNTRY_NAMES = {
		"Ontario","Quebec","NW Territory","Alberta","Greenland","E United States","W United States","Central America","Alaska",
		"Great Britain","W Europe","S Europe","Ukraine","N Europe","Iceland","Scandinavia",
		"Afghanistan","India","Middle East","Japan","Ural","Yakutsk","Kamchatka","Siam","Irkutsk","Siberia","Mongolia","China",
		"E Australia","New Guinea","W Australia","Indonesia",
		"Venezuela","Peru","Brazil","Argentina",
		"Congo","N Africa","S Africa","Egypt","E Africa","Madagascar" };  // for reference
	 
	// Parallel array, works with COUNTRY_NAMES constant for adding insignies to Cards.
	public static final String[] COUNTRY_INSIGNIAS = {
		"Cavalry", "Artillery", "Artillery", "Infantry", "Cavalry", "Artillery", "Infantry", "Cavalry", "Infantry",
		"Cavalry", "Infantry", "Cavalry", "Artillery", "Cavalry", "Infantry", "Artillery", "Infantry", "Infantry",
		"Artillery", "Infantry", "Cavalry", "Cavalry", "Cavalry", "Artillery", "Infantry", "Artillery", "Artillery",
		"Cavalry", "Infantry", "Cavalry", "Artillery", "Cavalry", "Artillery", "Cavalry", "Artillery", "Infantry",
		"Cavalry", "Infantry", "Artillery", "Infantry", "Artillery", "Infantry" };
	
	public static final int[][] ADJACENT = { 
		{4,1,5,6,3,2},    // 0
		{4,5,0},
		{4,0,3,8},
		{2,0,6,8},
		{14,1,0,2},
		{0,1,7,6}, 
		{3,0,5,7},
		{6,5,32},
		{2,3,22},
		{14,15,13,10},    
		{9,13,11,37},     // 10
		{13,12,18,39,10},
		{20,16,18,11,13,15},
		{15,12,11,10,9},
		{15,9,4},
		{12,13,14},
		{20,27,17,18,12}, 
		{16,27,23,18},
		{12,16,17,40,39,11},
		{26,22},
		{25,27,16,12},    // 20
		{22,24,25},        
		{8,19,26,24,21},
		{27,31,17},
		{21,22,26,25},
		{21,24,26,27,20},
		{24,22,19,27,25},
		{26,23,17,16,20,25},
		{29,30},          
		{28,30,31},
		{29,28,31},      // 30
		{23,29,30},
		{7,34,33},       
		{32,34,35},
		{32,37,35,33},
		{33,34},
		{37,40,38},      
		{10,11,39,40,36,34},
		{36,40,41},
		{11,18,40,37},
		{39,18,41,38,36,37},  //40
		{38,40}
	};
	
	public static final int NUM_CONTINENTS = 6;
	public static final String[] CONTINENT_NAMES = {"N America","Europe","Asia","Australia","S America","Africa"};  // for reference 
	public static final int[] CONTINENT_IDS = {0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,5,5};
	public static final int[] CONTINENT_VALUES = {5,5,7,2,2,3};
	public static final int FRAME_WIDTH = 1000;    // must be even
	public static final int FRAME_HEIGHT = 600;
	
	public static final int[][] COUNTRY_COORD = {
		{151,150},     // 0
		{215,161},
		{146,86},
		{80,144}, 
		{350,50},
		{175,215},
		{80,210}, 
		{155,279},
		{45,89},
		{435,120}, 
		{410,170},      // 10
		{440,200},
		{540,140},
		{475,145},
		{395,90}, 
		{510,75},
		{650,200},
		{700,285}, 
		{570,230}, 
		{870,190},
		{625,110},      // 20
		{763,70},
		{850,85},
		{800,300}, 
		{750,140},
		{695,65},
		{800,160}, 
		{760,220}, 
		{915,460}, 
		{915,360},
		{835,460},       // 30
		{820,370},
		{213,310}, 
		{221,410},
		{300,375}, 
		{233,510},
		{500,360},
		{410,290},
		{510,470},
		{505,240}, 
		{580,330},        // 40
		{595,430}
	};
	
	public static final int[][] CONT_COLORS = {
		{107,142,35},
		{154,205,50},
		{173,255,47},
		{0,255,127},
		{85,107,47},
		{34,139,34}
	};
	
	public static final String[] CONT_FONTS = {
		"Baskerville Old Face",
		"Bookman Old Style",
		"Stencil",
		"Arial",
		"Bauhaus 93",
		"Papyrus"
	};
	
	public static double SCALING_CONSTANT;
	
	public static void setScaling(double scaling){
		SCALING_CONSTANT = scaling;
	}
	
	public static final String[] PLAYER_NAMES = {
		"Player 1",
		"Player 2",
		"Neutral 1",
		"Neutral 2",
		"Neutral 3",
		"Neutral 4"
	};
	
	public static final Color[] PLAYER_COLORS = {
		Color.BLUE,
		Color.RED,
		Color.GRAY.darker(),
		Color.YELLOW,
		Color.CYAN,
		Color.MAGENTA
	};
	
}


