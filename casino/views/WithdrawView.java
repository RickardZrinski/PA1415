package casino.views;

import casino.AbstractView;
import casino.MainFrame;
import casino.events.CreditCardListener;
import casino.events.TransactionEvent;
import casino.views.forms.CreditCardForm;
import casino.views.forms.SimpleForm;
import shared.AuthenticationSession;
import shared.dao.DAOFactory;
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
public class WithdrawView extends AbstractView implements ActionListener, CreditCardListener {
    private static final String TITLE1 = "Withdraw - Amount";
    private static final String TITLE2 = "Withdraw - Credit card details";

    private CardLayout card = new CardLayout();
    private JPanel view = new JPanel();
    private MenuView menu = new MenuView();
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
        double amount = new Double(this.simpleForm.getFormattedField().getText());

        TransactionEvent event = new TransactionEvent(TransactionEvent.DEPOSIT, amount, card);

        // We retrieved a credit card, check if there was an amount set
        if (!event.isValid()) {
            JOptionPane.showMessageDialog(null, "There is no amount set.", "Amount", JOptionPane.ERROR_MESSAGE);
        } else {
            // Set the amount and send the deposit request.
            card.setAmount(amount);

            // Notify our listeners
            this.notify("withdrawPerformed", event);
        }
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
}
