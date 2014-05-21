package casino.views;

import casino.AbstractView;
import casino.events.MenuEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author  Dino Opijac
 * @since   18/05/14
 */
public class MenuView extends AbstractView implements ItemListener {
    private JLabel usernameLabel;
    private JLabel balanceLabel;
    private JComboBox<String> menu;
    private MenuEvent event = null;

    // Left and right panels
    private JPanel left  = new JPanel(new FlowLayout(FlowLayout.LEFT,  20, 15));
    private JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));

    public MenuView() {
        this.usernameLabel = new JLabel("User");
        this.balanceLabel = new JLabel("Balance");
        this.menu = new JComboBox<>();

        this.event = new MenuEvent();
        this.configure();
        this.addComponents();
    }

    private void configure() {
        // Only temporary
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        // Add items to the combo box
        this.menu.addItem("Menu");
        this.menu.addItem("Menu Item 1");
        this.menu.addItem("Menu Item 2");
        this.menu.addItem("Menu Item 3");

        this.menu.addItemListener(this);

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

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.DESELECTED)
            event.setDeselected(e.getItem());
        else {
            event.setSelected(e.getItem());
            this.notify("menuItemChanged", event);

            // Clear cache and reset for a new event
            event = new MenuEvent();
        }
    }
}
