package def;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

//zobaczmy czy to dziala
public class Tournament {
	private String name;
	private double prizePool;
	private LinkedList<Donator> donators;
	private LinkedList<Match> matches;
	private LinkedList<Match> matchesOfSemi;
	private Match matchOfFinal;
	private LinkedList<Team> teams;
	private LinkedList<Referee> referees;
	private LinkedList<AssistantReferee> aReferees;
	private Team winner;
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
	}public LinkedList<Match> matchesOfRoundRobin(LinkedList<Team> listOfTeamsInRoundRobin ) {
		teams=listOfTeamsInRoundRobin;
		for (int i = 0; i < teams.size(); i++) {

			for (int j = i + 1; j < teams.size(); j++) {

				int mainRefIdx = refChoice % referees.size();
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
		return matches;
	}
	public LinkedList<Team> roundRobin(){
		for(Match exampleMatch:matches)
		{
			exampleMatch.assignPointsAndSets();
		}
		LinkedList<Team> sortedTeams = sortTeams();

		LinkedList<Team> semiTeams = new LinkedList<Team>();

		for (int i = 0; i < 4; i++) semiTeams.add(sortedTeams.get(i));
		return semiTeams;
	}
	//mecze polfinalowe oraz wylonienie finalistow
	public LinkedList<Team> semiFinal(LinkedList<Match> matchesWithScoreUsedInMethod) {
		for(Match exampleMatch:matchesWithScoreUsedInMethod)
		{
			exampleMatch.assignPointsAndSets();
		}
		LinkedList<Team> finalTeams = new LinkedList<>();
		finalTeams.add(matchesWithScoreUsedInMethod.get(0).winner);
		finalTeams.add(matchesWithScoreUsedInMethod.get(1).winner);
		return finalTeams;
	}

	public LinkedList<Match> matchesOfSemiFinals(LinkedList<Team> teamsWinnersFromRoundRobin) {
		matchesOfSemi = new LinkedList<>();
		for (int i = 0; i < 2; ++i) {
			if (teamsWinnersFromRoundRobin.get(i) instanceof Volleyball) {
				matchesOfSemi.add(new VolleyballMatch(teamsWinnersFromRoundRobin.get(i), teamsWinnersFromRoundRobin.get(i + 2), referees.get(refChoice), 0, aReferees.get(aRefChoice), aReferees.get(aRefChoice + 1)));
				if (aRefChoice + 1 == aReferees.size())
					aRefChoice = 0;
				else
					++aRefChoice;
			}
			else {
				int typeOfMatch;
				if (teamsWinnersFromRoundRobin.get(i) instanceof Dodgeball)
					typeOfMatch = 1;
				else
					typeOfMatch = 2;
				matchesOfSemi.add(new Match(teamsWinnersFromRoundRobin.get(i), teamsWinnersFromRoundRobin.get(i + 2), referees.get(refChoice), typeOfMatch));
			}
			if (refChoice + 1 == referees.size())
				refChoice = 0;
			else
				++refChoice;
		}
		return matchesOfSemi;
	}
	//mecz final oraz wylonienie zwyciezcy
	public Team finalGame(Match finalMatch) {
		finalMatch.assignPointsAndSets();
		winner=finalMatch.getWinner();
		return winner;
	}
	public Match matchOfFinal(LinkedList<Team> finalTeams)
	{
		if (finalTeams.get(0) instanceof Volleyball) {
			matchOfFinal=(new VolleyballMatch(finalTeams.get(0), finalTeams.get(1), referees.get(refChoice), 0, aReferees.get(aRefChoice), aReferees.get(aRefChoice + 1)));
			if (aRefChoice + 1 == aReferees.size())
				aRefChoice = 0;
			else
				++aRefChoice;
		}
		else {
			int typeOfMatch;
			if (finalTeams.get(0) instanceof Dodgeball)
				typeOfMatch = 1;
			else
				typeOfMatch = 2;
			matchOfFinal=(new Match(finalTeams.get(0), finalTeams.get(1), referees.get(refChoice), typeOfMatch));
		}
		if (refChoice + 1 == referees.size())
			refChoice = 0;
		else
			++refChoice;
		return matchOfFinal;
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

	private int linesInFile() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(name + ".txt"));
		int lines = 0;
		while (reader.readLine() != null)
			lines++;
		reader.close();
		return lines;
	}

}