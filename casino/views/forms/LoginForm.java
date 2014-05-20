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
        this.signInButton.setActionCommand("login");
        this.resetButton.setActionCommand("reset");
    }

    private void addComponents() {
        this.setLayout(new GridBagLayout());

        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");

        // Create a JPanel for the two grouped buttons
        JPanel buttons = new JPanel();
        buttons.add(this.signInButton);
        buttons.add(this.resetButton);

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

        //                                                                    x, y   %     width
        GridBagUtilities.makeCell(this, usernameLabel,              new Point(0, 0), 0.25, 1,    GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        GridBagUtilities.makeCell(this, this.usernameTextField,     new Point(1, 0), 0.75, 1,    GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
        GridBagUtilities.makeCell(this, passwordLabel,              new Point(0, 1), 0.25, 1,    GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        GridBagUtilities.makeCell(this, this.passwordPasswordField, new Point(1, 1), 0.75, 1,    GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
        GridBagUtilities.makeCell(this, buttons,                    new Point(1, 2), 0.75, 1,    GridBagConstraints.NONE, GridBagConstraints.WEST);
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
