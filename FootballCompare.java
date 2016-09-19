import java.util.Comparator;

public class FootballCompare implements Comparator<FootballTeam> {

	public int compare(FootballTeam t1, FootballTeam t2) {
		if (t1.getProjectedTeamPoints() > t2.getProjectedTeamPoints())
			return -1;
		else if (t1.getProjectedTeamPoints() == t2.getProjectedTeamPoints())
			return 0;
		else
			return 1;
	}

}
