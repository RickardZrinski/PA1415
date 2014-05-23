package casino.models;

import shared.AuthenticationSession;
import shared.dao.DAOFactory;
import shared.transactions.Deposit;
import shared.transactions.Receipt;
import shared.transactions.Transaction;
import shared.transactions.Withdraw;
import shared.transactions.payments.Payment;

/**
 * @author  Dino Opijac
 * @since   23/05/2014
 */
public class TransactionModel {
    private Transaction transaction;

    /**
     * Prepares a new deposit
     * @param amount    the amount to deposit
     */
    public void deposit(double amount) {
        this.transaction = new Deposit(amount);
    }

    /**
     * Prepares a new withdraw
     * @param amount    the amount to withdraw
     */
    public void withdraw(double amount) {
        this.transaction = new Withdraw(amount);
    }

    /**
     * Sets the payment method
     * @param payment   the payment method used in this transaction
     */
    public void makePayment(Payment payment) {
        this.transaction.setPayment(payment);
    }

    /**
     * Performs the transaction
     */
    public void makeTransaction() {
        this.transaction.paymentTransfer();
    }

    /**
     * Ends the transaction
     */
    public void endTransaction() {
        try {
            this.transaction.setUser(AuthenticationSession.getInstance().getUser());
            this.transaction.accountTransfer();

            // Update our database records
            DAOFactory.getUserDao().update(this.transaction.getUser());

            // Create a new receipt
            Receipt receipt = new Receipt();

            receipt.setTimestamp( this.transaction.getTimestamp() );
            receipt.setAmount( this.transaction.getTimestamp() );

            receipt.send( this.transaction.getUser() );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
