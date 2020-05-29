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
			System.out.println("1. Dodaj turniej.");
			System.out.println("2. Wyświetl listę turniejów.");
			System.out.println("3. Przejdź do turnieju.");
			System.out.println("4. Zakończ program.");
			wybór = klawiatura.nextInt();
			switch (wybór){
				case 1:{
					System.out.println("Napisz nazwę turnieju, którego chcesz dodać: ");
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
			System.out.println("1. Zarządzaj drużynami");
			System.out.println("2. Zarządzaj sędziami");
			System.out.println("3. Zarządzaj sponsorami");
			System.out.println("4. Przejdź do rozgrywek");
			System.out.println("5. Cofnij się");
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
		System.out.println("1. Dodaj drużynę");
		System.out.println("2. Usuń drużynę");
		System.out.println("3. Pokaż wszystkie drużyny");
		System.out.println("4. Cofnij się");
		Scanner klawiatura = new Scanner(System.in);
		int wybór = 0;
		wybór = klawiatura.nextInt();
		switch (wybór){
			case 1:{
				System.out.println("Wpisz nazwę drużyny: ");
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
		System.out.println("1. Dodaj sędziego");
		System.out.println("2. Usuń sędziego");
		System.out.println("3. Pokaż wszystkich sędziów");
		System.out.println("4. Cofnij się");
		Scanner klawiatura = new Scanner(System.in);
		int wybór = 0;
		wybór = klawiatura.nextInt();
		switch (wybór){
			case 1:{
				System.out.println("Wpisz nazwę sędziego: ");
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
		System.out.println("1. Dodaj sponsora");
		System.out.println("2. Usuń sponsora");
		System.out.println("3. Pokaż wszystkich sponsoró");
		System.out.println("4. Cofnij się");
		Scanner klawiatura = new Scanner(System.in);
		int wybór = 0;
		wybór = klawiatura.nextInt();
		switch (wybór){
			case 1:{
				System.out.println("Wpisz nazwę sponsora: ");
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
			System.out.println("1. Stwórz mecze");
			System.out.println("2. Pokaż wyniki meczów");
			System.out.println("3. Wyświetlanie wszysktich meczy");
			System.out.println("4. Ustaw wynik meczu");
			System.out.println("5. Cofnij się");
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
