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
	private LinkedList<Donator> listOfDonators = new LinkedList<>();

	public LinkedList<Tournament> getTournaments() {
		return listOfTournaments;
	}

	public void addDonatorToList(Donator donatorInParameter)
	{
		listOfDonators.add(donatorInParameter);
	}

	public void showAllDonatorsFromBeach()
	{
		int i=0;
		for(Donator exampleDonator: listOfDonators)
		{
			System.out.println(i+" :"+exampleDonator);
			++i;
		}
	}

	public int getAmountOfDonators()
	{
		int i=-1;
		for(Donator exampleDonator: listOfDonators)
			++i;
		return i;
	}

	public Donator getDonator(int index){
		return listOfDonators.get(index);

	}
	public void showAllTournaments() {
		for (int i = 0; i < listOfTournaments.size(); i++) {
			System.out.println((i) + ". " + listOfTournaments.get(i).getNameOfTournament());
		}
	}

	public void removeDonator(int index){ listOfDonators.remove(index);}

	public void addTournament(Tournament t) {
		listOfTournaments.add(t);
	}

	public void newvTeamList(LinkedList<Volleyball> s){
		vTeams= s;
	}
	
	public LinkedList<Volleyball> getVTeams() { return vTeams; }
	
	public LinkedList<Dodgeball> getDTeams() {
		return dTeams;
	}
	
	public LinkedList<Tug_of_War> getTTeams() {
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
	
	public void deleteVTeam(int index) {
		vTeams.remove(index);
	}
	
	public void deleteDTeam(int index) {
		dTeams.remove(index);
	}
	
	public void deleteTTeam(int index) {
		tTeams.remove(index);
	}
	
	public void showAllVTeams() {
		int i = 0;
		for (Volleyball vteam : vTeams) {
			System.out.println(i + " :" + vteam);
			i++;
		}
	}
	
	public void showAllDTeams()
	{
		int i=0;
		for (Dodgeball dteam : dTeams) {
			System.out.println(i+" :"+dteam);
			++i;
		}
	}
	
	public void showAllTTeams()
	{
		int i=0;
		for (Tug_of_War tteam : tTeams) {
			System.out.println(i + " :" + tteam);
			++i;
		}
	}
	public void showAllVDTTeams()
	{
		int i=0;
		for(Volleyball exampleMatch: vTeams) {
			System.out.println(i+" :"+exampleMatch);
			++i;
		}
		for(Dodgeball exampleMatch: dTeams) {
			System.out.println(i + " :" + exampleMatch);
			++i;
		}
		for(Tug_of_War exampleMatch: tTeams){
			System.out.println(i+" :"+exampleMatch);
			++i;
		}
	}
	public int getAmountOfTeams(int parameter)
	{
		int i=-1;
		switch(parameter){
			case 0:
				for(Volleyball exampleTeam: vTeams)
					++i;
				return i;
			case 1:
				for(Dodgeball exampleTeam: dTeams)
					++i;
				return i;
			case 2:
				for(Tug_of_War exampleTeam: tTeams)
					++i;
				return i;
			default:
				return 0;
		}
	}
	public void addReferee(Referee r) {
		listOfReferee.add(r);
	}
	
	public void addAssistantReferee(AssistantReferee ar) {
		listOfAssistantReferee.add(ar);
	}
	
	public void deleteReferee(int r) {
		listOfReferee.remove(r);
	}
	
	public void deleteAssistantReferee(int ar) {
		listOfAssistantReferee.remove(ar);
	}
	

	public void showAllReferees(){
		System.out.println("Sedziowie glowni");
		int i=0;
		for (Referee exampleReferee : listOfReferee) {
			System.out.println(i + " :" + exampleReferee);
			++i;
		}
	}
	public void showAllAssistantReferee(){
		System.out.println("Sedziowie asystujacy");
		int i=0;
		for (AssistantReferee exampleAssReferee : listOfAssistantReferee) {
			System.out.println(i + " :" + exampleAssReferee);
			++i;
		}
	}
	public void showReferees_MainAndAssistant(){
		int i=0;
		System.out.println("Sedziowie glowni");
		for (Referee exampleReferee : listOfReferee) {
			System.out.println(exampleReferee);
			++i;
		}
		System.out.println("Sedziowie asystujacy");
		for (AssistantReferee exampleAssReferee : listOfAssistantReferee) {
			System.out.println(exampleAssReferee);
			++i;
		}
	}
	public LinkedList<Referee> getReferee(){ return listOfReferee;}

	public LinkedList<AssistantReferee> getAssistantReferee(){return listOfAssistantReferee;}

	public LinkedList<Tournament> getTournament(){return listOfTournaments;}

	public int getAmountOfReferee()
	{
		int i=-1;
		for (Referee exampleReferee : listOfReferee)
			++i;
		return i;
	}
	public int getAmountOfAssistantReferee(){
		int i=-1;
		for (AssistantReferee exampleAssReferee : listOfAssistantReferee)
			++i;
		return i;
	}
	public LinkedList<Referee> getListOfReferee() {return listOfReferee;}

	public LinkedList<AssistantReferee> getListOfAssistantReferee() {return listOfAssistantReferee;}

	public void saveToFile(LinkedList<Volleyball> v,LinkedList<Dodgeball> d,LinkedList<Tug_of_War> t, LinkedList<Referee> r, LinkedList<AssistantReferee> ar, LinkedList<Tournament> tour) {
		try {
			FileWriter fileWriter = new FileWriter("VolleyballFile.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);
			for (Volleyball volleyball : v) {
				printWriter.print(volleyball.toString());
				printWriter.print("\n");
			}
			printWriter.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			FileWriter fileWriter = new FileWriter("DodgeballFile.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);
			for (Dodgeball dodgeball : d) {
				printWriter.print(dodgeball.toString());
				printWriter.print("\n");
			}
			printWriter.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			FileWriter fileWriter = new FileWriter("Tug_of_WarFile.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);
			for (Tug_of_War tug_of_war : t) {
				printWriter.print(tug_of_war.toString());
				printWriter.print("\n");
			}
			printWriter.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			FileWriter fileWriter = new FileWriter("RefereeFile.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);
			for (Referee referee : r) {
				printWriter.print(referee.toString());
				printWriter.print("\n");
			}
			printWriter.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			FileWriter fileWriter = new FileWriter("AssistanceRefereeFile.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);
			for (AssistantReferee assistantReferee : ar) {
				printWriter.print(assistantReferee.toString());
				printWriter.print("\n");
			}
			printWriter.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			FileWriter fileWriter = new FileWriter("TournamentFile.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);
			for (Tournament tournament : tour) {
				printWriter.print(tournament.toString());
				printWriter.print("\n");
			}
			printWriter.close();
		} catch (Exception e) {
			System.out.println(e);
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
