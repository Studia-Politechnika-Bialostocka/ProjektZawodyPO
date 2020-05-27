package def;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner klawiatura = new Scanner();

		int menu = 0;
		int wybór = 0;
		while(menu == 0){
			System.out.println("---------------MENU---------------");
			System.out.println("1. Dodaj turiej.");
			System.out.println("2. Zakończ program.");
			wybór = klawiatura.nextInt();
			switch (wybór){
				case 1:{

				} break;
				case 2:{
					System.exit(0);
				}break;
			}
		}

	}
	
}
