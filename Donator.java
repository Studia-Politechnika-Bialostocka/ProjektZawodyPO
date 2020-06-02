package def;

public class Donator{
	private final String name;
	private final String lastName;
	private double allMoneyDonated=0.0;
	
	public Donator(String name, String lastName, double initialDonation) {
		this.name = name;
		this.lastName = lastName;
		allMoneyDonated += initialDonation;
	}
	
	public double getMoney() {
		return allMoneyDonated;
	}

	public String toString(){
		return name +" "+ lastName +" "+ allMoneyDonated;
	}
}
