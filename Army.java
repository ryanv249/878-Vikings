package vikingbattle;

import java.util.ArrayList;

public class Army
{
	String nation;
	int numBerserk, numNorse, numHouse, numThegn, numFyrd;
	Leader leader;
	ArrayList<Leader> leaders = new ArrayList<Leader>();
	
	public Army(String nation, int troops1, int troops2, Leader... leader)
	{
		this.nation = nation;
		
		if (leader.length > 0)
		{
			this.leader = leader[0];
		}
		
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
	
	public Army(String nation, int troops1, int troops2, int troops3, Leader... leader)
	{
		this.nation = nation;
		
		if (leader.length > 0)
		{
			this.leader = leader[0];
		}
		
		if (nation.equals("Vikings"))
		{
			numBerserk = troops1;
			numNorse = troops2;
		}
		else
		{
			numHouse = troops1;
			numThegn = troops2;
			numFyrd = troops3;
		}
	}
	
	public Army(Army a)
	{
		nation = a.nation;
		leader = a.leader;
		leaders = a.leaders;
		
		if (nation.equals("Vikings"))
		{
			numBerserk = a.numBerserk;
			numNorse = a.numNorse;
		}
		else
		{
			numHouse = a.numHouse;
			numThegn = a.numThegn;
			numFyrd = a.numFyrd;
		}
	}
	
	public boolean hasVikings()
	{
		return (numBerserk > 0 || numNorse > 0);
	}
	
	public boolean hasEnglish()
	{
		return (numHouse > 0 || numThegn > 0 || numFyrd > 0);
	}
	
	public String leaderNames()
	{
		String names = leader.name;
		
		for (int i = 0; i < leaders.size(); i++)
		{
			if (1 == leaders.size())
			{
				names += (" and " + leaders.get(i).name);
			}
			else if (i + 1 == leaders.size())
			{
				names += (", and " + leaders.get(i).name);
			}
			else
			{
				names += (", " + leaders.get(i).name);
			}
		}
		
		return names;
	}
	
	public String units()
	{
		String units = "";
		
		if (nation.equals("Vikings"))
		{
			if (numBerserk > 0)
			{
				units += (numBerserk + " Berserkers");
			}
			if (numNorse > 0)
			{
				if (units.length() > 0)
				{
					units += ("and " + numNorse + " Norsemen");
				}
				else
				{
					units += (numNorse + " Norsemen");
				}
			}
		}
		else
		{
			if (numHouse > 0)
			{
				units += (numHouse + " Housecarls");
			}
			if (numThegn > 0)
			{
				if (units.length() > 0)
				{
					if (numFyrd > 0)
					{
						units += (", " + numThegn + " Thegns,");
					}
					else
					{
						units += (" and " + numThegn + " Thegns");
					}
				}
				else
				{
					units += (numThegn + " Thegns");
				}
			}
			if (numFyrd > 0)
			{
				if (units.length() > 0)
				{
					units += (" and " + numFyrd + " Fyrds");
				}
				else
				{
					units += (numFyrd + " Fyrds");
				}
			}
		}
		
		if (units.length() > 0)
		{
			units += ".";
		}
		else
		{
			units = "no units.";
		}
		
		return units;
	}
	
	public String toString()
	{
		if (nation.equals("Vikings"))
		{
			return "Viking Army: " + (leader != null ? ("{" + leaderNames() + "} ") : "") + units();
		}
		else
		{
			return "English Army: " + (leader != null ? ("{" + leader.name + "} ") : "") + units();
		}
	}
}