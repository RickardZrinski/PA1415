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
    private final String IMAGE_SOURCE = "shared/resources/%d.png";
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

    /**
     * Clears the view from all components and effectively resets it
     */
    public void clear() {
        this.dicePanel.removeAll();
    }

    /**
     * Adds new pushable boxes to the view
     * @param number    the number of boxes to add
     * @param listener  the listener for which these boxes will invoke
     */
    public void addBoxes(int number, ActionListener listener) {
        for (int i = 0; i < number; i++) {
            Box box = new Box(String.format(IMAGE_SOURCE, 1));
            box.getButton().addActionListener(listener);
            this.dicePanel.add(box);
        }
    }

    /**
     * Updates the value of a given box
     * @param index the index of the box to update
     * @param face  the new face of the box
     */
    public void updateBox(int index, int face) {
        this.getBoxButton(index).setIcon(new ImageIcon(String.format(IMAGE_SOURCE, face)));
        this.dicePanel.repaint();
    }


    /**
     * Updates the progress of the game
     * @param number    the number of "throws" left in the game
     */
    public void updateProgress(int number) {
        this.progressText.setText(String.format("Remaining Throws: %d", number));
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
