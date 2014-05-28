package casino.controllers;

import casino.events.LoginListener;
import casino.MainFrame;
import casino.events.*;
import casino.models.LoginModel;
import casino.models.RegistrationModel;
import casino.views.LoginView;
import shared.AuthenticationSession;

/**
 * @author  Dino Opijac
 * @since   18/05/14
 */
public class AuthenticationController implements LoginListener {
    private LoginModel loginModel;
    private RegistrationModel registrationModel = new RegistrationModel();
    private LoginView view = new LoginView();

    public AuthenticationController() {}

    public void signInAction() {
        this.loginModel = new LoginModel();
        this.registrationModel = new RegistrationModel();
        this.view = new LoginView();

        this.view.subscribe(this);
        this.loginModel.subscribe(this.view);
        this.registrationModel.subscribe(this.view);

        MainFrame.getInstance().add(this.view);
    }

    public void signOutAction() {
        // Stop the authenticationSession
        AuthenticationSession.stop();

        // Go to sign in action
        this.signInAction();
    }

    @Override
    public void loginPerformed(LoginEvent e) {
        // If the event is valid, send it to the model, else
        // just respond directly back to the view
        if (e.isValid())
            this.loginModel.authenticate(e);
        else
            this.view.loginUnsuccessful();
    }

    @Override
    public void authorizationPerformed() {
        // User is finally logged in
        try {
            System.out.println("User is now logged in!:");
            System.out.println(AuthenticationSession.getInstance().getUser());

            FrontController.getInstance().gameAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logoutPerformed() {
        // User wishes to log out
    }

    @Override
    public void registrationPerformed(RegistrationEvent e) {
        this.registrationModel.create(e);
    }
}
