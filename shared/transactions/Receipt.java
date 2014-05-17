package shared.transactions;

import shared.users.User;

/**
 * @author  John Mogensen
 * @since   14/05/2014
 *
 * @TODO: In Assigment 3: Change method: send(Player player) to send(User user)
 */
public class Receipt {
    private int timestamp;
    private String dateFormat;
    private double amount;

    /**
     * Comments.
     */
    public Receipt() {
        this.timestamp = (int) System.currentTimeMillis() / 1000;
        this.dateFormat = "?";
        this.amount = 0.0;
    }

    /**
     * Comments.
     */
    public Receipt(String dateFormat, double amount) {
        this.timestamp = (int) System.currentTimeMillis() / 1000;
        this.dateFormat = dateFormat;
        this.amount = amount;
    }

    /**
     * Sets timestamp.
     */
    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Sets dateFormat.
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * Sets amount.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Get timestamp.
     */
    public int getTimestamp() {
        return this.timestamp;
    }

    /**
     * Gets dateFormat.
     */
    public String getDateFormat() {
        return this.dateFormat;
    }

    /**
     * Gets amount.
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Comments.
     */
    public boolean send(User user) {

        return false;
    }


}
