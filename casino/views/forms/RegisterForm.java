package casino.views.forms;

import utilities.ComponentUtilities;
import utilities.GridBagUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author  Dino Opijac
 * @since   27/05/2014
 */
public class RegisterForm extends JPanel {
    private JTextField usernameField = new JTextField();
    private JTextField firstNameField= new JTextField();
    private JTextField lastNameField= new JTextField();
    private JFormattedTextField ssnField= new JFormattedTextField();
    private JTextField emailField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JPasswordField passwordRepeatField = new JPasswordField();

    private JButton confirmButton = new JButton("Register");
    private JButton cancelButton = new JButton("Cancel");
    private JButton resetButton = new JButton("Reset");


    public RegisterForm() {
        this.configure();
        this.addComponents();
    }

    private void configure() {
        this.setLayout(new GridBagLayout());

        this.resetButton.addActionListener(this::resetAction);
    }

    private void addComponents() {
        GridBagUtilities.makeCell(this, new JLabel("Username"),                 new Point(0, 0), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.usernameField,                     new Point(1, 0), 0.8, 1, GridBagConstraints.HORIZONTAL);

        // Passwords
        GridBagUtilities.makeCell(this, new JLabel("Password"),                 new Point(0, 1), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.passwordField,                     new Point(1, 1), 0.8, 1, GridBagConstraints.HORIZONTAL);

        GridBagUtilities.makeCell(this, new JLabel("Repeat password"),          new Point(0, 2), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.passwordRepeatField,               new Point(1, 2), 0.8, 1, GridBagConstraints.HORIZONTAL);

        // Create empty space
        GridBagUtilities.makeCell(this, new JLabel(" "),                        new Point(0, 3), 1, 2, GridBagConstraints.HORIZONTAL);


        GridBagUtilities.makeCell(this, new JLabel("First Name"),              new Point(0, 4), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.firstNameField,                   new Point(1, 4), 0.8, 1, GridBagConstraints.HORIZONTAL);

        GridBagUtilities.makeCell(this, new JLabel("Last Name"),               new Point(0, 5), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.lastNameField,                    new Point(1, 5), 0.8, 1, GridBagConstraints.HORIZONTAL);

        GridBagUtilities.makeCell(this, new JLabel("SSN"),                     new Point(0, 6), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.ssnField,                         new Point(1, 6), 0.8, 1, GridBagConstraints.HORIZONTAL);

        // Create empty space
        GridBagUtilities.makeCell(this, new JLabel(" "),                       new Point(0, 7), 1, 2, GridBagConstraints.HORIZONTAL);

        GridBagUtilities.makeCell(this, new JLabel("Email"),                    new Point(0, 8), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.emailField,                        new Point(1, 8), 0.8, 1, GridBagConstraints.HORIZONTAL);


        GridBagUtilities.makeCell(this, ComponentUtilities.flow(FlowLayout.LEFT, this.confirmButton, this.resetButton, this.cancelButton),
                                                                    new Point(1, 10), 1, 1, GridBagConstraints.HORIZONTAL);

    }

    /**
     * Clears all the fields.
     *
     * @see     RegisterForm#clear()
     * @param e the action event (if any)
     */
    private void resetAction(ActionEvent e) {
        this.clear();
    }

    /**
     * Clears all the textfield elements
     */
    public void clear() {
        this.usernameField.setText("");
        this.firstNameField.setText("");
        this.lastNameField.setText("");
        this.ssnField.setText("");
        this.emailField.setText("");
        this.passwordField.setText("");
        this.passwordRepeatField.setText("");
    }

    public JTextField getUsernameField() {
        return this.usernameField;
    }

    public JTextField getFirstNameField() {
        return this.firstNameField;
    }

    public JTextField getLastNameField() {
        return this.lastNameField;
    }

    public JFormattedTextField getSsnField() {
        return this.ssnField;
    }

    public JTextField getEmailField() {
        return this.emailField;
    }

    public JPasswordField getPasswordField() {
        return this.passwordField;
    }

    public JPasswordField getPasswordRepeatField() {
        return this.passwordRepeatField;
    }

    public JButton getConfirmButton() {
        return this.confirmButton;
    }

    public JButton getCancelButton() {
        return this.cancelButton;
    }

    public JButton getResetButton() {
        return this.resetButton;
    }
}
