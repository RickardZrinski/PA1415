package casino.views;

import casino.controllers.FrontController;
import casino.events.TransactionListener;
import casino.views.components.MenuBar;
import shared.View;
import casino.MainFrame;
import casino.events.CreditCardListener;
import casino.events.TransactionEvent;
import casino.events.TransactionResponse;
import casino.views.forms.CreditCardForm;
import casino.views.forms.SimpleForm;
import shared.transactions.payments.CreditCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class DepositView extends TransactionView {
    public DepositView() {
        this.getSimpleForm().getTextLabel().setText("Deposit");
        this.getSimpleForm().getConfirmButton().setText("OK");

        this.getCreditCardForm().getResultLabel().setText("Amount to deposit");
    }

    @Override
    public void creditCardAction(CreditCard card) {
        TransactionEvent event = new TransactionEvent(TransactionEvent.DEPOSIT, new Double(this.getSimpleForm().getFormattedField().getText()), card);

        if (!event.isValid())
            JOptionPane.showMessageDialog(null, "There is no amount set.", "Amount", JOptionPane.ERROR_MESSAGE);
        else
            this.getObservers().forEach(o -> o.depositPerformed(event));
    }
}