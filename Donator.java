package def;

public class Donator {
    private String name;
    private String lastName;
    private double allMoneyDonated;

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
