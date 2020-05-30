package def;

import java.util.Scanner;

public class Main {
	public static Beach beach = new Beach();
	public static void main(String[] args) {

		int menu = 0;
		int menu2 = 0;
		int choice = 0;
		int choice2 = 0;
		while(true){
			Scanner keyboard = new Scanner(System.in);
			System.out.println("---------------MENU---------------");
			System.out.println("1. Add a tournament.");
			System.out.println("2. Display list of tournaments.");
			System.out.println("3. Select a tournament.");
			System.out.println("4. Manage teams, referees etc. in beach(main base for info)");
			System.out.println("5. Exit the program.");
			choice = keyboard.nextInt();
			switch (choice){
				case 1:{
					System.out.println("Enter desired tournament name: ");
					String nazwa = keyboard.next();
					System.out.println("Enter desired type_Of_Tournament<0,2>:");
					int type_Of_Match = getChoice(0,2);
					System.out.println("Enter desired initial prize: ");
					int initialPrize = keyboard.nextInt();
					beach.addTournament(new Tournament(nazwa,100,beach.getListOfReferee(),beach.getListOfAssistantReferee(),type_Of_Match));
				}break;
				case 2:{
					//wyświetla listę turniejów
					beach.showAllTournaments();
				}break;
				case 3:{
					beach.showAllTournaments();
					System.out.print("Choose a tournament: ");
					int tIndex = keyboard.nextInt(); //-1 bo showTournaments zaczyna od jedynki
					menu2(beach.getTournaments().get(tIndex));
					//przejdź do turnieju
				}break;
				case 4:{
					menuForBeach();
				}break;
				case 5:{
					System.exit(0);
				}
			}
		}

	}

	private static void menu2(Tournament tournament){

		Scanner klawiatura = new Scanner(System.in);
		int goBack = 0;
		int choice2 = 0;
		while(goBack == 0){
			System.out.println("---------------MENU2---------------");
			System.out.println("1. Manage teams");
			System.out.println("2. Manage referees");
			System.out.println("3. Manage sponsors");
			System.out.println("4. Go to playoffs");
			System.out.println("5. Go back");
			choice2 = klawiatura.nextInt();
			switch (choice2){
				case 1:{
					manageTeams(tournament);
				}break;
				case 2:{
					manageReferees(tournament);
				}break;
				case 3:{
					manageSponsors(tournament);
				}break;
				case 4:{
					goToPlayoffs(tournament);
				}break;
				case 5:{
					goBack = 1;
				}
				break;
				default: break;

			}
		}
	}

	private static void manageTeams(Tournament tournament){
		System.out.println("1. Add a team");
		System.out.println("2. Remove a team");
		System.out.println("3. Show all teams");
		System.out.println("4. Go back");
		Scanner keyboard = new Scanner(System.in);
		int choice = 0;
		choice = keyboard.nextInt();
		switch (choice){
			case 1:{
				addTeam(tournament);
			}break;
			case 2:{
				//usuń drużynę
				tournament.showAllTeams();
				System.out.println("Wybierz druzyne, ktora chcesz usunac:");
				int delete = getChoice(0,tournament.getAmountOfTeams());
				tournament.removeTeam(delete);
			}break;
			case 3:{
				//pokaż wszystkie drużyny
				tournament.showAllTeams();
			}
			case 4:{
				break;
			}
			default:break;
		}
	}

	private static void addTeam(Tournament tournament){
		System.out.println("1. Add an existing team");
		System.out.println("2. Create a new team");
		System.out.println("3. Go back");
		Scanner keyboard = new Scanner(System.in);
		int choice = 0;
		choice = keyboard.nextInt();
		switch (choice){
			case 1:{
				//Adding existing teams from list
				int teamIndex;
				switch (tournament.getTypeOfTournament()) {
					case 0:
						beach.showAllVTeams();
						System.out.print("Choose a volleyball team: ");
						teamIndex = keyboard.nextInt();
						tournament.addTeam(beach.getVTeams().get(teamIndex));
						break;
					case 1:
						beach.showAllDTeams();
						System.out.print("Choose a dodgeball team: ");
						teamIndex = keyboard.nextInt();
						tournament.addTeam(beach.getDTeams().get(teamIndex));
						break;
					case 2:
						beach.showAllTTeams();
						System.out.print("Choose a tug of war team: ");
						teamIndex = keyboard.nextInt();
						tournament.addTeam(beach.getTTeams().get(teamIndex));
						break;
				}
			}break;
			case 2:{
				//Creating and adding new teams;
				System.out.print("Enter desired team name: ");
				String teamName = keyboard.next();
				switch (tournament.getTypeOfTournament()) {
					case 0:
						Volleyball newVTeam = new Volleyball(teamName);
						beach.addVTeam(newVTeam);
						tournament.addTeam(newVTeam);
						break;
					case 1:
						Dodgeball newDTeam = new Dodgeball(teamName);
						beach.addDTeam(newDTeam);
						tournament.addTeam(newDTeam);
						break;
					case 2:
						Tug_of_War newTTeam = new Tug_of_War(teamName);
						beach.addTTeam(newTTeam);
						tournament.addTeam(newTTeam);
						break;
				}
			}break;
			case 3:{
				break;
			}
			default:break;
		}
	}

	private static void manageReferees(Tournament tournament){
		System.out.println("1. Add a referee");
		System.out.println("2. Remove a referee");
		System.out.println("3. Show all referees");
		System.out.println("4. Go back");
		Scanner keyboard = new Scanner(System.in);
		int choice = 0;
		choice = keyboard.nextInt();
		switch (choice){
			case 1:{
				System.out.println("Enter name of referee: ");
				String[] refereeName = keyboard.next().split(" ");
				//dodaj sędziego
				if(tournament.getTypeOfTournament()==0) {
					System.out.println("Czy to sedzia glowny(0) czy sedzia asystujacy(1):");
					int typeOfReferee = getChoice(0,1);
					if (typeOfReferee == 0) {
						tournament.addReferee(new Referee(refereeName[0], refereeName[1]));
						beach.addReferee(new Referee(refereeName[0],refereeName[1]));
					}
					else {
						tournament.addAssistantReferee(new AssistantReferee(refereeName[0], refereeName[1]));
						beach.addAssistantReferee(new AssistantReferee(refereeName[0],refereeName[1]));
					}
				}
				else				{
					tournament.addReferee(new Referee(refereeName[0],refereeName[1]));
					beach.addReferee(new Referee(refereeName[0],refereeName[1]));
				}
			}break;
			case 2:{
				//usun sedziow
				if(tournament.getTypeOfTournament()==0) {
					System.out.println("Pokazac sedziow glownych(0) czy asystujacych(1):");
					int choice_oftype = getChoice(0, 1);
					if (choice_oftype == 0) {
						tournament.showReferees();
						System.out.println("Podaj swoj wybor:");
						int delete = getChoice(0, tournament.getAmountOfReferee());
						tournament.removeReferee(delete);
					} else {
						tournament.showAssistantReferees();
						System.out.println("Podaj swoj wybor:");
						int delete = getChoice(0, tournament.getAmountOfAssistantReferee());
						tournament.removeAssistantReferee(delete);
					}
				}
				else{
					tournament.showReferees();
					System.out.println("Podaj swoj wybor:");
					int delete = getChoice(0, tournament.getAmountOfReferee());
					tournament.removeReferee(delete);
				}
			}break;
			case 3:{
				//pokaż wszystkich sędziów
				if(tournament.getTypeOfTournament()==0)
					tournament.showReferees_MainAndAssistant();
				else
					tournament.showReferees();
			}
			case 4:{
				break;
			}
			default:break;
		}
	}

	private static void manageSponsors(Tournament tournament){
		System.out.println("1. Add a sponsor");
		System.out.println("2. Add a sponsor from list");
		System.out.println("3. Remove a sponsor");
		System.out.println("4. Show all sponsors");
		System.out.println("5. Go back");
		Scanner keyboard = new Scanner(System.in);
		int choice = 0;
		choice = keyboard.nextInt();
		switch (choice){
			case 1:{
				//dodaj sponsora
				System.out.println("Enter name of sponsor: ");
				String[] sponsorName = keyboard.next().split(" ");
				System.out.println("Enter initial donation of sponsor: ");
				int initialDonation = keyboard.nextInt();
				tournament.addDonator(new Donator(sponsorName[0], sponsorName[1], initialDonation));
				beach.addDonatorToList(new Donator(sponsorName[0],sponsorName[1], initialDonation));
			}break;
			case 2: {
				beach.showAllDonatorsFromBeach();
				System.out.println("Wybierz swoj wybor<0:"+beach.getAmountOfDonators()+"> :");
				int choice_2=getChoice(0,beach.getAmountOfDonators());
				tournament.addDonator(beach.getDonator(choice_2));
			}break;
			case 3:{
				//usuń sponsora
				int choice_2;
				tournament.showDonators();
				System.out.println("Wybierz swoj wybor<0:"+tournament.getAmountOfDonators()+"> :");
				choice_2=getChoice(0,tournament.getAmountOfDonators());
				tournament.removeDonator(choice_2);
			}break;
			case 4:{
				tournament.showDonators();
			}
			case 5:{
				break;
			}
			default:break;
		}
	}

	private static void goToPlayoffs(Tournament tournament) {
		Scanner keyboard = new Scanner(System.in);
		int menu = 0;
		int choice = 0;
		int levelOfGaming = 0;
		while (menu == 0) {
			System.out.println("---------------MENU3---------------");
			System.out.println("1. Create a match/semifinals/finals");
			System.out.println("2. Show match results");
			System.out.println("3. Set a match result");
			System.out.println("4. Go back");
			choice = keyboard.nextInt();
			switch (choice) {
				case 1: {
					if (levelOfGaming == 0) {
						tournament.matchesOfRoundRobin();
						++levelOfGaming;
					}
					else if (levelOfGaming == 1 && tournament.areAllMatchesPlayedInRoundRobin() == true) {
						tournament.roundRobin();
						tournament.matchesOfSemiFinals();
						++levelOfGaming;
					}
					else if (levelOfGaming == 2 && tournament.areAllMatchesPlayedInSemiFinals() == true) {
						tournament.semiFinal();
						tournament.matchOfFinal();
						++levelOfGaming;
					}
					else if (levelOfGaming == 3 && tournament.areAllMatchesPlayedInFinals() == true) {
						tournament.finalGame();
						++levelOfGaming;
					}
					else if (levelOfGaming == 4)
						System.out.println("Zwyciezyl:" + tournament.getWinner().getTeamName());
				}
				break;
				case 2: {
					//pokaż wyniki meczów
					tournament.showAllMatchesIn_RoundRobin_SemiFinals_Finals(levelOfGaming);
				}
				break;
				case 3: {
					//ustaw wynik meczu
					int wybor;
					tournament.showAllMatchesIn_RoundRobin_SemiFinals_Finals(levelOfGaming);
					System.out.println("Podaj swoj wybor <0:" + tournament.countingAmountOfMatchesInRoundRobin(levelOfGaming) + ">:");
					wybor = getChoice(0, tournament.countingAmountOfMatchesInRoundRobin(levelOfGaming));
					tournament.getMatchRoundRobin(wybor).setResult();
				}
				break;
				case 4: {
					menu = 1;
				}
			}
		}
	}
		private static int getChoice(int d_gran, int g_gran) {
		int choice_within_method;
		Scanner in = new Scanner(System.in);
		do {
			choice_within_method = in.nextInt();
		}while(choice_within_method<d_gran  || choice_within_method > g_gran);
		return choice_within_method;
	}
	private static void menuForBeach(){

		Scanner klawiatura = new Scanner(System.in);
		int goBack = 0;
		int choice2 = 0;
		while(goBack == 0){
			System.out.println("---------------EDIT_BEACH---------------");
			System.out.println("1. Manage teams");
			System.out.println("2. Manage referees");
			System.out.println("3. Manage sponsors");
			System.out.println("4. Go back");
			choice2 = klawiatura.nextInt();
			switch (choice2){
				case 1:{
					manageTeamsForBeach();
				}break;
				case 2:{
					manageRefereesForBeach();
				}break;
				case 3:{
					manageSponsorsForBeach();
				}break;
				case 4:{
					goBack = 1;
				}
				break;
				default: break;

			}
		}
	}
	private static void manageTeamsForBeach(){
		System.out.println("1. Add a team");
		System.out.println("2. Remove a team");
		System.out.println("3. Show all teams");
		System.out.println("4. Go back");
		Scanner keyboard = new Scanner(System.in);
		int choice = 0;
		choice = keyboard.nextInt();
		switch (choice){
			case 1:{
				//dodaj drużynę
				System.out.println("Entered desired team name: ");
				String teamName = keyboard.next();
				System.out.println("What type of team is that? Volleyball (0), dodgeball(1) or tug_of_war(2)");
				int typeOfTournament=getChoice(0,2);
				switch(typeOfTournament){
					case 0:
						beach.addVTeam(new Volleyball(teamName));
						break;
					case 1:
						beach.addDTeam(new Dodgeball(teamName));
						break;
					case 2:
						beach.addTTeam(new Tug_of_War(teamName));
						break;
				}
			}break;
			case 2:{
				//usuń drużynę
				System.out.println("What type of team you want to delete? Volleyball (0), dodgeball(1), tyg_of_war(2)?");
				int typeOfTournament=getChoice(0,2);
				switch(typeOfTournament)
				{
					case 0:
						beach.showAllVTeams();
						System.out.println("Enter index of desired team:");
						int delete0 = getChoice(0,beach.getAmountOfTeams(0));
						beach.deleteVTeam(delete0);
						break;
					case 1:
						beach.showAllDTeams();
						System.out.println("Enter index of desired team:");
						int delete1 = getChoice(0,beach.getAmountOfTeams(1));
						beach.deleteDTeam(delete1);
						break;
					case 2:
						beach.showAllTTeams();
						System.out.println("Enter index of desired team:");
						int delete2 = getChoice(0,beach.getAmountOfTeams(2));
						beach.deleteTTeam(delete2);
						break;
				}
			}break;
			case 3:{
				beach.showAllVDTTeams();
			}
			case 4:{
				break;
			}
			default:break;
		}
	}

	private static void manageRefereesForBeach(){
		System.out.println("1. Add a referee");
		System.out.println("2. Remove a referee");
		System.out.println("3. Show all referees");
		System.out.println("4. Go back");
		Scanner keyboard = new Scanner(System.in);
		int choice = 0;
		choice = keyboard.nextInt();
		switch (choice) {
			case 1: {
				//dodaj sędziego
				System.out.println("Enter name of referee: ");
				String[] refereename = keyboard.next().split(" ");
				System.out.println("Czy to sedzia glowny(0) czy sedzia asystujacy(1):");
				int typeOfReferee = keyboard.nextInt();
				if (typeOfReferee == 0)
					beach.addReferee(new Referee(refereename[0], refereename[1]));
				else
					beach.addAssistantReferee(new AssistantReferee(refereename[0], refereename[1]));
			}
			break;
			case 2: {
				//usun sedziow
				System.out.println("Pokazac sedziow glownych(0) czy asystujacych(1):");
				int choice_oftype = getChoice(0, 1);
					if (choice_oftype == 0) {
						beach.showAllReferees();
						System.out.println("Podaj swoj wybor:");
						int delete = getChoice(0, beach.getAmountOfReferee());
						beach.deleteReferee(delete);
					}
						else {
							beach.showAllAssistantReferee();
						System.out.println("Podaj swoj wybor:");
						int delete = getChoice(0, beach.getAmountOfAssistantReferee());
						beach.deleteAssistantReferee(delete);
					}
				} break;
				case 3: {
					//pokaż wszystkich sędziów
					beach.showReferees_MainAndAssistant();
				}
				case 4: {
					break;
				}
				default:
					break;
			}
		}


	private static void manageSponsorsForBeach(){
		System.out.println("1. Add a sponsor");
		System.out.println("2. Remove a sponsor");
		System.out.println("3. Show all sponsors");
		System.out.println("4. Go back");
		Scanner keyboard = new Scanner(System.in);
		int choice = 0;
		choice = keyboard.nextInt();
		switch (choice){
			case 1:{
				//dodaj sponsora
				System.out.println("Enter name of sponsor: ");
				String[] sponsorName = keyboard.next().split(" ");
				System.out.println("Enter initial donation of sponsor: ");
				int initialDonation = keyboard.nextInt();
				beach.addDonatorToList(new Donator(sponsorName[0],sponsorName[1], initialDonation));
			}break;
			case 2:{
				//usuń sponsora
				int choice_2;
				beach.showAllDonatorsFromBeach();
				System.out.println("Wybierz swoj wybor<0:"+beach.getAmountOfDonators()+"> :");
				choice_2=getChoice(0,beach.getAmountOfDonators());
				beach.removeDonator(choice_2);
			}break;
			case 3:{
				beach.showAllDonatorsFromBeach();
			}
			case 4:{
				break;
			}
			default:break;
		}
	}
}

