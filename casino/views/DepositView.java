package casino.views;

import casino.MainFrame;
import casino.events.TransactionEvent;
import shared.transactions.payments.CreditCard;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class DepositView extends TransactionView {
    public DepositView() {
        MainFrame.getInstance().setTitle("Deposit");

        this.getSimpleForm().getTextLabel().setText("Deposit");
        this.getSimpleForm().getConfirmButton().setText("OK");
        this.getSimpleForm().getConfirmButton().addActionListener(this::nextButton);

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

    private void nextButton(ActionEvent e) {
        String amount = this.getSimpleForm().getFormattedField().getText();

        if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There is no amount set.", "Amount", JOptionPane.ERROR_MESSAGE);
        } else {
            this.getCards().next(this.getView());
            this.getCreditCardForm().getResultTextField().setText(this.getSimpleForm().getFormattedField().getText());
        }
    }
}