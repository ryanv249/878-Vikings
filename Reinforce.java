package vikingbattle;

import java.util.ArrayList;
import java.util.Scanner;

/* Notes:
 * Add reinforcement method for "Reinforcements" leader.
 * That "synchronized" method below is just for cosmetic purposes.
 */

public class Reinforce
{
	public static void main(String[] args)
	{
		
	}
	
	public static synchronized void printCards()
	{
		ArrayList<String> cardNames = new ArrayList<String>();
		ArrayList<Card> allCards = new ArrayList<Card>();
		allCards.addAll(Deck.berserkerCards);
		allCards.addAll(Deck.norsemenCards);
		allCards.addAll(Deck.housecarlCards);
		allCards.addAll(Deck.thegnCards);
		
		for (int i = 0; i < allCards.size(); i++)
		{
			Card c = allCards.get(i);
			
			if (!cardNames.contains(c.name()) && !c.name().equals("Movement"))
			{
				cardNames.add(c.name());
				System.out.println(c);
			}
		}
	}
	
	public static void fledDist(Player p)
	{
		boolean fleeing = true;
		
		if (p.name.equals("Berserker") || p.name.equals("Norsemen"))
		{
			distribute(findOpen("Vikings", fleeing));
		}
		else if (p.name.equals("Housecarl") || p.name.equals("Thegn"))
		{
			distribute(findOpen("English", fleeing));
		}
	}
	
	public static ArrayList<Integer> findOpen(String nation, boolean fledUnits)
	{
		ArrayList<Integer> openShires = new ArrayList<Integer>();
		
		if (nation.equals("Vikings"))
		{
			for (int i = 1; i < Map.shireList.size(); i++)
			{
				Shire s = Map.shireList.get(i);
				
				if (s.defenders.leader != null || (s.isCoastal() && s.isControlled()))
				{
					openShires.add(i);
				}
			}
		}
		else
		{
			for (int i = 1; i < Map.shireList.size(); i++)
			{
				Shire s = Map.shireList.get(i);
				
				if ((!s.isControlled() && s.isHub()) || (fledUnits && s.defenders.leader.name.equals("Alfred's Army")))
				{
					openShires.add(i);
				}
			}
		}
		
		return openShires;
	}
	
	public static ArrayList<Integer> findOpen(Leader l)
	{
		ArrayList<Integer> open = new ArrayList<Integer>();
		
		if (!l.name.equals("Alfred's Army"))
		{
			for (int i = 1; i < Map.shireList.size(); i++)
			{
				Shire s = Map.shireList.get(i);
				
				if (l.description.contains(s.coast) || (l.description.contains("any coast") && s.isCoastal()))
				{
					open.add(i);
				}
			}
		}
		else
		{
			for (int i = 1; i < Map.shireList.size(); i++)
			{
				Shire s = Map.shireList.get(i);
				
				if (!s.isControlled() && s.isHub())
				{
					open.add(i);
				}
			}
		}
		
		return open;
	}
	
	public static void distribute(ArrayList<Integer> open)
	{
		if (Phase.player.name.equals("Norsemen"))
		{
			System.out.println("Norsemen, distribute your men to the following Shires: ");
			Map.printLocations(open);
			
			while (Battle.fledStore[0] > 0)
			{
				System.out.println(Battle.fledStore[0] + " Norsemen remaining.");
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(System.in);
				int place = sc.nextInt();
				
				while (!open.contains(place))
				{
					System.out.println("Invalid number. Try again.");
					place = sc.nextInt();
				}
				
				System.out.println("How many men would you like to move to Shire " + place + "?");
				int numMen = sc.nextInt();
				
				while (numMen < 0 || numMen > Battle.fledStore[0])
				{
					System.out.println("Invalid number. Try again.");
					numMen = sc.nextInt();
				}
				
				System.out.println(numMen + " Norsemen moved to Shire " + place + ".\n");
				Battle.fledStore[0] -= numMen;
				Map.shireList.get(place).defenders.numNorse += numMen;
			}
		}
		if (Phase.player.name.equals("Housecarl"))
		{
			System.out.println("Housecarl, distribute your men to the following Shires: ");
			Map.printLocations(open);
			
			while (Battle.fledStore[1] > 0)
			{
				System.out.println(Battle.fledStore[1] + " Housecarls remaining.");
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(System.in);
				int place = sc.nextInt();
				
				while (!open.contains(place))
				{
					System.out.println("Invalid number. Try again.");
					place = sc.nextInt();
				}
				
				System.out.println("How many men would you like to move to Shire " + place + "?");
				int numMen = sc.nextInt();
				
				while (numMen < 0 || numMen > Battle.fledStore[1])
				{
					System.out.println("Invalid number. Try again.");
					numMen = sc.nextInt();
				}
				
				System.out.println(numMen + " Housecarls moved to Shire " + place + ".\n");
				Battle.fledStore[1] -= numMen;
				Map.shireList.get(place).defenders.numHouse += numMen;
			}
		}
		if (Phase.player.name.equals("Thegn"))
		{
			System.out.println("Thegn, distribute your men to the following Shires: ");
			Map.printLocations(open);
			
			while (Battle.fledStore[2] > 0)
			{
				System.out.println(Battle.fledStore[2] + " Thegns remaining.");
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(System.in);
				int place = sc.nextInt();
				
				while (!open.contains(place))
				{
					System.out.println("Invalid number. Try again.");
					place = sc.nextInt();
				}
				
				System.out.println("How many men would you like to move to Shire " + place + "?");
				int numMen = sc.nextInt();
				
				while (numMen < 0 || numMen > Battle.fledStore[2])
				{
					System.out.println("Invalid number. Try again.");
					numMen = sc.nextInt();
				}
				
				System.out.println(numMen + " Thegns moved to Shire " + place + ".\n");
				Battle.fledStore[2] -= numMen;
				Map.shireList.get(place).defenders.numThegn += numMen;
			}
		}
	}
	
	public static void distribute(ArrayList<Integer> open, int numNorse, int numBerserk)
	{
		System.out.println("Norsemen, distribute your men to the following Shires: ");
		Map.printLocations(open);
		
		while (numNorse > 0)
		{
			System.out.println(numNorse + " Norsemen remaining.");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			int place = sc.nextInt();
			
			while (!open.contains(place))
			{
				System.out.println("Invalid number. Try again.");
				place = sc.nextInt();
			}
			
			System.out.println("How many men would you like to move to Shire " + place + "?");
			int numMen = sc.nextInt();
			
			while (numMen < 0 || numMen > numNorse)
			{
				System.out.println("Invalid number. Try again.");
				numMen = sc.nextInt();
			}
			
			System.out.println(numMen + " Norsemen moved to Shire " + place + ".\n");
			numNorse -= numMen;
			Map.shireList.get(place).defenders.numNorse += numMen;
		}
		while (numBerserk > 0)
		{
			System.out.println(numBerserk + " Berserkers remaining.");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			int place = sc.nextInt();
			
			while (!open.contains(place))
			{
				System.out.println("Invalid number. Try again.");
				place = sc.nextInt();
			}
			
			System.out.println("How many men would you like to move to Shire " + place + "?");
			int numMen = sc.nextInt();
			
			while (numMen < 0 || numMen > numBerserk)
			{
				System.out.println("Invalid number. Try again.");
				numMen = sc.nextInt();
			}
			
			System.out.println(numMen + " Berserkers moved to Shire " + place + ".\n");
			numBerserk -= numMen;
			Map.shireList.get(place).defenders.numBerserk += numMen;
		}
	}
	
	public static void placeLeader(Leader l)
	{
		ArrayList<Integer> open = new ArrayList<Integer>(findOpen(l));
		
		System.out.println(Phase.player.name + ", place " + l.name + " in any of the following Shires: ");
		Map.printLocations(open);
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int place = sc.nextInt();
		
		while (!open.contains(place))
		{
			System.out.println("Invalid number. Try again.");
			place = sc.nextInt();
		}
		
		Movement.interArmy(l.toArmy(), Map.shireList.get(place));
	}
}