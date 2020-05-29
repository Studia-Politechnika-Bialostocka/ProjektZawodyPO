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
			System.out.println("4. Exit the program.");
			choice = keyboard.nextInt();
			switch (choice){
				case 1:{
					System.out.println("Enter desired tournament name: ");
					String nazwa = keyboard.next();
					beach.addTournament(new Tournament());
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
					System.exit(0);
				}
			}
		}

	}

	private static void menu2(Tournament tournament){
		System.out.println("---------------MENU2---------------");
		Scanner klawiatura = new Scanner(System.in);
		int goBack = 0;
		int choice2 = 0;
		while(goBack == 0){
			System.out.println("1. Manage teams");
			System.out.println("2. Manage referees");
			System.out.println("3. Manage sponsors");
			System.out.println("4. Go to playoffs");
			System.out.println("5. Go back");
			choice2 = klawiatura.nextInt();
			switch (choice2){
				case 1:{
					manageTeams();
				}break;
				case 2:{
					manageReferees();
				}break;
				case 3:{
					manageSponsors(tournament);
				}break;
				case 4:{
					goToPlayoffs();
				}break;
				case 5:{
					goBack = 1;
				}
				break;
				default: break;

			}
		}
	}

	private static void manageTeams(){
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

	private static void manageReferees(){
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
		System.out.println("2. Remove a sponsor");
		System.out.println("3. Show all sponsors");
		System.out.println("4. Go back");
		Scanner keyboard = new Scanner(System.in);
		int choice = 0;
		choice = keyboard.nextInt();
		switch (choice){
			case 1:{
				System.out.println("Enter name of sponsor: ");
				String[] sponsorName = keyboard.next().split(" ");
				System.out.println("Enter initial donation of sponsor: ");
				int initialDonation = keyboard.nextInt();
				//dodaj sponsora
				tournament.addDonator(new Donator(sponsorName[0], sponsorName[1], initialDonation));
			}break;
			case 2:{

				//usuń sponsora
			}break;
			case 3:{
				tournament.showDonators();
			}
			case 4:{
				break;
			}
			default:break;
		}
	}

	private static void goToPlayoffs(Tournament tournament){
		Scanner keyboard = new Scanner(System.in);
		int menu = 0;
		int choice = 0;
		int levelOfGaming = 0;
		while(menu == 0){
			System.out.println("---------------MENU3---------------");
			System.out.println("1. Create a match/semifinals/finals");
			System.out.println("2. Show match results");
			System.out.println("3. Set a match result");
			System.out.println("4. Go back");
			choice = keyboard.nextInt();
			switch (choice){
				case 1:{
					if(levelOfGaming==0) {
						tournament.roundRobin();
						++levelOfGaming;
					}
					if(levelOfGaming==1 && tournament.areAllMatchesPlayedInRoundRobin()==true) {
						tournament.matchesOfSemiFinals();
						++levelOfGaming;
					}
					if(levelOfGaming==2 && tournament.areAllMatchesPlayedInSemiFinals()==true) {
						tournament.matchOfFinal();
						++levelOfGaming;
					}
					if(levelOfGaming==3 && tournament.areAllMatchesPlayedInFinals()==true){
						tournament.finalGame();
					}
				}break;
				case 2:{
					//pokaż wyniki meczów
					tournament.showAllMatchesIn_RoundRobin_SemiFinals_Finals(levelOfGaming);
					}
				}break;
				case 3:{
					//ustaw wynik meczu
					int wybor;
					tournament.showAllMatchesIn_RoundRobin_SemiFinals_Finals(levelOfGaming);
					System.out.println("Podaj swoj wybor <1:"+tournament.countingAmountOfMatchesInRoundRobin()+">:");
					wybor = wczytaj(1,tournament.countingAmountOfMatchesInRoundRobin());
					tournament.getMatchRoundRobin(wybor).setResult();
				}break;
				case 4:{
					menu = 1;
				}
			}
		}

		private static int wczytaj(int d_gran, int g_gran) {
		int wybor;
		Scanner in = new Scanner(System.in);
		do {
			wybor = in.nextInt();
		}while(wybor>d_gran  || wybor < g_gran);
		return wybor;
	}

	}
}
