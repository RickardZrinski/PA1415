package casino.views;

import casino.controllers.FrontController;
import casino.events.*;
import casino.views.components.MenuBar;
import shared.View;
import casino.MainFrame;
import casino.views.forms.CreditCardForm;
import casino.views.forms.SimpleForm;
import shared.AuthenticationSession;
import shared.transactions.payments.CreditCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class WithdrawView extends TransactionView {
    public WithdrawView() {
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
