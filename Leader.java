package vikingbattle;

public class Leader
{
	String name, description;
	int numNorse, numBerserk, numHouse, numThegn;
	
	public Leader(String name, String description, int troops1, int troops2)
	{
		this.name = name;
		this.description = description;
		
		if (!name.equals("Alfred's Army"))
		{
			numNorse = troops1;
			numBerserk = troops2;
		}
		else
		{
			numHouse = troops1;
			numThegn = troops2;
		}
	}
	
	public Leader(Leader l)
	{
		name = l.name;
		description = l.description;
		
		if (!name.equals("Alfred's Army"))
		{
			numNorse = l.numNorse;
			numBerserk = l.numBerserk;
		}
		else
		{
			numHouse = l.numHouse;
			numThegn = l.numThegn;
		}
	}
	
	public Army toArmy()
	{
		if (!name.equals("Alfred's Army"))
		{
			return new Army("Vikings", numBerserk, numNorse, new Leader(name, description, numNorse, numBerserk));
		}
		else
		{
			return new Army("English", numHouse, numThegn, new Leader(name, description, numHouse, numThegn));
		}
	}
	
	public String toString()
	{
		if (!name.equals("Alfred's Army"))
		{
			return "[" + name + "]: " + description + " " + numBerserk + " Berserkers, " + numNorse + " Norsemen";
		}
		else
		{
			return "[" + name + "]: " + description + " " + numHouse + " Housecarls, " + numThegn + " Thegns";
		}
	}
}