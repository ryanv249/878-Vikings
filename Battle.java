package game;

import java.util.Arrays;
import java.util.Scanner;

public class Battle {
	
	static int[] fledStore = new int[3];
	static int[] choiceStore = new int[4];
	static int killCount = 0;
	static Scanner[] killMakers = new Scanner[8];
	static Scanner[] choiceMakers = new Scanner[5];
	
	public static void main(String[] args) {
		Army testMe = new Army("English",1,1);
		Army yeeyee = new Army("Vikings",0,0);
		testMe.numFyrd = 0;

//		Battler(yeeyee, testMe);
//		Battler(testMe, yeeyee);
	}
	
	public static void Battler(Army defender, Army attacker){
		int[] rollTracker = new int[2];
		if(defender.nation.equals("English")) {
			System.out.println("****ENGLISH DEFENSE****");
			rollTracker = rollDice(defender, true);
			if(rollTracker[1] != 0)stayOrRun(choiceStore,defender);
			if(rollTracker[0] != 0)killUnits(rollTracker[0], attacker, true);
			System.out.println("\n" + "***NEXT ROLL***");
			
			while((defender.numHouse > 0 || defender.numThegn > 0 || defender.numFyrd > 0) && (attacker.numBerserk > 0 || attacker.numNorse > 0)) {
			rollTracker = rollDice(attacker, false);
			if(rollTracker[1] != 0)stayOrRun(choiceStore,attacker);
			if(rollTracker[0] != 0)killUnits(rollTracker[0], defender, false);
			if(defender.numHouse == 0 && defender.numThegn == 0 && defender.numFyrd == 0)break;
			System.out.println("\n" + "***NEXT ROLL***");
			rollTracker = rollDice(defender, false);
			if(rollTracker[1] != 0)stayOrRun(choiceStore,defender);
			if(rollTracker[0] != 0)killUnits(rollTracker[0], attacker, false);
			if(attacker.numBerserk == 0 && attacker.numNorse == 0)break;
			System.out.println("\n" + "***NEXT ROLL***");
			}
			System.out.println("\n" + "*****************" + "\n" + "   BATTLE OVER   ");
			if(defender.numHouse > 0 || defender.numThegn > 0 || defender.numFyrd > 0)System.out.println(" ENGLISH VICTORY ");
			else System.out.println(" VIKING  VICTORY ");
		}
		if(defender.nation.equals("Vikings")) {
			System.out.println("****VIKING DEFENSE****");
			rollTracker = rollDice(defender, true);
			if(rollTracker[1] != 0)stayOrRun(choiceStore,defender);
			if(rollTracker[0] != 0)killUnits(rollTracker[0], attacker, true);
			System.out.println("\n" + "***NEXT ROLL***");
			
			while((attacker.numHouse > 0 || attacker.numThegn > 0) && (defender.numBerserk > 0 || defender.numNorse > 0)) {
			rollTracker = rollDice(attacker, false);
			if(rollTracker[1] != 0)stayOrRun(choiceStore,attacker);
			if(rollTracker[0] != 0)killUnits(rollTracker[0], defender, false);
			if(defender.numBerserk == 0 && defender.numNorse == 0)break;
			System.out.println("\n" + "***NEXT ROLL***");
			rollTracker = rollDice(defender, true);
			if(rollTracker[1] != 0)stayOrRun(choiceStore,defender);
			if(rollTracker[0] != 0)killUnits(rollTracker[0], attacker, false);
			if(attacker.numHouse == 0 && attacker.numThegn == 0 && attacker.numFyrd == 0)break;
			System.out.println("\n" + "***NEXT ROLL***");
			}
			System.out.println("\n" + "*****************" + "\n" + "   BATTLE OVER   ");
			if(defender.numBerserk > 0 || defender.numNorse > 0)System.out.println(" VIKING  VICTORY ");
			else System.out.println(" ENGLISH VICTORY ");
		}

	}
	
	public static int[] rollDice(Army a , boolean defending) {	
		int[] result = new int[2];
		int numKill = 0;
		int numChoice = 0;
		int numFlee = 0;
		killCount = 0;
		//result[0] = kill result[1] = choice result[2] = flee
		//66% attack 33% choice berserk max 2 
		//50% attack, 33% choice, 16% flee norse max 3 
		//50% attack, 33% choice, 16% flee house max 2
		//33% attack, 33% choice, 33% flee thegn max 3
		//33% attack, 16% N/A, 50% flee fyrd max 2
		
		//fledStore
		//[0] = norse store
		//[1] = house store
		//[2] = thegn store
		
		//choiceStore
		//[0] = norse store
		//[1] = house store 
		//[2] = thegn store
		//[3] = berserk store
		
		//the number of kills, flees, choices is calculated using RNG according to the %possibility for each die
		//all of these count the number of choices, kills, or flees for each unit type and adds them to the array for each result type
		//they then print out all of the information in an easy to understand way
		//final # of kills and choices are returned, with flees being auto completed. 
		//the order of action is : flee (auto) -> choice(rolling team) -> kill(opposing team)
		if(a.nation.equals("Vikings")){
			if(a.numBerserk != 0) {
				if(a.numBerserk >= 2) {
					for(int j = 0; j < 2; j++) {
						int x = (int)(Math.random() * 6 + 1);
						if(x >= 4) numKill++;
						else numChoice++;
					}
					
				}
				else{
					for(int j = 0; j <= a.numBerserk; j++) {
						int x = (int)(Math.random() * 6 + 1);
						if(x >= 4)numKill++;
						else numChoice++;
					}

				}
				result[0] += numKill;
				result[1] += numChoice;
				choiceStore[3] += numChoice;
				
					System.out.println("Berserkers: " + numKill + " kills, " + numChoice + " choices.");
			}
			if(a.numBerserk <= 0)System.out.println("No Berserkers are left.");
			
			numKill = 0;
			numChoice = 0;
			if(a.numNorse != 0) {
				if(a.numNorse >= 3) {
					for(int j = 0; j <= 2; j++) {
						int x = (int)(Math.random() * 6 + 1);
						if(x >= 4)numKill++;
						else if(x <= 2)numChoice++;
						else numFlee++;
					}

				}
				else{
					for(int j = 0; j <= a.numNorse; j++) {
						int x = (int)(Math.random() * 6 + 1);
						if(x >= 4)numKill++;
						else if(x <= 2)numChoice++;
						else numFlee++;
						
					}
					
				}
				result[0] += numKill;
				result[1] += numChoice;
				
				a.numNorse -= numFlee;
				fledStore[0] += numFlee;
				choiceStore[0] += numChoice;

				
				System.out.println("Norsemen: " + numKill + " kills, " + numChoice + " choices, " + numFlee + " flees.");
				if(a.numNorse > 0)System.out.println(numFlee + " Norsemen fled, " + a.numNorse + " Norsemen are left");
			}
			if(a.numNorse <= 0)System.out.println("No Norsemen are left.");
			System.out.println(Arrays.toString(result));
			System.out.println();
		}
		if(a.nation.equals("English")) {
			if(a.numThegn != 0) {
				if(a.numThegn >= 3) {
					for(int j = 0; j <= 2; j++) {
						int x = (int)(Math.random() * 6 + 1);
						if(x >= 5)numKill++;
						else if(x <= 2)numChoice++;
						else numFlee++;
					}
				}
				else{
					for(int j = 0; j < a.numThegn; j++) {
						int x = (int)(Math.random() * 6 + 1);
						if(x >= 5)numKill++;
						else if(x <= 2)numChoice++;
						else numFlee++;
					}
				}
				result[0] += numKill;
				result[1] += numChoice;
				
				a.numThegn -= numFlee;
				fledStore[2] += numFlee;
				choiceStore[2] += numChoice;
				
				System.out.println("Thegn: " + numKill + " kills, " + numChoice + " choices, " + numFlee + " flees.");
				if(a.numThegn > 0)System.out.println(numFlee + " Thegn fled, " + a.numThegn + " Thegn are left");
				numKill = 0;
				numChoice = 0;
				numFlee = 0;
			}
			if(a.numThegn <= 0)System.out.println("No Thegn are left.");
			
			if(a.numHouse != 0) {
				if(a.numHouse >= 2) {
					for(int j = 0; j <= 1; j++) {
						int x = (int)(Math.random() * 6 + 1);
						if(x >= 4)numKill++;
						else if(x <= 2)numChoice++;
						else numFlee++;
					}
				}
				else{
					for(int j = 0; j <= a.numHouse; j++) {
						int x = (int)(Math.random() * 6 + 1);
						if(x >= 4)numKill++;
						else if(x <= 2)numChoice++;
						else numFlee++;
					}
				}
				result[0] += numKill;
				result[1] += numChoice;
				
				a.numHouse -= numFlee;
				fledStore[1] += numFlee;
				choiceStore[1] += numChoice;
				
				System.out.println("Housecarl: " + numKill + " kills, " + numChoice + " choices, " + numFlee + " flees.");
				if(a.numHouse > 0)System.out.println(numFlee + " Housecarls fled, " + a.numHouse + " Housecarls are left");
				numKill = 0;
				numChoice = 0;
				numFlee = 0;
			}
			if(a.numHouse <= 0)System.out.println("No Housecarls are left.");
			if(defending == true) {
				if(a.numFyrd != 0) {
					if(a.numFyrd >= 2){
						for(int j = 0; j <= 1; j++) {
							int x = (int)(Math.random() * 6 + 1);
							if(x >= 5)numKill++;
							else if(x <= 2)numChoice++;
							else numFlee++;
						}
					}
					else{
						for(int j = 0; j < a.numFyrd; j++) {
							int x = (int)(Math.random() * 6 + 1);
							if(x >= 5)numKill++;
							else if(x == 1)numChoice++;
							else numFlee++;
						}
					}
					result[0] += numKill;
				
					a.numFyrd -= numFlee;

					System.out.println("Fyrds: " + numKill + " kills, "  + numFlee + " flees.");
					if(a.numFyrd > 0)System.out.println(numFlee + " Fyrds fled, " + a.numFyrd + " Fyrds are left.");
					}
				if(a.numFyrd<= 0)System.out.println("No Fyrds are left.");
			}	
			System.out.println(Arrays.toString(result));
			System.out.println();
		}
//		result[0] = 0;
		killCount += result[0];
		System.out.println("Fled unit total: " + Arrays.toString(fledStore));
		if(choiceStore[0] == 0 && choiceStore[1] == 0 && choiceStore[2] == 0 && choiceStore[3] == 0)System.out.println("No Choices.");
		else{System.out.println("Total choices by unit: " + Arrays.toString(choiceStore));
		}
		if(killCount == 0)System.out.println("No kills.\n");
		else if(killCount != 0)System.out.println("Total kills: " + killCount + "\n");
		return result;
	}
	
	public static void killUnits(int killCount, Army killFrom, boolean firstHit) {
		//this method takes the amount of kills passed to it from rollDice, and will take from the army which did not roll.
		//the user input is tied to an array of Scanners, which will never run out of scanners for each pass. the max # of passes is 7.
		//the scanner first checks for each available unit type. if not correct, it will repeat until a vaild type is passed.
		//the scanner then checks for the number of kills to do, and then the number of kills is taken from the type passed beforehand
		//if all unit types for any side the battle ends
		//if you select more units to die than you have, the number does not go negative
		//you cannot select a unit type that ran out of units to remove units from
		//the initial data for each unit type is taken directly from the Army object, and stored in an array. all changes are applied to the array 
		//the final data for each unit type is returned to the Army object by the array
		
		int MAXKILLS = 0;
		int x = 0;
		int[] scanInt = new int[8];
		String[] scanString = new String[8];
		int howMany = 0;
		String storeType = "";
		int[] unitList = new int[5];
		unitList[0] = killFrom.numNorse;
		unitList[1] = killFrom.numHouse;
		unitList[2] = killFrom.numThegn;
		unitList[3] = killFrom.numBerserk;
		unitList[4] = killFrom.numFyrd;
		System.out.println("\nOpposing Team, choose your losses." + "\nKills remaining: " + killCount + "\n" + "Remaining units: " + Arrays.toString(unitList));
		if(killFrom.nation.equals("English")){
				while(killCount != 0 && (unitList[1] > 0 || unitList[2] > 0 || unitList[4] > 0)) {
					x++;
					Scanner killMaker = new Scanner(System.in);
					killMakers[x] = killMaker;
					System.out.println( "Who dies?");
					scanString[x] = killMakers[x].nextLine();
					storeType = scanString[x];
					while((!storeType.equals("Housecarls") || unitList[1] == 0) && (!storeType.equals("Thegns") || unitList[2] == 0) && (!storeType.equals("Fyrds") || unitList[4] == 0)){

						if(storeType.equals("Housecarls") && unitList[1] == 0){
							System.out.println("No housecarls left.");
						}
						else if(storeType.equals("Thegns") && unitList[2] == 0) {
							System.out.println("No Thegns left.");
						}
						else if(storeType.equals("Fyrds") && unitList[4] == 0) {
							System.out.println("No Fyrds left.");
						}
						else{
							System.out.println("Invalid unit type");
						}
						System.out.println("Who dies?");
						scanString[x] = killMakers[x].nextLine();
						storeType = scanString[x];
					}
					System.out.println("How many?");
					scanInt[x] = killMakers[x].nextInt();
					howMany = scanInt[x];
					while(scanInt[x] <= 0) {
						System.out.println("You must lose at least 1 unit of this type.");
						System.out.println("How many?");
						scanInt[x] = killMakers[x].nextInt();
						howMany = scanInt[x];
					}
					
					if(storeType.equals("Housecarls")) {
						if(howMany > killCount) {
							howMany = killCount;
						}
						if(unitList[1] - howMany < 0) {
							MAXKILLS = unitList[1];
							unitList[1] -= MAXKILLS;
							killCount -= MAXKILLS;
							System.out.println("**** " +  "All Housecarls died. ****");
						}
						else{
							unitList[1] -= howMany;
							killCount -= howMany;
							System.out.println("**** " + howMany + " Housecarls died. ****");
						}
					}
					if(storeType.equals("Thegns")) {
						if(howMany > killCount) {
							howMany = killCount;
						}
						if(unitList[2] - howMany < 0) {
							MAXKILLS = unitList[2];
							unitList[2] -= MAXKILLS;
							killCount -= MAXKILLS;
							System.out.println("**** " +  "All Thegns died. ****");
						}
						else{
							unitList[2] -= howMany;
							killCount -= howMany;
							System.out.println("**** " + howMany + " Thegns died. ****");
						}
					}
					if(storeType.equals("Fyrds")) {
						if(howMany > killCount) {
							howMany = killCount;
						}
						if(unitList[4] - howMany < 0) {
							MAXKILLS = unitList[4];
							unitList[4] -= MAXKILLS;
							killCount -= MAXKILLS;
							System.out.println("**** " +  "All Fyrds died. ****");
						}
						else{
							unitList[4] -= howMany;
							killCount -= howMany;
							System.out.println("**** " + howMany + " Fyrds died. ****");
						}
					}
					System.out.println("Kills remaining: " + killCount);	
					System.out.println("Remaining units: " + Arrays.toString(unitList));
				}
				
				
			}
		
		if(killFrom.nation.equals("Vikings")){
				while(killCount != 0 && (unitList[0] > 0 || unitList[3] > 0)) {
					x++;
					Scanner killMaker = new Scanner(System.in);
					killMakers[x] = killMaker;
					if(firstHit == true && unitList[3] > 0) {
						unitList[3]--;
						killCount--;
						System.out.println("\n**** First Strike: A Berserker Died. ****");
						System.out.println("Kills remaining: " + killCount);	
						System.out.println("Remaining units: " + Arrays.toString(unitList));
						firstHit = false;
					}
					if(killCount == 0)break;
					System.out.println("Who dies?");
					scanString[x] = killMakers[x].nextLine();
					storeType = scanString[x];
					while((!storeType.equals("Norsemen") || unitList[0] == 0) && (!storeType.equals("Berserkers") || unitList[3] == 0)){
						if(storeType.equals("Norsemen") && unitList[0] == 0){
							System.out.println("No Norsemen left.");
						}
						else if(storeType.equals("Berserkers") && unitList[3] == 0) {
							System.out.println("No Berserkers left.");
						}
						else{
							System.out.println("Invalid unit type");
						}
						System.out.println("Who dies?");
						scanString[x] = killMakers[x].nextLine();
						storeType = scanString[x];
					}
					System.out.println("How many?");
					scanInt[x] = killMakers[x].nextInt();
					howMany = scanInt[x];
					while(scanInt[x] <= 0) {
						System.out.println("You must lose at least 1 unit of this type.");
						System.out.println("How many?");
						scanInt[x] = killMakers[x].nextInt();
						howMany = scanInt[x];
					}
				
					if(storeType.equals("Norsemen")) {
						if(howMany > killCount) {
							howMany = killCount;
						}
						if(unitList[0] - howMany < 0) {
							MAXKILLS = unitList[0];
							unitList[0] -= MAXKILLS;
							killCount -= MAXKILLS;
							System.out.println("**** " +  "All Norsemen died. ****");
						}
						else{
							unitList[0] -= howMany;
							killCount -= howMany;
							System.out.println("**** " + howMany + " Norsemen died. ****");
						}
					}
					if(storeType.equals("Berserkers")) {
						if(howMany > killCount) {
							howMany = killCount;
						}						
						if(unitList[3] - howMany < 0) {
							MAXKILLS = unitList[3];
							unitList[3] -= MAXKILLS;
							killCount -= MAXKILLS;
							System.out.println("**** " +  "All Berserkers died. ****");
						}
						else{
							unitList[3] -= howMany;
							killCount -= howMany;
							System.out.println("**** " + howMany + " Berserkers died. ****");
						}
					}
					System.out.println("Kills remaining: " + killCount);	
					System.out.println("Remaining units: " + Arrays.toString(unitList));
				}
			}
		
		killFrom.numNorse = unitList[0]; 
		killFrom.numHouse = unitList[1];
		killFrom.numThegn = unitList[2];
		killFrom.numBerserk = unitList[3]; 
		killFrom.numFyrd = unitList[4]; 
//		System.out.println("Remaining units: " + Arrays.toString(unitList));
		
	}
	
	public static void stayOrRun(int[] choices, Army chooser) {
		System.out.println("Rolling team, retreat?");
		Scanner yesOrNo = new Scanner(System.in);
		String s = yesOrNo.nextLine();
		if(s.equals("yes")) {
			System.out.println("\n \n \n sorry bud not ready yet \n \n \n ");
			
			//for now
			resetArray(choiceStore);
		}
		if(s.equals("no")) {
			System.out.println("No units retreat.");
			resetArray(choiceStore);
			return;
		}
		while(!s.equals("yes") && !s.equals("no")) {
			System.out.println("Please choose yes or no.");	
			s = yesOrNo.nextLine();
			if(s.equals("yes")) {
				System.out.println("\n \n \n sorry bud not ready yet \n \n \n ");
				
				//for now
				resetArray(choiceStore);
				break;
			}
			if(s.equals("no")) {
				System.out.println("No units retreat. \n");
				resetArray(choiceStore);
				break;
			}
		}	
		
	}
//	public static Shire runAway(int[] numRunners, Army RunningArmy, )
	
	public static void resetArray(int[] resetMe) {
		for(int j = 0; j < resetMe.length; j++) {
			resetMe[j] = 0;
		}
	}
}