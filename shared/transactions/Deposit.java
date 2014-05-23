package shared.transactions;

/**
 * @author  John Mogensen
 * @since   14/05/2014
 * @TODO: In Assignment 3: change method playerTransfer() -> accountTransfer()
 */
public class Deposit extends Transaction {

    /**
     * Sets the amount of the payment
     *
     * @param amount the amount to deposit
     */
    public Deposit(double amount) {
        super.setAmount(amount);
    }

    /**
     * Deposits money to players account
     */
    @Override
    public void accountTransfer() {
        super.getUser().getAccount().deposit(super.getAmount());
    }
}
