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
    }

    private void configure() {
        this.setLayout(new BorderLayout());
        this.form.setActionListener(this);
    }

    private void addComponents() {
        this.add(this.form, BorderLayout.CENTER);
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
    public void loginResponse(Boolean response) {
        if (response) {
            JOptionPane.showMessageDialog(null, "You logged in. The frame will become blank.", "Login successful", JOptionPane.INFORMATION_MESSAGE);

            MainFrame.getInstance().getContentPane().removeAll();
            MainFrame.getInstance().getContentPane().repaint();
        } else {
            JOptionPane.showMessageDialog(null, "Your username or password was invalid. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
