package shared.transactions.payments;

/**
 * @author  John Mogensen
 * @since   14/05/2014
 */
public abstract class Payment {
    private double amount;

    /**
     * Default constructor sets the payment to zero
     */
    public Payment() {
        this.amount = 0.0;
    }

    /**
     * Constructor that sets a predefined payment amount
     * @param amount    the amount in the payment
     */
    public Payment(double amount) {
        this.amount = amount;
    }

    /**
     * Sets the payment amount
     * @param amount    the amount in the payment
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the payment amount
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Pays for the abstract payment
     */
    public abstract void pay();

    @Override
    public String toString() {
        return String.format("amount: %f", this.amount);
    }
}
