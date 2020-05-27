package def;

public class Referee {
	protected String name;
	protected String lastName;
	protected int numOfMatches = 0;
	
	public Referee(String name, String lastName) {
		this.name = name;
		this.lastName = lastName;
	}

	public String getFullName() {
		return name + " " + lastName;
	}

	public void documentMatch() {
		numOfMatches++;
	}

	public String toString() {
		return "Name: " + getFullName() + "\nMatches reffed: " + numOfMatches;
	}
}
