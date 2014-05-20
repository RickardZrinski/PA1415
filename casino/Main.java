package casino;

import casino.controllers.FrontController;
import javax.swing.SwingUtilities;

/**
 * @author  Dino Opijac
 * @since   18/05/2014
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FrontController::new);
    }
}
