package casino.controllers;

import casino.events.MenuListener;

/**
 * @author  Dino Opijac
 * @since   20/05/2014
 */
public class FrontController implements MenuListener {
    public static FrontController instance;

    public FrontController() {
        // Always start the AuthenticationController using its sign in action.
        (new AuthenticationController()).signInAction();

        instance = this;
    }

    public static FrontController getInstance() {
        return FrontController.instance;
    }

    @Override
    public void gameAction() {
        (new GameController()).gameAction();
    }

    @Override
    public void depositAction() {
        (new TransactionController()).depositAction();
    }

    @Override
    public void withdrawAction() {
        (new TransactionController()).withdrawAction();
    }

    @Override
    public void messageAction() {
        (new MessageController()).messageAction();
    }

    @Override
    public void signOutAction() {
        (new AuthenticationController()).signOutAction();
    }
}
