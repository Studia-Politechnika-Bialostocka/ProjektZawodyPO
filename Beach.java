package def;

import java.io.*;
import java.util.LinkedList;
import java.io.Reader;


public class Beach {
    private LinkedList<Volleyball> vTeams = new LinkedList<>();
    private LinkedList<Dodgeball> dTeams = new LinkedList<>();
    private LinkedList<Tug_of_War> tTeams = new LinkedList<>();
    private LinkedList<Referee> listOfReferee = new LinkedList<>();
    private LinkedList<AssistantReferee> listOfAssistantReferee = new LinkedList<>();
    private LinkedList<Tournament> listOfTournaments = new LinkedList<>();
    private LinkedList<Donator> listOfDonators = new LinkedList<>();

    public LinkedList<Tournament> getTournaments() {
        return listOfTournaments;
    }

    public void addDonatorToList(Donator donatorInParameter) {
        listOfDonators.add(donatorInParameter);
    }

    public void showAllDonatorsFromBeach() {
        int i = 0;
        for (Donator exampleDonator : listOfDonators) {
            System.out.println(i + " :" + exampleDonator);
            ++i;
        }
    }

    public int getAmountOfDonators() {
        return (listOfDonators.size()-1);
    }

    public Donator getDonator(int index) {
        return listOfDonators.get(index);
    }

    public void showAllTournaments() {
        for (int i = 0; i < listOfTournaments.size(); i++) {
            System.out.println((i) + ". " + listOfTournaments.get(i).getNameOfTournament());
            System.out.println(listOfTournaments.get(i).getAllMaches());
        }
    }

    public void removeDonator(int index) {
        listOfDonators.remove(index);
    }

    public void addTournament(Tournament t) {
        listOfTournaments.add(t);
    }

    public void newvTeamList(LinkedList<Volleyball> s) {
        vTeams = s;
    }

    public void newdTeamList(LinkedList<Dodgeball> d) { dTeams = d;  }

    public void newtTeamList(LinkedList<Tug_of_War> t) { tTeams = t;}

    public void newReferee(LinkedList<Referee> r) {
        listOfReferee = r;
    }

    public void newAssistanceReferee(LinkedList<AssistantReferee> ar) {
        listOfAssistantReferee = ar;
    }

    public LinkedList<Volleyball> getVTeams() {
        return vTeams;
    }

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

    public void showAllDTeams() {
        int i = 0;
        for (Dodgeball dteam : dTeams) {
            System.out.println(i + " :" + dteam);
            ++i;
        }
    }

    public void showAllTTeams() {
        int i = 0;
        for (Tug_of_War tteam : tTeams) {
            System.out.println(i + " :" + tteam);
            ++i;
        }
    }

    public void showAllVDTTeams() {
        int i = 0;
        for (Volleyball exampleMatch : vTeams) {
            System.out.println(i + " :" + exampleMatch);
            ++i;
        }
        for (Dodgeball exampleMatch : dTeams) {
            System.out.println(i + " :" + exampleMatch);
            ++i;
        }
        for (Tug_of_War exampleMatch : tTeams) {
            System.out.println(i + " :" + exampleMatch);
            ++i;
        }
    }

    public int getAmountOfTeams(int parameter) {
        switch (parameter) {
            case 0:
                return (vTeams.size()-1);
            case 1:
                return (dTeams.size()-1);
            case 2:
                return (tTeams.size()-1);
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


    public void showAllReferees() {
        System.out.println("Sedziowie glowni");
        int i = 0;
        for (Referee exampleReferee : listOfReferee) {
            System.out.println(i + " :" + exampleReferee);
            ++i;
        }
    }

    public void showAllAssistantReferee() {
        System.out.println("Sedziowie asystujacy");
        int i = 0;
        for (AssistantReferee exampleAssReferee : listOfAssistantReferee) {
            System.out.println(i + " :" + exampleAssReferee);
            ++i;
        }
    }

    public void showReferees_MainAndAssistant() {
        int i = 0;
        if(listOfReferee.size()!=0) {
            System.out.println("Sedziowie glowni");
            for (Referee exampleReferee : listOfReferee) {
                System.out.println(i + ":" + exampleReferee);
                ++i;
            }
        }
        else
            System.out.println("Brak sedziow glownych");
        if(listOfAssistantReferee.size()!=0) {
            System.out.println("Sedziowie asystujacy");
            for (AssistantReferee exampleAssReferee : listOfAssistantReferee) {
                System.out.println(i + ":" + exampleAssReferee);
                ++i;
            }
        }
        else
            System.out.println("Brak sedziow asystujacych");
    }

    public LinkedList<Referee> getReferee() {
        return listOfReferee;
    }

    public LinkedList<AssistantReferee> getAssistantReferee() {
        return listOfAssistantReferee;
    }

    public int getAmountOfReferee() {
        return (listOfReferee.size()-1);
    }

    public int getAmountOfAssistantReferee() {
        return (listOfAssistantReferee.size()-1);
    }

    public LinkedList<Referee> getListOfReferee() {
        return listOfReferee;
    }

    public LinkedList<AssistantReferee> getListOfAssistantReferee() {
        return listOfAssistantReferee;
    }

    private void saveToFileVolleyball(){
        try {
            FileWriter fileWriter = new FileWriter("VolleyballFile.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Volleyball volleyball : vTeams) {
                printWriter.print(volleyball.toString());
                printWriter.print("\n");
            }
            printWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void saveToFileDodgeball() {
        try {
            FileWriter fileWriter = new FileWriter("DodgeballFile.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Dodgeball dodgeball : dTeams) {
                printWriter.print(dodgeball.toString());
                printWriter.print("\n");
            }
            printWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void saveToFileTug_of_War() {
        try {
            FileWriter fileWriter = new FileWriter("Tug_of_WarFile.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Tug_of_War tug_of_war : tTeams) {
                printWriter.print(tug_of_war.toString());
                printWriter.print("\n");
            }
            printWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void saveToFileReferee(){
        try {
            FileWriter fileWriter = new FileWriter("RefereeFile.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Referee referee : listOfReferee) {
                printWriter.print(referee.toString());
                printWriter.print("\n");
            }
            printWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void saveToFileAssistantReferee() {
        try {
            FileWriter fileWriter = new FileWriter("AssistanceRefereeFile.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (AssistantReferee assistantReferee : listOfAssistantReferee) {
                printWriter.print(assistantReferee.toString());
                printWriter.print("\n");
            }
            printWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void saveToFileTournament(){
        try {
            FileWriter fileWriter = new FileWriter("TournamentFile.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Tournament tournament : listOfTournaments) {
                printWriter.print(tournament.toString());
                printWriter.print("\n");
                printWriter.print((tournament.getAllMaches()));
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
            while ((buf = in.readLine()) != null) {
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
    public void saveToFile(){
        saveToFileVolleyball();
        saveToFileDodgeball();
        saveToFileTug_of_War();
        saveToFileReferee();
        saveToFileAssistantReferee();
        saveToFileTournament();
    }

    public LinkedList<Dodgeball> importFromFileDodgeball() {
        LinkedList<Dodgeball> d = new LinkedList<>();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("DodgeballFile.txt"));
            String buf;
            while ((buf = in.readLine()) != null) {
                String[] s = buf.split(" ");
                Dodgeball v1 = new Dodgeball(s[0]);
                v1.setWins(Integer.parseInt(s[1]));
                v1.setLosses(Integer.parseInt(s[2]));
                v1.setSetsWon(Integer.parseInt(s[3]));
                d.add(v1);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return d;
    }

    public LinkedList<Tug_of_War> importFromFileTug_of_War() {
        LinkedList<Tug_of_War> t = new LinkedList<>();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("Tug_of_WarFile.txt"));
            String buf;
            while ((buf = in.readLine()) != null) {
                String[] s = buf.split(" ");
                Tug_of_War v1 = new Tug_of_War(s[0]);
                v1.setWins(Integer.parseInt(s[1]));
                v1.setLosses(Integer.parseInt(s[2]));
                v1.setSetsWon(Integer.parseInt(s[3]));
                t.add(v1);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return t;
    }

    public LinkedList<Referee> importFromFileRefeere() {
        LinkedList<Referee> r = new LinkedList<>();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("RefereeFile.txt"));
            String buf;
            while ((buf = in.readLine()) != null) {
                String[] s = buf.split(" ");
                Referee v1 = new Referee(s[0], s[1]);
                v1.numOfMatches = Integer.parseInt(s[2]);
                r.add(v1);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return r;
    }

    public LinkedList<AssistantReferee> importFromFileAssistantReferee() {
        LinkedList<AssistantReferee> ar = new LinkedList<>();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("AssistanceRefereeFile.txt"));
            String buf;
            while ((buf = in.readLine()) != null) {
                String[] s = buf.split(" ");
                AssistantReferee v1 = new AssistantReferee(s[0], s[1]);
                v1.numOfMatches = Integer.parseInt(s[2]);
                ar.add(v1);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return ar;
    }

    public void importFromFileTournament() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("TournamentFile.txt"));
            String buf;
            while ((buf = in.readLine()) != null && buf != "") {
                String[] s = buf.split(" ");
                Tournament v1 = new Tournament(s[0], Double.parseDouble(s[1]), listOfReferee, listOfAssistantReferee, Integer.parseInt(s[2]));
                v1.setNRofMatches(Integer.parseInt(s[3]));
                for (int i = 0; i < Integer.parseInt(s[3]); ++i) {
                    buf = in.readLine();
                    v1.expandAllMaches(buf+"\n");
                }
                addTournament(v1);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public String getNameOfIndexInTournament(int index){
        return listOfTournaments.get(index).getNameOfTournament();
    }
    public boolean ifExistInList(String dowolnaNazwa){
        for(int i = 0; i < listOfTournaments.size(); ++i){
            if(dowolnaNazwa.equals(getNameOfIndexInTournament(i))) return true;
        }
        return false;
    }
}
