package casino.controllers;

import casino.MainFrame;
import casino.events.MenuListener;
import shared.AuthenticationSession;
import shared.dao.DAOFactory;

import javax.swing.*;

/**
 * @author  Dino Opijac
 * @since   20/05/2014
 */
public class FrontController implements MenuListener {
    public static FrontController instance;

    public FrontController() {
        // Set up the parameters for the application
        JFrame frame = MainFrame.getInstance();

        frame.setSize(640, 480);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

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
