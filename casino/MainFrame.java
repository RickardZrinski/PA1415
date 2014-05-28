package casino;

import casino.views.MenuView;

import javax.swing.*;
import java.awt.*;

/**
 * @author  Dino Opijac
 * @since   20/05/2014
 */
public class MainFrame {
    private static JFrame frame = new JFrame();
    private static MainFrame instance = new MainFrame();

    private CardLayout layout = new CardLayout();
    private JPanel panel = new JPanel();

    private MainFrame() {
        frame.setSize(640, 480);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        this.panel.setLayout(this.layout);
        frame.getContentPane().add(this.panel, BorderLayout.PAGE_START);
    }

    /**
     * @return retrieves an instance of the MainFrame object
     */
    public static MainFrame getInstance() {
        return MainFrame.instance;
    }

    /**
     * Sets a title of the frame
     * @param   newTitle    the new title to set
     */
    public void setTitle(String newTitle) {
        frame.setTitle(newTitle);
    }

    /**
     * @return retrieves an instance of the frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Adds components to the view area
     * @param   component   the component to add to the view area
     */
    public void add(Component component) {
        this.panel.add(component, component.getClass().getSimpleName());
        this.layout.show(this.panel, component.getClass().getSimpleName());
    }
}
