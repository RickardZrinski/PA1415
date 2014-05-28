package casino.views.components;

import casino.controllers.FrontController;
import shared.View;
import casino.events.MenuListener;
import shared.AuthenticationSession;
import shared.users.User;

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
        this.usernameLabel.setForeground(Color.WHITE);
        this.balanceLabel  = new JLabel("Balance");
        this.balanceLabel.setForeground(Color.WHITE);
        this.menu = new JComboBox<>();
        this.configure();
        this.addComponents();
    }

    private void configure() {
        try {
            // Set the user in the menu
            User user = AuthenticationSession.getInstance().getUser();

            this.usernameLabel.setText(String.format("Player: %s", user.getUsername()));

            if (user.getRole().getName().equals("Player"))
                this.setBalanceLabelValue(user.getAccount().getBalance());
            else
                this.setTrialsLabelValue(user.getNumberOfTrials());

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

    /**
     * Sets the text "Balance: <number>:-"
     * @param value the balance amount
     */
    public void setBalanceLabelValue(double value) {
        this.balanceLabel.setText(String.format("Balance: %1$,.2f:-", value));
    }

    /**
     * Sets the text, "Remaining Trials: <trials>"
     * @param trials the amount of trials left
     */
    public void setTrialsLabelValue(int trials) {
        this.balanceLabel.setText(String.format("Remaining Trials: %d", trials));
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
