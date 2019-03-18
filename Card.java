package game;

public class Card {
	String team, type, description, phase;
	int numArmies, numSpaces;
	
	public Card(String TEAM, String TYPE, String DES, String PHASE, int NUMARM, int NUMSPA) {
		team = TEAM;
		type = TYPE;
		description = DES;
		phase = PHASE;
		numArmies = NUMARM;
		numSpaces = NUMSPA;
	}
}
