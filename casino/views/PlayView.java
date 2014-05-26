package casino.views;

import casino.views.components.Box;
import utilities.ComponentUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author  Dino Opijac
 * @since   22/05/2014
 */
public class PlayView extends JPanel {
    private JButton tossButton = new JButton("Toss");
    private JButton resetButton = new JButton("Reset");
    private JLabel progressText = new JLabel("Remaining Throws: 5");
    private JPanel dicePanel;

    public PlayView() {
        this.dicePanel = new JPanel(new GridLayout( 0, 5 ));
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
        this.add(ComponentUtilities.flow(FlowLayout.CENTER, this.progressText), BorderLayout.PAGE_START);
        this.add(ComponentUtilities.flow(FlowLayout.CENTER, this.tossButton, this.resetButton), BorderLayout.PAGE_END);
        this.add(this.dicePanel, BorderLayout.CENTER);
    }

    public void addBoxes(int number, ActionListener listener) {
        for (int i = 0; i < number; i++) {
            Box box = new Box("shared/resources/1.png");
            box.getButton().addActionListener(listener);
            this.add(box);
        }
    }

    public void updateBox(int index, int face) {
        this.getBoxButton(index).setIcon(new ImageIcon(String.format("shared/resources/%d.png", face)));
        this.dicePanel.repaint();
    }

    public JToggleButton getBoxButton(int index) {
        return ((Box)this.dicePanel.getComponent(index)).getButton();
    }

    public JButton getTossButton() {
        return this.tossButton;
    }

    public JPanel getDicePanel() {
        return this.dicePanel;
    }
}
