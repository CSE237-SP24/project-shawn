package bankapp;

public class BankAccount {
	
	private double balance;
	
	//Constructors - not tested
	public BankAccount() {
		this.balance = 0;
	}
	
	//public method doing some work - lots of tests
	public void deposit(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		this.balance += amount;
	}

	public void withdraw(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}else if(amount > balance) {
			System.out.println("Unsufficient balance. You only have "+balance+" and are trying to withdraw "+amount);
			throw new IllegalArgumentException("Insufficient Funds to withdraw");
		}
		this.balance -= amount;
	}
	
	//getters and setters - not tested
	public double getBalance() {
		return this.balance;
	}
}
