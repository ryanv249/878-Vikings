package vikingbattle;

public class Card
{
	int number;
	String team, type, description, phase;
	int numArmies, numMoves;
	
	public Card(int number, String team, String type, String description, String phase, int numArmies, int numMoves)
	{
		this.number = number;
		this.team = team;
		this.type = type;
		this.description = description;
		this.phase = phase;
		this.numArmies = numArmies;
		this.numMoves = numMoves;
	}
	
	public String name()
	{
		return description.substring(0, description.indexOf("-") - 1);
	}
	
	public String toString()
	{
		return "[#" + number + "]: " + description + " Phase: " + phase + ".";
	}
}