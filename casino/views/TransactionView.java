package casino.views;

import casino.controllers.FrontController;
import casino.events.*;
import casino.views.components.MenuBar;
import shared.View;
import casino.views.forms.CreditCardForm;
import casino.views.forms.SimpleForm;

import javax.swing.*;
import java.awt.*;

/**
 * @author  Dino Opijac
 * @since   27/05/2014
 */
public abstract class TransactionView extends View<TransactionListener> implements CreditCardListener, TransactionResponse {
    private CardLayout cards = new CardLayout();
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
        this.cards.setVgap(20);
        this.cards.setHgap(20);
        this.view.setLayout(this.cards);

        // Register listeners
        this.creditCardForm.subscribe(this);

        // Show the result row and disable it
        this.creditCardForm.showResultRow();
        this.creditCardForm.getResultTextField().setEnabled(false);
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

    protected CardLayout getCards() {
        return this.cards;
    }

    protected JPanel getView() {
        return this.view;
    }

    @Override
    public void creditCardCancel() {
        this.cards.first(this.view);
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
