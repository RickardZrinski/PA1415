package casino.views;

import casino.AbstractView;
import casino.events.AuthenticationFormEvent;
import casino.AuthenticationListener;
import casino.views.form.LoginForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author  Dino Opijac
 * @since   18/05/2014
 */
public class AuthenticationView extends AbstractView<AuthenticationListener> implements ActionListener {
    private LoginForm form;

    public AuthenticationView() {
        this.form = new LoginForm();
        this.configure();
        this.addComponents();
    }

    private void configure() {
        this.form.setActionListener(this);
    }

    private void addComponents() {
        this.add(this.form);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("login"))
            this.fireEvent("authenticationPerformed", new AuthenticationFormEvent(this.form.getUsernameTextField().getText(), this.form.getPasswordPasswordField().getPassword()));
        else {
            this.form.getUsernameTextField().setText("");
            this.form.getPasswordPasswordField().setText("");
        }
    }
}
