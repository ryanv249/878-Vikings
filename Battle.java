package vikingbattle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/* Notes:
 * Move pre-battle specific card activations to before the battle begins so as to not confuse them with other card properties.
 */

public class Battle
{
	/** [0] == Norse, [1] == Housecarl, [2] == Thegn */
	public static int[] fledStore = new int[3];
	public static boolean firstEnglishRoll = true;
	public static boolean vikingTerror, surpriseAttack, vikingFort,
						  feignedRetreat, shieldWall, firstShieldWall, northumbrianDiscord,
						  danegeld, alfredsGambit, archers,
						  escapeIntoTheWilds, improvedTraining, spy, ambush;
	// The first element in this ArrayList should be the first battle to play.
	public static ArrayList<Battle> battles = new ArrayList<Battle>();
	Army attackers, defenders;
	Shire area;
	
	public Battle(Army attackers, Army defenders, Shire area)
	{
		this.attackers = attackers;
		this.defenders = defenders;
		this.area = area;
	}
	
	public static void main(String[] args)
	{
		Phase.choosePlayer();
//		Shire s = Map.shireList.get(0);
//		Army v = new Army("Vikings", 3, 5, Deck.pickCard(Deck.leaderCards));
//		Army e = new Army("English", 10, 12);
//		v.numBerserk += v.leader.numBerserk;
//		v.numNorse += v.leader.numNorse;
//		battles.add(new Battle(v, e, s));
//		
//		startBattle(v, e, s);
		
//		Army a = new Army("Vikings", 5, 4);
//		Map.shireList.get(1).defenders = new Army("Vikings", 10, 12);
//		Map.shireList.get(3).name = "a city";
//		moveArmy(a, Map.shireList.get(1), Map.shireList.get(3), true);
	}
	
	public static void startBattle(Army attacker, Army defender, Shire area)
	{
		// This ensures that *this* battle will be the first element in the battles ArrayList.
		for (int i = 1; i < battles.size(); i++)
		{
			if (battles.get(i).area.equals(area))
			{
				battles.add(0, battles.remove(i));
				break;
			}
		}
		
		Phase.phase = "Battle";
		Deck.notifyPlayers();
		firstEnglishRoll = true;
		firstShieldWall = shieldWall;
		boolean control = area.isControlled();
		
		System.out.println("*BATTLE START*\n");
			
		if (attacker.nation.equals("Vikings"))
		{
			System.out.println("--Vikings vs. English--\n");
			
			if (area.isHub() && !vikingTerror)
			{
				defender.numFyrd = Deck.pickCard(Deck.fyrdCards);
				System.out.println("English draw " + defender.numFyrd + " Fyrds.\n");
			}
			if (spy)
			{
				ArrayList<Integer> shires = new ArrayList<Integer>();
				
				for (int j = 0; j < area.adjacentShires.size(); j++)
				{
					Shire adjacent = Map.shireList.get(area.adjacentShires.get(j));
					
					for (int k = 0; k < adjacent.adjacentShires.size(); k++)
					{
						if (!shires.contains(adjacent.adjacentShires.get(k)) && adjacent.adjacentShires.get(k) != area.location)
						{
							shires.add(adjacent.adjacentShires.get(k));
						}
					}
				}
				
				for (int i = 0; i < shires.size(); i++)
				{
					if (!Map.shireList.get(shires.get(i)).defenders.hasEnglish())
					{
						shires.remove(i--);
					}
				}
				
				if (shires.size() == 0)
				{
					System.out.println("There are no English armies up to two Shires away from this Battle that can move to join it. Spy nullified.");
					spy = false;
				}
				else
				{
					Collections.sort(shires);
					System.out.println("English, choose an army to move here to join this Battle:");
					Map.printLocations(shires);
					
					@SuppressWarnings("resource")
					Scanner sc = new Scanner(System.in);
					int army = sc.nextInt();
					
					while (!shires.contains(army))
					{
						System.out.println("Invalid number. Try again.");
						army = sc.nextInt();
					}
					
					Movement.moveArmy(Map.shireList.get(army).defenders, Map.shireList.get(army), area, false);
				}
			}
			if (danegeld)
			{
				if (attacker.numBerserk > 1 && attacker.numNorse > 1)
				{
					System.out.println("Vikings, choose to remove either 1 Berserker or up to 2 Norsemen units from your army.");
					System.out.println("Which unit type will it be?");
					
					@SuppressWarnings("resource")
					Scanner sc = new Scanner(System.in);
					String choice = sc.nextLine();
					
					while (!choice.equals("Berserker") && !choice.equals("Norsemen"))
					{
						System.out.println("Invalid response. Try again.");
						choice = sc.nextLine();
					}
					
					if (choice.equals("Berserker"))
					{
						System.out.println("1 Berserker died due to Danegeld.");
						attacker.numBerserk--;
					}
					else
					{
						if (attacker.numNorse >= 2)
						{
							System.out.println("2 Norsemen died due to Danegeld.");
							attacker.numNorse -= 2;
						}
						else
						{
							System.out.println("1 Norsemen died due to Danegeld.");
							attacker.numNorse--;
						}
					}
				}
				else if (attacker.numBerserk >= 1)
				{
					System.out.println("1 Berserker died due to Danegeld.");
					attacker.numBerserk--;
				}
				else
				{
					if (attacker.numNorse >= 2)
					{
						System.out.println("2 Norsemen died due to Danegeld.");
						attacker.numNorse -= 2;
					}
					else
					{
						System.out.println("1 Norsemen died due to Danegeld.");
						attacker.numNorse--;
					}
				}
			}
			
			while (attacker.hasVikings() && defender.hasEnglish())
			{
				if (northumbrianDiscord && (area.name.equals("Carlisle") || area.name.equals("Durham")
										 || area.name.equals("York") || area.name.equals("Manchester")))
				{
					System.out.println("The English's Thegn and Housecarl units must first roll against each other due to Northumbrian Discord.");
					Army housecarl = new Army("English", defender.numHouse, 0);
					Army thegn = new Army("English", 0, defender.numThegn);
					killUnits(housecarl, thegn, area);
					killUnits(thegn, housecarl, area);
					defender.numHouse = housecarl.numHouse;
					defender.numThegn = thegn.numBerserk;
					northumbrianDiscord = false;
				}
				if (feignedRetreat)
				{
					System.out.println("The English roll first, counting only 'flee' results due to Feigned Retreat.\n");
					rollDice(defender);
					feignedRetreat = false;
				}
				if (ambush && surpriseAttack)
				{
					ambush = false;
					surpriseAttack = false;
				}
				if (ambush)
				{
					System.out.println("The English roll first due to Ambush.\n");
					killUnits(attacker, defender, area);
					ambush = false;
				}
				if (surpriseAttack)
				{
					System.out.println("The Vikings roll first due to Surprise Attack.\n");
					killUnits(defender, attacker, area);
					surpriseAttack = false;
				}
				
				killUnits(attacker, defender, area);
				
				if (escapeIntoTheWilds)
				{
					ArrayList<Integer> shires = new ArrayList<Integer>();
					
					for (int i = 0; i < area.adjacentShires.size(); i++)
					{
						Shire s = Map.shireList.get(area.adjacentShires.get(i));
						
						if (!s.isMarsh && !s.defenders.hasVikings())
						{
							shires.add(i);
						}
					}
					
					if (shires.size() == 0)
					{
						System.out.println("There are no accessible adjacent Shires for the English army to withdraw to. Escape into the Wilds nullified.");
						escapeIntoTheWilds = false;
					}
					else
					{
						System.out.println("English, choose a Shire to withdraw all units to with Escape into the Wilds:");
						Map.printLocations(shires);
						
						@SuppressWarnings("resource")
						Scanner sc = new Scanner(System.in);
						int place = sc.nextInt();
						
						while (!shires.contains(place))
						{
							System.out.println("Invalid number. Try again.");
							place = sc.nextInt();
						}
						
						escapeIntoTheWilds = false;
						Movement.interArmy(defender, Map.shireList.get(place));
						break;
					}
				}
				else
				{
					killUnits(defender, attacker, area);
				}
			}
			
			if (attacker.hasVikings())
			{
				System.out.println("Vikings win this battle.\n");
				area.defenders = attacker;
			}
			else
			{
				System.out.println("English win this battle.\n");
				area.defenders = defender;
				
				if (defender.numFyrd > 0)
				{
					System.out.println("The English army's Fyrd units return to the Reinforcements Stockpile.\n");
					defender.numFyrd = 0;
				}
			}
		}
		else
		{
			System.out.println("--English vs. Vikings--\n");
			
			if (alfredsGambit)
			{
				attacker.numFyrd = Deck.pickCard(Deck.fyrdCards);
				System.out.println("English draw " + defender.numFyrd + " Fyrds due to Alfred's Gambit.\n");
			}
			
			while (attacker.hasEnglish() && defender.hasVikings())
			{
				if (archers)
				{
					System.out.println("The English roll first, ignoring any 'flee' results due to Archers.");
					killUnits(defender, attacker, area);
					archers = false;
				}
				if (feignedRetreat)
				{
					System.out.println("The English roll first, counting only 'flee' results due to Feigned Retreat.\n");
					rollDice(attacker);
					feignedRetreat = false;
				}
				
				killUnits(attacker, defender, area);
				killUnits(defender, attacker, area);
			}
			
			if (attacker.hasEnglish())
			{
				System.out.println("English win this battle.\n");
				area.defenders = attacker;
			}
			else
			{
				System.out.println("Vikings win this battle.\n");
				area.defenders = defender;
			}
		}
		
		if (area.isControlled() && !control)
		{
			System.out.println("Vikings now have control over " + area.name + ".\n");
			Phase.controlMarkers++;
		}
		else if (area.isCity() && control)
		{
			System.out.println("English have regained control over " + area.name + ".\n");
			Phase.controlMarkers--;
		}
		
		System.out.println("[" + area.location + "] - " + area.defenders + "\n");
		
		battles.remove(0);
		Phase.phase = "Movement";
		vikingTerror = false;
		vikingFort = false;
		ambush = false;
	}
	
	public static void killUnits(Army a, Army b, Shire area)
	{
		if (a.nation.equals("Vikings"))
		{
			int[] rolls = rollDice(b);
			
			choose(b, rolls, area);
			
			if (firstEnglishRoll && a.numBerserk > 0)
			{
				if (rolls[0] > 0)
				{
					rolls[0]--;
					a.numBerserk--;
					System.out.println("1 Berserker died, as this is the first English Battle Roll.\n");
				}
				
				firstEnglishRoll = false;
			}
			
			while (rolls[0] > 0 && a.hasVikings())
			{
				System.out.println(rolls[0] + " kills remaining.");
				System.out.println("Berserkers:" + a.numBerserk + " Norsemen:" + a.numNorse);
				System.out.println("Vikings, who would you prefer to lose?");
				
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(System.in);
				String choice = sc.nextLine();
				
				while (!isApplicable(choice, a))
				{
					System.out.println("Invalid response. Try again.");
					choice = sc.nextLine();
				}
				
				System.out.println("How many " + choice + " would you like to kill?");
				
				boolean invalid = true;
				int numKills = sc.nextInt();
				
				while (invalid && a.hasVikings())
				{
					if (choice.equals("Berserkers") && numKills > 0 && numKills <= rolls[0] && numKills <= a.numBerserk)
					{
						invalid = false;
						a.numBerserk -= numKills;
						System.out.println(numKills + " Berserkers died.\n");
					}
					else if (choice.equals("Norsemen") && numKills > 0 && numKills <= rolls[0] && numKills <= a.numNorse)
					{
						invalid = false;
						a.numNorse -= numKills;
						System.out.println(numKills + " Norsemen died.\n");
					}
					else
					{
						System.out.println("Invalid number. Try again.");
						numKills = sc.nextInt();
					}
				}
				
				rolls[0] -= numKills;
			}
		}
		else
		{
			int[] rolls = rollDice(b);
			
			choose(b, rolls, area);
			
			if (!northumbrianDiscord && vikingFort && rolls[0] > 0)
			{
				System.out.println("English lose one 'kill' result due to Viking Fort.\n");
				rolls[0]--;
			}
			if (!northumbrianDiscord && firstShieldWall && rolls[0] > 0)
			{
				System.out.println("English lose one 'kill' result due to Shield Wall.\n");
				firstShieldWall = false;
				rolls[0]--;
			}
			
			while (rolls[0] > 0 && a.hasEnglish())
			{
				System.out.println(rolls[0] + " kills remaining.");
				System.out.println("Housecarls:" + a.numHouse + " Thegns:" + a.numThegn + " Fyrds:" + a.numFyrd);
				System.out.println("English, who would you prefer to lose?");
				
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(System.in);
				String choice = sc.nextLine();
				
				while (!isApplicable(choice, a))
				{
					System.out.println("Invalid response. Try again.");
					choice = sc.nextLine();
				}
				
				System.out.println("How many " + choice + " would you like to kill?");
				
				boolean invalid = true;
				int numKills = sc.nextInt();
				
				while (invalid && a.hasEnglish())
				{
					if (choice.equals("Housecarls") && numKills > 0 && numKills <= rolls[0] && numKills <= a.numHouse)
					{
						invalid = false;
						a.numHouse -= numKills;
						System.out.println(numKills + " Housecarls died.\n");
					}
					else if (choice.equals("Thegns") && numKills > 0 && numKills <= rolls[0] && numKills <= a.numThegn)
					{
						invalid = false;
						a.numThegn -= numKills;
						System.out.println(numKills + " Thegns died.\n");
					}
					else if (choice.equals("Fyrds") && numKills > 0 && numKills <= rolls[0] && numKills <= a.numFyrd)
					{
						invalid = false;
						a.numFyrd -= numKills;
						System.out.println(numKills + " Fyrds died.\n");
					}
					else
					{
						System.out.println("Invalid number. Try again.");
						numKills = sc.nextInt();
					}
				}
				
				rolls[0] -= numKills;
			}
		}
	}
	
	public static void choose(Army a, int[] choices, Shire area)
	{
		if (!a.hasVikings() && !a.hasEnglish())
		{
			return;
		}
		
		ArrayList<Integer> locations = new ArrayList<Integer>();
		
		for (int i = 0; i < area.adjacentShires.size(); i++)
		{
			Shire s = Map.shireList.get(area.adjacentShires.get(i));
			
			if (s.defenders.nation.equals(a.nation) && (s.defenders.hasVikings() || s.defenders.hasEnglish()))
			{
				locations.add(area.adjacentShires.get(i));
			}
		}
		
		if (locations.size() == 0)
		{
			System.out.println("There are no available locations to retreat to; all choices are disbanded.\n");
			return;
		}
		
		while (choices[1] > 0 || choices[2] > 0)
		{
			if (a.nation.equals("Vikings"))
			{
				System.out.println("Vikings, would you like to stay or retreat?");
				System.out.print("(" + choices[1] + " choices left for Berserkers and " + choices[2] + " choices left for Norsemen.)");
				
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(System.in);
				String choice = sc.nextLine();
				
				while (!choice.equals("stay") && !choice.equals("retreat"))
				{
					System.out.println("Invalid response. Try again.");
					choice = sc.nextLine();
				}
				if (choice.equals("stay"))
				{
					System.out.println();
					break;
				}
				else
				{
					System.out.println("Which unit type would you like to retreat?");
					choice = sc.nextLine();
					
					while ((!choice.equals("Berserkers") || (choice.equals("Berserkers") && choices[1] <= 0))
						&& (!choice.equals("Norsemen") || (choice.equals("Norsemen") && choices[2] <= 0)))
					{
						System.out.println("Invalid response. Try again.");
						choice = sc.nextLine();
					}
					if (choice.equals("Berserkers"))
					{
						System.out.println("How many Berserkers would you like to retreat?");
						int numRetreat = sc.nextInt();
						
						while (numRetreat <= 0 || numRetreat > choices[1])
						{
							System.out.println("Invalid number. Try again.");
							numRetreat = sc.nextInt();
						}
						
						System.out.println("Which location would you like to retreat to?");
						Map.printLocations(locations);
						int location = sc.nextInt();
						
						while (!locations.contains(location))
						{
							System.out.println("Invalid number. Try again.");
							location = sc.nextInt();
						}
						
						a.numBerserk -= numRetreat;
						choices[1] -= numRetreat;
						Map.shireList.get(location).defenders.numBerserk += numRetreat;
						
						System.out.println(numRetreat + " Berserkers have fled to Shire " + location + ".\n");
						
					}
					else
					{
						System.out.println("How many Norsemen would you like to retreat?");
						int numRetreat = sc.nextInt();
						
						while (numRetreat <= 0 || numRetreat > choices[2])
						{
							System.out.println("Invalid number. Try again.");
							numRetreat = sc.nextInt();
						}
						
						System.out.println("Which location would you like to retreat to?");
						Map.printLocations(locations);
						int location = sc.nextInt();
						
						while (!locations.contains(location))
						{
							System.out.println("Invalid response. Try again.");
							location = sc.nextInt();
						}
						
						a.numNorse -= numRetreat;
						choices[2] -= numRetreat;
						Map.shireList.get(location).defenders.numNorse += numRetreat;
						
						System.out.println(numRetreat + " Norsemen have fled to Shire " + location + ".\n");
					}
					
					if (!a.hasVikings() && a.leader != null)
					{
						System.out.println("As the last unit of the Viking army retreats to another Shire, " + a.leaderNames() + " moves with it.");
					}
				}
			}
			else
			{
				System.out.println("English, would you like to stay or retreat?");
				System.out.println("(" + choices[1] + " choices left for Housecarls and " + choices[2] + " choices left for Thegns.)");
				
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(System.in);
				String choice = sc.nextLine();
				
				while (!choice.equals("stay") && !choice.equals("retreat"))
				{
					System.out.println("Invalid response. Try again.");
					choice = sc.nextLine();
				}
				if (choice.equals("stay"))
				{
					System.out.println();
					break;
				}
				else
				{
					System.out.println("Which unit type would you like to retreat?");
					choice = sc.nextLine();
					
					while ((!choice.equals("Housecarls") || (choice.equals("Housecarls") && choices[1] <= 0))
						&& (!choice.equals("Thegns") || (choice.equals("Thegns") && choices[2] <= 0)))
					{
						System.out.println("Invalid response. Try again.");
						choice = sc.nextLine();
					}
					if (choice.equals("Housecarls"))
					{
						System.out.println("How many Housecarls would you like to retreat?");
						int numRetreat = sc.nextInt();
						
						while (numRetreat <= 0 || numRetreat > choices[1])
						{
							System.out.println("Invalid number. Try again.");
							numRetreat = sc.nextInt();
						}
						
						System.out.println("Which location would you like to retreat to?");
						Map.printLocations(locations);
						int location = sc.nextInt();
						
						while (!locations.contains(location))
						{
							System.out.println("Invalid number. Try again.");
							location = sc.nextInt();
						}
						
						a.numHouse -= numRetreat;
						choices[1] -= numRetreat;
						Map.shireList.get(location).defenders.numHouse += numRetreat;
						
						System.out.println(numRetreat + " Housecarls have fled to Shire " + location + ".\n");
						
					}
					else
					{
						System.out.println("How many Thegns would you like to retreat?");
						int numRetreat = sc.nextInt();
						
						while (numRetreat <= 0 || numRetreat > choices[2])
						{
							System.out.println("Invalid number. Try again.");
							numRetreat = sc.nextInt();
						}
						
						System.out.println("Which location would you like to retreat to?");
						Map.printLocations(locations);
						int location = sc.nextInt();
						
						while (!locations.contains(location))
						{
							System.out.println("Invalid number. Try again.");
							location = sc.nextInt();
						}
						
						a.numThegn -= numRetreat;
						choices[2] -= numRetreat;
						Map.shireList.get(location).defenders.numThegn += numRetreat;
						
						System.out.println(numRetreat + " Thegns have fled to Shire " + location + ".\n");
					}
					
					if (!a.hasEnglish() && a.leader != null)
					{
						System.out.println("As the last unit of the English army retreats to another Shire, " + a.leader.name + " moves with it.");
					}	
				}
			}
		}
	}
	
	public static int[] rollDice(Army a)
	{
		int[] result = new int[3];
		
		if (a.nation.equals("Vikings"))
		{
			int berserkKills = 0;
			int norseKills = 0;
			int norseFled = 0;
			
			for (int b = (a.numBerserk >= 2 ? 2 : a.numBerserk); b > 0; b--)
			{
				switch ((int)(Math.random() * 6 + 1))
				{
					case 1:
					case 2:
					case 3:
					case 4:
						result[0]++;
						berserkKills++;
						break;
					case 5:
					case 6:
						result[1]++;
						break;
				}
			}
			for (int n = (a.numNorse >= 3 ? 3 : a.numNorse); n > 0; n--)
			{
				switch ((int)(Math.random() * 6 + 1))
				{
					case 1:
					case 2:
					case 3:
						result[0]++;
						norseKills++;
						break;
					case 4:
					case 5:
						result[2]++;
						break;
					case 6:
						a.numNorse--;
						fledStore[0]++;
						norseFled++;
						break;
				}
			}
			
			if (a.numBerserk > 0)
			{
				System.out.println("Berserkers: " + berserkKills + " kills and " + result[1] + " choices.");
			}
			if (a.numNorse > 0 || norseFled > 0)
			{
				System.out.println("Norsemen: " + norseKills + " kills, " + result[2] + " choices, and " + norseFled + " fled.");
			}
			if (a.hasVikings() || norseFled > 0)
			{
				System.out.println();
			}
		}
		else
		{
			int houseKills = 0;
			int houseFled = 0;
			int thegnKills = 0;
			int thegnFled = 0;
			int fyrdKills = 0;
			int fyrdFled = 0;
			
			for (int h = (a.numHouse >= 2 ? 2 : a.numHouse); h > 0; h--)
			{
				switch ((int)(Math.random() * 6 + 1))
				{
					case 1:
					case 2:
					case 3:
						result[0]++;
						houseKills++;
						break;
					case 4:
					case 5:
						result[1]++;
						break;
					case 6:
						a.numHouse--;
						fledStore[1]++;
						houseFled++;
						break;
				}
			}
			for (int t = (a.numThegn >= 3 ? 3 : a.numThegn); t > 0; t--)
			{
				switch ((int)(Math.random() * 6 + 1))
				{
					case 1:
					case 2:
						result[0]++;
						thegnKills++;
						break;
					case 3:
					case 4:
						result[2]++;
						break;
					case 5:
					case 6:
						a.numThegn--;
						fledStore[2]++;
						thegnFled++;
						break;
				}
			}
			for (int f = (a.numFyrd >= 2 ? 2 : a.numFyrd); f > 0; f--)
			{
				switch ((int)(Math.random() * 6 + 1))
				{
					case 1:
					case 2:
						result[0]++;
						fyrdKills++;
						break;
					case 3:
					case 4:
					case 5:
						a.numFyrd--;
						fyrdFled++;
						break;
				}
			}
			
			if (feignedRetreat)
			{
				if (a.numHouse > 0)
				{
					System.out.println("Housecarls: " + houseFled + " fled.");
				}
				if (a.numThegn > 0)
				{
					System.out.println("Thegns: " + thegnFled + " fled.");
				}
				if (a.numFyrd > 0)
				{
					System.out.println("Fyrds: " + fyrdFled + " fled.");
				}
				
				System.out.println();
			}
			else if (archers)
			{
				if (a.numHouse > 0)
				{
					System.out.println("Housecarls: " + houseKills + " kills and " + result[1] + " choices.");
					a.numHouse += houseFled;
					fledStore[1] -= houseFled;
				}
				if (a.numThegn > 0)
				{
					System.out.println("Thegns: " + thegnKills + " kills and " + result[2] + " choices.");
					a.numThegn += thegnFled;
					fledStore[2] -= thegnFled;
				}
				if (a.numFyrd > 0)
				{
					System.out.println("Fyrds: " + fyrdKills + " kills.");
					a.numFyrd += fyrdFled;
				}
				
				System.out.println();
			}
			else
			{
				if (a.numHouse > 0 || houseFled > 0)
				{
					System.out.println("Housecarls: " + houseKills + " kills, " + result[1] + " choices, and " + houseFled + " fled.");
				}
				if (a.numThegn > 0 || thegnFled > 0)
				{
					if (improvedTraining)
					{
						System.out.println("Thegns: " + thegnKills + " kills, " + result[2] + " choices, and " + thegnFled + " fled.");
					}
					else
					{
						a.numThegn += thegnFled;
						fledStore[2] -= thegnFled;
						result[0] += thegnFled;
						thegnKills += thegnFled;
						System.out.println("Thegns: " + thegnKills + " kills and " + result[2] + " choices.");
					}
				}
				if (a.numFyrd > 0 || fyrdFled > 0)
				{
					System.out.println("Fyrds: " + fyrdKills + " kills and " + fyrdFled + " fled.");
				}
				if (a.hasEnglish() || houseFled > 0 || thegnFled > 0 || fyrdFled > 0)
				{
					System.out.println();
				}
			}
		}
		
		return result;
	}
	
	public static boolean isApplicable(String choice, Army a)
	{
		ArrayList<String> armyUnits = new ArrayList<String>();
		
		if (a.nation.equals("Vikings"))
		{
			if (a.numBerserk > 0)
			{
				armyUnits.add("Berserkers");
			}
			if (a.numNorse > 0)
			{
				armyUnits.add("Norsemen");
			}
		}
		else
		{
			if (a.numHouse > 0)
			{
				armyUnits.add("Housecarls");
			}
			if (a.numThegn > 0)
			{
				armyUnits.add("Thegns");
			}
			if (a.numFyrd > 0)
			{
				armyUnits.add("Fyrds");
			}
		}
		
		return armyUnits.contains(choice);
	}
	
	public String toString()
	{
		return "[" + area.location + "] - " + "(" + attackers + ") vs. (" + defenders + ")";
	}
}