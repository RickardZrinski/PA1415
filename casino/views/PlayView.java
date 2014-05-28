package casino.views;

import casino.views.components.Box;
import utilities.ComponentUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author  Dino Opijac
 * @since   22/05/2014
 */
public class PlayView extends JPanel {
    private final String IMAGE_SOURCE = "shared/resources/%d.png";
    private JButton tossButton = new JButton("Toss");
    private JButton finishButton = new JButton("Finish");
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
       // this.resetButton.setEnabled(false);

        // Make the finish button hidden
        this.finishButton.setVisible(false);
    }

    private void addComponents() {
        this.add(ComponentUtilities.flow(FlowLayout.CENTER, this.progressText), BorderLayout.PAGE_START);
        this.add(ComponentUtilities.flow(FlowLayout.CENTER, this.finishButton, this.tossButton), BorderLayout.PAGE_END);
        this.add(this.dicePanel, BorderLayout.CENTER);
    }

    /**
     * Sets the enabled state for the box buttons. Set to 'true'
     * for enabling the buttons and 'false' for disabling
     *
     * @param value     boolean value for the state
     */
    private void setEnabledStateForBoxButtons(boolean value) {
        for(Component c: this.dicePanel.getComponents()) {
            if (c instanceof Box) {
                ((Box) c).getButton().setEnabled(value);
            }
        }
    }

    /**
     * Enables all box buttons
     * @see PlayView#setEnabledStateForBoxButtons(boolean)
     */
    public void enableBoxButtons() {
        this.setEnabledStateForBoxButtons(true);
    }

    /**
     * Disables all box buttons
     * @see PlayView#setEnabledStateForBoxButtons(boolean)
     */
    public void disableBoxButtons() {
        this.setEnabledStateForBoxButtons(false);
    }

    /**
     * Clears the view from all components and effectively resets it
     */
    private void clear() {
        this.dicePanel.removeAll();
    }

    /**
     * Adds new pushable boxes to the view
     * @param number    the number of boxes to add
     * @param listener  the listener for which these boxes will invoke
     */
    public void addBoxes(int number, ActionListener listener) {
        // Remove all old boxes.
        this.clear();

        for (int i = 0; i < number; i++) {
            Box box = new Box(String.format(IMAGE_SOURCE, 1));

            // Add button listeners and its identifier
            box.getButton().addActionListener(listener);
            box.getButton().setActionCommand(String.valueOf(i));

            // Attach this to the panel
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

    /**
     * Toggles the finish and toss buttons (shows/hides)
     */
    public void toggleButtons() {
        ComponentUtilities.toggleComponent(this.finishButton);
        ComponentUtilities.toggleComponent(this.tossButton);
    }

    /**
     * Retrieves an box button at the desired index
     * @param index     the index of the box to retrieve
     * @return          the box button at the desired index
     */
    public JToggleButton getBoxButton(int index) {
        return ((Box)this.dicePanel.getComponent(index)).getButton();
    }

    /**
     * @return retrieves the button labeled 'Finish'
     */
    public JButton getFinishButton() {
        return this.finishButton;
    }

    /**
     * @return retrieves the button labeled 'Toss'
     */
    public JButton getTossButton() {
        return this.tossButton;
    }

    public JPanel getDicePanel() {
        return this.dicePanel;
    }
}
