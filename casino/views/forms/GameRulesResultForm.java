package casino.views.forms;

import casino.MainFrame;
import utilities.GridBagUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author  John Mogensen
 * @since   21/05/2014
 */
public class GameRulesResultForm extends JPanel {
    private JLabel titleLabel = new JLabel();
    private JTextArea textTextArea = new JTextArea();
    private JButton confirmButton = new JButton();
    private JButton cancelButton = new JButton("Cancel");


    public GameRulesResultForm(String title, String text, String buttonText) {
        this.titleLabel.setText(title);
        this.textTextArea.setText(text);
        this.confirmButton.setText(buttonText);

        this.configure();
        this.addComponents();
    }

    private void configure() {
        this.setLayout(new GridBagLayout());

        this.textTextArea.setEditable(false);
        this.textTextArea.setBackground(MainFrame.getInstance().getBackground());
    }

    private void addComponents() {
        GridBagUtilities.makeCell(this, this.titleLabel,    new Point(0, 0), 0.8, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.textTextArea,  new Point(0, 1), 0.8, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.confirmButton, new Point(0, 2), 0.1, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.cancelButton,  new Point(1, 2), 0.1, 1, GridBagConstraints.HORIZONTAL);
    }

    public JLabel getTitleLabel() {
        return this.titleLabel;
    }

    public JTextArea getTextTextArea() {
        return this.textTextArea;
    }

    public JButton getConfirmButton() {
        return this.confirmButton;
    }

    public JButton getCancelButton() {
        return this.cancelButton;
    }
}
