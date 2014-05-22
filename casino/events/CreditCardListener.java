package casino.events;

import shared.transactions.payments.CreditCard;

/**
 * @author  Dino Opijac
 * @since   22/05/14
 */
public interface CreditCardListener {
    public void creditCardAction(CreditCard card);
    public void creditCardError();
    public void creditCardCancel();
}
