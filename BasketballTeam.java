import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BasketballTeam {
	private ArrayList<BasketballPlayer> teamList;
	private double projectedTeamPoints;
	private int teamWorth;

	public BasketballTeam() {
		teamList = new ArrayList<BasketballPlayer>();
		this.projectedTeamPoints = 0;
	}

	public BasketballTeam(ArrayList<BasketballPlayer> list) {
		this.setTeamList(list);

	}

	public ArrayList<BasketballPlayer> getTeamList() {
		return teamList;
	}

	public void setTeamList(ArrayList<BasketballPlayer> teamList) {
		this.teamList = teamList;
	}

	public double getProjectedTeamPoints() {
		return projectedTeamPoints;
	}

	public void setProjectedTeamPoints(double projectedTeamPoints) {
		this.projectedTeamPoints = projectedTeamPoints;
	}

	public int getTeamWorth() {
		return teamWorth;
	}

	public void setTeamWorth(int teamWorth) {
		this.teamWorth = teamWorth;
	}

	public static ArrayList<BasketballPlayer> generateInitialList(String filename) throws IOException {

		// Parses .csv to ArrayList Format
		ArrayList<BasketballPlayer> list = new ArrayList<BasketballPlayer>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = "";
			br.readLine(); // Parses Over Given Key in Provided .csv
			while ((line = br.readLine()) != null) {
				// Creates Temporary FootballPlayer, Initializes All Variables,
				// Adds To List
				BasketballPlayer temp = new BasketballPlayer();
				String[] playerLine = line.split(",");
				temp.setPos(playerLine[0]);
				temp.setName(playerLine[1]);
				temp.setWorth(Integer.parseInt(playerLine[2]));
				temp.setNextGame(playerLine[3]);
				temp.setProjectedHighPoints(Double.parseDouble(playerLine[4]));
				temp.setProjectedLowPoints(Double.parseDouble(playerLine[5]));
				temp.setProjectedPoints(Double.parseDouble(playerLine[6]));
				temp.setAverageHisPoints(Double.parseDouble(playerLine[7]));
				temp.setTeam(playerLine[8]);
				temp.setDefMatchup(playerLine[9]);
				// Add Temporary Player To List
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

	public static ArrayList<BasketballPlayer> sortByPosition(ArrayList<BasketballPlayer> ver1) {

		ArrayList<BasketballPlayer> tempList = new ArrayList<BasketballPlayer>();

		for (BasketballPlayer x : ver1) {
			if (x.getPos().equalsIgnoreCase("pg"))
				tempList.add(x);
		}

		for (BasketballPlayer x : ver1) {
			if (x.getPos().equalsIgnoreCase("sg"))
				tempList.add(x);
		}
		for (BasketballPlayer x : ver1) {
			if (x.getPos().equalsIgnoreCase("sf"))
				tempList.add(x);
		}
		for (BasketballPlayer x : ver1) {
			if (x.getPos().equalsIgnoreCase("pf"))
				tempList.add(x);
		}
		for (BasketballPlayer x : ver1) {
			if (x.getPos().equalsIgnoreCase("c"))
				tempList.add(x);
		}
		for (BasketballPlayer x : ver1) {
			if (x.getPos().equalsIgnoreCase("g"))
				tempList.add(x);
		}
		for (BasketballPlayer x : ver1) {
			if (x.getPos().equalsIgnoreCase("f"))
				tempList.add(x);
		}

		// Returns Sorted ArrayList
		return tempList;
	}

	public static ArrayList<BasketballTeam> getPossibleTeams(ArrayList<BasketballPlayer> ver1) {

		// Generates ArrayList of All Possible
		// Teams
		int pgCount = 0;
		int sgCount = 0;
		int sfCount = 0;
		int pfCount = 0;
		int cCount = 0;
		int gCount = 0;

		for (int i = 0; i < ver1.size(); i++) {
			if (ver1.get(i).getPos().equalsIgnoreCase("pg"))
				pgCount++;
			else if (ver1.get(i).getPos().equalsIgnoreCase("sg"))
				sgCount++;
			else if (ver1.get(i).getPos().equalsIgnoreCase("sf"))
				sfCount++;
			else if (ver1.get(i).getPos().equalsIgnoreCase("pf"))
				pfCount++;
			else if (ver1.get(i).getPos().equalsIgnoreCase("c"))
				cCount++;
			else if (ver1.get(i).getPos().equalsIgnoreCase("g"))
				gCount++;

		}

		ArrayList<BasketballPlayer> pgList = new ArrayList<BasketballPlayer>();
		ArrayList<BasketballPlayer> sgList = new ArrayList<BasketballPlayer>();
		ArrayList<BasketballPlayer> sfList = new ArrayList<BasketballPlayer>();
		ArrayList<BasketballPlayer> pfList = new ArrayList<BasketballPlayer>();
		ArrayList<BasketballPlayer> cList = new ArrayList<BasketballPlayer>();
		ArrayList<BasketballPlayer> gList = new ArrayList<BasketballPlayer>();
		ArrayList<BasketballPlayer> fList = new ArrayList<BasketballPlayer>();

		int pgParam = pgCount + sgCount;
		int sgParam = pgCount + sgCount + sfCount;
		int sfParam = pgCount + sgCount + sfCount + pfCount;
		int pfParam = pgCount + sgCount + sfCount + pfCount + cCount;
		int cParam = pgCount + sgCount + sfCount + pfCount + cCount + gCount;

		for (int i = 0; i < pgCount; i++)
			pgList.add(ver1.get(i));
		for (int i = pgCount; i < pgParam; i++)
			sgList.add(ver1.get(i));
		for (int i = pgParam; i < sgParam; i++)
			sfList.add(ver1.get(i));
		for (int i = sgParam; i < sfParam; i++)
			pfList.add(ver1.get(i));
		for (int i = sfParam; i < pfParam; i++)
			cList.add(ver1.get(i));
		for (int i = pfParam; i < cParam; i++)
			gList.add(ver1.get(i));
		for (int i = cParam; i < ver1.size(); i++)
			fList.add(ver1.get(i));

		// Confirms Constraints Valid
		int salary = 50000;
		ArrayList<BasketballTeam> finTeams = new ArrayList<BasketballTeam>();

		for (BasketballPlayer pg : pgList) {
			for (BasketballPlayer sg : sgList) {
				for (BasketballPlayer sf : sfList) {
					for (BasketballPlayer pf : pfList) {
						for (BasketballPlayer g : gList) {
							for (BasketballPlayer f : fList) {
								for (int i = 0; i < cList.size() - 1; i++) {
									if (pg.getWorth() + sg.getWorth() + sf.getWorth() + pf.getWorth() + g.getWorth()
											+ f.getWorth() + cList.get(i).getWorth()
											+ cList.get(i + 1).getWorth() <= salary) {
										BasketballTeam tempTeam = new BasketballTeam();
										tempTeam.getTeamList().add(pg);
										tempTeam.getTeamList().add(sg);
										tempTeam.getTeamList().add(sf);
										tempTeam.getTeamList().add(pf);
										tempTeam.getTeamList().add(g);
										tempTeam.getTeamList().add(f);
										tempTeam.getTeamList().add(cList.get(i));
										tempTeam.getTeamList().add(cList.get(i + 1));
										finTeams.add(tempTeam);

									}
								}
							}
						}
					}
				}
			}
		}

		for (BasketballPlayer pg : pgList) {
			for (BasketballPlayer sg : sgList) {
				for (BasketballPlayer sf : sfList) {
					for (BasketballPlayer g : gList) {
						for (BasketballPlayer f : fList) {
							for (BasketballPlayer c : cList) {
								for (int i = 0; i < pfList.size() - 1; i++) {
									if (pg.getWorth() + sg.getWorth() + sf.getWorth() + g.getWorth() + f.getWorth()
											+ c.getWorth() + pfList.get(i).getWorth()
											+ pfList.get(i + 1).getWorth() <= salary) {
										BasketballTeam tempTeam = new BasketballTeam();
										tempTeam.getTeamList().add(pg);
										tempTeam.getTeamList().add(sg);
										tempTeam.getTeamList().add(sf);
										tempTeam.getTeamList().add(c);
										tempTeam.getTeamList().add(g);
										tempTeam.getTeamList().add(f);
										tempTeam.getTeamList().add(pfList.get(i));
										tempTeam.getTeamList().add(pfList.get(i + 1));
										finTeams.add(tempTeam);

									}
								}
							}
						}
					}
				}
			}
		}

		for (BasketballPlayer pg : pgList) {
			for (BasketballPlayer sg : sgList) {
				for (BasketballPlayer g : gList) {
					for (BasketballPlayer f : fList) {
						for (BasketballPlayer c : cList) {
							for (BasketballPlayer pf : pfList) {
								for (int i = 0; i < sfList.size() - 1; i++) {
									if (pg.getWorth() + sg.getWorth() + sfList.get(i).getWorth()
											+ sfList.get(i + 1).getWorth() + g.getWorth() + f.getWorth() + c.getWorth()
											+ pf.getWorth() <= salary) {
										BasketballTeam tempTeam = new BasketballTeam();
										tempTeam.getTeamList().add(pg);
										tempTeam.getTeamList().add(sg);
										tempTeam.getTeamList().add(g);
										tempTeam.getTeamList().add(f);
										tempTeam.getTeamList().add(c);
										tempTeam.getTeamList().add(pf);
										tempTeam.getTeamList().add(sfList.get(i));
										tempTeam.getTeamList().add(sfList.get(i + 1));
										finTeams.add(tempTeam);
									}
								}

							}
						}
					}
				}
			}
		}

		for (BasketballPlayer pg : pgList) {
			for (BasketballPlayer g : gList) {
				for (BasketballPlayer f : fList) {
					for (BasketballPlayer c : cList) {
						for (BasketballPlayer pf : pfList) {
							for (BasketballPlayer sf : sfList) {
								for (int i = 0; i < sgList.size() - 1; i++) {
									if (pg.getWorth() + sgList.get(i).getWorth() + sgList.get(i + 1).getWorth()
											+ sf.getWorth() + g.getWorth() + f.getWorth() + c.getWorth()
											+ pf.getWorth() <= salary) {
										BasketballTeam tempTeam = new BasketballTeam();
										tempTeam.getTeamList().add(pg);
										tempTeam.getTeamList().add(sgList.get(i));
										tempTeam.getTeamList().add(g);
										tempTeam.getTeamList().add(f);
										tempTeam.getTeamList().add(c);
										tempTeam.getTeamList().add(pf);
										tempTeam.getTeamList().add(sf);
										tempTeam.getTeamList().add(sgList.get(i + 1));
										finTeams.add(tempTeam);
									}
								}

							}
						}
					}
				}
			}
		}

		for (BasketballPlayer g : gList) {
			for (BasketballPlayer sg : sgList) {
				for (BasketballPlayer f : fList) {
					for (BasketballPlayer c : cList) {
						for (BasketballPlayer pf : pfList) {
							for (BasketballPlayer sf : sfList) {
								for (int i = 0; i < pgList.size(); i++) {
									if (pgList.get(i).getWorth() + sg.getWorth() + pgList.get(i + 1).getWorth()
											+ sf.getWorth() + g.getWorth() + f.getWorth() + c.getWorth()
											+ pf.getWorth() <= salary) {
										BasketballTeam tempTeam = new BasketballTeam();
										tempTeam.getTeamList().add(pgList.get(i));
										tempTeam.getTeamList().add(sg);
										tempTeam.getTeamList().add(g);
										tempTeam.getTeamList().add(f);
										tempTeam.getTeamList().add(c);
										tempTeam.getTeamList().add(pf);
										tempTeam.getTeamList().add(sf);
										tempTeam.getTeamList().add(pgList.get(i + 1));
										finTeams.add(tempTeam);
									}
								}

							}
						}
					}
				}
			}
			// Return Populated ArrayList

		}
		return finTeams;
	}

	public static double generateRank(BasketballTeam bbteam) {
		double tempTeamPoints = 0.0;
		Random rn = new Random();

		for (int i = 0; i < 9; i++) {
			tempTeamPoints += rn
					.nextInt((int) (bbteam.getTeamList().get(i).getProjectedHighPoints()
							- bbteam.getTeamList().get(i).getProjectedLowPoints()) + 1)
					+ bbteam.getTeamList().get(i).getProjectedLowPoints();
		}

		// Synergies?
		return tempTeamPoints;
	}

	public static void parse(ArrayList<BasketballTeam> finalList, String filename) throws IOException {
		FileWriter fw = new FileWriter(new File(filename));
		for (BasketballTeam x : finalList) {
			for (int i = 0; i < x.getTeamList().size(); i++) {
				fw.write(x.getTeamList().get(i).getName());
				fw.write(",");
			}
			fw.write(System.lineSeparator());
		}
		fw.close();

	}

	public static void parse(ArrayList<BasketballTeam> finalList, String filename, int batches) throws IOException {
		FileWriter fw = new FileWriter(new File(filename));
		for (int i = 0; i < batches; i++) {
			for (int j = 0; j < finalList.get(i).getTeamList().size(); j++) {
				// fw.write(finalList.get(i).getTeamList().get(j).getPos() + "
				// ");
				fw.write(finalList.get(i).getTeamList().get(j).getName());
				// fw.write(" " +
				// finalList.get(i).getTeamList().get(j).getTeam());
				if (j != 8) {
					fw.write(",");
				}

			}
			// fw.write(Double.toString(finalList.get(i).getProjectedTeamPoints()));
			// fw.write(String.valueOf(finalList.get(i).getTeamWorth()));
			fw.write(System.lineSeparator());

		}
		fw.close();

	}

	public static ArrayList<BasketballTeam> sortedForParsing(ArrayList<BasketballTeam> finalList) {
		ArrayList<BasketballTeam> returnList = new ArrayList<BasketballTeam>();
		for (BasketballTeam x : finalList) {
			BasketballTeam sortedTeam = new BasketballTeam(sortByPosition(x.getTeamList()));

			if (x.getTeamList().get(1).getPos().equalsIgnoreCase("pg")) {
				BasketballTeam tempTeam = new BasketballTeam();
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(0));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(2));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(3));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(4));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(5));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(6));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(7));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(1));
				returnList.add(tempTeam);
			}

			if (x.getTeamList().get(2).getPos().equalsIgnoreCase("sg")) {
				BasketballTeam tempTeam = new BasketballTeam();
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(0));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(1));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(3));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(4));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(5));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(6));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(7));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(2));
				returnList.add(tempTeam);
			}

			if (x.getTeamList().get(3).getPos().equalsIgnoreCase("sf")) {
				BasketballTeam tempTeam = new BasketballTeam();
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(0));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(1));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(2));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(4));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(5));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(6));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(7));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(3));
				returnList.add(tempTeam);
			}

			if (x.getTeamList().get(4).getPos().equalsIgnoreCase("pf")) {
				BasketballTeam tempTeam = new BasketballTeam();
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(0));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(1));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(2));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(3));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(5));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(6));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(7));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(4));
				returnList.add(tempTeam);
			}

			if (x.getTeamList().get(4).getPos().equalsIgnoreCase("c")) {
				BasketballTeam tempTeam = new BasketballTeam();
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(0));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(1));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(2));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(3));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(4));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(6));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(7));
				tempTeam.getTeamList().add(sortedTeam.getTeamList().get(5));
				returnList.add(tempTeam);
			}

		}
		return returnList;
	}

}
