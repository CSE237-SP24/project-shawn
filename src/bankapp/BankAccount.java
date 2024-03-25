package bankapp;

public class BankAccount {

	private double balance;
	private String accountId;

	// Constructors - not tested
	public BankAccount(String accountId,double balance) {
		this.balance = balance;
		this.accountId = accountId;
	}

	// public method doing some work - lots of tests
	public void deposit(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		this.balance += amount;
	}

	public void withdraw(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		} else if (amount > balance) {
			System.out.println("Insufficient balance. You only have " + balance
					+ " and are trying to withdraw " + amount);
			throw new IllegalArgumentException("Insufficient Funds to withdraw");
		}
		this.balance -= amount;
	}

	// getters and setters - not tested
	public double getBalance() {
		return this.balance;
	}

	// make a transaction
	public void transaction(BankAccount targetAccount, double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Invalid negative transaction");
		} else if (this.balance < amount) {
			System.out.println("Insufficient balance. You only have " + balance
					+ " and are trying to transact " + amount);
			throw new IllegalArgumentException("Insufficient balance");
		} else if (targetAccount == null) {
			throw new IllegalArgumentException("Invalid target account");
		}
		this.balance -= amount;
		targetAccount.deposit(amount);

	}
}
