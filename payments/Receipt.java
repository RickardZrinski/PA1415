package payments;

import users.Player;

/**
 * Created by JamJaws.
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
    public boolean send(Player player) {

        return false;
    }


}
