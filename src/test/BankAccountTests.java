package test;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;

class BankAccountTests {

	@Test
	void testSimpleDeposit() {
		//1. Setup Objects
		
		BankAccount testAccount = new BankAccount();
		
		//2. Call the method being tested
		testAccount.deposit(25);
		
		//3. Use assertions to verify results
		assertEquals(25.0, testAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testNegativeDeposit() {
		//1. Setup Objects	
		BankAccount testAccount = new BankAccount();
		
		//2. Call the method being tested
		try {
			testAccount.deposit(-25);
			fail();
		} catch (IllegalArgumentException e) {
			//we expect to end up here, -25 is a bad input
			assertTrue(true);
		}
	}

	@Test
	void testNegativeWithdraw() {
		//1. Setup objects
		BankAccount testAccount = new BankAccount();

		//2. Call the method being tested
		try {
			testAccount.withdraw(-25);
			fail();
		}catch (IllegalArgumentException e) {
			//We expect to end up here as -25 is an invalid withdraw amt.
			assertTrue(true);
		}
	}

	@Test
	void testSimpleWithdraw() {
		//1. Setup objects
		BankAccount testAccount = new BankAccount();

		testAccount.deposit(25);
		
		//2. Call method being tested
		testAccount.withdraw(10);

		//3. Use assertions to verify results
		assertEquals(15, testAccount.getBalance(), 0.01);
	}

	@Test
	void testInsufficientFundWithdraw() {
		//1. Setup objects
		BankAccount testAccount = new BankAccount();
		
		testAccount.deposit(25);

		//2. Call Method being tested.
		try {
			testAccount.withdraw(50);
			fail();
		}catch(IllegalArgumentException e) {
			//We expect to end up here, as we do not have enough money
			//to withdraw.
			assertTrue(true);
		}
	}


}
