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
 * @since   27/05/2014
 */
public abstract class TransactionView extends View<TransactionListener> implements CreditCardListener, TransactionResponse {
    private CardLayout card = new CardLayout();
    private JPanel view = new JPanel();
    private MenuBar menu = new MenuBar();

    private SimpleForm simpleForm = new SimpleForm();
    private CreditCardForm creditCardForm = new CreditCardForm();

    public TransactionView() {
        this.configure();
        this.addComponents();
    }

    private void configure() {
        this.setLayout(new BorderLayout());
        this.view.setLayout(this.card);

        // Register listeners
        this.simpleForm.getConfirmButton().addActionListener(this::nextButton);
        this.creditCardForm.subscribe(this);
        this.creditCardForm.showResultRow();
    }

    private void addComponents() {
        this.view.add(this.simpleForm);
        this.view.add(this.creditCardForm);

        this.add(this.menu, BorderLayout.PAGE_START);
        this.add(this.view, BorderLayout.CENTER);
    }

    protected SimpleForm getSimpleForm() {
        return this.simpleForm;
    }

    protected CreditCardForm getCreditCardForm() {
        return this.creditCardForm;
    }

    protected MenuBar getMenuBar() {
        return this.menu;
    }

    private void nextButton(ActionEvent e) {
        String amount = this.simpleForm.getFormattedField().getText();

        if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There is no amount set", "Amount", JOptionPane.ERROR_MESSAGE);
        } else {
            // Check if this user can withdraw funds
            try {
                double balance = AuthenticationSession.getInstance().getUser().getAccount().getBalance();

                if (balance >= new Double(amount)) {
                    this.card.next(this.view);
                    this.creditCardForm.getResultTextField().setText(this.simpleForm.getFormattedField().getText());
                    //MainFrame.getInstance().setTitle(WithdrawView.TITLE2);
                } else {
                    JOptionPane.showMessageDialog(null, "You do not have the sufficient balance", "Amount", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, String.format("'%s' is not a number", amount), "Not a number", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void creditCardCancel() {
        this.card.first(this.view);
    }

    @Override
    public void creditCardError() {
        JOptionPane.showMessageDialog(null, "There is a error in the form", "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void transactionSuccessful(double amount) {
        JOptionPane.showMessageDialog(null, "Your transaction has been completed", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Update the menu
        this.getMenuBar().setBalanceLabelValue(amount);

        // Return to game
        FrontController.getInstance().gameAction();
    }

    @Override
    public void transactionUnsuccessful() {
        JOptionPane.showMessageDialog(null, "Your transaction could not be processed, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
