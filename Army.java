package game;

public class Army {
	
	String nation, leader;
	int numBerserk, numNorse, numHouse, numThegn, numFyrd;
	
	public Army(String faction, int numUnit1, int numUnit2) {
		nation = faction;
		
		if(nation.equals("Vikings")) {
			numBerserk = numUnit1;
			numNorse = numUnit2;
		}
		else if(nation.equals("English")) {
			numHouse = numUnit1;
			numThegn = numUnit2;
		}
	}
	
	public String toString() {
		String returnMe = new String();
		if(nation.equals("Vikings")) {
			returnMe = "Nation: " + nation + "\n" + "Leader: " + leader + "\n" + "# Berserkers: " + numBerserk + "\n" + "# Norsemen: " + numNorse;
		}
		if(nation.equals("English")) {
			returnMe = "Nation: " + nation + "\n" + "Leader: " + leader + "\n" +"# Housecarls: " + numHouse + "\n" + "# Thegns: " + numThegn;
		}
		return returnMe;
	}

}
