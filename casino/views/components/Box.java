package casino.views.components;

import javax.swing.*;
import java.awt.*;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class Box extends JPanel {
    private JToggleButton button;
    private JLabel label = new JLabel();

    /**
     * Disabled default constructor
     */
    private Box() {}

    /**
     * Creates a Box with a image, leaving the label and dimension to its
     * default values
     *
     * @param buttonImage   the path to the image to be displayed on the button
     */
    public Box(String buttonImage) {
        this(buttonImage, null, null);
    }

    /**
     * Creates a Box with a image and label
     *
     * @param buttonImage   the path to the image to be displayed on the button
     * @param label         the label below the button
     */
    public Box(String buttonImage, String label) {
        this(buttonImage, label, null);
    }

    /**
     * Creates a Box with a image and a specified dimension
     *
     * @param buttonImage   the path to the image to be displayed on the button
     * @param dimension     the desired size of the panel, i.e button and label
     */
    public Box(String buttonImage, Dimension dimension) {
        this(buttonImage, null, dimension);
    }

    /**
     * Creates a Box with a image, label and a specified dimension
     *
     * @param buttonImage   the path to the image to be displayed on the button
     * @param label         the label below the button
     * @param dimension     the desired size of the panel, i.e button and label
     */
    public Box(String buttonImage, String label, Dimension dimension) {
        if (dimension != null)
            this.setSize(dimension);

        this.button = new JToggleButton(new ImageIcon(buttonImage));
        this.label = (label == null) ? new JLabel() : new JLabel(label);

        this.configure();
        this.addComponents();
    }

    private void configure() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.button.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.label.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void addComponents() {
        this.add(this.button);
        this.add(this.label);
    }

    public JLabel getLabel() {
        return this.label;
    }

    public JToggleButton getButton() {
        return this.button;
    }

    /**
     * Creates multiple Boxes without any text
     * @param   amount  amount of boxes to produce
     * @return          a JPanel with all boxes
     */
    public static JPanel create(int amount) {
        return Box.create(amount, null);
    }

    /**
     * Creates multiple Boxes and inserts a label underneath them
     * @param   amount  amount of boxes to produce
     * @param   text    a text under each box
     * @return          a JPanel with all boxes
     */
    public static JPanel create(int amount, String text) {
        JPanel panel = new JPanel(new GridLayout( 0, 5 ));

        int j = 1;
        for (int i = 0; i <= amount; i++) {
            if ((i % 6) == 0)
                j = 1;

            panel.add( new Box(String.format("shared/resources/%d.png", j++), text, new Dimension(50, 100) ) );
        }

        return panel;
    }
}
