package def;

public class Tug_of_War extends Team{
	private final int playerAmount = 5;
	
	public Tug_of_War(String teamName) {
		this.teamName = teamName;
		points = 0;
		wins = 0;
		losses = 0;
		prizesWon = 0f;
	}
}
