package casino.views;

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
public class WithdrawView extends View<TransactionListener> implements CreditCardListener, TransactionResponse {
    private static final String TITLE1 = "Withdraw - Amount";
    private static final String TITLE2 = "Withdraw - Credit card details";

    private CardLayout card = new CardLayout();
    private JPanel view = new JPanel();
    private MenuBar menu = new casino.views.components.MenuBar();

    private SimpleForm simpleForm = new SimpleForm("Withdraw", "OK");
    private CreditCardForm creditCardForm = new CreditCardForm("Amount to withdraw");

    public WithdrawView() {
        this.configure();
        this.addComponents();

        MainFrame.getInstance().setTitle(WithdrawView.TITLE2);
    }

    private void configure() {
        this.setLayout(new BorderLayout());
        this.view.setLayout(this.card);

        // Register listeners
        this.simpleForm.getConfirmButton().addActionListener(this::nextButton);
        this.creditCardForm.subscribe(this);
    }

    private void addComponents() {
        this.view.add(this.simpleForm);
        this.view.add(this.creditCardForm);

        this.add(this.menu, BorderLayout.PAGE_START);
        this.add(this.view, BorderLayout.CENTER);
    }

    private void nextButton(ActionEvent e) {
        String amount = this.simpleForm.getFormattedField().getText();

        if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There is no amount set.", "Amount", JOptionPane.ERROR_MESSAGE);
        } else {
            // Check if this user can withdraw funds
            try {
                double balance = AuthenticationSession.getInstance().getUser().getAccount().getBalance();

                if (balance >= new Double(amount)) {
                    this.card.next(this.view);
                    this.creditCardForm.getResultTextField().setText(this.simpleForm.getFormattedField().getText());
                    MainFrame.getInstance().setTitle(WithdrawView.TITLE2);
                } else {
                    JOptionPane.showMessageDialog(null, "You do not have the sufficient balance", "Amount", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void creditCardAction(CreditCard card) {
        TransactionEvent event = new TransactionEvent(TransactionEvent.DEPOSIT, new Double(this.simpleForm.getFormattedField().getText()), card);

        if (!event.isValid())
            JOptionPane.showMessageDialog(null, "There is no amount set.", "Amount", JOptionPane.ERROR_MESSAGE);
        else
            this.getObservers().forEach(o -> o.withdrawPerformed(event));
    }

    @Override
    public void creditCardCancel() {
        this.card.first(this.view);
        MainFrame.getInstance().setTitle(WithdrawView.TITLE1);
    }

    @Override
    public void creditCardError() {
        JOptionPane.showMessageDialog(null, "There is a error in the form", "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void transactionSuccessful() {
        JOptionPane.showMessageDialog(null, "Your transaction has been completed", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void transactionUnsuccessful() {
        JOptionPane.showMessageDialog(null, "Your transaction could not be processed, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
