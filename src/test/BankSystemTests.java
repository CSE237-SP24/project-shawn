package test;

import bankapp.BankSystem;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;

public class BankSystemTests {
    private final String pathToPassword = "data/password.txt";
    private final String pathToBalanceData = "data/balance.txt";
    private BankSystem bankSystem;
    private Map<String, String> readPasswordsFromFile() throws FileNotFoundException {
        Map<String, String> passwords = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(pathToPassword))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(" ", 2);
                if (parts.length == 2) {
                    passwords.put(parts[0], parts[1]);
                }
            }
        }
        return passwords;
    }

    private Map<String, Double> readBalancesFromFile() throws FileNotFoundException {
        Map<String, Double> balances = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(pathToBalanceData))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(" ", 2);
                if (parts.length == 2) {
                    balances.put(parts[0], Double.valueOf(parts[1]));
                }
            }
        }
        return balances;
    }


    @BeforeEach
    void setUp() throws FileNotFoundException {
    	//clean the data set for testing
        PrintWriter out = new PrintWriter(pathToPassword);
        out.close();
        out = new PrintWriter(pathToBalanceData);
        out.close();
        // Initialize BankSystem for each test
        bankSystem = new BankSystem(pathToPassword, pathToBalanceData);
    }
    
    @Test
    void testAccountCreationAndLogin() throws FileNotFoundException {
        // Test account creation
        boolean creationResult = bankSystem.createAccount("newUser", "newPass");
        Assertions.assertTrue(creationResult, "Account creation should be successful.");

        // Test login with the new account
        boolean loginResult = bankSystem.login("newUser", "newPass");
        Assertions.assertTrue(loginResult, "Login should be successful for the new account.");
        
        // Verify balance is initialized to 0 for new account
        double balance = bankSystem.getBalance();
        Assertions.assertEquals(0.0, balance, "New account should have a balance of 0.");

        // Verify the files are updated
        Map<String, String> passwords = readPasswordsFromFile();
        Map<String, Double> balances = readBalancesFromFile();

        Assertions.assertTrue(passwords.containsKey("newUser") && passwords.get("newUser").equals("newPass"),
            "Password file should be updated with new account.");
        Assertions.assertTrue(balances.containsKey("newUser") && balances.get("newUser") == 0.0,
            "Balance file should be updated with new account balance.");
    }

    
    @Test
    void testLoginFailureForNonexistentAccount() {
        boolean result = bankSystem.login("nonexistentUser", "somePassword");
        Assertions.assertFalse(result, "Login should fail for a nonexistent account.");
    }
    
    @Test
    void testLoginFailureForWrongPassword() {
        // Ensure account creation for test
        bankSystem.createAccount("testUser", "correctPassword");

        // Attempt login with incorrect password
        boolean result = bankSystem.login("testUser", "incorrectPassword");
        Assertions.assertFalse(result, "Login should fail with incorrect password.");
    }
    
    @Test
    void testBalanceRetrievalWithoutLogin() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> bankSystem.getBalance(), "Attempting to retrieve balance without login should throw an exception.");
    }
    
    @Test
    void testDuplicateAccountCreation() {
        // Attempt to create an account
        boolean firstCreationResult = bankSystem.createAccount("existingUser", "password123");
        Assertions.assertTrue(firstCreationResult, "First account creation should be successful.");

        // Attempt to create a duplicate account with the same account name
        boolean secondCreationResult = bankSystem.createAccount("existingUser", "password123");
        Assertions.assertFalse(secondCreationResult, "Duplicate account creation should fail.");
    }
    @Test
    void testAccountDataPersistenceAfterCreation() throws FileNotFoundException {
        bankSystem.createAccount("persistentUser", "securePass");
        bankSystem.logout(); // Ensure data is written to files
        
        // Now, read back the files to check if the account data was persisted
        Scanner passwordScanner = new Scanner(new File(pathToPassword));
        boolean foundPasswordEntry = false;
        while (passwordScanner.hasNextLine()) {
            String line = passwordScanner.nextLine();
            if (line.contains("persistentUser securePass")) {
                foundPasswordEntry = true;
                break;
            }
        }
        passwordScanner.close();
        
        Scanner balanceScanner = new Scanner(new File(pathToBalanceData));
        boolean foundBalanceEntry = false;
        while (balanceScanner.hasNextLine()) {
            String line = balanceScanner.nextLine();
            if (line.startsWith("persistentUser ")) {
                foundBalanceEntry = true;
                break;
            }
        }
        balanceScanner.close();
        
        Assertions.assertTrue(foundPasswordEntry, "The password file should contain the new account.");
        Assertions.assertTrue(foundBalanceEntry, "The balance file should contain the new account with initial balance.");
    }

    @Test
    void testBalanceModificationAndPersistenceAfterLogout() throws FileNotFoundException {
        // Step 1: Create a new account or use an existing one and log in
        String accountName = "balanceTestUser";
        bankSystem.createAccount(accountName, "testPass");
        bankSystem.login(accountName, "testPass");
        
        // Step 2: Modify the balance
        double depositAmount = 50.0; // This could be any testable amount
        bankSystem.deposit(depositAmount);
        
        // Step 3: Logout to trigger the data persistence
        bankSystem.logout();
        
        // Step 4: Check the balance file for the updated balance
        Scanner balanceScanner = new Scanner(new File(pathToBalanceData));
        boolean foundBalanceEntry = false;
        double readBalance = 0.0;
        while (balanceScanner.hasNextLine()) {
            String line = balanceScanner.nextLine();
            if (line.startsWith(accountName + " ")) {
                foundBalanceEntry = true;
                readBalance = Double.parseDouble(line.split(" ")[1]); // Assuming the format "accountName balance"
                break;
            }
        }
        balanceScanner.close();
        
        Assertions.assertTrue(foundBalanceEntry, "The balance file should contain the updated account.");
        Assertions.assertEquals(depositAmount, readBalance, "The balance file should reflect the updated balance.");
    }

    @AfterEach
    void tearDown() {
        // Cleanup test environment
        new File(pathToPassword).delete();
        new File(pathToBalanceData).delete();
    }
    
}
