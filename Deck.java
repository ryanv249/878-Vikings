package game;

import java.util.ArrayList;
import java.util.Arrays;

public class Deck {
    //40 norse
	//20 house
	//20 berserkers
	//40 thegns
	static ArrayList<Integer> fyrdCards = new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5));
	static ArrayList<Leader> leaderCards = new ArrayList<Leader>(Arrays.asList(			
			  new Leader("Halfdan's Great Heathen Host", "Invade North Sea coast.", 17, 8),
			  new Leader("Ivar the Boneless's Army", "Invade North Sea coast.", 14, 6),
			  new Leader("Björn Ironside's Army", "Invade North Sea coast.", 11, 4),
			  new Leader("Reinforcements", "No invasion this round. Place as reinforcments.", 9, 2),
			  new Leader("Guthrum's Great Summer Army", "Invade any coast.", 17, 8),
			  new Leader("Ubbe Ragnarsson's Army", "Invade any coast.", 14, 6),
			  new Leader("Rollo's Army", "Invade any coast.", 11, 4)
			  ));
		

	
}
