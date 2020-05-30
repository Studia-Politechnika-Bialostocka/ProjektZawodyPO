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
			System.out.println("4. Manage teams, referees etc. in beach");
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
					int i=1;
					beach.showAllTournaments();
				}break;
				case 3:{
					beach.showAllTournaments();
					System.out.print("Choose a tournament: ");
					int tIndex = keyboard.nextInt() - 1; //-1 bo showTournaments zaczyna od jedynki
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
				System.out.println("Entered desired team name: ");
				String teamName = keyboard.next();
				System.out.println("");
				//dodaj drużynę
			}break;
			case 2:{
				//usuń drużynę
			}break;
			case 3:{
				//pokaż wszystkie drużyny
			}
			case 4:{
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
				String nazwaSędziego = keyboard.next();
				//dodaj sędziego
			}break;
			case 2:{
				//usuń sędziego
			}break;
			case 3:{
				//pokaż wszystkich sędziów
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
						tournament.roundRobin();
						++levelOfGaming;
					}
					if (levelOfGaming == 1 && tournament.areAllMatchesPlayedInRoundRobin() == true) {
						tournament.matchesOfSemiFinals();
						++levelOfGaming;
					}
					if (levelOfGaming == 2 && tournament.areAllMatchesPlayedInSemiFinals() == true) {
						tournament.matchOfFinal();
						++levelOfGaming;
					}
					if (levelOfGaming == 3 && tournament.areAllMatchesPlayedInFinals() == true) {
						tournament.finalGame();
						++levelOfGaming;
					}
					if (levelOfGaming == 4)
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
				System.out.println("Entered desired team name: ");
				String teamName = keyboard.next();
				System.out.println("");
				//dodaj drużynę
			}break;
			case 2:{
				//usuń drużynę
			}break;
			case 3:{
				//pokaż wszystkie drużyny
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

