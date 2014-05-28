package casino.views;

import casino.events.*;
import casino.MainFrame;
import shared.transactions.payments.CreditCard;

import javax.swing.*;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class WithdrawView extends TransactionView {
    public WithdrawView() {
        MainFrame.getInstance().setTitle("Games");

        this.getSimpleForm().getTextLabel().setText("Withdraw");
        this.getSimpleForm().getConfirmButton().setText("OK");

        this.getCreditCardForm().getResultLabel().setText("Amount to deposit");
    }

    @Override
    public void creditCardAction(CreditCard card) {
        TransactionEvent event = new TransactionEvent(TransactionEvent.WITHDRAW, new Double(this.getSimpleForm().getFormattedField().getText()), card);

        if (!event.isValid())
            JOptionPane.showMessageDialog(null, "There is no amount set.", "Amount", JOptionPane.ERROR_MESSAGE);
        else
            this.getObservers().forEach(o -> o.withdrawPerformed(event));
    }
}
