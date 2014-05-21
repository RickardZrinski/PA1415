package casino.views;

import casino.AbstractView;
import casino.MainFrame;
import casino.views.forms.CreditCardForm;
import casino.views.forms.TransactionForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class DepositView extends AbstractView implements ActionListener {
    private MenuView menu = new MenuView();
    private TransactionForm transactionForm = new TransactionForm("Deposit");
    private CreditCardForm creditCardForm = new CreditCardForm();

    public DepositView() {
        this.configure();
        this.addComponents();

        MainFrame.getInstance().setTitle("Credit Card details");
    }

    private void configure() {
        this.setLayout(new BorderLayout());
        this.transactionForm.setActionListener(this);
        this.creditCardForm.setActionListener(this);
    }

    private void addComponents() {
        this.add(this.menu, BorderLayout.PAGE_START);
        this.add(this.transactionForm, BorderLayout.CENTER);
        this.add(this.creditCardForm, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("cancel")) {
            System.out.println("The user cancelled");
        } else {
            System.out.println(this.transactionForm);
            System.out.println(this.creditCardForm);
        }
    }
}
