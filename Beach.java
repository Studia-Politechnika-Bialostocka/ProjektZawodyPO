package def;

import java.io.*;
import java.util.LinkedList;
import java.io.Reader;


public class Beach{
	private LinkedList<Volleyball> vTeams=new LinkedList<>();
	private LinkedList<Dodgeball> dTeams = new LinkedList<>();
	private LinkedList<Tug_of_War> tTeams = new LinkedList<>();
	private LinkedList<Referee> listOfReferee = new LinkedList<>();
	private LinkedList<AssistantReferee> listOfAssistantReferee = new LinkedList<>();
	private LinkedList<Tournament> listOfTournaments = new LinkedList<>();
	
	public void addTournament(Tournament t) {
		listOfTournaments.add(t);
	}

	public void newvTeamList(LinkedList<Volleyball> s){
		vTeams= s;
	}
	
	public LinkedList<Volleyball> getVTeam() { return vTeams; }
	
	public LinkedList<Dodgeball> getDTeam() {
		return dTeams;
	}
	
	public LinkedList<Tug_of_War> getTTeam() {
		return tTeams;
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
		for (Volleyball vteam : vTeams) System.out.println(vteam);
	}
	
	public void showAllDTeams() {
		for (Dodgeball dteam : dTeams) System.out.println(dteam);
	}
	
	public void showAllTTeams() {
		for (Tug_of_War tteam : tTeams) System.out.println(tteam);
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
		for (Referee ref : listOfReferee) System.out.println(ref);
	}
	
	public void showAllAssistantReferee() {
		for (AssistantReferee aref : listOfAssistantReferee) System.out.println(aref);
	}

	public void saveToFileVolleyball(LinkedList<Volleyball> v) {
		try {
			FileWriter fileWriter = new FileWriter("VolleyballFile.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);
for(Volleyball volleyball : v) {
	printWriter.print(volleyball.toString());
	printWriter.print("\n");
}
			printWriter.close();
		} catch (Exception e) {
			System.out.println(e+"wwwww");
		}
	}

	public LinkedList<Volleyball> importFromFileVolleyball() {
		LinkedList<Volleyball> v = new LinkedList<Volleyball>();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("VolleyballFile.txt"));
			String buf;
			int index=0;
			while ((buf = in.readLine()) != null)
			{
				String[] s = buf.split(" ");
				Volleyball v1 = new Volleyball(s[0]);
				v1.setWins(Integer.parseInt(s[1]));
				v1.setLosses(Integer.parseInt(s[2]));
				v1.setSetsWon(Integer.parseInt(s[3]));
				v.add(v1);
			}
			} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		return v;
	}



}
