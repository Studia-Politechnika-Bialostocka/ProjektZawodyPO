package def;

import java.io.*;
import java.util.LinkedList;
import java.util.Random;

public class Tournament  {

	private String nameOfTheTournament;

	private double prizePool;
	private int typeOfTournament; // 0=Volleyball, 1=Dodgeball, 2 = Tug_of_War
	private LinkedList<Donator> donators;
	private LinkedList<Match> matches;
	private LinkedList<Match> matchesOfSemiFinal;
	private Match matchOfFinal;
	private LinkedList<Team> teams;
	private LinkedList<Team> semiTeams;
	private LinkedList<Team> finalTeams;
	private LinkedList<Referee> referees;
	private LinkedList<AssistantReferee> aReferees;
	private Team winner;
	private int refChoice = 0;
	private int aRefChoice = 0;


	public Tournament(String nameOfTheTournament, double Initialprize, LinkedList<Team> teams, LinkedList<Referee> refs, LinkedList<AssistantReferee> arefs,int typeOfTournament) {
		this.nameOfTheTournament = nameOfTheTournament;


	
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

	public void matchesOfRoundRobin() {
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
	}

	public void roundRobin(){
		for(Match exampleMatch:matches)
		{
			exampleMatch.assignPointsAndSets();
		}
		LinkedList<Team> sortedTeams = sortTeams();

		LinkedList<Team> semiTeams = new LinkedList<Team>();

		for (int i = 0; i < 4; i++) semiTeams.add(sortedTeams.get(i));
	}

	//mecze polfinalowe oraz wylonienie finalistow
	public void semiFinal() {
		for(Match exampleMatch:matchesOfSemiFinal)
		{
			exampleMatch.assignPointsAndSets();
		}
		finalTeams = new LinkedList<>();
		finalTeams.add(matchesOfSemiFinal.get(0).winner);
		finalTeams.add(matchesOfSemiFinal.get(1).winner);
	}

	public void matchesOfSemiFinals() {
		matchesOfSemiFinal = new LinkedList<>();
		for (int i = 0; i < 2; ++i) {
			int numberOfMainReferee = refChoice % referees.size();
			if (semiTeams.get(i) instanceof Volleyball) {
				int numberOfFirstReferee = aRefChoice%aReferees.size();
				int numberOfSecondReferee = (aRefChoice+1) % aReferees.size();
				matchesOfSemiFinal.add(new VolleyballMatch(semiTeams.get(i), semiTeams.get(i + 2),
						referees.get(numberOfMainReferee),0, aReferees.get(numberOfFirstReferee), aReferees.get(numberOfSecondReferee)));
					aRefChoice+=2;
			}
			else {
				if (semiTeams.get(i) instanceof Dodgeball)
					matchesOfSemiFinal.add(new Match(semiTeams.get(i), semiTeams.get(i + 2),
							referees.get(numberOfMainReferee), 1));
				else
					matchesOfSemiFinal.add(new Match(semiTeams.get(i), semiTeams.get(i + 2),
							referees.get(numberOfMainReferee), 2));
			}
				++refChoice;
		}
	}
	//mecz finalowy, wylonienie zwyciezcy oraz przydzielenie nagrod 4 pierwszym miejscom
	public void finalGame() {
		matchOfFinal.assignPointsAndSets();
		winner=matchOfFinal.getWinner();
		assignPrizes();
	}

	public void matchOfFinal()
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
	}

    // do ustalania przydzielanych nagrod
    private void assignPrizes()
    {
        Team loserOfFirstSemiFinals = matchesOfSemiFinal.get(0).getLoser();
        Team loserOfSecondSemiFinals = matchesOfSemiFinal.get(1).getLoser();
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

    public boolean areAllMatchesPlayedInRoundRobin()
	{
		for(Match exampleMatch:matches)
		{
			if(exampleMatch.isScoreSet==false)
				return false;
		}
		return true;
	}

	public boolean areAllMatchesPlayedInSemiFinals()
	{
		for(Match exampleMatch:matchesOfSemiFinal)
		{
			if(exampleMatch.isScoreSet==false)
				return false;
		}
		return true;
	}

	public boolean areAllMatchesPlayedInFinals()
	{
		if(matchOfFinal.isScoreSet==false)
			return false;
		return true;
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
		return nameOfTheTournament;
	}
	private String matchesToString()
	{
		String s="";
		for(Match match : matches)
			s+=match.toString();
		for(Match match : matchesOfSemiFinal)
			s+=match.toString();
		s+=matchOfFinal.toString();
		return s;
	}
	public void showAllMatchesIn_RoundRobin_SemiFinals_Finals(int parameter) {
		switch (parameter) {
			case 0:
				for (Match exampleMatch : matches)
					System.out.println(exampleMatch);
				break;
			case 1:
				for(Match exampleMatch:matchesOfSemiFinal)
					System.out.println(exampleMatch);
				break;
			case 2:
				System.out.println(matchOfFinal);
				break;
		}
	}

	private int linesInFile() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(nameOfTheTournament + ".txt"));
		int lines = 0;
		while (reader.readLine() != null)
			lines++;
		reader.close();
		return lines;
	}
	public String toString(){
		String prize = Double.toString(prizePool);
		return nameOfTheTournament + " "+ prize + " " + winner.toString() + "\n" + matchesToString();
	
	}
}