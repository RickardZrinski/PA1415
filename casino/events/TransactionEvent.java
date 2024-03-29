package casino.events;

import shared.transactions.payments.Payment;

/**
 * @author  Dino Opijac
 * @since   22/05/14
 */
public class TransactionEvent {
    public static final int DEPOSIT = 1;
    public static final int WITHDRAW = 2;

    private int type = 0;
    private double amount = 0.0;
    private Payment payment;

    /**
     * Creates a transaction event
     *
     * @see TransactionEvent#DEPOSIT
     * @see TransactionEvent#WITHDRAW
     *
     * @param type      the type of transaction
     * @param payment   the payment method
     */
    public TransactionEvent(int type, double amount, Payment payment) {
        this.type = type;
        this.amount = amount;
        this.payment = payment;
    }

    /**
     * @see TransactionEvent#DEPOSIT
     * @see TransactionEvent#WITHDRAW
     *
     * @return  the type of transaction
     */
    public int getType() {
        return this.type;
    }

    /**
     * @return the amount of the transaction
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * @return the payment method used for this transaction
     */
    public Payment getPayment() {
        return this.payment;
    }

    /**
     * Sets the type of transaction event
     * @param type      the transaction event
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Sets the transaction amount
     * @param amount    the amount of the transaction
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Sets the payment method for this transaction
     * @param payment   the payment method
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
    }


    /**
     * Checks if the amount is larger than 0.0 and if its a
     * deposit or withdrawal.
     *
     * @return true if the event is valid, else false
     */
    public boolean isValid() {
        return (this.amount > 0.0 && this.type == TransactionEvent.DEPOSIT || this.type == TransactionEvent.WITHDRAW);
    }
}
