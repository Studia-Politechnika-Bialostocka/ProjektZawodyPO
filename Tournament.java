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
	
	private int refChoice = 0;
	private int aRefChoice = 0;
	
	public Tournament(String name, double prize, LinkedList<Team> teams, LinkedList<Referee> refs, LinkedList<AssistantReferee> arefs) {
		this.name = name;
		prizePool = prize;
		this.teams = teams;
		referees = refs;
		aReferees = arefs;

		rand = new Random();
	}
	
	public LinkedList<Team> roundRobin() {

		for (int i = 0; i < teams.size(); i++) {
			for (int j = i+1; j < teams.size; j++) {
				int mainRefIdx = refChoice%referees.size();
				if (teams.get(0) instanceof Volleyball) {
					int ar1 = aRefChoice % aReferees.size();
					int ar2 = aRefChoice + 1 % aReferees.size();
					matches.add(new VolleyballMatch(teams.get(i), teams.get(j), referees.get(mainRefIdx), 0, aReferees.get(ar1), aReferees.get(ar2));
					aRefChoice += 2;
				} else {
					int typeOfMatch;
					if (teams.get(0) instanceof Dodgeball) typeOfMatch = 1;
					else if (teams.get(0) instanceof Tug_Of_War) typeOfMatch = 2;

					matches.add(new Match(teams.get(i), teams.get(j), referees.get(mainRefIdx), typeOfMatch));
				}
				refChoice++;
			}
		}

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
