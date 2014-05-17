package shared.transactions;

import payments.Payment;
import users.Player;

/**
 * @author  John Mogensen
 * @since   14/05/2014
 */
public abstract class Transaction {
    private int timestamp;
    private double amount;
    private Payment payment;
    private Player player;

    /**
     * Comments.
     */
    public Transaction () {
        this.timestamp = (int) System.currentTimeMillis() / 1000;
        this.amount = 0.0;
        this.payment = null;
        this.player = null;
    }

    /**
     * Comments.
     */
    public Transaction(double amount, Payment payment, Player player) {
        this.timestamp = (int) System.currentTimeMillis() / 1000;
        this.amount = amount;
        this.payment = payment;
        this.player = player;
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
    public void setPlayer(Player player) {
        this.player = player;
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
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Comments.
     */
    public abstract void paymentTransfer();

    /**
     * Transfers money from / to player.
     */
    public abstract void playerTransfer();
}
