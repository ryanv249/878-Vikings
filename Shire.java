package vikingbattle;

import java.util.ArrayList;

public class Shire
{
	int location;
	String group, name, coast;
	Army defenders;
	ArrayList<Integer> adjacentShires;
	int reinforceHouse, reinforceThegn;
	boolean isMarsh, isCeolwulf;
	
	public Shire(int location, String group, String name, String coast, Army defenders, ArrayList<Integer> adjacentShires, int reinforceHouse, int reinforceThegn, boolean isMarsh, boolean isCeolwulf)
	{
		this.location = location;
		this.group = group;
		this.name = name;
		this.coast = coast;
		this.defenders = defenders;
		this.adjacentShires = adjacentShires;
		this.reinforceHouse = reinforceHouse;
		this.reinforceThegn = reinforceThegn;
		this.isMarsh = isMarsh;
		this.isCeolwulf = isCeolwulf;
	}
	
	public boolean isCity()
	{
		return !name.isEmpty();
	}
	
	public boolean isCoastal()
	{
		return !coast.isEmpty();
	}
	
	public boolean isHub()
	{
		return (reinforceHouse > 0 || reinforceThegn > 0);
	}
	
	public boolean isControlled()
	{
		return (isCity() && defenders.hasVikings());
	}
	
	public Battle inBattle()
	{
		for (int i = 0; i < Battle.battles.size(); i++)
		{
			if (Battle.battles.get(i).area.equals(this))
			{
				return Battle.battles.get(i);
			}
		}
		
		return null;
	}
	
	public String toString()
	{
		return "[" + location + "]: " + group + (isCity() ? (", " + name) : "") + (isCoastal() ? " (" + coast + "). " : ". ") + (inBattle() != null ? inBattle().toString().substring(6) : defenders) + " Adjacent Shires: " + adjacentShires + (isHub() ? (" RH: " + reinforceHouse + " RT: " + reinforceThegn) : "") + (isMarsh ? " <Marsh>" : "") + (isCeolwulf ? " <Ceolwulf>" : "");
	}
}