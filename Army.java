package vikingbattle;

public class Army
{
	String nation;
	Leader leader;
	int numBerserk, numNorse, numHouse, numThegn, numFyrd;
	
	public Army(String newNation, int troops1, int troops2)
	{
		nation = newNation;
		
		if (nation.equals("Vikings"))
		{
			numBerserk = troops1;
			numNorse = troops2;
		}
		else
		{
			numHouse = troops1;
			numThegn = troops2;
		}
	}
	public Army(Army a)
	{
		nation = a.nation;
		
		if (nation.equals("Vikings"))
		{
			numBerserk = a.numBerserk;
			numNorse = a.numNorse;
		}
		else
		{
			numHouse = a.numHouse;
			numThegn = a.numThegn;
		}
	}
	
	public String toString()
	{
		if (nation.equals("Vikings"))
		{
			return "Nation: " + nation + "\nLeader: " + (leader != null ? leader.name : "none") + "\nBerserkers: " + numBerserk + "\nNorsemen: " + numNorse;
		}
		else
		{
			return "Nation: " + nation + "\nLeader: " + (leader != null ? leader.name : "none") + "\nHouseCarl: " + numHouse + "\nThegn: " + numThegn;
		}
	}
}
