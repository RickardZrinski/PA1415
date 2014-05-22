package casino.views;

import casino.MainFrame;
import casino.views.components.Box;
import utilities.ComponentUtilities;

import javax.swing.*;
import java.awt.*;

/**
 * @author  Dino Opijac
 * @since   22/05/2014
 */
public class PlayView extends JPanel {
    private JButton tossButton = new JButton("Toss");
    private JButton resetButton = new JButton("Reset");
    private JLabel progressText = new JLabel("Remaining Throws: 5");
    private JPanel dicePanel;

    public PlayView(int die) {
        this.dicePanel = Box.create(die, "Test");

        this.configure();
        this.addComponents();
    }

    private void configure() {
        this.setLayout(new BorderLayout());

        // The reset button should be disabled until the user
        // has toggled Box (dice)
        this.resetButton.setEnabled(false);
    }

    private void addComponents() {
        this.add(ComponentUtilities.group(FlowLayout.CENTER, this.progressText), BorderLayout.PAGE_START);
        this.add(ComponentUtilities.group(FlowLayout.CENTER, this.tossButton, this.resetButton), BorderLayout.PAGE_END);
        this.add(this.dicePanel, BorderLayout.CENTER);
    }

    public JButton getTossButton() {
        return this.tossButton;
    }

    public JPanel getDicePanel() {
        return this.dicePanel;
    }
}
