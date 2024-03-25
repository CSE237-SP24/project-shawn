package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import bankapp.BankAccount;

class BankAccountTests {

	@Test
	void testSimpleDeposit() {
		// 1. Setup Objects

		BankAccount testAccount = new BankAccount(null,0);

		// 2. Call the method being tested
		testAccount.deposit(25);

		// 3. Use assertions to verify results
		assertEquals(25.0, testAccount.getBalance(), 0.01);
	}

	@Test
	void testNegativeDeposit() {
		// 1. Setup Objects
		BankAccount testAccount = new BankAccount(null, 0);

		// 2. Call the method being tested
		try {
			testAccount.deposit(-25);
			fail();
		} catch (IllegalArgumentException e) {
			// we expect to end up here, -25 is a bad input
			assertTrue(true);
		}
	}

	@Test
	void testNegativeWithdraw() {
		// 1. Setup objects
		BankAccount testAccount = new BankAccount(null, 0);

		// 2. Call the method being tested
		try {
			testAccount.withdraw(-25);
			fail();
		} catch (IllegalArgumentException e) {
			// We expect to end up here as -25 is an invalid withdraw amt.
			assertTrue(true);
		}
	}

	@Test
	void testSimpleWithdraw() {
		// 1. Setup objects
		BankAccount testAccount = new BankAccount(null, 0);

		testAccount.deposit(25);

		// 2. Call method being tested
		testAccount.withdraw(10);

		// 3. Use assertions to verify results
		assertEquals(15, testAccount.getBalance(), 0.01);
	}

	@Test
	void testInsufficientFundWithdraw() {
		// 1. Setup objects
		BankAccount testAccount = new BankAccount(null, 0);

		testAccount.deposit(25);

		// 2. Call Method being tested.
		try {
			testAccount.withdraw(50);
			fail();
		} catch (IllegalArgumentException e) {
			// We expect to end up here, as we do not have enough money
			// to withdraw.
			assertTrue(true);
		}
	}

	@Test
	void testSimpleTransaction() {
		BankAccount testAccount = new BankAccount(null, 0);

		testAccount.deposit(100);

		BankAccount targetAccount = new BankAccount(null, 0);

		testAccount.transaction(targetAccount, 30);

		assertEquals(70, testAccount.getBalance(), 0.01);
		assertEquals(30, targetAccount.getBalance(), 0.01);

	}

	@Test
	void testNegativeTransaction() {

		BankAccount testAccount = new BankAccount(null, 0);

		testAccount.deposit(100);

		BankAccount targetAccount = new BankAccount(null, 0);

		try {
			testAccount.transaction(targetAccount, -30);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid negative transaction", e.getMessage());
		}
	}

	@Test
	void testInsufficientTransaction() {

		BankAccount testAccount = new BankAccount(null, 0);

		testAccount.deposit(100);

		BankAccount targetAccount = new BankAccount(null, 0);

		try {
			testAccount.transaction(targetAccount, 200);
		} catch (IllegalArgumentException e) {
			assertEquals("Insufficient balance", e.getMessage());
		}
	}

	@Test
	void testInvalidTargetTransaction() {

		BankAccount testAccount = new BankAccount(null, 0);

		testAccount.deposit(100);

		BankAccount targetAccount = null;

		try {
			testAccount.transaction(targetAccount, 30);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid target account", e.getMessage());
		}
	}


}
