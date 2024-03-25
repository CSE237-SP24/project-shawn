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
			while(in.hasNext()) {
				String account = in.next();
				String password = in.next();
				in.nextLine();
				this.accountPassword.put(account, password);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.in = new Scanner(balanceData);
			while(in.hasNext()) {
				String account = in.next();
				String balance = in.next();
				in.nextLine();
				this.accountBalance.add(new BankAccount(account,Double.parseDouble(balance)));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	}
	public boolean createAccount(String account,String password) {
		if(this.accountPassword.containsKey(account)) {
			return false;
		}
		this.accountPassword.put(account, password);
		this.accountBalance.put(account,new BankAccount(account,0));
		return true;
	}
	
}
