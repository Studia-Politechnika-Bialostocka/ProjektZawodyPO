package def;


public class Referee {
    protected String name;
    protected String lastName;
    protected int numOfMatches = 0;

    public Referee(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }


    public void documentMatch() {
        numOfMatches++;
    }

    public String toString() {
        return name + " " + lastName + " " + numOfMatches;
    }
}
