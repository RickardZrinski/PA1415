package casino.views;

import shared.View;
import casino.events.LoginListener;
import casino.events.LoginResponse;
import casino.MainFrame;
import casino.events.LoginEvent;
import casino.views.forms.LoginForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author  Dino Opijac
 * @since   18/05/2014
 */
public class LoginView extends View<LoginListener> implements LoginResponse {
    private LoginForm form = new LoginForm();

    public LoginView() {
        this.configure();
        this.addComponents();

        MainFrame.getInstance().setTitle("Sign In");
    }

    private void configure() {
        this.setLayout(new FlowLayout());
        this.form.getSignInButton().addActionListener(this::signInAction);
        this.form.getResetButton().addActionListener(this::resetAction);
    }

    private void addComponents() {
        this.add(this.form);
    }

    private void signInAction(ActionEvent e) {
        this.getObservers().forEach(o -> o.loginPerformed(
                new LoginEvent( this.form.getUsernameTextField().getText(),
                                this.form.getPasswordPasswordField().getPassword())) );
    }

    private void resetAction(ActionEvent e) {
        this.form.getUsernameTextField().setText("");
        this.form.getPasswordPasswordField().setText("");
    }

    @Override
    public void loginSuccessful() {
        // The model has told us that everything checked out, tell the controller that we're done.
        this.getObservers().forEach(LoginListener::authorizationPerformed);
    }

    @Override
    public void loginUnsuccessful() {
        JOptionPane.showMessageDialog(null, "Your username or password was invalid. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
    }
}
