package game;

import java.util.ArrayList;

public class Shire {
	int location, reinforceHouse, reinforceThegn;
	String name, group, coast;
	boolean isMarsh, isHub, isCeolwulf, isControlled;
	Army defenders;
	ArrayList<Integer> AdjacentShires = new ArrayList<Integer>();
	
	public Shire(int LOCATION, String GROUP, String NAME, String COAST, Army DEFENDERS, ArrayList<Integer> NEARSHIRES, int NUMREINFORCEHOUSE, int NUMREINFORCETHEGN, boolean ISCONTROLLED, boolean ISMARSH, boolean ISCEOLWULF) {
		location = LOCATION;
		group = GROUP;
		defenders = DEFENDERS;
		isMarsh = ISMARSH;
		isControlled = ISCONTROLLED;
		AdjacentShires = NEARSHIRES;
		group = GROUP;
		name = NAME;
		coast = COAST;
		isCeolwulf = ISCEOLWULF;
		reinforceHouse = NUMREINFORCEHOUSE;
		reinforceThegn = NUMREINFORCETHEGN;
	}
	
}
