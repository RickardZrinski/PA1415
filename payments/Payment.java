package payments;

/**
 * Created by JamJaws.
 */
public abstract class Payment {
    private double amount;


    /**
     * Comments.
     */
    public Payment() {
        this.amount = 0.0;
    }

    /**
     * Comments.
     */
    public Payment(double amount) {
        this.amount = amount;
    }

    /**
     * Comments.
     */
    public void pay() {

    }

    /**
     * Sets amount.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets amount.
     */
    public double getAmount() {
        return this.amount;
    }
}
