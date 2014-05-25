package casino.views.forms;

import utilities.GridBagUtilities;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;

/**
 * @author  Dino Opijac
 * @since   20/05/2014
 */
public class SimpleForm extends JPanel {
    private JLabel textLabel;
    private JFormattedTextField formattedField;

    private JButton confirmButton = new JButton("OK");

    /**
     * Disable default constructor
     */
    private SimpleForm() {}

    /**
     * A simple form that sets the initial text of the label
     * @param   text        text of the label
     */
    public SimpleForm(String text) {
        this(text, null, null);
    }

    /**
     * A simple form that sets the initial text of the label and the confirm button.
     *
     * @param   text        the initial label text
     * @param   buttonText  the text of the confirm button
     */
    public SimpleForm(String text, String buttonText) {
        this(text, buttonText, null);
    }

    /**
     * A simple form that sets the initial text of the label and a formatter.
     * For formatting see {@link utilities.ComponentUtilities#createMaskFormat(String)}
     *
     * @see     utilities.ComponentUtilities#createMaskFormat(String)
     * @param   text        the initial label text
     * @param   buttonText  the text of the confirm button
     * @param   formatter   the desired formatter
     */
    public SimpleForm(String text, String buttonText, MaskFormatter formatter) {
        this.confirmButton  = (buttonText == null) ? new JButton("OK") : new JButton(buttonText);
        this.formattedField = (formatter == null) ? new JFormattedTextField() : new JFormattedTextField(formatter);

        this.textLabel = new JLabel();
        this.textLabel.setText(text);

        this.configure();
        this.addComponents();
    }

    private void configure() {
        this.setLayout(new GridBagLayout());
    }

    private void addComponents() {
        GridBagUtilities.makeCell(this, this.textLabel,         new Point(0, 0), 0.2, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.formattedField,    new Point(1, 0), 0.7, 1, GridBagConstraints.HORIZONTAL);
        GridBagUtilities.makeCell(this, this.confirmButton,     new Point(2, 0), 0.1, 1, GridBagConstraints.HORIZONTAL);
    }

    public JLabel getTextLabel() {
        return this.textLabel;
    }

    public JButton getConfirmButton() {
        return this.confirmButton;
    }

    public JFormattedTextField getFormattedField() {
        return this.formattedField;
    }

    @Override
    public String toString() {
        return String.format("label: %s field: %s", this.textLabel.getText(), this.formattedField.getText());
    }
}
