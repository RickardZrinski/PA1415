package casino.views.forms;

import utilities.ComponentUtilities;
import utilities.GridBagUtilities;

import javax.swing.*;
import java.awt.*;

/**
 * @author  Dino Opijac
 * @since   18/05/2014
 */
public class LoginForm extends JPanel {
    private JTextField usernameTextField = new JTextField();
    private JPasswordField passwordPasswordField = new JPasswordField();
    private JButton signInButton = new JButton("Sign In");
    private JButton resetButton = new JButton("Reset");
    private JButton registerButton = new JButton("Register");

    public LoginForm() {
        this.configure();
        this.addComponents();
    }

    private void configure() {
        this.setLayout(new GridBagLayout());

        // Set the action commands of each event, this way we know what
        // button was pressed
        this.signInButton.setActionCommand("login");
        this.resetButton.setActionCommand("reset");
    }

    private void addComponents() {
        // Create a JPanel for the two grouped buttons
        JPanel buttons = ComponentUtilities.flow(FlowLayout.LEFT, this.signInButton, this.resetButton, this.registerButton);

        /**
         * GridBagLayout works with grids. The point is a coordinate inside a table, for example:
         *  (x,y)
         * +-----+-----+-----+
         * |(0,0)|(1,0)|(2,0)|
         * +-----+-----+-----+
         * |(0,1)|(1,1)|(2,1)|
         * +-----+-----+-----+
         *
         * The percentage (labeled % below) is the width of the column. Columns in the above table should have 0.33 (since 100% table / 3 columns = 33%)
         * The width is how many positions a column should occupy. In the example below, the second row occupies 3 columns and has Point (0, 1)
         * *  (x,y)
         * +-----+-----+-----+
         * |(0,0)|(1,0)|(2,0)|
         * +-----+-----+-----+
         * |(0,1)            | (1,1) and (2,1) are replaced entirely with (0,1), since (0,1) has width 3
         * +-----+-----+-----+
         */

        //                                                                    x, y   %    width
        GridBagUtilities.makeCell(this, new JLabel("Username"),     new Point(0, 0), 0.2, 1,    GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.usernameTextField,     new Point(1, 0), 0.8, 1,    GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, new JLabel("Password"),     new Point(0, 1), 0.2, 1,    GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.passwordPasswordField, new Point(1, 1), 0.8, 1,    GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, buttons,                    new Point(1, 2), 1,   2,    GridBagConstraints.HORIZONTAL);
    }

    public JTextField getUsernameTextField() {
        return this.usernameTextField;
    }

    public JPasswordField getPasswordPasswordField() {
        return this.passwordPasswordField;
    }

    public JButton getSignInButton() {
        return signInButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JButton getRegisterButton() {
        return this.registerButton;
    }

    /**
     * Hides the registration button, it might not be needed (admin interface for example)
     */
    public void hideRegisterButton() {
        this.registerButton.setVisible(false);
    }

    @Override
    public String toString() {
        return String.format("username: %s, password: %s",
                this.usernameTextField.getText(), String.valueOf(this.passwordPasswordField.getPassword()));
    }
}
