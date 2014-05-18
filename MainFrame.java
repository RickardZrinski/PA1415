import javax.swing.*;
import java.awt.*;

/**
 * @author  Dino Opijac
 * @since   14/05/14
 */
public class MainFrame extends JFrame {
    private JPanel bar;
    private JLabel label;
    private JComboBox<String> menu;

    public MainFrame() {
        this.bar = new JPanel();
        this.label = new JLabel("Some Text");
        this.menu = new JComboBox<>();

        this.configure();
        this.addComponents();
    }

    private void configure() {
        // Only temporary
        this.bar.setBackground(Color.DARK_GRAY);
        this.bar.setLayout(new BoxLayout(this.bar, BoxLayout.LINE_AXIS));

        // Add items to the combo box
        this.menu.addItem("Item 1");
        this.menu.addItem("Item 2");
        this.menu.addItem("Item 3");

        this.setSize(600, 480);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void addComponents() {
        // The text with the label
        this.bar.add(this.label, BorderLayout.LINE_START);

        // Add empty space between label and menu.
        this.bar.add(Box.createHorizontalGlue(), BorderLayout.CENTER);

        // The menu
        this.bar.add(this.menu, BorderLayout.LINE_END);

        this.add(this.bar, BorderLayout.PAGE_START);
    }

    public JPanel getBar() {
        return this.bar;
    }

    public JLabel getLabel() {
        return this.label;
    }

    public JComboBox<String> getBox() {
        return this.menu;
    }
}
