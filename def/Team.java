package def;

public abstract class Team {
	protected int playerAmount;
	protected String teamName;
	protected int points=0;
	protected int wins=0;
	protected int losses=0;
	protected double prizesWon=0f;
	protected int setsWon=0;
	public int getPlayerAmount() {
		return playerAmount;
	}

	public void WonAMatch()
	{
		wins+=1;
		points+=3;
		setsWon+=3;
	}

	public void LostAMatch(int sets)
	{
		losses+=1;
		setsWon+=sets;
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
	
	public double getPrizesWon()
	{
		return prizesWon;
	}
}
