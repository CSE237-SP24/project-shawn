package bankapp;

import java.util.Scanner;

public class Menu {

	private Scanner in;
	private BankAccount account;
	private int userOption;
	
	private final int DEPOSIT_OPTION = 1;
	private final int WITHDRAW_OPTION = 2; 
	private final int VIEW_BALANCE_OPTION = 3;
			
	
	public static void main(String[] args) {
		Menu mainMenu = new Menu();
		mainMenu.runBankAccount();
	}
	
	//Constructor
	public Menu() {
		this.in = new Scanner(System.in);
		this.account = new BankAccount();
		this.userOption = 0;
	}
	
	
	//Display menu options to user 
	public void displayingOptions() {
		System.out.println("Select an option number: ");
		System.out.println("1. Deposit");
		System.out.println("2. Withdraw");
	    System.out.println("3. View Balance");
	}
	
	//Run bank account program
	public void runBankAccount(){
		
		boolean continueProgram = true;
		
		while (continueProgram) {
			displayingOptions();
			getValidUserOption();
			
			processingUserSelection();
			
			System.out.println("Do you want to perform another transaction? (Y/N)");
			String continueInput = in.next();
			
			//Exiting program
			if (!continueInput.equalsIgnoreCase("Y")) {
				continueProgram = false; 
			}
		}
	}
	
	//Get valid user option (either 1, 2, or 3)
	public void getValidUserOption() {
		int option;
		
	    while (true) {
	        if (in.hasNextInt()) {
	        	
	            option = in.nextInt();
	            if (option >= DEPOSIT_OPTION && option <= VIEW_BALANCE_OPTION) {
	                break;
	            } 
	            else {
	                System.out.println("Invalid option! Please enter a number between 1 and 3.");
	            }
	        } 
	        else {
	            in.next(); // Consume invalid input
	            System.out.println("Invalid option! Please enter a number between 1 and 3.");
	        }
	    }
	    setOption(option);
	}
	
	//Test
	//Process bank account based on chosen option
	public void processingUserSelection() {
		switch (userOption) {
			//Deposit
	        case DEPOSIT_OPTION:
	            processingDeposit();
	            System.out.println("Deposit successful. Your balance is now: " + account.getBalance());
	            break;
	            
	        //Withdraw
	        case WITHDRAW_OPTION:
	        	
	        	processingWithdraw();
//	            try {
//	            	processingWithdraw();
//	                System.out.println("Withdrawal successful. Your balance is now: " + account.getBalance());
//	            } catch (IllegalArgumentException e) {
//	                System.out.println("Insufficient funds. Withdrawal failed.");
//	            }
	            break;
	            
	        //View Balance
	        case VIEW_BALANCE_OPTION:
	            System.out.println("Your current balance is: " + account.getBalance());
	            break;
	            
	        default:
	            System.out.println("Invalid option. Please try again.");
		}
    }
	
	//Test
	//Process deposit operation
	public void processingDeposit(){
		System.out.println("How much money do you want to deposit?");
		double amount = in.nextDouble();
		
		//If amount is negative, ask user again
		while(amount < 0) {
			System.out.println("Invalid value!");
			System.out.println("How much money do you want to deposit?");
			amount = in.nextDouble();
		}
		
		account.deposit(amount);
	}
	
	//Test
	//Process withdraw operation
	public void processingWithdraw(){
		System.out.println("How much money do you want to withdraw?");
		double amount = in.nextDouble();
		
		//If amount is negative, ask user again
		while(amount < 0) {
			System.out.println("Invalid value!");
			System.out.println("How much money do you want to withdraw?");
			amount = in.nextDouble();
		}
		
		try {
			account.withdraw(amount);
            System.out.println("Withdrawal successful. Your balance is now: " + account.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
	}
	
		
	public BankAccount getAccount() {
		return account;
	}
	
	public int getOption() {
		return userOption;
	}
	
	public void setOption(int option) {
		userOption = option;
	}
}
