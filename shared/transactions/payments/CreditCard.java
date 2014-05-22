package shared.transactions.payments;

import shared.transactions.services.Bank;

/**
 * @author  John Mogensen
 * @since   14/05/2014
 *
 * @TODO:   Assignments:    -number: int            is  -number: long
 * @TODO:                   +setNumber(int number)  is  +setNumber(long number)
 * @TODO:                   +getNumber(): int       is  +getNumber(): long
 */
public class CreditCard extends Payment {
    private String holder;
    private long number;
    private int securityCode;
    private int expirationYear;
    private int expirationMonth;
    private boolean authorized = false;

    /**
     * Creates a new credit card payment
     *
     * @param amount            the amount to bind to this payment
     * @param holder            the holder of this credit card
     * @param number            the number of this credit card
     * @param securityCode      the security code of this credit card
     * @param expirationMonth   the expiration month for this credit card in M format (e.g. 5)
     * @param expirationYear    the expiration year for this credit card in YYYY format (e.g. 2014)
     */
    public CreditCard(double amount, String holder, long number, int securityCode, int expirationMonth, int expirationYear) {
        super.setAmount(amount);
        this.holder = holder;
        this.number = number;
        this.securityCode = securityCode;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    /**
     * Sets a holder for this credit card
     *
     * @param holder            the name of the credit card holder
     */
    public void setHolder(String holder) {
        this.holder = holder;
    }

    /**
     * Sets the number of this credit card
     *
     * @param number            the number of this credit card
     */
    public void setNumber(long number) {
        this.number = number;
    }

    /**
     * Sets the security code for this credit card
     *
     * @param securityCode      the security code of this credit card
     */
    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    /**
     * Sets the year when this credit card expires
     *
     * @param expirationYear    the year in YYYY-format (e.g. '2014' for year 2014)
     */
    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    /**
     * Sets the month when this credit card expires
     *
     * @param expirationMonth   the month in M-format (e.g. '5' for May)
     */
    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    /**
     * Sets the authorization state for this credit card
     *
     * @param authorized        true if the credit card is authorized, else false
     */
    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    /**
     * Sends this payment to the bank and verifies it.
     */
    @Override
    public void pay() {
        Bank bank = new Bank();
        bank.authorized();
    }

    /**
     * @return  the holder of this credit card
     */
    public String getHolder() {
        return this.holder;
    }

    /**
     * @return  the number of this credit card
     */
    public long getNumber() {
        return this.number;
    }

    /**
     * @return  the security code
     */
    public int getSecurityCode() {
        return this.securityCode;
    }

    /**
     * @return  the year when this credit card expires
     */
    public int getExpirationYear() {
        return this.expirationYear;
    }

    /**
     * @return  the month this credit card expires
     */
    public int getExpirationMonth() {
        return this.expirationMonth;
    }

    /**
     * @return  the authorization state of this payment
     */
    public boolean isAuthorized() {
        return this.authorized;
    }
}
