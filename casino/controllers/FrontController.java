package casino.controllers;

import casino.MainFrame;
import shared.AuthenticationSession;
import shared.dao.DAOFactory;

import javax.swing.*;

/**
 * @author  Dino Opijac
 * @since   20/05/2014
 */
public class FrontController {

    public FrontController() {
        // Set up the parameters for the application
        JFrame frame = MainFrame.getInstance();

        frame.setSize(640, 480);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        try {
            // Start a fake AuthenticationSession
            AuthenticationSession.start(DAOFactory.getUserDao().get("dino"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // new GameController();
        // new AuthenticationController();
        new TransactionController();
        // new MessageController();

    }
}
