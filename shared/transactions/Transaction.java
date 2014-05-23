package shared.transactions;

import shared.transactions.payments.Payment;
import shared.users.User;

/**
 * @author  John Mogensen
 * @since   14/05/2014
 *
 * @TODO: In Assignment 3: Change attribute Player player -> User user
 * @TODO: In Assignment 3: Change constructor from Player player -> User user
 * @TODO: In Assignment 3: Change method setPlayer(Player player) -> setUser(User user)
 * @TODO: In Assignment 3: Change method getPlayer() -> getUser()
 * @TODO: In Assignment 3: Change abstract method playerTransfer() -> accountTransfer()
 */
public abstract class Transaction {
    private int timestamp;
    private double amount;
    private Payment payment;
    private User user;

    /**
     * Comments.
     */
    public Transaction () {
        this.timestamp = (int) System.currentTimeMillis() / 1000;
        this.amount = 0.0;
        this.payment = null;
        this.user = null;
    }

    /**
     * Comments.
     */
    public Transaction(double amount, Payment payment, User user) {
        this.timestamp = (int) System.currentTimeMillis() / 1000;
        this.amount = amount;
        this.payment = payment;
        this.user = user;
    }

    /**
     * Sets timestamp.
     */
    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Sets amount.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Sets payment.
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    /**
     * Sets player.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets timestamp.
     */
    public int getTimestamp() {
        return this.timestamp;
    }

    /**
     * Gets amount.
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Gets payment.
     */
    public Payment getPayment() {
        return this.payment;
    }

    /**
     * Gets player.
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Comments.
     */
    public abstract void paymentTransfer();

    /**
     * Transfers money from / to player.
     */
    public abstract void accountTransfer();

    @Override
    public String toString() {
        return String.format("timestamp: %d, amount: %s, payment: %s, user: %s", this.timestamp, this.amount,
                                                                                 this.payment, this.user);
    }
}
