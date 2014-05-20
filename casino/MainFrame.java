package casino;

import javax.swing.*;

/**
 * @author  Dino Opijac
 * @since   20/05/2014
 */
public class MainFrame {
    private static JFrame frame = new JFrame();

    private MainFrame() {}

    public static JFrame getInstance() {
        return MainFrame.frame;
    }
}
