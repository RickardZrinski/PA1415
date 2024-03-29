package casino.views.forms;

import utilities.ComponentUtilities;
import utilities.GridBagUtilities;

import javax.swing.*;
import java.awt.*;

/**
 * @author  Dino Opijac
 * @since   19/05/2014
 */
public class MessageForm extends JPanel {

    private JTextField nameTextField;
    private JTextField subjectTextField;
    private JComboBox categoryComboBox;
    private JTextField contactDetailsTextField;
    private JTextArea messageTextArea;
    private JButton sendButton;
    private JButton cancelButton;

    public MessageForm() {
        // Form elements
        this.nameTextField = new JTextField();
        this.subjectTextField = new JTextField();
        this.categoryComboBox  = new JComboBox<>(new String[] {"Support", "Praise", "Feature request"});
        this.contactDetailsTextField = new JTextField();
        this.messageTextArea = new JTextArea();
        this.sendButton = new JButton("Send");
        this.cancelButton = new JButton("Cancel");
        this.configure();
        this.addComponents();
    }

    private void configure() {
        this.setLayout(new GridBagLayout());
        this.messageTextArea.setRows(10);
    }

    private void addComponents() {
        JLabel nameLabel = new JLabel("Full Name");
        JLabel subjectLabel = new JLabel("Subject");
        JLabel categoryLabel = new JLabel("Category");
        JLabel contactDetailsLabel = new JLabel("Contact Details");
        JLabel messageLabel = new JLabel("Message");
        JLabel contactDetailsInfoLabel = new JLabel("Where can we reach you? Leave a phone number or email address.");

        JPanel buttons = ComponentUtilities.flow(FlowLayout.LEFT, this.sendButton, this.cancelButton);

        // Create a scroll pane for the text area
        JScrollPane pane = new JScrollPane(this.messageTextArea);

        GridBagUtilities.makeCell(this, nameLabel, new Point(0, 0), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.nameTextField, new Point(1, 0), 0.8, 1, GridBagConstraints.HORIZONTAL);

        GridBagUtilities.makeCell(this, subjectLabel, new Point(0, 1), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.subjectTextField, new Point(1, 1), 0.8, 1, GridBagConstraints.HORIZONTAL);

        GridBagUtilities.makeCell(this, categoryLabel, new Point(0, 2), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.categoryComboBox, new Point(1, 2), 0.8, 1, GridBagConstraints.HORIZONTAL);

        GridBagUtilities.makeCell(this, contactDetailsLabel, new Point(0, 3), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.contactDetailsTextField, new Point(1, 3), 0.8, 1, GridBagConstraints.HORIZONTAL);

        GridBagUtilities.makeCell(this, contactDetailsInfoLabel, new Point(1, 4), 0.8, 1, GridBagConstraints.HORIZONTAL);

        // Create empty space
        GridBagUtilities.makeCell(this, new JLabel(" "), new Point(0, 5), 1, 2, GridBagConstraints.HORIZONTAL);

        GridBagUtilities.makeCell(this, messageLabel, new Point(0, 6), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, pane, new Point(1, 6), 0.8, 1, GridBagConstraints.HORIZONTAL);


        GridBagUtilities.makeCell(this, buttons, new Point(1, 7), 0.8, 1, GridBagConstraints.HORIZONTAL);
    }

    public JTextField getNameTextField() {
        return this.nameTextField;
    }

    public JTextField getSubjectTextField() {
        return this.subjectTextField;
    }

    public JComboBox getCategoryComboBox() {
        return this.categoryComboBox;
    }

    public JTextField getContactDetailsTextField() {
        return this.contactDetailsTextField;
    }

    public JTextArea getMessageTextArea() {
        return this.messageTextArea;
    }

    public JButton getSendButton() {
        return this.sendButton;
    }

    public JButton getCancelButton() {
        return this.cancelButton;
    }

    @Override
    public String toString() {
        return String.format("sender: %s, subject: %s, category: %s, contact details: %s, message: %s",
                this.nameTextField.getText(), this.subjectTextField.getText(), this.categoryComboBox.getSelectedItem(),
                this.contactDetailsTextField.getText(), this.messageTextArea.getText());
    }
}
