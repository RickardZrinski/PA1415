package payments;

/**
 * Created by JamJaws.
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
    public void playerTransfer() {
        if (this.getPlayer().getAccount().isWithdrawable(this.getAmount()))
            this.getPlayer().getAccount().withdraw(this.getAmount());
    }
}
