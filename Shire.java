package vikingbattle;

import java.util.ArrayList;

public class Shire
{
	int location;
	String group, name, coast;
	Army defenders;
	ArrayList<Integer> adjacentShires;
	int reinforceHouse, reinforceThegn;
	boolean isControlled, isMarsh, isCeolwulf;
	
	public Shire(int location, String group, String name, String coast, Army defenders, ArrayList<Integer> adjacentShires, int reinforceHouse, int reinforceThegn, boolean isControlled, boolean isMarsh, boolean isCeolwulf)
	{
		this.location = location;
		this.group = group;
		this.name = name;
		this.coast = coast;
		this.defenders = defenders;
		this.adjacentShires = adjacentShires;
		this.reinforceHouse = reinforceHouse;
		this.reinforceThegn = reinforceThegn;
		this.isControlled = isControlled;
		this.isMarsh = isMarsh;
		this.isCeolwulf = isCeolwulf;
	}
}