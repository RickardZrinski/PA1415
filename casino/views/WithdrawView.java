package casino.views;

import casino.events.*;
import casino.MainFrame;
import shared.AuthenticationSession;
import shared.transactions.payments.CreditCard;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class WithdrawView extends TransactionView {
    public WithdrawView() {
        MainFrame.getInstance().setTitle("Withdraw");

        this.getSimpleForm().getTextLabel().setText("Withdraw");
        this.getSimpleForm().getConfirmButton().setText("OK");
        this.getSimpleForm().getConfirmButton().addActionListener(this::nextButton);
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

    private void nextButton(ActionEvent e) {
        String amount = this.getSimpleForm().getFormattedField().getText();

        if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There is no amount set", "Amount", JOptionPane.ERROR_MESSAGE);
        } else {
            // Check if this user can withdraw funds
            try {
                double balance = AuthenticationSession.getInstance().getUser().getAccount().getBalance();

                if (balance >= new Double(amount)) {
                    this.getCards().next(this.getView());
                    this.getCreditCardForm().getResultTextField().setText(this.getSimpleForm().getFormattedField().getText());
                } else {
                    JOptionPane.showMessageDialog(null, "You do not have the sufficient balance", "Amount", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, String.format("'%s' is not a number", amount), "Not a number", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
