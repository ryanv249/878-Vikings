package vikingbattle;

import java.util.ArrayList;
import java.util.Arrays;

/* Notes:
 * Create a Leader phase.
 */

public class Phase
{
	public static int round = 0, turn = 0, controlMarkers = 0;
	public static String phase = "";
	public static ArrayList<String> playerChoices = new ArrayList<String>();
	public static Player player;
	public static Player[] players = {new Player("Berserker", Deck.berserkerCards, new ArrayList<Card>(), new ArrayList<Card>()),
									  new Player("Norsemen", Deck.norsemenCards, new ArrayList<Card>(), new ArrayList<Card>()),
									  new Player("Housecarl", Deck.housecarlCards, new ArrayList<Card>(), new ArrayList<Card>()),
									  new Player("Thegn", Deck.thegnCards, new ArrayList<Card>(), new ArrayList<Card>())};
	public static Player[] berserkOrder = {players[0], players[1], players[2], players[3]};
	public static Player[] norseOrder = {players[1], players[0], players[2], players[3]};
	public static Player[] houseOrder = {players[2], players[3], players[0], players[1]};
	public static Player[] thegnOrder = {players[3], players[2], players[0], players[1]};
	
	public static void choosePlayer()
	{
		if (playerChoices.size() > 0)
		{
			switch (playerChoices.remove((int)(Math.random() * playerChoices.size())))
			{
				case "Berserker":
					player = players[0];
					break;
				case "Norsemen":
					player = players[1];
					break;
				case "Housecarl":
					player = players[2];
					break;
				case "Thegn":
					player = players[3];
					break;
			}
			
			turn++;
		}
		else
		{
			playerChoices = new ArrayList<String>(Arrays.asList("Berserker", "Norsemen", "Housecarl", "Thegn"));
			round++;
			
			if (round == 1)
			{
				playerChoices.remove(1);
				player = players[1];
			}
			else
			{
				switch (playerChoices.remove((int)(Math.random() * playerChoices.size())))
				{
					case "Berserker":
						player = players[0];
						break;
					case "Norsemen":
						player = players[1];
						break;
					case "Housecarl":
						player = players[2];
						break;
					case "Thegn":
						player = players[3];
						break;
				}
			}
			
			turn = 1;
			phase = "Reinforcements";
		}
	}
}