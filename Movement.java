package vikingbattle;

import java.util.ArrayList;
import java.util.Scanner;

/* Notes:
 * Add mergeArmy() method, or some sort of utility to keep track of moving armies, how many moves they have, etc. for combination purposes.
 * Create a leader movement method.
 */

public class Movement
{
	public static boolean vikingShips, thegnLeadership;
	public static ArrayList<Integer> saxonNavy = new ArrayList<Integer>();
	
	public static void move(Player p, int numArmies, int numMoves)
	{
		// Going to use this later for merging armies/keeping track of their location.
		ArrayList<Army> armies = new ArrayList<Army>();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		if (thegnLeadership)
		{
			numMoves += 2;
			thegnLeadership = false;
		}
		
		while (numArmies > 0)
		{	
			if (armies.size() == 0)
			{
				System.out.println(p.name + ", would you like to move an army?");
			}
			else
			{
				System.out.println(p.name + ", would you like to move any more armies?");
			}
			
			String choice = sc.nextLine();
			
			while (!choice.equals("yes") && !choice.equals("no"))
			{
				System.out.println("Invalid response. Try again.");
				choice = sc.nextLine();
			}
			
			if (choice.equals("no"))
			{
				break;
			}
			else
			{
				ArrayList<Integer> shires = new ArrayList<Integer>();
				
				for (int i = 1; i < Map.shireList.size(); i++)
				{
					Army a = Map.shireList.get(i).defenders;
					
					if ((p.name.equals("Berserker") && a.numBerserk > 0) || (p.name.equals("Norsemen") && a.numNorse > 0)
					|| (p.name.equals("Housecarl") && a.numHouse > 0) || (p.name.equals("Thegn") && a.numThegn > 0))
					{
						shires.add(i);
					}
				}
				
				numArmies--;
				System.out.println("Choose an army to move:");
				Map.printLocations(shires);
				int army = sc.nextInt();
				int moves = numMoves;
				
				while (!shires.contains(army))
				{
					System.out.println("Invalid number. Try again.");
					army = sc.nextInt();
				}
				
				Shire s = Map.shireList.get(army);
				Army a = new Army(s.defenders);
				boolean firstMove = true;
				
				while (moves > 0)
				{
					if (!firstMove)
					{
						System.out.println("Would you like to keep moving?");
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
						else if (a.leader != null)
						{
							int numBerserk = s.defenders.numBerserk - a.numBerserk;
							int numNorse = s.defenders.numNorse - a.numNorse;
							int numHouse = s.defenders.numHouse - a.numHouse;
							int numThegn = s.defenders.numThegn - a.numThegn;
							
							if (numBerserk > 0 || numNorse > 0 || numHouse > 0 || numThegn > 0)
							{
								System.out.println("Would you like to pick up any units in this Shire before moving?");
								
								if (a.nation.equals("Vikings"))
								{
									System.out.println("(" + numBerserk + " Berserkers and " + numNorse + " Norsemen remaining.)");
								}
								else
								{
									System.out.println("(" + numHouse + " Housecarls and " + numThegn + " Thegns remaining.)");
								}
								
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
								
								if (a.nation.equals("Vikings"))
								{
									if (numBerserk > 0)
									{
										System.out.println("How many Berserkers?");
										int units = sc.nextInt();
										
										while (units < 0 || units > numBerserk)
										{
											System.out.println("Invalid number. Try again.");
											units = sc.nextInt();
										}
										
										a.numBerserk += units;
									}
									if (numNorse > 0)
									{
										System.out.println("How many Norsemen?");
										int units = sc.nextInt();
										
										while (units < 0 || units > numNorse)
										{
											System.out.println("Invalid number. Try again.");
											units = sc.nextInt();
										}
										
										a.numNorse += units;
									}
								}
								else
								{
									if (numHouse > 0)
									{
										System.out.println("How many Housecarls?");
										int units = sc.nextInt();
										
										while (units < 0 || units > numHouse)
										{
											System.out.println("Invalid number. Try again.");
											units = sc.nextInt();
										}
										
										a.numHouse += units;
									}
									if (numThegn > 0)
									{
										System.out.println("How many Thegns?");
										int units = sc.nextInt();
										
										while (units < 0 || units > numThegn)
										{
											System.out.println("Invalid number. Try again.");
											units = sc.nextInt();
										}
										
										a.numThegn += units;
									}
								}
							}
						}
					}
					
					ArrayList<Integer> adjacentShires = new ArrayList<Integer>(s.adjacentShires);
					
					for (int i = 0; i < adjacentShires.size(); i++)
					{
						Shire as = Map.shireList.get(adjacentShires.get(i));
						
						if (as.isMarsh || (s.defenders.nation.equals("Vikings") && saxonNavy.contains(as.location)))
						{
							adjacentShires.remove(i--);
						}
					}
					
					if (vikingShips && s.isCoastal())
					{
						ArrayList<Integer> coastalShires = new ArrayList<Integer>();
						
						for (int i = 1; i < Map.shireList.size(); i++)
						{
							Shire cs = Map.shireList.get(i);
							
							if (cs.coast.contains(s.coast) && !cs.isMarsh && !saxonNavy.contains(i) && s.location != i)
							{
								coastalShires.add(i);
							}
						}
						
						System.out.println("Would you like to use Viking Ships to move this army to another " + s.coast + " Shire?");
						choice = sc.nextLine();
						
						while (!choice.equals("yes") && !choice.equals("no"))
						{
							System.out.println("Invalid response. Try again.");
							choice = sc.nextLine();
						}
						
						if (choice.equals("yes"))
						{
							adjacentShires = new ArrayList<Integer>(coastalShires);
							vikingShips = false;
						}
					}
					
					if (adjacentShires.size() == 0)
					{
						System.out.println("All adjacent Shires are currently inaccessible. All moves canceled for this Army.\n");
						break;
					}
					
					System.out.println("Choose a location to move this army (currently in Shire " + army + "):");
					Map.printLocations(adjacentShires);
					int location = sc.nextInt();
					
					while (!adjacentShires.contains(location))
					{
						System.out.println("Invalid number. Try again.");
						location = sc.nextInt();
					}
					
					int battleCheck = Battle.battles.size();
					Movement.moveArmy(a, s, Map.shireList.get(location), firstMove);
					
					if (battleCheck < Battle.battles.size())
					{
						moves = 0;
					}
					
					firstMove = false;
					army = location;
					s = Map.shireList.get(army);
				}
			}
		}
	}
	
	public static void moveArmy(Army moved, Shire start, Shire end, boolean firstMove)
	{
		Army stayed = start.defenders;
		
		if (stayed.nation.equals("Vikings"))
		{
			stayed.numBerserk -= moved.numBerserk;
			stayed.numNorse -= moved.numNorse;
			
			if ((stayed.leaders.size() > 0 || stayed.leader != null) && moved.leader != null)
			{
				stayed.leaders.add(0, stayed.leader);
				stayed.leader = null;
				moved.leaders.add(0, moved.leader);
				
				for (int i = 0; i < stayed.leaders.size(); i++)
				{
					if (moved.leaders.contains(stayed.leaders.get(i)))
					{
						stayed.leaders.remove(i--);
					}
				}
				
				stayed.leader = (stayed.leaders.size() > 0 ? stayed.leaders.get(0) : null);
				moved.leaders.remove(0);
			}
		}
		else
		{
			stayed.numHouse -= moved.numHouse;
			stayed.numThegn -= moved.numThegn;
			
			if (moved.leader != null)
			{
				stayed.leader = null;
			}
		}
		
		if (moved.nation.equals("Vikings") && (moved.leader != null || firstMove))
		{
			if (firstMove)
			{
				System.out.println(Phase.player.name + ", would you like to omit any units from your army before moving?");
			}
			else
			{
				System.out.println(moved.leaderNames() + ", would you like to leave any of your units behind?");
			}
			if (!start.isControlled() && start.isCity())
			{
				System.out.println("Note: Not leaving any units behind will cause the English to regain control of this City Shire.");
			}
			System.out.print("(" + moved.numBerserk + " Berserkers and " + moved.numNorse + " Norsemen remaining.)\n");
			
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			String choice = sc.nextLine();
			
			while (!choice.equals("yes") && !choice.equals("no"))
			{
				System.out.println("Invalid response. Try again.");
				choice = sc.nextLine();
			}
			if (choice.equals("yes"))
			{
				if (moved.numBerserk > 0)
				{
					System.out.println("How many Berserkers?");
					int numBerserk = sc.nextInt();
					
					while ((numBerserk < 0 && numBerserk > moved.numBerserk) || (Phase.player.name.equals("Berserker") && numBerserk == moved.numBerserk))
					{
						System.out.println("Invalid number. Try again.");
						numBerserk = sc.nextInt();
					}
					
					moved.numBerserk -= numBerserk;
					stayed.numBerserk += numBerserk;
				}
				if (moved.numNorse > 0)
				{
					System.out.println("How many Norsemen?");
					int numNorse = sc.nextInt();
					
					while ((numNorse < 0 && numNorse > moved.numNorse) || (Phase.player.name.equals("Norsemen") && numNorse == moved.numNorse))
					{
						System.out.println("Invalid number. Try again.");
						numNorse = sc.nextInt();
					}
					
					moved.numNorse -= numNorse;
					stayed.numNorse += numNorse;
				}
				if ((firstMove && moved.leader != null) || moved.leaders.size() > 0)
				{
					if (moved.leaders.size() > 0)
					{
						System.out.println("Would you like to leave any leaders behind?");
						choice = sc.nextLine();
						
						while (!choice.equals("yes") && !choice.equals("no"))
						{
							System.out.println("Invalid response. Try again.");
							choice = sc.nextLine();
						}
						
						if (choice.equals("yes"))
						{
							moved.leaders.add(0, moved.leader);
							moved.leader = null;
							
							for (int i = 0; i < moved.leaders.size(); i++)
							{
								System.out.println("Do you want to leave " + moved.leaders.get(i).name + " behind?");
								choice = sc.nextLine();
								
								while (!choice.equals("yes") && !choice.equals("no"))
								{
									System.out.println("Invalid response. Try again.");
									choice = sc.nextLine();
								}
								
								if (choice.equals("yes"))
								{
									if (stayed.leader == null)
									{
										stayed.leader = moved.leaders.remove(i--);
									}
									else
									{
										stayed.leaders.add(moved.leaders.remove(i--));
									}
								}
							}
						}
					}
					else
					{
						System.out.println("Would you like to leave " + moved.leader.name + " behind?");
						
						while (!choice.equals("yes") && !choice.equals("no"))
						{
							System.out.println("Invalid response. Try again.");
							choice = sc.nextLine();
						}
						
						if (choice.equals("yes"))
						{
							stayed.leader = moved.leader;
							moved.leader = null;
						}
					}
				}
			}
		}
		else if (moved.leader != null || firstMove)
		{
			if (firstMove)
			{
				System.out.println(Phase.player.name + ", would you like to omit any units from your army before moving?");
			}
			else
			{
				System.out.println(moved.leader.name + ", would you like to leave any of your units behind?");
			}
			System.out.print("(" + moved.numHouse + " Housecarls and " + moved.numThegn + " Thegns remaining.)\n");
			
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			String choice = sc.nextLine();
			
			while (!choice.equals("yes") && !choice.equals("no"))
			{
				System.out.println("Invalid response. Try again.");
				choice = sc.nextLine();
			}
			if (choice.equals("yes"))
			{
				if (moved.numHouse > 0)
				{
					System.out.println("How many Housecarls?");
					int numHouse = sc.nextInt();
					
					while ((numHouse < 0 && numHouse > moved.numHouse) || (Phase.player.name.equals("Housecarl") && numHouse == moved.numHouse))
					{
						System.out.println("Invalid number. Try again.");
						numHouse = sc.nextInt();
					}
					
					moved.numHouse -= numHouse;
					stayed.numHouse += numHouse;
				}
				if (moved.numThegn > 0)
				{
					System.out.println("How many Thegns?");
					int numThegn = sc.nextInt();
					
					while ((numThegn < 0 && numThegn > moved.numThegn) || (Phase.player.name.equals("Thegn") && numThegn == moved.numThegn))
					{
						System.out.println("Invalid number. Try again.");
						numThegn = sc.nextInt();
					}
					
					moved.numThegn -= numThegn;
					stayed.numThegn += numThegn;
				}
				if (firstMove && moved.leader != null)
				{
					System.out.println("Would you like to leave " + moved.leader.name + " behind?");
					choice = sc.nextLine();
					
					while (!choice.equals("yes") && !choice.equals("no"))
					{
						System.out.println("Invalid response. Try again.");
						choice = sc.nextLine();
					}
					
					if (choice.equals("yes"))
					{
						stayed.leader = moved.leader;
						moved.leader = null;
					}
				}
			}
		}
		
		if (stayed.nation.equals("Vikings") && !stayed.hasVikings())
		{
			stayed.nation = "English";
			
			if (stayed.leader != null)
			{
				System.out.println(stayed.leaderNames() + " moved along with the rest of the Viking army to avoid elimination.\n");
				
				if (moved.leader == null)
				{
					moved.leader = stayed.leader;
				}
				else
				{
					moved.leaders.add(stayed.leader);
				}
				
				moved.leaders.addAll(stayed.leaders);
				stayed.leader = null;
				stayed.leaders.clear();
			}
			if (start.isCity())
			{
				System.out.println("English have regained control over " + start.name + ".\n");
				Phase.controlMarkers--;
			}
		}
		
		System.out.println("[" + start.location + "] - " + start.defenders);
		
		interArmy(moved, end);
	}
	
	public static void interArmy(Army a, Shire area)
	{
		if (area.defenders.nation.equals(a.nation))
		{
			if (a.nation.equals("Vikings"))
			{
				area.defenders.numBerserk += a.numBerserk;
				area.defenders.numNorse += a.numNorse;
			}
			else
			{
				area.defenders.numHouse += a.numHouse;
				area.defenders.numThegn += a.numThegn;
			}
			
			if (a.leader != null)
			{
				if (area.defenders.leader == null)
				{
					area.defenders.leader = a.leader;
				}
				else
				{
					area.defenders.leaders.add(a.leader);
				}
				
				area.defenders.leaders.addAll(a.leaders);
			}
			
			System.out.println("[" + area.location + "] - " + area.defenders);
		}
		else if (a.nation.equals("Vikings") && !area.defenders.hasEnglish())
		{
			area.defenders.nation = "Vikings";
			area.defenders.numBerserk = a.numBerserk;
			area.defenders.numNorse = a.numNorse;
			area.defenders.leader = a.leader;
			area.defenders.leaders = a.leaders;
			
			System.out.println("[" + area.location + "] - " + area.defenders);
			
			if (area.isControlled())
			{
				System.out.println("\nVikings now have control over " + area.name + ".\n");
				Phase.controlMarkers++;
			}
		}
		else if (a.nation.equals("English") && !area.defenders.hasVikings())
		{
			area.defenders.nation = "English";
			area.defenders.numHouse = a.numHouse;
			area.defenders.numThegn = a.numThegn;
			area.defenders.numFyrd = a.numFyrd;
			area.defenders.leader = a.leader;
			
			System.out.println("[" + area.location + "] - " + area.defenders);
			
			if (area.isCity())
			{
				System.out.println("English have regained control over " + area.name + ".\n");
				Phase.controlMarkers--;
			}
			if (a.numFyrd > 0)
			{
				System.out.println("The English army's Fyrd units return to the Reinforcements Stockpile.\n");
				a.numFyrd = 0;
			}
		}
		else
		{
			Battle.battles.add(new Battle(a, area.defenders, area));
			System.out.println("[" + area.location + "] - " + "(" + a + ") vs. (" + area.defenders + ")");
			
//			if (a.leader != null)
//			{
//				Battle.startBattle(a, area.defenders, area);
//			}
		}
	}
}
