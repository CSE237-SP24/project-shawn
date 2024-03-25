package bankapp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
public class BankSystem {
	HashMap<String,String> accountPassword;
	HashMap<String,BankAccount> accountBalance;
	Scanner in;
	PrintWriter out;
	File passwordData;
	File balanceData;
	BankAccount currentAccount;
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
	private boolean checkState() {
		return !(this.currentAccount == null);
	}
	public double getBalance() {
		if(!this.checkState()) {
			throw new IllegalArgumentException("you are not login");
		}
		return this.currentAccount.getBalance();
	}
	public void deposit(double num) {
		if(!this.checkState()) {
			throw new IllegalArgumentException("you are not login");
		}
		this.currentAccount.deposit(num);
	}
	public void withdraw(double num) {
		if(!this.checkState()) {
			throw new IllegalArgumentException("you are not login");
		}
		this.currentAccount.withdraw(num);
	}
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
	public void logout() {
	    this.currentAccount = null;
	    try {
	        writePasswordsToFile();
	        writeBalancesToFile();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace(); // Again, consider a better error handling strategy
	    }
	}
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

	private void writePasswordsToFile() throws FileNotFoundException {
	    PrintWriter out = new PrintWriter(passwordData);
	    for (Map.Entry<String, String> entry : accountPassword.entrySet()) {
	        out.println(entry.getKey() + " " + entry.getValue());
	    }
	    out.close();
	}

	private void writeBalancesToFile() throws FileNotFoundException {
	    PrintWriter out = new PrintWriter(balanceData);
	    for (Map.Entry<String, BankAccount> entry : accountBalance.entrySet()) {
	        out.println(entry.getKey() + " " + entry.getValue().getBalance());
	    }
	    out.close();
	}

	
}
