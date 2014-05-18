package casino.views.form;

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
        Dimension size = this.usernameTextField.getPreferredSize();
        size.setSize(300, size.getHeight());

            this.usernameTextField.setPreferredSize(size);
        this.passwordPasswordField.setPreferredSize(size);
    }

    private GridBagConstraints fill(JComponent parent, JComponent component, int w, int x, int y, int constraint, int anchor) {
        return this.fill(parent, component, w, x, y, constraint, anchor, new GridBagConstraints());
    }

    private GridBagConstraints fill(JComponent parent, JComponent component, int w, int x, int y, int constraint, int anchor, GridBagConstraints constraints) {
        constraints.fill = constraint;
        constraints.gridwidth = w;
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.anchor = anchor;

        parent.add(component, constraints);

        return constraints;
    }

    private void addComponents() {
        this.setLayout(new GridBagLayout());

        this.fill(this, new JLabel("Username", JLabel.TRAILING), 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.EAST);
        this.fill(this, this.usernameTextField, 0, 1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);

        this.fill(this, new JLabel("Password", JLabel.TRAILING), 1, 0, 1, GridBagConstraints.LINE_START, GridBagConstraints.EAST);
        this.fill(this, this.passwordPasswordField, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);

        GridBagConstraints previous = this.fill(this, this.signInButton, 2, 1, 2, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
                                      this.fill(this, this.resetButton,  2, 4, 2, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST, previous);
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
