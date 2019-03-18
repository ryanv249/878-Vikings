package vikingbattle;

import java.util.ArrayList;
import java.util.Scanner;

public class BattleMain
{
	/** [0] == Norse, [1] == Housecarl, [2] == Thegn */
	static int[] fledStore = new int[3];
	
	public static void main(String[] args)
	{
		Shire s = Map.shireList.get(2);
		Army v = new Army("Vikings", 2, 4);
		Army e = new Army("English", 4, 6);
		
		v.leader = Deck.pickCard(Deck.leaderCards);
		v.numBerserk += v.leader.numBerserk;
		v.numNorse += v.leader.numNorse;
		
		startBattle(v, e, s);
		
		System.out.println(v + "\n\n" + e);
	}
	
	public static void startBattle(Army attacker, Army defender, Shire area)
	{
		if (attacker.nation.equals("Vikings"))
		{
			defender.numFyrd = Deck.pickCard(Deck.fyrdCards);
			
			killUnits(attacker, defender);
			killUnits(defender, attacker);
		}
		else
		{
			killUnits(attacker, defender);
			killUnits(defender, attacker);
		}
	}
	
	public static void killUnits(Army a, Army b)
	{
		if (a.nation.equals("Vikings"))
		{
			int[] rolls = rollDice(b);
			
			choose(b, rolls);
			
			while (rolls[0] > 0 && (a.numBerserk > 0 || a.numNorse > 0))
			{
				System.out.println(rolls[0] + " kills remaining.");
				System.out.println("Berserkers:" + a.numBerserk + " Norsemen:" + a.numNorse);
				System.out.println("Vikings, who would you prefer to lose?");
				
				@SuppressWarnings("resource")
				Scanner executioner = new Scanner(System.in);
				String choice = executioner.nextLine();
				
				while (!isApplicable(choice, a))
				{
					System.out.println("Invalid choice. Try again.");
					choice = executioner.nextLine();
				}
				
				System.out.println("How many " + choice + " would you like to kill?");
				
				boolean invalid = true;
				int numKills = executioner.nextInt();
				
				while (invalid && (a.numBerserk > 0 || a.numNorse > 0))
				{
					if (choice.equals("Berserkers") && numKills > 0 && numKills <= rolls[0] && numKills <= a.numBerserk)
					{
						invalid = false;
						a.numBerserk -= numKills;
						System.out.println(numKills + " Berserkers were killed.\n");
					}
					else if (choice.equals("Norsemen") && numKills > 0 && numKills <= rolls[0] && numKills <= a.numNorse)
					{
						invalid = false;
						a.numNorse -= numKills;
						System.out.println(numKills + " Norsemen were killed.\n");
					}
					else
					{
						System.out.println("Invalid number. Try again.");
						numKills = executioner.nextInt();
					}
				}
				
				rolls[0] -= numKills;
			}
		}
		else
		{
			int[] rolls = rollDice(b);
			
			choose(b, rolls);
			
			while (rolls[0] > 0 && (a.numHouse > 0 || a.numThegn > 0 || a.numFyrd > 0))
			{
				System.out.println(rolls[0] + " kills remaining.");
				System.out.println("Housecarls:" + a.numHouse + " Thegns:" + a.numThegn + " Fyrds:" + a.numFyrd);
				System.out.println("English, who would you prefer to lose?");
				
				@SuppressWarnings("resource")
				Scanner executioner = new Scanner(System.in);
				String choice = executioner.nextLine();
				
				while (!isApplicable(choice, a))
				{
					System.out.println("Invalid choice. Try again.");
					
					choice = executioner.nextLine();
				}
				
				System.out.println("How many " + choice + " would you like to kill?");
				
				boolean invalid = true;
				int numKills = executioner.nextInt();
				
				while (invalid && (a.numHouse > 0 || a.numThegn > 0 || a.numFyrd > 0))
				{
					if (choice.equals("Housecarls") && numKills > 0 && numKills <= rolls[0] && numKills <= a.numHouse)
					{
						invalid = false;
						a.numHouse -= numKills;
						System.out.println(numKills + " Housecarls were killed.\n");
					}
					else if (choice.equals("Thegns") && numKills > 0 && numKills <= rolls[0] && numKills <= a.numThegn)
					{
						invalid = false;
						a.numThegn -= numKills;
						System.out.println(numKills + " Thegns were killed.\n");
					}
					else if (choice.equals("Fyrds") && numKills > 0 && numKills <= rolls[0] && numKills <= a.numFyrd)
					{
						invalid = false;
						a.numFyrd -= numKills;
						System.out.println(numKills + " Fyrds were killed.\n");
					}
					else
					{
						System.out.println("Invalid number. Try again.");
						numKills = executioner.nextInt();
					}
				}
					
				rolls[0] -= numKills;
			}
		}
	}
	
	public static void choose(Army a, int[] choices)
	{
		while (choices[1] > 0 || choices[2] > 0)
		{
			if (a.nation.equals("Vikings"))
			{
				System.out.println("Vikings, would you like to stay or flee?");
				System.out.print("(" + choices[1] + " choices left for Berserkers and " + choices[2] + " choices left for Norsemen.)");
				
				@SuppressWarnings("resource")
				Scanner decisions = new Scanner(System.in);
				String choice = decisions.nextLine();
				
				while (!choice.equals("stay") && !choice.equals("flee"))
				{
					System.out.println("Invalid decision. Try again.");
					choice = decisions.nextLine();
				}
				if (choice.equals("stay"))
				{
					break;
				}
				else
				{
					System.out.println("Which unit type would you like to flee?");
					
					choice = decisions.nextLine();
					
					while ((!choice.equals("Berserkers") || (choice.equals("Berserkers") && choices[1] <= 0))
						&& (!choice.equals("Norsemen") || (choice.equals("Norsemen") && choices[2] <= 0)))
					{
						System.out.println("Invalid decision. Try again.");
						choice = decisions.nextLine();
					}
					if (choice.equals("Berserkers"))
					{
						System.out.println("How many Berserkers would you like to flee?");
						
						int numFlee = decisions.nextInt();
						
						while (numFlee <= 0 || numFlee > choices[1])
						{
							System.out.println("Invalid number. Try again.");
							numFlee = decisions.nextInt();
						}
						
						a.numBerserk -= numFlee;
						choices[1] -= numFlee;
						
						System.out.println(numFlee + " Berserkers have fled (map locations are currently unimplemented).\n");
						
					}
					else
					{
						System.out.println("How many Norsemen would you like to flee?");
						
						int numFlee = decisions.nextInt();
						
						while (numFlee <= 0 || numFlee > choices[2])
						{
							System.out.println("Invalid number. Try again.");
							numFlee = decisions.nextInt();
						}
						
						a.numNorse -= numFlee;
						choices[2] -= numFlee;
						
						System.out.println(numFlee + " Norsemen have fled (map locations are currently unimplemented).\n");
					}
				}
			}
			else
			{
				System.out.println("English, would you like to stay or flee?");
				System.out.println("(" + choices[1] + " choices left for Housecarls and " + choices[2] + " choices left for Thegns.)");
				
				@SuppressWarnings("resource")
				Scanner decisions = new Scanner(System.in);
				String choice = decisions.nextLine();
				
				while (!choice.equals("stay") && !choice.equals("flee"))
				{
					System.out.println("Invalid decision. Try again.");
					choice = decisions.nextLine();
				}
				if (choice.equals("stay"))
				{
					break;
				}
				else
				{
					System.out.println("Which unit type would you like to flee?");
					
					choice = decisions.nextLine();
					
					while ((!choice.equals("Housecarls") || (choice.equals("Housecarls") && choices[1] <= 0))
						&& (!choice.equals("Thegns") || (choice.equals("Thegns") && choices[2] <= 0)))
					{
						System.out.println("Invalid decision. Try again.");
						choice = decisions.nextLine();
					}
					if (choice.equals("Housecarls"))
					{
						System.out.println("How many Housecarls would you like to flee?");
						
						int numFlee = decisions.nextInt();
						
						while (numFlee <= 0 || numFlee > choices[1])
						{
							System.out.println("Invalid number. Try again.");
							numFlee = decisions.nextInt();
						}
						
						a.numHouse -= numFlee;
						choices[1] -= numFlee;
						
						System.out.println(numFlee + " Housecarls have fled (map locations are currently unimplemented).\n");
						
					}
					else
					{
						System.out.println("How many Thegns would you like to flee?");
						
						int numFlee = decisions.nextInt();
						
						while (numFlee <= 0 || numFlee > choices[2])
						{
							System.out.println("Invalid number. Try again.");
							numFlee = decisions.nextInt();
						}
						
						a.numThegn -= numFlee;
						choices[2] -= numFlee;
						
						System.out.println(numFlee + " Thegns have fled (map locations are currently unimplemented).\n");
					}
				}
			}
		}
	}
	
	/** Returns the number of kills, number of choices for troop 1, and number of choices for troop 2 in an array. <p>
	 * Dice roll from 1-6: <p>
	 * Berserkers: 1-4 == Kill, 5-6 == Choice <p>
	 * Norsemen: 1-3 == Kill, 4-5 == Choice, 6 == Flee <p>
	 * Housecarls: 1-3 == Kill, 4-5 == Choice, 6 == Flee <p>
	 * Therns: 1-2 == Kill, 3-4 == Choice, 5-6 == Flee <p>
	 * Fyrd: 1-2 == Kill, 3-5 == Flee, 6 == none <p>
	 */
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
			
			if (a.numHouse > 0 || houseFled > 0)
			{
				System.out.println("Housecarls: " + houseKills + " kills, " + result[1] + " choices, and " + houseFled + " fled.");
			}
			if (a.numThegn > 0 || thegnFled > 0)
			{
				System.out.println("Thegns: " + thegnKills + " kills, " + result[2] + " choices, and " + thegnFled + " fled.");
			}
			if (a.numFyrd > 0 || fyrdFled > 0)
			{
				System.out.println("Fyrds: " + fyrdKills + " kills and " + fyrdFled + " fled.");
			}
		}
		
		System.out.println();
		
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
		
		if (armyUnits.contains(choice))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}