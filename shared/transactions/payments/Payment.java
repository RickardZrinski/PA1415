package shared.transactions.payments;

/**
 * @author  John Mogensen
 * @since   14/05/2014
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
