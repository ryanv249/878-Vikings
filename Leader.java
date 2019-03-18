package vikingbattle;

public class Leader
{
	String name, description;
	int numNorse, numBerserk;
	
	public Leader(String newName, String newDescription, int newNumNorse, int newNumBerserk)
	{
		name = newName;
		description = newDescription;
		numNorse = newNumNorse;
		numBerserk = newNumBerserk;
	}
	
	public String toString()
	{
		return "Name: " + name + "\nDescription: " + description + "\nNorsemen: " + numNorse + "\nBerserkers: " + numBerserk;
	}
}