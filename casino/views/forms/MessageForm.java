package casino.views.forms;

import casino.MainFrame;
import utilities.ComponentUtilities;
import utilities.GridBagUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
        this.categoryComboBox  = new JComboBox<String>(new String[] {"Support", "Praise", "Feature request"});
        this.contactDetailsTextField = new JTextField();
        this.messageTextArea = new JTextArea();
        this.sendButton = new JButton("Send");
        this.cancelButton = new JButton("Cancel");
        this.configure();
        this.addComponents();
    }

    private void configure()
    {
        this.sendButton.setActionCommand("send");
        this.cancelButton.setActionCommand("cancel");
    }

    private void addComponents() {
        this.setLayout(new GridBagLayout());

        JLabel nameLabel = new JLabel("Full Name");
        JLabel subjectLabel = new JLabel("Subject");
        JLabel categoryLabel = new JLabel("Category");
        JLabel contactDetailsLabel = new JLabel("Contact Details");
        JLabel messageLabel = new JLabel("Message");
        JLabel contactDetailsInfoLabel = new JLabel("Where can we reach you? Leave a phone number or email address.");

        JPanel buttons = new JPanel();
        buttons.add(this.sendButton);
        buttons.add(this.cancelButton);


        GridBagUtilities.makeCell(this, nameLabel,              new Point(0, 0), 0.25, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
        GridBagUtilities.makeCell(this, this.nameTextField,     new Point(1, 0), 0.75, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);

        GridBagUtilities.makeCell(this, subjectLabel,           new Point(0, 1), 0.25, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
        GridBagUtilities.makeCell(this, this.subjectTextField,  new Point(1, 1), 0.27, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);

        GridBagUtilities.makeCell(this, categoryLabel,          new Point(0, 2), 0.25, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
        GridBagUtilities.makeCell(this, this.categoryComboBox,  new Point(1, 2), 0.50, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);

        GridBagUtilities.makeCell(this, contactDetailsLabel,            new Point(0, 3), 0.25, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
        GridBagUtilities.makeCell(this, this.contactDetailsTextField,   new Point(1, 3), 0.75, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);

        GridBagUtilities.makeCell(this, contactDetailsInfoLabel,   new Point(1, 4), 0.75, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);

        GridBagUtilities.makeCell(this, messageLabel,           new Point(0, 5), 0.25, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
        GridBagUtilities.makeCell(this, this.messageTextArea,   new Point(1, 5), 0.75, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);


        GridBagUtilities.makeCell(this, buttons,                new Point(1, 6), 0.75, 1, GridBagConstraints.NONE, GridBagConstraints.WEST);
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

    public void setActionListener(ActionListener listener) {
        this.sendButton.addActionListener(listener);
        this.cancelButton.addActionListener(listener);
    }

}
