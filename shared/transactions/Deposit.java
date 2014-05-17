package shared.transactions;

/**
 * @author  John Mogensen
 * @since   14/05/2014
 * @TODO: In Assigment 3: change method playerTransfer() -> accountTransfer()
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
    public void accountTransfer() {
        this.getUser().getAccount().deposit(this.getAmount());
    }
}
