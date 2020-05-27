package def;

import java.util.LinkedList;

public class Beach {
	private LinkedList<Volleyball> vTeams=new LinkedList<>();
	private LinkedList<Dodgeball> dTeams = new LinkedList<>();
	private LinkedList<Tug_of_War> tTeams = new LinkedList<>();
	private LinkedList<Referee> listOfReferee = new LinkedList<>();
	private LinkedList<AssistantReferee> listOfAssistantReferee = new LinkedList<>();
	private LinkedList<Tournament> listOfTournaments = new listOfTournaments<>();
	
	public void addTournament(Tournament t) {
		listOfTournaments.add(t);
	}
	
	public Volleyball getVTeam(int index) {
		return vTeams.get(index);
	}
	
	public Dodgeball getDTeam(int index) {
		return dTeams.get(index);
	}
	
	public Tug_of_War getTTeam(int index) {
		return tTeams.get(index);
	}
	
	public void addVTeam(Volleyball v) {
		vTeams.add(v);
	}
	
	public void addDTeam(Dodgeball d) {
		dTeams.add(d);
	}
	
	public void addTTeam(Tug_of_War t) {
		tTeams.add(t);
	}
	
	public void deleteVTeam(Volleyball v) {
		vTeams.remove(v);
	}
	
	public void deleteDTeam(Dodgeball d) {
		dTeams.remove(d);
	}
	
	public void deleteTTeam(Tug_of_War t) {
		tTeams.remove(t);
	}
	
	public void showAllVTeams() {
		
	}
	
	public void showAllDTeams() {
		
	}
	
	public void showAllTTeams() {
		
	}
	
	public void addReferee(Referee r) {
		listOfReferee.add(r);
	}
	
	public void addAssistantReferee(AssistantReferee ar) {
		listOfAssistantReferee.add(ar);
	}
	
	public void deleteReferee(Referee r) {
		listOfReferee.remove(r);
	}
	
	public void deleteAssistantReferee(AssistantReferee ar) {
		listOfAssistantReferee.remove(ar);
	}
	
	public void showAllReferee() {
		
	}
	
	public void showAllAssistantReferee() {
		
	}
	
	public void saveToFile() {
		
	}
	
	public void importFromFile() {
		
	}
	
	
}
