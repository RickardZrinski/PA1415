package shared.transactions;

/**
 * @author  John Mogensen
 * @since   14/05/2014
 * @TODO: In Assignment 3: change method playerTransfer() -> accountTransfer()
 */
public class Withdraw extends Transaction {

    /**
     * Sets the amount of the payment
     *
     * @param amount the amount to withdraw
     */
    public Withdraw(double amount) {
        super.setAmount(amount);
    }

    /**
     * Verifies the payment and withdraws money from the business bank.
     */
    @Override
    public void paymentTransfer() {
        if (this.getUser().getAccount().isWithdrawable(this.getAmount()))
            this.getPayment().pay();
    }

    /**
     * Withdraws money from players account
     */
    @Override
    public void accountTransfer() {
        this.getUser().getAccount().withdraw(this.getAmount());
    }
}
