package vikingbattle;

import java.util.ArrayList;
import java.util.Arrays;

public class Deck
{
	static ArrayList<Integer> fyrdCards = new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5));
	static ArrayList<Leader> leaderCards = new ArrayList<Leader>(Arrays.asList(new Leader("Halfdan's Great Heathen Host", "Invade North Sea coast.", 17, 8),
																			   new Leader("Ivar the Boneless's Army", "Invade North Sea coast.", 14, 6),
																			   new Leader("Björn Ironside's Army", "Invade North Sea coast.", 11, 4),
																			   new Leader("Reinforcements", "No invasion this round. Place as reinforcments.", 9, 2),
																			   new Leader("Guthrum's Great Summer Army", "Invade any coast.", 17, 8),
																			   new Leader("Ubbe Ragnarsson's Army", "Invade any coast.", 14, 6),
																			   new Leader("Rollo's Army", "Invade any coast.", 11, 4)));
	static ArrayList<Berserker> berserkerCards = new ArrayList<Berserker>(Arrays.asList(new Berserker("Treaty of Wedmore - Check for Game End on Round 5 or later.", "Treaty", 2, 4),
																						new Berserker("Movement", "Berserker Movement", 2, 4),
																						new Berserker("Movement", "Berserker Movement", 2, 4),
																						new Berserker("Movement", "Berserker Movement", 2, 4),
																						new Berserker("Movement", "Berserker Movement", 2, 3),
																						new Berserker("Movement", "Berserker Movement", 2, 3),
																						new Berserker("Movement", "Berserker Movement", 2, 2),
																						new Berserker("Increased Morale - Replace up to two Norsemen with Berserker Units in any one Viking army.", "Berserker Reinforcements Phase", 0, 0),
																						new Berserker("Viking Terror - For this Battle, the English may not draw a Fyrd Card.", "Berserker Battle", 0, 0),
																						new Berserker("Surprise Attack - Before this Battle, the Viking army resolves one Battle Dice roll.", "Berserker Battle", 0, 0),
																						new Berserker("Ceolwulf - If any Ceolwulf Shire is Viking-controlled, eliminate any English Units in another Ceolwulf Shire and move up to two Viking Units on the map to the emptied Shire.", "Berserker Reinforcements Phase", 0, 0),
																						new Berserker("Viking Fort - For this Battle, the Viking army ignores one 'Hit' result from each English Battle Dice roll.", "English Battle", 0, 0),
																						new Berserker("Viking Ships - One Viking Army may move from one Coastal Shire to another Shire on the same Sea's coast at the cost of one move.", "Berserker Movement", 0, 0),
																						new Berserker("Increased Morale - Replace up to two Norsemen with Berserker Units in any one Viking army.", "Berserker Reinforcements Phase", 0, 0),
																						new Berserker("Viking Terror - For this Battle, the English may not draw a Fyrd Card.", "Berserker Battle", 0, 0),
																						new Berserker("Surprise Attack - Before this Battle, the Viking army resolves one Battle Dice roll.", "Berserker Battle", 0, 0),
																						new Berserker("Ceolwulf - If any Ceolwulf Shire is Viking-controlled, eliminate any English Units in another Ceolwulf Shire and move up to two Viking Units on the map to the emptied Shire.", "Berserker Reinforcements Phase", 0, 0),
																						new Berserker("Viking Fort - For this Battle, the Viking army ignores one 'Hit' result from each English Battle Dice roll.", "English Battle", 0, 0),
																						new Berserker("Viking Ships - One Viking Army may move from one Coastal Shire to another Shire on the same Sea's coast at the cost of one move.", "Berserker Movement", 0, 0)));
	
	public static <T> T pickCard(ArrayList<T> cards)
	{
		return cards.remove((int)(Math.random() * cards.size()));
	}
}