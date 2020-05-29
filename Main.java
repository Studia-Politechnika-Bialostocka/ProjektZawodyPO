package def;

import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;
public class Main {

	public static void main(String[] args) {
		LinkedList<Tournament> listOfTournaments = new LinkedList<>();
		LinkedList<Referee> listOfReferees = new LinkedList<>();
		LinkedList<AssistantReferee> listOfAssistantReferees = new LinkedList<>();
		Scanner klawiatura = new Scanner(System.in);
		int menu = 0;
		int menu2 = 0;
		int wybór = 0;
		int wybór2 = 0;
		while(true){
			System.out.println("---------------MENU---------------");
			System.out.println("1. Add a tournament.");
			System.out.println("2. Display list of tournaments.");
			System.out.println("3. Select a tournament.");
			System.out.println("4. Exit the program.");
			wybór = klawiatura.nextInt();
			switch (wybór){
				case 1:{
					System.out.println("Enter desired tournament name: ");
					String nazwa = klawiatura.next();
					//listOfTournaments.add(new Tournament(nazwa), 100,listOfReferees,);
				}break;
				case 2:{
					//wyświetla listę turniejów
					int i=1;
					for(Tournament exampleTournament: listOfTournaments)
						System.out.println(exampleTournament.getNameOfTournament());
				}break;
				case 3:{
					menu2();
					//przejdź do turnieju
				}break;
				case 4:{
					System.exit(0);
				}
			}
		}

	}

	private static void menu2(){
		System.out.println("---------------MENU2---------------");
		Scanner klawiatura = new Scanner(System.in);
		int cofnijSię = 0;
		int wybór2 = 0;
		while(cofnijSię == 0){
			System.out.println("1. Manage teams");
			System.out.println("2. Manage referees");
			System.out.println("3. Manage sponsors");
			System.out.println("4. Go to playoffs");
			System.out.println("5. Go back");
			wybór2 = klawiatura.nextInt();
			switch (wybór2){
				case 1:{
					zarządzajDrużynami();
				}break;
				case 2:{
					zarządzajSędziami();
				}break;
				case 3:{
					zarządzajSponsorami();
				}break;
				case 4:{
					przejdzDoRozgrywek();
				}break;
				case 5:{
					cofnijSię = 1;
				}
				break;
				default: break;

			}
		}
	}

	private static void zarządzajDrużynami(){
		System.out.println("1. Add a team");
		System.out.println("2. Remove a team");
		System.out.println("3. Show all teams");
		System.out.println("4. Go back");
		Scanner klawiatura = new Scanner(System.in);
		int wybór = 0;
		wybór = klawiatura.nextInt();
		switch (wybór){
			case 1:{
				System.out.println("Entered desired team name: ");
				String nazwaDrużyny = klawiatura.next();
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

	private static void zarządzajSędziami(){
		System.out.println("1. Add a referee");
		System.out.println("2. Remove a referee");
		System.out.println("3. Show all referees");
		System.out.println("4. Go back");
		Scanner klawiatura = new Scanner(System.in);
		int wybór = 0;
		wybór = klawiatura.nextInt();
		switch (wybór){
			case 1:{
				System.out.println("Enter name of referee: ");
				String nazwaSędziego = klawiatura.next();
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

	private static void zarządzajSponsorami(){
		System.out.println("1. Add a sponsor");
		System.out.println("2. Remove a sponsor");
		System.out.println("3. Show all sponsors");
		System.out.println("4. Go back");
		Scanner klawiatura = new Scanner(System.in);
		int wybór = 0;
		wybór = klawiatura.nextInt();
		switch (wybór){
			case 1:{
				System.out.println("Enter name of sponsor: ");
				String nazwaSponsora = klawiatura.next();

				//dodaj sponsora
			}break;
			case 2:{
				//usuń sponsora
			}break;
			case 3:{
				//pokaż wszystkich sponsorów
			}
			case 4:{
				break;
			}
			default:break;
		}
	}

	private static void przejdzDoRozgrywek(){
		Scanner klawiatura = new Scanner(System.in);
		int menu = 0;
		int wybór = 0;
		while(menu == 0){
			System.out.println("---------------MENU3---------------");

			System.out.println("1. Create a match");
			System.out.println("2. Show match results");
			System.out.println("3. Show all matches");
			System.out.println("4. Set a match result");
			System.out.println("5. Go back");

			wybór = klawiatura.nextInt();
			switch (wybór){
				case 1:{
					//stwórz mecz
				}break;
				case 2:{
					//pokaż wyniki meczów
				}break;
				case 3:{
					//wyświetlanie wszystkich meczy
				}break;
				case 4:{
					//ustaw wynik meczu
				}break;
				case 5:{
					menu = 1;
				}
			}
		}
	}
}
