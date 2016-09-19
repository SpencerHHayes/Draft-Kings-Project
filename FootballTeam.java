
/*
 * FootballTeam Created to House FootballPlayer
 * and to Simplify Creation of .csv
 * For Bulk Optimizations
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FootballTeam {
	private ArrayList<FootballPlayer> teamList;
	private double projectedTeamPoints;

	public static void parse(ArrayList<FootballTeam> finalList, String filename) throws IOException {
		FileWriter fw = new FileWriter(new File(filename));
		for (FootballTeam x : finalList) {
			for (int i = 0; i < x.getTeamList().size(); i++) {
				fw.write(x.getTeamList().get(i).getName());
				fw.write(",");
			}
			fw.write(System.lineSeparator());
		}
		fw.close();

	}

	public static ArrayList<FootballPlayer> generateInitialListO(String filename) throws IOException {

		// Parses .csv to ArrayList Format
		ArrayList<FootballPlayer> list = new ArrayList<FootballPlayer>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = "";
			br.readLine(); // Parses Over Given Key in Provided .csv
			while ((line = br.readLine()) != null) {
				// Creates Temporary FootballPlayer, Initializes All Variables,
				// Adds To List

				FootballPlayer temp = new FootballPlayer();
				String[] playerLine = line.split(",");
				temp.setPos(playerLine[0]);
				temp.setName(playerLine[1]);
				temp.setWorth(Integer.parseInt(playerLine[2]));
				temp.setNextGame(playerLine[6]);
				temp.setProjectedHighPoints(Double.parseDouble(playerLine[4]));
				temp.setProjectedLowPoints(Double.parseDouble(playerLine[5]));
				temp.setProjectedPoints(Double.parseDouble(playerLine[3]));
				temp.setAverageHisPoints(Double.parseDouble(playerLine[7]));
				temp.setTeam(playerLine[8]);
				// temp.setDefMatchup(playerLine[9]);
				list.add(temp);

				/*
				 * FootballPlayer temp = new FootballPlayer(); String[]
				 * playerLine = line.split(","); temp.setPos(playerLine[0]);
				 * temp.setName(playerLine[1]);
				 * temp.setWorth(Integer.parseInt(playerLine[2]));
				 * temp.setNextGame(playerLine[3]);
				 * temp.setAverageHisPoints(Double.parseDouble(playerLine[4]));
				 * temp.setTeam(playerLine[5]);
				 * 
				 * list.add(temp);
				 */

			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Does Not Exist");
			System.exit(0);
		}
		// return Completed List, In Order Of .csv

		return list;

	}

	public static ArrayList<FootballPlayer> generateInitialListDK(String filename) throws IOException {

		// Parses .csv to ArrayList Format
		ArrayList<FootballPlayer> list = new ArrayList<FootballPlayer>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = "";
			br.readLine(); // Parses Over Given Key in Provided .csv
			while ((line = br.readLine()) != null) {
				// Creates Temporary FootballPlayer, Initializes All Variables,
				// Adds To List

				FootballPlayer temp = new FootballPlayer();
				String[] playerLine = line.split(",");
				temp.setPos(playerLine[0]);
				temp.setName(playerLine[1]);
				temp.setWorth(Integer.parseInt(playerLine[2]));
				temp.setNextGame(playerLine[3]);
				temp.setAverageHisPoints(Double.parseDouble(playerLine[4]));
				temp.setTeam(playerLine[5]);

				list.add(temp);

			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Does Not Exist");
			System.exit(0);
		}
		// return Completed List, In Order Of .csv

		return list;

	}

	public static double generateRankO(FootballTeam footballTeam) {
		double tempTeamPoints = 0.0;
		Random rn = new Random();

		for (int i = 0; i < 9; i++) {
			tempTeamPoints += (double) rn.nextInt((int) (footballTeam.getTeamList().get(i).getProjectedHighPoints()
					- footballTeam.getTeamList().get(i).getProjectedLowPoints()) + 1)
					+ footballTeam.getTeamList().get(i).getProjectedLowPoints();
		}

		int numSim = 0;
		String t1, t2, t3, t4, t5, t6, t7, t8, t9;
		t1 = footballTeam.getTeamList().get(0).getTeam();
		t2 = footballTeam.getTeamList().get(1).getTeam();
		t3 = footballTeam.getTeamList().get(2).getTeam();
		t4 = footballTeam.getTeamList().get(3).getTeam();
		t5 = footballTeam.getTeamList().get(4).getTeam();
		t6 = footballTeam.getTeamList().get(5).getTeam();
		t7 = footballTeam.getTeamList().get(6).getTeam();
		t8 = footballTeam.getTeamList().get(7).getTeam();
		t9 = footballTeam.getTeamList().get(8).getTeam();

		if (t1.equalsIgnoreCase(t2))
			numSim++;
		if (t1.equalsIgnoreCase(t3))
			numSim++;
		if (t1.equalsIgnoreCase(t4))
			numSim++;
		if (t1.equalsIgnoreCase(t5))
			numSim++;
		if (t1.equalsIgnoreCase(t6))
			numSim++;
		if (t1.equalsIgnoreCase(t7))
			numSim++;
		if (t1.equalsIgnoreCase(t8))
			numSim++;
		if (t1.equalsIgnoreCase(t9))
			numSim++;

		if (numSim == 3)
			tempTeamPoints -= 3;
		if (numSim == 4)
			tempTeamPoints -= 7;
		if (numSim == 5)
			tempTeamPoints -= 10;
		if (numSim == 6)
			tempTeamPoints -= 15;
		return tempTeamPoints;

	}

	public static double generateRankDK(FootballTeam footballTeam) {
		double tempTeamPoints = 0.0;

		for (int i = 0; i < 9; i++) {
			tempTeamPoints += footballTeam.getTeamList().get(i).getAverageHisPoints();
		}

		int numSim = 0;
		String t1, t2, t3, t4, t5, t6, t7, t8, t9;
		t1 = footballTeam.getTeamList().get(0).getTeam();
		t2 = footballTeam.getTeamList().get(1).getTeam();
		t3 = footballTeam.getTeamList().get(2).getTeam();
		t4 = footballTeam.getTeamList().get(3).getTeam();
		t5 = footballTeam.getTeamList().get(4).getTeam();
		t6 = footballTeam.getTeamList().get(5).getTeam();
		t7 = footballTeam.getTeamList().get(6).getTeam();
		t8 = footballTeam.getTeamList().get(7).getTeam();
		t9 = footballTeam.getTeamList().get(8).getTeam();

		if (t1.equalsIgnoreCase(t2))
			numSim++;
		if (t1.equalsIgnoreCase(t3))
			numSim++;
		if (t1.equalsIgnoreCase(t4))
			numSim++;
		if (t1.equalsIgnoreCase(t5))
			numSim++;
		if (t1.equalsIgnoreCase(t6))
			numSim++;
		if (t1.equalsIgnoreCase(t7))
			numSim++;
		if (t1.equalsIgnoreCase(t8))
			numSim++;
		if (t1.equalsIgnoreCase(t9))
			numSim++;

		if (numSim == 3)
			tempTeamPoints -= 3;
		if (numSim == 4)
			tempTeamPoints -= 7;
		if (numSim == 5)
			tempTeamPoints -= 10;
		if (numSim == 6)
			tempTeamPoints -= 15;
		return tempTeamPoints;

	}

	public static ArrayList<FootballPlayer> sortByPosition(ArrayList<FootballPlayer> ver1) {

		// Sorts ArrayList of FootballPlayer by Position: qb, rb, wr, te, dst
		ArrayList<FootballPlayer> tempList = new ArrayList<FootballPlayer>();

		for (FootballPlayer x : ver1) {
			if (x.getPos().equalsIgnoreCase("qb"))
				tempList.add(x);
		}

		for (FootballPlayer x : ver1) {
			if (x.getPos().equalsIgnoreCase("rb"))
				tempList.add(x);
		}
		for (FootballPlayer x : ver1) {
			if (x.getPos().equalsIgnoreCase("wr"))
				tempList.add(x);
		}
		for (FootballPlayer x : ver1) {
			if (x.getPos().equalsIgnoreCase("te"))
				tempList.add(x);
		}
		for (FootballPlayer x : ver1) {
			if (x.getPos().equalsIgnoreCase("dst"))
				tempList.add(x);
		}

		// Returns Sorted ArrayList
		return tempList;
	}

	public static ArrayList<FootballTeam> getPossibleTeams(ArrayList<FootballPlayer> ver1) {

		// Generates ArrayList of All Possible
		// Teams, NFI
		int qbCount = 0;
		int rbCount = 0;
		int wrCount = 0;
		int teCount = 0;
		int dstCount = 0;

		for (int i = 0; i < ver1.size(); i++) {
			if (ver1.get(i).getPos().equalsIgnoreCase("qb"))
				qbCount++;
			else if (ver1.get(i).getPos().equalsIgnoreCase("rb"))
				rbCount++;
			else if (ver1.get(i).getPos().equalsIgnoreCase("wr"))
				wrCount++;
			else if (ver1.get(i).getPos().equalsIgnoreCase("te"))
				teCount++;
			else if (ver1.get(i).getPos().equalsIgnoreCase("dst"))
				dstCount++;
		}

		ArrayList<FootballPlayer> qbList = new ArrayList<FootballPlayer>();
		ArrayList<FootballPlayer> rbList = new ArrayList<FootballPlayer>();
		ArrayList<FootballPlayer> wrList = new ArrayList<FootballPlayer>();
		ArrayList<FootballPlayer> teList = new ArrayList<FootballPlayer>();
		ArrayList<FootballPlayer> dstList = new ArrayList<FootballPlayer>();

		int rbParam = qbCount + rbCount;
		int wrParam = qbCount + rbCount + wrCount;
		int teParam = qbCount + rbCount + wrCount + teCount;
		int dstParam = qbCount + rbCount + wrCount + teCount + dstCount;

		for (int i = 0; i < qbCount; i++)
			qbList.add(ver1.get(i));
		for (int i = qbCount; i < rbParam; i++)
			rbList.add(ver1.get(i));
		for (int i = rbParam; i < wrParam; i++)
			wrList.add(ver1.get(i));
		for (int i = wrParam; i < teParam; i++)
			teList.add(ver1.get(i));
		for (int i = teParam; i < dstParam; i++)
			dstList.add(ver1.get(i));

		// Confirms Constraints Valid
		int salary = 50000;
		ArrayList<FootballTeam> finTeams = new ArrayList<FootballTeam>();

		for (FootballPlayer qb : qbList) {
			for (FootballPlayer dst : dstList) {
				for (int rb = 0; rb < rbList.size() - 2; rb++) {
					for (int wr = 0; wr < wrList.size() - 2; wr++) {
						for (int te = 0; te < teList.size() - 1; te++) {
							if (qb.getWorth() + dst.getWorth() + rbList.get(rb).getWorth()
									+ rbList.get(rb + 1).getWorth() + rbList.get(rb + 2).getWorth()
									+ wrList.get(wr).getWorth() + wrList.get(wr + 1).getWorth()
									+ wrList.get(wr + 2).getWorth() + teList.get(te).getWorth() <= salary) {
								FootballTeam tempTeam = new FootballTeam();
								tempTeam.getTeamList().add(qb);
								tempTeam.getTeamList().add(dst);
								tempTeam.getTeamList().add(rbList.get(rb));
								tempTeam.getTeamList().add(rbList.get(rb + 1));
								tempTeam.getTeamList().add(rbList.get(rb + 2));
								tempTeam.getTeamList().add(wrList.get(wr));
								tempTeam.getTeamList().add(wrList.get(wr + 1));
								tempTeam.getTeamList().add(wrList.get(wr + 2));
								tempTeam.getTeamList().add(teList.get(te));
								finTeams.add(tempTeam);
							}
						}
					}
				}
			}
		}

		for (FootballPlayer qb : qbList) {
			for (FootballPlayer dst : dstList) {
				for (int rb = 0; rb < rbList.size() - 1; rb++) {
					for (int wr = 0; wr < wrList.size() - 3; wr++) {
						for (int te = 0; te < teList.size() - 1; te++) {
							if (qb.getWorth() + dst.getWorth() + rbList.get(rb).getWorth()
									+ rbList.get(rb + 1).getWorth() + wrList.get(wr).getWorth()
									+ wrList.get(wr + 1).getWorth() + wrList.get(wr + 2).getWorth()
									+ wrList.get(wr + 3).getWorth() + teList.get(te).getWorth() <= salary) {
								FootballTeam tempTeam = new FootballTeam();
								tempTeam.getTeamList().add(qb);
								tempTeam.getTeamList().add(dst);
								tempTeam.getTeamList().add(rbList.get(rb));
								tempTeam.getTeamList().add(rbList.get(rb + 1));
								tempTeam.getTeamList().add(wrList.get(wr));
								tempTeam.getTeamList().add(wrList.get(wr + 1));
								tempTeam.getTeamList().add(wrList.get(wr + 2));
								tempTeam.getTeamList().add(wrList.get(wr + 3));
								tempTeam.getTeamList().add(teList.get(te));
								finTeams.add(tempTeam);
							}
						}
					}
				}
			}
		}

		for (FootballPlayer qb : qbList) {
			for (FootballPlayer dst : dstList) {
				for (int rb = 0; rb < rbList.size() - 1; rb++) {
					for (int wr = 0; wr < wrList.size() - 2; wr++) {
						for (int te = 0; te < teList.size() - 2; te++) {
							if (qb.getWorth() + dst.getWorth() + rbList.get(rb).getWorth()
									+ rbList.get(rb + 1).getWorth() + wrList.get(wr).getWorth()
									+ wrList.get(wr + 1).getWorth() + wrList.get(wr + 2).getWorth()
									+ teList.get(te).getWorth() + teList.get(te + 1).getWorth() <= salary) {
								FootballTeam tempTeam = new FootballTeam();
								tempTeam.getTeamList().add(qb);
								tempTeam.getTeamList().add(dst);
								tempTeam.getTeamList().add(rbList.get(rb));
								tempTeam.getTeamList().add(rbList.get(rb + 1));
								tempTeam.getTeamList().add(wrList.get(wr));
								tempTeam.getTeamList().add(wrList.get(wr + 1));
								tempTeam.getTeamList().add(wrList.get(wr + 2));
								tempTeam.getTeamList().add(teList.get(te));
								tempTeam.getTeamList().add(teList.get(te + 1));
								finTeams.add(tempTeam);
							}
						}
					}
				}
			}
		}
		// Return Populated ArrayList
		return finTeams;
	}

	public static ArrayList<FootballTeam> sortedForParsing(ArrayList<FootballTeam> finalList) {
		ArrayList<FootballTeam> returnList = new ArrayList<FootballTeam>();
		for (FootballTeam x : finalList) {
			FootballTeam sortedTeam = new FootballTeam(sortByPosition(x.getTeamList()));
			int rbCount = 0;
			int wrCount = 0;
			int teCount = 0;

			for (int i = 0; i < sortedTeam.getTeamList().size(); i++) {
				if (sortedTeam.getTeamList().get(i).getPos().equalsIgnoreCase("rb"))
					rbCount++;
				else if (sortedTeam.getTeamList().get(i).getPos().equalsIgnoreCase("wr"))
					wrCount++;
				else if (sortedTeam.getTeamList().get(i).getPos().equalsIgnoreCase("te"))
					teCount++;
			}

			if (rbCount == 3) {
				FootballTeam returnTeam = new FootballTeam();
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(0));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(1));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(2));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(4));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(5));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(6));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(7));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(3));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(8));
				returnTeam.setProjectedTeamPoints(x.getProjectedTeamPoints());
				returnList.add(returnTeam);
			}

			else if (wrCount == 4) {
				FootballTeam returnTeam = new FootballTeam();
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(0));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(1));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(2));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(3));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(4));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(5));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(7));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(6));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(8));
				returnList.add(returnTeam);

			}

			else if (teCount == 2) {
				FootballTeam returnTeam = new FootballTeam();
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(0));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(1));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(2));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(3));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(4));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(5));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(6));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(7));
				returnTeam.getTeamList().add(sortedTeam.getTeamList().get(8));
				returnList.add(returnTeam);

			}

		}
		return returnList;
	}

	public static void parse(ArrayList<FootballTeam> finalList, String filename, int batches) throws IOException {
		FileWriter fw = new FileWriter(new File(filename));
		for (int i = 0; i < batches; i++) {
			for (int j = 0; j < 9; j++) {
				// fw.write(finalList.get(i).getTeamList().get(j).getPos() + "
				// ");
				fw.write(finalList.get(i).getTeamList().get(j).getName());
				// fw.write(" " +
				// finalList.get(i).getTeamList().get(j).getTeam());
				if (j != 8) {
					fw.write(",");
				}

			}
			//fw.write(Double.toString(finalList.get(i).getProjectedTeamPoints()));
			// fw.write(String.valueOf(finalList.get(i).getTeamWorth()));
			fw.write(System.lineSeparator());

		}
		fw.close();

	}

	public FootballTeam(ArrayList<FootballPlayer> list) {
		this.setTeamList(list);
	}

	public FootballTeam() {
		teamList = new ArrayList<FootballPlayer>();
		this.projectedTeamPoints = 0;
	}

	public ArrayList<FootballPlayer> getTeamList() {
		return teamList;
	}

	public void setTeamList(ArrayList<FootballPlayer> teamList) {
		this.teamList = teamList;
	}

	public double getProjectedTeamPoints() {
		return projectedTeamPoints;
	}

	public void setProjectedTeamPoints(double projectedTeamPoints) {
		this.projectedTeamPoints = projectedTeamPoints;
	}

}
