package shared.users;

import utilities.sql.annotations.PrimaryKey;

/**
 * This class models operations that can be
 * performed on a account.
 *
 * @author  Dino Opijac
 * @since   11/05/14
 */
public class Account {
    @PrimaryKey private int id;
    private double balance;

    /**
     * Creates a Account object with 0 funds.
     */
    public Account() {
        this.id = 0;
        this.balance = 0.0;
    }

    /**
     * Creates a Account object with a given balance
     * @param   amount  The starting balance
     */
    public Account(double amount) {
        this.id = 0;
        this.balance = amount;
    }

    /**
     * Sets the account id
     * @param id the account id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the account id
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Retrieves the current balance of the account
     * @return  A float of the current balance on the account
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Increases the balance of the account by a given value
     * @param   amount  the amount to deposit to the account
     */
    public void deposit(double amount) {
        if (amount > 0)
            this.balance = this.balance + amount;
    }

    /**
     * Decreases the balance of the account by a given value.
     *
     * IMPORTANT: This method DOES NOT evaluate the balance! You can still
     * withdraw amounts that are larger than the balance.
     * Use {@link Account#isWithdrawable(double)} before attempting to
     * withdraw money.
     *
     * @param   amount  the amount to withdraw from the account
     */
    public void withdraw(double amount) {
        if (amount < 0)
            amount = 0 - amount;

        this.balance = this.balance - amount;
    }

    /**
     * Checks if its possible to withdraw the desired amount
     * from the account
     * @param   amount  the amount to withdraw from the account
     * @return  true if the amount is withdrawable, else false
     */
    public boolean isWithdrawable(double amount) {
        return (this.balance > amount);
    }

    @Override
    public String toString() {
        return String.format("id: %d, balance: %f", this.id, this.balance);
    }
}

