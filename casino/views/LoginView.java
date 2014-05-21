package casino.views;

import casino.AbstractView;
import casino.events.LoginResponseListener;
import casino.MainFrame;
import casino.events.LoginEvent;
import casino.views.forms.LoginForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author  Dino Opijac
 * @since   18/05/2014
 */
public class LoginView extends AbstractView implements ActionListener, LoginResponseListener {
    private LoginForm form = new LoginForm();

    public LoginView() {
        this.configure();
        this.addComponents();

        MainFrame.getInstance().setTitle("Sign In");
    }

    private void configure() {
        this.setLayout(new FlowLayout());
        this.form.setActionListener(this);
    }

    private void addComponents() {
        this.add(this.form);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("login"))
            this.notify("loginPerformed", new LoginEvent(this.form.getUsernameTextField().getText(), this.form.getPasswordPasswordField().getPassword()));
        else {
            this.form.getUsernameTextField().setText("");
            this.form.getPasswordPasswordField().setText("");
        }
    }

    @Override
    public void loginSuccessful() {
        // The model has told us that everything checked out, tell the controller that we're done.
        this.notify("authorizationPerformed");
    }

    @Override
    public void loginUnsuccessful() {
        JOptionPane.showMessageDialog(null, "Your username or password was invalid. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
    }
}
