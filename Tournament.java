package def;

import java.io.*;
import java.util.LinkedList;
import java.util.Random;

public class Tournament  {
	private String name;
	private double prizePool;
	private int typeOfTournament; // 0=Volleyball, 1=Dodgeball, 2 = Tug_of_War
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

	public Tournament(String name, double Initialprize, LinkedList<Team> teams, LinkedList<Referee> refs, LinkedList<AssistantReferee> arefs,int typeOfTournament) {
		this.name = name;
		prizePool = Initialprize;
		this.teams = teams;
		referees = refs;
		aReferees = arefs;
		Random rand = new Random();
		//Random rand = new Random();
		this.typeOfTournament=typeOfTournament;
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

	public LinkedList<Match> matchesOfRoundRobin(LinkedList<Team> listOfTeamsInRoundRobin ) {
		teams=listOfTeamsInRoundRobin;
		for (int i = 0; i < teams.size(); i++) {
			for (int j = i + 1; j < teams.size(); j++) {
				int mainRefIdx = refChoice % referees.size();
				if (teams.get(0) instanceof Volleyball) {
					int ar1 = aRefChoice % aReferees.size();
					int ar2 = (aRefChoice + 1) % aReferees.size();
					matches.add(new VolleyballMatch(teams.get(i), teams.get(j), referees.get(mainRefIdx),
							0, aReferees.get(ar1), aReferees.get(ar2)));
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
			int numberOfMainReferee = refChoice % referees.size();
			if (teamsWinnersFromRoundRobin.get(i) instanceof Volleyball) {
				int numberOfFirstReferee = aRefChoice%aReferees.size();
				int numberOfSecondReferee = (aRefChoice+1) % aReferees.size();
				matchesOfSemi.add(new VolleyballMatch(teamsWinnersFromRoundRobin.get(i), teamsWinnersFromRoundRobin.get(i + 2),
						referees.get(numberOfMainReferee),0, aReferees.get(numberOfFirstReferee), aReferees.get(numberOfSecondReferee)));
					aRefChoice+=2;
			}
			else {
				if (teamsWinnersFromRoundRobin.get(i) instanceof Dodgeball)
					matchesOfSemi.add(new Match(teamsWinnersFromRoundRobin.get(i), teamsWinnersFromRoundRobin.get(i + 2),
							referees.get(numberOfMainReferee), 1));
				else
					matchesOfSemi.add(new Match(teamsWinnersFromRoundRobin.get(i), teamsWinnersFromRoundRobin.get(i + 2),
							referees.get(numberOfMainReferee), 2));
			}
				++refChoice;
		}
		return matchesOfSemi;
	}
	//mecz finalowy, wylonienie zwyciezcy oraz przydzielenie nagrod 4 pierwszym miejscom
	public Team finalGame(Match finalMatch) {
		finalMatch.assignPointsAndSets();
		winner=finalMatch.getWinner();
		assignPrizes();
		return winner;
	}

	public Match matchOfFinal(LinkedList<Team> finalTeams)
	{
		int numberOfMainReferee = refChoice%referees.size();
		if (finalTeams.get(0) instanceof Volleyball) {
			int numberOfFirstReferee = aRefChoice%aReferees.size();
			int numberOfSecondReferee = (aRefChoice+1) % aReferees.size();
			matchOfFinal=(new VolleyballMatch(finalTeams.get(0), finalTeams.get(1), referees.get(numberOfMainReferee),
					0, aReferees.get(numberOfFirstReferee), aReferees.get(numberOfSecondReferee)));
			aRefChoice+=2;
		}
		else {
			if (finalTeams.get(0) instanceof Dodgeball)
				matchOfFinal=(new Match(finalTeams.get(0), finalTeams.get(1), referees.get(numberOfMainReferee), 1));
			else
				matchOfFinal=(new Match(finalTeams.get(0), finalTeams.get(1), referees.get(numberOfMainReferee), 2));
		}
			++refChoice;
		return matchOfFinal;
	}

    // do ustalania przydzielanych nagrod
    private void assignPrizes()
    {
        Team loserOfFirstSemiFinals = matchesOfSemi.get(0).getLoser();
        Team loserOfSecondSemiFinals = matchesOfSemi.get(1).getLoser();
        winner.addPrizesWon(0.5*prizePool);
        matchOfFinal.getLoser().addPrizesWon(0.25*prizePool);
        if(loserOfFirstSemiFinals.getSetsWon() > loserOfSecondSemiFinals.getSetsWon())
        {
            loserOfFirstSemiFinals.addPrizesWon(0.15*prizePool);
            loserOfSecondSemiFinals.addPrizesWon(0.1*prizePool);
        }
        else if(loserOfFirstSemiFinals.getSetsWon() < loserOfSecondSemiFinals.getSetsWon())
        {
            loserOfFirstSemiFinals.addPrizesWon(0.1*prizePool);
            loserOfSecondSemiFinals.addPrizesWon(0.15*prizePool);
        }
        else
        {
            loserOfFirstSemiFinals.addPrizesWon(0.125*prizePool);
            loserOfSecondSemiFinals.addPrizesWon(0.125*prizePool);
        }
    }

	public void addDonator(Donator d) {
		donators.add(d);
	}

	public void showDonators() {
		for(Donator exampleDonators: donators)
			System.out.println(exampleDonators);
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

	public String getNameOfTournament(){
		return name;
	}
	private String matchesToString()
	{
		String s="";
		for(Match match : matches)
			s+=match.toString();
		for(Match match : matchesOfSemi)
			s+=match.toString();
		s+=matchOfFinal.toString();
		return s;
	}

	private int linesInFile() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(name + ".txt"));
		int lines = 0;
		while (reader.readLine() != null)
			lines++;
		reader.close();
		return lines;
	}
	public String toString(){
		String prize = Double.toString(prizePool);
		return name + " "+ prize + " " + winner.toString() + "\n" + matchesToString();
	}
}