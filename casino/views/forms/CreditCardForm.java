package casino.views.forms;

import utilities.ComponentUtilities;
import utilities.GridBagUtilities;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Formatter;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class CreditCardForm extends JPanel implements Form {
    private JTextField holderTextField = new JTextField();
    private JTextField numberTextField = new JFormattedTextField(ComponentUtilities.createMaskFormat("#### #### #### ####"));
    private JTextField expirationMonthTextField = new JTextField();
    private JTextField expirationYearTextField = new JTextField();
    private JTextField securityCodeTextField = new JFormattedTextField(ComponentUtilities.createMaskFormat("###"));

    private JComboBox<Integer> expirationMonthBox;
    private JComboBox<Integer> expirationYearBox;

    private JButton nextButton;
    private JButton cancelButton;

    public CreditCardForm() {
        this.expirationMonthBox = new JComboBox<>();
        this.expirationYearBox = new JComboBox<>();

        this.nextButton = new JButton("Next");
        this.cancelButton = new JButton("Cancel");

        this.configure();
        this.addComponents();
    }

    private void configure() {
        for (int i = 1; i <= 12; i++) {
            this.expirationMonthBox.addItem(i);
            this.expirationYearBox.addItem(2014 + i);
        }

        ComponentUtilities.setWidth(200, this.securityCodeTextField);
        ComponentUtilities.setPreferredWidth(40, this.securityCodeTextField);

        this.nextButton.setActionCommand("next");
        this.cancelButton.setActionCommand("cancel");
    }

    private void addComponents() {
        this.setLayout(new GridBagLayout());

        JLabel holderLabel = new JLabel("Holder");
        JLabel numberLabel = new JLabel("Number");
        JLabel expirationLabel = new JLabel("Expiration");
        JLabel securityCodeLabel = new JLabel("Security Code");

        JPanel buttons = ComponentUtilities.group(FlowLayout.LEFT, this.nextButton, this.cancelButton);
        JPanel boxes = ComponentUtilities.group(FlowLayout.LEFT, this.expirationMonthBox, this.expirationYearBox);

        GridBagUtilities.makeCell(this, holderLabel,                   new Point(0, 0), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.holderTextField,          new Point(1, 0), 0.8, 4, GridBagConstraints.HORIZONTAL);

        GridBagUtilities.makeCell(this, numberLabel,                   new Point(0, 1), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.numberTextField,          new Point(1, 1), 0.8, 4, GridBagConstraints.HORIZONTAL);

        GridBagUtilities.makeCell(this, expirationLabel,               new Point(0, 2), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, boxes,                         new Point(1, 2), 0.8, 4, GridBagConstraints.NONE, GridBagConstraints.WEST);

        GridBagUtilities.makeCell(this, securityCodeLabel,             new Point(0, 3), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.securityCodeTextField,    new Point(1, 3), 0.8, 1, GridBagConstraints.RELATIVE, GridBagConstraints.WEST);

        GridBagUtilities.makeCell(this, buttons,                       new Point(1, 4), 1, 4, GridBagConstraints.HORIZONTAL);
    }

    public JTextField getHolderTextField() {
        return holderTextField;
    }

    public JTextField getNumberTextField() {
        return numberTextField;
    }

    public JTextField getExpirationMonthTextField() {
        return expirationMonthTextField;
    }

    public JTextField getExpirationYearTextField() {
        return expirationYearTextField;
    }

    public JTextField getSecurityCodeTextField() {
        return securityCodeTextField;
    }

    @Override
    public void setActionListener(ActionListener listener) {
        this.nextButton.addActionListener(listener);
        this.cancelButton.addActionListener(listener);
    }

    @Override
    public String toString() {
        return String.format("holder: %s, number: %s, expiration: %s/%s, code: %s", this.holderTextField.getText(), this.numberTextField.getText(),
                this.expirationMonthBox.getSelectedItem(), this.expirationYearBox.getSelectedItem(), this.securityCodeTextField.getText());
    }
}
