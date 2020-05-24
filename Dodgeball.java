package def;

public class Dodgeball extends Team {
private final int playerAmount = 6;
	
	public Dodgeball(String teamName) {
		this.teamName = teamName;
		points = 0;
		wins = 0;
		losses = 0;
		prizesWon = 0f;
	}
}
