package shared.transactions.services;

import shared.transactions.payments.Payment;

/**
 * @author  Dino Opijac
 * @since   13/05/14
 *
 * @TODO:   Assignment 3: Member added: - authorized: bool
 * @TODO:   Assignment 3: Method added: + authorize(Payment)
 */
public class Bank {
    private boolean authorized = false;

    /**
     * Authorizes a payment with the bank.
     * @param payment
     */
    public void authorize(Payment payment) {
        // Just print the payment
        System.out.println(String.format("Bank received a payment: %s", payment));

        this.authorized = true;
    }

    public boolean authorized() {
        return this.authorized;
    }
}
