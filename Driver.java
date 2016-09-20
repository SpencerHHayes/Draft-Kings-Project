/*
 * Draft Kings Project
 * Code by Spencer Hayes
 * Work in Progress
 */

//Import Needed Packages

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws IOException {
		// Acquire Necessary Optimization Count
		long startTime = System.currentTimeMillis();
		System.out.println("How many optimizations would you like carried out?");
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		System.out.println("Please Enter The Abbreviation of The Sport We Are Processing, e.g. NFL, NBA...");
		String spt = in.next();
		// Pass Execution Method with Optimization Parameters
		runProject(x, spt);
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Program Runtime: " + totalTime / 1000 + " seconds.");
		in.close();

	}

	private static void runProject(int x, String spt) throws IOException {
		if (spt.equalsIgnoreCase("nfl")) {

			// Acquire Needed Filename, to be placed in Project Folder
			System.out.println("Welcome to The Draft Kings Project!\nOpening for " + x + " batches!");
			System.out.println("Please Type the filename we are working with, i.e. 'filename.csv");
			Scanner in = new Scanner(System.in);
			String filename = in.nextLine();
			System.out.println("Is This a DK or O Type File?");
			String fileType = in.nextLine();
			System.out.println("Thanks!\nInitializing Global Variables...");
			// Convert given csv into ArrayList of FootballPlayer
			ArrayList<FootballPlayer> ver1 = new ArrayList<FootballPlayer>();
			if (fileType.equalsIgnoreCase("O")) {
				ver1 = FootballTeam.generateInitialListO(filename);
			} else {
				ver1 = FootballTeam.generateInitialListDK(filename);
			}
			// Declare and Initialize ArrayList of Possible FootballTeam
			ArrayList<FootballTeam> allPossibleTeams = new ArrayList<FootballTeam>();
			System.out.println("Calculating Number of Possible Teams...");
			allPossibleTeams = FootballTeam.getPossibleTeams(FootballTeam.sortByPosition(ver1));
			System.out.println(allPossibleTeams.size() + " Teams Generated, Now Generating Ranks...");
			if (fileType.equalsIgnoreCase("O")) {
				for (int i = 0; i < allPossibleTeams.size(); i++) {
					allPossibleTeams.get(i).setProjectedTeamPoints(FootballTeam.generateRankO(allPossibleTeams.get(i)));
					if (i % 100000 == 0)
						System.out.println("Team " + i + " Out of " + allPossibleTeams.size() + " Ranked...");
				}
			} else {
				for (int i = 0; i < allPossibleTeams.size(); i++) {
					allPossibleTeams.get(i)
							.setProjectedTeamPoints(FootballTeam.generateRankDK(allPossibleTeams.get(i)));
					if (i % 100000 == 0)
						System.out.println("Team " + i + " Out of " + allPossibleTeams.size() + " Ranked...");
				}
			}

			Collections.sort(allPossibleTeams, new FootballCompare());
			System.out.println("Ranking Completed, Now Parsing Top " + x + " Teams...");

			// Parse Optimized ArrayList through runProject Initial Parameter
			ArrayList<FootballTeam> finalList = new ArrayList<FootballTeam>();
			for (int i = 0; i < x; i++) {
				finalList.add(allPossibleTeams.get(i));
			}
			System.out.println("Enter The Name of the .csv you wish to create in format 'filename.csv'");
			String tempName = in.nextLine();
			if (x < finalList.size()) {

				System.out.println(
						"Only" + finalList.size() + " Batches Exist, Parsing " + finalList.size() + " Batches...");
				FootballTeam.parse(FootballTeam.sortedForParsing(finalList), tempName);
			} else
				FootballTeam.parse(FootballTeam.sortedForParsing(finalList), tempName, x);
			System.out.println("Parsing Completed, End of Program.");
			in.close();
		}

		else if (spt.equalsIgnoreCase("nba")) {
			System.out.println("Welcome to The Draft Kings Project!\nOpening for " + x + " batches!");
			System.out.println("Please Type the filename we are working with, i.e. 'filename.csv");
			Scanner in = new Scanner(System.in);
			String filename = in.nextLine();
			System.out.println("Thanks!\nInitializing Global Variables...");
			ArrayList<BasketballPlayer> ver1 = BasketballTeam.generateInitialList(filename);
			ArrayList<BasketballTeam> allPossibleTeams = new ArrayList<BasketballTeam>();
			System.out.println("Calculating Number of Possible Teams...");
			allPossibleTeams = BasketballTeam.getPossibleTeams(BasketballTeam.sortByPosition(ver1));
			System.out.println(allPossibleTeams.size() + " Teams Generated, Now Generating Ranks...");
			for (int i = 0; i < allPossibleTeams.size(); i++) {
				allPossibleTeams.get(i).setProjectedTeamPoints(BasketballTeam.generateRank(allPossibleTeams.get(i)));
				if (i % 100000 == 0)
					System.out.println("Team " + i + " Out of " + allPossibleTeams.size() + " Ranked...");
			}
			Collections.sort(allPossibleTeams, new BasketballCompare());
			ArrayList<BasketballTeam> finalList = new ArrayList<BasketballTeam>();
			for (int i = 0; i < x; i++) {
				finalList.add(allPossibleTeams.get(i));
			}
			System.out.println("Enter The Name of the .csv you wish to create in format 'filename.csv'");
			String tempName = in.nextLine();
			if (x < finalList.size()) {

				System.out.println(
						"Only" + finalList.size() + " Batches Exist, Parsing " + finalList.size() + " Batches...");
				BasketballTeam.parse(BasketballTeam.sortedForParsing(finalList), tempName);
			} else
				BasketballTeam.parse(BasketballTeam.sortedForParsing(finalList), tempName, x);
			System.out.println("Parsing Completed, End of Program.");
			in.close();

		}

		else
			System.out.println("Invalid Sport, Project Closing...");
	}
}
