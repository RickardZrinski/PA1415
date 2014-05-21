package casino.controllers;

import casino.events.LoginListener;
import casino.MainFrame;
import casino.events.*;
import casino.models.LoginModel;
import casino.views.LoginView;
import shared.AuthenticationSession;

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
            this.model.authenticate(e);
        else
            this.view.loginUnsuccessful();
    }

    @Override
    public void authorizationPerformed() {
        // User is finally logged in
        try {
            System.out.println("User is now logged in!:");
            System.out.println(AuthenticationSession.getInstance().getUser());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logoutPerformed() {
        // User wishes to log out
    }
}
