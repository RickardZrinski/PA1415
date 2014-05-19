package casino.controllers;

import casino.events.*;
import casino.AuthenticationListener;
import casino.MenuListener;
import casino.views.AuthenticationView;
import casino.views.MenuView;

import javax.swing.*;
import java.awt.*;

/**
 * @author  Dino Opijac
 * @since   18/05/14
 */
public class Controller implements AuthenticationListener, MenuListener {
    private AuthenticationView auth = new AuthenticationView();
    private MenuView menu = new MenuView();
    private JFrame frame = new JFrame();

    public Controller() {
        // Register this controller with the event the view emits.
        this.auth.addListener(this);
        this.menu.addListener(this);

        // This should not be here. Only for testing purposes.
        this.frame.add(this.menu, BorderLayout.PAGE_START);
        this.frame.add(this.auth, BorderLayout.CENTER);

        this.frame.setSize(640, 480);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);

        this.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.frame.setVisible(true);
    }

    @Override
    public void authenticationPerformed(AuthenticationFormEvent e) {
        System.out.println(String.format("Controller received:\n%s", e));
    }

    @Override
    public void menuItemChanged(MenuEvent e) {
        System.out.println(String.format("Controller received:\n%s", e));
    }
}
