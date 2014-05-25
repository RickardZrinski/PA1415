package casino.views;

import shared.View;
import casino.events.MenuEvent;
import casino.events.MenuListener;
import shared.AuthenticationSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * @author  Dino Opijac
 * @since   18/05/14
 */
public class MenuView extends View<MenuListener> {
    private JLabel usernameLabel;
    private JLabel balanceLabel;
    private JComboBox<String> menu;
    private MenuEvent event = null;

    // Left and right panels
    private JPanel left  = new JPanel(new FlowLayout(FlowLayout.LEFT,  20, 15));
    private JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));

    public MenuView() {
        this.usernameLabel = new JLabel("User");
        this.balanceLabel  = new JLabel("Balance");
        this.menu = new JComboBox<>();

        this.event = new MenuEvent();
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
        this.menu.addItem("Menu Item 1");
        this.menu.addItem("Menu Item 2");
        this.menu.addItem("Menu Item 3");

        this.menu.addItemListener(this::itemStateChanged);

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
        if (e.getStateChange() == ItemEvent.DESELECTED)
            event.setDeselected(e.getItem());
        else {
            event.setSelected(e.getItem());
            this.getObservers().forEach(o -> o.menuItemChanged(event) );

            // Clear cache and reset for a new event
            event = new MenuEvent();
        }
    }
}
