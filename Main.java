package def;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Beach beach = new Beach();

    //Glowne menu, przy wejsciu do programu
    public static void main(String[] args) {
        int choice = 0;
        while (true) {
            try {
                Scanner keyboard = new Scanner(System.in);
                //caly napis do main
                mainMenuShowUp();

                choice = keyboard.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Wpisz nazwe turnieju: ");
                        String nazwa = keyboard.next();
                        if (beach.ifExistInList(nazwa))
                            throw new IfExistInListException();

                        System.out.println("Wybierz rodzaj turnieju: Siatkowka(0), Dwa Ognie(1), Przeciaganie Liny(2)");

                        int type_Of_Match = getChoice(0, 2);
                        System.out.println("Podaj poczatkowa nagrode: ");
                        double initialPrize = 0.0;

                        while (true) {
                            try {
                                initialPrize = keyboard.nextDouble();
                                break;
                            } catch (Exception e) {
                                System.out.println("Wpisz poprawną wartość");
                                keyboard.next();

                            }

                        }

                        beach.addTournament(new Tournament(nazwa, initialPrize, beach.getListOfReferee(), beach.getListOfAssistantReferee(), type_Of_Match));
                        break;
                    case 2:
                        //wyświetla listę turniejów
                        beach.showAllTournaments();
                        break;
                    case 3:
                        beach.showAllTournaments();
                        System.out.print("Wybierz turniej: ");
                        int tIndex = keyboard.nextInt(); //-1 bo showTournaments zaczyna od jedynki
                        menu2(beach.getTournaments().get(tIndex));
                        //przejdź do turnieju
                        break;
                    case 4:
                        menuForBeach();
                        break;
                    case 5:
                        beach.saveToFile();
                        break;
                    case 6:
                        importInfo();
                        break;
                    case 7:
                        System.exit(0);
                        break;
                    default:
                        throw new InvalidValueException(choice);
                }
            } catch (InvalidValueException e) {
                System.out.println("Niepoprawna wartosc: " + e.getInvalidValue() + ". Sprobuj ponownie");
            } catch (InputMismatchException e) {
                System.out.println("Wpisz poprawną wartosc. Sprobuj ponownie. ");
            } catch (IfExistInListException e) {
                System.out.println("Taka nazwa już istnieje. Sprobuj ponownie.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid Index");
            }
        }

    }

    //importowanie danych --start--
    private static void importInfo() {
        beach.newvTeamList(beach.importFromFileVolleyball());
        beach.newdTeamList(beach.importFromFileDodgeball());
        beach.newtTeamList(beach.importFromFileTug_of_War());
        beach.newReferee(beach.importFromFileRefeere());
        beach.newAssistanceReferee(beach.importFromFileAssistantReferee());
        beach.importFromFileTournament();
    }

    //importowanie danych --stop--
    //zapisywanie danych --start--
    private static void mainMenuShowUp() {

        System.out.println("---------------MENU---------------");
        System.out.println("1. Dodaj turniej.");
        System.out.println("2. Pokaz liste turniejow.");
        System.out.println("3. Wybierz turniej.");
        System.out.println("4. Zarzadzaj druzynami, sedziami etc. w plazy");
        System.out.println("5. Zapisz do pliku");
        System.out.println("6. Wczytaj z pliku ");
        System.out.println("7. Zakoncz program");

    }

    //cale glowne menu
    private static void menu2(Tournament tournament) {
        Scanner klawiatura = new Scanner(System.in);
        int goBack = 0;
        int choice2;
        while (goBack == 0) {

            System.out.println("---------------MENU2---------------");
            System.out.println("1. Zarzadzaj druzynami");
            System.out.println("2. Zarzadzaj sedziami");
            System.out.println("3. Zarzadzaj sponsorami");
            System.out.println("4. Przejdz do rozgrywek");
            System.out.println("5. Wroc");

            choice2 = klawiatura.nextInt();
            switch (choice2) {
                case 1:
                    manageTeams(tournament);
                    break;
                case 2:
                    manageReferees(tournament);
                    break;
                case 3:
                    manageSponsors(tournament);
                    break;
                case 4:
                    goToPlayoffs(tournament);
                    break;
                case 5:
                    goBack = 1;
                    break;
                default:
                    try {
                        throw new InvalidValueException(choice2);
                    } catch (InvalidValueException e) {
                        System.out.println("Nieprawidlowa wartosc: " + e.getInvalidValue() + ". Sprobuj ponownie");
                    }
            }
        }
    }

    private static void manageTeams(Tournament tournament) {
        boolean check = true;
        do {
            System.out.println("1. Dodaj druzyne");
            System.out.println("2. Usun druzyne");
            System.out.println("3. Pokaz wszystkie druzyny");
            System.out.println("4. Wroc");
            Scanner keyboard = new Scanner(System.in);
            int choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                    addTeam(tournament);
                    break;
                case 2:
                    //usuń drużynę
                    tournament.showAllTeams();
                    System.out.println("Wybierz druzyne, ktora chcesz usunac:");
                    int delete = getChoice(0, tournament.getAmountOfTeams());
                    tournament.removeTeam(delete);
                    break;
                case 3:
                    //pokaż wszystkie drużyny
                    tournament.showAllTeams();
                    break;
                case 4:
                    check = false;
                    break;
                default:
                    break;
            }
        } while (check);
    }

    private static void addTeam(Tournament tournament) {
        boolean check = true;
        do {
            System.out.println("1. Dodaj istniejaca druzyne");
            System.out.println("2. Stworz nowa druzyne");
            System.out.println("3. Wroc");
            Scanner keyboard = new Scanner(System.in);
            int choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                    //Adding existing teams from list
                    int teamIndex;
                    switch (tournament.getTypeOfTournament()) {
                        case 0:
                            beach.showAllVTeams();
                            System.out.print("Wybierz druzyne siatkowki: ");
                            teamIndex = keyboard.nextInt();
                            if (tournament.getTeams().indexOf(tournament.getTeams().get(teamIndex)) == -1)
                                tournament.addTeam(beach.getVTeams().get(teamIndex));
                            break;
                        case 1:
                            beach.showAllDTeams();
                            System.out.print("Wybierz druzyne dwoch ogni: ");
                            teamIndex = keyboard.nextInt();
                            if (tournament.getTeams().indexOf(tournament.getTeams().get(teamIndex)) == -1)
                                tournament.addTeam(beach.getDTeams().get(teamIndex));
                            break;
                        case 2:
                            beach.showAllTTeams();
                            System.out.print("Wybierz druzyne przeciagania liny: ");
                            teamIndex = keyboard.nextInt();
                            if (tournament.getTeams().indexOf(tournament.getTeams().get(teamIndex)) == -1)
                                tournament.addTeam(beach.getTTeams().get(teamIndex));
                            break;
                    }
                    break;
                case 2:
                    //Creating and adding new teams;
                    System.out.print("Wpisz nazwe druzyny: ");
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
                    break;
                case 3:
                    check = false;
                    break;
                default:
                    break;
            }
        } while (check);
    }

    private static void manageReferees(Tournament tournament) {
        boolean check = true;
        do {
            System.out.println("1. Dodaj sedziego");
            System.out.println("2. Usun sedziego");
            System.out.println("3. Pokaz wszystkich sedzi");
            System.out.println("4. Wroc");
            Scanner keyboard = new Scanner(System.in);
            int choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Wpisz imie: ");
                    String refereeName = keyboard.next();
                    System.out.println("Wpisz nazwisko: ");
                    String refereelastName = keyboard.next();
                    //dodaj sędziego
                    if (tournament.getTypeOfTournament() == 0) {
                        System.out.println("Czy to sedzia glowny(0) czy sedzia asystujacy(1):");
                        int typeOfReferee = getChoice(0, 1);
                        if (typeOfReferee == 0) {
                            beach.addReferee(new Referee(refereeName, refereelastName));
                        } else {
                            beach.addAssistantReferee(new AssistantReferee(refereeName, refereelastName));
                        }
                    } else {
                        Referee r = new Referee(refereeName, refereelastName);
                        beach.addReferee(r);
                    }
                    break;
                case 2:
                    //usun sedziow
                    if (tournament.getTypeOfTournament() == 0) {
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
                    } else {
                        tournament.showReferees();
                        System.out.println("Podaj swoj wybor:");
                        int delete = getChoice(0, tournament.getAmountOfReferee());
                        tournament.removeReferee(delete);
                    }
                    break;
                case 3:
                    //pokaż wszystkich sędziów
                    if (tournament.getTypeOfTournament() == 0)
                        tournament.showReferees_MainAndAssistant();
                    else
                        tournament.showReferees();
                    break;
                case 4:
                    check = false;
                    break;
                default:
                    break;
            }
        } while (check);
    }

    private static void manageSponsors(Tournament tournament) {
        boolean check = true;
        do {
            System.out.println("1. Dodaj sponsora");
            System.out.println("2. Dodaj sponsora z listy");
            System.out.println("3. Usun sponsora");
            System.out.println("4. Pokaz wszystkich sponsorow");
            System.out.println("5. Powrot");
            Scanner keyboard = new Scanner(System.in);
            int choice_2;
            int choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                    //dodaj sponsora
                    System.out.println("Podaj imie sponsora: ");
                    String sponsorName = keyboard.next();
                    System.out.println("Podaj nazwisko sponsora: ");
                    String sponsorLastname = keyboard.next();
                    System.out.println("Podaj poczatkowa wartosc dotacji sponsora: ");

                    double initialDonation = keyboard.nextDouble();

                    Donator donatorForMoment = new Donator(sponsorName, sponsorLastname, initialDonation);
                    tournament.addDonator(donatorForMoment);
                    beach.addDonatorToList(donatorForMoment);
                    break;
                case 2:
                    beach.showAllDonatorsFromBeach();
                    System.out.println("Wybierz swoj wybor<0:" + beach.getAmountOfDonators() + "> :");
                    choice_2 = getChoice(0, beach.getAmountOfDonators());
                    if (tournament.getDonators().indexOf(tournament.getDonators().get(choice_2)) == -1)
                        tournament.addDonator(beach.getDonator(choice_2));
                    break;
                case 3:
                    //usuń sponsora
                    tournament.showDonators();
                    System.out.println("Wybierz swoj wybor<0:" + tournament.getAmountOfDonators() + "> :");
                    choice_2 = getChoice(0, tournament.getAmountOfDonators());
                    tournament.removeDonator(choice_2);
                    break;
                case 4:
                    tournament.showDonators();
                case 5:
                    check = false;
                    break;
                default:
                    break;
            }
        } while (check);
    }

    private static void goToPlayoffs(Tournament tournament) {
        Scanner keyboard = new Scanner(System.in);
        boolean check = true;
        int choice;
        int levelOfGaming = 0;
        while (check) {
            System.out.println("---------------Rozgrywki---------------");
            System.out.println("1. Stworz mecze/polfinaly/finaly");
            System.out.println("2. Pokaz rezultaty meczy");
            System.out.println("3. Ustal wyniki meczy");
            System.out.println("4. Powrot");
            choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                    if (levelOfGaming == 0) {
                        if (tournament.getTeams().size() >= 4) {
                            tournament.matchesOfRoundRobin();
                            ++levelOfGaming;
                        } else System.out.println("\nProsze dodac przynajmniej 4 druzyny przed rozpoczeciem\n");

                    } else if (levelOfGaming == 1 && tournament.areAllMatchesPlayedInRoundRobin()) {
                        tournament.roundRobin();
                        tournament.matchesOfSemiFinals();
                        ++levelOfGaming;
                    } else if (levelOfGaming == 2 && tournament.areAllMatchesPlayedInSemiFinals()) {
                        tournament.semiFinal();
                        tournament.matchOfFinal();
                        ++levelOfGaming;
                    } else if (levelOfGaming == 3 && tournament.areAllMatchesPlayedInFinals()) {
                        tournament.finalGame();
                        System.out.println("Zwyciezyl:" + tournament.getWinner().getTeamName());
                        ++levelOfGaming;
                    } else if (levelOfGaming == 4)
                        System.out.println("Zwyciezyl:" + tournament.getWinner().getTeamName());
                    break;
                case 2:
                    //pokaż wyniki meczów
                    if (levelOfGaming == 4)
                        tournament.showFinalScores();
                    else if (levelOfGaming != 0)
                        tournament.showAllMatchesIn_RoundRobin_SemiFinals_Finals(levelOfGaming - 1);
                    break;
                case 3:
                    //ustaw wynik meczu
                    if (levelOfGaming != 0) {
                        int wybor;
                        tournament.showAllMatchesIn_RoundRobin_SemiFinals_Finals(levelOfGaming - 1);
                        System.out.println("Podaj swoj wybor <0:" + tournament.countingAmountOfMatchesInRoundRobin(levelOfGaming - 1) + ">:");
                        wybor = getChoice(0, tournament.countingAmountOfMatchesInRoundRobin(levelOfGaming - 1));
                        switch (levelOfGaming - 1) {
                            case 0:
                                tournament.getMatchRoundRobin(wybor).setResult();
                                break;
                            case 1:
                                tournament.getMatchSemiFinals(wybor).setResult();
                                break;
                            case 2:
                                tournament.getMatchFinal().setResult();
                                break;
                        }
                    }
                    break;
                case 4:
                    check = false;
                    break;
                default:
                    break;
            }
        }
    }

    private static int getChoice(int d_gran, int g_gran) {
        int choice_within_method;
        Scanner in = new Scanner(System.in);
        do {
            choice_within_method = in.nextInt();
            if (choice_within_method < d_gran || choice_within_method > g_gran)
                System.out.println("Wpisz poprawną wartość");
        } while (choice_within_method < d_gran || choice_within_method > g_gran);
        return choice_within_method;
    }

    private static void menuForBeach() {
        Scanner klawiatura = new Scanner(System.in);
        boolean check = true;
        int choice2;
        while (check) {
            System.out.println("---------------Plaza_ustawienia---------------");
            System.out.println("1. Zarzadzaj druzynami");
            System.out.println("2. Zarzadzaj sedziow");
            System.out.println("3. Zarzadzaj sponsorow");
            System.out.println("4. Powrot");
            choice2 = klawiatura.nextInt();
            switch (choice2) {
                case 1:
                    manageTeamsForBeach();
                    break;
                case 2:
                    manageRefereesForBeach();
                    break;
                case 3:
                    manageSponsorsForBeach();
                    break;
                case 4:

                    check = false;

                    break;
                default:
                    break;
            }
        }
    }

    private static void manageTeamsForBeach() {
        boolean check = true;
        do {
            System.out.println("1. Dodaj druzyne");
            System.out.println("2. Usun druzyne");
            System.out.println("3. Pokaz wszystkie druzyny");
            System.out.println("4. Powroc");
            Scanner keyboard = new Scanner(System.in);
            int typeOfTournament;
            int choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                    //dodaj drużynę
                    System.out.println("Wpisz nazwe druzyny: ");
                    String teamName = keyboard.next();
                    System.out.println("W jakie konkurencji gra dana druzyna? Siatkowka(0), dwa ognie(1) or przeciaganie liny(2)");
                    typeOfTournament = getChoice(0, 2);
                    switch (typeOfTournament) {
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
                    break;
                case 2:
                    //usuń drużynę
                    System.out.println("Jaki typ druzyny chcesz usunac? Siatkowke (0), dwa ognie(1), przeciaganie liny(2)?");
                    typeOfTournament = getChoice(0, 2);
                    switch (typeOfTournament) {
                        case 0:
                            beach.showAllVTeams();
                            System.out.println("Wpisz indeks druzyny:");
                            int delete0 = getChoice(0, beach.getAmountOfTeams(0));
                            beach.deleteVTeam(delete0);
                            break;
                        case 1:
                            beach.showAllDTeams();
                            System.out.println("Wpisz indeks oczekiwanej druzyny:");
                            int delete1 = getChoice(0, beach.getAmountOfTeams(1));
                            beach.deleteDTeam(delete1);
                            break;
                        case 2:
                            beach.showAllTTeams();
                            System.out.println("Wpisz indeks oczekiwanej druzyny:");
                            int delete2 = getChoice(0, beach.getAmountOfTeams(2));
                            beach.deleteTTeam(delete2);
                            break;
                    }
                    break;
                case 3:
                    beach.showAllVDTTeams();
                    break;
                case 4:
                    check = false;
                    break;
                default:
                    break;
            }
        } while (check);
    }

    private static void manageRefereesForBeach() {
        boolean check = true;
        do {
            System.out.println("1. Dodaj sedziego");
            System.out.println("2. Usun sedziego z listy");
            System.out.println("3. Pokaz sedziow na liscie");
            System.out.println("4. Powroc do poprzedniego menu");
            Scanner keyboard = new Scanner(System.in);
            int choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                    //dodaj sędziego
                    System.out.println("Podaj imie sedziego: ");
                    String refereename = keyboard.next();
                    System.out.println("Podaj nazwisko sedziego: ");
                    String refereeLastname = keyboard.next();
                    System.out.println("Czy to sedzia glowny(0) czy sedzia asystujacy(1):");
                    int typeOfReferee = keyboard.nextInt();
                    if (typeOfReferee == 0)
                        beach.addReferee(new Referee(refereename, refereeLastname));
                    else
                        beach.addAssistantReferee(new AssistantReferee(refereename, refereeLastname));
                    break;
                case 2:
                    //usun sedziow
                    System.out.println("Pokazac sedziow glownych(0) czy asystujacych(1):");
                    int choice_oftype = getChoice(0, 1);
                    if (choice_oftype == 0) {
                        beach.showAllReferees();
                        System.out.println("Podaj swoj wybor:");
                        int delete = getChoice(0, beach.getAmountOfReferee());
                        beach.deleteReferee(delete);
                    } else {
                        beach.showAllAssistantReferee();
                        System.out.println("Podaj swoj wybor:");
                        int delete = getChoice(0, beach.getAmountOfAssistantReferee());
                        beach.deleteAssistantReferee(delete);
                    }
                    break;
                case 3:
                    //pokaż wszystkich sędziów
                    beach.showReferees_MainAndAssistant();
                case 4:
                    check = false;
                    break;
                default:
                    break;
            }
        } while (check);
    }


    private static void manageSponsorsForBeach() {
        boolean check = true;
        do {
            System.out.println("1. Dodaj sponsora");
            System.out.println("2. Usun sponsora z listy");
            System.out.println("3. Pokaz wszystkich sponsorow:");
            System.out.println("4. Powroc do poprzedniego menu");
            Scanner keyboard = new Scanner(System.in);
            int choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                    //dodaj sponsora
                    System.out.println("Wpisz imie sponsora: ");
                    String sponsorName = keyboard.next();
                    System.out.println("Wpisz nazwisko sponsora: ");
                    String sponsorLastname = keyboard.next();
                    System.out.println("Wpisz poczatkowa wartosc dotacji: ");
                    int initialDonation = keyboard.nextInt();
                    beach.addDonatorToList(new Donator(sponsorName, sponsorLastname, initialDonation));
                    break;
                case 2:
                    //usuń sponsora
                    int choice_2;
                    beach.showAllDonatorsFromBeach();
                    System.out.println("Wybierz swoj wybor<0:" + beach.getAmountOfDonators() + "> :");
                    choice_2 = getChoice(0, beach.getAmountOfDonators());
                    beach.removeDonator(choice_2);
                    break;
                case 3:
                    beach.showAllDonatorsFromBeach();
                    break;
                case 4:
                    check = false;
                    break;
                default:
                    break;
            }
        } while (check);
    }
//sprawdzanie komendy diff
}

