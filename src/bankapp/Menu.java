package bankapp;

import java.util.Scanner;

public class Menu {

	private Scanner in;
	private BankAccount account;
	
	//not tested
	public static void main(String[] args) {
		Menu mainMenu = new Menu();
		mainMenu.displayingOptions();
		double amount = mainMenu.getValidUserInput();
		mainMenu.processingUserSelection(amount);
	}
	
	//Constructor
	public Menu() {
		this.in = new Scanner(System.in);
		this.account = new BankAccount();
	}
	
	//Code that just displays stuff - no tests needed
	public void displayingOptions() {
		System.out.println("Select an option number: ");
		System.out.println("1. Deposit");
		System.out.println("2. Withdraw");
	    System.out.println("3. View Balance");
	}
	
	public void handleUserSelection(int option) {
	    
	}
	
	//Code that gets user input
	//No tests needed...for now (probably discuss in future class)
	public double getValidUserInput() {
		double amount = in.nextDouble();
		while(amount < 0) {
			System.out.println("Invalid value!");
			System.out.println("How much money do you want to deposit?");
			amount = in.nextDouble();
		}
		return amount;
	}
	
	//Does work - needs tests
	public void processingUserSelection(int option) {
		switch (option) {
		//Deposit
        case 1:
            double depositAmount = getValidUserInput();
            account.deposit(depositAmount);
            System.out.println("Deposit successful. Your balance is now: " + account.getBalance());
            break;
        //Withdraw
        case 2:
            double withdrawAmount = getValidUserInput();
            try {
                account.withdraw(withdrawAmount);
                System.out.println("Withdrawal successful. Your balance is now: " + account.getBalance());
            } catch (InsufficientFundsException e) {
                System.out.println("Insufficient funds. Withdrawal failed.");
            }
            break;
        //View Balance
        case 3:
            System.out.println("Your current balance is: " + account.getBalance());
            break;
        default:
            System.out.println("Invalid option. Please try again.");
		}
    }
		
	public BankAccount getAccount() {
		return account;
	}
}
