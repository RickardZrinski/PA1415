package shared.transactions;

/**
 * @author  John Mogensen
 * @since   14/05/2014
 * @TODO: In Assigment 3: change method playerTransfer() -> accountTransfer()
 */
public class Withdraw extends Transaction {

    /**
     * Comments.
     */
    @Override
    public void paymentTransfer() {
        this.getPayment().pay();
    }

    /**
     * Withdraws money from players account.
     */
    @Override
    public void accountTransfer() {
        if (this.getUser().getAccount().isWithdrawable(this.getAmount()))
            this.getUser().getAccount().withdraw(this.getAmount());
    }
}
