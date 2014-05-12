package tests.users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import users.Account;

/**
 * @author  Dino Opijac
 * @since   12/05/14
 */
public class AccountTest {
    private Account account;

    @Before
    public void setUp() throws Exception {
        this.account = new Account(100);
    }

    @After
    public void tearDown() throws Exception {
        this.account = null;
    }

    @Test
    public void testDeposit() throws Exception {
        account.deposit(100);
        assertEquals("Account balance should be 200", 200, account.getBalance(), 2);
    }

    @Test
    public void testDepositNegative() throws Exception {
        account.deposit(-50.0);

        assertEquals("It should not be possible to deposit negative values", 100.0, account.getBalance(), 2);
        System.out.println(account.getBalance());
    }

    @Test
    public void testWithdraw() throws Exception {
        account.withdraw(150);
        assertEquals("Withdrawing a larger amount than available, should return a negative value", -50.0, account.getBalance(), 2);
    }

    @Test
    public void testWithdrawNegative() throws Exception {
        account.withdraw(-50);

        assertEquals("Withdrawing a negative value should be evaluated as positive", 50, account.getBalance(), 2);
    }

    @Test
    public void testIsWithdrawable() throws Exception {
        assertFalse("Withdrawing a larger amount than available should return false", account.isWithdrawable(account.getBalance() + 50));
    }
}
