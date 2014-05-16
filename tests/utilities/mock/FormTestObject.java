package tests.utilities.mock;

/**
 * This is a mock object, and only used for testing.
 *
 * @author  Dino Opijac
 * @since   14/05/14
 */
public class FormTestObject {
    private String comboBox;
    private String passwordField;
    private String textArea;
    private String textField;
    private int intTextField;
    private float floatTextField;
    private double doubleTextField;
    private char charTextField;

    public void setComboBox(String comboBox) {
        this.comboBox = comboBox;
    }
    public void setPasswordField(String password) {
        this.passwordField = password;
    }
    public void setTextArea(String area) {
        this.textArea = area;
    }
    public void setTextField(String field) {
        this.textField = field;
    }
    public void setIntTextField(int i) {
        this.intTextField = i;
    }
    public void setFloatTextField(float f) {
        this.floatTextField = f;
    }
    public void setDoubleTextField(double d) {
        this.doubleTextField = d;
    }
    public void setCharTextField(char c) {
        this.charTextField = c;
    }

    public String getComboBox() {
        return this.comboBox;
    }
    public String getPasswordField() {
        return this.passwordField;
    }
    public String getTextArea() {
        return this.textArea;
    }
    public String getTextField() {
        return this.textField;
    }
    public int getIntTextField() {
        return this.intTextField;
    }
    public float getFloatTextField() { return this.floatTextField; }
    public double getDoubleTextField() { return this.doubleTextField; }
    public char getCharTextField() { return this.charTextField; }

    @Override
    public String toString() {
        return String.format("ComboBox: %s, passwordField: %s, textArea: %s, textField: %s, int: %d, float: %f, double: %f, char: %c",
                this.comboBox, this.passwordField, this.textArea, this.textField, this.intTextField,
                this.floatTextField, this.doubleTextField, this.charTextField);
    }
}
