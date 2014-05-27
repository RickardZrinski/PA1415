package casino.views;

import casino.controllers.FrontController;
import casino.events.*;
import casino.views.forms.RegisterForm;
import shared.View;
import casino.MainFrame;
import casino.views.forms.LoginForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

/**
 * @author  Dino Opijac
 * @since   18/05/2014
 */
public class LoginView extends View<LoginListener> implements LoginResponse, RegistrationResponse {
    private LoginForm form = new LoginForm();
    private RegisterForm registerForm = new RegisterForm();
    private CardLayout cards = new CardLayout();
    private JPanel card = new JPanel(cards);

    public LoginView() {
        this.configure();
        this.addComponents();

        MainFrame.getInstance().setTitle("Sign In");
    }

    private void configure() {
        // this.setLayout(new FlowLayout());
        this.form.getSignInButton().addActionListener(this::signInAction);
        this.form.getResetButton().addActionListener(this::resetAction);
        this.form.getRegisterButton().addActionListener(this::registerFormAction);

        this.registerForm.getConfirmButton().addActionListener(this::confirmRegistrationAction);
        this.registerForm.getCancelButton().addActionListener(this::cancelRegisterFormAction);
    }

    private void addComponents() {
        this.card.add(this.form, "1");
        this.card.add(this.registerForm, "2");

        this.add(this.card);
    }

    private void registerFormAction(ActionEvent e) {
        this.cards.show(this.card, "2");
    }

    private void cancelRegisterFormAction(ActionEvent e) {
        this.cards.show(this.card, "1");
    }

    private void signInAction(ActionEvent e) {
        this.getObservers().forEach(o -> o.loginPerformed(
                new LoginEvent( this.form.getUsernameTextField().getText(),
                                this.form.getPasswordPasswordField().getPassword())) );
    }

    private void resetAction(ActionEvent e) {
        this.form.getUsernameTextField().setText("");
        this.form.getPasswordPasswordField().setText("");

        //FrontController.getInstance().showGameController();
    }

    private void confirmRegistrationAction(ActionEvent e) {
        String username = this.registerForm.getUsernameField().getText();
        String firstName = this.registerForm.getFirstNameField().getText();
        String lastName = this.registerForm.getLastNameField().getText();
        int ssn = new Integer(this.registerForm.getSsnField().getText());
        String email = this.registerForm.getEmailField().getText();
        char[] password1 = this.registerForm.getPasswordField().getPassword();
        char[] password2 = this.registerForm.getPasswordRepeatField().getPassword();

        // Check if the both passwords match
        if (Arrays.equals(password1, password2))
            this.getObservers().forEach(o -> o.registrationPerformed(new RegistrationEvent(username, firstName, lastName, password1, ssn, email)));
        else
            this.registrationUnsuccessful(Registration.PASSWORD_NO_MATCH);
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

    @Override
    public void registrationSuccessful() {
        JOptionPane.showMessageDialog(null, "Registration succeeded! Please log in with the details you just entered.", "Registration Succeeded", JOptionPane.INFORMATION_MESSAGE);

        // Show the correct card and clear the register form
        this.cards.show(this.card, "1");

        this.registerForm.clear();
    }

    @Override
    public void registrationUnsuccessful(Registration reason) {
        String reasoning = "";

        switch(reason) {
            case OCCUPIED:
                reasoning = "This username is already taken";
                break;

            case PASSWORD_NO_MATCH:
                reasoning = "Passwords do not match";
                break;

            default:
                reasoning = "Invalid details given";
                break;
        }

        JOptionPane.showMessageDialog(null, reasoning, "Registration Failed", JOptionPane.ERROR_MESSAGE);
    }
}
