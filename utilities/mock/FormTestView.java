package utilities.mock;

import utilities.IForm;
import javax.swing.*;

/**
 * This is a mock object, and only used for testing.
 *
 * @author  Dino Opijac
 * @since   14/05/14
 */
public class FormTestView implements IForm {
    private JComboBox<String> comboBox = new JComboBox<>();
    private JPasswordField passwordField = new JPasswordField();
    private JTextArea textArea = new JTextArea();
    private JTextField textField = new JTextField();
    private JTextField intTextField = new JTextField();
    private JTextField floatTextField = new JTextField();
    private JTextField doubleTextField = new JTextField();
    private JTextField charTextField = new JTextField();

    // Only to display window
    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame();

    public FormTestView() {
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.pack();
        this.frame.add(this.panel);
    }

    public void addComboBoxItem(String item) {
        this.comboBox.addItem(item);
    }
    public void setPasswordField(String password) {
        this.passwordField.setText(password);
    }
    public void setTextArea(String text) {
        this.textArea.setText(text);
    }
    public void setTextField(String text) {
        this.textField.setText(text);
    }
    public void setIntTextField(int text) {
        this.intTextField.setText(Integer.toString(text));
    }
    public void setFloatTextField(float text) {
        this.floatTextField.setText(Float.toString(text));
    }
    public void setDoubleTextField(double text) {
        this.doubleTextField.setText(Double.toString(text));
    }
    public void setCharTextField(char text) {
        this.charTextField.setText(Character.toString(text));
    }

    public String getComboBoxText() {
        return this.comboBox.getItemAt(this.comboBox.getSelectedIndex());
    }
    public String getPasswordFieldText() {
        return String.valueOf(this.passwordField.getPassword());
    }
    public String getTextAreaText() {
        return this.textArea.getText();
    }
    public String getTextFieldText() {
        return this.textField.getText();
    }
    public String getIntTextFieldText() {
        return intTextField.getText();
    }
    public String getFloatTextFieldText() {
        return floatTextField.getText();
    }
    public String getDoubleTextFieldText() {
        return doubleTextField.getText();
    }
    public String getCharTextFieldText() {
        return charTextField.getText();
    }
    public JPanel getPanel() {
        return panel;
    }
    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormTestView view = new FormTestView();
                view.getFrame().setVisible(true);
            }
        });
    }
}

