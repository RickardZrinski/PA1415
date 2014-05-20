package casino.controllers;

import casino.MainFrame;

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

        new AuthenticationController();
    }
}
