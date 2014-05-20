package casino.controllers;

import casino.events.LoginListener;
import casino.MainFrame;
import casino.events.*;
import casino.models.LoginModel;
import casino.views.LoginView;

import java.awt.*;

/**
 * @author  Dino Opijac
 * @since   18/05/14
 */
public class AuthenticationController implements LoginListener {
    private LoginModel model = new LoginModel();
    private LoginView view = new LoginView();

    public AuthenticationController() {
        // Register the view to the controller
        // Register the model to the view
        this.view.subscribe(this);
        this.model.subscribe(this.view);

        MainFrame.getInstance().add(this.view, BorderLayout.CENTER);
    }

    @Override
    public void loginPerformed(LoginEvent e) {
        // If the event is valid, send it to the model, else
        // just respond directly back to the view
        if (e.isValid())
            this.model.doSomething();
        else
            this.view.loginResponse(false);
    }

    @Override
    public void logoutPerformed() {
        // User wishes to log out
    }
}
