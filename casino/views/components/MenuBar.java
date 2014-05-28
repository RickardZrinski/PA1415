package casino.views.components;

import casino.controllers.FrontController;
import shared.View;
import casino.events.MenuListener;
import shared.AuthenticationSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * @author  Dino Opijac
 * @since   18/05/14
 */
public class MenuBar extends View<MenuListener> {
    private JLabel usernameLabel;
    private JLabel balanceLabel;
    private JComboBox<String> menu;

    // Left and right panels
    private JPanel left  = new JPanel(new FlowLayout(FlowLayout.LEFT,  20, 15));
    private JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));

    public MenuBar() {
        this.usernameLabel = new JLabel("User");
        this.balanceLabel  = new JLabel("Balance");
        this.menu = new JComboBox<>();
        this.configure();
        this.addComponents();
    }

    private void configure() {
        try {
            // Set the user in the menu
            this.usernameLabel.setText(String.format("Player: %s", AuthenticationSession.getInstance().getUser().getUsername()));
            this.balanceLabel.setText(String.format("Balance: %f", AuthenticationSession.getInstance().getUser().getAccount().getBalance()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Only temporary
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        // Add items to the combo box
        this.menu.addItem("Menu");
        this.menu.addItem("Games");
        this.menu.addItem("Deposit");
        this.menu.addItem("Withdraw");
        this.menu.addItem("Message");
        this.menu.addItem("Sign Out");

        this.menu.addItemListener(this::itemStateChanged);

        // Subscribe this object to the FrontController
        this.subscribe(FrontController.getInstance());

        // Make the left and right panels opaque
        this.left.setOpaque(false);
        this.right.setOpaque(false);
    }

    private void addComponents() {
        this.left.add(this.usernameLabel);
        this.left.add(this.balanceLabel);
        this.right.add(this.menu);

        this.add(this.left, BorderLayout.LINE_START);
        this.add(this.right, BorderLayout.LINE_END);
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public JLabel getBalanceLabel() {
        return balanceLabel;
    }

    public JComboBox<String> getMenu() {
        return menu;
    }

    public void addMenuItem(String item) {
        this.menu.addItem(item);
    }

    public void removeMenuItem(String item) {
        this.menu.removeItem(item);
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            switch(String.valueOf(e.getItem())) {
                case "Games":       this.getObservers().forEach(MenuListener::gameAction);      break;
                case "Deposit":     this.getObservers().forEach(MenuListener::depositAction);   break;
                case "Withdraw":    this.getObservers().forEach(MenuListener::withdrawAction);  break;
                case "Message":     this.getObservers().forEach(MenuListener::messageAction);   break;
                case "Sign Out":    this.getObservers().forEach(MenuListener::signOutAction);   break;
            }
        }
    }
}
