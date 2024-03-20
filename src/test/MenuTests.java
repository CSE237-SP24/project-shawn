package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.Menu;

class MenuTests {
	
	private final int DEPOSIT_OPTION = 1;
	private final int WITHDRAW_OPTION = 2; 
	private final int VIEW_BALANCE_OPTION = 3;
	
	@Test
    public void testValidInput() {
				
        //Set up objects
        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Menu menu = new Menu();
        
        //Call method that is being tested
        menu.getValidUserOption();
        
        //Use assertions to verify results
        assertEquals(WITHDRAW_OPTION, menu.getOption());
    }
	
	@Test
    public void testInvalidInput() {
        
        //Set up objects
        String input = "abc\n2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Menu menu = new Menu();
        
        //Call method that is being tested
        menu.getValidUserOption();
        
        //Use assertions to verify results
        assertEquals(WITHDRAW_OPTION, menu.getOption()); 
    }
	
	@Test
	public void testOutOfRangeInput() {
		
        //Set up objects
		String input = "4\n2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Menu menu = new Menu();
        
        //Call method that is being tested
        menu.getValidUserOption();
        
        //Use assertions to verify results
        assertEquals(WITHDRAW_OPTION, menu.getOption()); 
	}
	
	@Test
	public void testProcessingUserSelection_DepositOption() {
		//Set up objects
        String input = "1\n10\n"; // Simulate deposit of 10 
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        Menu menu = new Menu();
        menu.getValidUserOption();
       
        
        //Call method that is being tested
        menu.processingUserSelection();
        
        //Use assertions to verify results
        BankAccount account = menu.getAccount();
        
        assertEquals(10, account.getBalance(), 0.01); 
       
    }
	
	@Test
	public void testProcessingUserSelection_WithdrawOption() {
		//Set up objects
        String input = "2\n10\n"; // Simulate withdraw of 10 
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        Menu menu = new Menu();
        menu.getAccount().deposit(10); //Initially deposit 10 
        
        menu.getValidUserOption();
        
        //Call method that is being tested
        menu.processingUserSelection();
        
        //Use assertions to verify results
        BankAccount account = menu.getAccount();
        
        assertEquals(0, account.getBalance(), 0.01); 
       
    }
	
	@Test
	public void testProcessingUserSelection_WithdrawOptionInvalid() {
		//Set up objects
        String input = "2\n20\n"; // Simulate withdraw of 20
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        Menu menu = new Menu();
        menu.getAccount().deposit(10); //Initially deposit 10 
        
        menu.getValidUserOption();
        
        //Call method that is being tested
        menu.processingUserSelection();
        
        //Use assertions to verify results
        BankAccount account = menu.getAccount();
        
        assertEquals(10, account.getBalance(), 0.01); //Nothing should change since invalid withdraw
        
    }
	
	@Test
	public void testProcessingUserSelection_ViewBalanceOption() {
		//Set up objects
        String input = "3\n20\n"; // Simulate with balance
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        Menu menu = new Menu();
        menu.getAccount().deposit(10); //Initially deposit 10 
        
        menu.getValidUserOption();
        
        //Call method that is being tested
        menu.processingUserSelection();
        
        //Use assertions to verify results
        BankAccount account = menu.getAccount();
        
        assertEquals(10, account.getBalance(), 0.01); //Nothing should change
    }
	
	@Test
	public void testProcessingDeposit() {
		//Set up objects
        String input = "20\n"; // Simulate with deposit of 20
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        Menu menu = new Menu();
        
        //Call method that is being tested
        menu.processingDeposit();
        
        //Use assertions to verify results
        BankAccount account = menu.getAccount();
        
        assertEquals(20, account.getBalance(), 0.01); 
    }
	
	@Test
	public void testProcessingWithdraw() {
		//Set up objects
        String input = "20\n"; // Simulate with withdraw of 20
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        Menu menu = new Menu();
        menu.getAccount().deposit(20); //Initially deposit 20
        
        //Call method that is being tested
        menu.processingWithdraw();
        
        //Use assertions to verify results
        BankAccount account = menu.getAccount();
        
        assertEquals(0, account.getBalance(), 0.01); 
    }
	
	@Test
	public void testProcessingWithdraw_Invalid() {
		//Set up objects
        String input = "20\n"; // Simulate with withdraw of 20
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        Menu menu = new Menu();
        menu.getAccount().deposit(10); //Initially deposit 10
        
        //Call method that is being tested
        menu.processingWithdraw();
        
        //Use assertions to verify results
        BankAccount account = menu.getAccount();
        
        assertEquals(10, account.getBalance(), 0.01); //Nothing should change since invalid withdraw
    }
	
	
	
	
	
	
	
	
	
	
	
	
}
