import java.util.Comparator;

public class BasketballCompare implements Comparator<BasketballTeam> {

	public int compare(BasketballTeam t1, BasketballTeam t2) {
		if (t1.getProjectedTeamPoints() > t2.getProjectedTeamPoints())
			return -1;
		else
			return 1;
	}

}
