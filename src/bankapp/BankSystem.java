package bankapp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
public class BankSystem {
	
	//data
	HashMap<String,String> accountPassword;
	HashMap<String,BankAccount> accountBalance;
	
	//io process
	Scanner in;
	PrintWriter out;
	File passwordData;
	File balanceData;
	
	// current login account
	BankAccount currentAccount;
	
	 /**
     * Constructor to initialize the banking system with necessary data files.
     *
     * @param pathToPassword The file path for the account passwords.
     * @param pathToBalanceData The file path for the account balances.
     */
	public BankSystem(String pathToPassword, String pathToBalanceData) {
		this.currentAccount = null;
		this.passwordData = new File(pathToPassword);
		this.balanceData = new File(pathToBalanceData);
		this.accountBalance = new HashMap<>();
		this.accountPassword = new HashMap<>();
		try {
			this.in = new Scanner(passwordData);
			while(in.hasNextLine()) {
				String line = in.nextLine();
				String[] parts = line.split(" ", 2);
				if (parts.length >= 2) { // Make sure there are two parts
                    String account = parts[0];
                    String password = parts[1];
    				this.accountPassword.put(account, password);
                }
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.in = new Scanner(balanceData);
			while(in.hasNextLine()) {
				String line = in.nextLine();
				String[] parts = line.split(" ", 2);
				if (parts.length >= 2) { // Make sure there are two parts
                    String account = parts[0];
                    double balance = Double.parseDouble(parts[1]);
    				this.accountBalance.put(account, new BankAccount(account,balance));
                }
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
     * Checks if a user is currently logged into the system.
     *
     * @return true if a user is logged in; false otherwise.
     */
	private boolean checkState() {
		return !(this.currentAccount == null);
	}
	  /**
     * Retrieves the balance of the current account. Throws an exception if no user is logged in.
     *
     * @return The balance of the currently logged-in account.
     * @throws IllegalArgumentException If no user is currently logged in.
     */
	public double getBalance() {
		if(!this.checkState()) {
			throw new IllegalArgumentException("you are not login");
		}
		return this.currentAccount.getBalance();
	}
	 /**
     * Deposits a specified amount into the current account. Throws an exception if no user is logged in.
     *
     * @param num The amount to deposit.
     * @throws IllegalArgumentException If no user is currently logged in.
     */
	public void deposit(double num) {
		if(!this.checkState()) {
			throw new IllegalArgumentException("you are not login");
		}
		this.currentAccount.deposit(num);
	}
	 /**
     * Withdraws a specified amount from the current account. Throws an exception if no user is logged in.
     *
     * @param num The amount to withdraw.
     * @throws IllegalArgumentException If no user is currently logged in.
     */
	public void withdraw(double num) {
		if(!this.checkState()) {
			throw new IllegalArgumentException("you are not login");
		}
		this.currentAccount.withdraw(num);
	}
	 /**
     * Attempts to log in a user with the specified account and password.
     *
     * @param account The account name.
     * @param password The password for the account.
     * @return true if login is successful; false otherwise.
     */
	public boolean login(String account, String password) {
		if(!this.accountPassword.containsKey(account)) {
			return false;
		}
		if(this.accountPassword.get(account).equals(password)) {
			this.currentAccount = accountBalance.get(account);
		return true;
		}
		return false;
	}

    /**
     * Logs out the current user and saves changes to the files.
     */
	public void logout() {
	    this.currentAccount = null;
	    try {
	        writePasswordsToFile();
	        writeBalancesToFile();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace(); // Again, consider a better error handling strategy
	    }
	}
	/**
     * Creates a new account with the specified name and password.
     *
     * @param account The name of the new account.
     * @param password The password for the new account.
     * @return true if the account was successfully created; false if the account already exists.
     */
	public boolean createAccount(String account, String password) {
	    if (accountPassword.containsKey(account)) {
	        return false;
	    }
	    accountPassword.put(account, password);
	    accountBalance.put(account, new BankAccount(account, 0));
	    try {
	        writePasswordsToFile();
	        writeBalancesToFile();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace(); // Consider a better error handling strategy for your context
	    }
	    this.currentAccount = accountBalance.get(account);
	    return true;
	}
	// store data
	private void writePasswordsToFile() throws FileNotFoundException {
	    PrintWriter out = new PrintWriter(passwordData);
	    for (Map.Entry<String, String> entry : accountPassword.entrySet()) {
	        out.println(entry.getKey() + " " + entry.getValue());
	    }
	    out.close();
	}
    // store data
	private void writeBalancesToFile() throws FileNotFoundException {
	    PrintWriter out = new PrintWriter(balanceData);
	    for (Map.Entry<String, BankAccount> entry : accountBalance.entrySet()) {
	        out.println(entry.getKey() + " " + entry.getValue().getBalance());
	    }
	    out.close();
	}

	
}
