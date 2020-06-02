package def;

public class Donator{
	private final String name;
	private final String lastName;
	private double allMoneyDonated=0.0;
	
	public Donator(String name, String lastName, int initialDonation) {
		this.name = name;
		this.lastName = lastName;
		allMoneyDonated += initialDonation;
	}
	
	public double getMoney() {
		return allMoneyDonated;
	}
	
	public void addMoney(double sum) {
		allMoneyDonated += sum;
	}
}
