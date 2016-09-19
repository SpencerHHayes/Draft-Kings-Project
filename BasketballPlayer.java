
public class BasketballPlayer extends Player {

	private String team;
	private String pos;
	private String nextGame;
	private double projectedPoints;
	private double projectedHighPoints;
	private double projectedLowPoints;
	private double averageHisPoints;
	private String defMatchup;

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getNextGame() {
		return nextGame;
	}

	public void setNextGame(String nextGame) {
		this.nextGame = nextGame;
	}

	public double getProjectedPoints() {
		return projectedPoints;
	}

	public void setProjectedPoints(double projectedPoints) {
		this.projectedPoints = projectedPoints;
	}

	public double getProjectedHighPoints() {
		return projectedHighPoints;
	}

	public void setProjectedHighPoints(double projectedHighPoints) {
		this.projectedHighPoints = projectedHighPoints;
	}

	public double getProjectedLowPoints() {
		return projectedLowPoints;
	}

	public void setProjectedLowPoints(double projectedLowPoints) {
		this.projectedLowPoints = projectedLowPoints;
	}

	public double getAverageHisPoints() {
		return averageHisPoints;
	}

	public void setAverageHisPoints(double averageHisPoints) {
		this.averageHisPoints = averageHisPoints;
	}

	public String getDefMatchup() {
		return defMatchup;
	}

	public void setDefMatchup(String defMatchup) {
		this.defMatchup = defMatchup;
	}

}
