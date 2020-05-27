package def;

public class VolleyballMatch extends Match {
	private AssistantReferee[] assistantReferees;
	
	public VolleyballMatch(Team teamOne, Team teamTwo, Referee ref, int typeOfMatch, AssistantReferee ar1, AssistantReferee ar2) {
		super(teamOne, teamTwo, ref, typeOfMatch);
		assistantReferees[0] = ar1;
		assistantReferees[1] = ar2;
		ar1.documentMatch();
		ar2.documentMatch();
	}

	

}
