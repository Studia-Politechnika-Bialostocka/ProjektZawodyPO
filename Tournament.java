package def;

import java.util.LinkedList;

public class Tournament {
	private String name;
	private double prizePool;
	private LinkedList<Donator> donators;
	private LinkedList<Match> matches;
	private LinkedList<Team> teams;
	private LinkedList<Referee> referees;
	private Linked List<AssistantReferee> aReferees;
	
	
	
	public Tournament(String name, double prize, LinkedList<Team> teams, LinkedList<Referee> refs, LinkedList<AssistantReferee> arefs) {
		this.name = name;
		prizePool = prize;
		this.teams = teams;
		referees = refs;
		aReferees = arefs;
	}
	
	public LinkedList<Team> roundRobin() {

		for (int i = 0; i < teams.size(); i++) {
			for (int j = i+1; j < teams.size; j++) {
				if (teams.get(0) instanceof Volleyball)
					matches.add(new Match(teams.get(i), teams.get(j), referees.get()));
			}
		}

	}
	
	public LinkedList<Team> semiFinal(LinkedList<Team> sTeams) {
		for(int i=0; i<4; ++i)
			for(int j = i+1; j < 4 ;++j)
			{
					if(teams.get(i) instanceof )
			}
		return null;
	}
	
	public Team finalGame(LinkedList<Team> fTeams) {
		return null;
	}
	
	public void addDonator(Donator d) {
		donators.add(d);
	}
	
	public void showDonators() {
		
	}
	
	public void deleteDonator(Donator d) {
		donators.remove(d);
	}
	
	public void showFinalScores() {
		
	}
	
	public void addMatch(Match m) {
		matches.add(m);
	}
	
	public LinkedList<Match> getMatches() {
		return matches;
	}
	
	public void saveToFile() {
		
	}
	
	public void importFromFile() {
		
	}
	
}
