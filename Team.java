package def;

public abstract class Team {
	protected int playerAmount;
	protected String teamName;
	protected int points;
	protected int wins;
	protected int losses;
	protected double prizesWon;
	
	public int getPlayerAmount() {
		return playerAmount;
	}
	
	public void addNewTeam() {
		
	}

	public void WonAMatch()
	{
		wins+=1;
		points+=3;
	}

	public void LostAMatch()
	{
		losses+=1;
	}

	public String getTeamName() {
		return teamName;
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getWins() {
		return wins;
	}
	
	public int getLosses() {
		return losses;
	}
	
	public double getPrizesWon() {
		return prizesWon;
	}
}
