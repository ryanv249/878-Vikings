package vikingbattle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/* Notes:
 * Need to fix the card order so that used cards aren't immediately activated.
 * Need to make more Player conditions for different phases.
 * Find a way to reset cards at the end of every "Phase." (Saxon Navy)
 ** Make sure that movement cards are played in the Reinforcements phase, and "move" type cards are played in the movement phase.
 */

public class Deck
{
	public static ArrayList<Leader> leaderCards = new ArrayList<Leader>(Arrays.asList(new Leader("Halfdan's Great Heathen Host", "Invade North Sea coast.", 17, 8),
																					  new Leader("Ivar the Boneless's Army", "Invade North Sea coast.", 14, 6),
																					  new Leader("Björn Ironside's Army", "Invade North Sea coast.", 11, 4),
																					  new Leader("Reinforcements", "No invasion this round. Place as reinforcments.", 9, 2),
																					  new Leader("Guthrum's Great Summer Army", "Invade any coast.", 17, 8),
																					  new Leader("Ubbe Ragnarsson's Army", "Invade any coast.", 14, 6),
																					  new Leader("Rollo's Army", "Invade any coast.", 11, 4)));
	public static ArrayList<Card> berserkerCards = new ArrayList<Card>(Arrays.asList(new Card(1, "Berserker", "Treaty", "Treaty of Wedmore - Check for Game End on Round 5 or later; 2 armies may move 4 times.", "Berserker Movement", 2, 4),
																					 new Card(2, "Berserker", "Move", "Movement - 2 armies may move 4 times.", "Berserker Movement", 2, 4),
																					 new Card(3, "Berserker", "Move", "Movement - 2 armies may move 4 times.", "Berserker Movement", 2, 4),
																					 new Card(4, "Berserker", "Move", "Movement - 2 armies may move 4 times.", "Berserker Movement", 2, 4),
																					 new Card(5, "Berserker", "Move", "Movement - 2 armies may move 3 times.", "Berserker Movement", 2, 3),
																					 new Card(6, "Berserker", "Move", "Movement - 2 armies may move 3 times.", "Berserker Movement", 2, 3),
																					 new Card(7, "Berserker", "Move", "Movement - 2 armies may move 2 times.", "Berserker Movement", 2, 2),
																					 new Card(8, "Berserker", "Spell", "Increased Morale - Replace up to two Norsemen with Berserker Units in any one Viking army.", "Berserker Reinforcements Phase", 0, 0),
																					 new Card(9, "Berserker", "Spell", "Viking Terror - For this Battle, the English may not draw a Fyrd Card.", "Berserker Battle", 0, 0),
																					 new Card(10, "Berserker", "Spell", "Surprise Attack - Before this Battle, the Viking army resolves one Battle Dice roll.", "Berserker Battle", 0, 0),
																					 new Card(11, "Berserker", "Spell", "Ceolwulf - If any Ceolwulf Shire is Viking-controlled, eliminate any English Units in another Ceolwulf Shire and move up to two Viking Units on the map to the emptied Shire.", "Berserker Reinforcements Phase", 0, 0),
																					 new Card(12, "Berserker", "Spell", "Viking Fort - For this Battle, the Viking army ignores one 'Hit' result from each English Battle Dice roll.", "English Battle", 0, 0),
																					 new Card(13, "Berserker", "Spell", "Viking Ships - One Viking Army may move from one Coastal Shire to another Shire on the same Sea's coast at the cost of one move.", "Berserker Movement", 0, 0),
																					 new Card(14, "Berserker", "Spell", "Increased Morale - Replace up to two Norsemen with Berserker Units in any one Viking army.", "Berserker Reinforcements Phase", 0, 0),
																					 new Card(15, "Berserker", "Spell", "Viking Terror - For this Battle, the English may not draw a Fyrd Card.", "Berserker Battle", 0, 0),
																					 new Card(16, "Berserker", "Spell", "Surprise Attack - Before this Battle, the Viking army resolves one Battle Dice roll.", "Berserker Battle", 0, 0),
																					 new Card(17, "Berserker", "Spell", "Ceolwulf - If any Ceolwulf Shire is Viking-controlled, eliminate any English Units in another Ceolwulf Shire and move up to two Viking Units on the map to the emptied Shire.", "Berserker Reinforcements Phase", 0, 0),
																					 new Card(18, "Berserker", "Spell", "Viking Fort - For this Battle, the Viking army ignores one 'Hit' result from each English Battle Dice roll.", "English Battle", 0, 0),
																					 new Card(19, "Berserker", "Spell", "Viking Ships - One Viking Army may move from one Coastal Shire to another Shire on the same Sea's coast at the cost of one move.", "Berserker Movement", 0, 0)));
	public static ArrayList<Card> norsemenCards = new ArrayList<Card>(Arrays.asList(new Card(1, "Norsemen", "Treaty", "Treaty of Wedmore - Check for Game End on Round 5 or later; 2 armies may move 4 times.", "Norsemen Movement", 2, 4),
																					new Card(2, "Norsemen", "Move", "Movement - 2 armies may move 4 times.", "Norsemen Movement", 2, 4),
																					new Card(3, "Norsemen", "Move", "Movement - 2 armies may move 4 times.", "Norsemen Movement", 2, 4),
																					new Card(4, "Norsemen", "Move", "Movement - 2 armies may move 4 times.", "Norsemen Movement", 2, 4),
																					new Card(5, "Norsemen", "Move", "Movement - 2 armies may move 3 times.", "Norsemen Movement", 2, 3),
																					new Card(6, "Norsemen", "Move", "Movement - 2 armies may move 3 times.", "Norsemen Movement", 2, 3),
																					new Card(7, "Norsemen", "Move", "Movement - 2 armies may move 2 times.", "Norsemen Movement", 2, 2),
																					new Card(8, "Norsemen", "Spell", "Feigned Retreat - Before this Battle, the English resolves one Battle Dice roll, counting only 'Flee' results.", "Any Battle", 0, 0),
																					new Card(9, "Norsemen", "Spell", "English Traitors - Replace up to two Thegn with as many Norsemen Units in a Shire of the Norsemen player's choice. May result in a Battle.", "Norsemen Reinforcements Phase", 0, 0),
																					new Card(10, "Norsemen", "Spell", "Shield Wall - For all Battles this Turn, Viking armies ignore the first 'Hit' result of each Battle.", "English Battle", 0, 0),
																					new Card(11, "Norsemen", "Spell", "Northumbrian Discord - Before resolving this Battle in any of the northern Shires of Carlisle, Durham, York, or Manchester, the Thegn and Housecarl Units resolve one Battle Dice roll against each other.", "Norsemen Battle", 0, 0),
																					new Card(12, "Norsemen", "Spell", "Viking Ships - One Viking Army may move from one Coastal Shire to another Shire on the same Sea's coast at the cost of one Move.", "Norsemen Movement", 0, 0),
																					new Card(13, "Norsemen", "Spell", "Viking Fort - For this Battle, the Viking army ignores one 'Hit' result from Each English Battle Dice roll.", "English Battle", 0, 0),
																					new Card(14, "Norsemen", "Spell", "Feigned Retreat - Before this Battle, the English resolves one Battle Dice roll, counting only 'Flee' results.", "Any Battle", 0, 0),
																					new Card(15, "Norsemen", "Spell", "English Traitors - Replace up to two Thegn with as many Norsemen Units in a Shire of the Norsemen player's choice. May result in a Battle.", "Norsemen Reinforcements Phase", 0, 0),
																					new Card(16, "Norsemen", "Spell", "Shield Wall - For all Battles this Turn, Viking armies ignore the first 'Hit' result of each Battle.", "English Battle", 0, 0),
																					new Card(17, "Norsemen", "Spell", "Northumbrian Discord - Before resolving this Battle in any of the northern Shires of Carlisle, Durham, York, or Manchester, the Thegn and Housecarl Units resolve one Battle Dice roll against each other.", "Norsemen Battle", 0, 0),
																					new Card(18, "Norsemen", "Spell", "Viking Ships - One Viking Army may move from one Coastal Shire to another Shire on the same Sea's coast at the cost of one Move.", "Norsemen Movement", 0, 0),
																					new Card(19, "Norsemen", "Spell", "Viking Fort - For this Battle, the Viking army ignores one 'Hit' result from Each English Battle Dice roll.", "English Battle", 0, 0)));
	public static ArrayList<Card> housecarlCards = new ArrayList<Card>(Arrays.asList(new Card(1, "Housecarl", "Treaty", "Treaty of Wedmore - Check for Game End on Round 5 or later; 2 armies may move 3 times.", "Housecarl Movement", 2, 3),
																					 new Card(2, "Housecarl", "Move", "Movement - 2 armies may move 2 times.", "Housecarl Movement", 2, 2),
																					 new Card(3, "Housecarl", "Move", "Movement - 3 armies may move 2 times.", "Housecarl Movement", 3, 2),
																					 new Card(4, "Housecarl", "Move", "Movement - 3 armies may move 2 times.", "Housecarl Movement", 3, 2),
																					 new Card(5, "Housecarl", "Move", "Movement - 4 armies may move 2 times.", "Housecarl Movement", 4, 2),
																					 new Card(6, "Housecarl", "Move", "Movement - 3 armies may move 1 time.", "Housecarl Movement", 3, 1),
																					 new Card(7, "Housecarl", "Move", "Movement - 4 armies may move 1 time.", "Housecarl Movement", 4, 1),
																					 new Card(8, "Housecarl", "Spell", "King's Purse - Add a total of up to three Housecarl Units to Shires that contain at least one Housecarl Unit.", "Housecarl Reinforcements Phase", 0, 0),
																					 new Card(9, "Housecarl", "Spell", "Rebellion - Remove one Viking Unit (Viking choice) from a Shire. Then draw a Fyrd Card and immediately initiate a Battle against any remaining Vikings in that Shire.", "Housecarl Reinforcements Phase", 0, 0),
																					 new Card(10, "Housecarl", "Spell", "Saxon Navy - Choose 6 Coastal Shires. The Vikings may not enter these Shires this Turn.", "Viking Movement", 0, 0),
																					 new Card(11, "Housecarl", "Spell", "Danegeld - Before each Battle this Turn, the attacking Viking players must choose to remove either: 1 Berserker or up to 2 Norsemen Units from their army.", "Viking Battle", 0, 0),
																					 new Card(12, "Housecarl", "Spell", "Alfred's Gambit - Before each Battle this Turn, draw 1 Fyrd Card that joins the English attack.", "English Battle", 0, 0),
																					 new Card(13, "Housecarl", "Spell", "Archers - Before this Battle, the English army resolves one Battle Dice roll. Ignore 'Flee' results.", "English Battle", 0, 0),
																					 new Card(14, "Housecarl", "Spell", "King's Purse - Add a total of up to three Housecarl Units to Shires that contain at least one Housecarl Unit.", "Housecarl Reinforcements Phase", 0, 0),
																					 new Card(15, "Housecarl", "Spell", "Rebellion - Remove one Viking Unit (Viking choice) from a Shire. Then draw a Fyrd Card and immediately initiate a Battle against any remaining Vikings in that Shire.", "Housecarl Reinforcements Phase", 0, 0),
																					 new Card(16, "Housecarl", "Spell", "Saxon Navy - Choose 6 Coastal Shires. The Vikings may not enter these Shires this Turn.", "Viking Movement", 0, 0),
																					 new Card(17, "Housecarl", "Spell", "Danegeld - Before each Battle this Turn, the attacking Viking players must choose to remove either: 1 Berserker or up to 2 Norsemen Units from their army.", "Viking Battle", 0, 0),
																					 new Card(18, "Housecarl", "Spell", "Alfred's Gambit - Before each Battle this Turn, draw 1 Fyrd Card that joins the English attack.", "English Battle", 0, 0),
																					 new Card(19, "Housecarl", "Spell", "Archers - Before this Battle, the English army resolves one Battle Dice roll. Ignore 'Flee' results.", "English Battle", 0, 0)));
	public static ArrayList<Card> thegnCards = new ArrayList<Card>(Arrays.asList(new Card(1, "Thegn", "Treaty", "Treaty of Wedmore - Check for Game End on Round 5 or later; 2 armies may move 3 times.", "Thegn Movement", 2, 3),
																				 new Card(2, "Thegn", "Move", "Movement - 1 army may move 3 times.", "Thegn Movement", 1, 3),
																				 new Card(3, "Thegn", "Move", "Movement - 2 armies may move 4 times.", "Thegn Movement", 2, 4),
																				 new Card(4, "Thegn", "Move", "Movement - 3 armies may move 2 times.", "Thegn Movement", 3, 2),
																				 new Card(5, "Thegn", "Move", "Movement - 2 armies may move 2 times.", "Thegn Movement", 2, 2),
																				 new Card(6, "Thegn", "Move", "Movement - 4 armies may move 1 time.", "Thegn Movement", 4, 1),
																				 new Card(7, "Thegn", "Move", "Movement - 3 armies may move 1 time.", "Thegn Movement", 3, 1),
																				 new Card(8, "Thegn", "Spell", "Religious Conversion - Replace up to two Norsemen with Thegn Units in a Shire of the Thegn player's choice. May result in a Battle.", "Thegn Reinforcements Phase", 0, 0),
																				 new Card(9, "Thegn", "Spell", "Escape into the Wilds - After an English Battle Dice roll and before the Viking Battle Dice roll, withdraw all English Units to an adjacent Shire not occupied by Vikings.", "Viking Battle", 0, 0),
																				 new Card(10, "Thegn", "Spell", "Improved Training - For all Battles this Turn, Thegn faction 'Flee' results count as 'Hit' results.", "Thegn Battle", 0, 0),
																				 new Card(11, "Thegn", "Spell", "Thegn Leadership - Each English army may move up to two extra Shires this Turn.", "Thegn Movement", 0, 0),
																				 new Card(12, "Thegn", "Spell", "Spy - One English army may move up to two Shires to join in this Battle.", "Viking Battle", 0, 0),
																				 new Card(13, "Thegn", "Spell", "Ambush - Before this Battle, the English army resolves one Battle Dice roll.", "Viking Battle", 0, 0),
																				 new Card(14, "Thegn", "Spell", "Religious Conversion - Replace up to two Norsemen with Thegn Units in a Shire of the Thegn player's choice. May result in a Battle.", "Thegn Reinforcements Phase", 0, 0),
																				 new Card(15, "Thegn", "Spell", "Escape into the Wilds - After an English Battle Dice roll and before the Viking Battle Dice roll, withdraw all English Units to an adjacent Shire not occupied by Vikings.", "Viking Battle", 0, 0),
																				 new Card(16, "Thegn", "Spell", "Improved Training - For all Battles this Turn, Thegn faction 'Flee' results count as 'Hit' results.", "Thegn Battle", 0, 0),
																				 new Card(17, "Thegn", "Spell", "Thegn Leadership - Each English army may move up to two extra Shires this Turn.", "Thegn Movement", 0, 0),
																				 new Card(18, "Thegn", "Spell", "Spy - One English army may move up to two Shires to join in this Battle.", "Viking Battle", 0, 0),
																				 new Card(19, "Thegn", "Spell", "Ambush - Before this Battle, the English army resolves one Battle Dice roll.", "Viking Battle", 0, 0)));
	public static ArrayList<Integer> fyrdCards = new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5));
	public static ArrayList<Card> activeCards = new ArrayList<Card>();
	
	public static void createHand(Player p)
	{
		for (int i = 0; i < p.hand.size(); i++)
		{
			p.cards.add(0, p.hand.get(i));
		}
		
		p.hand.clear();
		
		for (int i = 0; i < 3; i++)
		{
			p.hand.add(p.cards.remove((int)(Math.random() * (p.cards.size() - (7 + p.playedCards.size())))));
		}
	}
	
	public static void drawCard(Player p)
	{
		p.hand.add(p.cards.remove((int)(Math.random() * (p.cards.size() - (7 + p.playedCards.size())))));
	}
	
	public static void resetCards(Player p)
	{
		for (int i = 0; i < p.playedCards.size(); i++)
		{
			// Self-explanatory
		}
	}
	
	public static void playCards()
	{
		for (int i = 0; i < activeCards.size(); i++)
		{
			switch (activeCards.get(i).team)
			{
				case "Berserker":
					useCard(activeCards.get(i), Phase.players[0]);
					break;
				case "Norsemen":
					useCard(activeCards.get(i), Phase.players[1]);
					break;
				case "Housecarl":
					useCard(activeCards.get(i), Phase.players[2]);
					break;
				case "Thegn":
					useCard(activeCards.get(i), Phase.players[3]);
					break;
			}
		}
	}
	
	public static void notifyPlayers()
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Player[] players = new Player[4];
		
		switch (Phase.player.name)
		{
			case "Berserker":
				players = Phase.berserkOrder;
				break;
			case "Norsemen":
				players = Phase.norseOrder;
				break;
			case "Housecarl":
				players = Phase.houseOrder;
				break;
			case "Thegn":
				players = Phase.thegnOrder;
				break;
		}
		
		for (int j = 0; j < players.length; j++)
		{
			switch (players[j].name)
			{
				case "Berserker":
					for (int i = 1; i < Map.shireList.size(); i++)
					{
						if (Map.shireList.get(i).defenders.numBerserk > 0)
						{
							break;
						}
					}
					
					continue;
				case "Norsemen":
					for (int i = 1; i < Map.shireList.size(); i++)
					{
						if (Map.shireList.get(i).defenders.numNorse > 0)
						{
							break;
						}
					}
					
					continue;
				case "Housecarl":
					for (int i = 1; i < Map.shireList.size(); i++)
					{
						if (Map.shireList.get(i).defenders.numHouse > 0)
						{
							break;
						}
					}
					
					continue;
				case "Thegn":
					for (int i = 1; i < Map.shireList.size(); i++)
					{
						if (Map.shireList.get(i).defenders.numThegn > 0)
						{
							break;
						}
					}
					
					continue;
			}
			
			Player p = players[j];
			ArrayList<Boolean> playableCards = checkCards(p);
			ArrayList<Integer> cardNumbers = new ArrayList<Integer>();
			boolean usedCard = false;
			
			if (p.equals(Phase.player) && Phase.phase.equals("Movement"))
			{
				usedCard = true;
				
				if (!playableCards.contains(true) || movementCondition(playableCards.size(), p.hand))
				{
					System.out.println(p.name + " needs to redraw cards for the Movement phase.");
					createHand(p);
					
					while (!playableCards.contains(true))
					{
						createHand(p);
					}
				}
				
				System.out.println(p.name + ", pick a card's number to use it for the Movement phase.\n");
				
				for (int k = 0; k < playableCards.size(); k++)
				{
					cardNumbers.add(p.hand.get(k).number);
					
					if (playableCards.get(k) && !p.hand.get(k).type.equals("Spell"))
					{
						System.out.println("Playable >\t" + p.hand.get(k));
					}
					else
					{
						System.out.println("Not playable >\t" + p.hand.get(k));
					}
				}
				
				int choice = sc.nextInt();
				
				while (!cardNumbers.contains(choice))
				{
					System.out.println("Invalid number. Try again.");
					choice = sc.nextInt();
				}
				
				useCard(p.hand.get(choice - 1), p);
				p.playedCards.add(p.hand.remove(choice - 1));
			}
			
			while (playableCards.contains(true))
			{
				if (usedCard)
				{
					playableCards = checkCards(p);
					System.out.println(p.name + ", would you like to use any additional cards before the " + Phase.phase + " phase begins?");
				}
				else
				{
					usedCard = true;
					System.out.println(p.name + ", would you like to use any cards before the " + Phase.phase + " phase begins?");
				}
				
				String response = sc.nextLine();
				
				while (!response.equals("yes") && !response.equals("no"))
				{
					System.out.println("Invalid response. Try again.");
					response = sc.nextLine();
				}
				
				System.out.println(p.name + ", pick a card's number to use it for the " + Phase.phase + " phase.\n");
				
				for (int k = 0; k < playableCards.size(); k++)
				{
					cardNumbers.add(p.hand.get(k).number);
					
					if (playableCards.get(k) == true)
					{
						System.out.println("Playable >\t" + p.hand.get(k));
					}
					else
					{
						System.out.println("Not playable >\t" + p.hand.get(k));
					}
				}
				
				int choice = sc.nextInt();
				
				while (!cardNumbers.contains(choice))
				{
					System.out.println("Invalid number. Try again.");
					choice = sc.nextInt();
				}
				
				activeCards.add(p.hand.get(choice - 1));
				p.playedCards.add(p.hand.remove(choice - 1));
			}
		}
	}
	
	public static ArrayList<Boolean> checkCards(Player p)
	{
		ArrayList<Boolean> isPlayable = new ArrayList<Boolean>();
		
		for (int i = 0; i < p.hand.size(); i++)
		{
			Card c = p.hand.get(i);
			
			if (((c.phase.contains("Viking") && (Phase.player.name.equals("Berserker") || Phase.player.name.equals("Norsemen")))
			|| (c.phase.contains("English") && (Phase.player.name.equals("Housecarl") || Phase.player.name.equals("Thegn")))
			|| (c.phase.contains(p.name)) || (c.phase.contains("Any"))) && (Phase.phase.contains(c.phase))
			)
			{
				isPlayable.add(true);
			}
			else
			{
				isPlayable.add(false);
			}
		}
		
		return isPlayable;
	}
	
	public static void useCard(Card c, Player p)
	{
		switch (c.name())
		{
			case "Treaty of Wedmore":
			{
				p.treaty = true;
				Movement.move(p, c.numArmies, c.numMoves);
				break;
			}
			case "Movement":
			{
				Movement.move(p, c.numArmies, c.numMoves);
				break;
			}
			case "Increased Morale":
			{
				increasedMorale(p);
				break;
			}
			case "Viking Terror":
			{
				Battle.vikingTerror = true;
				break;
			}
			case "Surprise Attack":
			{
				Battle.surpriseAttack = true;
				break;
			}
			case "Ceolwulf":
			{
				ceolwulf(p);
				break;
			}
			case "Viking Fort":
			{
				Battle.vikingFort = true;
				break;
			}
			case "Viking Ships":
			{
				Movement.vikingShips = true;
				break;
			}
			case "Feigned Retreat":
			{
				Battle.feignedRetreat = true;
				break;
			}
			case "English Traitors":
			{
				englishTraitors(p);
				break;
			}
			case "Shield Wall":
			{
				Battle.shieldWall = true;
				break;
			}
			case "Northumbrian Discord":
			{
				Battle.northumbrianDiscord = true;
				break;
			}
			case "King's Purse":
			{
				kingsPurse(p);
				break;
			}
			case "Rebellion":
			{
				rebellion(p);
				break;
			}
			case "Saxon Navy":
			{
				saxonNavy(p);
				break;
			}
			case "Danegeld":
			{
				Battle.danegeld = true;
				break;
			}
			case "Alfred's Gambit":
			{
				Battle.alfredsGambit = true;
				break;
			}
			case "Archers":
			{
				Battle.archers = true;
				break;
			}
			case "Religious Conversion":
			{
				religiousConversion(p);
				break;
			}
			case "Escape into the Wilds":
			{
				Battle.escapeIntoTheWilds = true;
				break;
			}
			case "Improved Training":
			{
				Battle.improvedTraining = true;
				break;
			}
			case "Thegn Leadership":
			{
				Movement.thegnLeadership = true;
				break;
			}
			case "Spy":
			{
				Battle.spy = true;
				break;
			}
			case "Ambush":
			{
				Battle.ambush = true;
				break;
			}
		}
	}
	
	public static void increasedMorale(Player p)
	{
		ArrayList<Integer> shires = new ArrayList<Integer>();
		
		for (int i = 1; i < Map.shireList.size(); i++)
		{
			if (Map.shireList.get(i).defenders.numNorse > 0)
			{
				shires.add(i);
			}
		}
		
		if (shires.size() == 0)
		{
			return;
		}
		
		System.out.println("Choose a Shire location to replace up to two of a Viking army's Norsemen with Berserkers:");
		Map.printLocations(shires);
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int place = sc.nextInt();
		int choice = 1;
		
		while (!shires.contains(place))
		{
			System.out.println("Invalid number. Try again.");
			place = sc.nextInt();
		}
		
		if (Map.shireList.get(place).defenders.numNorse > 1)
		{
			System.out.println("How many Norsemen would you like to replace with Berserkers?");
			choice = sc.nextInt();
			
			while (choice != 1 && choice != 2)
			{
				System.out.println("Invalid number. Try again.");
				choice = sc.nextInt();
			}	
		}
		
		System.out.println(choice + " Norsemen have been replaced with " + choice + " Berserkers in Shire " + place + ".\n");
		Map.shireList.get(place).defenders.numBerserk += choice;
		Map.shireList.get(place).defenders.numNorse -= choice;
	}
	
	public static void ceolwulf(Player p)
	{
		ArrayList<Integer> shires = new ArrayList<Integer>();
		
		for (int i = 1; i < Map.shireList.size(); i++)
		{
			if (Map.shireList.get(i).isCeolwulf && !Map.shireList.get(i).isControlled())
			{
				shires.add(i);
			}
		}
		
		if (shires.size() == 0)
		{
			return;
		}
		
		System.out.println("Choose a Shire location to eliminate all English units from:");
		Map.printLocations(shires);
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int place = sc.nextInt();
		
		while (!shires.contains(place))
		{
			System.out.println("Invalid number. Try again.");
			place = sc.nextInt();
		}
		
		Shire ceolwulf = Map.shireList.get(place);
		
		if (ceolwulf.defenders.hasEnglish())
		{
			System.out.println(ceolwulf.defenders.numHouse + " Housecarls and " + ceolwulf.defenders.numThegn + " Thegns died.\n");
			
			ceolwulf.defenders.numHouse -= ceolwulf.defenders.numHouse;
			ceolwulf.defenders.numThegn -= ceolwulf.defenders.numThegn;
			ceolwulf.defenders.leader = null;
		}
		
		System.out.println("Would you like to move up to two Viking units on the map to this Shire?");
		String choice = sc.nextLine();
		
		while (!choice.equals("yes") && !choice.equals("no"))
		{
			System.out.println("Invalid response. Try again.");
			choice = sc.nextLine();
		}
		
		if (choice.equals("no"))
		{
			return;
		}
		else
		{
			ceolwulf.defenders.nation = "Vikings";
			int vikingUnits = 2;
			shires.clear();
			
			for (int i = 1; i < Map.shireList.size(); i++)
			{
				if (Map.shireList.get(i).defenders.hasVikings())
				{
					shires.add(i);
				}
			}
			
			while (vikingUnits > 0)
			{
				if (vikingUnits == 1)
				{
					System.out.println("Would you like to move any more Viking units?");
					choice = sc.nextLine();
					
					while (!choice.equals("yes") && !choice.equals("no"))
					{
						System.out.println("Invalid response. Try again.");
						choice = sc.nextLine();
					}
					
					if (choice.equals("no"))
					{
						break;
					}
				}
				
				System.out.println("Choose a Shire to move the Viking units from:");
				Map.printLocations(shires);
				place = sc.nextInt();
				
				while (!shires.contains(place))
				{
					System.out.println("Invalid number. Try again.");
					place = sc.nextInt();
				}
				
				Army a = Map.shireList.get(place).defenders;
				int numBerserk = 0;
				int numNorse = 0;
				
				System.out.println("Which unit type would you like to take from this Shire?");
				
				if (vikingUnits == 2)
				{
					System.out.println("(Type 'both' if you want one Berserker and one Norsemen.)");
				}
				
				choice = sc.nextLine();
				
				while ((!choice.equals("Berserker") && !choice.equals("Norsemen") && !choice.equals("both"))
				|| ((choice.equals("Berserker") && a.numBerserk == 0)
				|| (choice.equals("Norsemen") && a.numNorse == 0)
				|| (choice.equals("both") && (a.numBerserk == 0 || a.numNorse == 0))))
				{
					System.out.println("Invalid response. Try again.");
					choice = sc.nextLine();
				}
				
				if (choice.equals("Beserker"))
				{
					if (vikingUnits > 2)
					{
						System.out.println("How many Berserkers?");
						numBerserk = sc.nextInt();
						
						while (numBerserk != 1 && numBerserk != 2)
						{
							System.out.println("Invalid number. Try again.");
							numBerserk = sc.nextInt();
						}
					}
					else
					{
						numBerserk = 1;
					}
				}
				else if (choice.equals("Norsemen"))
				{
					if (vikingUnits > 2)
					{
						System.out.println("How many Norsemen?");
						numNorse = sc.nextInt();
						
						while (numNorse != 1 && numNorse != 2)
						{
							System.out.println("Invalid number. Try again.");
							numNorse = sc.nextInt();
						}
					}
					else
					{
						numNorse = 1;
					}
				}
				else
				{
					numBerserk = 1;
					numNorse = 1;
				}
				
				Movement.moveArmy(new Army("Vikings", numBerserk, numNorse), Map.shireList.get(place), ceolwulf, false);
			}
		}
	}
	
	public static void englishTraitors(Player p)
	{
		ArrayList<Integer> shires = new ArrayList<Integer>();
		
		for (int i = 1; i < Map.shireList.size(); i++)
		{
			if (Map.shireList.get(i).defenders.numThegn > 0)
			{
				shires.add(i);
			}
		}
		
		System.out.println("Choose a Shire to replace up to two Thegn with as many Norsemen units:");
		Map.printLocations(shires);
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int place = sc.nextInt();
		
		while (!shires.contains(place))
		{
			System.out.println("Invalid number. Try again.");
			place = sc.nextInt();
		}
		
		Shire s = Map.shireList.get(place);
		int units = 0;
		
		if (s.defenders.numThegn >= 2)
		{
			units = 2;
		}
		else
		{
			units = 1;
		}
		
		System.out.println(units + " Thegns have been replaced with " + units + " Norsemen in Shire " + place + ".\n");
		s.defenders.numThegn -= units;
		Movement.interArmy(new Army("Vikings", 0, units), s);
	}
	
	public static void kingsPurse(Player p)
	{
		ArrayList<Integer> shires = new ArrayList<Integer>();
		
		for (int i = 1; i < Map.shireList.size(); i++)
		{
			if (Map.shireList.get(i).defenders.numHouse > 0)
			{
				shires.add(i);
			}
		}
		
		if (shires.size() == 0)
		{
			return;
		}
		
		System.out.println("Choose a Shire to add a total of up to 3 Housecarl units to:");
		Map.printLocations(shires);
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int place = sc.nextInt();
		
		while (!shires.contains(place))
		{
			System.out.println("Invalid number. Try again.");
			place = sc.nextInt();
		}
		
		System.out.println("How many Housecarl?");
		int numHouse = sc.nextInt();
		
		while (numHouse < 0 && numHouse > 3)
		{
			System.out.println("Invalid number. Try again.");
			numHouse = sc.nextInt();
		}
		
		Map.shireList.get(place).defenders.numHouse += numHouse;
		System.out.println(numHouse + " Housecarls added to Shire " + place + ".\n");
	}
	
	public static void rebellion(Player p)
	{
		ArrayList<Integer> shires = new ArrayList<Integer>();
		
		for (int i = 1; i < Map.shireList.size(); i++)
		{
			if (Map.shireList.get(i).defenders.hasVikings())
			{
				shires.add(i);
			}
		}
		
		if (shires.size() == 0)
		{
			return;
		}
		
		System.out.println("Choose a Shire to remove one Viking unit from:");
		Map.printLocations(shires);
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int place = sc.nextInt();
		
		while (!shires.contains(place))
		{
			System.out.println("Invalid number. Try again.");
			place = sc.nextInt();
		}
		
		Shire s = Map.shireList.get(place);
		
		if (s.defenders.numBerserk > 0 && s.defenders.numNorse > 0)
		{
			System.out.println("Vikings, which unit type would you like to remove from this army?");
			String choice = sc.nextLine();
			
			while (!choice.equals("Berserker") && !choice.equals("Norsemen"))
			{
				System.out.println("Invalid response. Try again.");
				choice = sc.nextLine();
			}
			
			if (choice.equals("Bersekrer"))
			{
				System.out.println("1 Berserker died.\n");
				s.defenders.numBerserk--;
			}
			else
			{
				System.out.println("1 Norsemen died.\n");
				s.defenders.numNorse--;
			}
			
		}
		else if (s.defenders.numBerserk > 0)
		{
			System.out.println("1 Berserker died.\n");
			s.defenders.numBerserk--;
		}
		else
		{
			System.out.println("1 Norsemen died.\n");
			s.defenders.numNorse--;
		}
		
		Army rebellion = new Army("English", 0, 0, Deck.pickCard(Deck.fyrdCards));
		System.out.println("English draw " + rebellion.numFyrd + " Fyrds.\n");
		
		Movement.interArmy(rebellion, s);
	}
	
	public static void saxonNavy(Player p)
	{
		ArrayList<Integer> shires = new ArrayList<Integer>();
		
		for (int i = 1; i < Map.shireList.size(); i++)
		{
			if (Map.shireList.get(i).isCoastal())
			{
				shires.add(i);
			}
		}
		
		System.out.println("Choose 6 Coastal Shires to restrict from Viking movement:");
		Map.printLocations(shires);
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		for (int i = 0; i < 6; i++)
		{
			int place = sc.nextInt();
			
			while (!shires.contains(place))
			{
				System.out.println("Invalid number. Try again.");
				place = sc.nextInt();
			}
			
			Movement.saxonNavy.add(shires.remove(place));
		}
		
		System.out.println("Restricted Shires: " + shires + "\n");
	}
	
	public static void religiousConversion(Player p)
	{
		ArrayList<Integer> shires = new ArrayList<Integer>();
		
		for (int i = 1; i < Map.shireList.size(); i++)
		{
			if (Map.shireList.get(i).defenders.numNorse > 0)
			{
				shires.add(i);
			}
		}
		
		if (shires.size() == 0)
		{
			return;
		}
		
		System.out.println("Choose a Shire to replace up to two Norsemen with Thegn units:");
		Map.printLocations(shires);
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int place = sc.nextInt();
		
		while (!shires.contains(place))
		{
			System.out.println("Invalid number. Try again.");
			place = sc.nextInt();
		}
		
		Shire s = Map.shireList.get(place);
		int units = 0;
		
		if (s.defenders.numNorse >= 2)
		{
			units = 2;
		}
		else
		{
			units = 1;
		}
		
		System.out.println(units + " Norsemen have been replaced with " + units + " Thegns in Shire " + place + ".\n");
		s.defenders.numNorse -= units;
		Movement.interArmy(new Army("English", 0, units), s);
	}
	
	public static boolean movementCondition(int playableCards, ArrayList<Card> hand)
	{
		int cards = 0;
		
		for (int i = 0; i < hand.size(); i++)
		{
			Card c = hand.get(i);
			
			if (c.type.equals("Spell") && c.phase.contains("Movement"))
			{
				cards++;
			}
		}
		
		return (playableCards == cards);
	}
	
	public static <T> T pickCard(ArrayList<T> cards)
	{
		return cards.remove((int)(Math.random() * cards.size()));
	}
}