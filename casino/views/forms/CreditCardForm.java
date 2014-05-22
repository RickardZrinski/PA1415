package casino.views.forms;

import casino.AbstractView;
import shared.transactions.payments.CreditCard;
import utilities.ComponentUtilities;
import utilities.GridBagUtilities;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Formatter;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class CreditCardForm extends AbstractView implements ActionListener {
    private JLabel resultLabel = new JLabel();
    private JTextField resultTextField = new JTextField();
    private JTextField holderTextField = new JTextField();
    private JFormattedTextField numberTextField = new JFormattedTextField(ComponentUtilities.createMaskFormat("#### #### #### ####"));
    private JFormattedTextField securityCodeTextField = new JFormattedTextField(ComponentUtilities.createMaskFormat("###"));

    private JComboBox<Integer> expirationMonthBox;
    private JComboBox<Integer> expirationYearBox;

    private JButton nextButton;
    private JButton cancelButton;

    public CreditCardForm(String text) {
        this.expirationMonthBox = new JComboBox<>();
        this.expirationYearBox = new JComboBox<>();

        this.nextButton = new JButton("Next");
        this.cancelButton = new JButton("Cancel");

        if (text == null)
            this.hideResultRow();
        else {
            this.resultLabel.setText(text);
            this.resultTextField.setEnabled(false);
        }

        this.configure();
        this.addComponents();
    }

    public CreditCardForm() {
        this(null);
    }

    private void configure() {
        for (int i = 1; i <= 12; i++) {
            this.expirationMonthBox.addItem(i);
            this.expirationYearBox.addItem(2014 + i);
        }

        ComponentUtilities.setWidth(200, this.securityCodeTextField);
        ComponentUtilities.setPreferredWidth(40, this.securityCodeTextField);

        // The next button should listen to this class, it emits a creditCardAction event
        this.nextButton.addActionListener(this);
        this.cancelButton.addActionListener(this);

        this.cancelButton.setActionCommand("cancel");
    }

    private void addComponents() {
        this.setLayout(new GridBagLayout());

        JLabel holderLabel = new JLabel("Holder");
        JLabel numberLabel = new JLabel("Number");
        JLabel expirationLabel = new JLabel("Expiration");
        JLabel securityCodeLabel = new JLabel("Security Code");

        JPanel buttons = ComponentUtilities.flow(FlowLayout.LEFT, this.nextButton, this.cancelButton);
        JPanel boxes = ComponentUtilities.flow(FlowLayout.LEFT, this.expirationMonthBox, this.expirationYearBox);

        GridBagUtilities.makeCell(this, this.resultLabel,              new Point(0, 0), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.resultTextField,          new Point(1, 0), 0.8, 4, GridBagConstraints.HORIZONTAL);

        GridBagUtilities.makeCell(this, holderLabel,                   new Point(0, 1), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.holderTextField,          new Point(1, 1), 0.8, 4, GridBagConstraints.HORIZONTAL);

        GridBagUtilities.makeCell(this, numberLabel,                   new Point(0, 2), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.numberTextField,          new Point(1, 2), 0.8, 4, GridBagConstraints.HORIZONTAL);

        GridBagUtilities.makeCell(this, expirationLabel,               new Point(0, 3), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, boxes,                         new Point(1, 3), 0.8, 4, GridBagConstraints.NONE, GridBagConstraints.WEST);

        GridBagUtilities.makeCell(this, securityCodeLabel,             new Point(0, 4), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.securityCodeTextField,    new Point(1, 4), 0.8, 1, GridBagConstraints.RELATIVE, GridBagConstraints.WEST);

        GridBagUtilities.makeCell(this, buttons,                       new Point(1, 5), 1, 4, GridBagConstraints.HORIZONTAL);
    }

    public JLabel getResultLabel() {
        return this.resultLabel;
    }

    public JTextField getResultTextField() {
        return this.resultTextField;
    }

    public JTextField getHolderTextField() {
        return this.holderTextField;
    }

    public JFormattedTextField getNumberTextField() {
        return this.numberTextField;
    }

    public JFormattedTextField getSecurityCodeTextField() {
        return this.securityCodeTextField;
    }

    public JComboBox<Integer> getExpirationYearBox() {
        return this.expirationYearBox;
    }

    public JComboBox<Integer> getExpirationMonthBox() {
        return this.expirationMonthBox;
    }

    public JButton getNextButton() {
        return this.nextButton;
    }

    public JButton getCancelButton() {
        return this.cancelButton;
    }

    public void hideResultRow() {
        this.resultLabel.setVisible(false);
        this.resultTextField.setVisible(false);
    }

    public void showResultRow() {
        this.resultLabel.setVisible(true);
        this.resultTextField.setVisible(true);
    }

    @Override
    public String toString() {
        return String.format("holder: %s, number: %s, expiration: %s/%s, code: %s", this.holderTextField.getText(), this.numberTextField.getText(),
                this.expirationMonthBox.getSelectedItem(), this.expirationYearBox.getSelectedItem(), this.securityCodeTextField.getText());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("cancel"))
        {
            this.notify("creditCardCancel");
        } else {
            try {
                CreditCard card = new CreditCard(0.0,
                        this.holderTextField.getText(),
                        new Long(this.numberTextField.getText().replace(" ", "")),
                        new Integer(this.securityCodeTextField.getText()),
                        this.expirationMonthBox.getSelectedIndex(),
                        this.expirationYearBox.getSelectedIndex());

                this.notify("creditCardAction", card);
            } catch (Exception exception) {
                this.notify("creditCardError");
            }
        }
    }
}
