package casino.views;

import casino.events.TransactionListener;
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
public class DepositView extends View<TransactionListener> implements CreditCardListener, TransactionResponse {
    private static final String TITLE1 = "Deposit - Amount";
    private static final String TITLE2 = "Deposit - Credit card details";

    private CardLayout card = new CardLayout();
    private JPanel view = new JPanel();
    private MenuView menu = new MenuView();
    private SimpleForm simpleForm = new SimpleForm("Deposit", "OK");

    private CreditCardForm creditCardForm = new CreditCardForm("Amount to deposit");

    public DepositView() {
        this.configure();
        this.addComponents();

        MainFrame.getInstance().setTitle(DepositView.TITLE2);
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
            this.card.next(this.view);
            this.creditCardForm.getResultTextField().setText(this.simpleForm.getFormattedField().getText());
            MainFrame.getInstance().setTitle(DepositView.TITLE2);
        }
    }

    @Override
    public void creditCardAction(CreditCard card) {
        TransactionEvent event = new TransactionEvent(TransactionEvent.DEPOSIT, new Double(this.simpleForm.getFormattedField().getText()), card);

        if (!event.isValid())
            JOptionPane.showMessageDialog(null, "There is no amount set.", "Amount", JOptionPane.ERROR_MESSAGE);
        else
            this.getObservers().forEach(o -> o.depositPerformed(event));
    }

    @Override
    public void creditCardCancel() {
        this.card.first(this.view);
        MainFrame.getInstance().setTitle(DepositView.TITLE1);
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
