package def;

public class Match {
	protected int typeOfMatch; //v = 0, d = 1, t = 2
	protected Referee referee;
	protected String finalScore;
	
	protected Team teamOne;
	protected Team teamTwo;
	protected Team winner;
	
	
	
	public Match(Team teamOne, Team teamTwo, Referee ref, int typeOfMatch) {
		this.teamOne = teamOne;
		this.teamTwo = teamTwo;
		referee = ref;
		this.typeOfMatch = typeOfMatch;
		
		setResult();
		
	}
	
	protected void showFinalScore() {
		
	}
	
	public void setResult() {
		
	}
	
	public String getFinalScore() {
		return finalScore;
	}
	
	public String toString() {
		return teamOne+" i "+ teamTwo+". Zwyciezca:"+winner+". Wynik 3:2";
	}
	
}
