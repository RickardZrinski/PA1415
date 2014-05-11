package users;

/**
 * This class models operations that can be
 * performed on a account.
 *
 * @author  Dino Opijac
 * @since   11/05/14
 */
public class Account {
    private float balance;

    /**
     * Creates a Account object with 0 funds.
     */
    public Account() {
        this.balance = 0.0f;
    }

    /**
     * Creates a Account object with a given balance
     * @param   amount  The starting balance
     */
    public Account(float amount) {
        this.balance = amount;
    }

    /**
     * Retrieves the current balance of the account
     * @return  A float of the current balance on the account
     */
    public float getBalance() {
        return balance;
    }

    /**
     * Increases the balance of the account by a given value
     * @param   amount  the amount to deposit to the account
     */
    public void deposit(float amount) {
        this.balance = this.balance + amount;
    }

    /**
     * Decreases the balance of the account by a given value
     * @param   amount  the amount to withdraw from the account
     */
    public void withdraw(float amount) {
        this.balance = this.balance - amount;
    }

    /**
     * Checks if its possible to withdraw the desired amount
     * from the account
     * @param   amount  the amount to withdraw from the account
     * @return  true if the amount is withdrawable, else false
     */
    public boolean isWithdrawable(float amount) {
        return (this.balance > amount);
    }

    @Override
    public String toString() {
        return String.format("Balance: %f", this.balance);
    }
}