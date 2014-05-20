package casino.views.forms;

import utilities.GridBagUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author  Dino Opijac
 * @since   20/05/2014
 */
public class TransactionForm extends JPanel implements Form {
    private JTextField transactionTextField = new JTextField();
    private JLabel transactionTextLabel = new JLabel();
    private JButton confirmButton = new JButton("OK");

    public TransactionForm(String text) {
        this.transactionTextLabel.setText(text);

        this.configure();
        this.addComponents();
    }

    private void configure() {
        this.setLayout(new GridBagLayout());
    }

    private void addComponents() {
        GridBagUtilities.makeCell(this, this.transactionTextLabel, new Point(0, 0), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.transactionTextField, new Point(1, 0), 0.7, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.confirmButton,        new Point(2, 0), 0.1, 1, GridBagConstraints.HORIZONTAL);
    }

    public JTextField getTransactionTextField() {
        return transactionTextField;
    }

    public void setTransactionTextField(JTextField transactionTextField) {
        this.transactionTextField = transactionTextField;
    }

    public JLabel getTransactionTextLabel() {
        return transactionTextLabel;
    }

    public void setTransactionTextLabel(JLabel transactionTextLabel) {
        this.transactionTextLabel = transactionTextLabel;
    }

    @Override
    public void setActionListener(ActionListener listener) {
        this.confirmButton.addActionListener(listener);
    }
}
