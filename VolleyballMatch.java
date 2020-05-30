package def;

public class VolleyballMatch extends Match {
	private AssistantReferee[] assistantReferees;
	
	public VolleyballMatch(Team teamOne, Team teamTwo, Referee ref, int typeOfMatch, AssistantReferee ar1, AssistantReferee ar2) {
		super(teamOne, teamTwo, ref, typeOfMatch);
		assistantReferees = new AssistantReferee[2];
		assistantReferees[0] = ar1;
		assistantReferees[1] = ar2;
		ar1.documentMatch();
		ar2.documentMatch();
	}

	public String toString()
	{
		if (isScoreSet)
			return "Konkurencja:Siatkowka. " + teamOne + " i " + teamTwo + ". Zwyciezca:" + winner + ". Wynik: " + finalScore+"\n";
		else
			return "Konkurencja:Siatkowka. " + teamOne + " i " + teamTwo + ". Zwyciezca: nieustalony. Wynik: nieustalony\n";
	}

}
