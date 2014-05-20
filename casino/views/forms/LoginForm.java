package casino.views.forms;

import utilities.ComponentUtilities;
import utilities.GridBagUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginForm extends JPanel {
    private JTextField usernameTextField;
    private JPasswordField passwordPasswordField;
    private JButton signInButton;
    private JButton resetButton;

    public LoginForm() {
        // Form elements
        this.usernameTextField = new JTextField("abc");
        this.passwordPasswordField = new JPasswordField("pass");
        this.signInButton  = new JButton("Sign In");
        this.resetButton   = new JButton("Reset");
        this.configure();
        this.addComponents();
    }

    private void configure()
    {
        ComponentUtilities.setWidth(50, this.usernameTextField);
        ComponentUtilities.setWidth(50, this.passwordPasswordField);

        this.signInButton.setActionCommand("login");
        this.resetButton.setActionCommand("reset");
    }

    private void addComponents() {
        this.setLayout(new GridBagLayout());

        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");

        ComponentUtilities.setWidth(100, usernameLabel);
        ComponentUtilities.setWidth(100, passwordLabel);

        GridBagUtilities.makeCell(this, usernameLabel, new Point(0, 0), 2, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
        GridBagUtilities.makeCell(this, this.usernameTextField, new Point(2, 0), 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);

        GridBagUtilities.makeCell(this, passwordLabel, new Point(0, 1), 2, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
        GridBagUtilities.makeCell(this, this.passwordPasswordField, new Point(2, 1), 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);

        GridBagUtilities.makeCell(this, this.signInButton, new Point(2, 2), 2);
        GridBagUtilities.makeCell(this, this.resetButton, new Point(4, 2), 2);
    }

    public JTextField getUsernameTextField() {
        return this.usernameTextField;
    }

    public JPasswordField getPasswordPasswordField() {
        return this.passwordPasswordField;
    }

    public void setActionListener(ActionListener listener) {
        this.signInButton.addActionListener(listener);
        this.resetButton.addActionListener(listener);
    }
}
