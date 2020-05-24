package def;

public class Volleyball extends Team {
	private final int playerAmount = 2;
	
	public Volleyball(String teamName) {
		this.teamName = teamName;
		points = 0;
		wins = 0;
		losses = 0;
		prizesWon = 0f;
	}
}
