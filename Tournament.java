package def;

import java.util.LinkedList;
import java.util.Random;

//zobaczmy czy to dziala
public class Tournament {
	private String name;
	private double prizePool;
	private LinkedList<Donator> donators;
	private LinkedList<Match> matches;
	private LinkedList<Match> matchesOfSemi;
	private LinkedList<Team> teams;
	private LinkedList<Referee> referees;
	private LinkedList<AssistantReferee> aReferees;
	
	private int refChoice = 0;
	private int aRefChoice = 0;
	
	public Tournament(String name, double prize, LinkedList<Team> teams, LinkedList<Referee> refs, LinkedList<AssistantReferee> arefs) {
		this.name = name;
		prizePool = prize;
		this.teams = teams;
		referees = refs;
		aReferees = arefs;


		Random rand = new Random();

	}

	private boolean sortCondition(Team t1, Team t2) {
		if (t1.getWins() > t2.getWins()) return true;
		else if (t1.getWins() < t2.getWins()) return false;
		else if (t1.getSetsWon() > t2.getSetsWon()) return true;
		else return false;
	}

	private LinkedList<Team> sortTeams() {
		LinkedList<Team> sortedTeams = teams;
		int i, j;
		Team temp;
		for (i = 0; i<sortedTeams.size()-1; i++) {
			for (j=0; j<sortedTeams.size()-1-i; j++) {
				if (sortCondition(sortedTeams.get(j), sortedTeams.get(j+1))) {
					temp = sortedTeams.get(j+1);
					sortedTeams.set(j+1, sortedTeams.get(j));
					sortedTeams.set(j, temp);
				}
			}
		}
		return sortedTeams;
	}
	
	public LinkedList<Team> roundRobin() {
		for (int i = 0; i < teams.size(); i++) {

			for (int j = i+1; j < teams.size(); j++) {

				int mainRefIdx = refChoice%referees.size();
				if (teams.get(0) instanceof Volleyball) {
					int ar1 = aRefChoice % aReferees.size();
					int ar2 = aRefChoice + 1 % aReferees.size();
					matches.add(new VolleyballMatch(teams.get(i), teams.get(j), referees.get(mainRefIdx), 0, aReferees.get(ar1), aReferees.get(ar2)));
					aRefChoice += 2;
				} else {
					int typeOfMatch = 1;
					if (teams.get(0) instanceof Dodgeball) typeOfMatch = 1;
					else if (teams.get(0) instanceof Tug_of_War) typeOfMatch = 2;

					matches.add(new Match(teams.get(i), teams.get(j), referees.get(mainRefIdx), typeOfMatch));
				}
				refChoice++;
			}
		}
		LinkedList<Team> sortedTeams = sortTeams();

		LinkedList<Team> semiTeams = new LinkedList<Team>();

		for (int i = 0; i < 4; i++) semiTeams.add(sortedTeams.get(i));
		return semiTeams;
	}
	
	public LinkedList<Team> semiFinal(LinkedList<Team> sTeams) {
		return null;
	}
	public LinkedList<Match> matchesOfSemiFinals(LinkedList<Team> teamsWinnersFromRoundRobin) {
		matchesOfSemi = new LinkedList<>();
		for (int i = 0; i < 4; ++i) {
			for (int j = i + 1; j < 4; ++j) {
				if (teamsWinnersFromRoundRobin.get(i) instanceof Volleyball)
					matchesOfSemi.add(new VolleyballMatch(teamsWinnersFromRoundRobin.get(i), teamsWinnersFromRoundRobin.get(j), referees.get(refChoice), 0, aReferees.get(aRefChoice), aReferees.get(aRefChoice + 1)));
				else{
					int typeOfMatch;
					if (teamsWinnersFromRoundRobin.get(i) instanceof Dodgeball)
						typeOfMatch = 1;
					else
						typeOfMatch = 2;
					matchesOfSemi.add(new Match(teamsWinnersFromRoundRobin.get(i), teamsWinnersFromRoundRobin.get(j), referees.get(refChoice),typeOfMatch));
				}
				if (aRefChoice + 1 == aReferees.size())
					aRefChoice = 0;
				else
					++aRefChoice;
				if (refChoice + 1 == referees.size())
					refChoice = 0;
				else
					++refChoice;
			}
	}
		return matchesOfSemi;
	}

	public Team finalGame(LinkedList<Team> fTeams) {
		Team winner;
		winner;
		if (aRefChoice + 1 == aReferees.size())
			aRefChoice = 0;
		else
			++aRefChoice;
		if (refChoice + 1 == referees.size())
			refChoice = 0;
		else
			++refChoice;
		return winner;
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
