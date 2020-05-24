package def;

import java.util.LinkedList;

public class Tournament {
	private String name;
	private double prizePool;
	private LinkedList<Donator> donators;
	private LinkedList<Match> matches;
	private LinkedList<Team> teams;
	
	
	
	public Tournament(String name, double prize, LinkedList<Team> teams) {
		this.name = name;
		prizePool = prize;
		this.teams = teams;
	}
	
	public LinkedList<Team> roundRobin() {
		return null;
	}
	
	public LinkedList<Team> semiFinal(LinkedList<Team> sTeams) {
		
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
