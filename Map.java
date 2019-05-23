package vikingbattle;

import java.util.ArrayList;
import java.util.Arrays;

public class Map
{
	static ArrayList<Shire> shireList = new ArrayList<Shire>(Arrays.asList(new Shire(0, "The Void", "Testing Shire", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63)), 0, 0, false, false),
																		   new Shire(1, "Kingdom of Northumbria", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(2, 3, 4)), 0, 0, false, false),
																		   new Shire(2, "Kingdom of Northumbria", "Durham", "North Sea", new Army("English", 0, 1), new ArrayList<Integer>(Arrays.asList(1, 3)), 0, 0, false, false),
																		   new Shire(3, "Kingdom of Northumbria", "", "North Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(1, 2, 4, 10, 11, 13, 15, 16)), 0, 0, false, false),
																		   new Shire(4, "Kingdom of Northumbria", "Carlisle", "Irish Sea", new Army("English", 0, 1), new ArrayList<Integer>(Arrays.asList(1, 3, 5, 6, 9, 10)), 0, 1, false, false),
																		   new Shire(5, "Kingdom of Northumbria", "", "Irish Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(4, 6, 7)), 0, 0, false, false),
																		   new Shire(6, "Kingdom of Northumbria", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(4, 5, 7, 8, 9)), 0, 0, false, false),
																		   new Shire(7, "Kingdom of Northumbria", "", "Irish Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(5, 6, 8)), 0, 0, false, false),
																		   new Shire(8, "Kingdom of Northumbria", "", "Irish Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(6, 7, 9)), 0, 0, false, false),
																		   new Shire(9, "Kingdom of Northumbria", "", "Irish Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(4, 6, 8, 10, 12, 22)), 0, 0, false, false),
																		   new Shire(10, "Kingdom of Northumbria", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(3, 4, 9, 11, 12, 13)), 0, 0, false, false),
																		   new Shire(11, "Kingdom of Northumbria", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(3, 10, 13)), 0, 0, false, false),
																		   new Shire(12, "Kingdom of Northumbria", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(9, 10, 13, 14, 22)), 0, 0, false, false),
																		   new Shire(13, "Kingdom of Northumbria", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(3, 10, 11, 12, 14, 15)), 0, 0, false, false),
																		   new Shire(14, "Kingdom of Northumbria", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(12, 13, 15, 22)), 0, 0, false, false),
																		   new Shire(15, "Kingdom of Northumbria", "York", "North Sea", new Army("English", 1, 1), new ArrayList<Integer>(Arrays.asList(3, 13, 14, 16, 17, 18, 19, 22, 28)), 1, 1, false, false),
																		   new Shire(16, "Kingdom of Northumbria", "", "North Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(3, 15, 17)), 0, 0, false, false),
																		   new Shire(17, "Kingdom of Northumbria", "", "North Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(15, 16, 18)), 0, 0, false, false),
																		   new Shire(18, "Kingdom of Northumbria", "", "North Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(15, 17)), 0, 0, false, false),
																		   new Shire(19, "Kingdom of Northumbria", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(15, 20, 22, 26, 27, 28)), 0, 0, false, false),
																		   new Shire(20, "Kingdom of Northumbria", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(19, 21, 22, 26)), 0, 0, false, false),
																		   new Shire(21, "Kingdom of Northumbria", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(20, 22, 24, 25, 26)), 0, 0, false, false),
																		   new Shire(22, "Kingdom of Northumbria", "Manchester", "Irish Sea", new Army("English", 0, 1), new ArrayList<Integer>(Arrays.asList(9, 12, 14, 15, 19, 20, 21, 23, 24)), 0, 0, false, false),
																		   new Shire(23, "Kingdom of Northumbria", "", "Irish Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(22, 24)), 0, 0, false, false),
																		   new Shire(24, "Kingdom of Mercia", "Chester", "Irish Sea", new Army("English", 0, 1), new ArrayList<Integer>(Arrays.asList(21, 22, 23, 25, 37, 38)), 0, 0, false, false),
																		   new Shire(25, "Kingdom of Mercia", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(21, 24, 26, 36, 37)), 0, 0, false, false),
																		   new Shire(26, "Kingdom of Mercia", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(19, 20, 21, 25, 27, 36)), 0, 0, false, false),
																		   new Shire(27, "Kingdom of Mercia", "Lincoln", "", new Army("English", 0, 1), new ArrayList<Integer>(Arrays.asList(19, 26, 28, 29, 30, 31, 34, 36)), 0, 0, false, true),
																		   new Shire(28, "Kingdom of Mercia", "", "North Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(15, 19, 27, 29)), 0, 0, false, false),
																		   new Shire(29, "Kingdom of Mercia", "", "North Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(27, 28, 30)), 0, 0, false, false),
																		   new Shire(30, "Kingdom of Mercia", "", "North Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(27, 29, 31)), 0, 0, false, false),
																		   new Shire(31, "Kingdom of Mercia", "", "North Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(27, 30, 32, 34, 63)), 0, 0, true, false),
																		   new Shire(32, "Kingdom of Mercia", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(31, 33, 34, 60, 63)), 0, 0, false, false),
																		   new Shire(33, "Kingdom of Mercia", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(32, 34, 58, 59, 60)), 0, 0, false, false),
																		   new Shire(34, "Kingdom of Mercia", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(27, 31, 32, 33, 35, 36, 40, 58)), 0, 0, false, false),
																		   new Shire(35, "Kingdom of Mercia", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(34, 36, 40)), 0, 0, false, false),
																		   new Shire(36, "Kingdom of Mercia", "Leicester", "", new Army("English", 0, 1), new ArrayList<Integer>(Arrays.asList(25, 26, 27, 34, 35, 37, 40)), 0, 1, false, true),
																		   new Shire(37, "Kingdom of Mercia", "Lichfield", "", new Army("English", 1, 1), new ArrayList<Integer>(Arrays.asList(24, 25, 36, 38, 39, 40)), 1, 1, false, true),
																		   new Shire(38, "Kingdom of Mercia", "Hereford", "", new Army("English", 0, 1), new ArrayList<Integer>(Arrays.asList(24, 37, 39, 43)), 0, 0, false, false),
																		   new Shire(39, "Kingdom of Mercia", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(37, 38, 40, 42, 43)), 0, 0, false, false),
																		   new Shire(40, "Kingdom of Mercia", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(34, 35, 36, 37, 39, 41, 42, 58)), 0, 0, false, false),
																		   new Shire(41, "Kingdom of Mercia", "Oxford", "", new Army("English", 0, 1), new ArrayList<Integer>(Arrays.asList(40, 42, 52, 58)), 0, 1, false, false),
																		   new Shire(42, "Kingdom of Mercia", "", "Severn Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(39, 40, 41, 43, 44, 48, 51, 52)), 0, 0, false, false),
																		   new Shire(43, "Kingdom of Mercia", "", "Severn Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(38, 39, 42)), 0, 0, false, false),
																		   new Shire(44, "Kingdom of Wessex", "", "Severn Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(42, 45, 48)), 0, 0, true, false),
																		   new Shire(45, "Kingdom of Wessex", "", "Severn Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(44, 46, 47, 48)), 0, 0, true, false),
																		   new Shire(46, "Kingdom of Wessex", "", "Severn Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(45, 47)), 0, 0, false, false),
																		   new Shire(47, "Kingdom of Wessex", "Exeter", "English Channel", new Army("English", 1, 1), new ArrayList<Integer>(Arrays.asList(45, 46, 48, 49)), 1, 2, false, false),
																		   new Shire(48, "Kingdom of Wessex", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(42, 44, 45, 47, 49, 51)), 0, 0, false, false),
																		   new Shire(49, "Kingdom of Wessex", "", "English Channel", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(47, 48, 50, 51, 52)), 0, 0, false, false),
																		   new Shire(50, "Kingdom of Wessex", "", "English Channel", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(49, 52)), 0, 0, false, false),
																		   new Shire(51, "Kingdom of Wessex", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(42, 48, 49, 52)), 0, 0, false, false),
																		   new Shire(52, "Kingdom of Wessex", "Winchester", "English Channel", new Army("English", 1, 1), new ArrayList<Integer>(Arrays.asList(41, 42, 50, 51, 53, 54, 58)), 2, 1, false, false),
																		   new Shire(53, "Kingdom of Wessex", "", "", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(52, 54)), 0, 0, false, false),
																		   new Shire(54, "Kingdom of Wessex", "Selsey", "English Channel", new Army("English", 1, 1), new ArrayList<Integer>(Arrays.asList(52, 53, 55, 57, 58)), 0, 1, false, false),
																		   new Shire(55, "Kingdom of Wessex", "", "English Channel", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(54, 56, 57)), 0, 0, false, false),
																		   new Shire(56, "Kingdom of Wessex", "", "English Channel", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(55, 57)), 0, 0, false, false),
																		   new Shire(57, "Kingdom of Wessex", "Canterbury", "North Sea/English Channel", new Army("English", 1, 1), new ArrayList<Integer>(Arrays.asList(54, 55, 56, 58)), 0, 1, false, false),
																		   new Shire(58, "Kingdom of Wessex", "London", "North Sea", new Army("English", 1, 1), new ArrayList<Integer>(Arrays.asList(33, 34, 40, 41, 52, 54, 57, 59)), 1, 1, false, false),
																		   new Shire(59, "Kingdom of Wessex", "", "North Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(33, 58, 60, 61)), 0, 0, false, false),
																		   new Shire(60, "East Anglia", "Thetford", "", new Army("English", 1, 1), new ArrayList<Integer>(Arrays.asList(32, 33, 59, 61, 62, 63)), 1, 1, false, false),
																		   new Shire(61, "East Anglia", "", "North Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(59, 60, 62)), 0, 0, false, false),
																		   new Shire(62, "East Anglia", "Elmham", "North Sea", new Army("English", 0, 1), new ArrayList<Integer>(Arrays.asList(60, 61, 63)), 0, 0, false, false),
																		   new Shire(63, "East Anglia", "", "North Sea", new Army("English", 0, 0), new ArrayList<Integer>(Arrays.asList(31, 32, 60, 62)), 0, 0, true, false)));
	
//	public static void printArmies(ArrayList<Integer> armies)
//	{
//		for (int i = 0; i < armies.size(); i++)
//		{
//			System.out.println("[" + armies.get(i) + "] - " + shireList.get(armies.get(i)).defenders);
//		}
//	}
	
	public static void printLocations(ArrayList<Integer> locations)
	{
		for (int i = 0; i < locations.size(); i++)
		{
			System.out.println(Map.shireList.get(i));
		}
	}
}