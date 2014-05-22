package casino.views;

import casino.AbstractView;
import casino.MainFrame;
import casino.events.CreditCardListener;
import casino.events.TransactionEvent;
import casino.views.forms.CreditCardForm;
import casino.views.forms.SimpleForm;
import shared.transactions.payments.CreditCard;
import shared.transactions.payments.Payment;
import utilities.ComponentUtilities;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class DepositView extends AbstractView implements ActionListener, CreditCardListener {
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
        this.simpleForm.setActionListener(this);
        this.creditCardForm.subscribe(this);
    }

    private void addComponents() {
        this.view.add(this.simpleForm);
        this.view.add(this.creditCardForm);

        this.add(this.menu, BorderLayout.PAGE_START);
        this.add(this.view, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
        String amount = this.simpleForm.getFormattedField().getText();

        // We retrieved a credit card, check if there was an amount set
        if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There is no amount set.", "Amount", JOptionPane.ERROR_MESSAGE);
        } else {
            // Set the amount and send the deposit request.
            card.setAmount(new Double(amount));

            // Notify our listeners
            this.notify("depositPerformed", new TransactionEvent(TransactionEvent.DEPOSIT, card));
        }
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
}
