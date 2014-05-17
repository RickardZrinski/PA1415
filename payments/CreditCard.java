package payments;

/**
 * @author  John Mogensen
 * @since   14/05/2014
 */
public class CreditCard extends Payment {
    private String holder;
    private int number;
    private int securityCode;
    private int expirationYear;
    private int expirationMonth;
    private boolean authorized;


    /**
     * Comments.
     */
    public CreditCard() {
        this.setAmount(0.0);
        this.holder = "?";
        this.number = 0000000000000000;
        this.securityCode = 000;
        this.expirationYear = 00;
        this.expirationMonth = 00;
        this.authorized = false;
    }

    /**
     * Comments.
     */
    public CreditCard(double amount, String holder, int number, int securityCode, int expirationYear, int expirationMonth, boolean authorized) {
        this.setAmount(amount);
        this.holder = holder;
        this.number = number;
        this.securityCode = securityCode;
        this.expirationYear = expirationYear;
        this.expirationMonth = expirationMonth;
        this.authorized = authorized;
    }

    /**
     * Sets holder.
     */
    public void setHolder(String holder) {
        this.holder = holder;
    }

    /**
     * Sets number.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Sets securityCode.
     */
    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    /**
     * Sets expirationYear.
     */
    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    /**
     * Sets expirationMonth.
     */
    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    /**
     * Sets authorized.
     */
    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    /**
     * Comments.
     */
    @Override
    public void pay() {

    }

    /**
     * Returns holder.
     */
    public String getHolder() {
        return this.holder;
    }

    /**
     * Returns number.
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * Returns securityCode.
     */
    public int getSecurityCode() {
        return this.securityCode;
    }

    /**
     * Returns expirationYear.
     */
    public int getExpirationYear() {
        return this.expirationYear;
    }

    /**
     * Returns expirationMonth.
     */
    public int getExpirationMonth() {
        return this.expirationMonth;
    }

    /**
     * Returns authorized.
     */
    public boolean isAuthorized() {
        return this.authorized;
    }
}
