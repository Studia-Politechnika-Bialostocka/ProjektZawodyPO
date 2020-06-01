


package def;

import java.io.*;
import java.util.LinkedList;
import java.util.Random;

public class Tournament {

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
    private String AllMaches = "";
    private int refChoice = 0;
    private int aRefChoice = 0;
    public int nrofMatches = 0;


    public Tournament(String nameOfTheTournament, double Initialprize, LinkedList<Referee> refs, LinkedList<AssistantReferee> arefs, int typeOfTournament) {
        this.nameOfTheTournament = nameOfTheTournament;
        if (Initialprize > 0)
            prizePool = Initialprize;
        else
            prizePool += 0;
        referees = refs;
        aReferees = arefs;
        Random rand = new Random();
        //Random rand = new Random();
        this.typeOfTournament = typeOfTournament;
        teams = new LinkedList<Team>();
        semiTeams = new LinkedList<Team>();
        finalTeams = new LinkedList<Team>();
        matchesOfSemiFinal = new LinkedList<>();
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
        for (i = 0; i < sortedTeams.size() - 1; i++) {
            for (j = 0; j < sortedTeams.size() - 1 - i; j++) {
                if (sortCondition(sortedTeams.get(j), sortedTeams.get(j + 1))) {
                    temp = sortedTeams.get(j + 1);
                    sortedTeams.set(j + 1, sortedTeams.get(j));
                    sortedTeams.set(j, temp);
                }
            }
        }
        return sortedTeams;
    }

    public void matchesOfRoundRobin() {
        matches = new LinkedList<Match>();
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                int mainRefIdx = refChoice % referees.size();
                if (typeOfTournament == 0) {
                    int ar1 = aRefChoice % aReferees.size();
                    int ar2 = (aRefChoice + 1) % aReferees.size();
                    matches.add(new VolleyballMatch(teams.get(i), teams.get(j), referees.get(mainRefIdx),
                            0, aReferees.get(ar1), aReferees.get(ar2)));
                    aRefChoice += 2;
                } else
                    matches.add(new Match(teams.get(i), teams.get(j), referees.get(mainRefIdx), typeOfTournament));
                refChoice++;
            }
        }
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void removeTeam(int index) {
        teams.remove(index);
    }

    public void roundRobin() {
        for (Match exampleMatch : matches)
            exampleMatch.assignPointsAndSets();
        LinkedList<Team> sortedTeams = sortTeams();
        for (int i = 0; i < 4; i++) semiTeams.add(sortedTeams.get(i));
    }

    //mecze polfinalowe oraz wylonienie finalistow
    public void semiFinal() {
        for (Match exampleMatch : matchesOfSemiFinal)
            exampleMatch.assignPointsAndSets();
        finalTeams.add(matchesOfSemiFinal.get(0).winner);
        finalTeams.add(matchesOfSemiFinal.get(1).winner);
    }

    public void matchesOfSemiFinals() {
        for (int i = 0; i < 2; ++i) {
            int numberOfMainReferee = refChoice % referees.size();
            if (typeOfTournament==0) {
                int numberOfFirstReferee = aRefChoice % aReferees.size();
                int numberOfSecondReferee = (aRefChoice + 1) % aReferees.size();
                matchesOfSemiFinal.add(new VolleyballMatch(semiTeams.get(i), semiTeams.get(i + 2),referees.get(numberOfMainReferee),
                        0, aReferees.get(numberOfFirstReferee), aReferees.get(numberOfSecondReferee)));
                aRefChoice += 2;
            }
            else
                matchesOfSemiFinal.add(new Match(semiTeams.get(i), semiTeams.get(i + 2),
                        referees.get(numberOfMainReferee), typeOfTournament));
            ++refChoice;
        }
    }

    //mecz finalowy, wylonienie zwyciezcy oraz przydzielenie nagrod 4 pierwszym miejscom
    public void finalGame() {
        matchOfFinal.assignPointsAndSets();
        winner = matchOfFinal.getWinner();
        assignPrizes();
        matchesToString();
    }

    public void matchOfFinal() {
        int numberOfMainReferee = refChoice % referees.size();
        if (typeOfTournament == 0) {
            int numberOfFirstReferee = aRefChoice % aReferees.size();
            int numberOfSecondReferee = (aRefChoice + 1) % aReferees.size();
            matchOfFinal = (new VolleyballMatch(finalTeams.get(0), finalTeams.get(1), referees.get(numberOfMainReferee),
                    0, aReferees.get(numberOfFirstReferee), aReferees.get(numberOfSecondReferee)));
            aRefChoice += 2;
        }
        else
            matchOfFinal = (new Match(finalTeams.get(0), finalTeams.get(1), referees.get(numberOfMainReferee), typeOfTournament));
        ++refChoice;
    }

    // do ustalania przydzielanych nagrod
    private void assignPrizes() {
        Team loserOfFirstSemiFinals = matchesOfSemiFinal.get(0).getLoser();
        Team loserOfSecondSemiFinals = matchesOfSemiFinal.get(1).getLoser();
        winner.addPrizesWon(0.5 * prizePool);
        matchOfFinal.getLoser().addPrizesWon(0.25 * prizePool);
        if (loserOfFirstSemiFinals.getSetsWon() > loserOfSecondSemiFinals.getSetsWon()) {
            loserOfFirstSemiFinals.addPrizesWon(0.15 * prizePool);
            loserOfSecondSemiFinals.addPrizesWon(0.1 * prizePool);
        } else if (loserOfFirstSemiFinals.getSetsWon() < loserOfSecondSemiFinals.getSetsWon()) {
            loserOfFirstSemiFinals.addPrizesWon(0.1 * prizePool);
            loserOfSecondSemiFinals.addPrizesWon(0.15 * prizePool);
        } else {
            loserOfFirstSemiFinals.addPrizesWon(0.125 * prizePool);
            loserOfSecondSemiFinals.addPrizesWon(0.125 * prizePool);
        }
    }

    public String getAllMaches(){
        return AllMaches;
    }

    public void expandAllMaches(String exampleString){
        AllMaches+=exampleString;
    }

    public boolean areAllMatchesPlayedInRoundRobin() {
        for (Match exampleMatch : matches)
            if (exampleMatch.isScoreSet == false)
                return false;
        return true;
    }

    public boolean areAllMatchesPlayedInSemiFinals() {
        for (Match exampleMatch : matchesOfSemiFinal) {
            if (exampleMatch.isScoreSet == false)
                return false;
        }
        return true;
    }

    public boolean areAllMatchesPlayedInFinals() {
        if (matchOfFinal.isScoreSet == false)
            return false;
        return true;
    }

    public void addDonator(Donator d) {
        donators.add(d);
    }

    public void showDonators() {
        int i = 0;
        for (Donator exampleDonators : donators) {
            System.out.println(i + ":" + exampleDonators);
            ++i;
        }
    }

    public void addReferee(Referee example_referee) {
        referees.add(example_referee);
    }

    public void addAssistantReferee(AssistantReferee example_Ass_referee) {
        aReferees.add(example_Ass_referee);
    }

    public void showReferees() {
        int i = 0;
        for (Referee exampleReferee : referees) {
            System.out.println(i + " :" + exampleReferee);
            ++i;
        }
    }

    public void showAssistantReferees() {
        int i = 0;
        for (AssistantReferee exampleAssReferee : aReferees) {
            System.out.println(i + " :" + exampleAssReferee);
            ++i;
        }
    }

    public void showReferees_MainAndAssistant() {
        int i = 0;
        System.out.println("Sedziowie glowni");
        for (Referee exampleReferee : referees) {
            System.out.println(i + " :" + exampleReferee);
            ++i;
        }
        System.out.println("Sedziowie asystujacy");
        for (AssistantReferee exampleAssReferee : aReferees) {
            System.out.println(exampleAssReferee);
            ++i;
        }
    }

    public int getAmountOfReferee() {
        int i = -1;
        for (Referee exampleReferee : referees)
            ++i;
        return i;
    }

    public int getAmountOfAssistantReferee() {
        int i = -1;
        for (AssistantReferee exampleAssReferee : aReferees)
            ++i;
        return i;
    }

    public int getTypeOfTournament() {
        return typeOfTournament;
    }

    public void showAllTeams() {
        int i = 0;
        for (Team team : teams) {
            System.out.println(team);
            ++i;
        }
    }

    public void removeDonator(int index) {
        donators.remove(index);
    }

    public void removeReferee(int index) {
        referees.remove(index);
    }

    public void removeAssistantReferee(int index) {
        aReferees.remove(index);
    }

    public int getAmountOfDonators() {
        int i = -1;
        for (Donator exampleDonators : donators)
            ++i;
        return i;
    }

    public int getAmountOfTeams() {
        int i = -1;
        for (Team exampleTeam : teams)
            ++i;
        return i;
    }

    public void showFinalScores() {
            for (Match match : matches) {
                System.out.println(match);
            }
            for (Match match : matchesOfSemiFinal) {
                System.out.println(match);
            }
            System.out.println(matchOfFinal);
    }

    public void addMatch(Match m) {
        matches.add(m);
    }

    public LinkedList<Match> getMatches() {
        return matches;
    }

    public Match getMatchRoundRobin(int index) {
        return matches.get(index);
    }

    public Match getMatchSemiFinals(int index) {
        return matchesOfSemiFinal.get(index);
    }

    public Match getMatchFinal() {
        return matchOfFinal;
    }

    public void saveToFile() {

    }

    public void importFromFile() {

    }
    public void setNRofMatches(int exampleInteger){
        nrofMatches=exampleInteger;
    }
    public Team getWinner() {
        return winner;
    }

    public String getNameOfTournament() {
        return nameOfTheTournament;
    }

    public String matchesToString() {
        String s = "";
        for (Match match : matches) {
            s += match.toString();
            ++nrofMatches;
        }
        for (Match match : matchesOfSemiFinal) {
            s += match.toString();
            ++nrofMatches;
        }
        s += matchOfFinal.toString();
        ++nrofMatches;
        AllMaches = s;
        return s;
    }

    public void showAllMatchesIn_RoundRobin_SemiFinals_Finals(int parameter) {
        int i = 0;
        switch (parameter) {
            case 0:
                for (Match exampleMatch : matches) {
                    System.out.println(i + ":" + exampleMatch);
                    ++i;
                }
                break;
            case 1:
                for (Match exampleMatch : matchesOfSemiFinal) {
                    System.out.println(i + ":" + exampleMatch);
                    ++i;
                }
                break;
            case 2:
                System.out.println("0" + matchOfFinal);
                break;
        }
    }

    public int countingAmountOfMatchesInRoundRobin(int parameter) {
        switch (parameter) {

            case 0:
                int i = -1;
                for (Match exampleMatch : matches)
                    ++i;
                return i;
            case 1:
                return 3;
            default:
                return 0;
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

    public String toStr1ing() {
        return nameOfTheTournament + " " + prizePool + " " + typeOfTournament + " " + nrofMatches; //FIXME to też średnio działa, bo mi przeszkadza i nie mogę skorzystaż z metody .toString bo ta która jest tutaj ja nadpisuje
    }

}
