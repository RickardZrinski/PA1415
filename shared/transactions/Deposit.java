package shared.transactions;

/**
 * @author  John Mogensen
 * @since   14/05/2014
 */
public class Deposit extends Transaction {

    /**
     * Comments.
     */
    @Override
    public void paymentTransfer() {
        this.getPayment().pay();
    }

    /**
     * Deposits money to players account.
     */
    @Override
    public void playerTransfer() {
        this.getPlayer().getAccount().deposit(this.getAmount());
    }
}
