package casino.views;

import casino.AbstractView;
import casino.MainFrame;
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
public class DepositView extends AbstractView implements ActionListener {
    private CardLayout card = new CardLayout();
    private JPanel view = new JPanel();
    private MenuView menu = new MenuView();
    private SimpleForm simpleForm = new SimpleForm("Deposit", "OK");
    private SimpleForm disabledSimpleForm = new SimpleForm("Deposit");

    private CreditCardForm creditCardForm = new CreditCardForm();

    public DepositView() {
        this.configure();
        this.addComponents();

        MainFrame.getInstance().setTitle("Deposit - Amount");
    }

    private void configure() {
        this.setLayout(new BorderLayout());
        this.view.setLayout(this.card);

        this.simpleForm.setActionListener(this);
        this.creditCardForm.setActionListener(this);

        this.simpleForm.getConfirmButton().setActionCommand("amount");
        this.creditCardForm.getNextButton().setActionCommand("next");
        this.creditCardForm.getCancelButton().setActionCommand("cancel");
    }

    private void addComponents() {
        this.view.add(this.simpleForm);

        // Create a disabled simpleForm so the user can see how much they wanted to deposit.
        this.disabledSimpleForm.getConfirmButton().setVisible(false);
        this.disabledSimpleForm.getFormattedField().setEnabled(false);

        JPanel group = ComponentUtilities.box(BoxLayout.PAGE_AXIS, this.disabledSimpleForm, this.creditCardForm);
        this.view.add(group);

        this.add(this.menu, BorderLayout.PAGE_START);
        this.add(this.view, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        switch(action) {
            case "next": {
                // Create the payment method
                Payment p = new CreditCard( new Double(this.simpleForm.getFormattedField().getText()),
                        this.creditCardForm.getHolderTextField().getText(),
                        new Long(this.creditCardForm.getNumberTextField().getText().replace(" ", "")),
                        new Integer(this.creditCardForm.getSecurityCodeTextField().getText()),
                        this.creditCardForm.getExpirationMonthBox().getSelectedIndex(),
                        this.creditCardForm.getExpirationYearBox().getSelectedIndex());

                this.notify("depositPerformed", new TransactionEvent(TransactionEvent.DEPOSIT, p));
            }

            break;

            case "amount":
                this.disabledSimpleForm.getFormattedField().setText(this.simpleForm.getFormattedField().getText());
                this.card.next(this.view);
                MainFrame.getInstance().setTitle("Deposit - Credit Card Details");
                break;

            default: System.out.println("The user cancelled"); break;
        }

    }
}
